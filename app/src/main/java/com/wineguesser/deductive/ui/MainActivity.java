package com.wineguesser.deductive.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity implements DeductionFormContract,
        RepoKeyContract {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private int RC_SIGN_IN = 42;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mReferenceUsers;

    @BindView(R.id.textView_blind_taste)
    TextView mTextViewBlindTaste;
    @BindView(R.id.button_log_in)
    Button mButtonLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mReferenceUsers = FirebaseDatabase.getInstance().getReference("users");

        Timber.e("TESTING IF THIS WORKS.");
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                mButtonLogIn.setText(getString(R.string.log_out));
                String name = user.getDisplayName();
                String email = user.getEmail();
                Uri photoUrl = user.getPhotoUrl();
                String uid = user.getUid();

                if (name == null) {
                    mTextViewBlindTaste.setText(getString(R.string.welcome_tag, email));
                } else {
                    mTextViewBlindTaste.setText(getString(R.string.welcome_tag, name));
                }

                boolean emailVerified = user.isEmailVerified();

                if (!emailVerified) {
                    ValueEventListener listener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(uid).child("verificationEmailSent")
                                    .getValue() != (Boolean) true) {
                                user.sendEmailVerification().addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        Timber.d("Email verification for user sent.");
                                        mReferenceUsers.child(uid).child("verificationEmailSent")
                                                .setValue(true);
                                    } else {
                                        Timber.d("Sending of email verification failed");
                                    }
                                });
                            } else {
                                Timber.d("Skipped sending user verification e-mail");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Timber.e(databaseError.toString());
                        }
                    };
                    mReferenceUsers.addListenerForSingleValueEvent(listener);
                }
            } else {
                mButtonLogIn.setText(getString(R.string.log_in));
            }
        };

        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();

        mAuth.removeAuthStateListener(mAuthListener);
    }

    public void buttonRedWine(View view) {
        Intent intent = new Intent(this, DeductionFormActivity.class);
        intent.putExtra(IS_RED_WINE, RED_WINE);
        startActivity(intent);
    }

    public void buttonWhiteWine(View view) {
        Intent intent = new Intent(this, DeductionFormActivity.class);
        startActivity(intent);
    }

    public void onLogInButtonClicked(View view) {
        FirebaseUser user = mAuth.getCurrentUser();

        if (user == null) {
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build()
            );

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN);
        } else {
            mAuth.signOut();
        }
    }

    public void signOutCurrentFirebaseUser() {
        AuthUI.getInstance().signOut(this).addOnCompleteListener(task -> {
            // Do something when sign out completed.
        });
    }
}
