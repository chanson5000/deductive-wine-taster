package com.wineguesser.deductive.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wineguesser.deductive.R;
import com.wineguesser.deductive.databinding.ActivityUserProfileBinding;
import com.wineguesser.deductive.model.UserProfileForm;
import com.wineguesser.deductive.repository.DatabaseContract;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class UserProfileActivity extends AppCompatActivity implements DatabaseContract {

    private static final String NEW_DISPLAY_NAME = "NEW_DISPLAY_NAME";
    private static final String NEW_EMAIL_ADDRESS = "NEW_EMAIL_ADDRESS";
    private static final String NEW_CONFIRM_EMAIL_ADDRESS = "NEW_CONFIRM_EMAIL_ADDRESS";
    private static final String NEW_PHOTO_URI = "NEW_PHOTO_URI";
    private static final String NEW_PASSWORD = "NEW_PASSWORD";
    private static final String NEW_CONFIRM_PASSWORD = "NEW_CONFIRM_PASSWORD";

    private Context mContext;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mReferenceUsers;

    // Representing that the user has changed fields.
    private String mNewTextDisplayName;
    private String mNewTextEmailAddress;
    private String mNewTextConfirmEmailAddress;
    private String mNewUriPhotoUri;
    private String mNewPassword;
    private String mNewConfirmPassword;

    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.textView_auth_provider)
    TextView mTextProviderName;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.imageView_profile_photo)
    ImageView mImageProfilePhoto;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.button_delete_photo)
    Button mButtonDeletePhoto;

    UserProfileForm userProfileForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUserProfileBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_user_profile);

        userProfileForm = new UserProfileForm();
        binding.setUserProfileForm(userProfileForm);

        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            mNewTextDisplayName = savedInstanceState.getString(NEW_DISPLAY_NAME);
            mNewTextEmailAddress = savedInstanceState.getString(NEW_EMAIL_ADDRESS);
            mNewTextConfirmEmailAddress = savedInstanceState.getString(NEW_CONFIRM_EMAIL_ADDRESS);
            mNewUriPhotoUri = savedInstanceState.getString(NEW_PHOTO_URI);
            mNewPassword = savedInstanceState.getString(NEW_PASSWORD);
            mNewConfirmPassword = savedInstanceState.getString(NEW_CONFIRM_PASSWORD);
        }

        mContext = this;
        mAuth = FirebaseAuth.getInstance();
        mReferenceUsers = FirebaseDatabase.getInstance().getReference(DB_REFERENCE_USERS);
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
                showProfilePhoto(null);
                startLoginUI();
            }
        };

        mAuth.addAuthStateListener(mAuthListener);
    }

    private void setUserLoggedIn(FirebaseUser user) {
        String provider = user.getProviderId();
        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();

        userProfileForm.setUserName(name);

        // TODO: Decide that you don't need this.
        mTextProviderName.setText(provider);

        if (mNewTextDisplayName == null) {
            userProfileForm.setDisplayName(name);
        }

        if (mNewTextEmailAddress == null) {
            userProfileForm.setEmailAddress(email);
        }

        if (photoUrl != null) {
            if (mNewUriPhotoUri == null) {
                userProfileForm.setPhotoUrl(photoUrl.toString());
            }
            showProfilePhoto(photoUrl);
        } else {
            showProfilePhoto(null);
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
            userProfileForm.setDisplayName(mNewTextDisplayName);
        }
        if (mNewTextEmailAddress != null) {
            userProfileForm.setEmailAddress(mNewTextEmailAddress);
        }
        if (mNewTextConfirmEmailAddress != null) {
            userProfileForm.setConfirmEmailAddress(mNewTextConfirmEmailAddress);
        }
        if (mNewUriPhotoUri != null) {
            userProfileForm.setPhotoUrl(mNewUriPhotoUri);
        }
        if (mNewPassword != null) {
            userProfileForm.setPassword(mNewPassword);
        }
        if (mNewConfirmPassword != null) {
            userProfileForm.setConfirmPassword(mNewConfirmPassword);
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
        mNewTextDisplayName = userProfileForm.getDisplayName();
        mNewTextEmailAddress = userProfileForm.getEmailAddress();
        mNewTextConfirmEmailAddress = userProfileForm.getConfirmEmailAddress();
        mNewUriPhotoUri = userProfileForm.getPhotoUrl();
        mNewPassword = userProfileForm.getPassword();
        mNewConfirmPassword = userProfileForm.getConfirmPassword();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // These need to be saved to savedInstanceState on occasion.
        savedInstanceState.putString(NEW_DISPLAY_NAME, mNewTextDisplayName);
        savedInstanceState.putString(NEW_EMAIL_ADDRESS, mNewTextEmailAddress);
        savedInstanceState.putString(NEW_CONFIRM_EMAIL_ADDRESS, mNewTextConfirmEmailAddress);
        savedInstanceState.putString(NEW_PHOTO_URI, mNewUriPhotoUri);
        savedInstanceState.putString(NEW_PASSWORD, mNewPassword);
        savedInstanceState.putString(NEW_CONFIRM_PASSWORD, mNewConfirmPassword);
    }

    private void showProfilePhoto(Uri photoUrl) {
        if (photoUrl != null) {
            Picasso.get().load(photoUrl).placeholder(R.drawable.ic_placeholder_profile_photo)
                    .into(mImageProfilePhoto, new Callback() {
                        @Override
                        public void onSuccess() {
                            Timber.d("Photo image successfully loaded.");
                            mButtonDeletePhoto.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onError(Exception e) {
                            Timber.d("Error loading profile image.");
                        }
                    });
        } else {
            mImageProfilePhoto.setImageResource(R.drawable.ic_placeholder_profile_photo);
            mButtonDeletePhoto.setVisibility(View.GONE);
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

    public void onClickUpdateDetails(View view) {
        FirebaseUser user = mAuth.getCurrentUser();

        boolean updateDisplayName = false;
        boolean updateEmailAddress = false;

        String errorDisplayName = null;
        String errorEmailAddress = null;
        String errorConfirmEmailAddress = null;

        String newDisplayName = userProfileForm.getDisplayName();
        String newEmailAddress = userProfileForm.getEmailAddress();
        String newConfirmEmailAddress = userProfileForm.getConfirmEmailAddress();

        if (user != null) {
            if (!newDisplayName.equals(user.getDisplayName())) {
                if (newDisplayName.isEmpty()) {
                    errorDisplayName = "Display Name field must not be empty.";
                } else {
                    errorDisplayName = null;
                    updateDisplayName = true;
                }
            }

            if (!newEmailAddress.equals(user.getEmail())) {
                if (newEmailAddress.isEmpty()) {
                    errorEmailAddress = "Email address field must not be empty.";
                } else if (newConfirmEmailAddress.isEmpty()) {
                    errorConfirmEmailAddress = "Confirm Email address field must not be empty.";
                } else if (!newEmailAddress.equals(newConfirmEmailAddress)) {
                    errorEmailAddress = "Email address fields must match.";
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
                        userProfileForm.setUserName(newDisplayName);
                        userProfileForm.setDisplayName(newDisplayName);
                        resetErrorDisplayName();
                        Toast.makeText(mContext,
                                "Updated display name.",
                                Toast.LENGTH_SHORT).show();
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
                        userProfileForm.setEmailAddress(newEmailAddress);
                        userProfileForm.setConfirmEmailAddress("");
                        resetAllErrorEmailAddress();
                        Toast.makeText(mContext,
                                "Updated email address.",
                                Toast.LENGTH_SHORT).show();
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

    public void onClickUpdateProfilePhoto(View view) {
        FirebaseUser user = mAuth.getCurrentUser();

        boolean updatePhotoUrl = false;
        String errorPhotoUrl = null;

        String newPhotoUrl = userProfileForm.getPhotoUrl();

        if (user != null) {
            Uri oldPhotoUri = user.getPhotoUrl();
            String oldPhotoUrl;
            if (oldPhotoUri != null) {
                oldPhotoUrl = oldPhotoUri.toString();

                if (!newPhotoUrl.equals(oldPhotoUrl)) {
                    if (newPhotoUrl.isEmpty()) {
                        errorPhotoUrl = "Photo URL field must not be empty.";
                    } else {
                        errorPhotoUrl = null;
                        updatePhotoUrl = true;
                    }
                }
            } else if (!newPhotoUrl.isEmpty()) {
                errorPhotoUrl = null;
                updatePhotoUrl = true;
            }

            if (updatePhotoUrl) {
                Uri newPhotoUri = Uri.parse(newPhotoUrl);

                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setPhotoUri(newPhotoUri).build();

                user.updateProfile(profileUpdates).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Timber.d("Successfully updated user photo URI.");
                        showProfilePhoto(newPhotoUri);
                        userProfileForm.setPhotoUrl(newPhotoUrl);
                        resetErrorPhotoUrl();
                        Toast.makeText(mContext,
                                "Update photo URL.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (errorPhotoUrl != null) {
                setErrorPhotoUrl(errorPhotoUrl);
            } else {
                resetErrorPhotoUrl();
            }

        } else {
            startLoginUI();
        }
    }

    public void onClickDeleteProfilePhoto(View view) {
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setCancelable(true);
            builder.setTitle("Remove Profile Photo");
            builder.setMessage("Are you sure you want to remove your profile photo?");
            builder.setPositiveButton("Yes", (dialog, which) -> {
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setPhotoUri(null).build();

                user.updateProfile(profileUpdates).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Timber.d("Successfully removed user photo URI.");
                        Toast.makeText(mContext, "Removed profile photo.", Toast.LENGTH_SHORT).show();
                        resetErrorPhotoUrl();
                        showProfilePhoto(null);
                    }
                });
            });

            builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> {

            });

            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            startLoginUI();
        }
    }

    public void onClickUpdatePassword(View view) {
        FirebaseUser user = mAuth.getCurrentUser();

        boolean updatePassword = false;
        String errorPassword = null;
        String errorConfirmPassword = null;

        String newPassword = userProfileForm.getPassword();
        String newConfirmPassword = userProfileForm.getConfirmPassword();

        if (user != null) {
            if (!newPassword.isEmpty()) {
                if (newConfirmPassword.isEmpty()) {
                    errorPassword = "Confirm password field must not be empty.";
                } else if (!newPassword.equals(newConfirmPassword)) {
                    errorConfirmPassword = "Passwords must match.";
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
                        Toast.makeText(mContext,
                                "Updated password.",
                                Toast.LENGTH_SHORT).show();
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

    private void setErrorEmailAddress(String error) {
        resetErrorConfirmEmailAddress();
        userProfileForm.setErrorEmailAddress(error);
    }

    private void resetErrorEmailAddress() {
        userProfileForm.setErrorEmailAddress(null);
    }

    private void setErrorConfirmEmailAddress(String error) {
        resetErrorEmailAddress();
        userProfileForm.setErrorConfirmEmailAddress(error);
    }

    private void resetErrorConfirmEmailAddress() {
        userProfileForm.setErrorConfirmEmailAddress(null);
    }

    private void resetAllErrorEmailAddress() {
        resetErrorEmailAddress();
        resetErrorConfirmEmailAddress();
    }

    private void setErrorDisplayName(String error) {
        userProfileForm.setErrorDisplayName(error);
    }

    private void resetErrorDisplayName() {
        userProfileForm.setErrorDisplayName(null);
    }

    private void setErrorPhotoUrl(String error) {
        userProfileForm.setErrorPhotoUrl(error);
    }

    private void resetErrorPhotoUrl() {
        userProfileForm.setErrorPhotoUrl(null);
    }

    private void resetErrorNewPassword() {
        userProfileForm.setErrorPassword(null);
    }

    private void resetErrorConfirmPassword() {
        userProfileForm.setErrorConfirmPassword(null);
    }

    private void resetAllErrorPassword() {
        resetErrorNewPassword();
        resetErrorConfirmPassword();
    }

    private void setErrorNewPassword(String error) {
        resetErrorConfirmPassword();
        userProfileForm.setErrorPassword(error);
    }

    private void setErrorConfirmPassword(String error) {
        resetErrorNewPassword();
        userProfileForm.setConfirmPassword(error);
    }

    private void resetAllPasswordFields() {
        userProfileForm.setPassword(null);
        userProfileForm.setConfirmPassword(null);
    }

    private void checkEmailVerification(FirebaseUser user) {
        boolean emailVerified = user.isEmailVerified();

        if (!emailVerified) {
            String uid = user.getUid();

            setErrorEmailAddress(getString(R.string.verify_email_request));

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
                    Timber.e(databaseError.toString());
                }
            };

            mReferenceUsers.addListenerForSingleValueEvent(listener);
        }
    }
}
