package com.wineguesser.deductive.repository;

import com.google.firebase.database.DatabaseReference;

public class WhiteVarietyDataRepository extends FirebaseRepository {

    private final DatabaseReference mReference;

    public WhiteVarietyDataRepository() {
        mReference = mDatabase.getReference("whiteVarietyData");
    }


}
