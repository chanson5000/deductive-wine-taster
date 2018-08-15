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
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nverno.deductivewinetaster.R;
import com.nverno.deductivewinetaster.viewmodel.ViewModel;

import java.util.List;
import java.util.Map;

public class InitialConclusionFragment extends Fragment implements DeductionFormContract {

    private FragmentActivity mFragmentActivity;
    private SharedPreferences mSharedPreferences;
    private boolean mIsRedWine;
    private ViewModel mViewModel;

    private MultiAutoCompleteTextView mMultiAutoTextVarieties;
    private MultiAutoCompleteTextView mMultiAutoTextCountries;

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
        mViewModel = ViewModelProviders.of(mFragmentActivity).get(ViewModel.class);

        SharedPreferences activityPreferences =
                mFragmentActivity.getPreferences(Context.MODE_PRIVATE);

        if (activityPreferences.getString(WINE_TYPE, WHITE_WINE).equals(RED_WINE)) {
            mSharedPreferences = mFragmentActivity
                    .getSharedPreferences(RED_WINE_FORM_PREFERENCES, Context.MODE_PRIVATE);
            mIsRedWine = true;
        } else {
            mSharedPreferences = mFragmentActivity
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
            mViewModel.redVarietiesList().observe(this, redVarieties -> {
                if (redVarieties != null) {
                    setMultiAutoTextVarieties(redVarieties);
                }
            });

        } else {
            mViewModel.whiteVarietiesList().observe(this, whiteVarieties -> {
                if (whiteVarieties != null) {
                    setMultiAutoTextVarieties(whiteVarieties);
                }
            });
        }

        mViewModel.countriesList().observe(this, countries -> {
            if (countries != null) {
                setMultiAutoTextCountries(countries);
            }
        });
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        saveUiState();
    }

    @Override
    public void onResume() {
        super.onResume();
        setUiState();
    }

    private void saveUiState() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(Integer.toString(INITIAL_GRAPE_VARIETIES),
                mMultiAutoTextVarieties.getText().toString());
        editor.putString(Integer.toString(INITIAL_COUNTRIES),
                mMultiAutoTextCountries.getText().toString());
        editor.apply();
    }

    private void setMultiAutoTextCountries(List<String> countries) {
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

    private void setUiState() {
        Map<String, ?> allEntries = mSharedPreferences.getAll();
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
