package com.nverno.deductivewinetaster.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ScrollView;

import com.nverno.deductivewinetaster.R;
import com.nverno.deductivewinetaster.util.AppExecutors;
import com.nverno.deductivewinetaster.viewmodel.ViewModel;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FinalConclusionFragment extends Fragment implements DeductionFormContract {

    private FragmentActivity mFragmentActivity;
    private SharedPreferences mActivityPreferences;
    private SharedPreferences mWinePreferences;
    private boolean mIsRedWine;
    private ViewModel mViewModel;

    private AutoCompleteTextView mAutoTextVariety;
    private AutoCompleteTextView mAutoTextCountry;

    @BindView(R.id.scrollView_final)
    ScrollView mScrollViewFinal;

    public FinalConclusionFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentActivity = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(mFragmentActivity).get(ViewModel.class);

        mActivityPreferences =
                mFragmentActivity.getPreferences(Context.MODE_PRIVATE);

        if (mActivityPreferences.getString(WINE_TYPE, WHITE_WINE).equals(RED_WINE)) {
            mWinePreferences = mFragmentActivity
                    .getSharedPreferences(RED_WINE_FORM_PREFERENCES, Context.MODE_PRIVATE);
            mIsRedWine = true;
        } else {
            mWinePreferences = mFragmentActivity
                    .getSharedPreferences(WHITE_WINE_FORM_PREFERENCES, Context.MODE_PRIVATE);
            mIsRedWine = false;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;

        rootView = inflater.inflate(R.layout.fragment_final_conclusion,
                container, false);

        mAutoTextVariety = rootView.findViewById(R.id.autoText_final_grape_variety);
        mAutoTextCountry = rootView.findViewById(R.id.autoText_final_country);

        if (mIsRedWine) {
            mViewModel.redVarietiesList().observe(this, redVarieties -> {
                if (redVarieties != null) {
                    setAutoTextVarieties(redVarieties);
                }
            });
        } else {
            mViewModel.whiteVarietiesList().observe(this, whiteVarieties -> {
                if (whiteVarieties != null) {
                    setAutoTextVarieties(whiteVarieties);
                }
            });
        }

        mViewModel.countriesList().observe(this, countries -> {
            if (countries != null) {
                setAutoTextCountries(countries);
            }
        });

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        saveSelectionState();
        saveScrollState();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadSelectionState();
        loadScrollState();
    }

    private void saveScrollState() {
        SharedPreferences.Editor editor = mActivityPreferences.edit();
        if (mIsRedWine) {
            editor.putInt(RED_FINAL_Y_SCROLL, mScrollViewFinal.getScrollY());
        } else {
            editor.putInt(WHITE_FINAL_Y_SCROLL, mScrollViewFinal.getScrollY());
        }
        editor.apply();
    }

    private void loadScrollState() {
        if (mIsRedWine) {
            AppExecutors.getInstance().mainThread().execute(() ->
                    mScrollViewFinal.scrollTo(0, mActivityPreferences
                            .getInt(RED_FINAL_Y_SCROLL, 0)));
        } else {
            AppExecutors.getInstance().mainThread().execute(() ->
                    mScrollViewFinal.scrollTo(0, mActivityPreferences
                            .getInt(WHITE_FINAL_Y_SCROLL, 0)));
        }
    }

    private void saveSelectionState() {
        SharedPreferences.Editor editor = mWinePreferences.edit();
        editor.putString(Integer.toString(FINAL_GRAPE_VARIETY),
                mAutoTextVariety.getText().toString());
        editor.putString(Integer.toString(FINAL_COUNTRY_ORIGIN),
                mAutoTextCountry.getText().toString());
        editor.apply();
    }

    private void setAutoTextCountries(List<String> countries) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line, countries);
        mAutoTextCountry.setAdapter(adapter);
    }

    private void setAutoTextVarieties(List<String> varieties) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line, varieties);
        mAutoTextVariety.setAdapter(adapter);
    }

    private int castKey(String key) {
        return Integer.parseInt(key);
    }

    private void loadSelectionState() {
        Map<String, ?> allEntries = mWinePreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            int view = castKey(entry.getKey());

            if (finalConclusionViews.contains(view) && AllAutoText.contains(view)) {
                ((AutoCompleteTextView) mFragmentActivity.findViewById(view))
                        .setText(entry.getValue().toString());
            }
        }
    }
}
