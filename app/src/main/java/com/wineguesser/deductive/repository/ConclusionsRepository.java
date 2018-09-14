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
import java.util.Collections;
import java.util.List;

import timber.log.Timber;

public class ConclusionsRepository extends FirebaseRepository {

    private final DatabaseReference mConclusionsReference;

    public ConclusionsRepository() {
        mConclusionsReference = getDatabaseInstance().getReference("conclusions");
    }

    public void clearUserConclusions(String uid) {
        mConclusionsReference.child(uid).removeValue();
    }

    public void saveConclusionRecord(String uid, ConclusionRecord conclusionRecord) {
        // Get the reference of where our new conclusion record wil be pushed.
        DatabaseReference newConclusionRecordReference
                = mConclusionsReference.child(uid).push();

        // Add the new conclusion record to the database.
        newConclusionRecordReference.setValue(conclusionRecord);
    }

    public void removeConclusionRecord(ConclusionRecord conclusionRecord) {
        mConclusionsReference.child(conclusionRecord.getUserId()).child(conclusionRecord.getConclusionId()).removeValue();
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
                    Collections.reverse(data);
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

