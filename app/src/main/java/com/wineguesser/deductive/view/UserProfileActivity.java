package com.wineguesser.deductive.view;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.wineguesser.deductive.R;
import com.wineguesser.deductive.databinding.ActivityUserProfileBinding;
import com.wineguesser.deductive.repository.DatabaseContract;
import com.wineguesser.deductive.repository.UserRepository;
import com.wineguesser.deductive.util.Helpers;
import com.wineguesser.deductive.viewmodel.UserProfileViewModel;

import java.util.Collections;
import java.util.List;

import timber.log.Timber;

public class UserProfileActivity extends AppCompatActivity implements DatabaseContract {

    private static final String NEW_DISPLAY_NAME = "NEW_DISPLAY_NAME";
    private static final String NEW_EMAIL_ADDRESS = "NEW_EMAIL_ADDRESS";
    private static final String NEW_CONFIRM_EMAIL_ADDRESS = "NEW_CONFIRM_EMAIL_ADDRESS";
    private static final String NEW_PASSWORD = "NEW_PASSWORD";
    private static final String NEW_CONFIRM_PASSWORD = "NEW_CONFIRM_PASSWORD";

    private Context mContext;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    // Representing that the user has changed fields.
    private String mNewTextDisplayName;
    private String mNewTextEmailAddress;
    private String mNewTextConfirmEmailAddress;
    private String mNewPassword;
    private String mNewConfirmPassword;

    private UserProfileViewModel userProfileModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        ActivityUserProfileBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_user_profile);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(insets.left, insets.top, insets.right, insets.bottom);
            return windowInsets;
        });

        setTitle(R.string.user_profile_activity_title);

        userProfileModel = new ViewModelProvider(this).get(UserProfileViewModel.class);
        binding.setLifecycleOwner((LifecycleOwner) this);
        binding.setUserProfileForm(userProfileModel);

        if (savedInstanceState != null) {
            mNewTextDisplayName = savedInstanceState.getString(NEW_DISPLAY_NAME);
            mNewTextEmailAddress = savedInstanceState.getString(NEW_EMAIL_ADDRESS);
            mNewTextConfirmEmailAddress = savedInstanceState.getString(NEW_CONFIRM_EMAIL_ADDRESS);
            mNewPassword = savedInstanceState.getString(NEW_PASSWORD);
            mNewConfirmPassword = savedInstanceState.getString(NEW_CONFIRM_PASSWORD);
        }

        mContext = this;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Set a listener for authentications so we may set states.
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();

            // Activity requires a logged in user.
            // If not logged in, send to log in UI.
            if (user != null) {
                setUserLoggedIn(user);
                checkEmailVerification(user);
            } else {
                startLoginUI();
            }
        };

        mAuth.addAuthStateListener(mAuthListener);
    }

    private void setUserLoggedIn(FirebaseUser user) {
        String name = user.getDisplayName();
        String email = user.getEmail();

        userProfileModel.setUserName(name);

        if (mNewTextDisplayName == null) {
            userProfileModel.setDisplayName(name);
        }

        if (mNewTextEmailAddress == null) {
            userProfileModel.setEmailAddress(email);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUiState();
    }

    private void loadUiState() {
        // Populating the UI with saved form values.
        if (mNewTextDisplayName != null) {
            userProfileModel.setDisplayName(mNewTextDisplayName);
        }
        if (mNewTextEmailAddress != null) {
            userProfileModel.setEmailAddress(mNewTextEmailAddress);
        }
        if (mNewTextConfirmEmailAddress != null) {
            userProfileModel.setConfirmEmailAddress(mNewTextConfirmEmailAddress);
        }
        if (mNewPassword != null) {
            userProfileModel.setPassword(mNewPassword);
        }
        if (mNewConfirmPassword != null) {
            userProfileModel.setConfirmPassword(mNewConfirmPassword);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveUiState();
    }

    private void saveUiState() {
        mNewTextDisplayName = userProfileModel.getDisplayName().getValue();
        mNewTextEmailAddress = userProfileModel.getEmailAddress().getValue();
        mNewTextConfirmEmailAddress = userProfileModel.getConfirmEmailAddress().getValue();
        mNewPassword = userProfileModel.getPassword().getValue();
        mNewConfirmPassword = userProfileModel.getConfirmPassword().getValue();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // These need to be saved to savedInstanceState on occasion.
        savedInstanceState.putString(NEW_DISPLAY_NAME, mNewTextDisplayName);
        savedInstanceState.putString(NEW_EMAIL_ADDRESS, mNewTextEmailAddress);
        savedInstanceState.putString(NEW_CONFIRM_EMAIL_ADDRESS, mNewTextConfirmEmailAddress);
        savedInstanceState.putString(NEW_PASSWORD, mNewPassword);
        savedInstanceState.putString(NEW_CONFIRM_PASSWORD, mNewConfirmPassword);
    }

    private void startLoginUI() {
        FirebaseUser user = mAuth.getCurrentUser();
        // Just any number required to identify if we were using a call back.
        // Using a listener instead.
        int RC_SIGN_IN = 42;

        if (user == null) {
            // TODO: Decide if more providers are wanted.
            List<AuthUI.IdpConfig> providers = Collections.singletonList(
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

    public void onClickUpdateDetails(View view) {
        FirebaseUser user = mAuth.getCurrentUser();

        boolean updateDisplayName = false;
        boolean updateEmailAddress = false;

        String errorDisplayName = null;
        String errorEmailAddress = null;
        String errorConfirmEmailAddress = null;

        String newDisplayName = userProfileModel.getDisplayName().getValue();
        String newEmailAddress = userProfileModel.getEmailAddress().getValue();
        String newConfirmEmailAddress = userProfileModel.getConfirmEmailAddress().getValue();

        if (user != null) {
            if (newDisplayName != null && !newDisplayName.equals(user.getDisplayName())) {
                if (newDisplayName.isEmpty()) {
                    errorDisplayName = getString(R.string.up_error_display_name_field_empty);
                } else {
                    errorDisplayName = null;
                    updateDisplayName = true;
                }
            }

            if (newEmailAddress != null && !newEmailAddress.equals(user.getEmail())) {
                if (newEmailAddress.isEmpty()) {
                    errorEmailAddress = getString(R.string.up_error_email_field_empty);
                } else if (newConfirmEmailAddress != null && newConfirmEmailAddress.isEmpty()) {
                    errorConfirmEmailAddress = getString(R.string.up_error_confirm_email_field_empty);
                } else if (!newEmailAddress.equals(newConfirmEmailAddress)) {
                    errorEmailAddress = getString(R.string.up_error_email_fields_must_match);
                } else {
                    errorEmailAddress = null;
                    errorConfirmEmailAddress = null;
                    updateEmailAddress = true;
                }
            }

            if (updateDisplayName) {
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(newDisplayName).build();

                user.updateProfile(profileUpdates).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Timber.d("Successfully updated user display name.");
                        userProfileModel.setUserName(newDisplayName);
                        userProfileModel.setDisplayName(newDisplayName);
                        resetErrorDisplayName();
                        Helpers.makeToastShort(mContext, R.string.up_toast_updated_display_name);
                    }
                });
            } else if (errorDisplayName != null) {
                setErrorDisplayName(errorDisplayName);
            } else {
                resetErrorDisplayName();
            }

            if (updateEmailAddress) {
                user.updateEmail(newEmailAddress).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Timber.d("Successfully updated user email address.");
                        userProfileModel.setEmailAddress(newEmailAddress);
                        userProfileModel.setConfirmEmailAddress(null);
                        resetAllErrorEmailAddress();
                        Helpers.makeToastShort(mContext, R.string.up_toast_updated_email);
                    } else if (task.getException() instanceof FirebaseAuthRecentLoginRequiredException) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle(R.string.up_re_authenticate);
                        final EditText password = new EditText(this);
                        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        builder.setView(password);
                        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
                            String email = user.getEmail();
                            if (email != null) {
                                AuthCredential credential = EmailAuthProvider
                                        .getCredential(user.getEmail(), password.getText().toString());

                                user.reauthenticate(credential).addOnCompleteListener(reAuthTask ->
                                        user.updateEmail(newEmailAddress).addOnCompleteListener(updateEmailTask -> {
                                            Timber.d("Successfully updated user email address.");
                                            userProfileModel.setEmailAddress(newEmailAddress);
                                            userProfileModel.setConfirmEmailAddress(null);
                                            resetAllErrorEmailAddress();
                                            Helpers.makeToastShort(mContext, R.string.up_toast_updated_email);
                                        }));
                            }
                        });
                        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.cancel());
                        builder.show();
                    } else {
                        if (task.getException() != null) {
                            Timber.e("Error updating email address: %s", task.getException().toString());
                        } else if (task.getResult() != null) {
                            Timber.e("Error updating email address: %s", task.getResult().toString());
                        } else {
                            Timber.e("Unknown error while updating email address.");
                        }
                        setErrorEmailAddress(getString(R.string.up_unable_to_update_email));
                    }
                });
            } else if (errorEmailAddress != null) {
                setErrorEmailAddress(errorEmailAddress);
            } else if (errorConfirmEmailAddress != null) {
                setErrorConfirmEmailAddress(errorConfirmEmailAddress);
            } else {
                resetAllErrorEmailAddress();
            }
        } else {
            startLoginUI();
        }
    }

    public void onClickUpdatePassword(View view) {
        FirebaseUser user = mAuth.getCurrentUser();

        boolean updatePassword = false;
        String errorPassword = null;
        String errorConfirmPassword = null;

        String newPassword = userProfileModel.getPassword().getValue();
        String newConfirmPassword = userProfileModel.getConfirmPassword().getValue();

        if (user != null) {
            if (newPassword != null && !newPassword.isEmpty()) {
                if (newConfirmPassword != null && newConfirmPassword.isEmpty()) {
                    errorPassword = getString(R.string.up_error_password_empty);
                } else if (!newPassword.equals(newConfirmPassword)) {
                    errorConfirmPassword = getString(R.string.up_error_passwords_must_match);
                } else {
                    updatePassword = true;
                }
            }

            if (updatePassword) {
                user.updatePassword(newPassword).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Timber.d("Successfully updated user password.");
                        resetAllPasswordFields();
                        resetAllErrorPassword();
                        Helpers.makeToastShort(mContext, R.string.up_toast_updated_password);
                    } else if (task.getException() instanceof FirebaseAuthRecentLoginRequiredException) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle(R.string.up_re_authenticate);
                        final EditText password = new EditText(this);
                        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        builder.setView(password);
                        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
                            String email = user.getEmail();
                            if (email != null) {
                                AuthCredential credential = EmailAuthProvider
                                        .getCredential(user.getEmail(), password.getText().toString());

                                user.reauthenticate(credential).addOnCompleteListener(reAuthTask ->
                                        user.updatePassword(newPassword)).addOnCompleteListener(updateEmailTask -> {
                                    Timber.d("Successfully updated user password.");
                                    resetAllPasswordFields();
                                    resetAllErrorPassword();
                                    Helpers.makeToastShort(mContext, R.string.up_toast_updated_password);
                                });
                            }
                        });
                        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.cancel());
                        builder.show();
                    } else if (task.getException() instanceof FirebaseAuthWeakPasswordException) {
                        setErrorNewPassword(getString(R.string.up_weak_password));
                    } else {
                        if (task.getException() != null) {
                            Timber.e("Error updating password: %s", task.getException().toString());
                        } else if (task.getResult() != null) {
                            Timber.e("Error updating password: %s", task.getResult().toString());
                        } else {
                            Timber.e("Unknown error while updating password.");
                        }
                        setErrorNewPassword(getString(R.string.up_unable_to_update_password));
                    }
                });

            } else if (errorPassword != null) {
                setErrorNewPassword(errorPassword);
            } else if (errorConfirmPassword != null) {
                setErrorConfirmPassword(errorConfirmPassword);
            } else {
                resetAllErrorPassword();
            }

        } else {
            startLoginUI();
        }
    }

    public void onClickDeleteAccount(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.up_confirm_account_deletion);
        builder.setPositiveButton(android.R.string.yes, (dialog, which) -> {
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null) {
                user.delete();
                Helpers.makeToastShort(mContext, R.string.up_account_deleted);
            } else {
                Helpers.makeToastShort(mContext, R.string.up_account_deletion_failed);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void setErrorEmailAddress(String error) {
        resetErrorConfirmEmailAddress();
        userProfileModel.setErrorEmailAddress(error);
    }

    private void resetErrorEmailAddress() {
        userProfileModel.setErrorEmailAddress(null);
    }

    private void setErrorConfirmEmailAddress(String error) {
        resetErrorEmailAddress();
        userProfileModel.setErrorConfirmEmailAddress(error);
    }

    private void resetErrorConfirmEmailAddress() {
        userProfileModel.setErrorConfirmEmailAddress(null);
    }

    private void resetAllErrorEmailAddress() {
        resetErrorEmailAddress();
        resetErrorConfirmEmailAddress();
    }

    private void setErrorDisplayName(String error) {
        userProfileModel.setErrorDisplayName(error);
    }

    private void resetErrorDisplayName() {
        userProfileModel.setErrorDisplayName(null);
    }

    private void resetErrorNewPassword() {
        userProfileModel.setErrorPassword(null);
    }

    private void resetErrorConfirmPassword() {
        userProfileModel.setErrorConfirmPassword(null);
    }

    private void resetAllErrorPassword() {
        resetErrorNewPassword();
        resetErrorConfirmPassword();
    }

    private void setErrorNewPassword(String error) {
        resetErrorConfirmPassword();
        userProfileModel.setErrorPassword(error);
    }

    private void setErrorConfirmPassword(String error) {
        resetErrorNewPassword();
        userProfileModel.setConfirmPassword(error);
    }

    private void resetAllPasswordFields() {
        userProfileModel.setPassword(null);
        userProfileModel.setConfirmPassword(null);
    }

    private void checkEmailVerification(FirebaseUser user) {
        boolean emailVerified = user.isEmailVerified();

        if (!emailVerified) {
            setErrorEmailAddress(getString(R.string.verify_email_request));

            UserRepository repository = new UserRepository();
            repository.checkEmailVerification(user);
        }
    }
}
