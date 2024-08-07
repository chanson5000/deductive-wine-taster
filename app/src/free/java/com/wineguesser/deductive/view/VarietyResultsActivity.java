package com.wineguesser.deductive.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wineguesser.deductive.R;
import com.wineguesser.deductive.databinding.ActivityVarietyResultsBinding;
import com.wineguesser.deductive.model.ConclusionRecord;
import com.wineguesser.deductive.repository.ConclusionsRepository;
import com.wineguesser.deductive.repository.DatabaseContract;
import com.wineguesser.deductive.util.Helpers;
import com.wineguesser.deductive.util.SpecialCharArrayAdapter;
import com.wineguesser.deductive.viewmodel.ConclusionInputErrorsViewModel;
import com.wineguesser.deductive.viewmodel.VarietyResultsViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VarietyResultsActivity extends AppCompatActivity implements DatabaseContract,
        DeductionFormContract {

    private VarietyResultsViewModel inputForm;
    private ConclusionInputErrorsViewModel inputErrors;

    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.autoText_actual_variety)
    AutoCompleteTextView mSingleViewActualVariety;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.autoText_actual_country)
    AutoCompleteTextView mSingleViewActualCountry;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.autoText_actual_region)
    AutoCompleteTextView mSingleViewActualRegion;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.autoText_actual_quality)
    AutoCompleteTextView mSingleViewActualQuality;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.autoText_actual_vintage)
    AutoCompleteTextView mEditTextActualVintage;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private boolean mIsRedWine;

    private String mActualLabel;
    private String mActualVariety;
    private String mActualCountry;
    private String mActualRegion;
    private String mActualQuality;
    private String mActualVintage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set data binding.
        ActivityVarietyResultsBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_variety_results);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setTitle(R.string.variety_results_activity_title);
        // Initialize our view models.
        inputForm = ViewModelProviders.of(this)
                .get(VarietyResultsViewModel.class);
        inputErrors = ViewModelProviders.of(this)
                .get(ConclusionInputErrorsViewModel.class);
        // Set our lifecycle owner for our view models to work.
        binding.setLifecycleOwner((LifecycleOwner) this);
        // Set our variables to our binding.
        binding.setActualWine(inputForm);
        binding.setInputError(inputErrors);

        Context mContext = this;
        // Still using Butterknife for setting adapters for our AutoCompleteTextViews.
        ButterKnife.bind(this);

        // Check for any relevant data in our savedInstanceState.
        if (savedInstanceState != null) {
            inputForm.setActualLabel(savedInstanceState.getString(FORM_ACTUAL_LABEL));
            inputForm.setActualVariety(savedInstanceState.getString(FORM_ACTUAL_VARIETY));
            inputForm.setActualCountry(savedInstanceState.getString(FORM_ACTUAL_COUNTRY));
            inputForm.setActualRegion(savedInstanceState.getString(FORM_ACTUAL_REGION));
            inputForm.setActualQuality(savedInstanceState.getString(FORM_ACTUAL_QUALITY));
            inputForm.setActualVintage(Integer.toString(savedInstanceState.getInt(FORM_ACTUAL_VINTAGE)));
        }

        // Check for the data from our parent intent.
        Intent parentIntent = getIntent();
        // A Guess Id should have been passed.
        if (parentIntent != null) {

            // Check to see if it was a red wine.
            mIsRedWine = parentIntent.hasExtra(IS_RED_WINE);

            // Retrieve all of the data from the parent intent and put it to our view model.
            Bundle bundle = parentIntent.getExtras();
            if (bundle != null) {
                inputForm.setAppVarietyById(mIsRedWine, bundle.getString(APP_VARIETY_GUESS_ID));
                inputForm.setUserVariety(bundle.getString(USER_CONCLUSION_VARIETY));
                inputForm.setUserCountry(bundle.getString(USER_CONCLUSION_COUNTRY));
                inputForm.setUserRegion(bundle.getString(USER_CONCLUSION_REGION));
                inputForm.setUserQuality(bundle.getString(USER_CONCLUSION_QUALITY));
                inputForm.setUserVintage(Integer.toString(bundle.getInt(USER_CONCLUSION_VINTAGE)));
            }
        }

        // Get our auth instance for user validation.
        mAuth = FirebaseAuth.getInstance();

        List<String> varieties = new ArrayList<>(parseResourceArray(R.array.all_varieties));
        List<String> countries = new ArrayList<>(parseResourceArray(R.array.all_countries));
        List<String> regions = new ArrayList<>(parseResourceArray(R.array.all_regions));
        List<String> qualities = new ArrayList<>(parseResourceArray(R.array.all_qualities));

        mSingleViewActualVariety.setAdapter(new SpecialCharArrayAdapter<>(mContext,
                android.R.layout.simple_dropdown_item_1line, varieties));

        mSingleViewActualCountry.setAdapter(new SpecialCharArrayAdapter<>(mContext,
                android.R.layout.simple_dropdown_item_1line, countries));

        mSingleViewActualRegion.setAdapter(new SpecialCharArrayAdapter<>(mContext,
                android.R.layout.simple_dropdown_item_1line, regions));

        mSingleViewActualQuality.setAdapter(new SpecialCharArrayAdapter<>(mContext,
                android.R.layout.simple_dropdown_item_1line, qualities));

        mEditTextActualVintage.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                onButtonWineResultSave(v);
                return true;
            }
            return false;
        });
    }

    @Override
    public void onPause() {
        super.onPause();

        mActualLabel = inputForm.getActualLabel().getValue();
        mActualVariety = inputForm.getActualVariety().getValue();
        mActualCountry = inputForm.getActualCountry().getValue();
        mActualRegion = inputForm.getActualRegion().getValue();
        mActualVintage = inputForm.getActualVintage().getValue();
        mActualQuality = inputForm.getActualQuality().getValue();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString(FORM_ACTUAL_LABEL, mActualLabel);
        savedInstanceState.putString(FORM_ACTUAL_VARIETY, mActualVariety);
        savedInstanceState.putString(FORM_ACTUAL_COUNTRY, mActualCountry);
        savedInstanceState.putString(FORM_ACTUAL_REGION, mActualRegion);
        savedInstanceState.putString(FORM_ACTUAL_QUALITY, mActualQuality);
        savedInstanceState.putString(FORM_ACTUAL_VINTAGE, mActualVintage);
    }

    private List<String> parseResourceArray(int resourceId) {
        return Arrays.asList(getResources().getStringArray(resourceId));
    }

    @Override
    public void onStart() {
        super.onStart();

        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                inputForm.setResultButtonText(getString(R.string.save_result));
            } else {
                inputForm.setResultButtonText(getString(R.string.log_in_for_result_save));
            }
        };

        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();

        mAuth.removeAuthStateListener(mAuthListener);
    }

    public void onButtonWineResultSave(View view) {
        // Validate our inputs.
        if (!validInputs()) {
            return;
        }

        // Validate our user.
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String uid = user.getUid();

            ConclusionRecord conclusionRecord = new ConclusionRecord();
            conclusionRecord.setAppConclusionVariety(inputForm.getAppVariety().getValue());
            conclusionRecord.setActualLabel(inputForm.getActualLabel().getValue());
            conclusionRecord.setActualVariety(inputForm.getActualVariety().getValue());
            conclusionRecord.setActualCountry(inputForm.getActualCountry().getValue());
            conclusionRecord.setActualRegion(inputForm.getActualRegion().getValue());
            conclusionRecord.setActualQuality(inputForm.getActualQuality().getValue());
            conclusionRecord.setActualVintage(
                    Integer.parseInt(inputForm.getActualVintage().getValue()));

            conclusionRecord.setUserConclusionVariety(inputForm.getUserVariety().getValue());
            conclusionRecord.setUserConclusionCountry(inputForm.getUserCountry().getValue());
            conclusionRecord.setUserConclusionRegion(inputForm.getUserRegion().getValue());
            conclusionRecord.setUserConclusionQuality(inputForm.getUserQuality().getValue());
            conclusionRecord.setUserConclusionVintage(
                    Integer.parseInt(inputForm.getUserVintage().getValue()));

            ConclusionsRepository conclusionsRepository = new ConclusionsRepository();
            conclusionsRepository.saveConclusionRecord(uid, conclusionRecord);
            conclusionRecord.setUserId(uid);

            Intent intent = new Intent(this, HistoryRecordActivity.class);
            intent.putExtra(Helpers.CONCLUSION_PARCEL, conclusionRecord);
            startActivity(intent);
            finish();
        } else {
            List<AuthUI.IdpConfig> providers = Collections.singletonList(
                    new AuthUI.IdpConfig.EmailBuilder().build()
            );

            int RC_SIGN_IN = 43;

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN);
        }
    }

    private boolean validInputs() {
        String actualLabelString = inputForm.getActualLabel().getValue();
        String actualVarietyString = inputForm.getActualVariety().getValue();
        String actualCountryString = inputForm.getActualCountry().getValue();
        String actualRegionString = inputForm.getActualRegion().getValue();
        String actualQualityString = inputForm.getActualQuality().getValue();
        String parseInteger = inputForm.getActualVintage().getValue();

        boolean isValid = true;

        // Check that user has provided their conclusion of grape variety.
        if (actualLabelString == null || actualLabelString.isEmpty()) {
            inputErrors.setErrorLabel(getString(R.string.error_input_valid_label));
        }

        if (mIsRedWine
                && !parseResourceArray(R.array.red_varieties).contains(actualVarietyString)) {
            inputErrors.setErrorVariety(getString(R.string.error_input_valid_grape));
            isValid = false;
        } else if (!mIsRedWine
                && !parseResourceArray(R.array.white_varieties).contains(actualVarietyString)) {
            inputErrors.setErrorVariety(getString(R.string.error_input_valid_grape));
            isValid = false;
        } else {
            inputErrors.setErrorVariety(null);
        }

        if (actualCountryString == null || actualCountryString.isEmpty()
                || !parseResourceArray(R.array.all_countries).contains(actualCountryString)) {
            inputErrors.setErrorCountry(getString(R.string.error_input_country_origin));
            isValid = false;
        } else {
            inputErrors.setErrorCountry(null);
        }

        if (actualRegionString == null || actualRegionString.isEmpty()
                || !parseResourceArray(R.array.all_regions).contains(actualRegionString)) {
            inputErrors.setErrorRegion(getString(R.string.error_input_valid_region));
            isValid = false;
        } else {
            inputErrors.setErrorRegion(null);
        }

        if (actualQualityString == null || actualQualityString.isEmpty()) {
            inputForm.setActualQuality("None");
        }

        if (parseInteger == null || parseInteger.isEmpty()) {
            inputErrors.setErrorVintage(getString(R.string.error_input_valid_vintage));
            isValid = false;
        } else {
            int actualVintageInteger = Integer.parseInt(parseInteger);

            if (actualVintageInteger > Calendar.getInstance().get(Calendar.YEAR)
                    || actualVintageInteger < 1900) {
                inputErrors.setErrorVintage(getString(R.string.error_input_valid_vintage));
                isValid = false;
            } else {
                inputErrors.setErrorVintage(null);
            }
        }

        return isValid;
    }
}

