package com.wineguesser.deductive.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
import com.wineguesser.deductive.repository.DatabaseContract;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements DeductionFormContract,
        DatabaseContract {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mReferenceUsers;
    private Context mContext;
    private boolean mUserLoggedIn;

    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.textView_blind_taste)
    TextView mTextViewBlindTaste;

    private MenuItem mMenuAuthToggle;
    private MenuItem mMenuProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mReferenceUsers = FirebaseDatabase.getInstance().getReference(DB_REFERENCE_USERS);
        mContext = this;
    }

    @Override
    public void onStart() {
        super.onStart();

        // Set a listener for authentications so we may set states.
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();

            if (user != null) {
                setUserLoggedIn(user);
                checkEmailVerification(user);
            } else {
                setUserLoggedOut();
            }
        };

        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();

        mAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        mMenuAuthToggle = menu.findItem(R.id.auth_toggle);
        mMenuProfile = menu.findItem(R.id.profile_settings);
        if (mUserLoggedIn) {
            setMenuLoggedIn();
        } else {
            setMenuLoggedOut();
        }
        return true;
    }

    private void setMenuLoggedIn() {
        if (mMenuAuthToggle != null) {
            mMenuAuthToggle.setTitle(R.string.log_out);
            mMenuAuthToggle.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
            mMenuProfile.setVisible(true);
        }
    }

    private void setMenuLoggedOut() {
        if (mMenuAuthToggle != null) {
            mMenuAuthToggle.setTitle(R.string.log_in);
            mMenuAuthToggle.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            mMenuProfile.setVisible(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.auth_toggle:
                if (mUserLoggedIn) {
                    signOutCurrentFirebaseUser();
                } else {
                    startLoginUI();
                }
                return true;
            case R.id.profile_settings:
                Intent intent = new Intent(mContext, UserProfileActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void buttonRedWine(View view) {
        Intent intent = new Intent(mContext, DeductionFormActivity.class);
        intent.putExtra(IS_RED_WINE, RED_WINE);
        startActivity(intent);
    }

    public void buttonWhiteWine(View view) {
        Intent intent = new Intent(mContext, DeductionFormActivity.class);
        startActivity(intent);
    }

    private void checkEmailVerification(FirebaseUser user) {
        boolean emailVerified = user.isEmailVerified();

        if (!emailVerified) {
            String uid = user.getUid();

            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(uid).child(DB_EMAIL_VERIFICATION)
                            .getValue() != (Boolean) true) {

                        user.sendEmailVerification().addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Timber.d("Email verification for user sent.");
                                // Set that we have requested e-mail verification so we don't
                                // spam the user.
                                mReferenceUsers.child(uid).child(DB_EMAIL_VERIFICATION)
                                        .setValue(true);
                            } else {
                                Timber.d("Sending of email verification failed.");
                            }
                        });
                    } else {
                        Timber.d("Skipped sending user verification e-mail.");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Timber.e("Error checking for email verification: %s", databaseError.toString());
                }
            };

            mReferenceUsers.addListenerForSingleValueEvent(listener);
        }
    }

    private void setUserLoggedIn(FirebaseUser user) {
        mUserLoggedIn = true;

        String name = user.getDisplayName();
        String email = user.getEmail();
        if (name == null) {
            mTextViewBlindTaste.setText(getString(R.string.welcome_tag, email));
        } else {
            mTextViewBlindTaste.setText(getString(R.string.welcome_tag, name));
        }
        setMenuLoggedIn();
    }

    private void setUserLoggedOut() {
        mUserLoggedIn = false;
        mTextViewBlindTaste.setText(R.string.log_in);
        setMenuLoggedOut();
    }

    private void startLoginUI() {
        FirebaseUser user = mAuth.getCurrentUser();
        // Just any number required to identify if we were using a call back.
        // Using a listener instead.
        int RC_SIGN_IN = 42;

        if (user == null) {
            // TODO: Decide if more providers are wanted.
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

    public void onHistoryButtonClicked(View view) {
        Intent intent = new Intent(mContext, HistoryActivity.class);
        startActivity(intent);
    }

    private void signOutCurrentFirebaseUser() {
        AuthUI.getInstance().signOut(mContext).addOnCompleteListener(task ->
                setUserLoggedOut());
    }
}
