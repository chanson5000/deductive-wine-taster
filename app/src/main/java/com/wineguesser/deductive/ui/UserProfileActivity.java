package com.wineguesser.deductive.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wineguesser.deductive.R;
import com.wineguesser.deductive.repository.DatabaseContract;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class UserProfileActivity extends AppCompatActivity implements DatabaseContract {

    private Context mContext;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mReferenceUsers;
    private boolean mUserLoggedIn;

    @BindView(R.id.textView_user_name)
    TextView mTextViewUserName;
    @BindView(R.id.editText_display_name)
    EditText mEditTextDisplayName;
    @BindView(R.id.editText_email_address)
    EditText mEditTextEmailAddress;
    @BindView(R.id.editText_confirm_email_address)
    EditText mEditTextConfirmEmailAddress;
    @BindView(R.id.editText_photo_url)
    EditText mEditTextPhotoUri;
    @BindView(R.id.imageView_profile_photo)
    ImageView mImageProfilePhoto;
    @BindView(R.id.editText_new_password)
    EditText mEditTextNewPassword;
    @BindView(R.id.editText_confirm_password)
    EditText mEditTextConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        ButterKnife.bind(this);

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

            if (user != null) {
                setUserLoggedIn(user);
//                checkEmailVerification(user);
            } else {
                mUserLoggedIn = false;
                mImageProfilePhoto.setVisibility(View.GONE);
                startLoginUI();
            }
        };

        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        mAuth.removeAuthStateListener(mAuthListener);
    }

    private void setUserLoggedIn(FirebaseUser user) {
        mUserLoggedIn = true;

        String uid = user.getUid();
        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();

        mTextViewUserName.setText(name);
        mEditTextDisplayName.setText(name);
        mEditTextEmailAddress.setText(email);

        if (photoUrl != null) {
            mEditTextPhotoUri.setText(photoUrl.toString());
            Picasso.get().load(photoUrl).into(mImageProfilePhoto, new Callback() {
                @Override
                public void onSuccess() {
                    mImageProfilePhoto.setVisibility(View.VISIBLE);
                    Timber.d("Photo image successfully loaded.");
                }

                @Override
                public void onError(Exception e) {
                    mImageProfilePhoto.setVisibility(View.GONE);
                    Timber.d("Error loading profile image.");
                }
            });
        } else {
            mImageProfilePhoto.setVisibility(View.GONE);
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

    public void onClickUpdateProfile(View view) {

    }

    public void onClickUpdatePassword(View view) {

    }

}
