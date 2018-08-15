package com.nverno.deductivewinetaster.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.nverno.deductivewinetaster.R;

import java.util.Map;

public class DeductionFormActivity extends AppCompatActivity implements DeductionFormContract {

    private ViewPager mPager;
    private SharedPreferences mWinePreferences;
    private SharedPreferences mActivityPreferences;
    private boolean mResettingScrollView;

    // Set a strong reference to the listener so that it avoids garbage collection.
    private SharedPreferences.OnSharedPreferenceChangeListener mSharedPreferenceChangeListener;

    SightFragment mSightFragment;
    NoseFragment mNoseFragment;
    PalateFragmentA palateFragmentA;
    PalateFragmentB palateFragmentB;
    InitialConclusionFragment mInitialConclusionFragment;
    FinalConclusionFragment mFinalConclusionFragment;

    private boolean isRedWine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent parentIntent = getIntent();
        mActivityPreferences = getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = mActivityPreferences.edit();

        if (parentIntent != null && parentIntent.hasExtra(WINE_TYPE)) {
            setContentView(R.layout.activity_red_deduction_form);

            editor.putString(WINE_TYPE, RED_WINE);

            mWinePreferences =
                    getSharedPreferences(RED_WINE_FORM_PREFERENCES, Context.MODE_PRIVATE);
            mPager = findViewById(R.id.view_pager_red_deduction);
            PagerAdapter pagerAdapter = new DeductionFormPagerAdapter(getSupportFragmentManager());
            mPager.setAdapter(pagerAdapter);
        } else {
            setContentView(R.layout.activity_white_deduction_form);

            editor.putString(WINE_TYPE, WHITE_WINE);

            mWinePreferences =
                    getSharedPreferences(WHITE_WINE_FORM_PREFERENCES, Context.MODE_PRIVATE);
            mPager = findViewById(R.id.view_pager_white_deduction);
            PagerAdapter pagerAdapter = new DeductionFormPagerAdapter(getSupportFragmentManager());
            mPager.setAdapter(pagerAdapter);
        }

        editor.apply();



        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                // TODO: Get the scrollY to reset after a rotation. (ScrollView returns null)
                if (mSightFragment.mScrollViewSight != null
                        && mResettingScrollView
                        && position == 0) {
                    mSightFragment.mScrollViewSight.scrollTo(0, 0);
                    mResettingScrollView = false;
                }
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
                mResettingScrollView = true;
                resetSharedPreferences();
                mPager.setCurrentItem(0);
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
        saveCurrentPageSelection();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPager.setCurrentItem(mActivityPreferences.getInt(CURRENT_PAGE, 0));
        syncCurrentTitle();
        registerPreferencesListener();
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
        SharedPreferences.Editor edit = mWinePreferences.edit();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            int key = castKey(entry.getKey());
            if (AllRadioGroups.contains(key)) {
                edit.putInt(entry.getKey(), NONE_SELECTED);
            } else if (AllCheckBoxes.contains(key)) {
                edit.putInt(entry.getKey(), NOT_CHECKED);
            } else if (AllSwitches.contains(key)) {
                edit.putInt(entry.getKey(), NOT_CHECKED);
            } else if (AllAutoText.contains(key)) {
                edit.putString(entry.getKey(), "");
            } else if (AllAutoMultiText.contains(key)) {
                edit.putString(entry.getKey(), "");
            }
        }
        edit.apply();
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
        return checkedInt == 1;
    }

    private int castChecked(boolean checkedBoolean) {
        if (checkedBoolean) {
            return 1;
        } else {
            return 0;
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
        if (switchId == NOSE_WOOD) {
            mNoseFragment.syncWoodRadioState();
        } else if (switchId == PALATE_WOOD) {
            palateFragmentA.syncWoodRadioState();
        }
    }

    private void saveCurrentPageSelection() {
        SharedPreferences.Editor editor = mActivityPreferences.edit();
        editor.putInt(CURRENT_PAGE, mPager.getCurrentItem());
        editor.apply();
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
            case FINAL_CONCLUSION_PAGE:
                setTitle(FINAL_CONCLUSION_PAGE_TITLE);
            default:
                break;
        }
    }

    class DeductionFormPagerAdapter extends FragmentPagerAdapter {
        DeductionFormPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            mSightFragment = new SightFragment();
            mNoseFragment = new NoseFragment();
            palateFragmentA = new PalateFragmentA();
            palateFragmentB = new PalateFragmentB();
            mInitialConclusionFragment = new InitialConclusionFragment();
            mFinalConclusionFragment = new FinalConclusionFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case SIGHT_PAGE:
                    return mSightFragment;
                case NOSE_PAGE:
                    return mNoseFragment;
                case PALATE_PAGE_A:
                    return palateFragmentA;
                case PALATE_PAGE_B:
                    return palateFragmentB;
                case INITIAL_CONCLUSION_PAGE:
                    return mInitialConclusionFragment;
                case FINAL_CONCLUSION_PAGE:
                    return mFinalConclusionFragment;
                default:
                    break;
            }
            return null;
        }
    }

    private void wipeSharedPreferences() {
        SharedPreferences.Editor editor = mWinePreferences.edit();
        editor.clear();
        editor.apply();
    }
}
