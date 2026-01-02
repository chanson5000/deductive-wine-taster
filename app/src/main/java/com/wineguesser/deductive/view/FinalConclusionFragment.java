package com.wineguesser.deductive.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

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

public class FinalConclusionFragment extends Fragment implements
        DeductionFormContract, DatabaseContract {

    private FragmentActivity mFragmentActivity;
    private SharedPreferences mActivityPreferences;
    private SharedPreferences mWinePreferences;
    private ConclusionInputErrorsViewModel inputErrorsViewModel;
    private FinalConclusionFragmentViewModel finalConclusionFragmentViewModel;
    private boolean mIsRedWine;
    private FragmentFinalConclusionBinding binding;

    public FinalConclusionFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
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

        binding = FragmentFinalConclusionBinding.inflate(inflater, container, false);
        inputErrorsViewModel = ViewModelProviders.of(mFragmentActivity)
                .get(ConclusionInputErrorsViewModel.class);

        finalConclusionFragmentViewModel = ViewModelProviders.of(mFragmentActivity)
                .get(FinalConclusionFragmentViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setInputError(inputErrorsViewModel);
        binding.setSelf(finalConclusionFragmentViewModel);

        setAutoTextVarietyByType(mIsRedWine);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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
        sharedPreferencesEditor.putInt(getScrollType(mIsRedWine), binding.scrollViewFinal.getScrollY());
        sharedPreferencesEditor.apply();
    }

    private String getScrollType(boolean mIsRedWine) {
        return mIsRedWine ? RED_FINAL_Y_SCROLL : WHITE_FINAL_Y_SCROLL;
    }

    private void loadScrollState() {
        AppExecutors.getInstance().mainThread().execute(() ->
                binding.scrollViewFinal.scrollTo(0, mActivityPreferences
                        .getInt(getScrollType(mIsRedWine), 0)));
    }

    void scrollToTop() {
        AppExecutors.getInstance().mainThread().execute(() ->
                binding.scrollViewFinal.scrollTo(0, 0));
    }

    private void saveSelectionState() {
        SharedPreferences.Editor editor = mWinePreferences.edit();
        editor.putString(resourceIdToString(TEXT_SINGLE_FINAL_GRAPE_VARIETY),
                getTextViewString(binding.autoTextFinalGrapeVariety));
        editor.putString(resourceIdToString(TEXT_SINGLE_FINAL_COUNTRY_ORIGIN),
                getTextViewString(binding.autoTextFinalCountry));
        editor.putString(resourceIdToString(TEXT_SINGLE_FINAL_REGION),
                getTextViewString(binding.autoTextFinalRegion));
        editor.putString(resourceIdToString(TEXT_SINGLE_FINAL_QUALITY),
                getTextViewString(binding.autoTextFinalQuality));
        editor.putString(resourceIdToString(TEXT_SINGLE_FINAL_VINTAGE),
                getTextViewString(binding.autoTextFinalVintage));
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
        View rootView = getView();
        if (rootView == null) return;

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            int viewId = Helpers.castKey(entry.getKey());

            if (finalConclusionViews.contains(viewId) && AllAutoText.contains(viewId)) {
                View view = rootView.findViewById(viewId);
                if (view instanceof AutoCompleteTextView) {
                    ((AutoCompleteTextView) view).setText(entry.getValue().toString());
                }
            }
        }
    }

    private void setAutoTextVarietyByType(Boolean isRedWine) {
        binding.autoTextFinalGrapeVariety.setAdapter(
                new SpecialCharArrayAdapter<>(
                        mFragmentActivity,
                        android.R.layout.simple_dropdown_item_1line,
                        getVarietiesList(isRedWine)));

        binding.autoTextFinalCountry.setAdapter(
                new SpecialCharArrayAdapter<>(
                        mFragmentActivity,
                        android.R.layout.simple_dropdown_item_1line,
                        getListFromArrayResourceId(R.array.all_countries)));

        binding.autoTextFinalRegion.setAdapter(
                new SpecialCharArrayAdapter<>(
                        mFragmentActivity,
                        android.R.layout.simple_dropdown_item_1line,
                        getListFromArrayResourceId(R.array.all_regions)));

        binding.autoTextFinalQuality.setAdapter(
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
