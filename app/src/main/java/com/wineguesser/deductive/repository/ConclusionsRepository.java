package com.wineguesser.deductive.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.wineguesser.deductive.model.ConclusionRecord;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class ConclusionsRepository extends FirebaseRepository {

    private final DatabaseReference mConclusionsReference;
    private final DatabaseReference mUsersReference;

    public ConclusionsRepository() {
        mConclusionsReference = mDatabase.getReference("conclusions");
        mUsersReference = mDatabase.getReference("users");
    }

    public void clearUserConclusions(String uid) {
        mUsersReference.child(uid).child("conclusions").removeValue();
        mConclusionsReference.child(uid).removeValue();
    }

    public void saveConclusionRecord(String uid, ConclusionRecord conclusionRecord) {
        // Get the reference of where our new conclusion record wil be pushed.
        DatabaseReference newConclusionRecordReference
                = mConclusionsReference.child(uid).push();

        // Retrieve and validate the new generated key.
        String conclusionReferenceKey = mConclusionsReference.getKey();
        if (conclusionReferenceKey != null) {
            // Keep a reference of that key in the user's record.
            mUsersReference.child(uid).child(DB_REFERENCE_USER_CONCLUSIONS)
                    .child(conclusionReferenceKey).setValue(true);
        }

        // Add the new conclusion record to the database.
        newConclusionRecordReference.setValue(conclusionRecord);
    }

    public LiveData<List<ConclusionRecord>> getConclusionsForUser(String uid) {
        return new ConclusionsList(uid);
    }

    class ConclusionsList extends LiveData<List<ConclusionRecord>> {
        private final Query query;
        private final MyValueEventListener listener = new MyValueEventListener();

        ConclusionsList(String uid) {
            query = mConclusionsReference.child(uid);
        }

        @Override
        protected void onActive() {
            query.addValueEventListener(listener);
        }

        @Override
        protected void onInactive() {
            query.removeEventListener(listener);
        }

        private class MyValueEventListener implements ValueEventListener {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    List<ConclusionRecord> data = new ArrayList<>();

                    for (DataSnapshot entry : dataSnapshot.getChildren()) {
                        String conclusionId = entry.getKey();
                        ConclusionRecord conclusionRecord = entry.getValue(ConclusionRecord.class);
                        if (conclusionRecord != null) {
                            conclusionRecord.setConclusionId(conclusionId);
                        }
                        data.add(conclusionRecord);
                    }
                    setValue(data);
                } else {
                    setValue(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Timber.e(databaseError.toException());
                setValue(null);
            }
        }
    }
}

