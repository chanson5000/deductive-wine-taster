package com.wineguesser.deductive.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.firebase.ui.auth.AuthUI
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.wineguesser.deductive.R

class MainActivity : AppCompatActivity() {

    private lateinit var mContext: Context
    private var mUserLoggedIn: Boolean = false
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mAuthListener: AuthStateListener

    private var mMenuAuthToggle: MenuItem? = null
    private var mMenuProfile: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.activity_main)

        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.coordinator_main)) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(insets.left, insets.top, insets.right, insets.bottom)
            windowInsets
        }

        mAuth = FirebaseAuth.getInstance()
        mContext = this
    }

    override fun onStart() {
        super.onStart()
        // Set a listener for authentications so we may set states.
        mAuthListener = AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            setUserLoggedIn(user != null)
        }

        mAuth.addAuthStateListener(mAuthListener)
    }

    override fun onStop() {
        super.onStop()
        mAuth.removeAuthStateListener(mAuthListener)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        mMenuAuthToggle = menu.findItem(R.id.auth_toggle)
        mMenuProfile = menu.findItem(R.id.profile_settings)
        setMenuLoggedIn(mUserLoggedIn)
        return true
    }

    private fun setMenuLoggedIn(loggedIn: Boolean) {
        if (loggedIn && mMenuAuthToggle != null) {
            mMenuAuthToggle?.setTitle(R.string.log_out)
            mMenuAuthToggle?.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)
            mMenuProfile?.isVisible = true
        } else if (!loggedIn && mMenuAuthToggle != null) {
            mMenuAuthToggle?.setTitle(R.string.log_in)
            mMenuAuthToggle?.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
            mMenuProfile?.isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.auth_toggle -> {
                if (mUserLoggedIn) {
                    signOutCurrentFirebaseUser()
                } else {
                    startLoginUI()
                }
                true
            }
            R.id.profile_settings -> {
                startActivity(Intent(mContext, UserProfileActivity::class.java))
                true
            }
            R.id.feedback -> {
                startActivity(Intent(mContext, FeedbackActivity::class.java))
                true
            }
            R.id.privacy_policy -> {
                startActivity(Intent(mContext, PrivacyPolicyActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun buttonRedWine(view: View) {
        val intent = Intent(mContext, DeductionFormActivity::class.java)
        intent.putExtra(IS_RED_WINE, TRUE)
        startActivity(intent)
    }

    fun buttonWhiteWine(view: View) {
        val intent = Intent(mContext, DeductionFormActivity::class.java)
        startActivity(intent)
    }

    private fun setUserLoggedIn(loggedIn: Boolean) {
        mUserLoggedIn = loggedIn
        setMenuLoggedIn(loggedIn)
    }

    private fun startLoginUI() {
        val user = mAuth.currentUser
        // Just any number required to identify if we were using a call back.
        // Using a listener instead.
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

    fun onHistoryButtonClicked(view: View) {
        if (mUserLoggedIn) {
            val intent = Intent(mContext, HistoryActivity::class.java)
            startActivity(intent)
        } else {
            val snackbar = Snackbar.make(
                findViewById(R.id.coordinator_main),
                R.string.snack_log_in, Snackbar.LENGTH_LONG
            )
            snackbar.setAction(R.string.log_in) { startLoginUI() }
            snackbar.show()
        }
    }

    private fun signOutCurrentFirebaseUser() {
        AuthUI.getInstance().signOut(mContext).addOnCompleteListener {
            setUserLoggedIn(false)
        }
    }
}
