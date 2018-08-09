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

import com.nverno.deductivewinetaster.R;
import com.nverno.deductivewinetaster.util.RedWineContract;

public class RedDeductionFormActivity extends AppCompatActivity implements RedWineContract {

    private static final int NUM_PAGES = 3;

    private ViewPager mPager;
    private Context mContext;
    private int mCurrentPage;

    private SharedPreferences sharedPreferences;

    RedSightFragment mRedWineSightFragment;
    RedNoseFragment mRedWineNoseFragment;
    RedPalateFragment mRedWinePalateFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_deduction_form);

        mContext = this;
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        mPager = findViewById(R.id.view_pager_red_deduction);
        PagerAdapter pagerAdapter = new RedDeductionFormPagerAdapter(getSupportFragmentManager());

        mPager.setAdapter(pagerAdapter);

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
                mRedWineSightFragment.resetView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
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

    class RedDeductionFormPagerAdapter extends FragmentPagerAdapter {
        RedDeductionFormPagerAdapter(FragmentManager fragmentManager) {
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
                    mRedWineSightFragment = new RedSightFragment();
                    return mRedWineSightFragment;
                case 1:
                    mRedWineNoseFragment = new RedNoseFragment();
                    return mRedWineNoseFragment;
                case 2:
                    mRedWinePalateFragment = new RedPalateFragment();
                    return mRedWinePalateFragment;
                default:
                    break;
            }
            return null;
        }
    }
}

