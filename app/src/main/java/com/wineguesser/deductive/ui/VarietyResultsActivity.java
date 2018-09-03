package com.wineguesser.deductive.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wineguesser.deductive.R;
import com.wineguesser.deductive.databinding.ActivityActualWineBinding;
import com.wineguesser.deductive.model.ConclusionRecord;
import com.wineguesser.deductive.model.ErrorsFinalForm;
import com.wineguesser.deductive.repository.DatabaseContract;
import com.wineguesser.deductive.viewmodel.VarietyResultsViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VarietyResultsActivity extends AppCompatActivity implements DatabaseContract {

    String FORM_ACTUAL_VARIETY = "FORM_ACTUAL_VARIETY";
    String FORM_ACTUAL_COUNTRY = "FORM_ACTUAL_COUNTRY";
    String FORM_ACTUAL_REGION = "FORM_ACTUAL_REGION";
    String FORM_ACTUAL_QUALITY = "FORM_ACTUAL_QUALITY";
    String FORM_ACTUAL_VINTAGE = "FORM_ACTUAL_VINTAGE";

    private Context mContext;
    VarietyResultsViewModel actualWineForm;

    private static DatabaseReference mDbReferenceUsers;
    private static DatabaseReference mDbReferenceUserConclusions;

    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.textView_our_guess)
    TextView mTextViewAppConclusion;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.textView_user_variety)
    TextView mTextViewUserConclusion;
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
    AutoCompleteTextView mSingleViewActualVintage;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.wine_result_save)
    Button mButtonWineResultSave;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private InterstitialAd mInterstitialAd;
    private ErrorsFinalForm errorsForm = new ErrorsFinalForm();

    private String mActualVariety;
    private String mActualCountry;
    private String mActualRegion;
    private String mActualQuality;
    private String mActualVintage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityActualWineBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_actual_wine);

        actualWineForm = ViewModelProviders.of(this)
                .get(VarietyResultsViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setActualWine(actualWineForm);

        errorsForm = new ErrorsFinalForm();
        binding.setErrorGroup(errorsForm);

        mContext = this;
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            actualWineForm.setActualVariety(savedInstanceState.getString(FORM_ACTUAL_VARIETY));
            actualWineForm.setActualCountry(savedInstanceState.getString(FORM_ACTUAL_COUNTRY));
            actualWineForm.setActualRegion(savedInstanceState.getString(FORM_ACTUAL_REGION));
            actualWineForm.setActualQuality(savedInstanceState.getString(FORM_ACTUAL_QUALITY));
            actualWineForm.setActualVintage(Integer.toString(savedInstanceState.getInt(FORM_ACTUAL_VINTAGE)));
        }

        Intent parentIntent = getIntent();

        if (parentIntent != null && parentIntent.hasExtra(APP_VARIETY_GUESS_ID)) {
            boolean mIsRedWine = parentIntent.hasExtra(IS_RED_WINE);

            Bundle bundle = parentIntent.getExtras();
            if (bundle != null) {

                actualWineForm.setAppVarietyById(mIsRedWine, bundle.getString(APP_VARIETY_GUESS_ID));

                actualWineForm.setUserVariety(bundle.getString(USER_CONCLUSION_VARIETY));
                actualWineForm.setUserCountry(bundle.getString(USER_CONCLUSION_COUNTRY));
                actualWineForm.setUserRegion(bundle.getString(USER_CONCLUSION_REGION));
                actualWineForm.setUserQuality(bundle.getString(USER_CONCLUSION_QUALITY));
                actualWineForm.setUserVintage(Integer.toString(bundle.getInt(USER_CONCLUSION_VINTAGE)));
            }
        }

        mAuth = FirebaseAuth.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        mDbReferenceUsers = database.getReference(DB_REFERENCE_USERS);
        mDbReferenceUserConclusions = database.getReference(DB_REFERENCE_CONCLUSIONS);

        List<String> varieties = new ArrayList<>(parseResourceArray(R.array.all_varieties));
        List<String> countries = new ArrayList<>(parseResourceArray(R.array.all_countries));
        List<String> regions = new ArrayList<>(parseResourceArray(R.array.all_regions));

        mSingleViewActualVariety.setAdapter(new ArrayAdapter<>(mContext,
                android.R.layout.simple_dropdown_item_1line, varieties));

        mSingleViewActualCountry.setAdapter(new ArrayAdapter<>(mContext,
                android.R.layout.simple_dropdown_item_1line, countries));

        mSingleViewActualRegion.setAdapter(new ArrayAdapter<>(mContext,
                android.R.layout.simple_dropdown_item_1line, regions));

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    @Override
    public void onPause() {
        super.onPause();

        mActualVariety = actualWineForm.getActualVariety().getValue();
        mActualCountry = actualWineForm.getActualCountry().getValue();
        mActualRegion = actualWineForm.getActualRegion().getValue();
        mActualQuality = actualWineForm.getActualQuality().getValue();
        mActualVintage = actualWineForm.getActualVintage().getValue();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

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
                mButtonWineResultSave.setText(getString(R.string.save_result));
            } else {
                mButtonWineResultSave.setText(getString(R.string.log_in_for_result_save));
            }
        };

        mAuth.addAuthStateListener(mAuthListener);

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();

        mAuth.removeAuthStateListener(mAuthListener);
    }

    public void onButtonWineResultSave(View view) {
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            String uid = user.getUid();

            DatabaseReference newConclusionRecordReference
                    = mDbReferenceUserConclusions.child(uid).push();
            String conclusionReferenceKey = newConclusionRecordReference.getKey();
            if (conclusionReferenceKey != null) {
                mDbReferenceUsers.child(uid).child(DB_REFERENCE_USER_CONCLUSIONS)
                        .child(conclusionReferenceKey).setValue(true);
            }

            String actualVariety = actualWineForm.getActualVariety().getValue();
            String actualCountry = actualWineForm.getActualCountry().getValue();
            String actualRegion = actualWineForm.getActualRegion().getValue();
            String actualQuality = actualWineForm.getActualQuality().getValue();
            String parseInt = mSingleViewActualVintage.getText().toString();
            Integer actualVintage = Integer.parseInt(parseInt);

            String userVariety = actualWineForm.getUserVariety().getValue();
            String userCountry = actualWineForm.getUserCountry().getValue();
            String userRegion = actualWineForm.getUserRegion().getValue();
            String userQuality = actualWineForm.getUserQuality().getValue();
            parseInt = actualWineForm.getUserVintage().getValue();
            Integer userVintage = Integer.parseInt(parseInt);

            String appVariety = actualWineForm.getAppVariety().getValue();

            ConclusionRecord conclusionRecord = new ConclusionRecord();
            conclusionRecord.setAppConclusionVariety(appVariety);
            conclusionRecord.setActualVariety(actualVariety);
            conclusionRecord.setActualCountry(actualCountry);
            conclusionRecord.setActualRegion(actualRegion);
            conclusionRecord.setActualQuality(actualQuality);
            conclusionRecord.setActualVintage(actualVintage);

            conclusionRecord.setUserConclusionVariety(userVariety);
            conclusionRecord.setUserConclusionCountry(userCountry);
            conclusionRecord.setUserConclusionRegion(userRegion);
            conclusionRecord.setUserConclusionQuality(userQuality);
            conclusionRecord.setUserConclusionVintage(userVintage);

            newConclusionRecordReference.setValue(conclusionRecord);

            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);

        } else {
            List<AuthUI.IdpConfig> providers = Arrays.asList(
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
}

