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
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.wineguesser.deductive.R;
import com.wineguesser.deductive.repository.DatabaseContract;
import com.wineguesser.deductive.util.AppExecutors;
import com.wineguesser.deductive.util.Helpers;

import java.util.Map;

public class PalateFragmentB extends Fragment implements DeductionFormContract,
        DatabaseContract {

    private FragmentActivity mFragmentActivity;
    private SharedPreferences mActivityPreferences;
    private SharedPreferences mWinePreferences;
    private boolean mIsRedWine;

    private ScrollView mScrollViewPalateB;

    public PalateFragmentB() {
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
            rootView = inflater.inflate(R.layout.fragment_palate_red_b,
                    container, false);
        } else {
            rootView = inflater.inflate(R.layout.fragment_palate_white_b,
                    container, false);
        }

        mScrollViewPalateB = rootView.findViewById(R.id.scrollView_palate_b);

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
            editor.putInt(RED_PALATE_B_Y_SCROLL, mScrollViewPalateB.getScrollY());
        } else {
            editor.putInt(WHITE_PALATE_B_Y_SCROLL, mScrollViewPalateB.getScrollY());
        }
        editor.apply();
    }

    private void loadScrollState() {
        if (mIsRedWine) {
            AppExecutors.getInstance().mainThread().execute(() ->
                    mScrollViewPalateB.scrollTo(0, mActivityPreferences
                            .getInt(RED_PALATE_B_Y_SCROLL, 0)));
        } else {
            AppExecutors.getInstance().mainThread().execute(() ->
                    mScrollViewPalateB.scrollTo(0, mActivityPreferences
                            .getInt(WHITE_PALATE_B_Y_SCROLL, 0)));
        }
    }

    public void scrollToTop() {
        AppExecutors.getInstance().mainThread().execute(() ->
                mScrollViewPalateB.scrollTo(0, 0));
    }

    private void loadSelectionState() {
        Map<String, ?> allEntries = mWinePreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            int view = Helpers.castKey(entry.getKey());

            if (mIsRedWine && redPalateViewsB.contains(view) && AllRadioGroups.contains(view)) {
                ((RadioGroup) mFragmentActivity.findViewById(view))
                        .check(Helpers.parseEntryValue(entry.getValue()));
            } else if (!mIsRedWine && whitePalateViewsB.contains(view) && AllRadioGroups.contains(view)) {
                ((RadioGroup) mFragmentActivity.findViewById(view))
                        .check(Helpers.parseEntryValue(entry.getValue()));
            }
        }
    }
}
