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

import butterknife.BindView;
import butterknife.ButterKnife;

public class RedSightFragment extends Fragment {

    private FragmentActivity mFragmentActivity;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedEditor;

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

    public RedSightFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentActivity = getActivity();
        sharedPreferences =
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
    }


}
