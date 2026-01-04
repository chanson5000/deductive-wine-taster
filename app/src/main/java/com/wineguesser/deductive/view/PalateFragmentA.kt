package com.wineguesser.deductive.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.ScrollView
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.wineguesser.deductive.databinding.FragmentPalateRedABinding
import com.wineguesser.deductive.databinding.FragmentPalateWhiteABinding
import com.wineguesser.deductive.util.AppExecutors
import com.wineguesser.deductive.util.Helpers
import androidx.core.content.edit

class PalateFragmentA : Fragment() {

    private lateinit var mFragmentActivity: FragmentActivity
    private lateinit var mActivityPreferences: SharedPreferences
    private lateinit var mWinePreferences: SharedPreferences
    private var mIsRedWine: Boolean = false

    private var redBinding: FragmentPalateRedABinding? = null
    private var whiteBinding: FragmentPalateWhiteABinding? = null

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
        return if (mIsRedWine) {
            redBinding = FragmentPalateRedABinding.inflate(inflater, container, false)
            redBinding!!.root
        } else {
            whiteBinding = FragmentPalateWhiteABinding.inflate(inflater, container, false)
            whiteBinding!!.root
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        redBinding = null
        whiteBinding = null
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

    private fun getScrollViewPalateA(): ScrollView? {
        return if (mIsRedWine) {
            redBinding?.scrollViewPalateA
        } else {
            whiteBinding?.scrollViewPalateA
        }
    }

    private fun getWoodGroup(): LinearLayout? {
        return if (mIsRedWine) {
            redBinding?.palateWood?.groupPalateWood
        } else {
            whiteBinding?.palateWood?.groupPalateWood
        }
    }

    private fun saveScrollState() {
        mActivityPreferences.edit {
            val scrollView = getScrollViewPalateA() ?: return

            if (mIsRedWine) {
                putInt(RED_PALATE_A_Y_SCROLL, scrollView.scrollY)
            } else {
                putInt(WHITE_PALATE_A_Y_SCROLL, scrollView.scrollY)
            }
        }
    }

    private fun loadScrollState() {
        AppExecutors.mainThread.execute {
            val scrollView = getScrollViewPalateA() ?: return@execute

            if (mIsRedWine) {
                scrollView.scrollTo(0, mActivityPreferences.getInt(RED_PALATE_A_Y_SCROLL, 0))
            } else {
                scrollView.scrollTo(0, mActivityPreferences.getInt(WHITE_PALATE_A_Y_SCROLL, 0))
            }
        }
    }

    fun scrollToTop() {
        AppExecutors.mainThread.execute {
            getScrollViewPalateA()?.scrollTo(0, 0)
        }
    }

    private fun loadSelectionState() {
        val allEntries = mWinePreferences.all
        val rootView = view ?: return

        for (entry in allEntries) {
            val key = entry.key
            val value = entry.value ?: continue
            val viewId = Helpers.castKey(key)

            if ((mIsRedWine && redPalateViewsA.contains(viewId)) ||
                !mIsRedWine && whitePalateViewsA.contains(viewId)
            ) {

                val view = rootView.findViewById<View>(viewId)
                if (view != null) {
                    when (view) {
                        is RadioGroup -> view.check(Helpers.parseEntryValue(value))
                        is CheckBox -> view.isChecked = Helpers.parseChecked(value)
                        is Switch -> view.isChecked = Helpers.parseChecked(value)
                    }
                }
            }
        }
        syncWoodRadioState(false)
    }

    private fun getCheckBoxState(key: Int): Boolean {
        return mWinePreferences.getInt(key.toString(), NOT_CHECKED) == 1
    }

    fun syncWoodRadioState(viewToggled: Boolean) {
        val woodGroup = getWoodGroup() ?: return

        if (getCheckBoxState(SWITCH_PALATE_WOOD)) {
            woodGroup.visibility = View.VISIBLE
            if (viewToggled) {
                AppExecutors.mainThread.execute {
                    getScrollViewPalateA()?.fullScroll(ScrollView.FOCUS_DOWN)
                }
            }
        } else {
            woodGroup.visibility = View.GONE
        }
    }
}
