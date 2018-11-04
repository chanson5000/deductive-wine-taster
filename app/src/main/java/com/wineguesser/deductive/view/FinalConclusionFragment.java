package com.wineguesser.deductive.view;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ScrollView;

import com.wineguesser.deductive.R;
import com.wineguesser.deductive.databinding.FragmentFinalConclusionBinding;
import com.wineguesser.deductive.repository.DatabaseContract;
import com.wineguesser.deductive.util.AppExecutors;
import com.wineguesser.deductive.util.Helpers;
import com.wineguesser.deductive.util.SpecialCharArrayAdapter;
import com.wineguesser.deductive.viewmodel.ConclusionInputErrorsViewModel;
import com.wineguesser.deductive.viewmodel.FinalConclusionFragmentViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FinalConclusionFragment extends Fragment implements
        DeductionFormContract, DatabaseContract {

    private FragmentActivity mFragmentActivity;
    private SharedPreferences mActivityPreferences;
    private SharedPreferences mWinePreferences;
    private ConclusionInputErrorsViewModel inputErrorsViewModel;
    private FinalConclusionFragmentViewModel finalConclusionFragmentViewModel;
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
        mActivityPreferences = mFragmentActivity.getPreferences(Context.MODE_PRIVATE);

        String wineColorPreferenceType;
        if (mActivityPreferences.getBoolean(IS_RED_WINE, FALSE)) {
            mIsRedWine = true;
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
        // Set data binding.
        FragmentFinalConclusionBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_final_conclusion, container, false);
        // Initialize our view models.
        inputErrorsViewModel = ViewModelProviders.of(mFragmentActivity)
                .get(ConclusionInputErrorsViewModel.class);

        finalConclusionFragmentViewModel = ViewModelProviders.of(mFragmentActivity)
                .get(FinalConclusionFragmentViewModel.class);

        // Set our lifecycle owner.
        binding.setLifecycleOwner(this);
        binding.setInputError(inputErrorsViewModel);

        // Retrieve our rootView.
        rootView = binding.getRoot();

        ButterKnife.bind(this, rootView);

        setAutoTextVarietyByType(mIsRedWine);

        return rootView;
    }

    ConclusionInputErrorsViewModel errorsFinalForm() {
        return inputErrorsViewModel;
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

    void scrollToTop() {
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
            int view = Helpers.castKey(entry.getKey());

            if (finalConclusionViews.contains(view) && AllAutoText.contains(view)) {
                ((AutoCompleteTextView) mFragmentActivity.findViewById(view))
                        .setText(entry.getValue().toString());
            }
        }
    }

    private void setAutoTextVarietyByType(Boolean isRedWine) {
        List<String> varieties;

        if (isRedWine) {
            varieties = new ArrayList<>(parseResourceArray(R.array.red_varieties));
        } else {
            varieties = new ArrayList<>(parseResourceArray(R.array.white_varieties));
        }

        mAutoTextVariety.setAdapter(new SpecialCharArrayAdapter<>(mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line, varieties));

        List<String> countries = new ArrayList<>(parseResourceArray(R.array.all_countries));
        List<String> regions = new ArrayList<>(parseResourceArray(R.array.all_regions));
        List<String> qualities = new ArrayList<>(parseResourceArray(R.array.all_qualities));

        mAutoTextCountry.setAdapter(new SpecialCharArrayAdapter<>(mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line, countries));

        mAutoTextRegion.setAdapter(new SpecialCharArrayAdapter<>(mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line, regions));

        mAutoTextQuality.setAdapter((new SpecialCharArrayAdapter<>(mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line, qualities)));
    }

    void showLoadingIndicator() {
        finalConclusionFragmentViewModel.setIsLoading(true);
    }

    void hideLoadingIndicator() {
        finalConclusionFragmentViewModel.setIsLoading(false);
    }
}
