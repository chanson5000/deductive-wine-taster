package com.wineguesser.deductive.repository;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import timber.log.Timber;

public class UserRepository extends FirebaseRepository {

    private final DatabaseReference mDatabaseReference;

    public UserRepository() {
       mDatabaseReference = getDatabaseInstance().getReference("users");
    }

    public void checkEmailVerification(FirebaseUser user) {
        boolean emailVerified = user.isEmailVerified();

        if (!emailVerified) {
            String uid = user.getUid();
            DatabaseReference userReference = mDatabaseReference.child(uid);

            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(DB_EMAIL_VERIFICATION)
                            .getValue() != (Boolean) true) {

                        user.sendEmailVerification().addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Timber.d("Email verification for user sent.");
                                // Set that we have requested e-mail verification so we don't
                                // spam the user.
                                userReference.child(DB_EMAIL_VERIFICATION)
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

            userReference.addListenerForSingleValueEvent(listener);
        }
    }
}
