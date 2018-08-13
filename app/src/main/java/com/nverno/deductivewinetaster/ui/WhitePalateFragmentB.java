package com.nverno.deductivewinetaster.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.nverno.deductivewinetaster.R;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WhitePalateFragmentB extends Fragment implements DeductionFormContract {

    private WhiteDeductionFormActivity mFragmentActivity;
    private SharedPreferences mSharedPreferences;

    @BindView(R.id.scrollView_palate_white_b)
    ScrollView mScrollViewPalateWhiteB;

    public WhitePalateFragmentB() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentActivity = (WhiteDeductionFormActivity) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = mFragmentActivity
                .getSharedPreferences(WHITE_WINE_FORM_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_palate_white_b,
                container, false);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        setUiState();
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
            int key = castKey(entry.getKey());

            if (whitePalateViewsB.contains(key) && AllRadioGroups.contains(key)) {
                ((RadioGroup) mFragmentActivity.findViewById(key))
                        .check(parseEntryValue(entry.getValue()));
            }
        }
    }
}
