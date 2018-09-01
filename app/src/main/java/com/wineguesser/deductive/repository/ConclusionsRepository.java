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

    private final DatabaseReference mReference;

    public ConclusionsRepository() {
        mReference = mDatabase.getReference("conclusions");
    }

    public LiveData<List<ConclusionRecord>> getConclusionsForUser(String uid) {
        return new ConclusionsList(uid);
    }

    class ConclusionsList extends LiveData<List<ConclusionRecord>> {
        private final Query query;
        private final MyValueEventListener listener = new MyValueEventListener();

        ConclusionsList(String uid) {
            query = mReference.child(uid);
        }

        @Override
        protected void onActive() { query.addValueEventListener(listener); }

        @Override
        protected void onInactive() { query.removeEventListener(listener); }

        private class MyValueEventListener implements ValueEventListener {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<ConclusionRecord> data = new ArrayList<>();

                for (DataSnapshot entry : dataSnapshot.getChildren()) {
                    String conclusionId = entry.getKey();
                    ConclusionRecord conclusionRecord = (ConclusionRecord) entry.getValue();
                    if (conclusionRecord != null) {
                        conclusionRecord.setConclusionId(conclusionId);
                    }
                    data.add(conclusionRecord);
                }

                setValue(data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Timber.e(databaseError.toException());
                setValue(null);
            }
        }
    }
}
