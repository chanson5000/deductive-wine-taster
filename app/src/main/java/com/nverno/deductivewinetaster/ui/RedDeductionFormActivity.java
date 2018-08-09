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
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.nverno.deductivewinetaster.R;
import com.nverno.deductivewinetaster.util.RedWineContract;

import java.util.Map;


public class RedDeductionFormActivity extends AppCompatActivity implements RedWineContract {

    private static final int NUM_PAGES = 3;

    private ViewPager mPager;
    private Context mContext;
    private int mCurrentPage;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedEditor;


    private RadioGroup mRadioGroupClarity;
    private RadioGroup mRadioGroupConcentration;
    private RadioGroup mRadioGroupColor;
    private RadioGroup mRadioGroupSecondaryColor;
    private RadioGroup mRadioGroupRimVariation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_deduction_form);

        mContext = this;
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        mPager = findViewById(R.id.view_pager_red_deduction);
        PagerAdapter pagerAdapter = new RedDeductionFromPagerAdapter(getSupportFragmentManager());

        mPager.setAdapter(pagerAdapter);

        mRadioGroupClarity = findViewById(R.id.radio_group_clarity);
        mRadioGroupConcentration = findViewById(R.id.radio_group_concentration);
        mRadioGroupColor = findViewById(R.id.radio_group_color_redwine);
        mRadioGroupSecondaryColor = findViewById(R.id.radio_group_colorsecondary_redwine);
        mRadioGroupRimVariation = findViewById(R.id.radio_group_rimvariation);



        setTitle("Sight");

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mCurrentPage = 0;
                        setTitle("Sight");
                        break;
                    case 1:
                        mCurrentPage = 1;
                        setTitle("Nose");
                        break;
                    case 2:
                        mCurrentPage = 2;
                        mRadioGroupConcentration.clearCheck();
                        setTitle("Palate");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private int getSelectionState(String key) {
        return sharedPreferences.getInt(CLARITY, 0);
    }

    private Map<String, ?> getAllState() {
        return sharedPreferences.getAll();
    }

    @Override
    public void onPause() {
        super.onPause();
        setSelectionState(CURRENT_PAGE, mCurrentPage);
    }

    @Override
    public void onResume() {
        super.onResume();
        mCurrentPage = sharedPreferences.getInt(CURRENT_PAGE, 0);
        mPager.setCurrentItem(mCurrentPage);
        loadSelectionState();
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    public void loadSelectionState() {
        Map<String, ?> allEntries = getAllState();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            findViewById(entry.getKey(), );
        }
    }

    private void setSelectionState(int key, Boolean state) {
        sharedEditor = sharedPreferences.edit();
        sharedEditor.putBoolean(Integer.toString(key), state);
        sharedEditor.apply();
    }

    private void setRadioButtonState(RadioGroup radioGroup, int checkedInt) {
        radioGroup.check(checkedInt);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        setSelectionState(view.getId(), checked);

//        switch (view.getId()) {


//            case R.id.radio_clarity_clear:
//                if (checked) {
//                    setSelectionState(R.id.radio_clarity_clear, CLEAR);
//                }
//                break;
//            case R.id.radio_clarity_hazy:
//                if (checked) {
//                    setSelectionState(CLARITY, HAZY);
//                }
//                break;
//            case R.id.radio_clarity_turbid:
//                if (checked) {
//                    setSelectionState(CLARITY, TURBID);
//                }
//                break;
//            case R.id.radio_concentration_pale:
//                if (checked) {
//                    setSelectionState(CONCENTRATION, PALE);
//                }
//                break;
//            case R.id.radio_concentration_medium:
//                if (checked) {
//                    setSelectionState(CONCENTRATION, MEDIUM);
//                }
//                break;
//            case R.id.radio_concentration_deep:
//                if (checked) {
//                    setSelectionState(CONCENTRATION, DEEP);
//                }
//                break;
//
//
//            default:
//                break;

        }
    }

    private class RedDeductionFromPagerAdapter extends FragmentPagerAdapter {
        RedDeductionFromPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new RedSightFragment();
                case 1:
                    return new RedNoseFragment();
                case 2:
                    return new RedPalateFragment();
                default:
                    break;
            }
            return null;
        }
    }

}
