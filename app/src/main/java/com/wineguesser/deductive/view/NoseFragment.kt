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
import com.wineguesser.deductive.databinding.FragmentNoseRedBinding
import com.wineguesser.deductive.databinding.FragmentNoseWhiteBinding
import com.wineguesser.deductive.util.AppExecutors
import com.wineguesser.deductive.view.*
import com.wineguesser.deductive.util.Helpers
import com.wineguesser.deductive.view.*
class NoseFragment : Fragment() {

    private lateinit var mFragmentActivity: FragmentActivity
    private lateinit var mActivityPreferences: SharedPreferences
    private lateinit var mWinePreferences: SharedPreferences
    private var mIsRedWine: Boolean = false

    private var redBinding: FragmentNoseRedBinding? = null
    private var whiteBinding: FragmentNoseWhiteBinding? = null

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
            redBinding = FragmentNoseRedBinding.inflate(inflater, container, false)
            redBinding!!.root
        } else {
            whiteBinding = FragmentNoseWhiteBinding.inflate(inflater, container, false)
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

    private fun getScrollViewNose(): ScrollView? {
        return if (mIsRedWine) {
            redBinding?.scrollViewNose
        } else {
            whiteBinding?.scrollViewNose
        }
    }

    private fun getWoodGroup(): LinearLayout? {
        return if (mIsRedWine) {
            redBinding?.noseWood?.groupNoseWood
        } else {
            whiteBinding?.noseWood?.groupNoseWood
        }
    }

    private fun saveScrollState() {
        val editor = mActivityPreferences.edit()
        val scrollView = getScrollViewNose() ?: return
        if (mIsRedWine) {
            editor.putInt(RED_NOSE_Y_SCROLL, scrollView.scrollY)
        } else {
            editor.putInt(WHITE_NOSE_Y_SCROLL, scrollView.scrollY)
        }
        editor.apply()
    }

    private fun loadScrollState() {
        AppExecutors.mainThread.execute {
            val scrollView = getScrollViewNose() ?: return@execute
            if (mIsRedWine) {
                scrollView.scrollTo(0, mActivityPreferences.getInt(RED_NOSE_Y_SCROLL, 0))
            } else {
                scrollView.scrollTo(0, mActivityPreferences.getInt(WHITE_NOSE_Y_SCROLL, 0))
            }
        }
    }

    fun scrollToTop() {
        AppExecutors.mainThread.execute {
            getScrollViewNose()?.scrollTo(0, 0)
        }
    }

    private fun loadSelectionState() {
        val allEntries = mWinePreferences.all
        val rootView = view ?: return

        for (entry in allEntries) {
            val key = entry.key
            val value = entry.value ?: continue
            val viewId = Helpers.castKey(key)

            if ((mIsRedWine && redNoseViews.contains(viewId)) ||
                (!mIsRedWine && whiteNoseViews.contains(viewId))
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

        if (getCheckBoxState(SWITCH_NOSE_WOOD)) {
            woodGroup.visibility = View.VISIBLE
            if (viewToggled) {
                AppExecutors.mainThread.execute {
                    getScrollViewNose()?.fullScroll(ScrollView.FOCUS_DOWN)
                }
            }
        } else {
            woodGroup.visibility = View.GONE
        }
    }
}
