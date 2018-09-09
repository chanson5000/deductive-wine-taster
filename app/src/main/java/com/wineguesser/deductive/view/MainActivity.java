package com.wineguesser.deductive.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wineguesser.deductive.R;
import com.wineguesser.deductive.repository.DatabaseContract;
import com.wineguesser.deductive.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements DeductionFormContract,
        DatabaseContract {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
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
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ButterKnife.bind(this);
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
                setUserLoggedIn(user);
                UserRepository userRepository = new UserRepository();
                userRepository.checkEmailVerification(user);
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
