package com.wineguesser.deductive.repository;

import com.google.firebase.database.DatabaseReference;

public class UserRepository extends FirebaseRepository {

    private final DatabaseReference mReference;

    public UserRepository() {
        mReference = mDatabase.getReference("users");
    }


}
