package com.wineguesser.deductive.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.wineguesser.deductive.R
import com.wineguesser.deductive.util.AppExecutors
import com.wineguesser.deductive.util.Helpers
import androidx.core.content.edit

class SightFragment : Fragment() {

    private lateinit var mFragmentActivity: FragmentActivity
    private lateinit var mActivityPreferences: SharedPreferences
    private lateinit var mWinePreferences: SharedPreferences
    private var mIsRedWine: Boolean = false
    private lateinit var mScrollViewSight: ScrollView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mFragmentActivity = requireActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityPreferences = mFragmentActivity.getPreferences(Context.MODE_PRIVATE)

        val wineColorPreferenceType: String
        if (mActivityPreferences.getBoolean(IS_RED_WINE, FALSE)) {
            mIsRedWine = true
            wineColorPreferenceType = RED_WINE_FORM_PREFERENCES
        } else {
            wineColorPreferenceType = WHITE_WINE_FORM_PREFERENCES
        }
        mWinePreferences = mFragmentActivity.getSharedPreferences(wineColorPreferenceType, Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = if (mIsRedWine) {
            inflater.inflate(R.layout.fragment_sight_red, container, false)
        } else {
            inflater.inflate(R.layout.fragment_sight_white, container, false)
        }

        mScrollViewSight = rootView.findViewById(R.id.scrollView_sight)
        return rootView
    }

    override fun onPause() {
        super.onPause()
        saveScrollState()
    }

    override fun onResume() {
        super.onResume()
        loadSelectionState()
        loadScrollState()
    }

    private fun saveScrollState() {
        mActivityPreferences.edit {
            if (mIsRedWine) {
                putInt(RED_SIGHT_Y_SCROLL, mScrollViewSight.scrollY)
            } else {
                putInt(WHITE_SIGHT_Y_SCROLL, mScrollViewSight.scrollY)
            }
        }
    }

    private fun loadScrollState() {
        AppExecutors.mainThread.execute {
            if (mIsRedWine) {
                mScrollViewSight.scrollTo(0, mActivityPreferences.getInt(RED_SIGHT_Y_SCROLL, 0))
            } else {
                mScrollViewSight.scrollTo(0, mActivityPreferences.getInt(WHITE_SIGHT_Y_SCROLL, 0))
            }
        }
    }

    fun scrollToTop() {
        AppExecutors.mainThread.execute {
            mScrollViewSight.scrollTo(0, 0)
        }
    }

    private fun loadSelectionState() {
        val allEntries = mWinePreferences.all
        for (entry in allEntries) {
            val key = entry.key
            val value = entry.value ?: continue
            val viewId = Helpers.castKey(key)

            if (mIsRedWine && redSightViews.contains(viewId) && AllRadioGroups.contains(viewId)) {
                mFragmentActivity.findViewById<RadioGroup>(viewId)?.check(Helpers.parseEntryValue(value))
            } else if (!mIsRedWine && whiteSightViews.contains(viewId) && AllRadioGroups.contains(viewId)) {
                mFragmentActivity.findViewById<RadioGroup>(viewId)?.check(Helpers.parseEntryValue(value))
            }
        }
    }
}
