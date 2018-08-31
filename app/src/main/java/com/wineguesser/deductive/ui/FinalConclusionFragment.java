package com.wineguesser.deductive.ui;

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

import com.wineguesser.deductive.R;
import com.wineguesser.deductive.repository.DatabaseContract;
import com.wineguesser.deductive.util.AppExecutors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class FinalConclusionFragment extends Fragment implements
        DeductionFormContract, DatabaseContract {

    private FragmentActivity mFragmentActivity;
    private SharedPreferences mActivityPreferences;
    private SharedPreferences mWinePreferences;
    private boolean mIsRedWine;

    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.scrollView_final)
    ScrollView mScrollViewFinal;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.autoText_final_grape_variety)
    AutoCompleteTextView mAutoTextVariety;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.autoText_final_country)
    AutoCompleteTextView mAutoTextCountry;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.autoText_final_region)
    AutoCompleteTextView mAutoTextRegion;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.autoText_final_quality)
    AutoCompleteTextView mAutoTextQuality;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.autoText_final_vintage)
    AutoCompleteTextView mAutoTextVintage;
    @SuppressWarnings("WeakerAccess")
    @BindViews({R.id.progressBar_final_background, R.id.progressBar_final_conclusion})
    List<View> mLoadingIndicator;

    private static final ButterKnife.Action<View> HIDE = (view, index) ->
            view.setVisibility(View.INVISIBLE);

    private static final ButterKnife.Action<View> SHOW = (view, index) ->
            view.setVisibility(View.VISIBLE);

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

        mActivityPreferences =
                mFragmentActivity.getPreferences(Context.MODE_PRIVATE);

        if (mActivityPreferences.getString(IS_RED_WINE, WHITE_WINE).equals(RED_WINE)) {
            mIsRedWine = true;
        }

        String wineColorPreferenceType;

        if (mIsRedWine) {
            wineColorPreferenceType = RED_WINE_FORM_PREFERENCES;
        } else {
            wineColorPreferenceType = WHITE_WINE_FORM_PREFERENCES;
        }
        mWinePreferences = mFragmentActivity
                .getSharedPreferences(wineColorPreferenceType, Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;

        rootView = inflater.inflate(R.layout.fragment_final_conclusion,
                container, false);

        ButterKnife.bind(this, rootView);

        setAutoTextVarietyByType(mIsRedWine);

        mAutoTextCountry.setAdapter(new ArrayAdapter<>(mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line,
                new ArrayList<>(parseResourceArray(R.array.all_countries))));

        mAutoTextCountry.setAdapter(new ArrayAdapter<>(mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line,
                new ArrayList<>(parseResourceArray(R.array.all_regions))));
        return rootView;
    }

    private List<String> parseResourceArray(int resourceId) {
        return Arrays.asList(getResources().getStringArray(resourceId));
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
        String scrollType;

        SharedPreferences.Editor editor = mActivityPreferences.edit();
        if (mIsRedWine) {
            scrollType = RED_FINAL_Y_SCROLL;
        } else {
            scrollType = WHITE_FINAL_Y_SCROLL;
        }
        editor.putInt(scrollType, mScrollViewFinal.getScrollY());
        editor.apply();
    }

    private void loadScrollState() {
        String scrollType;

        if (mIsRedWine) {
            scrollType = RED_FINAL_Y_SCROLL;
        } else {
            scrollType = WHITE_FINAL_Y_SCROLL;
        }

        // scrollTo must not be executed on the main thread.
        AppExecutors.getInstance().mainThread().execute(() ->
                mScrollViewFinal.scrollTo(0, mActivityPreferences
                        .getInt(scrollType, 0)));
    }

    public void scrollToTop() {
        AppExecutors.getInstance().mainThread().execute(() ->
                mScrollViewFinal.scrollTo(0, 0));
    }

    private void saveSelectionState() {
        SharedPreferences.Editor editor = mWinePreferences.edit();
        editor.putString(Integer.toString(TEXT_SINGLE_FINAL_GRAPE_VARIETY),
                mAutoTextVariety.getText().toString());
        editor.putString(Integer.toString(TEXT_SINGLE_FINAL_COUNTRY_ORIGIN),
                mAutoTextCountry.getText().toString());
        editor.putString(Integer.toString(TEXT_SINGLE_FINAL_REGION),
                mAutoTextRegion.getText().toString());
        editor.putString(Integer.toString(TEXT_SINGLE_FINAL_QUALITY),
                mAutoTextQuality.getText().toString());
        editor.putString(Integer.toString(TEXT_SINGLE_FINAL_VINTAGE),
                mAutoTextVintage.getText().toString());
        editor.apply();
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

    private void setAutoTextVarietyByType(Boolean isRedWine) {
        List<String> varieties;

        if (isRedWine) {
            varieties = new ArrayList<>(R.array.red_varieties);
        } else {
            varieties = new ArrayList<>(R.array.white_varieties);
        }

        mAutoTextVariety.setAdapter(new ArrayAdapter<>(mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line, varieties));

    }

    private int castKey(String key) {
        return Integer.parseInt(key);
    }

    public void showLoadingIndicator() {
        ButterKnife.apply(mLoadingIndicator, SHOW);
    }

    public void hideLoadingIndicator() {
        ButterKnife.apply(mLoadingIndicator, HIDE);
    }
}
