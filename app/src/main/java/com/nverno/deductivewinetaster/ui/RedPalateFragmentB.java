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
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Switch;

import com.nverno.deductivewinetaster.R;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RedPalateFragmentB extends Fragment implements RedWineContract {

    private FragmentActivity mFragmentActivity;
    private SharedPreferences mSharedPreferences;

    @BindView(R.id.scrollView_palate_red_b)
    ScrollView mScrollViewPalateRedB;

    public RedPalateFragmentB() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentActivity = getActivity();
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

        View rootView = inflater.inflate(R.layout.fragment_palate_red_b,
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

            if (redPalateViewsB.contains(key) && AllRadioGroups.contains(key)) {
                ((RadioGroup) mFragmentActivity.findViewById(key))
                        .check(parseEntryValue(entry.getValue()));
            }
        }
    }
}
