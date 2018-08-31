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
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.wineguesser.deductive.R;
import com.wineguesser.deductive.repository.DatabaseContract;
import com.wineguesser.deductive.util.AppExecutors;

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

    private MultiAutoCompleteTextView mMultiAutoTextVarieties;
    private MultiAutoCompleteTextView mMultiAutoTextCountries;

    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.scrollView_initial)
    ScrollView mScrollViewInitial;

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

        mActivityPreferences =
                mFragmentActivity.getPreferences(Context.MODE_PRIVATE);

        if (mActivityPreferences.getString(IS_RED_WINE, WHITE_WINE).equals(RED_WINE)) {
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

        rootView = inflater.inflate(R.layout.fragment_initial_conclusion,
                container, false);

        mMultiAutoTextVarieties = rootView.findViewById(R.id.multiText_initial_varieties);
        mMultiAutoTextCountries = rootView.findViewById(R.id.multiText_initial_countries);

        if (mIsRedWine) {
            setMultiAutoTextVarieties(RedVarieties);
        } else {
            setMultiAutoTextVarieties(WhiteVarieties);
        }
        setMultiAutoTextCountries(AllCountries);

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
            editor.putInt(RED_INITIAL_Y_SCROLL, mScrollViewInitial.getScrollY());
        } else {
            editor.putInt(WHITE_INITIAL_Y_SCROLL, mScrollViewInitial.getScrollY());
        }
        editor.apply();
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

    private void saveSelectionState() {
        SharedPreferences.Editor editor = mWinePreferences.edit();
        editor.putString(Integer.toString(TEXT_MULTI_INITIAL_GRAPE_VARIETIES),
                mMultiAutoTextVarieties.getText().toString());
        editor.putString(Integer.toString(TEXT_MULTI_INITIAL_COUNTRIES),
                mMultiAutoTextCountries.getText().toString());
        editor.apply();
    }

    private void setMultiAutoTextCountries(String[] countries) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line, countries);
        mMultiAutoTextCountries.setAdapter(adapter);
        mMultiAutoTextCountries.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    private void setMultiAutoTextVarieties(List<String> varieties) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line, varieties);
        mMultiAutoTextVarieties.setAdapter(adapter);
        mMultiAutoTextVarieties.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    private int castKey(String key) {
        return Integer.parseInt(key);
    }

    private int parseEntryValue(Object value) {
        return Integer.parseInt(value.toString());
    }

    private void loadSelectionState() {
        Map<String, ?> allEntries = mWinePreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            int view = castKey(entry.getKey());

            if (initialConclusionViews.contains(view)) {
                if (AllRadioGroups.contains(view)) {
                    ((RadioGroup) mFragmentActivity.findViewById(view))
                            .check(parseEntryValue(entry.getValue()));
                } else if (AllAutoMultiText.contains(view)) {
                    ((MultiAutoCompleteTextView) mFragmentActivity.findViewById(view))
                            .setText(entry.getValue().toString());
                }
            }
        }
    }
}
