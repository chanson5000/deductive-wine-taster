package com.wineguesser.deductive.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ScrollView;
import android.widget.TextView;

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

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
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

        mIsRedWine = mActivityPreferences.getBoolean(IS_RED_WINE, FALSE);

        String wineColorPreferenceType = mIsRedWine
                ? RED_WINE_FORM_PREFERENCES
                : WHITE_WINE_FORM_PREFERENCES;

        mWinePreferences = mFragmentActivity
                .getSharedPreferences(wineColorPreferenceType, Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Set data binding.
        FragmentFinalConclusionBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_final_conclusion, container, false);
        // Initialize our view models.
        inputErrorsViewModel = ViewModelProviders.of(mFragmentActivity)
                .get(ConclusionInputErrorsViewModel.class);

        finalConclusionFragmentViewModel = ViewModelProviders.of(mFragmentActivity)
                .get(FinalConclusionFragmentViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setInputError(inputErrorsViewModel);
        binding.setSelf(finalConclusionFragmentViewModel);

        View rootView = binding.getRoot();
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
        SharedPreferences.Editor sharedPreferencesEditor = mActivityPreferences.edit();
        sharedPreferencesEditor.putInt(getScrollType(mIsRedWine), mScrollViewFinal.getScrollY());
        sharedPreferencesEditor.apply();
    }

    private String getScrollType(boolean mIsRedWine) {
        return mIsRedWine ? RED_FINAL_Y_SCROLL : WHITE_FINAL_Y_SCROLL;
    }

    private void loadScrollState() {
        // scrollTo must not be executed on the main thread.
        AppExecutors.getInstance().mainThread().execute(() ->
                mScrollViewFinal.scrollTo(0, mActivityPreferences
                        .getInt(getScrollType(mIsRedWine), 0)));
    }

    void scrollToTop() {
        AppExecutors.getInstance().mainThread().execute(() ->
                mScrollViewFinal.scrollTo(0, 0));
    }

    private void saveSelectionState() {
        SharedPreferences.Editor editor = mWinePreferences.edit();
        editor.putString(resourceIdToString(TEXT_SINGLE_FINAL_GRAPE_VARIETY),
                getTextViewString(mAutoTextVariety));
        editor.putString(resourceIdToString(TEXT_SINGLE_FINAL_COUNTRY_ORIGIN),
                getTextViewString(mAutoTextCountry));
        editor.putString(resourceIdToString(TEXT_SINGLE_FINAL_REGION),
                getTextViewString(mAutoTextRegion));
        editor.putString(resourceIdToString(TEXT_SINGLE_FINAL_QUALITY),
                getTextViewString(mAutoTextQuality));
        editor.putString(resourceIdToString(TEXT_SINGLE_FINAL_VINTAGE),
                getTextViewString(mAutoTextVintage));
        editor.apply();
    }

    private String getTextViewString(TextView textView) {
        return textView.getText().toString();
    }

    private String resourceIdToString(int resourceId) {
        return Integer.toString(resourceId);
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
        mAutoTextVariety.setAdapter(
                new SpecialCharArrayAdapter<>(
                        mFragmentActivity,
                        android.R.layout.simple_dropdown_item_1line,
                        getVarietiesList(isRedWine)));

        mAutoTextCountry.setAdapter(
                new SpecialCharArrayAdapter<>(
                        mFragmentActivity,
                        android.R.layout.simple_dropdown_item_1line,
                        getListFromArrayResourceId(R.array.all_countries)));

        mAutoTextRegion.setAdapter(
                new SpecialCharArrayAdapter<>(
                        mFragmentActivity,
                        android.R.layout.simple_dropdown_item_1line,
                        getListFromArrayResourceId(R.array.all_regions)));

        mAutoTextQuality.setAdapter(
                new SpecialCharArrayAdapter<>(
                        mFragmentActivity,
                        android.R.layout.simple_dropdown_item_1line,
                        getListFromArrayResourceId(R.array.all_qualities)));
    }

    private List<String> getVarietiesList(boolean isRedWine) {
        int varietyType = isRedWine ? R.array.red_varieties : R.array.white_varieties;
        return getListFromArrayResourceId(varietyType);
    }

    private List<String> getListFromArrayResourceId(int arrayId) {
        return new ArrayList<>(parseResourceArray(arrayId));
    }

    void showLoadingIndicator() {
        finalConclusionFragmentViewModel.setIsLoading(true);
    }

    void hideLoadingIndicator() {
        finalConclusionFragmentViewModel.setIsLoading(false);
    }
}
