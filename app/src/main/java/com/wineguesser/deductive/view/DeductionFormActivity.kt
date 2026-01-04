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
import android.widget.AutoCompleteTextView
import android.widget.CheckBox
import android.widget.MultiAutoCompleteTextView
import android.widget.RadioGroup
import android.widget.ScrollView
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.wineguesser.deductive.R
import com.wineguesser.deductive.util.FormMapper
import com.wineguesser.deductive.util.GrapeVarietyScore
import com.wineguesser.deductive.util.GrapeVarietyScoreResult
import com.wineguesser.deductive.util.Helpers
import kotlinx.coroutines.launch
import java.util.Arrays
import java.util.Calendar

class DeductionFormActivity : AppCompatActivity(), GrapeVarietyScoreResult {

    private lateinit var pager: ViewPager2
    private lateinit var winePreferences: SharedPreferences
    private lateinit var activityPreferences: SharedPreferences
    private var isRedWine: Boolean = false
    private var launchingIntent: Boolean = false

    private var menuShowWhiteScreen: MenuItem? = null

    private var sharedPreferenceChangeListener: OnSharedPreferenceChangeListener? = null
    private var onPageChangeCallback: ViewPager2.OnPageChangeCallback? = null

    private lateinit var context: Context
    private var sightFragment: SightFragment? = null
    private var noseFragment: NoseFragment? = null
    private var palateFragmentA: PalateFragmentA? = null
    private var palateFragmentB: PalateFragmentB? = null
    private var initialFragment: InitialConclusionFragment? = null
    private var finalFragment: FinalConclusionFragment? = null

    private var userFinalVarietyString: String? = null
    private var userFinalCountryString: String? = null
    private var userFinalRegionString: String? = null
    private var userFinalQualityString: String? = null
    private var userFinalVintageInteger: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        context = this
        activityPreferences = getPreferences(MODE_PRIVATE)

        setSharedPreferences(intent)
        setUpActionBar(findViewById(R.id.toolbar))

        supportFragmentManager.registerFragmentLifecycleCallbacks(
            object : FragmentManager.FragmentLifecycleCallbacks() {
                override fun onFragmentViewCreated(
                    fm: FragmentManager,
                    f: Fragment,
                    v: View,
                    savedInstanceState: Bundle?
                ) {
                    when (f) {
                        is SightFragment -> sightFragment = f
                        is NoseFragment -> noseFragment = f
                        is PalateFragmentA -> palateFragmentA = f
                        is PalateFragmentB -> palateFragmentB = f
                        is InitialConclusionFragment -> initialFragment = f
                        is FinalConclusionFragment -> finalFragment = f
                    }
                }
            }, true
        )

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

    private fun setSharedPreferences(parentIntent: Intent?) {
        activityPreferences.edit {
            if (parentIntent != null && parentIntent.hasExtra(IS_RED_WINE)) {
                setContentView(R.layout.activity_red_deduction_form)

                putBoolean(IS_RED_WINE, TRUE)
                isRedWine = true

                winePreferences =
                    getSharedPreferences(RED_WINE_FORM_PREFERENCES, MODE_PRIVATE)
                pager = findViewById(R.id.view_pager_red_deduction)
            } else {
                setContentView(R.layout.activity_white_deduction_form)

                putBoolean(IS_RED_WINE, FALSE)
                isRedWine = false

                winePreferences =
                    getSharedPreferences(WHITE_WINE_FORM_PREFERENCES, MODE_PRIVATE)
                pager = findViewById(R.id.view_pager_white_deduction)
            }
            val pagerAdapter = DeductionFormPagerAdapter(this@DeductionFormActivity)
            pager.adapter = pagerAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        onPageChangeCallback = createOnPageChangeListener()
        onPageChangeCallback?.let { pager.registerOnPageChangeCallback(it) }
    }

    private fun createOnPageChangeListener(): ViewPager2.OnPageChangeCallback {
        return object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                syncCurrentTitle(position)
                syncCurrentMenuOptions(position, menuShowWhiteScreen)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        onPageChangeCallback?.let { pager.unregisterOnPageChangeCallback(it) }
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
                Helpers.makeToastShort(context, R.string.form_cleared)
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
                    ContextCompat.getColor(context, R.color.colorPrimaryBackground)
                )
            }
        }
    }

    private fun toggleWineEvaluationMode(menuItem: MenuItem, sightScroll: ScrollView) {
        val root = sightScroll.rootView
        if (sightScroll.isVisible) {
            menuItem.setIcon(R.drawable.ic_menu_visibility_off_24px)
            root.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBackground))
        } else {
            menuItem.setIcon(R.drawable.ic_menu_visibility_on_24px)
            root.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
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
            root.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBackground))
            sightScroll.visibility = View.VISIBLE
        }
    }

    private fun setSightWhiteScreen(isEnabled: Boolean, view: View?) {
        if (view != null) {
            if (isEnabled) {
                menuShowWhiteScreen?.setIcon(R.drawable.ic_menu_visibility_on_24px)
                val root = view.rootView
                root.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                view.visibility = View.INVISIBLE
                Helpers.makeToastShort(context, R.string.da_toast_showing_screen_for_wine)
            } else {
                menuShowWhiteScreen?.setIcon(R.drawable.ic_menu_visibility_off_24px)
                val root = view.rootView
                root.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBackground))
                view.visibility = View.VISIBLE
                Helpers.makeToastShort(context, R.string.da_toast_hiding_screen_for_wine)
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
        if (pager.currentItem != SIGHT_PAGE) {
            pager.currentItem = SIGHT_PAGE
        }
    }

    override fun onPause() {
        super.onPause()
        unRegisterSharedPreferencesListener()
        if (!launchingIntent) {
            setCurrentPageInPreferences(getCurrentPageFromPager())
            launchingIntent = false
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
        activityPreferences.edit {
            if (isRedWine) {
                putInt(CURRENT_PAGE_RED, page)
            } else {
                putInt(CURRENT_PAGE_WHITE, page)
            }
        }
    }

    private fun getCurrentPageFromPreferences(): Int {
        return if (isRedWine) {
            activityPreferences.getInt(CURRENT_PAGE_RED, 0)
        } else {
            activityPreferences.getInt(CURRENT_PAGE_WHITE, 0)
        }
    }

    private fun setCurrentPage(page: Int) {
        pager.currentItem = page
    }

    private fun getCurrentPageFromPager(): Int {
        return pager.currentItem
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
        sharedPreferenceChangeListener =
            OnSharedPreferenceChangeListener { sharedPreferences, key ->
                if (key == null) return@OnSharedPreferenceChangeListener
                val viewId = Helpers.castKey(key)
                val view = findViewById<View>(viewId)

                if (view != null) {
                    if (view is RadioGroup) {
                        view.check(sharedPreferences.getInt(key, NONE_SELECTED))
                    } else if (view is CheckBox) {
                        view.isChecked =
                            Helpers.castChecked(sharedPreferences.getInt(key, NOT_CHECKED))
                    } else if (view is Switch) {
                        view.isChecked =
                            Helpers.castChecked(sharedPreferences.getInt(key, NOT_CHECKED))
                    } else if (view is MultiAutoCompleteTextView) {
                        view.setText(sharedPreferences.getString(key, ""))
                    } else if (view is AutoCompleteTextView) {
                        view.setText(sharedPreferences.getString(key, ""))
                    }
                }
            }
        winePreferences.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener)
    }

    private fun unRegisterSharedPreferencesListener() {
        winePreferences.unregisterOnSharedPreferenceChangeListener(sharedPreferenceChangeListener)
    }

    private fun resetSharedPreferences() {
        val allEntries = winePreferences.all
        winePreferences.edit {
            for (key in allEntries.keys) {
                val viewId = Helpers.castKey(key)
                if (AllRadioGroups.contains(viewId)) {
                    putInt(key, NONE_SELECTED)
                } else if (AllCheckBoxes.contains(viewId)) {
                    putInt(key, NOT_CHECKED)
                } else if (AllSwitches.contains(viewId)) {
                    putInt(key, NOT_CHECKED)
                } else if (AllAutoText.contains(viewId)) {
                    putString(key, "")
                    val autoView = findViewById<AutoCompleteTextView>(viewId)
                    autoView?.setText("")
                } else if (AllAutoMultiText.contains(viewId)) {
                    putString(key, "")
                    val multiView = findViewById<MultiAutoCompleteTextView>(viewId)
                    multiView?.setText("")
                }
            }
        }

        activityPreferences.edit {
            if (isRedWine) {
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
        winePreferences.edit {
            putInt(key.toString(), state)
        }
    }

    private fun saveCheckBoxState(key: Int, checkedInt: Int) {
        winePreferences.edit {
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
            noseFragment?.syncWoodRadioState(true)
        } else if (switchId == SWITCH_PALATE_WOOD) {
            palateFragmentA?.syncWoodRadioState(true)
        }
    }

    private fun resetAllTopScroll() {
        sightFragment?.scrollToTop()
        noseFragment?.scrollToTop()
        palateFragmentA?.scrollToTop()
        palateFragmentB?.scrollToTop()
        initialFragment?.scrollToTop()
        finalFragment?.scrollToTop()
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

    private class DeductionFormPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment {
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
    }

    private fun validInputs(): Boolean {
        val singleTextViewFinalVariety = findViewById<AutoCompleteTextView>(TEXT_SINGLE_FINAL_GRAPE_VARIETY)
        val singleTextViewFinalCountry = findViewById<AutoCompleteTextView>(TEXT_SINGLE_FINAL_COUNTRY_ORIGIN)
        val singleTextViewFinalRegion = findViewById<AutoCompleteTextView>(TEXT_SINGLE_FINAL_REGION)
        val singleTextViewFinalQuality = findViewById<AutoCompleteTextView>(TEXT_SINGLE_FINAL_QUALITY)
        val singleTextViewFinalVintage = findViewById<AutoCompleteTextView>(TEXT_SINGLE_FINAL_VINTAGE)

        userFinalVarietyString = singleTextViewFinalVariety.text.toString()
        userFinalCountryString = singleTextViewFinalCountry.text.toString()
        userFinalRegionString = singleTextViewFinalRegion.text.toString()
        userFinalQualityString = singleTextViewFinalQuality.text.toString()

        userFinalVintageInteger = null
        val parseInteger = singleTextViewFinalVintage.text.toString()

        userFinalVintageInteger = if (parseInteger.isNotEmpty()) {
            Integer.parseInt(parseInteger)
        } else {
            0
        }

        var isValid = true

        if (isRedWine && !parseResourceArray(R.array.red_varieties).contains(userFinalVarietyString)) {
            finalFragment?.errorsFinalForm()?.setErrorVariety(getString(R.string.error_input_valid_grape))
            isValid = false
        } else if (!isRedWine && !parseResourceArray(R.array.white_varieties).contains(userFinalVarietyString)) {
            finalFragment?.errorsFinalForm()?.setErrorVariety(getString(R.string.error_input_valid_grape))
            isValid = false
        } else {
            finalFragment?.errorsFinalForm()?.setErrorVariety(null)
        }

        if (userFinalCountryString!!.isEmpty()) {
            finalFragment?.errorsFinalForm()?.setErrorCountry(getString(R.string.error_input_country_origin))
            isValid = false
        } else {
            finalFragment?.errorsFinalForm()?.setErrorCountry(null)
        }

        if (userFinalRegionString!!.isEmpty()) {
            finalFragment?.errorsFinalForm()?.setErrorRegion(getString(R.string.error_input_valid_region))
            isValid = false
        } else {
            finalFragment?.errorsFinalForm()?.setErrorRegion(null)
        }

        if (userFinalQualityString!!.isEmpty()) {
            userFinalQualityString = "None"
        }

        val year = Calendar.getInstance().get(Calendar.YEAR)
        if (userFinalVintageInteger!! > year || userFinalVintageInteger!! < 1900) {
            finalFragment?.errorsFinalForm()?.setErrorVintage(getString(R.string.error_input_valid_vintage))
            isValid = false
        } else {
            finalFragment?.errorsFinalForm()?.setErrorVintage(null)
        }

        val allEntries = winePreferences.all
        val requiredKeysInPreferences = ArrayList<Int>()

        var needNoseWoodRadios = false
        var needPalateWoodRadios = false
        var needSnackbar = false

        val radioGroups = if (isRedWine) AllRedRadioGroups else AllWhiteRadioGroups

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
        val allEntries = winePreferences.all
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
        val scoreTask = GrapeVarietyScore(isRedWine)
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
        launchingIntent = true

        val intent = Intent(context, VarietyResultsActivity::class.java)
        if (isRedWine) {
            intent.putExtra(IS_RED_WINE, TRUE)
        }
        intent.putExtra(APP_VARIETY_GUESS_ID, topScoreVariety)
        intent.putExtra(USER_CONCLUSION_VARIETY, userFinalVarietyString)
        intent.putExtra(USER_CONCLUSION_COUNTRY, userFinalCountryString)
        intent.putExtra(USER_CONCLUSION_REGION, userFinalRegionString)
        intent.putExtra(USER_CONCLUSION_QUALITY, userFinalQualityString)
        intent.putExtra(USER_CONCLUSION_VINTAGE, userFinalVintageInteger)
        isScoring(false)
        startActivity(intent)
        finish()
    }

    private fun isScoring(loading: Boolean) {
        if (loading) {
            finalFragment?.showLoadingIndicator()
        } else {
            finalFragment?.hideLoadingIndicator()
        }
    }

    override fun onGrapeFailure() {
        isScoring(false)
        Helpers.makeToastShort(context, R.string.da_toast_unable_to_score)
    }

    private fun parseResourceArray(resourceId: Int): List<String> {
        return Arrays.asList(*resources.getStringArray(resourceId))
    }
}
