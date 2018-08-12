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
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.nverno.deductivewinetaster.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RedSightFragment extends Fragment implements RedWineContract {

    private RedDeductionFormActivity mFragmentActivity;
    private SharedPreferences mSharedPreferences;
    private Context mContext;
    private View mRootView;

    // We keep a strong reference to the listener in order to prevent being garbage collected.
    private SharedPreferences.OnSharedPreferenceChangeListener mSharedPreferenceChangeListener;

    @BindView(R.id.scrollView_sight_red)
    ScrollView mScrollViewSightRed;
    @BindView(R.id.radio_group_clarity)
    RadioGroup mRadioGroupClarity;
    @BindView(R.id.radio_group_concentration)
    RadioGroup mRadioGroupConcentration;
    @BindView(R.id.radio_group_color_redwine)
    RadioGroup mRadioGroupColor;
    @BindView(R.id.radio_group_colorsecondary_redwine)
    RadioGroup mRadioGroupSecondaryColor;
    @BindView(R.id.radio_group_rimvariation)
    RadioGroup mRadioGroupRimVariation;
    @BindView(R.id.radio_group_extractstain_redwine)
    RadioGroup mRadioGroupExtractStain;
    @BindView(R.id.radio_group_tearing)
    RadioGroup mRadioGroupTearing;
    @BindView(R.id.radio_group_gasevidence)
    RadioGroup mRadioGroupGasEvidence;

    public RedSightFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentActivity = (RedDeductionFormActivity) getActivity();
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = mFragmentActivity
                .getSharedPreferences(RED_WINE_FORM_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_sight_red,
                container, false);

        ButterKnife.bind(this, mRootView);

        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
//        unRegisterSharedPreferencesListener();
    }

    @Override
    public void onResume() {
        super.onResume();
        setUiState();
//        registerViewListeners();
//        registerPreferencesListener();
    }

    private int getRadioGroupState(int key) {
        return mSharedPreferences.getInt(Integer.toString(key), NONE_SELECTED);
    }

    private void saveRadioGroupState(int key, int state) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(Integer.toString(key), state);
        editor.apply();
    }

    private void setUiState() {
        mRadioGroupClarity.check(getRadioGroupState(CLARITY));
        mRadioGroupConcentration.check(getRadioGroupState(CONCENTRATION));
        mRadioGroupColor.check(getRadioGroupState(COLOR));
        mRadioGroupSecondaryColor.check(getRadioGroupState(SECONDARY_COLOR));
        mRadioGroupRimVariation.check(getRadioGroupState(RIM_VARIATION));
        mRadioGroupExtractStain.check(getRadioGroupState(EXTRACT_STAINING));
        mRadioGroupTearing.check(getRadioGroupState(TEARING));
        mRadioGroupGasEvidence.check(getRadioGroupState(GAS_EVIDENCE));
    }

//    private void registerPreferencesListener() {
//        mSharedPreferences.registerOnSharedPreferenceChangeListener(mSharedPreferenceChangeListener
//                = (sharedPreferences, key) -> {
//
//            View view = mRootView.findViewById(Integer.parseInt(key));
//
//            if (view instanceof RadioGroup) {
//                RadioGroup radioGroup = (RadioGroup) view;
//                radioGroup.check(sharedPreferences.getInt(key, 0));
//            }
//        });
//    }

//    private void unRegisterSharedPreferencesListener() {
//        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(mSharedPreferenceChangeListener);
//    }



//    private void registerViewListeners() {
//
//        mRadioGroupClarity.setOnCheckedChangeListener((group, checkedId) ->
//                saveRadioGroupState(CLARITY, checkedId));
//
//        mRadioGroupConcentration.setOnCheckedChangeListener((group, checkedId) ->
//                saveRadioGroupState(CONCENTRATION, checkedId));
//
//        mRadioGroupColor.setOnCheckedChangeListener((group, checkedId) ->
//                saveRadioGroupState(COLOR, checkedId));
//
//        mRadioGroupSecondaryColor.setOnCheckedChangeListener((group, checkedId) ->
//                saveRadioGroupState(SECONDARY_COLOR, checkedId));
//
//        mRadioGroupRimVariation.setOnCheckedChangeListener((group, checkedId) ->
//                saveRadioGroupState(RIM_VARIATION, checkedId));
//
//        mRadioGroupExtractStain.setOnCheckedChangeListener((group, checkedId) ->
//                saveRadioGroupState(EXTRACT_STAINING, checkedId));
//
//        mRadioGroupTearing.setOnCheckedChangeListener((group, checkedId) ->
//                saveRadioGroupState(TEARING, checkedId));
//
//        mRadioGroupGasEvidence.setOnCheckedChangeListener((group, checkedId) ->
//                saveRadioGroupState(GAS_EVIDENCE, checkedId));
//    }

//    private void clearAllSelectionStates() {
//        mRadioGroupClarity.clearCheck();
//        mRadioGroupConcentration.clearCheck();
//        mRadioGroupColor.clearCheck();
//        mRadioGroupSecondaryColor.clearCheck();
//        mRadioGroupRimVariation.clearCheck();
//        mRadioGroupExtractStain.clearCheck();
//        mRadioGroupTearing.clearCheck();
//        mRadioGroupGasEvidence.clearCheck();
//    }
//
//    private void clearAllSharedPreferenceStates() {
//        saveRadioGroupState(CLARITY, NONE_SELECTED);
//        saveRadioGroupState(CONCENTRATION, NONE_SELECTED);
//        saveRadioGroupState(COLOR, NONE_SELECTED);
//        saveRadioGroupState(SECONDARY_COLOR, NONE_SELECTED);
//        saveRadioGroupState(RIM_VARIATION, NONE_SELECTED);
//        saveRadioGroupState(EXTRACT_STAINING, NONE_SELECTED);
//        saveRadioGroupState(TEARING, NONE_SELECTED);
//        saveRadioGroupState(GAS_EVIDENCE, NONE_SELECTED);
//    }
}
