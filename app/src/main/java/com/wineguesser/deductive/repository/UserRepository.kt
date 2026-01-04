package com.wineguesser.deductive.repository

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.wineguesser.deductive.repository.DatabaseContract.DB_EMAIL_VERIFICATION
import timber.log.Timber

class UserRepository : FirebaseRepository() {

    private val mDatabaseReference: DatabaseReference = databaseInstance.getReference("users")

    fun checkEmailVerification(user: FirebaseUser) {
        val emailVerified = user.isEmailVerified

        if (!emailVerified) {
            val uid = user.uid
            val userReference = mDatabaseReference.child(uid)

            val listener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.child(DB_EMAIL_VERIFICATION).value != true) {

                        user.sendEmailVerification().addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Timber.d("Email verification for user sent.")
                                // Set that we have requested e-mail verification so we don't
                                // spam the user.
                                userReference.child(DB_EMAIL_VERIFICATION).setValue(true)
                            } else {
                                Timber.d("Sending of email verification failed.")
                            }
                        }
                    } else {
                        Timber.d("Skipped sending user verification e-mail.")
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Timber.e("Error checking for email verification: %s", databaseError.toString())
                }
            }

            userReference.addListenerForSingleValueEvent(listener)
        }
    }
}
