package com.wineguesser.deductive.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.wineguesser.deductive.R;
import com.wineguesser.deductive.repository.DatabaseContract;
import com.wineguesser.deductive.util.AppExecutors;
import com.wineguesser.deductive.util.Helpers;
import com.wineguesser.deductive.util.SpecialCharArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InitialConclusionFragment extends Fragment implements DeductionFormContract,
        DatabaseContract {

    private FragmentActivity mFragmentActivity;
    private SharedPreferences mActivityPreferences;
    private SharedPreferences mWinePreferences;
    private boolean mIsRedWine;

    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.scrollView_initial)
    ScrollView mScrollViewInitial;

    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.multiText_initial_varieties)
    MultiAutoCompleteTextView mMultiAutoTextVarieties;

    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.multiText_initial_countries)
    MultiAutoCompleteTextView mMultiAutoTextCountries;

    public InitialConclusionFragment() {
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

        String wineColorPreferenceType =
                mIsRedWine ? RED_WINE_FORM_PREFERENCES : WHITE_WINE_FORM_PREFERENCES;

        mWinePreferences = mFragmentActivity
                .getSharedPreferences(wineColorPreferenceType, Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_initial_conclusion,
                container, false);

        ButterKnife.bind(this, rootView);

        setAutoTextVarietyByType(mIsRedWine);

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        saveSelectionState(mWinePreferences);

        saveScrollState(mIsRedWine, mActivityPreferences, mScrollViewInitial);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadSelectionState();
        loadScrollState();
    }

    private void saveScrollState(boolean isRedWine, SharedPreferences preferences, ScrollView scrollView) {
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(getScrollType(isRedWine), scrollView.getScrollY());

        editor.apply();
    }

    private String getScrollType(boolean mIsRedWine) {
        return mIsRedWine ? RED_FINAL_Y_SCROLL : WHITE_FINAL_Y_SCROLL;
    }

    private void loadScrollState() {
        if (mIsRedWine) {
            AppExecutors.getInstance().mainThread().execute(() ->
                    mScrollViewInitial.scrollTo(0, mActivityPreferences
                            .getInt(RED_INITIAL_Y_SCROLL, 0)));
        } else {
            AppExecutors.getInstance().mainThread().execute(() ->
                    mScrollViewInitial.scrollTo(0, mActivityPreferences
                            .getInt(WHITE_INITIAL_Y_SCROLL, 0)));
        }
    }

    public void scrollToTop() {
        AppExecutors.getInstance().mainThread().execute(() ->
                mScrollViewInitial.scrollTo(0, 0));
    }

    private void saveSelectionState(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Integer.toString(TEXT_MULTI_INITIAL_GRAPE_VARIETIES),
                mMultiAutoTextVarieties.getText().toString());
        editor.putString(Integer.toString(TEXT_MULTI_INITIAL_COUNTRIES),
                mMultiAutoTextCountries.getText().toString());
        editor.apply();
    }

    private void setAutoTextVarietyByType(Boolean isRedWine) {
        mMultiAutoTextVarieties.setAdapter(new SpecialCharArrayAdapter<>(mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line, getVarietiesList(isRedWine)));
        mMultiAutoTextVarieties.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        List<String> countries = new ArrayList<>(parseResourceArray(R.array.all_countries));
        mMultiAutoTextCountries.setAdapter(new SpecialCharArrayAdapter<>(mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line, countries));
        mMultiAutoTextCountries.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
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
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            int view = Helpers.castKey(entry.getKey());

            if (initialConclusionViews.contains(view)) {
                if (AllRadioGroups.contains(view)) {
                    ((RadioGroup) mFragmentActivity.findViewById(view))
                            .check(Helpers.parseEntryValue(entry.getValue()));
                } else if (AllAutoMultiText.contains(view)) {
                    ((MultiAutoCompleteTextView) mFragmentActivity.findViewById(view))
                            .setText(entry.getValue().toString());
                }
            }
        }
    }
}
