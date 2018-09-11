package com.wineguesser.deductive.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wineguesser.deductive.R;
import com.wineguesser.deductive.repository.DatabaseContract;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        DeductionFormContract, DatabaseContract {

    private Context mContext;
    private boolean mUserLoggedIn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private MenuItem mMenuAuthToggle;
    private MenuItem mMenuProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        mAuth = FirebaseAuth.getInstance();
        mContext = this;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Set a listener for authentications so we may set states.
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                setUserLoggedIn(true);
            } else {
                setUserLoggedIn(false);
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
            setMenuLoggedIn(true);
        } else {
            setMenuLoggedIn(false);
        }
        return true;
    }

    private void setMenuLoggedIn(boolean loggedIn) {
        if (loggedIn && mMenuAuthToggle != null) {
            mMenuAuthToggle.setTitle(R.string.log_out);
            mMenuAuthToggle.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
            mMenuProfile.setVisible(true);
        } else if (!loggedIn && mMenuAuthToggle != null) {
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
        intent.putExtra(IS_RED_WINE, TRUE);
        startActivity(intent);
    }

    public void buttonWhiteWine(View view) {
        Intent intent = new Intent(mContext, DeductionFormActivity.class);
        startActivity(intent);
    }

    private void setUserLoggedIn(boolean loggedIn) {
        if (loggedIn) {
            mUserLoggedIn = true;
            setMenuLoggedIn(true);
        } else {
            mUserLoggedIn = false;
            setMenuLoggedIn(false);
        }
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

    public class SnackbarLogInListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            startLoginUI();
        }
    }

    public void onHistoryButtonClicked(View view) {
        if (mUserLoggedIn) {
            Intent intent = new Intent(mContext, HistoryActivity.class);
            startActivity(intent);
        } else {
            Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator_main),
                    R.string.snack_log_in, Snackbar.LENGTH_LONG);
            snackbar.setAction(R.string.log_in, new SnackbarLogInListener());
            snackbar.show();
        }
    }

    private void signOutCurrentFirebaseUser() {
        AuthUI.getInstance().signOut(mContext).addOnCompleteListener(task ->
                setUserLoggedIn(false));
    }
}
