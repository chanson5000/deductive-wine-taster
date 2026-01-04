package com.wineguesser.deductive.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.wineguesser.deductive.R;
import com.wineguesser.deductive.databinding.FragmentInitialConclusionBinding;
import com.wineguesser.deductive.repository.DatabaseContract;
import com.wineguesser.deductive.util.AppExecutors;
import com.wineguesser.deductive.util.Helpers;
import com.wineguesser.deductive.util.SpecialCharArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class InitialConclusionFragment extends Fragment implements DeductionFormContract,
        DatabaseContract {

    private FragmentActivity mFragmentActivity;
    private SharedPreferences mActivityPreferences;
    private SharedPreferences mWinePreferences;
    private boolean mIsRedWine;
    private FragmentInitialConclusionBinding binding;

    public InitialConclusionFragment() {
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

        String wineColorPreferenceType =
                mIsRedWine ? RED_WINE_FORM_PREFERENCES : WHITE_WINE_FORM_PREFERENCES;

        mWinePreferences = mFragmentActivity
                .getSharedPreferences(wineColorPreferenceType, Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInitialConclusionBinding.inflate(inflater, container, false);
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

    @Override
    public void onPause() {
        super.onPause();
        saveSelectionState(mWinePreferences);
        saveScrollState(mIsRedWine, mActivityPreferences, binding.scrollViewInitial);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadSelectionState();
        loadScrollState();
    }

    private void saveScrollState(boolean isRedWine, SharedPreferences preferences, ScrollView scrollView) {
        if (scrollView == null) return;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(getScrollType(isRedWine), scrollView.getScrollY());
        editor.apply();
    }

    private String getScrollType(boolean mIsRedWine) {
        return mIsRedWine ? RED_INITIAL_Y_SCROLL : WHITE_INITIAL_Y_SCROLL;
    }

    private void loadScrollState() {
        AppExecutors.getInstance().mainThread().execute(() -> {
            if (binding == null) return;
            if (mIsRedWine) {
                binding.scrollViewInitial.scrollTo(0, mActivityPreferences
                        .getInt(RED_INITIAL_Y_SCROLL, 0));
            } else {
                binding.scrollViewInitial.scrollTo(0, mActivityPreferences
                        .getInt(WHITE_INITIAL_Y_SCROLL, 0));
            }
        });
    }

    void scrollToTop() {
        AppExecutors.getInstance().mainThread().execute(() -> {
            if (binding != null) {
                binding.scrollViewInitial.scrollTo(0, 0);
            }
        });
    }

    private void saveSelectionState(SharedPreferences preferences) {
        if (binding == null) return;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Integer.toString(TEXT_MULTI_INITIAL_GRAPE_VARIETIES),
                binding.multiTextInitialVarieties.getText().toString());
        editor.putString(Integer.toString(TEXT_MULTI_INITIAL_COUNTRIES),
                binding.multiTextInitialCountries.getText().toString());
        editor.apply();
    }

    private void setAutoTextVarietyByType(Boolean isRedWine) {
        if (binding == null) return;
        binding.multiTextInitialVarieties.setAdapter(new SpecialCharArrayAdapter<>(mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line, getVarietiesList(isRedWine)));
        binding.multiTextInitialVarieties.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        List<String> countries = new ArrayList<>(parseResourceArray(R.array.all_countries));
        binding.multiTextInitialCountries.setAdapter(new SpecialCharArrayAdapter<>(mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line, countries));
        binding.multiTextInitialCountries.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    private List<String> getVarietiesList(boolean isRedWine) {
        int varietyType = isRedWine ? R.array.red_varieties : R.array.white_varieties;
        return getListFromArrayResourceId(varietyType);
    }

    private List<String> getListFromArrayResourceId(int arrayId) {
        return new ArrayList<>(parseResourceArray(arrayId));
    }

    private List<String> parseResourceArray(int resourceId) {
        return Arrays.asList(getResources().getStringArray(resourceId));
    }

    private void loadSelectionState() {
        Map<String, ?> allEntries = mWinePreferences.getAll();
        View rootView = getView();
        if (rootView == null) return;

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            int viewId = Helpers.castKey(entry.getKey());

            if (initialConclusionViews.contains(viewId)) {
                View view = rootView.findViewById(viewId);
                if (view != null) {
                    if (view instanceof RadioGroup) {
                        ((RadioGroup) view).check(Helpers.parseEntryValue(entry.getValue()));
                    } else if (view instanceof MultiAutoCompleteTextView) {
                        ((MultiAutoCompleteTextView) view).setText(entry.getValue().toString());
                    }
                }
            }
        }
    }
}
