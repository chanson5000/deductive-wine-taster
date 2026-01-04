package com.wineguesser.deductive.view


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import android.util.SparseIntArray
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.CheckBox
import android.widget.MultiAutoCompleteTextView
import android.widget.RadioGroup
import android.widget.ScrollView
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import com.wineguesser.deductive.R
import com.wineguesser.deductive.util.FormMapper
import com.wineguesser.deductive.util.GrapeVarietyScore
import com.wineguesser.deductive.util.GrapeVarietyScoreResult
import com.wineguesser.deductive.util.Helpers
import kotlinx.coroutines.launch
import java.util.Arrays
import java.util.Calendar
import androidx.core.content.edit
import androidx.core.view.isVisible

class DeductionFormActivity : AppCompatActivity(), GrapeVarietyScoreResult {

    private lateinit var mPager: ViewPager
    private lateinit var mWinePreferences: SharedPreferences
    private lateinit var mActivityPreferences: SharedPreferences
    private var mIsRedWine: Boolean = false
    private var mLaunchingIntent: Boolean = false

    private var menuShowWhiteScreen: MenuItem? = null

    private var mSharedPreferenceChangeListener: OnSharedPreferenceChangeListener? = null
    private var mOnPageChangeListener: ViewPager.OnPageChangeListener? = null

    private lateinit var mContext: Context
    private var mSightFragment: SightFragment? = null
    private var mNoseFragment: NoseFragment? = null
    private var mPalateFragmentA: PalateFragmentA? = null
    private var mPalateFragmentB: PalateFragmentB? = null
    private var mInitialFragment: InitialConclusionFragment? = null
    private var mFinalFragment: FinalConclusionFragment? = null

    private var mUserFinalVarietyString: String? = null
    private var mUserFinalCountryString: String? = null
    private var mUserFinalRegionString: String? = null
    private var mUserFinalQualityString: String? = null
    private var mUserFinalVintageInteger: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        mContext = this
        mActivityPreferences = getPreferences(MODE_PRIVATE)

        setSharedPreferences(intent, supportFragmentManager)

        setUpActionBar(findViewById(R.id.toolbar))

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.deduction_form_coordinator)) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(insets.left, insets.top, insets.right, insets.bottom)
            windowInsets
        }
    }

    private fun setUpActionBar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setSharedPreferences(parentIntent: Intent?, mFragmentManager: FragmentManager) {
        mActivityPreferences.edit {
            if (parentIntent != null && parentIntent.hasExtra(IS_RED_WINE)) {
                setContentView(R.layout.activity_red_deduction_form)

                putBoolean(IS_RED_WINE, TRUE)
                mIsRedWine = true

                mWinePreferences =
                    getSharedPreferences(RED_WINE_FORM_PREFERENCES, MODE_PRIVATE)
                mPager = findViewById(R.id.view_pager_red_deduction)
            } else {
                setContentView(R.layout.activity_white_deduction_form)

                putBoolean(IS_RED_WINE, FALSE)
                mIsRedWine = false

                mWinePreferences =
                    getSharedPreferences(WHITE_WINE_FORM_PREFERENCES, MODE_PRIVATE)
                mPager = findViewById(R.id.view_pager_white_deduction)
            }
            val pagerAdapter: PagerAdapter = DeductionFormPagerAdapter(mFragmentManager)
            mPager.adapter = pagerAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        mOnPageChangeListener = createOnPageChangeListener()
        mPager.addOnPageChangeListener(mOnPageChangeListener!!)
    }

    private fun createOnPageChangeListener(): ViewPager.OnPageChangeListener {
        return object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                syncCurrentTitle(position)
                syncCurrentMenuOptions(position, menuShowWhiteScreen)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        }
    }

    override fun onStop() {
        super.onStop()
        mOnPageChangeListener?.let { mPager.removeOnPageChangeListener(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.wine_deduction_menu, menu)
        menuShowWhiteScreen = menu.findItem(R.id.menu_show_white_screen)
        if (getCurrentPageFromPager() != SIGHT_PAGE) {
            menuShowWhiteScreen?.isVisible = false
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.clear_selections -> {
                resetSharedPreferences()
                resetAllTopScroll()
                resetCurrentPage()
                resetWhiteScreen()
                Helpers.makeToastShort(mContext, R.string.form_cleared)
                true
            }
            R.id.menu_show_white_screen -> {
                toggleSightWhiteScreen()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun syncCurrentMenuOptions(page: Int, menuItem: MenuItem?) {
        if (menuItem != null) {
            val sightScroll = findViewById<ScrollView>(R.id.scrollView_sight)
            if (page == SIGHT_PAGE) {
                menuItem.isVisible = true
                sightScroll?.let { toggleWineEvaluationMode(menuItem, it) }
            } else {
                menuShowWhiteScreen?.isVisible = false
                sightScroll?.rootView?.setBackgroundColor(
                    ContextCompat.getColor(mContext, R.color.colorPrimaryBackground)
                )
            }
        }
    }

    private fun toggleWineEvaluationMode(menuItem: MenuItem, sightScroll: ScrollView) {
        val root = sightScroll.rootView
        if (sightScroll.isVisible) {
            menuItem.setIcon(R.drawable.ic_menu_visibility_off_24px)
            root.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryBackground))
        } else {
            menuItem.setIcon(R.drawable.ic_menu_visibility_on_24px)
            root.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white))
        }
    }

    private fun resetWhiteScreen() {
        if (menuShowWhiteScreen != null && getCurrentPageFromPager() == SIGHT_PAGE) {
            menuShowWhiteScreen!!.isVisible = true
            menuShowWhiteScreen!!.setIcon(R.drawable.ic_menu_visibility_off_24px)
            val sightScroll = findViewById<ScrollView>(R.id.scrollView_sight)
            resetScrollView(sightScroll)
        }
    }

    private fun resetScrollView(sightScroll: ScrollView?) {
        if (sightScroll != null) {
            val root = sightScroll.rootView
            root.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryBackground))
            sightScroll.visibility = View.VISIBLE
        }
    }

    private fun setSightWhiteScreen(isEnabled: Boolean, view: View?) {
        if (view != null) {
            if (isEnabled) {
                menuShowWhiteScreen?.setIcon(R.drawable.ic_menu_visibility_on_24px)
                val root = view.rootView
                root.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white))
                view.visibility = View.INVISIBLE
                Helpers.makeToastShort(mContext, R.string.da_toast_showing_screen_for_wine)
            } else {
                menuShowWhiteScreen?.setIcon(R.drawable.ic_menu_visibility_off_24px)
                val root = view.rootView
                root.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryBackground))
                view.visibility = View.VISIBLE
                Helpers.makeToastShort(mContext, R.string.da_toast_hiding_screen_for_wine)
            }
        }
    }

    private fun toggleSightWhiteScreen() {
        val sightScroll = findViewById<ScrollView>(R.id.scrollView_sight)
        if (getCurrentPageFromPager() == SIGHT_PAGE && sightScroll != null) {
            if (sightScroll.isVisible) {
                setSightWhiteScreen(true, sightScroll)
            } else {
                setSightWhiteScreen(false, sightScroll)
            }
        }
    }

    private fun resetCurrentPage() {
        if (mPager.currentItem != SIGHT_PAGE) {
            mPager.currentItem = SIGHT_PAGE
        }
    }

    override fun onPause() {
        super.onPause()
        unRegisterSharedPreferencesListener()
        if (!mLaunchingIntent) {
            setCurrentPageInPreferences(getCurrentPageFromPager())
            mLaunchingIntent = false
        }
    }

    override fun onResume() {
        super.onResume()
        val currentPage = getCurrentPageFromPreferences()
        setCurrentPage(currentPage)
        syncCurrentTitle(currentPage)
        registerPreferencesListener()
    }

    private fun setCurrentPageInPreferences(page: Int) {
        mActivityPreferences.edit {
            if (mIsRedWine) {
                putInt(CURRENT_PAGE_RED, page)
            } else {
                putInt(CURRENT_PAGE_WHITE, page)
            }
        }
    }

    private fun getCurrentPageFromPreferences(): Int {
        return if (mIsRedWine) {
            mActivityPreferences.getInt(CURRENT_PAGE_RED, 0)
        } else {
            mActivityPreferences.getInt(CURRENT_PAGE_WHITE, 0)
        }
    }

    private fun setCurrentPage(page: Int) {
        mPager.currentItem = page
    }

    private fun getCurrentPageFromPager(): Int {
        return mPager.currentItem
    }

    override fun onBackPressed() {
        if (getCurrentPageFromPager() == 0) {
            super.onBackPressed()
        } else {
            setCurrentPage(getCurrentPageFromPager() - 1)
        }
    }

    fun onClickNext(v: View) {
        setCurrentPage(getCurrentPageFromPager() + 1)
    }

    private fun registerPreferencesListener() {
        mSharedPreferenceChangeListener = OnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (key == null) return@OnSharedPreferenceChangeListener
            val viewId = Helpers.castKey(key)
            val view = findViewById<View>(viewId)

            if (view != null) {
                if (view is RadioGroup) {
                    view.check(sharedPreferences.getInt(key, NONE_SELECTED))
                } else if (view is CheckBox) {
                    view.isChecked = Helpers.castChecked(sharedPreferences.getInt(key, NOT_CHECKED))
                } else if (view is Switch) {
                    view.isChecked = Helpers.castChecked(sharedPreferences.getInt(key, NOT_CHECKED))
                } else if (view is MultiAutoCompleteTextView) {
                    view.setText(sharedPreferences.getString(key, ""))
                } else if (view is AutoCompleteTextView) {
                    view.setText(sharedPreferences.getString(key, ""))
                }
            }
        }
        mWinePreferences.registerOnSharedPreferenceChangeListener(mSharedPreferenceChangeListener)
    }

    private fun unRegisterSharedPreferencesListener() {
        mWinePreferences.unregisterOnSharedPreferenceChangeListener(mSharedPreferenceChangeListener)
    }

    private fun resetSharedPreferences() {
        val allEntries = mWinePreferences.all
        val winePreferencesEditor = mWinePreferences.edit()
        for (key in allEntries.keys) {
            val viewId = Helpers.castKey(key)
            if (AllRadioGroups.contains(viewId)) {
                winePreferencesEditor.putInt(key, NONE_SELECTED)
            } else if (AllCheckBoxes.contains(viewId)) {
                winePreferencesEditor.putInt(key, NOT_CHECKED)
            } else if (AllSwitches.contains(viewId)) {
                winePreferencesEditor.putInt(key, NOT_CHECKED)
            } else if (AllAutoText.contains(viewId)) {
                winePreferencesEditor.putString(key, "")
                val autoView = findViewById<AutoCompleteTextView>(viewId)
                autoView?.setText("")
            } else if (AllAutoMultiText.contains(viewId)) {
                winePreferencesEditor.putString(key, "")
                val multiView = findViewById<MultiAutoCompleteTextView>(viewId)
                multiView?.setText("")
            }
        }
        winePreferencesEditor.apply()

        mActivityPreferences.edit {
            if (mIsRedWine) {
                putInt(RED_SIGHT_Y_SCROLL, 0)
                putInt(RED_NOSE_Y_SCROLL, 0)
                putInt(RED_PALATE_A_Y_SCROLL, 0)
                putInt(RED_PALATE_B_Y_SCROLL, 0)
                putInt(RED_INITIAL_Y_SCROLL, 0)
                putInt(RED_FINAL_Y_SCROLL, 0)
            } else {
                putInt(WHITE_SIGHT_Y_SCROLL, 0)
                putInt(WHITE_NOSE_Y_SCROLL, 0)
                putInt(WHITE_PALATE_A_Y_SCROLL, 0)
                putInt(WHITE_PALATE_B_Y_SCROLL, 0)
                putInt(WHITE_INITIAL_Y_SCROLL, 0)
                putInt(WHITE_FINAL_Y_SCROLL, 0)
            }
        }
    }

    private fun saveRadioGroupState(key: Int, state: Int) {
        mWinePreferences.edit {
            putInt(key.toString(), state)
        }
    }

    private fun saveCheckBoxState(key: Int, checkedInt: Int) {
        mWinePreferences.edit {
            putInt(key.toString(), checkedInt)
        }
    }

    fun onRadioButtonClicked(view: View) {
        val radioGroup = view.parent as RadioGroup
        saveRadioGroupState(radioGroup.id, radioGroup.checkedRadioButtonId)
    }

    fun onCheckBoxButtonClicked(view: View) {
        val checkBox = view as CheckBox
        saveCheckBoxState(checkBox.id, Helpers.castChecked(checkBox.isChecked))
    }

    fun onSwitchToggleClicked(view: View) {
        val switchToggle = view as Switch
        val switchId = switchToggle.id
        val checked = switchToggle.isChecked
        saveCheckBoxState(switchId, Helpers.castChecked(checked))
        if (switchId == SWITCH_NOSE_WOOD) {
            mNoseFragment?.syncWoodRadioState(true)
        } else if (switchId == SWITCH_PALATE_WOOD) {
            mPalateFragmentA?.syncWoodRadioState(true)
        }
    }

    private fun resetAllTopScroll() {
        mSightFragment?.scrollToTop()
        mNoseFragment?.scrollToTop()
        mPalateFragmentA?.scrollToTop()
        mPalateFragmentB?.scrollToTop()
        mInitialFragment?.scrollToTop()
        mFinalFragment?.scrollToTop()
    }

    private fun syncCurrentTitle(page: Int) {
        when (page) {
            SIGHT_PAGE -> title = getString(R.string.sight_page_title)
            NOSE_PAGE -> title = getString(R.string.nose_page_title)
            PALATE_PAGE_A -> title = getString(R.string.palate_page_title)
            PALATE_PAGE_B -> title = getString(R.string.palate_page_title)
            INITIAL_CONCLUSION_PAGE -> title = getString(R.string.initial_conclusion_page_title)
            FINAL_CONCLUSION_PAGE -> title = getString(R.string.final_conclusion_page_title)
            else -> {}
        }
    }

    internal inner class DeductionFormPagerAdapter(fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager) {

        override fun getCount(): Int {
            return NUM_PAGES
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                SIGHT_PAGE -> SightFragment()
                NOSE_PAGE -> NoseFragment()
                PALATE_PAGE_A -> PalateFragmentA()
                PALATE_PAGE_B -> PalateFragmentB()
                INITIAL_CONCLUSION_PAGE -> InitialConclusionFragment()
                FINAL_CONCLUSION_PAGE -> FinalConclusionFragment()
                else -> throw IllegalStateException("Unexpected position $position")
            }
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val createdFragment = super.instantiateItem(container, position) as Fragment

            when (position) {
                SIGHT_PAGE -> mSightFragment = createdFragment as SightFragment
                NOSE_PAGE -> mNoseFragment = createdFragment as NoseFragment
                PALATE_PAGE_A -> mPalateFragmentA = createdFragment as PalateFragmentA
                PALATE_PAGE_B -> mPalateFragmentB = createdFragment as PalateFragmentB
                INITIAL_CONCLUSION_PAGE -> mInitialFragment = createdFragment as InitialConclusionFragment
                FINAL_CONCLUSION_PAGE -> mFinalFragment = createdFragment as FinalConclusionFragment
            }
            return createdFragment
        }
    }

    private fun validInputs(): Boolean {
        val singleTextViewFinalVariety = findViewById<AutoCompleteTextView>(TEXT_SINGLE_FINAL_GRAPE_VARIETY)
        val singleTextViewFinalCountry = findViewById<AutoCompleteTextView>(TEXT_SINGLE_FINAL_COUNTRY_ORIGIN)
        val singleTextViewFinalRegion = findViewById<AutoCompleteTextView>(TEXT_SINGLE_FINAL_REGION)
        val singleTextViewFinalQuality = findViewById<AutoCompleteTextView>(TEXT_SINGLE_FINAL_QUALITY)
        val singleTextViewFinalVintage = findViewById<AutoCompleteTextView>(TEXT_SINGLE_FINAL_VINTAGE)

        mUserFinalVarietyString = singleTextViewFinalVariety.text.toString()
        mUserFinalCountryString = singleTextViewFinalCountry.text.toString()
        mUserFinalRegionString = singleTextViewFinalRegion.text.toString()
        mUserFinalQualityString = singleTextViewFinalQuality.text.toString()

        mUserFinalVintageInteger = null
        val parseInteger = singleTextViewFinalVintage.text.toString()

        mUserFinalVintageInteger = if (parseInteger.isNotEmpty()) {
            Integer.parseInt(parseInteger)
        } else {
            0
        }

        var isValid = true

        if (mIsRedWine && !parseResourceArray(R.array.red_varieties).contains(mUserFinalVarietyString)) {
            mFinalFragment?.errorsFinalForm()?.setErrorVariety(getString(R.string.error_input_valid_grape))
            isValid = false
        } else if (!mIsRedWine && !parseResourceArray(R.array.white_varieties).contains(mUserFinalVarietyString)) {
            mFinalFragment?.errorsFinalForm()?.setErrorVariety(getString(R.string.error_input_valid_grape))
            isValid = false
        } else {
            mFinalFragment?.errorsFinalForm()?.setErrorVariety(null)
        }

        if (mUserFinalCountryString!!.isEmpty()) {
            mFinalFragment?.errorsFinalForm()?.setErrorCountry(getString(R.string.error_input_country_origin))
            isValid = false
        } else {
            mFinalFragment?.errorsFinalForm()?.setErrorCountry(null)
        }

        if (mUserFinalRegionString!!.isEmpty()) {
            mFinalFragment?.errorsFinalForm()?.setErrorRegion(getString(R.string.error_input_valid_region))
            isValid = false
        } else {
            mFinalFragment?.errorsFinalForm()?.setErrorRegion(null)
        }

        if (mUserFinalQualityString!!.isEmpty()) {
            mUserFinalQualityString = "None"
        }

        val year = Calendar.getInstance().get(Calendar.YEAR)
        if (mUserFinalVintageInteger!! > year || mUserFinalVintageInteger!! < 1900) {
            mFinalFragment?.errorsFinalForm()?.setErrorVintage(getString(R.string.error_input_valid_vintage))
            isValid = false
        } else {
            mFinalFragment?.errorsFinalForm()?.setErrorVintage(null)
        }

        val allEntries = mWinePreferences.all
        val requiredKeysInPreferences = ArrayList<Int>()

        var needNoseWoodRadios = false
        var needPalateWoodRadios = false
        var needSnackbar = false

        val radioGroups = if (mIsRedWine) AllRedRadioGroups else AllWhiteRadioGroups

        for (entry in allEntries) {
            val key = entry.key
            val value = entry.value ?: continue
            val wineFormKey = Helpers.castKey(key)
            if (SWITCH_NOSE_WOOD == wineFormKey) {
                val isChecked = Helpers.parseChecked(value)
                if (isChecked) {
                    needNoseWoodRadios = true
                }
            } else if (SWITCH_PALATE_WOOD == wineFormKey) {
                val isChecked = Helpers.parseChecked(value)
                if (isChecked) {
                    needPalateWoodRadios = true
                }
            }
        }

        for (entry in allEntries) {
            val key = entry.key
            val value = entry.value ?: continue
            val wineFormKey = Helpers.castKey(key)

            if (radioGroups.contains(wineFormKey)) {
                if (needNoseWoodRadios && NoseWoodRadioGroups.contains(wineFormKey)) {
                    requiredKeysInPreferences.add(wineFormKey)
                    val radioButtonSelection = Helpers.parseEntryValue(value)
                    if (radioButtonSelection == NONE_SELECTED) {
                        isValid = false
                        needSnackbar = true
                    }
                } else if (needPalateWoodRadios && PalateWoodRadioGroups.contains(wineFormKey)) {
                    requiredKeysInPreferences.add(wineFormKey)
                    val radioButtonSelection = Helpers.parseEntryValue(value)
                    if (radioButtonSelection == NONE_SELECTED) {
                        isValid = false
                        needSnackbar = true
                    }
                } else if (!AllWoodRadioGroups.contains(wineFormKey)) {
                    requiredKeysInPreferences.add(wineFormKey)
                    val radioButtonSelection = Helpers.parseEntryValue(value)
                    if (radioButtonSelection == NONE_SELECTED) {
                        isValid = false
                        needSnackbar = true
                    }
                }
            }
        }

        if (!needSnackbar) {
            for (key in radioGroups) {
                if (!requiredKeysInPreferences.contains(key)) {
                    if (AllWoodRadioGroups.contains(key)) {
                        if (NoseWoodRadioGroups.contains(key) && needNoseWoodRadios) {
                            isValid = false
                            needSnackbar = true
                            break
                        } else if (PalateWoodRadioGroups.contains(key) && needPalateWoodRadios) {
                            isValid = false
                            needSnackbar = true
                            break
                        }
                    } else {
                        isValid = false
                        needSnackbar = true
                        break
                    }
                }
            }
        }

        if (needSnackbar) {
            val snackbar = Snackbar.make(
                findViewById(R.id.deduction_form_coordinator),
                R.string.df_snackbar_make_selections, Snackbar.LENGTH_LONG
            )
            snackbar.setAction(R.string.df_snackbar_dismiss) { snackbar.dismiss() }
            snackbar.show()
        }

        return isValid
    }

    private fun retrieveSharedPreferencesValues(): SparseIntArray {
        val allEntries = mWinePreferences.all
        val wineFormSelections = SparseIntArray()

        for (entry in allEntries) {
            val key = entry.key
            val value = entry.value ?: continue
            val wineFormKey = Helpers.castKey(key)
            if (AllRadioGroups.contains(wineFormKey)) {
                val radioButtonSelection = Integer.parseInt(value.toString())
                if (radioButtonSelection != NONE_SELECTED) {
                    wineFormSelections.put(radioButtonSelection, CHECKED)
                }
            } else if (AllCheckBoxes.contains(wineFormKey)) {
                val checkValue = Integer.parseInt(value.toString())
                if (checkValue == CHECKED) {
                    wineFormSelections.put(wineFormKey, checkValue)
                }
            }
        }

        return wineFormSelections
    }

    fun onSubmitFinalConclusion(view: View) {
        isScoring(true)
        if (!validInputs()) {
            isScoring(false)
            return
        }

        val formSelections = retrieveSharedPreferencesValues()
        val scoreTask = GrapeVarietyScore(mIsRedWine)
        val formMapper = FormMapper()
        val varietyScoreDatabaseMap = formMapper.formToDbFormat(formSelections)

        lifecycleScope.launch {
            try {
                val topScoreVariety = scoreTask.calculateScore(varietyScoreDatabaseMap)
                onGrapeResult(topScoreVariety)
            } catch (e: Exception) {
                onGrapeFailure()
            }
        }
    }

    override fun onGrapeResult(topScoreVariety: String?) {
        resetSharedPreferences()
        resetAllTopScroll()
        setCurrentPageInPreferences(SIGHT_PAGE)
        mLaunchingIntent = true

        val intent = Intent(mContext, VarietyResultsActivity::class.java)
        if (mIsRedWine) {
            intent.putExtra(IS_RED_WINE, TRUE)
        }
        intent.putExtra(APP_VARIETY_GUESS_ID, topScoreVariety)
        intent.putExtra(USER_CONCLUSION_VARIETY, mUserFinalVarietyString)
        intent.putExtra(USER_CONCLUSION_COUNTRY, mUserFinalCountryString)
        intent.putExtra(USER_CONCLUSION_REGION, mUserFinalRegionString)
        intent.putExtra(USER_CONCLUSION_QUALITY, mUserFinalQualityString)
        intent.putExtra(USER_CONCLUSION_VINTAGE, mUserFinalVintageInteger)
        isScoring(false)
        startActivity(intent)
        finish()
    }

    private fun isScoring(loading: Boolean) {
        if (loading) {
            mFinalFragment?.showLoadingIndicator()
        } else {
            mFinalFragment?.hideLoadingIndicator()
        }
    }

    override fun onGrapeFailure() {
        isScoring(false)
        Helpers.makeToastShort(mContext, R.string.da_toast_unable_to_score)
    }

    private fun parseResourceArray(resourceId: Int): List<String> {
        return Arrays.asList(*resources.getStringArray(resourceId))
    }
}
