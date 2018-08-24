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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wineguesser.deductive.R;
import com.wineguesser.deductive.repository.RepoKeyContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class VarietyResultsActivity extends AppCompatActivity implements RepoKeyContract {

    private Context mContext;

    private static DatabaseReference mDbReferenceUsers;
    private static DatabaseReference mDbReferenceUserGuesses;

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

    private String mWinningWineId;
    private String mUserGuessedWine;
    private String mWinningWineString;
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
                mWinningWineId = bundle.getString(APP_VARIETY_GUESS_ID);
                mUserGuessedWine = bundle.getString(USER_GUESSED_WINE);
                mTextViewUserConclusion.setText(mUserGuessedWine);
            }

            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Object dataObject =
                            dataSnapshot.child(mWinningWineId).child("variety").getValue();

                    if (dataObject != null) {
                        mWinningWineString = dataObject.toString();
                        mTextViewAppConclusion.setText(mWinningWineString);
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

        mDbReferenceUsers = database.getReference("users");
        mDbReferenceUserGuesses = database.getReference("userGuesses");
    }

    @Override
    public void onStart() {
        super.onStart();

        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                mButtonWineResultSave.setText(getString(R.string.save_result));
                String name = user.getDisplayName();
                String email = user.getEmail();
                Uri photoUrl = user.getPhotoUrl();
                String uid = user.getUid();

                boolean emailVerified = user.isEmailVerified();

            } else {
                mButtonWineResultSave.setText(getString(R.string.log_in_for_result_save));
            }
        };

        mAuth.addAuthStateListener(mAuthListener);
    }

    public void onButtonWineResultSave(View view) {
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            String uid = user.getUid();

            DatabaseReference newGuessReference = mDbReferenceUserGuesses.child(uid).push();
            String guessReferenceKey = newGuessReference.getKey();
            if (guessReferenceKey != null) {
                mDbReferenceUsers.child(uid).child("guesses").child(guessReferenceKey).setValue(true);
            }
            newGuessReference.child("app_guess").setValue(mWinningWineString);
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
                newGuessReference.child("actual_wine").setValue(actualWine);
            }
            if (!actualCountry.isEmpty()) {
                newGuessReference.child("actual_country").setValue(actualCountry);
            }
            if (!actualRegion.isEmpty()) {
                newGuessReference.child("actual_region").setValue(actualRegion);
            }
            if (!actualQuality.isEmpty()) {
                newGuessReference.child("actual_quality").setValue(actualQuality);
            }
            if (actualVintage != null) {
                newGuessReference.child("actual_vintage").setValue(actualVintage);
            }

            newGuessReference.child("user_guess").setValue(mUserGuessedWine);
        } else {
            Timber.d("The wine saving didn't work!");
        }
    }
}
