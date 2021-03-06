package com.wineguesser.deductive.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Switch;

import com.wineguesser.deductive.R;
import com.wineguesser.deductive.repository.DatabaseContract;
import com.wineguesser.deductive.util.AppExecutors;
import com.wineguesser.deductive.util.Helpers;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PalateFragmentA extends Fragment implements DeductionFormContract, DatabaseContract {

    private FragmentActivity mFragmentActivity;
    private SharedPreferences mActivityPreferences;
    private SharedPreferences mWinePreferences;
    private boolean mIsRedWine;

    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.scrollView_palate_a)
    ScrollView mScrollViewPalateA;

    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.group_palate_wood)
    LinearLayout mWoodGroup;

    public PalateFragmentA() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentActivity = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityPreferences = mFragmentActivity.getPreferences(Context.MODE_PRIVATE);

        String wineColorPreferenceType;
        if (mActivityPreferences.getBoolean(IS_RED_WINE, FALSE)) {
            mIsRedWine = true;
            wineColorPreferenceType = RED_WINE_FORM_PREFERENCES;
        } else {
            wineColorPreferenceType = WHITE_WINE_FORM_PREFERENCES;
        }
        mWinePreferences = mFragmentActivity
                .getSharedPreferences(wineColorPreferenceType, Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;

        if (mIsRedWine) {
            rootView = inflater.inflate(R.layout.fragment_palate_red_a,
                    container, false);
        } else {
            rootView = inflater.inflate(R.layout.fragment_palate_white_a,
                    container, false);
        }

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        saveScrollState();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadSelectionState();
        loadScrollState();
    }

    private void saveScrollState() {
        SharedPreferences.Editor editor = mActivityPreferences.edit();
        if (mIsRedWine) {
            editor.putInt(RED_PALATE_A_Y_SCROLL, mScrollViewPalateA.getScrollY());
        } else {
            editor.putInt(WHITE_PALATE_A_Y_SCROLL, mScrollViewPalateA.getScrollY());
        }
        editor.apply();
    }

    private void loadScrollState() {
        if (mIsRedWine) {
            AppExecutors.getInstance().mainThread().execute(() ->
                    mScrollViewPalateA.scrollTo(0, mActivityPreferences
                            .getInt(RED_PALATE_A_Y_SCROLL, 0)));
        } else {
            AppExecutors.getInstance().mainThread().execute(() ->
                    mScrollViewPalateA.scrollTo(0, mActivityPreferences
                            .getInt(WHITE_PALATE_A_Y_SCROLL, 0)));
        }
    }

    public void scrollToTop() {
        AppExecutors.getInstance().mainThread().execute(() ->
                mScrollViewPalateA.scrollTo(0, 0));
    }


    private void loadSelectionState() {
        Map<String, ?> allEntries = mWinePreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            int view = Helpers.castKey(entry.getKey());

            if (mIsRedWine && redPalateViewsA.contains(view)) {
                if (AllRadioGroups.contains(view)) {
                    ((RadioGroup) mFragmentActivity.findViewById(view))
                            .check(Helpers.parseEntryValue(entry.getValue()));
                } else if (AllCheckBoxes.contains(view)) {
                    ((CheckBox) mFragmentActivity.findViewById(view))
                            .setChecked(Helpers.parseChecked(entry.getValue()));
                } else if (AllSwitches.contains(view)) {
                    ((Switch) mFragmentActivity.findViewById(view))
                            .setChecked(Helpers.parseChecked(entry.getValue()));
                }
            } else if (!mIsRedWine && whitePalateViewsA.contains(view)) {
                if (AllRadioGroups.contains(view)) {
                    ((RadioGroup) mFragmentActivity.findViewById(view))
                            .check(Helpers.parseEntryValue(entry.getValue()));
                } else if (AllCheckBoxes.contains(view)) {
                    ((CheckBox) mFragmentActivity.findViewById(view))
                            .setChecked(Helpers.parseChecked(entry.getValue()));
                } else if (AllSwitches.contains(view)) {
                    ((Switch) mFragmentActivity.findViewById(view))
                            .setChecked(Helpers.parseChecked(entry.getValue()));
                }
            }
        }
        syncWoodRadioState(false);
    }

    @SuppressWarnings("SameParameterValue")
    private boolean getCheckBoxState(int key) {
        return mWinePreferences.getInt(Integer.toString(key), NOT_CHECKED) == 1;
    }

    public void syncWoodRadioState(boolean viewToggled) {
        if (getCheckBoxState(SWITCH_PALATE_WOOD)) {
            mWoodGroup.setVisibility(View.VISIBLE);
            if (viewToggled) {
                AppExecutors.getInstance().mainThread().execute(() ->
                        mScrollViewPalateA.fullScroll(ScrollView.FOCUS_DOWN));
            }
        } else {
            mWoodGroup.setVisibility(View.GONE);
        }
    }
}
