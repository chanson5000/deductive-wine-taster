package com.nverno.deductivewinetaster.ui;

import android.content.Context;
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
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.nverno.deductivewinetaster.R;

import java.util.Map;

public class RedDeductionFormActivity extends AppCompatActivity implements RedWineContract {

    private static final int NUM_PAGES = 4;

    private ViewPager mPager;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences mActivityPreferences;
    private boolean mResettingScrollView;

    // Set a strong reference to the listener so that it avoids garbage collection.
    private SharedPreferences.OnSharedPreferenceChangeListener mSharedPreferenceChangeListener;

    RedSightFragment mRedWineSightFragment;
    RedNoseFragment mRedWineNoseFragment;
    RedPalateFragmentA mRedWinePalateFragmentA;
    RedPalateFragmentB mRedWinePalateFragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_deduction_form);

        mSharedPreferences = getSharedPreferences(RED_WINE_FORM_PREFERENCES, Context.MODE_PRIVATE);
        mActivityPreferences = getPreferences(Context.MODE_PRIVATE);

        mPager = findViewById(R.id.view_pager_red_deduction);
        PagerAdapter pagerAdapter = new RedDeductionFormPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                // TODO: Get the scrollY to reset after a rotation. (ScrollView returns null)
                if (mRedWineSightFragment.mScrollViewSightRed != null
                        && mResettingScrollView
                        && position == 0) {
                    mRedWineSightFragment.mScrollViewSightRed.scrollTo(0, 0);
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
        mSharedPreferences.registerOnSharedPreferenceChangeListener(mSharedPreferenceChangeListener
                = ((sharedPreferences, key) -> {

            View view = findViewById(castKey(key));

            if (view != null) {
                if (view instanceof RadioGroup) {
                    ((RadioGroup) view).check(sharedPreferences.getInt(key, NONE_SELECTED));
                } else if (view instanceof CheckBox) {
                    ((CheckBox) view).setChecked(castChecked(sharedPreferences.getInt(key, NOT_CHECKED)));
                } else if (view instanceof Switch) {
                    ((Switch) view).setChecked(castChecked(sharedPreferences.getInt(key, NOT_CHECKED)));
                }
            }
        }));
    }

    private void unRegisterSharedPreferencesListener() {
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(mSharedPreferenceChangeListener);
    }

    private void resetSharedPreferences() {
        Map<String, ?> allEntries = mSharedPreferences.getAll();
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            int key = castKey(entry.getKey());
            if (AllRadioGroups.contains(key)) {
                edit.putInt(entry.getKey(), NONE_SELECTED);
            } else if (AllCheckBoxes.contains(key)) {
                edit.putInt(entry.getKey(), NOT_CHECKED);
            } else if (AllSwitches.contains(key)) {
                edit.putInt(entry.getKey(), NOT_CHECKED);
            }
        }
        edit.apply();
    }

    private void saveRadioGroupState(int key, int state) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(Integer.toString(key), state);
        editor.apply();
    }

    private void saveCheckBoxState(int key, int checkedInt) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
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
            mRedWineNoseFragment.syncRedNoseWoodRadioState();
        } else if (switchId == PALATE_WOOD) {
            mRedWinePalateFragmentA.syncRedPalateWoodRadioState();
        }
    }

    private void saveCurrentPageSelection() {
        SharedPreferences.Editor editor = mActivityPreferences.edit();
        editor.putInt(CURRENT_PAGE, mPager.getCurrentItem());
        editor.apply();
    }

    private void syncCurrentTitle() {
        switch (mPager.getCurrentItem()) {
            case RED_SIGHT_PAGE:
                setTitle(RED_SIGHT_PAGE_TITLE);
                break;
            case RED_NOSE_PAGE:
                setTitle(RED_NOSE_PAGE_TITLE);
                break;
            case RED_PALATE_PAGE_A:
                setTitle(RED_PALATE_PAGE_TITLE);
                break;
            case RED_PALATE_PAGE_B:
                setTitle(RED_PALATE_PAGE_TITLE);
                break;
            default:
                break;
        }
    }

    class RedDeductionFormPagerAdapter extends FragmentPagerAdapter {
        RedDeductionFormPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            mRedWineSightFragment = new RedSightFragment();
            mRedWineNoseFragment = new RedNoseFragment();
            mRedWinePalateFragmentA = new RedPalateFragmentA();
            mRedWinePalateFragmentB = new RedPalateFragmentB();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case RED_SIGHT_PAGE:
                    return mRedWineSightFragment;
                case RED_NOSE_PAGE:
                    return mRedWineNoseFragment;
                case RED_PALATE_PAGE_A:
                    return mRedWinePalateFragmentA;
                case RED_PALATE_PAGE_B:
                    return mRedWinePalateFragmentB;
                default:
                    break;
            }
            return null;
        }
    }

    private void wipeSharedPreferences() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}

