package com.wineguesser.deductive.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.wineguesser.deductive.databinding.FragmentNoseRedBinding;
import com.wineguesser.deductive.databinding.FragmentNoseWhiteBinding;
import com.wineguesser.deductive.repository.DatabaseContract;
import com.wineguesser.deductive.util.AppExecutors;
import com.wineguesser.deductive.util.Helpers;

import java.util.Map;

public class NoseFragment extends Fragment implements DeductionFormContract,
        DatabaseContract {

    private FragmentActivity mFragmentActivity;
    private SharedPreferences mActivityPreferences;
    private SharedPreferences mWinePreferences;
    private boolean mIsRedWine;

    private FragmentNoseRedBinding redBinding;
    private FragmentNoseWhiteBinding whiteBinding;

    public NoseFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
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
        if (mIsRedWine) {
            redBinding = FragmentNoseRedBinding.inflate(inflater, container, false);
            return redBinding.getRoot();
        } else {
            whiteBinding = FragmentNoseWhiteBinding.inflate(inflater, container, false);
            return whiteBinding.getRoot();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // View setup can be done here using the binding objects.
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        redBinding = null;
        whiteBinding = null;
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

    private ScrollView getScrollViewNose() {
        if (mIsRedWine) {
            return redBinding != null ? redBinding.scrollViewNose : null;
        } else {
            return whiteBinding != null ? whiteBinding.scrollViewNose : null;
        }
    }

    private LinearLayout getWoodGroup() {
        if (mIsRedWine) {
            return redBinding != null ? redBinding.noseWood.groupNoseWood : null;
        } else {
            return whiteBinding != null ? whiteBinding.noseWood.groupNoseWood : null;
        }
    }

    private void saveScrollState() {
        SharedPreferences.Editor editor = mActivityPreferences.edit();
        ScrollView scrollView = getScrollViewNose();
        if (scrollView == null) return;
        if (mIsRedWine) {
            editor.putInt(RED_NOSE_Y_SCROLL, scrollView.getScrollY());
        } else {
            editor.putInt(WHITE_NOSE_Y_SCROLL, scrollView.getScrollY());
        }
        editor.apply();
    }

    private void loadScrollState() {
        AppExecutors.getInstance().mainThread().execute(() -> {
            ScrollView scrollView = getScrollViewNose();
            if (scrollView == null) return;
            if (mIsRedWine) {
                scrollView.scrollTo(0, mActivityPreferences.getInt(RED_NOSE_Y_SCROLL, 0));
            } else {
                scrollView.scrollTo(0, mActivityPreferences.getInt(WHITE_NOSE_Y_SCROLL, 0));
            }
        });
    }

    public void scrollToTop() {
        AppExecutors.getInstance().mainThread().execute(() -> {
            ScrollView scrollView = getScrollViewNose();
            if (scrollView != null) {
                scrollView.scrollTo(0, 0);
            }
        });
    }

    private void loadSelectionState() {
        Map<String, ?> allEntries = mWinePreferences.getAll();
        View rootView = getView();
        if (rootView == null) {
            return;
        }

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            int viewId = Helpers.castKey(entry.getKey());

            if ((mIsRedWine && redNoseViews.contains(viewId)) ||
                    (!mIsRedWine && whiteNoseViews.contains(viewId))) {

                View view = rootView.findViewById(viewId);
                if (view != null) {
                    if (view instanceof RadioGroup) {
                        ((RadioGroup) view).check(Helpers.parseEntryValue(entry.getValue()));
                    } else if (view instanceof CheckBox) {
                        ((CheckBox) view).setChecked(Helpers.parseChecked(entry.getValue()));
                    } else if (view instanceof Switch) {
                        ((Switch) view).setChecked(Helpers.parseChecked(entry.getValue()));
                    }
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
        LinearLayout woodGroup = getWoodGroup();
        if (woodGroup == null) return;

        if (getCheckBoxState(SWITCH_NOSE_WOOD)) {
            woodGroup.setVisibility(View.VISIBLE);
            if (viewToggled) {
                AppExecutors.getInstance().mainThread().execute(() -> {
                    ScrollView scrollView = getScrollViewNose();
                    if (scrollView != null) {
                        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            }
        } else {
            woodGroup.setVisibility(View.GONE);
        }
    }
}
