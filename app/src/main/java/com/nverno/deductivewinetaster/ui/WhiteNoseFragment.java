package com.nverno.deductivewinetaster.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Switch;

import com.nverno.deductivewinetaster.R;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class WhiteNoseFragment extends Fragment implements DeductionFormContract {

    private WhiteDeductionFormActivity mFragmentActivity;
    private SharedPreferences mSharedPreferences;

    @BindView(R.id.scrollView_nose_white)
    ScrollView mScrollViewNoseWhite;
    @BindViews({R.id.radio_nose_wood_old, R.id.radio_nose_wood_new,
            R.id.radio_nose_wood_large, R.id.radio_nose_wood_small,
            R.id.radio_nose_wood_french, R.id.radio_nose_wood_american})
    List<RadioButton> mRadioGroupNoseWood;

    static final ButterKnife.Action<RadioButton> WOOD_ENABLE = (view, index) ->
            view.setEnabled(true);

    static final ButterKnife.Action<RadioButton> WOOD_DISABLE = (view, index) ->
            view.setEnabled(false);

    public WhiteNoseFragment() {
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

        View rootView = inflater.inflate(R.layout.fragment_nose_white,
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

    private boolean castChecked(int checkedInt) {
        return checkedInt == 1;
    }

    private int parseEntryValue(Object value) {
        return Integer.parseInt(value.toString());
    }

    private void setUiState() {
        Map<String, ?> allEntries = mSharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            int key = castKey(entry.getKey());

            if (whiteNoseViews.contains(key)) {
                if (AllRadioGroups.contains(key)) {
                    ((RadioGroup) mFragmentActivity.findViewById(key))
                            .check(parseEntryValue(entry.getValue()));
                } else if (AllCheckBoxes.contains(key)) {
                    ((CheckBox) mFragmentActivity.findViewById(key))
                            .setChecked(castChecked(parseEntryValue(entry.getValue())));
                } else if (AllSwitches.contains(key)) {
                    ((Switch) mFragmentActivity.findViewById(key))
                            .setChecked(castChecked(parseEntryValue(entry.getValue())));
                }
            }
        }
        syncWoodRadioState();
    }

    private boolean getCheckBoxState(int key) {
        return mSharedPreferences.getInt(Integer.toString(key), NOT_CHECKED) == 1;
    }

    public void syncWoodRadioState() {
        if (getCheckBoxState(NOSE_WOOD)) {
            ButterKnife.apply(mRadioGroupNoseWood, WOOD_ENABLE);
        } else {
            ButterKnife.apply(mRadioGroupNoseWood, WOOD_DISABLE);
        }
    }
}
