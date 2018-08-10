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
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.nverno.deductivewinetaster.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RedSightFragment extends Fragment implements RedWineContract {

    private FragmentActivity mFragmentActivity;
    private SharedPreferences mSharedPreferences;

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
        mFragmentActivity = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = mFragmentActivity.getPreferences(Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sight_red,
                container, false);

        ButterKnife.bind(this, rootView);

        setSelectionListeners();

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        saveScrollPositionState(mScrollViewSightRed.getScrollX(), mScrollViewSightRed.getScrollY());
    }

    @Override
    public void onResume() {
        super.onResume();
        loadAllSelectionStates();
        syncScrollPositionFromState();
    }

    public void resetView() {
        clearAllSelectionStates();
        saveScrollPositionState(0, 0);
        mScrollViewSightRed.scrollTo(0, 0);
    }

    private void syncScrollPositionFromState() {
        mScrollViewSightRed.scrollTo(getSavedScrollXPositionState(), getSavedScrollYPositionState());
    }

    private int getSavedScrollXPositionState() {
        return mSharedPreferences.getInt(SCROLL_X_POS, 0);
    }

    private int getSavedScrollYPositionState() {
        return mSharedPreferences.getInt(SCROLL_Y_POS, 0);
    }

    private void saveScrollPositionState(int x, int y) {
         SharedPreferences.Editor editor = mSharedPreferences.edit();
         editor.putInt(SCROLL_X_POS, x);
         editor.putInt(SCROLL_Y_POS, y);
         editor.apply();
    }

    private void getRadioGroupState(String key, int state) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, state);
        editor.apply();
    }

    private int saveRadioGroupState(String key) {
        return mSharedPreferences.getInt(key, NONE_SELECTED);
    }

    private void loadAllSelectionStates() {
        mRadioGroupClarity.check(saveRadioGroupState(CLARITY));
        mRadioGroupConcentration.check(saveRadioGroupState(CONCENTRATION));
        mRadioGroupColor.check(saveRadioGroupState(COLOR));
        mRadioGroupSecondaryColor.check(saveRadioGroupState(SECONDARY_COLOR));
        mRadioGroupRimVariation.check(saveRadioGroupState(RIM_VARIATION));
        mRadioGroupExtractStain.check(saveRadioGroupState(EXTRACT_STAINING));
        mRadioGroupTearing.check(saveRadioGroupState(TEARING));
        mRadioGroupGasEvidence.check(saveRadioGroupState(GAS_EVIDENCE));
    }

    private void clearAllSelectionStates() {
        mRadioGroupClarity.clearCheck();
        mRadioGroupConcentration.clearCheck();
        mRadioGroupColor.clearCheck();
        mRadioGroupSecondaryColor.clearCheck();
        mRadioGroupRimVariation.clearCheck();
        mRadioGroupExtractStain.clearCheck();
        mRadioGroupTearing.clearCheck();
        mRadioGroupGasEvidence.clearCheck();
    }

    private void setSelectionListeners() {
        mRadioGroupClarity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                getRadioGroupState(CLARITY, checkedId);
            }
        });

        mRadioGroupConcentration.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                getRadioGroupState(CONCENTRATION, checkedId);
            }
        });

        mRadioGroupColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                getRadioGroupState(COLOR, checkedId);
            }
        });

        mRadioGroupSecondaryColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                getRadioGroupState(SECONDARY_COLOR, checkedId);
            }
        });

        mRadioGroupRimVariation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                getRadioGroupState(RIM_VARIATION, checkedId);
            }
        });

        mRadioGroupExtractStain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                getRadioGroupState(EXTRACT_STAINING, checkedId);
            }
        });

        mRadioGroupTearing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                getRadioGroupState(TEARING, checkedId);
            }
        });

        mRadioGroupGasEvidence.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                getRadioGroupState(GAS_EVIDENCE, checkedId);
            }
        });
    }
}
