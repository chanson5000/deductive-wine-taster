package com.wineguesser.deductive.ui;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wineguesser.deductive.R;
import com.wineguesser.deductive.databinding.ActivityActualWineBinding;
import com.wineguesser.deductive.model.ErrorsFinalForm;
import com.wineguesser.deductive.repository.DatabaseContract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VarietyResultsActivity extends AppCompatActivity implements DatabaseContract {

    private Context mContext;

    private static DatabaseReference mDbReferenceUsers;
    private static DatabaseReference mDbReferenceUserConclusions;

    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.textView_our_guess)
    TextView mTextViewAppConclusion;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.textView_user_guess)
    TextView mTextViewUserConclusion;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.autoText_final_grape_variety)
    AutoCompleteTextView mSingleViewActualVariety;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.autoText_final_country)
    AutoCompleteTextView mSingleViewActualCountry;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.autoText_final_region)
    AutoCompleteTextView mSingleViewActualRegion;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.autoText_final_quality)
    AutoCompleteTextView mSingleViewActualQuality;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.autoText_final_vintage)
    AutoCompleteTextView mSingleViewActualVintage;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.wine_result_save)
    Button mButtonWineResultSave;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private InterstitialAd mInterstitialAd;
    private ErrorsFinalForm errorsForm = new ErrorsFinalForm();

    private String mAppGrapeConclusionId;
    private String mUserConclusionGrape;
    private String mAppGrapeConclusionString;
    private String mUserConclusionOrigin;
    private String mUserConclusionRegion;
    private String mUserConclusionQuality;
    private Integer mUserConclusionVintage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityActualWineBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_actual_wine);

        errorsForm = new ErrorsFinalForm();
        binding.setErrorGroup(errorsForm);

        mContext = this;
        ButterKnife.bind(this);
        Intent parentIntent = getIntent();

        if (parentIntent != null && parentIntent.hasExtra(APP_VARIETY_GUESS_ID)) {
            boolean mIsRedWine = parentIntent.hasExtra(IS_RED_WINE);

            Bundle bundle = parentIntent.getExtras();
            if (bundle != null) {
                mAppGrapeConclusionId = bundle.getString(APP_VARIETY_GUESS_ID);
                mUserConclusionGrape = bundle.getString(USER_CONCLUSION_GRAPE);
                mUserConclusionOrigin = bundle.getString(USER_CONCLUSION_ORIGIN);
                mUserConclusionRegion = bundle.getString(USER_CONCLUSION_REGION);
                mUserConclusionQuality = bundle.getString(USER_CONCLUSION_QUALITY);
                mUserConclusionVintage = bundle.getInt(USER_CONCLUSION_VINTAGE);

                mTextViewUserConclusion.setText(mUserConclusionGrape);
            }

            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Object dataObject =
                            dataSnapshot.child(mAppGrapeConclusionId).child("variety").getValue();

                    if (dataObject != null) {
                        mAppGrapeConclusionString = dataObject.toString();
                        mTextViewAppConclusion.setText(mAppGrapeConclusionString);
                    } else {
                        Toast.makeText(mContext, "Unable to retrieve varietal name.",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(mContext, "Unable to retrieve varietal name.",
                            Toast.LENGTH_SHORT).show();
                }
            };

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference;
            if (mIsRedWine) {
                databaseReference = database.getReference(DB_RED_INFO_PATH);
            } else {
                databaseReference = database.getReference(DB_WHITE_INFO_PATH);
            }
            databaseReference.addListenerForSingleValueEvent(listener);
        }

        mAuth = FirebaseAuth.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        mDbReferenceUsers = database.getReference(DB_REFERENCE_USERS);
        mDbReferenceUserConclusions = database.getReference(DB_REFERENCE_ALL_CONCLUSIONS);

        List<String> varieties = new ArrayList<>(parseResourceArray(R.array.all_varieties));
        List<String> countires = new ArrayList<>(parseResourceArray(R.array.all_countries));
        List<String> regions = new ArrayList<>(parseResourceArray(R.array.all_regions));

        mSingleViewActualVariety.setAdapter(new ArrayAdapter<>(mContext,
                android.R.layout.simple_dropdown_item_1line, varieties));



        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
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

            DatabaseReference newGuessReference = mDbReferenceUserConclusions.child(uid).push();
            String guessReferenceKey = newGuessReference.getKey();
            if (guessReferenceKey != null) {
                mDbReferenceUsers.child(uid).child(DB_USER_CONCLUSIONS)
                        .child(guessReferenceKey).setValue(true);
            }
            newGuessReference.child(DB_APP_CONCLUSION).setValue(mAppGrapeConclusionString);
            String actualWine = mSingleViewActualVariety.getText().toString();
            String actualCountry = mSingleViewActualCountry.getText().toString();
            String actualRegion = mSingleViewActualRegion.getText().toString();
            String actualQuality = mSingleViewActualQuality.getText().toString();
            String parseInteger = mSingleViewActualVintage.getText().toString();
            Integer actualVintage = null;
            if (!parseInteger.isEmpty()) {
                actualVintage = Integer.parseInt(parseInteger);
            }

            if (!actualWine.isEmpty()) {
                newGuessReference.child(DB_ACTUAL_GRAPE).setValue(actualWine);
            }
            if (!actualCountry.isEmpty()) {
                newGuessReference.child(DB_ACTUAL_COUNTRY).setValue(actualCountry);
            }
            if (!actualRegion.isEmpty()) {
                newGuessReference.child(DB_ACTUAL_REGION).setValue(actualRegion);
            }
            if (!actualQuality.isEmpty()) {
                newGuessReference.child(DB_ACTUAL_QUALITY).setValue(actualQuality);
            }
            if (actualVintage != null) {
                newGuessReference.child(DB_ACTUAL_VINTAGE).setValue(actualVintage);
            }

            newGuessReference.child(DB_USER_CONCLUSION_GRAPE).setValue(mUserConclusionGrape);
            newGuessReference.child(DB_USER_CONCLUSION_COUNTRY).setValue(mUserConclusionOrigin);
            newGuessReference.child(DB_USER_CONCLUSION_REGION).setValue(mUserConclusionRegion);
            newGuessReference.child(DB_USER_CONCLUSION_QUALITY).setValue(mUserConclusionQuality);
            newGuessReference.child(DB_USER_CONCLUSION_VINTAGE).setValue(mUserConclusionVintage);

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

