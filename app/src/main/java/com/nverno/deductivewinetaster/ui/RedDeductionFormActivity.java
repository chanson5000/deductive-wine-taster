package com.nverno.deductivewinetaster.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.nverno.deductivewinetaster.R;
import com.nverno.deductivewinetaster.util.RedWineContract;

public class RedDeductionFormActivity extends AppCompatActivity implements RedWineContract {

    private static final int NUM_PAGES = 2;

    private ViewPager mPager;
    private Context mContext;
    private int mCurrentPage;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_deduction_form);

        mContext = this;
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        mPager = findViewById(R.id.view_pager_red_deduction);
        mPager.setAdapter(new RedDeductionFromPagerAdapter(getSupportFragmentManager()));

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
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(CURRENT_PAGE, mCurrentPage);
        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        mCurrentPage = sharedPreferences.getInt(CURRENT_PAGE, 0);
        mPager.setCurrentItem(mCurrentPage);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio_clarity_clear:



            case R.id.radio_nose_earth_forest_floor:
                if (checked)
                    Toast.makeText(mContext, "It Worked!", Toast.LENGTH_SHORT).show();
                break;
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
                default:
                    break;
            }
            return null;
        }
    }

}
