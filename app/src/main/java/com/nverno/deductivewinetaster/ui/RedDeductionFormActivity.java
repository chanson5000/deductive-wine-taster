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
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.nverno.deductivewinetaster.R;

import java.util.Map;

public class RedDeductionFormActivity extends AppCompatActivity implements RedWineContract {

    private static final int NUM_PAGES = 3;

    private ViewPager mPager;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences mActivityPreferences;
    private boolean mResettingScrollView;

    private SharedPreferences.OnSharedPreferenceChangeListener mSharedPreferenceChangeListener;

    RedSightFragment mRedWineSightFragment;
    RedNoseFragment mRedWineNoseFragment;
    RedPalateFragment mRedWinePalateFragment;

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
                if (mResettingScrollView && position == 0) {
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
                clearAllFragmentPreferences();
                mPager.setCurrentItem(0);
                Toast.makeText(this, "Form Cleared!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void clearAllFragmentPreferences() {
        resetSharedPreferences();
//        wipeSharedPreferences();
//        clearRedSightFragmentPreferences();
//        clearRedNoseFragmentPreferences();
//        clearRedPalateFragmentPreferences();
    }

    private void clearRedSightFragmentPreferences() {
        saveRadioGroupState(CLARITY, NONE_SELECTED);
        saveRadioGroupState(CONCENTRATION, NONE_SELECTED);
        saveRadioGroupState(COLOR, NONE_SELECTED);
        saveRadioGroupState(SECONDARY_COLOR, NONE_SELECTED);
        saveRadioGroupState(RIM_VARIATION, NONE_SELECTED);
        saveRadioGroupState(EXTRACT_STAINING, NONE_SELECTED);
        saveRadioGroupState(TEARING, NONE_SELECTED);
        saveRadioGroupState(GAS_EVIDENCE, NONE_SELECTED);
    }

    private void clearRedNoseFragmentPreferences() {

        saveCheckBoxState(FAULTY_TCA, NOT_CHECKED);
        saveCheckBoxState(FAULTY_HYDROGEN_SULFIDE, NOT_CHECKED);
        saveCheckBoxState(FAULTY_VOLATILE_ACIDITY, NOT_CHECKED);
        saveCheckBoxState(FAULTY_ETHYL_ACETATE, NOT_CHECKED);
        saveCheckBoxState(FAULTY_BRETT, NOT_CHECKED);
        saveCheckBoxState(FAULTY_OXIDIZATION, NOT_CHECKED);
        saveCheckBoxState(FAULTY_OTHER, NOT_CHECKED);
        saveRadioGroupState(NOSE_INTENSITY, NONE_SELECTED);
        saveRadioGroupState(NOSE_AGE_ASSESSMENT, NONE_SELECTED);
        saveCheckBoxState(NOSE_FRUIT_RED, NOT_CHECKED);
        saveCheckBoxState(NOSE_FRUIT_BLACK, NOT_CHECKED);
        saveCheckBoxState(NOSE_FRUIT_BLUE, NOT_CHECKED);
        saveCheckBoxState(NOSE_FRUIT_CHARACTER_RIPE, NOT_CHECKED);
        saveCheckBoxState(NOSE_FRUIT_CHARACTER_FRESH, NOT_CHECKED);
        saveCheckBoxState(NOSE_FRUIT_CHARACTER_TART, NOT_CHECKED);
        saveCheckBoxState(NOSE_FRUIT_CHARACTER_BAKED, NOT_CHECKED);
        saveCheckBoxState(NOSE_FRUIT_CHARACTER_STEWED, NOT_CHECKED);
        saveCheckBoxState(NOSE_FRUIT_CHARACTER_DRIED, NOT_CHECKED);
        saveCheckBoxState(NOSE_FRUIT_CHARACTER_DESICATTED, NOT_CHECKED);
        saveCheckBoxState(NOSE_FRUIT_CHARACTER_BRUISED, NOT_CHECKED);
        saveCheckBoxState(NOSE_FRUIT_CHARACTER_JAMMY, NOT_CHECKED);
        saveCheckBoxState(NOSE_NON_FRUIT_FLORAL, NOT_CHECKED);
        saveCheckBoxState(NOSE_NON_FRUIT_VEGETAL, NOT_CHECKED);
        saveCheckBoxState(NOSE_NON_FRUIT_HERBAL, NOT_CHECKED);
        saveCheckBoxState(NOSE_NON_FRUIT_SPICE, NOT_CHECKED);
        saveCheckBoxState(NOSE_NON_FRUIT_ANIMAL, NOT_CHECKED);
        saveCheckBoxState(NOSE_NON_FRUIT_BARN, NOT_CHECKED);
        saveCheckBoxState(NOSE_NON_FRUIT_PETROL, NOT_CHECKED);
        saveCheckBoxState(NOSE_NON_FRUIT_FERMENTATION, NOT_CHECKED);
        saveCheckBoxState(NOSE_EARTH_FOREST_FLOOR, NOT_CHECKED);
        saveCheckBoxState(NOSE_EARTH_COMPOST, NOT_CHECKED);
        saveCheckBoxState(NOSE_EARTH_MUSHROOMS, NOT_CHECKED);
        saveCheckBoxState(NOSE_EARTH_POTTING_SOIL, NOT_CHECKED);
        saveCheckBoxState(NOSE_MINERAL_MINERAL, NOT_CHECKED);
        saveCheckBoxState(NOSE_MINERAL_WET_STONE, NOT_CHECKED);
        saveCheckBoxState(NOSE_MINERAL_LIMESTONE, NOT_CHECKED);
        saveCheckBoxState(NOSE_MINERAL_CHALK, NOT_CHECKED);
        saveCheckBoxState(NOSE_MINERAL_SLATE, NOT_CHECKED);
        saveCheckBoxState(NOSE_MINERAL_FLINT, NOT_CHECKED);
        saveCheckBoxState(NOSE_WOOD, NOT_CHECKED);
        saveRadioGroupState(NOSE_WOOD_OLD_VS_NEW, NONE_SELECTED);
        saveRadioGroupState(NOSE_WOOD_LARGE_VS_SMALL, NONE_SELECTED);
        saveRadioGroupState(NOSE_WOOD_FRENCH_VS_AMERICAN, NONE_SELECTED);
    }

    private void clearRedPalateFragmentPreferences() {
        saveRadioGroupState(PALATE_SWEETNESS, NONE_SELECTED);
        saveCheckBoxState(PALATE_FRUIT_RED, NOT_CHECKED);
        saveCheckBoxState(PALATE_FRUIT_BLACK, NOT_CHECKED);
        saveCheckBoxState(PALATE_FRUIT_BLUE, NOT_CHECKED);
        saveCheckBoxState(PALATE_FRUIT_CHARACTER_RIPE, NOT_CHECKED);
        saveCheckBoxState(PALATE_FRUIT_CHARACTER_FRESH, NOT_CHECKED);
        saveCheckBoxState(PALATE_FRUIT_CHARACTER_TART, NOT_CHECKED);
        saveCheckBoxState(PALATE_FRUIT_CHARACTER_BAKED, NOT_CHECKED);
        saveCheckBoxState(PALATE_FRUIT_CHARACTER_STEWED, NOT_CHECKED);
        saveCheckBoxState(PALATE_FRUIT_CHARACTER_DRIED, NOT_CHECKED);
        saveCheckBoxState(PALATE_FRUIT_CHARACTER_DESICATTED, NOT_CHECKED);
        saveCheckBoxState(PALATE_FRUIT_CHARACTER_BRUISED, NOT_CHECKED);
        saveCheckBoxState(PALATE_FRUIT_CHARACTER_JAMMY, NOT_CHECKED);
        saveCheckBoxState(PALATE_NON_FRUIT_FLORAL, NOT_CHECKED);
        saveCheckBoxState(PALATE_NON_FRUIT_VEGETAL, NOT_CHECKED);
        saveCheckBoxState(PALATE_NON_FRUIT_HERBAL, NOT_CHECKED);
        saveCheckBoxState(PALATE_NON_FRUIT_SPICE, NOT_CHECKED);
        saveCheckBoxState(PALATE_NON_FRUIT_ANIMAL, NOT_CHECKED);
        saveCheckBoxState(PALATE_NON_FRUIT_BARN, NOT_CHECKED);
        saveCheckBoxState(PALATE_NON_FRUIT_PETROL, NOT_CHECKED);
        saveCheckBoxState(PALATE_NON_FRUIT_FERMENTATION, NOT_CHECKED);
        saveCheckBoxState(PALATE_EARTH_FOREST_FLOOR, NOT_CHECKED);
        saveCheckBoxState(PALATE_EARTH_COMPOST, NOT_CHECKED);
        saveCheckBoxState(PALATE_EARTH_MUSHROOMS, NOT_CHECKED);
        saveCheckBoxState(PALATE_EARTH_POTTING_SOIL, NOT_CHECKED);
        saveCheckBoxState(PALATE_MINERAL_MINERAL, NOT_CHECKED);
        saveCheckBoxState(PALATE_MINERAL_WET_STONE, NOT_CHECKED);
        saveCheckBoxState(PALATE_MINERAL_LIMESTONE, NOT_CHECKED);
        saveCheckBoxState(PALATE_MINERAL_CHALK, NOT_CHECKED);
        saveCheckBoxState(PALATE_MINERAL_SLATE, NOT_CHECKED);
        saveCheckBoxState(PALATE_MINERAL_FLINT, NOT_CHECKED);
        saveCheckBoxState(PALATE_WOOD, NOT_CHECKED);
        saveRadioGroupState(PALATE_WOOD_OLD_VS_NEW, NONE_SELECTED);
        saveRadioGroupState(PALATE_WOOD_LARGE_VS_SMALL, NONE_SELECTED);
        saveRadioGroupState(PALATE_WOOD_FRENCH_VS_AMERICAN, NONE_SELECTED);
    }

    private void saveRadioGroupState(int key, int state) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(Integer.toString(key), state);
        editor.apply();
    }

//    private void saveRadioGroupState(String key, int state) {
//        SharedPreferences.Editor editor = mSharedPreferences.edit();
//        editor.putInt(key, state);
//        editor.apply();
//    }

    private void saveCheckBoxState(int key, int checkedInt) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(Integer.toString(key), checkedInt);
        editor.apply();
    }


    @Override
    public void onPause() {
        super.onPause();
        unRegisterSharedPreferencesListener();
        saveCurrentPageSelection();
    }

    private void unRegisterSharedPreferencesListener() {
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(mSharedPreferenceChangeListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPager.setCurrentItem(mActivityPreferences.getInt(CURRENT_PAGE, 0));
        syncCurrentTitle();
        registerPreferencesListener();
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

    private void registerPreferencesListener() {
        mSharedPreferences.registerOnSharedPreferenceChangeListener(mSharedPreferenceChangeListener
                = ((sharedPreferences, key) -> {

            View view = findViewById(Integer.parseInt(key));

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

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private void syncCurrentTitle() {
        switch (mPager.getCurrentItem()) {
            case RED_SIGHT_PAGE:
                setTitle(RED_SIGHT_PAGE_TITLE);
                break;
            case RED_NOSE_PAGE:
                setTitle(RED_NOSE_PAGE_TITLE);
                break;
            case RED_PALATE_PAGE:
                setTitle(RED_PALATE_PAGE_TITLE);
                break;
            default:
                break;
        }
    }

    private void saveCurrentPageSelection() {
        SharedPreferences.Editor editor = mActivityPreferences.edit();
        editor.putInt(CURRENT_PAGE, mPager.getCurrentItem());
        editor.apply();
    }

    private void resetSharedPreferences() {
        Map<String, ?> allEntries = mSharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            View view = findViewById(Integer.parseInt(entry.getKey()));
            if (view instanceof RadioGroup) {
                saveRadioGroupState(view.getId(), NONE_SELECTED);
            } else if (view instanceof CheckBox) {
                saveCheckBoxState(view.getId(), NOT_CHECKED);
            } else if (view instanceof Switch) {
                saveCheckBoxState(view.getId(), NOT_CHECKED);
            }
        }
    }

    private void wipeSharedPreferences() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void onRadioButtonClicked(View view) {
        RadioGroup radioGroup = (RadioGroup) view.getParent();
        saveRadioGroupState(radioGroup.getId(), radioGroup.getCheckedRadioButtonId());
    }

    public void onCheckBoxButtonClicked(View view) {
        CheckBox checkBox = (CheckBox) view;
        saveCheckBoxState(checkBox.getId(), castChecked(checkBox.isChecked()));
    }

    class RedDeductionFormPagerAdapter extends FragmentPagerAdapter {
        RedDeductionFormPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            mRedWineSightFragment = new RedSightFragment();
            mRedWineNoseFragment = new RedNoseFragment();
            mRedWinePalateFragment = new RedPalateFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return mRedWineSightFragment;
                case 1:
                    return mRedWineNoseFragment;
                case 2:
                    return mRedWinePalateFragment;
                default:
                    break;
            }
            return null;
        }
    }
}

