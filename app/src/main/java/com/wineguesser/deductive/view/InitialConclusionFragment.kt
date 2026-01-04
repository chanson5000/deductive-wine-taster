package com.wineguesser.deductive.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MultiAutoCompleteTextView
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.wineguesser.deductive.R
import com.wineguesser.deductive.databinding.FragmentInitialConclusionBinding
import com.wineguesser.deductive.util.AppExecutors
import com.wineguesser.deductive.view.*
import com.wineguesser.deductive.util.Helpers
import com.wineguesser.deductive.view.*
import com.wineguesser.deductive.util.SpecialCharArrayAdapter
import com.wineguesser.deductive.view.*
import java.util.Arrays

class InitialConclusionFragment : Fragment() {

    private lateinit var mFragmentActivity: FragmentActivity
    private lateinit var mActivityPreferences: SharedPreferences
    private lateinit var mWinePreferences: SharedPreferences
    private var mIsRedWine: Boolean = false
    private var _binding: FragmentInitialConclusionBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mFragmentActivity = requireActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityPreferences = mFragmentActivity.getPreferences(Context.MODE_PRIVATE)

        mIsRedWine = mActivityPreferences.getBoolean(IS_RED_WINE, FALSE)

        val wineColorPreferenceType = if (mIsRedWine) RED_WINE_FORM_PREFERENCES else WHITE_WINE_FORM_PREFERENCES

        mWinePreferences = mFragmentActivity.getSharedPreferences(wineColorPreferenceType, Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInitialConclusionBinding.inflate(inflater, container, false)
        setAutoTextVarietyByType(mIsRedWine)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPause() {
        super.onPause()
        saveSelectionState(mWinePreferences)
        saveScrollState(mIsRedWine, mActivityPreferences)
    }

    override fun onResume() {
        super.onResume()
        loadSelectionState()
        loadScrollState()
    }

    private fun saveScrollState(isRedWine: Boolean, preferences: SharedPreferences) {
        if (_binding == null) return
        val editor = preferences.edit()
        editor.putInt(getScrollType(isRedWine), binding.scrollViewInitial.scrollY)
        editor.apply()
    }

    private fun getScrollType(isRedWine: Boolean): String {
        return if (isRedWine) RED_INITIAL_Y_SCROLL else WHITE_INITIAL_Y_SCROLL
    }

    private fun loadScrollState() {
        AppExecutors.mainThread.execute {
            if (_binding == null) return@execute
            val scrollY = mActivityPreferences.getInt(getScrollType(mIsRedWine), 0)
            binding.scrollViewInitial.scrollTo(0, scrollY)
        }
    }

    fun scrollToTop() {
        AppExecutors.mainThread.execute {
            _binding?.scrollViewInitial?.scrollTo(0, 0)
        }
    }

    private fun saveSelectionState(preferences: SharedPreferences) {
        if (_binding == null) return
        val editor = preferences.edit()
        editor.putString(
            TEXT_MULTI_INITIAL_GRAPE_VARIETIES.toString(),
            binding.multiTextInitialVarieties.text.toString()
        )
        editor.putString(
            TEXT_MULTI_INITIAL_COUNTRIES.toString(),
            binding.multiTextInitialCountries.text.toString()
        )
        editor.apply()
    }

    private fun setAutoTextVarietyByType(isRedWine: Boolean) {
        if (_binding == null) return
        binding.multiTextInitialVarieties.setAdapter(
            SpecialCharArrayAdapter(
                mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line,
                getVarietiesList(isRedWine)
            )
        )
        binding.multiTextInitialVarieties.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())

        val countries = ArrayList(parseResourceArray(R.array.all_countries))
        binding.multiTextInitialCountries.setAdapter(
            SpecialCharArrayAdapter(
                mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line,
                countries
            )
        )
        binding.multiTextInitialCountries.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
    }

    private fun getVarietiesList(isRedWine: Boolean): MutableList<String> {
        val varietyType = if (isRedWine) R.array.red_varieties else R.array.white_varieties
        return getListFromArrayResourceId(varietyType)
    }

    private fun getListFromArrayResourceId(arrayId: Int): MutableList<String> {
        return ArrayList(parseResourceArray(arrayId))
    }

    private fun parseResourceArray(resourceId: Int): List<String> {
        return Arrays.asList(*resources.getStringArray(resourceId))
    }

    private fun loadSelectionState() {
        val allEntries = mWinePreferences.all
        val rootView = view ?: return

        for (entry in allEntries) {
            val key = entry.key
            val value = entry.value ?: continue
            val viewId = Helpers.castKey(key)

            if (initialConclusionViews.contains(viewId)) {
                val view = rootView.findViewById<View>(viewId)
                if (view != null) {
                    if (view is RadioGroup) {
                        view.check(Helpers.parseEntryValue(value))
                    } else if (view is MultiAutoCompleteTextView) {
                        view.setText(value.toString())
                    }
                }
            }
        }
    }
}
