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

import java.util.Map;

public class SightFragment extends Fragment implements DeductionFormContract {

    private FragmentActivity mFragmentActivity;
    private SharedPreferences mWinePreferences;
    private boolean mIsRedWine;

    ScrollView mScrollViewSight;

    public SightFragment() {
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
            rootView = inflater.inflate(R.layout.fragment_sight_red,
                    container, false);
            mScrollViewSight = rootView.findViewById(R.id.scrollView_sight_red);
        } else {
            rootView = inflater.inflate(R.layout.fragment_sight_white,
                    container, false);
            mScrollViewSight = rootView.findViewById(R.id.scrollView_sight_white);
        }

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
        Map<String, ?> allEntries = mWinePreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            int view = castKey(entry.getKey());

            if (mIsRedWine && redSightViews.contains(view) && AllRadioGroups.contains(view)) {
                ((RadioGroup) mFragmentActivity.findViewById(view))
                        .check(parseEntryValue(entry.getValue()));
            } else if (!mIsRedWine && whiteSightViews.contains(view) && AllRadioGroups.contains(view)) {
                ((RadioGroup) mFragmentActivity.findViewById(view))
                        .check(parseEntryValue(entry.getValue()));
            }
        }
    }
}
