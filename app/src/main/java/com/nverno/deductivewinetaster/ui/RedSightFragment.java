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

import com.nverno.deductivewinetaster.R;
import com.nverno.deductivewinetaster.util.RedWineContract;
import com.nverno.deductivewinetaster.util.RedWineFormHandler;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RedSightFragment extends Fragment implements RedWineContract, RedWineFormHandler {

    private FragmentActivity mFragmentActivity;
    private SharedPreferences sharedPreferences;

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
        sharedPreferences = mFragmentActivity.getPreferences(Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sight_red,
                container, false);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadAllSelectionStates();
        setSelectionListeners();
    }

    private void setSelectionState(String key, int state) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, state);
        editor.apply();
    }

    private int getSelectionState(String key) {
        return sharedPreferences.getInt(key, NOT_CHECKED);
    }

    private void loadAllSelectionStates() {
        mRadioGroupClarity.check(getSelectionState(CLARITY));
        mRadioGroupConcentration.check(getSelectionState(CONCENTRATION));
        mRadioGroupColor.check(getSelectionState(COLOR));
        mRadioGroupSecondaryColor.check(getSelectionState(SECONDARY_COLOR));
        mRadioGroupRimVariation.check(getSelectionState(RIM_VARIATION));
        mRadioGroupExtractStain.check(getSelectionState(EXTRACT_STAINING));
        mRadioGroupTearing.check(getSelectionState(TEARING));
        mRadioGroupGasEvidence.check(getSelectionState(GAS_EVIDENCE));
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

    private void wipeSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void resetView() {
        clearAllSelectionStates();
    }

    public void setSelectionListeners() {
        mRadioGroupClarity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setSelectionState(CLARITY, checkedId);
            }
        });

        mRadioGroupConcentration.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setSelectionState(CONCENTRATION, checkedId);
            }
        });

        mRadioGroupColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setSelectionState(COLOR, checkedId);
            }
        });

        mRadioGroupSecondaryColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setSelectionState(SECONDARY_COLOR, checkedId);
            }
        });

        mRadioGroupRimVariation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setSelectionState(RIM_VARIATION, checkedId);
            }
        });

        mRadioGroupExtractStain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setSelectionState(EXTRACT_STAINING, checkedId);
            }
        });

        mRadioGroupTearing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setSelectionState(TEARING, checkedId);
            }
        });

        mRadioGroupGasEvidence.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setSelectionState(GAS_EVIDENCE, checkedId);
            }
        });
    }
}
