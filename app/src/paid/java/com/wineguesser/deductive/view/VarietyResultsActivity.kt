package com.wineguesser.deductive.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.splashscreen.SplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.wineguesser.deductive.R
import com.wineguesser.deductive.databinding.ActivityVarietyResultsBinding
import com.wineguesser.deductive.model.ConclusionRecord
import com.wineguesser.deductive.repository.ConclusionsRepository
import com.wineguesser.deductive.repository.DatabaseContract
import com.wineguesser.deductive.util.SpecialCharArrayAdapter
import com.wineguesser.deductive.viewmodel.ConclusionInputErrorsViewModel
import com.wineguesser.deductive.view.*
import com.wineguesser.deductive.viewmodel.VarietyResultsViewModel
import com.wineguesser.deductive.view.*
import java.util.ArrayList
import java.util.Arrays
import java.util.Calendar

class VarietyResultsActivity : AppCompatActivity() {

    private lateinit var inputForm: VarietyResultsViewModel
    private lateinit var inputErrors: ConclusionInputErrorsViewModel
    private lateinit var binding: ActivityVarietyResultsBinding

    private lateinit var mAuth: FirebaseAuth
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null
    private var mIsRedWine: Boolean = false

    private var mActualLabel: String? = null
    private var mActualVariety: String? = null
    private var mActualCountry: String? = null
    private var mActualRegion: String? = null
    private var mActualQuality: String? = null
    private var mActualVintage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        // SplashScreen.installSplashScreen(this)
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        binding = ActivityVarietyResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(insets.left, insets.top, insets.right, insets.bottom)
            windowInsets
        }

        setTitle(R.string.variety_results_activity_title)
        inputForm = ViewModelProvider(this)[VarietyResultsViewModel::class.java]
        inputErrors = ViewModelProvider(this)[ConclusionInputErrorsViewModel::class.java]
        binding.lifecycleOwner = this
        binding.actualWine = inputForm
        binding.inputError = inputErrors

        val mContext: Context = this

        if (savedInstanceState != null) {
            inputForm.actualLabel.value = savedInstanceState.getString(FORM_ACTUAL_LABEL)
            inputForm.actualVariety.value = savedInstanceState.getString(FORM_ACTUAL_VARIETY)
            inputForm.actualCountry.value = savedInstanceState.getString(FORM_ACTUAL_COUNTRY)
            inputForm.actualRegion.value = savedInstanceState.getString(FORM_ACTUAL_REGION)
            inputForm.actualQuality.value = savedInstanceState.getString(FORM_ACTUAL_QUALITY)
            inputForm.actualVintage.value = savedInstanceState.getInt(FORM_ACTUAL_VINTAGE).toString()
        }

        val parentIntent = intent
        if (parentIntent != null) {
            mIsRedWine = parentIntent.hasExtra(IS_RED_WINE)

            val bundle = parentIntent.extras
            if (bundle != null) {
                bundle.getString(APP_VARIETY_GUESS_ID)?.let {
                    inputForm.setAppVarietyById(mIsRedWine, it)
                }
                inputForm.userVariety.value = bundle.getString(USER_CONCLUSION_VARIETY)
                inputForm.userCountry.value = bundle.getString(USER_CONCLUSION_COUNTRY)
                inputForm.userRegion.value = bundle.getString(USER_CONCLUSION_REGION)
                inputForm.userQuality.value = bundle.getString(USER_CONCLUSION_QUALITY)
                inputForm.userVintage.value = bundle.getInt(USER_CONCLUSION_VINTAGE).toString()
            }
        }

        mAuth = FirebaseAuth.getInstance()

        val varieties = ArrayList(parseResourceArray(R.array.all_varieties))
        val countries = ArrayList(parseResourceArray(R.array.all_countries))
        val regions = ArrayList(parseResourceArray(R.array.all_regions))
        val qualities = ArrayList(parseResourceArray(R.array.all_qualities))

        binding.autoTextActualVariety.setAdapter(
            SpecialCharArrayAdapter(
                mContext,
                android.R.layout.simple_dropdown_item_1line, varieties
            )
        )

        binding.autoTextActualCountry.setAdapter(
            SpecialCharArrayAdapter(
                mContext,
                android.R.layout.simple_dropdown_item_1line, countries
            )
        )

        binding.autoTextActualRegion.setAdapter(
            SpecialCharArrayAdapter(
                mContext,
                android.R.layout.simple_dropdown_item_1line, regions
            )
        )

        binding.autoTextActualQuality.setAdapter(
            SpecialCharArrayAdapter(
                mContext,
                android.R.layout.simple_dropdown_item_1line, qualities
            )
        )

        binding.autoTextActualVintage.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                onButtonWineResultSave(v)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    override fun onPause() {
        super.onPause()
        mActualLabel = inputForm.actualLabel.value
        mActualVariety = inputForm.actualVariety.value
        mActualCountry = inputForm.actualCountry.value
        mActualRegion = inputForm.actualRegion.value
        mActualVintage = inputForm.actualVintage.value
        mActualQuality = inputForm.actualQuality.value
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(FORM_ACTUAL_LABEL, mActualLabel)
        outState.putString(FORM_ACTUAL_VARIETY, mActualVariety)
        outState.putString(FORM_ACTUAL_COUNTRY, mActualCountry)
        outState.putString(FORM_ACTUAL_REGION, mActualRegion)
        outState.putString(FORM_ACTUAL_QUALITY, mActualQuality)
        if (mActualVintage != null) {
            // See note in Free version about likely bug in original Java. 
            // Maintaining original bug's structural equivalent (saving string) 
            // but fixed read logic to match.
            outState.putString(FORM_ACTUAL_VINTAGE, mActualVintage)
        } else {
             outState.putString(FORM_ACTUAL_VINTAGE, null)
        }
    }

    private fun parseResourceArray(resourceId: Int): List<String> {
        return Arrays.asList(*resources.getStringArray(resourceId))
    }

    public override fun onStart() {
        super.onStart()

        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                inputForm.setResultButtonText(getString(R.string.save_result))
            } else {
                inputForm.setResultButtonText(getString(R.string.log_in_for_result_save))
            }
        }

        mAuth.addAuthStateListener(mAuthListener!!)
    }

    public override fun onStop() {
        super.onStop()
        mAuthListener?.let { mAuth.removeAuthStateListener(it) }
    }

    fun onButtonWineResultSave(view: View?) {
        if (!validInputs()) {
            return
        }

        val user = mAuth.currentUser
        if (user != null) {
            val uid = user.uid

            val conclusionRecord = ConclusionRecord()
            conclusionRecord.appConclusionVariety = inputForm.appVariety?.value
            conclusionRecord.actualLabel = inputForm.actualLabel.value
            conclusionRecord.actualVariety = inputForm.actualVariety.value
            conclusionRecord.actualCountry = inputForm.actualCountry.value
            conclusionRecord.actualRegion = inputForm.actualRegion.value
            conclusionRecord.actualQuality = inputForm.actualQuality.value
             try {
                inputForm.actualVintage.value?.let { 
                    conclusionRecord.actualVintage = it.toInt()
                }
            } catch (e: NumberFormatException) {
                 conclusionRecord.actualVintage = 0
            }

            conclusionRecord.userConclusionVariety = inputForm.userVariety.value
            conclusionRecord.userConclusionCountry = inputForm.userCountry.value
            conclusionRecord.userConclusionRegion = inputForm.userRegion.value
            conclusionRecord.userConclusionQuality = inputForm.userQuality.value
             try {
                inputForm.userVintage.value?.let {
                    conclusionRecord.userConclusionVintage = it.toInt()
                }
            } catch (e: NumberFormatException) {
                 conclusionRecord.userConclusionVintage = 0
            }

            val conclusionsRepository = ConclusionsRepository()
            conclusionsRepository.saveConclusionRecord(uid, conclusionRecord)

            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        } else {
            val providers = listOf(
                AuthUI.IdpConfig.EmailBuilder().build()
            )

            val RC_SIGN_IN = 43

            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build(),
                RC_SIGN_IN
            )
        }
    }

    private fun validInputs(): Boolean {
        val actualLabelString = inputForm.actualLabel.value
        val actualVarietyString = inputForm.actualVariety.value
        val actualCountryString = inputForm.actualCountry.value
        val actualRegionString = inputForm.actualRegion.value
        val actualQualityString = inputForm.actualQuality.value
        val parseInteger = inputForm.actualVintage.value

        var isValid = true

        if (actualLabelString.isNullOrEmpty()) {
            inputErrors.setErrorLabel(getString(R.string.error_input_valid_label))
        }

        if (mIsRedWine
            && !parseResourceArray(R.array.red_varieties).contains(actualVarietyString)
        ) {
            inputErrors.setErrorVariety(getString(R.string.error_input_valid_grape))
            isValid = false
        } else if (!mIsRedWine
            && !parseResourceArray(R.array.white_varieties).contains(actualVarietyString)
        ) {
            inputErrors.setErrorVariety(getString(R.string.error_input_valid_grape))
            isValid = false
        } else {
            inputErrors.setErrorVariety(null)
        }

        if (actualCountryString.isNullOrEmpty()
            || !parseResourceArray(R.array.all_countries).contains(actualCountryString)
        ) {
            inputErrors.setErrorCountry(getString(R.string.error_input_country_origin))
            isValid = false
        } else {
            inputErrors.setErrorCountry(null)
        }

        if (actualRegionString.isNullOrEmpty()
            || !parseResourceArray(R.array.all_regions).contains(actualRegionString)
        ) {
            inputErrors.setErrorRegion(getString(R.string.error_input_valid_region))
            isValid = false
        } else {
            inputErrors.setErrorRegion(null)
        }

        if (actualQualityString.isNullOrEmpty()) {
            inputForm.setActualQuality("None")
        }

        if (parseInteger.isNullOrEmpty()) {
            inputErrors.setErrorVintage(getString(R.string.error_input_valid_vintage))
            isValid = false
        } else {
            try {
                val actualVintageInteger = parseInteger.toInt()
                if (actualVintageInteger > Calendar.getInstance().get(Calendar.YEAR)
                    || actualVintageInteger < 1900
                ) {
                    inputErrors.setErrorVintage(getString(R.string.error_input_valid_vintage))
                    isValid = false
                } else {
                    inputErrors.setErrorVintage(null)
                }
            } catch (e: NumberFormatException) {
                inputErrors.setErrorVintage(getString(R.string.error_input_valid_vintage))
                isValid = false
            }
        }

        return isValid
    }
}
