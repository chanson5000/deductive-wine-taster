package com.wineguesser.deductive.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import timber.log.Timber;

public class VarietyDataRepository extends FirebaseRepository {

    private final DatabaseReference mRedReference;
    private final DatabaseReference mWhiteReference;
    private DatabaseReference mReference;

    public VarietyDataRepository() {
        mRedReference = mDatabase.getReference(DB_REFERENCE_RED_VARIETAL_DATA);
        mWhiteReference = mDatabase.getReference(DB_REFERENCE_WHITE_VARIETAL_DATA);
    }

    public LiveData<String> getRedVarietyNameById(String id) {
        return new VarietyName(mRedReference, id);
    }

    public LiveData<String> getWhiteVarietyNameById(String id) {
        return new VarietyName(mWhiteReference, id);
    }

    class VarietyName extends LiveData<String> {
        private final Query query;
        private final String varietyId;
        private final MyValueEventListener listener = new MyValueEventListener();

        VarietyName(DatabaseReference reference, String varietyId) {
            query = reference;
            this.varietyId = varietyId;
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
                Object dataObject =
                        dataSnapshot.child(varietyId).child("variety").getValue();
                if (dataObject != null) {
                    setValue(dataObject.toString());
                } else {
                    Timber.e("Unable to retrieve variety name.");
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
