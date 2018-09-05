package com.wineguesser.deductive.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.wineguesser.deductive.model.RedVarietyDescriptors;
import com.wineguesser.deductive.model.WhiteVarietyDescriptors;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class VarietyDescriptorsRepository extends FirebaseRepository {

    private final DatabaseReference mRedReference;
    private final DatabaseReference mWhiteReference;

    public VarietyDescriptorsRepository() {
        mRedReference = mDatabase.getReference(DB_REFERENCE_RED_VARIETY_DESCRIPTORS);
        mWhiteReference = mDatabase.getReference(DB_REFERENCE_WHITE_VARIETY_DESCRIPTORS);
    }

    public LiveData<List<RedVarietyDescriptors>> getAllRedVarietyDescriptors() {
        return new RedVarietyDescriptorsList();
    }

    public LiveData<List<WhiteVarietyDescriptors>> getAllWhiteVarietyDescriptors() {
        return new WhiteVarietyDescriptorsList();
    }

    class RedVarietyDescriptorsList extends LiveData<List<RedVarietyDescriptors>> {
        private final Query query;
        private final MyValueEventListener listener = new MyValueEventListener();

        RedVarietyDescriptorsList() {
            query = mRedReference;
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
                List<RedVarietyDescriptors> data = new ArrayList<>();

                for (DataSnapshot entry : dataSnapshot.getChildren()) {
                    String varietyId = entry.getKey();
                    RedVarietyDescriptors redVarietyDescriptors
                            = (RedVarietyDescriptors) entry.getValue();
                    if (redVarietyDescriptors != null) {
                        redVarietyDescriptors.setVarietyId(varietyId);
                    }

                    data.add(redVarietyDescriptors);
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

    class WhiteVarietyDescriptorsList extends LiveData<List<WhiteVarietyDescriptors>> {
        private final Query query;
        private final MyValueEventListener listener = new MyValueEventListener();

        WhiteVarietyDescriptorsList() {
            query = mWhiteReference;
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
                List<WhiteVarietyDescriptors> data = new ArrayList<>();

                for (DataSnapshot entry : dataSnapshot.getChildren()) {
                    String varietyId = entry.getKey();
                    WhiteVarietyDescriptors whiteVarietyDescriptors
                            = (WhiteVarietyDescriptors) entry.getValue();
                    if (whiteVarietyDescriptors != null) {
                        whiteVarietyDescriptors.setVarietyId(varietyId);
                    }

                    data.add(whiteVarietyDescriptors);
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
