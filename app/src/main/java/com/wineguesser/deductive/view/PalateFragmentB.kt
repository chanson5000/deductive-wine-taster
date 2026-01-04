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
import com.wineguesser.deductive.view.*
import com.wineguesser.deductive.util.Helpers
import com.wineguesser.deductive.view.*
class PalateFragmentB : Fragment() {

    private lateinit var mFragmentActivity: FragmentActivity
    private lateinit var mActivityPreferences: SharedPreferences
    private lateinit var mWinePreferences: SharedPreferences
    private var mIsRedWine: Boolean = false
    private lateinit var mScrollViewPalateB: ScrollView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mFragmentActivity = requireActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityPreferences = mFragmentActivity.getPreferences(Context.MODE_PRIVATE)

        val wineColorPreferenceType: String = if (mActivityPreferences.getBoolean(IS_RED_WINE, FALSE)) {
            mIsRedWine = true
            RED_WINE_FORM_PREFERENCES
        } else {
            WHITE_WINE_FORM_PREFERENCES
        }
        mWinePreferences = mFragmentActivity.getSharedPreferences(wineColorPreferenceType, Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = if (mIsRedWine) {
            inflater.inflate(R.layout.fragment_palate_red_b, container, false)
        } else {
            inflater.inflate(R.layout.fragment_palate_white_b, container, false)
        }

        mScrollViewPalateB = rootView.findViewById(R.id.scrollView_palate_b)
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
        val editor = mActivityPreferences.edit()
        if (mIsRedWine) {
            editor.putInt(RED_PALATE_B_Y_SCROLL, mScrollViewPalateB.scrollY)
        } else {
            editor.putInt(WHITE_PALATE_B_Y_SCROLL, mScrollViewPalateB.scrollY)
        }
        editor.apply()
    }

    private fun loadScrollState() {
        AppExecutors.mainThread.execute {
            if (mIsRedWine) {
                mScrollViewPalateB.scrollTo(0, mActivityPreferences.getInt(RED_PALATE_B_Y_SCROLL, 0))
            } else {
                mScrollViewPalateB.scrollTo(0, mActivityPreferences.getInt(WHITE_PALATE_B_Y_SCROLL, 0))
            }
        }
    }

    fun scrollToTop() {
        AppExecutors.mainThread.execute {
            mScrollViewPalateB.scrollTo(0, 0)
        }
    }

    private fun loadSelectionState() {
        val allEntries = mWinePreferences.all
        for (entry in allEntries) {
            val key = entry.key
            val value = entry.value ?: continue
            val viewId = Helpers.castKey(key)
            if (mIsRedWine && redPalateViewsB.contains(viewId) && AllRadioGroups.contains(viewId)) {
                mFragmentActivity.findViewById<RadioGroup>(viewId)?.check(Helpers.parseEntryValue(value))
            } else if (!mIsRedWine && whitePalateViewsB.contains(viewId) && AllRadioGroups.contains(viewId)) {
                mFragmentActivity.findViewById<RadioGroup>(viewId)?.check(Helpers.parseEntryValue(value))
            }
        }
    }
}
