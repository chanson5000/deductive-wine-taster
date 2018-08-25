package com.wineguesser.deductive.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wineguesser.deductive.R;
import com.wineguesser.deductive.repository.RepoKeyContract;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class VarietyResultsActivity extends AppCompatActivity implements RepoKeyContract {

    private Context mContext;

    private int RC_SIGN_IN = 43;

    private static DatabaseReference mDbReferenceUsers;
    private static DatabaseReference mDbReferenceUserConclusions;

    @BindView(R.id.textView_our_guess)
    TextView mTextViewAppConclusion;
    @BindView(R.id.textView_user_guess)
    TextView mTextViewUserConclusion;
    @BindView(R.id.autoText_final_grape_variety)
    AutoCompleteTextView mSingleViewActualVariety;
    @BindView(R.id.autoText_final_country)
    AutoCompleteTextView mSingleViewActualCountry;
    @BindView(R.id.autoText_final_region)
    AutoCompleteTextView mSingleViewActualRegion;
    @BindView(R.id.autoText_final_quality)
    AutoCompleteTextView mSingleViewActualQuality;
    @BindView(R.id.autoText_final_vintage)
    AutoCompleteTextView mSingleViewActualVintage;
    @BindView(R.id.wine_result_save)
    Button mButtonWineResultSave;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private String mAppGrapeConclusionId;
    private String mUserConclusionGrape;
    private String mAppGrapeConclusionString;
    private String mUserConclusionOrigin;
    private String mUserConclusionRegion;
    private String mUserConclusionQuality;
    private Integer mUserConclusionVintage;

    private boolean mIsRedWine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_wine);

        mContext = this;
        ButterKnife.bind(this);
        Intent parentIntent = getIntent();

        if (parentIntent != null && parentIntent.hasExtra(APP_VARIETY_GUESS_ID)) {
            mIsRedWine = parentIntent.hasExtra(IS_RED_WINE);

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

        } else {
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build()
            );

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN);
        }
    }
}

