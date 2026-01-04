package com.wineguesser.deductive.view

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.wineguesser.deductive.R
import com.wineguesser.deductive.databinding.ActivityUserProfileBinding
import com.wineguesser.deductive.repository.UserRepository
import com.wineguesser.deductive.util.Helpers
import com.wineguesser.deductive.viewmodel.UserProfileViewModel
import timber.log.Timber

class UserProfileActivity : AppCompatActivity() {

    private lateinit var mContext: Context
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mAuthListener: FirebaseAuth.AuthStateListener

    private var mNewTextDisplayName: String? = null
    private var mNewTextEmailAddress: String? = null
    private var mNewTextConfirmEmailAddress: String? = null
    private var mNewPassword: String? = null
    private var mNewConfirmPassword: String? = null

    private lateinit var userProfileModel: UserProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        // SplashScreen.installSplashScreen(this)
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val binding: ActivityUserProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_profile)

        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(insets.left, insets.top, insets.right, insets.bottom)
            windowInsets
        }

        title = getString(R.string.user_profile_activity_title)

        userProfileModel = ViewModelProvider(this)[UserProfileViewModel::class.java]
        binding.lifecycleOwner = this
        binding.userProfileForm = userProfileModel

        if (savedInstanceState != null) {
            mNewTextDisplayName = savedInstanceState.getString(NEW_DISPLAY_NAME)
            mNewTextEmailAddress = savedInstanceState.getString(NEW_EMAIL_ADDRESS)
            mNewTextConfirmEmailAddress = savedInstanceState.getString(NEW_CONFIRM_EMAIL_ADDRESS)
            mNewPassword = savedInstanceState.getString(NEW_PASSWORD)
            mNewConfirmPassword = savedInstanceState.getString(NEW_CONFIRM_PASSWORD)
        }

        mContext = this
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                setUserLoggedIn(user)
                checkEmailVerification(user)
            } else {
                startLoginUI()
            }
        }
        mAuth.addAuthStateListener(mAuthListener)
    }

    private fun setUserLoggedIn(user: FirebaseUser) {
        val name = user.displayName
        val email = user.email

        userProfileModel.setUserName(name)

        if (mNewTextDisplayName == null) {
            userProfileModel.setDisplayName(name)
        }

        if (mNewTextEmailAddress == null) {
            userProfileModel.setEmailAddress(email)
        }
    }

    override fun onResume() {
        super.onResume()
        loadUiState()
    }

    private fun loadUiState() {
        if (mNewTextDisplayName != null) {
            userProfileModel.setDisplayName(mNewTextDisplayName)
        }
        if (mNewTextEmailAddress != null) {
            userProfileModel.setEmailAddress(mNewTextEmailAddress)
        }
        if (mNewTextConfirmEmailAddress != null) {
            userProfileModel.setConfirmEmailAddress(mNewTextConfirmEmailAddress)
        }
        if (mNewPassword != null) {
            userProfileModel.setPassword(mNewPassword)
        }
        if (mNewConfirmPassword != null) {
            userProfileModel.setConfirmPassword(mNewConfirmPassword)
        }
    }

    override fun onStop() {
        super.onStop()
        mAuth.removeAuthStateListener(mAuthListener)
    }

    override fun onPause() {
        super.onPause()
        saveUiState()
    }

    private fun saveUiState() {
        mNewTextDisplayName = userProfileModel.displayName.value
        mNewTextEmailAddress = userProfileModel.emailAddress.value
        mNewTextConfirmEmailAddress = userProfileModel.confirmEmailAddress.value
        mNewPassword = userProfileModel.password.value
        mNewConfirmPassword = userProfileModel.confirmPassword.value
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(NEW_DISPLAY_NAME, mNewTextDisplayName)
        outState.putString(NEW_EMAIL_ADDRESS, mNewTextEmailAddress)
        outState.putString(NEW_CONFIRM_EMAIL_ADDRESS, mNewTextConfirmEmailAddress)
        outState.putString(NEW_PASSWORD, mNewPassword)
        outState.putString(NEW_CONFIRM_PASSWORD, mNewConfirmPassword)
    }

    private fun startLoginUI() {
        val user = mAuth.currentUser
        val RC_SIGN_IN = 42

        if (user == null) {
            val providers = listOf(
                AuthUI.IdpConfig.EmailBuilder().build()
            )

            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build(),
                RC_SIGN_IN
            )
        } else {
            mAuth.signOut()
        }
    }

    fun onClickUpdateDetails(view: View) {
        val user = mAuth.currentUser

        var updateDisplayName = false
        var updateEmailAddress = false

        var errorDisplayName: String? = null
        var errorEmailAddress: String? = null
        var errorConfirmEmailAddress: String? = null

        val newDisplayName = userProfileModel.displayName.value
        val newEmailAddress = userProfileModel.emailAddress.value
        val newConfirmEmailAddress = userProfileModel.confirmEmailAddress.value

        if (user != null) {
            if (newDisplayName != null && newDisplayName != user.displayName) {
                if (newDisplayName.isEmpty()) {
                    errorDisplayName = getString(R.string.up_error_display_name_field_empty)
                } else {
                    errorDisplayName = null
                    updateDisplayName = true
                }
            }

            if (newEmailAddress != null && newEmailAddress != user.email) {
                if (newEmailAddress.isEmpty()) {
                    errorEmailAddress = getString(R.string.up_error_email_field_empty)
                } else if (newConfirmEmailAddress != null && newConfirmEmailAddress.isEmpty()) {
                    errorConfirmEmailAddress = getString(R.string.up_error_confirm_email_field_empty)
                } else if (newEmailAddress != newConfirmEmailAddress) {
                    errorEmailAddress = getString(R.string.up_error_email_fields_must_match)
                } else {
                    errorEmailAddress = null
                    errorConfirmEmailAddress = null
                    updateEmailAddress = true
                }
            }

            if (updateDisplayName) {
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(newDisplayName).build()

                user.updateProfile(profileUpdates).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Timber.d("Successfully updated user display name.")
                        userProfileModel.setUserName(newDisplayName)
                        userProfileModel.setDisplayName(newDisplayName)
                        resetErrorDisplayName()
                        Helpers.makeToastShort(mContext, R.string.up_toast_updated_display_name)
                    }
                }
            } else if (errorDisplayName != null) {
                setErrorDisplayName(errorDisplayName)
            } else {
                resetErrorDisplayName()
            }

            if (updateEmailAddress) {
                user.updateEmail(newEmailAddress!!).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Timber.d("Successfully updated user email address.")
                        userProfileModel.setEmailAddress(newEmailAddress)
                        userProfileModel.setConfirmEmailAddress(null)
                        resetAllErrorEmailAddress()
                        Helpers.makeToastShort(mContext, R.string.up_toast_updated_email)
                    } else if (task.exception is FirebaseAuthRecentLoginRequiredException) {
                        val builder = AlertDialog.Builder(this)
                        builder.setTitle(R.string.up_re_authenticate)
                        val password = EditText(this)
                        password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        builder.setView(password)
                        builder.setPositiveButton(android.R.string.ok) { _, _ ->
                            val email = user.email
                            if (email != null) {
                                val credential = EmailAuthProvider
                                    .getCredential(user.email!!, password.text.toString())

                                user.reauthenticate(credential).addOnCompleteListener { reAuthTask ->
                                    if (reAuthTask.isSuccessful) {
                                        user.updateEmail(newEmailAddress).addOnCompleteListener { updateEmailTask ->
                                            if (updateEmailTask.isSuccessful) {
                                                Timber.d("Successfully updated user email address.")
                                                userProfileModel.setEmailAddress(newEmailAddress)
                                                userProfileModel.setConfirmEmailAddress(null)
                                                resetAllErrorEmailAddress()
                                                Helpers.makeToastShort(mContext, R.string.up_toast_updated_email)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        builder.setNegativeButton(android.R.string.cancel) { dialog, _ -> dialog.cancel() }
                        builder.show()
                    } else {
                        if (task.exception != null) {
                            Timber.e("Error updating email address: %s", task.exception.toString())
                        } else if (task.result != null) {
                            Timber.e("Error updating email address: %s", task.result.toString())
                        } else {
                            Timber.e("Unknown error while updating email address.")
                        }
                        setErrorEmailAddress(getString(R.string.up_unable_to_update_email))
                    }
                }
            } else if (errorEmailAddress != null) {
                setErrorEmailAddress(errorEmailAddress)
            } else if (errorConfirmEmailAddress != null) {
                setErrorConfirmEmailAddress(errorConfirmEmailAddress)
            } else {
                resetAllErrorEmailAddress()
            }
        } else {
            startLoginUI()
        }
    }

    fun onClickUpdatePassword(view: View) {
        val user = mAuth.currentUser

        var updatePassword = false
        var errorPassword: String? = null
        var errorConfirmPassword: String? = null

        val newPassword = userProfileModel.password.value
        val newConfirmPassword = userProfileModel.confirmPassword.value

        if (user != null) {
            if (!newPassword.isNullOrEmpty()) {
                if (newConfirmPassword.isNullOrEmpty()) {
                    errorPassword = getString(R.string.up_error_password_empty)
                } else if (newPassword != newConfirmPassword) {
                    errorConfirmPassword = getString(R.string.up_error_passwords_must_match)
                } else {
                    updatePassword = true
                }
            }

            if (updatePassword) {
                user.updatePassword(newPassword!!).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Timber.d("Successfully updated user password.")
                        resetAllPasswordFields()
                        resetAllErrorPassword()
                        Helpers.makeToastShort(mContext, R.string.up_toast_updated_password)
                    } else if (task.exception is FirebaseAuthRecentLoginRequiredException) {
                        val builder = AlertDialog.Builder(this)
                        builder.setTitle(R.string.up_re_authenticate)
                        val password = EditText(this)
                        password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        builder.setView(password)
                        builder.setPositiveButton(android.R.string.ok) { _, _ ->
                            val email = user.email
                            if (email != null) {
                                val credential = EmailAuthProvider
                                    .getCredential(user.email!!, password.text.toString())

                                user.reauthenticate(credential).addOnCompleteListener {
                                    user.updatePassword(newPassword).addOnCompleteListener { updatePasswordTask ->
                                        if (updatePasswordTask.isSuccessful) {
                                            Timber.d("Successfully updated user password.")
                                            resetAllPasswordFields()
                                            resetAllErrorPassword()
                                            Helpers.makeToastShort(mContext, R.string.up_toast_updated_password)
                                        }
                                    }
                                }
                            }
                        }
                        builder.setNegativeButton(android.R.string.cancel) { dialog, _ -> dialog.cancel() }
                        builder.show()
                    } else if (task.exception is FirebaseAuthWeakPasswordException) {
                        setErrorNewPassword(getString(R.string.up_weak_password))
                    } else {
                        if (task.exception != null) {
                            Timber.e("Error updating password: %s", task.exception.toString())
                        } else if (task.result != null) {
                            Timber.e("Error updating password: %s", task.result.toString())
                        } else {
                            Timber.e("Unknown error while updating password.")
                        }
                        setErrorNewPassword(getString(R.string.up_unable_to_update_password))
                    }
                }

            } else if (errorPassword != null) {
                setErrorNewPassword(errorPassword)
            } else if (errorConfirmPassword != null) {
                setErrorConfirmPassword(errorConfirmPassword)
            } else {
                resetAllErrorPassword()
            }

        } else {
            startLoginUI()
        }
    }

    fun onClickDeleteAccount(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.up_confirm_account_deletion)
        builder.setPositiveButton(android.R.string.yes) { _, _ ->
            val user = mAuth.currentUser
            if (user != null) {
                user.delete()
                Helpers.makeToastShort(mContext, R.string.up_account_deleted)
            } else {
                Helpers.makeToastShort(mContext, R.string.up_account_deletion_failed)
            }
        }
        builder.setNegativeButton(android.R.string.cancel) { dialog, _ -> dialog.cancel() }
        builder.show()
    }

    private fun setErrorEmailAddress(error: String) {
        resetErrorConfirmEmailAddress()
        userProfileModel.setErrorEmailAddress(error)
    }

    private fun resetErrorEmailAddress() {
        userProfileModel.setErrorEmailAddress(null)
    }

    private fun setErrorConfirmEmailAddress(error: String) {
        resetErrorEmailAddress()
        userProfileModel.setErrorConfirmEmailAddress(error)
    }

    private fun resetErrorConfirmEmailAddress() {
        userProfileModel.setErrorConfirmEmailAddress(null)
    }

    private fun resetAllErrorEmailAddress() {
        resetErrorEmailAddress()
        resetErrorConfirmEmailAddress()
    }

    private fun setErrorDisplayName(error: String) {
        userProfileModel.setErrorDisplayName(error)
    }

    private fun resetErrorDisplayName() {
        userProfileModel.setErrorDisplayName(null)
    }

    private fun resetErrorNewPassword() {
        userProfileModel.setErrorPassword(null)
    }

    private fun resetErrorConfirmPassword() {
        userProfileModel.setErrorConfirmPassword(null)
    }

    private fun resetAllErrorPassword() {
        resetErrorNewPassword()
        resetErrorConfirmPassword()
    }

    private fun setErrorNewPassword(error: String) {
        resetErrorConfirmPassword()
        userProfileModel.setErrorPassword(error)
    }

    private fun setErrorConfirmPassword(error: String) {
        resetErrorNewPassword()
        userProfileModel.setConfirmPassword(error)
    }

    private fun resetAllPasswordFields() {
        userProfileModel.setPassword(null)
        userProfileModel.setConfirmPassword(null)
    }

    private fun checkEmailVerification(user: FirebaseUser) {
        val emailVerified = user.isEmailVerified

        if (!emailVerified) {
            setErrorEmailAddress(getString(R.string.verify_email_request))

            val repository = UserRepository()
            repository.checkEmailVerification(user)
        }
    }

    companion object {
        private const val NEW_DISPLAY_NAME = "NEW_DISPLAY_NAME"
        private const val NEW_EMAIL_ADDRESS = "NEW_EMAIL_ADDRESS"
        private const val NEW_CONFIRM_EMAIL_ADDRESS = "NEW_CONFIRM_EMAIL_ADDRESS"
        private const val NEW_PASSWORD = "NEW_PASSWORD"
        private const val NEW_CONFIRM_PASSWORD = "NEW_CONFIRM_PASSWORD"
    }
}
