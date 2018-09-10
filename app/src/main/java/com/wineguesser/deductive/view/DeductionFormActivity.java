package com.wineguesser.deductive.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.wineguesser.deductive.R;
import com.wineguesser.deductive.repository.DatabaseContract;
import com.wineguesser.deductive.util.GrapeScore;
import com.wineguesser.deductive.util.GrapeResult;

import java.util.Calendar;
import java.util.Map;

public class DeductionFormActivity extends AppCompatActivity implements DeductionFormContract,
        DatabaseContract, GrapeResult {

    private ViewPager mPager;
    private SharedPreferences mWinePreferences;
    private SharedPreferences mActivityPreferences;
    private boolean mIsRedWine;
    private boolean mLaunchingIntent;

    // Set a strong reference to the listener so that it avoids garbage collection.
    private SharedPreferences.OnSharedPreferenceChangeListener mSharedPreferenceChangeListener;

    private Context mContext;
    private SightFragment mSightFragment;
    private NoseFragment mNoseFragment;
    private PalateFragmentA mPalateFragmentA;
    private PalateFragmentB mPalateFragmentB;
    private InitialConclusionFragment mInitialFragment;
    private FinalConclusionFragment mFinalFragment;

    private String mUserFinalVarietyString;
    private String mUserFinalCountryString;
    private String mUserFinalRegionString;
    private String mUserFinalQualityString;
    private Integer mUserFinalVintageInteger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivityPreferences = getPreferences(Context.MODE_PRIVATE);

        Intent parentIntent = getIntent();
        FragmentManager mFragmentManager = getSupportFragmentManager();

        SharedPreferences.Editor editor = mActivityPreferences.edit();
        if (parentIntent != null && parentIntent.hasExtra(IS_RED_WINE)) {
            setTheme(R.style.RedTheme);

            setContentView(R.layout.activity_red_deduction_form);

            editor.putString(IS_RED_WINE, RED_WINE);
            mIsRedWine = true;

            mWinePreferences =
                    getSharedPreferences(RED_WINE_FORM_PREFERENCES, Context.MODE_PRIVATE);
            mPager = findViewById(R.id.view_pager_red_deduction);
            PagerAdapter pagerAdapter = new DeductionFormPagerAdapter(mFragmentManager);
            mPager.setAdapter(pagerAdapter);
        } else {
            setTheme(R.style.WhiteTheme);

            setContentView(R.layout.activity_white_deduction_form);

            editor.putString(IS_RED_WINE, WHITE_WINE);
            mIsRedWine = false;

            mWinePreferences =
                    getSharedPreferences(WHITE_WINE_FORM_PREFERENCES, Context.MODE_PRIVATE);
            mPager = findViewById(R.id.view_pager_white_deduction);
            PagerAdapter pagerAdapter = new DeductionFormPagerAdapter(mFragmentManager);
            mPager.setAdapter(pagerAdapter);
        }
        editor.apply();

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                syncCurrentTitle();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        };

        mPager.addOnPageChangeListener(onPageChangeListener);
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.wine_deduction_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear_selections:
                resetSharedPreferences();
                resetAllTopScroll();
                if (mPager.getCurrentItem() != SIGHT_PAGE) {
                    mPager.setCurrentItem(SIGHT_PAGE);
                }
                Toast.makeText(this, getString(R.string.form_cleared), Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        unRegisterSharedPreferencesListener();
        if (!mLaunchingIntent) {
            setCurrentPageInPreferences(getCurrentPageFromPager());
            mLaunchingIntent = false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setCurrentPage(getCurrentPageFromPreferences());
        syncCurrentTitle();
        registerPreferencesListener();
    }

    private void setCurrentPageInPreferences(int page) {
        SharedPreferences.Editor editor = mActivityPreferences.edit();
        if (mIsRedWine) {
            editor.putInt(CURRENT_PAGE_RED, page);
        } else {
            editor.putInt(CURRENT_PAGE_WHITE, page);
        }
        editor.apply();
    }

    private int getCurrentPageFromPreferences() {
        if (mIsRedWine) {
            return mActivityPreferences.getInt(CURRENT_PAGE_RED, 0);
        } else {
            return mActivityPreferences.getInt(CURRENT_PAGE_WHITE, 0);
        }
    }

    private void setCurrentPage(int page) {
        mPager.setCurrentItem(page);
    }

    private int getCurrentPageFromPager() {
        return mPager.getCurrentItem();
    }

    // Make the built in back press work with the view pager while the
    // Toolbar back press will take you to the parent activity.
    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    // When shared preferences are changed, the view is also updated. This is most useful when
    // clearing preferences
    private void registerPreferencesListener() {
        mWinePreferences.registerOnSharedPreferenceChangeListener(mSharedPreferenceChangeListener
                = ((sharedPreferences, key) -> {

            View view = findViewById(castKey(key));

            if (view != null) {
                if (view instanceof RadioGroup) {
                    ((RadioGroup) view).check(
                            sharedPreferences.getInt(key, NONE_SELECTED));
                } else if (view instanceof CheckBox) {
                    ((CheckBox) view).setChecked(
                            castChecked(sharedPreferences.getInt(key, NOT_CHECKED)));
                } else if (view instanceof Switch) {
                    ((Switch) view).setChecked(
                            castChecked(sharedPreferences.getInt(key, NOT_CHECKED)));
                } else if (view instanceof MultiAutoCompleteTextView) {
                    ((MultiAutoCompleteTextView) view).setText(
                            sharedPreferences.getString(key, ""));
                } else if (view instanceof AutoCompleteTextView) {
                    ((AutoCompleteTextView) view).setText(
                            sharedPreferences.getString(key, ""));
                }
            }
        }));
    }

    private void unRegisterSharedPreferencesListener() {
        mWinePreferences.unregisterOnSharedPreferenceChangeListener(mSharedPreferenceChangeListener);
    }

    private void resetSharedPreferences() {
        Map<String, ?> allEntries = mWinePreferences.getAll();
        SharedPreferences.Editor winePreferencesEditor = mWinePreferences.edit();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            int key = castKey(entry.getKey());
            if (AllRadioGroups.contains(key)) {
                winePreferencesEditor.putInt(entry.getKey(), NONE_SELECTED);
            } else if (AllCheckBoxes.contains(key)) {
                winePreferencesEditor.putInt(entry.getKey(), NOT_CHECKED);
            } else if (AllSwitches.contains(key)) {
                winePreferencesEditor.putInt(entry.getKey(), NOT_CHECKED);
            } else if (AllAutoText.contains(key)) {
                winePreferencesEditor.putString(entry.getKey(), "");
                AutoCompleteTextView autoView = findViewById(key);
                if (autoView != null) {
                    autoView.setText("");
                }
            } else if (AllAutoMultiText.contains(key)) {
                winePreferencesEditor.putString(entry.getKey(), "");
                MultiAutoCompleteTextView multiView = findViewById(key);
                if (multiView != null) {
                    multiView.setText("");
                }
            }
        }
        winePreferencesEditor.apply();
        SharedPreferences.Editor activityPreferencesEditor = mActivityPreferences.edit();
        if (mIsRedWine) {
            activityPreferencesEditor.putInt(RED_SIGHT_Y_SCROLL, 0);
            activityPreferencesEditor.putInt(RED_NOSE_Y_SCROLL, 0);
            activityPreferencesEditor.putInt(RED_PALATE_A_Y_SCROLL, 0);
            activityPreferencesEditor.putInt(RED_PALATE_B_Y_SCROLL, 0);
            activityPreferencesEditor.putInt(RED_INITIAL_Y_SCROLL, 0);
            activityPreferencesEditor.putInt(RED_FINAL_Y_SCROLL, 0);
        } else {
            activityPreferencesEditor.putInt(WHITE_SIGHT_Y_SCROLL, 0);
            activityPreferencesEditor.putInt(WHITE_NOSE_Y_SCROLL, 0);
            activityPreferencesEditor.putInt(WHITE_PALATE_A_Y_SCROLL, 0);
            activityPreferencesEditor.putInt(WHITE_PALATE_B_Y_SCROLL, 0);
            activityPreferencesEditor.putInt(WHITE_INITIAL_Y_SCROLL, 0);
            activityPreferencesEditor.putInt(WHITE_FINAL_Y_SCROLL, 0);
        }

        activityPreferencesEditor.apply();

        activityPreferencesEditor.commit();

    }

    private void saveRadioGroupState(int key, int state) {
        SharedPreferences.Editor editor = mWinePreferences.edit();
        editor.putInt(Integer.toString(key), state);
        editor.apply();
    }

    private void saveCheckBoxState(int key, int checkedInt) {
        SharedPreferences.Editor editor = mWinePreferences.edit();
        editor.putInt(Integer.toString(key), checkedInt);
        editor.apply();
    }

    private int castKey(String key) {
        return Integer.parseInt(key);
    }

    private boolean castChecked(int checkedInt) {
        return checkedInt == CHECKED;
    }

    private int castChecked(boolean checkedBoolean) {
        if (checkedBoolean) {
            return CHECKED;
        } else {
            return NOT_CHECKED;
        }
    }

    public void onRadioButtonClicked(View view) {
        RadioGroup radioGroup = (RadioGroup) view.getParent();
        saveRadioGroupState(radioGroup.getId(), radioGroup.getCheckedRadioButtonId());
    }

    public void onCheckBoxButtonClicked(View view) {
        CheckBox checkBox = (CheckBox) view;
        saveCheckBoxState(checkBox.getId(), castChecked(checkBox.isChecked()));
    }

    public void onSwitchToggleClicked(View view) {
        Switch switchToggle = (Switch) view;
        int switchId = switchToggle.getId();
        boolean checked = switchToggle.isChecked();
        saveCheckBoxState(switchId, castChecked(checked));
        if (switchId == SWITCH_NOSE_WOOD) {
            mNoseFragment.syncWoodRadioState();
        } else if (switchId == SWITCH_PALATE_WOOD) {
            mPalateFragmentA.syncWoodRadioState();
        }
    }

    private void resetAllTopScroll() {
        if (mSightFragment != null) {
            mSightFragment.scrollToTop();

        }
        if (mNoseFragment != null) {
            mNoseFragment.scrollToTop();

        }
        if (mPalateFragmentA != null) {
            mPalateFragmentA.scrollToTop();

        }
        if (mPalateFragmentB != null) {
            mPalateFragmentB.scrollToTop();

        }
        if (mInitialFragment != null) {
            mInitialFragment.scrollToTop();

        }
        if (mFinalFragment != null) {
            mFinalFragment.scrollToTop();
        }
    }

    private void syncCurrentTitle() {
        switch (mPager.getCurrentItem()) {
            case SIGHT_PAGE:
                setTitle(SIGHT_PAGE_TITLE);
                break;
            case NOSE_PAGE:
                setTitle(NOSE_PAGE_TITLE);
                break;
            case PALATE_PAGE_A:
                setTitle(PALATE_PAGE_TITLE);
                break;
            case PALATE_PAGE_B:
                setTitle(PALATE_PAGE_TITLE);
                break;
            case INITIAL_CONCLUSION_PAGE:
                setTitle(INITIAL_CONCLUSION_PAGE_TITLE);
                break;
            case FINAL_CONCLUSION_PAGE:
                setTitle(FINAL_CONCLUSION_PAGE_TITLE);
                break;
            default:
                break;
        }
    }

    class DeductionFormPagerAdapter extends FragmentPagerAdapter {
        DeductionFormPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case SIGHT_PAGE:
                    return new SightFragment();
                case NOSE_PAGE:
                    return new NoseFragment();
                case PALATE_PAGE_A:
                    return new PalateFragmentA();
                case PALATE_PAGE_B:
                    return new PalateFragmentB();
                case INITIAL_CONCLUSION_PAGE:
                    return new InitialConclusionFragment();
                case FINAL_CONCLUSION_PAGE:
                    return new FinalConclusionFragment();
                default:
                    break;
            }
            return null;
        }

        // Using this to save our fragment state/reference on view rotation.
        @NonNull
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment createdFragment = (Fragment) super.instantiateItem(container, position);

            switch (position) {
                case SIGHT_PAGE:
                    mSightFragment = (SightFragment) createdFragment;
                    break;
                case NOSE_PAGE:
                    mNoseFragment = (NoseFragment) createdFragment;
                    break;
                case PALATE_PAGE_A:
                    mPalateFragmentA = (PalateFragmentA) createdFragment;
                    break;
                case PALATE_PAGE_B:
                    mPalateFragmentB = (PalateFragmentB) createdFragment;
                    break;
                case INITIAL_CONCLUSION_PAGE:
                    mInitialFragment = (InitialConclusionFragment) createdFragment;
                    break;
                case FINAL_CONCLUSION_PAGE:
                    mFinalFragment = (FinalConclusionFragment) createdFragment;
                    break;
                default:
                    break;
            }
            return createdFragment;
        }
    }

    private boolean validInputs() {
        AutoCompleteTextView singleTextViewFinalVariety =
                findViewById(TEXT_SINGLE_FINAL_GRAPE_VARIETY);
        AutoCompleteTextView singleTextViewFinalCountry =
                findViewById(TEXT_SINGLE_FINAL_COUNTRY_ORIGIN);
        AutoCompleteTextView singleTextViewFinalRegion =
                findViewById(TEXT_SINGLE_FINAL_REGION);
        AutoCompleteTextView singleTextViewFinalQuality =
                findViewById(TEXT_SINGLE_FINAL_QUALITY);
        AutoCompleteTextView singleTextViewFinalVintage =
                findViewById(TEXT_SINGLE_FINAL_VINTAGE);

        mUserFinalVarietyString = singleTextViewFinalVariety.getText().toString();
        mUserFinalCountryString = singleTextViewFinalCountry.getText().toString();
        mUserFinalRegionString = singleTextViewFinalRegion.getText().toString();
        mUserFinalQualityString = singleTextViewFinalQuality.getText().toString();

        mUserFinalVintageInteger = null;
        String parseInteger = singleTextViewFinalVintage.getText().toString();
        if (!parseInteger.isEmpty()) {
            mUserFinalVintageInteger = Integer.parseInt(parseInteger);
        } else {
            mUserFinalVintageInteger = 0;
        }

        boolean isValid = true;

        // Check that user has provided their conclusion of grape variety.
        if (mIsRedWine && !RedVarieties.contains(mUserFinalVarietyString)) {
            mFinalFragment.errorsFinalForm()
                    .setErrorVariety(getString(R.string.error_input_valid_grape));
            isValid = false;
        } else if (!mIsRedWine && !WhiteVarieties.contains(mUserFinalVarietyString)) {
            mFinalFragment.errorsFinalForm()
                    .setErrorVariety(getString(R.string.error_input_valid_grape));
            isValid = false;
        } else {
            mFinalFragment.errorsFinalForm().setErrorVariety(null);
        }

        if (mUserFinalCountryString.isEmpty()) {
            mFinalFragment.errorsFinalForm()
                    .setErrorCountry(getString(R.string.error_input_country_origin));
            isValid = false;
        } else {
            mFinalFragment.errorsFinalForm().setErrorCountry(null);
        }

        if (mUserFinalRegionString.isEmpty()) {
            mFinalFragment.errorsFinalForm()
                    .setErrorRegion(getString(R.string.error_input_valid_region));
            isValid = false;
        } else {
            mFinalFragment.errorsFinalForm().setErrorRegion(null);
        }

        if (mUserFinalQualityString.isEmpty()) {
            mUserFinalQualityString = "None";
        }

        if (mUserFinalVintageInteger > Calendar.getInstance().get(Calendar.YEAR)
                || mUserFinalVintageInteger < 1900) {
            mFinalFragment.errorsFinalForm()
                    .setErrorVintage(getString(R.string.error_input_valid_vintage));
            isValid = false;
        }

        return isValid;
    }

    private SparseIntArray retrieveSharedPreferencesValues() {
        // Retrieve all the form selections from preferences.
        Map<String, ?> allEntries = mWinePreferences.getAll();

        // Create a SparseIntArray that will contain our normalized map of form selections.
        // SparseIntArray uses less memory but is a little slower. There is no real specific
        // reason for its use here other than seeing it in action as an alternative to HashMap.
        // SparseArrays are an Android only component.
        SparseIntArray wineFormSelections = new SparseIntArray();

        // *** Combing through our form input keys and parsing for relevance. ***
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Integer wineFormKey = castKey(entry.getKey());
            // Radio groups and check boxes will be handled slightly differently because
            // a radio group will return either an Id that will be included in our map or
            // return -1 (for none selected) and result in no map put.
            // Check boxes will just be looking for check or no check.

            if (AllRadioGroups.contains(wineFormKey)) {
                Integer radioButtonSelection = Integer.parseInt(entry.getValue().toString());
                // Checking to see if a selection has been made.
                if (radioButtonSelection != NONE_SELECTED) {
                    // If a selection has been made we add it as checked (value of 1)
                    wineFormSelections.put(radioButtonSelection, CHECKED);
                }
            } else if (AllCheckBoxes.contains(wineFormKey)) {
                Integer value = Integer.parseInt(entry.getValue().toString());
                // Only adding to map if element has been checked.
                if (value == CHECKED) {
                    wineFormSelections.put(wineFormKey, value);
                }
            }
        }

        return wineFormSelections;
    }

    public void onSubmitFinalConclusion(View view) {
        // Do nothing if inputs are not valid. validInputs() will display toasts for bad inputs.
        if (!validInputs()) {
            return;
        }

        SparseIntArray formSelections = retrieveSharedPreferencesValues();

        GrapeScore scoreTask = new GrapeScore(this, mIsRedWine);
        isScoring(true);
        scoreTask.execute(formSelections);
    }

    public void onGrapeResult(String topScoreVariety) {
        resetSharedPreferences();
        resetAllTopScroll();
        setCurrentPageInPreferences(SIGHT_PAGE);
        mLaunchingIntent = true;

        Intent intent = new Intent(mContext, VarietyResultsActivity.class);
        if (mIsRedWine) {
            intent.putExtra(IS_RED_WINE, true);
        }
        // We put our guess into the intent to be launched.
        intent.putExtra(APP_VARIETY_GUESS_ID, topScoreVariety);
        // Putting the user's guess into the intent.
        intent.putExtra(USER_CONCLUSION_VARIETY, mUserFinalVarietyString);
        intent.putExtra(USER_CONCLUSION_COUNTRY, mUserFinalCountryString);
        intent.putExtra(USER_CONCLUSION_REGION, mUserFinalRegionString);
        intent.putExtra(USER_CONCLUSION_QUALITY, mUserFinalQualityString);
        intent.putExtra(USER_CONCLUSION_VINTAGE, mUserFinalVintageInteger);
        isScoring(false);
        // We can now launch the activity that will show results to the user.
        startActivity(intent);
        finish();
    }

    public void isScoring(Boolean loading) {
        if (loading) {
            mFinalFragment.showLoadingIndicator();
        } else {
            mFinalFragment.hideLoadingIndicator();
        }
    }

    public void onGrapeFailure() {
        isScoring(false);
        Toast.makeText(mContext,
                "Unable to score grape variety.", Toast.LENGTH_SHORT).show();
    }
}
