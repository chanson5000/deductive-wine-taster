package com.wineguesser.deductive.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.wineguesser.deductive.R
import com.wineguesser.deductive.databinding.FragmentFinalConclusionBinding
import com.wineguesser.deductive.util.AppExecutors
import com.wineguesser.deductive.util.Helpers
import com.wineguesser.deductive.util.SpecialCharArrayAdapter
import com.wineguesser.deductive.viewmodel.ConclusionInputErrorsViewModel
import com.wineguesser.deductive.viewmodel.FinalConclusionFragmentViewModel

class FinalConclusionFragment : Fragment() {

    private lateinit var mFragmentActivity: FragmentActivity
    private lateinit var mActivityPreferences: SharedPreferences
    private lateinit var mWinePreferences: SharedPreferences
    private lateinit var inputErrorsViewModel: ConclusionInputErrorsViewModel
    private lateinit var finalConclusionFragmentViewModel: FinalConclusionFragmentViewModel
    private var mIsRedWine: Boolean = false
    private var _binding: FragmentFinalConclusionBinding? = null
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
        _binding = FragmentFinalConclusionBinding.inflate(inflater, container, false)
        inputErrorsViewModel = ViewModelProvider(mFragmentActivity)[ConclusionInputErrorsViewModel::class.java]
        finalConclusionFragmentViewModel = ViewModelProvider(mFragmentActivity)[FinalConclusionFragmentViewModel::class.java]

        binding.lifecycleOwner = this
        binding.inputError = inputErrorsViewModel
        binding.self = finalConclusionFragmentViewModel

        setAutoTextVarietyByType(mIsRedWine)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun errorsFinalForm(): ConclusionInputErrorsViewModel {
        return inputErrorsViewModel
    }

    private fun parseResourceArray(resourceId: Int): List<String> {
        return listOf(*resources.getStringArray(resourceId))
    }

    override fun onPause() {
        super.onPause()
        saveSelectionState()
        saveScrollState()
    }

    override fun onResume() {
        super.onResume()
        loadSelectionState()
        loadScrollState()
    }

    private fun saveScrollState() {
        if (_binding == null) return
        mActivityPreferences.edit {
            putInt(getScrollType(mIsRedWine), binding.scrollViewFinal.scrollY)
        }
    }

    private fun getScrollType(isRedWine: Boolean): String {
        return if (isRedWine) RED_FINAL_Y_SCROLL else WHITE_FINAL_Y_SCROLL
    }

    private fun loadScrollState() {
        AppExecutors.mainThread.execute {
            if (_binding == null) return@execute
            val scrollY = mActivityPreferences.getInt(getScrollType(mIsRedWine), 0)
            binding.scrollViewFinal.scrollTo(0, scrollY)
        }
    }

    fun scrollToTop() {
        AppExecutors.mainThread.execute {
            _binding?.scrollViewFinal?.scrollTo(0, 0)
        }
    }

    private fun saveSelectionState() {
        if (_binding == null) return
        mWinePreferences.edit {
            putString(
                resourceIdToString(TEXT_SINGLE_FINAL_GRAPE_VARIETY),
                getTextViewString(binding.autoTextFinalGrapeVariety)
            )
            putString(
                resourceIdToString(TEXT_SINGLE_FINAL_COUNTRY_ORIGIN),
                getTextViewString(binding.autoTextFinalCountry)
            )
            putString(
                resourceIdToString(TEXT_SINGLE_FINAL_REGION),
                getTextViewString(binding.autoTextFinalRegion)
            )
            putString(
                resourceIdToString(TEXT_SINGLE_FINAL_QUALITY),
                getTextViewString(binding.autoTextFinalQuality)
            )
            putString(
                resourceIdToString(TEXT_SINGLE_FINAL_VINTAGE),
                getTextViewString(binding.autoTextFinalVintage)
            )
        }
    }

    private fun getTextViewString(textView: TextView): String {
        return textView.text.toString()
    }

    private fun resourceIdToString(resourceId: Int): String {
        return resourceId.toString()
    }

    private fun loadSelectionState() {
        val allEntries = mWinePreferences.all
        val rootView = view ?: return

        for (entry in allEntries) {
            val key = entry.key
            val value = entry.value ?: continue
            val viewId = Helpers.castKey(key)

            if (finalConclusionViews.contains(viewId) && AllAutoText.contains(viewId)) {
                val view = rootView.findViewById<View>(viewId)
                if (view is AutoCompleteTextView) {
                    view.setText(value.toString())
                }
            }
        }
    }

    private fun setAutoTextVarietyByType(isRedWine: Boolean) {
        if (_binding == null) return
        binding.autoTextFinalGrapeVariety.setAdapter(
            SpecialCharArrayAdapter(
                mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line,
                getVarietiesList(isRedWine)
            )
        )

        binding.autoTextFinalCountry.setAdapter(
            SpecialCharArrayAdapter(
                mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line,
                getListFromArrayResourceId(R.array.all_countries)
            )
        )

        binding.autoTextFinalRegion.setAdapter(
            SpecialCharArrayAdapter(
                mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line,
                getListFromArrayResourceId(R.array.all_regions)
            )
        )

        binding.autoTextFinalQuality.setAdapter(
            SpecialCharArrayAdapter(
                mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line,
                getListFromArrayResourceId(R.array.all_qualities)
            )
        )
    }

    private fun getVarietiesList(isRedWine: Boolean): MutableList<String> {
        val varietyType = if (isRedWine) R.array.red_varieties else R.array.white_varieties
        return getListFromArrayResourceId(varietyType)
    }

    private fun getListFromArrayResourceId(arrayId: Int): MutableList<String> {
        return ArrayList(parseResourceArray(arrayId))
    }

    fun showLoadingIndicator() {
        finalConclusionFragmentViewModel.setIsLoading(true)
    }

    fun hideLoadingIndicator() {
        finalConclusionFragmentViewModel.setIsLoading(false)
    }
}
