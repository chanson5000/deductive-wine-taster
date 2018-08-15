package com.nverno.deductivewinetaster.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.nverno.deductivewinetaster.R;

import java.util.List;
import java.util.Map;

import butterknife.BindViews;
import butterknife.ButterKnife;

public class NoseFragment extends Fragment implements DeductionFormContract {

    private FragmentActivity mFragmentActivity;
    private SharedPreferences mWinePreferences;
    private boolean mIsRedWine;

    @BindViews({R.id.radio_nose_wood_old, R.id.radio_nose_wood_new,
            R.id.radio_nose_wood_large, R.id.radio_nose_wood_small,
            R.id.radio_nose_wood_french, R.id.radio_nose_wood_american})
    List<RadioButton> mRadioGroupsNoseWood;

    static final ButterKnife.Action<RadioButton> WOOD_ENABLE = (view, index) ->
            view.setEnabled(true);

    static final ButterKnife.Action<RadioButton> WOOD_DISABLE = (view, index) ->
            view.setEnabled(false);

    public NoseFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentActivity = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences activityPreferences =
                mFragmentActivity.getPreferences(Context.MODE_PRIVATE);

        if (activityPreferences.getString(WINE_TYPE, WHITE_WINE).equals(RED_WINE)) {
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
        if (mIsRedWine) {
            rootView = inflater.inflate(R.layout.fragment_nose_red,
                    container, false);
        } else {
            rootView = inflater.inflate(R.layout.fragment_nose_white,
                    container, false);
        }

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
        Map<String, ?> allEntries = mWinePreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            int view = castKey(entry.getKey());

            if (mIsRedWine && redNoseViews.contains(view)) {
                if (AllRadioGroups.contains(view)) {
                    ((RadioGroup) mFragmentActivity.findViewById(view))
                            .check(parseEntryValue(entry.getValue()));
                } else if (AllCheckBoxes.contains(view)) {
                    ((CheckBox) mFragmentActivity.findViewById(view))
                            .setChecked(castChecked(parseEntryValue(entry.getValue())));
                } else if (AllSwitches.contains(view)) {
                    ((Switch) mFragmentActivity.findViewById(view))
                            .setChecked(castChecked(parseEntryValue(entry.getValue())));
                }
            } else if (!mIsRedWine && whiteNoseViews.contains(view)) {
                if (AllRadioGroups.contains(view)) {
                    ((RadioGroup) mFragmentActivity.findViewById(view))
                            .check(parseEntryValue(entry.getValue()));
                } else if (AllCheckBoxes.contains(view)) {
                    ((CheckBox) mFragmentActivity.findViewById(view))
                            .setChecked(castChecked(parseEntryValue(entry.getValue())));
                } else if (AllSwitches.contains(view)) {
                    ((Switch) mFragmentActivity.findViewById(view))
                            .setChecked(castChecked(parseEntryValue(entry.getValue())));
                }
            }
        }
        syncWoodRadioState();
    }

    private boolean getCheckBoxState(int key) {
        return mWinePreferences.getInt(Integer.toString(key), NOT_CHECKED) == 1;
    }

    public void syncWoodRadioState() {
        if (getCheckBoxState(NOSE_WOOD)) {
            ButterKnife.apply(mRadioGroupsNoseWood, WOOD_ENABLE);
        } else {
            ButterKnife.apply(mRadioGroupsNoseWood, WOOD_DISABLE);
        }
    }
}
