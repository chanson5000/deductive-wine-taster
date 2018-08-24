package com.wineguesser.deductive.ui;

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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wineguesser.deductive.R;
import com.wineguesser.deductive.repository.RepoKeyContract;
import com.wineguesser.deductive.util.WineKeyConverter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DeductionFormActivity extends AppCompatActivity implements DeductionFormContract,
        RepoKeyContract {

    private static final String LOG_TAG = DeductionFormActivity.class.getSimpleName();

    private ViewPager mPager;
    private SharedPreferences mWinePreferences;
    private SharedPreferences mActivityPreferences;
    private boolean mIsRedWine;

    // Set a strong reference to the listener so that it avoids garbage collection.
    private SharedPreferences.OnSharedPreferenceChangeListener mSharedPreferenceChangeListener;

    private Context mContext;
    private SightFragment mSightFragment;
    private NoseFragment mNoseFragment;
    private PalateFragmentA mPalateFragmentA;
    private PalateFragmentB mPalateFragmentB;
    private InitialConclusionFragment mInitialFragment;
    private FinalConclusionFragment mFinalFragment;

    private static DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        Intent parentIntent = getIntent();
        mActivityPreferences = getPreferences(Context.MODE_PRIVATE);

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

        SharedPreferences.Editor editor = mActivityPreferences.edit();
        FragmentManager mFragmentManager = getSupportFragmentManager();

        if (parentIntent != null && parentIntent.hasExtra(IS_RED_WINE)) {
            setContentView(R.layout.activity_red_deduction_form);

            editor.putString(IS_RED_WINE, RED_WINE);
            mIsRedWine = true;
            mDatabaseReference = mDatabase.getReference(DB_RED_DESC_PATH);

            mWinePreferences =
                    getSharedPreferences(RED_WINE_FORM_PREFERENCES, Context.MODE_PRIVATE);
            mPager = findViewById(R.id.view_pager_red_deduction);
            PagerAdapter pagerAdapter = new DeductionFormPagerAdapter(mFragmentManager);
            mPager.setAdapter(pagerAdapter);
        } else {
            setContentView(R.layout.activity_white_deduction_form);

            editor.putString(IS_RED_WINE, WHITE_WINE);
            mIsRedWine = false;
            mDatabaseReference = mDatabase.getReference(DB_WHITE_DESC_PATH);

            mWinePreferences =
                    getSharedPreferences(WHITE_WINE_FORM_PREFERENCES, Context.MODE_PRIVATE);
            mPager = findViewById(R.id.view_pager_white_deduction);
            PagerAdapter pagerAdapter = new DeductionFormPagerAdapter(mFragmentManager);
            mPager.setAdapter(pagerAdapter);
        }

        editor.apply();


        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        });
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
                Toast.makeText(this, "Form Cleared!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        unRegisterSharedPreferencesListener();
        setCurrentPageInPreferences(getCurrentPageFromPager());
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

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private void registerPreferencesListener() {
        mWinePreferences.registerOnSharedPreferenceChangeListener(mSharedPreferenceChangeListener
                = ((sharedPreferences, key) -> {

            View view = findViewById(castKey(key));

            if (view != null) {
                if (view instanceof RadioGroup) {
                    ((RadioGroup) view).check(sharedPreferences.getInt(key, NONE_SELECTED));
                } else if (view instanceof CheckBox) {
                    ((CheckBox) view).setChecked(castChecked(sharedPreferences.getInt(key, NOT_CHECKED)));
                } else if (view instanceof Switch) {
                    ((Switch) view).setChecked(castChecked(sharedPreferences.getInt(key, NOT_CHECKED)));
                } else if (view instanceof MultiAutoCompleteTextView) {
                    ((MultiAutoCompleteTextView) view).setText(sharedPreferences.getString(key, ""));
                } else if (view instanceof AutoCompleteTextView) {
                    ((AutoCompleteTextView) view).setText(sharedPreferences.getString(key, ""));
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
            } else if (AllAutoMultiText.contains(key)) {
                winePreferencesEditor.putString(entry.getKey(), "");
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

    public void onSubmitFinalConclusion(View view) {
        // Retrieve all the current form selections from preferences.
        Map<String, ?> allEntries = mWinePreferences.getAll();

        SparseIntArray wineFormSelections = new SparseIntArray();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Integer wineFormKey = castKey(entry.getKey());

            // Combing through our preference keys and parsing for relevance.
            // Checking for radio groups.
            if (AllRadioGroups.contains(wineFormKey)) {
                Integer radioButtonSelection = Integer.parseInt(entry.getValue().toString());
                // Checking to see if a selection has been made.
                if (radioButtonSelection != NONE_SELECTED) {
                    // If a selection has been made it would have included
                    wineFormSelections.put(radioButtonSelection, CHECKED);
                }
            } else if (AllCheckBoxes.contains(wineFormKey)) {
                Integer value = Integer.parseInt(entry.getValue().toString());
                if (value == CHECKED) {
                    wineFormSelections.put(wineFormKey, value);
                }
            }
        }

        WineKeyConverter converter = new WineKeyConverter();
        HashMap<String, Integer> converted = converter.convertToDataFormat(wineFormSelections);

        scoreWine(converted);
    }


    public void scoreWine(HashMap<String, Integer> wineToCompare) {

        // <Wine Id, Wine Score>
        HashMap<String, Integer> wineScores = new HashMap<>();

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot varietyRecord : dataSnapshot.getChildren()) {
                    String wineId = varietyRecord.getKey();
                    int currentWineScore = 0;
                    for (DataSnapshot varietyDescriptor : varietyRecord.getChildren()) {
                        if (wineToCompare.containsKey(varietyDescriptor.getKey())) {
                            currentWineScore++;
                        }
                    }
                    wineScores.put(wineId, currentWineScore);
                }

                // Retrieve the wine key with the highest score.
                String winnerId = Collections.max(wineScores.entrySet(), (wineId, wineScore)
                        -> wineId.getValue() - wineScore.getValue()).getKey();

                if (winnerId != null) {
                    Intent intent = new Intent(mContext, ActualWineActivity.class);
                    intent.putExtra(WINNING_WINE_ID, winnerId);
                    if (mIsRedWine) {
                        intent.putExtra(IS_RED_WINE, true);
                    }
                    AutoCompleteTextView viewFinalConclusionText = findViewById(TEXT_SINGLE_FINAL_GRAPE_VARIETY);
                    String finalVarietyText = viewFinalConclusionText.getText().toString();
                    intent.putExtra(USER_GUESSED_WINE, finalVarietyText);
                    startActivity(intent);
                } else {
                    Toast.makeText(mContext,
                            "Unable To Calculate Winner", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(mContext, "Database Error", Toast.LENGTH_SHORT).show();
                Log.e(LOG_TAG, databaseError.toString());
            }
        };

        mDatabaseReference.addListenerForSingleValueEvent(listener);
    }

    private void wipeSharedPreferences() {
        SharedPreferences.Editor editor = mWinePreferences.edit();
        editor.clear();
        editor.apply();
    }
}
