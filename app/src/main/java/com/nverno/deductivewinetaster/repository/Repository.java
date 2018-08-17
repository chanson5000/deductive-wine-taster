package com.nverno.deductivewinetaster.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nverno.deductivewinetaster.data.FirebaseDataContract;

import java.util.ArrayList;
import java.util.List;

public class Repository implements FirebaseDataContract {
    private final DatabaseReference COUNTRIES_REF;
    private final DatabaseReference RED_VARIETIES;
    private final DatabaseReference WHITE_VARIETIES;

    public Repository() {
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

        COUNTRIES_REF = mDatabase.getReference("/lists/countries");
        RED_VARIETIES = mDatabase.getReference("/lists/varieties/red");
        WHITE_VARIETIES = mDatabase.getReference("/lists/varieties/white");
    }

    public FirebaseChildrenList getCountriesList() {
        return new FirebaseChildrenList(COUNTRIES_REF);
    }

    public FirebaseChildrenList getRedVarietiesList() {
        return new FirebaseChildrenList(RED_VARIETIES);
    }

    public FirebaseChildrenList getWhiteVarietiesList() {
        return new FirebaseChildrenList(WHITE_VARIETIES);
    }

    class FirebaseChildrenList extends LiveData<List<String>> {

        private static final String LOG_TAG = "FirebaseLiveData";
        private final Query query;
        private final MyValueEventListener listener = new MyValueEventListener();

        FirebaseChildrenList(Query query) {
            this.query = query;
        }

        FirebaseChildrenList(DatabaseReference ref) {
            this.query = ref;
        }

        @Override
        protected void onActive() {
            Log.d(LOG_TAG, "onActive");
            query.addValueEventListener(listener);
        }

        @Override
        protected void onInactive() {
            Log.d(LOG_TAG, "onInactive");
            query.removeEventListener(listener);
        }

        private class MyValueEventListener implements ValueEventListener {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> data = new ArrayList<>();
                for (DataSnapshot entry : dataSnapshot.getChildren()) {
                    data.add(entry.getKey());
                }
                setValue(data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(LOG_TAG, "Can't listen to query " + query, databaseError.toException());
            }
        }
    }

    class FirebaseLiveData extends LiveData<DataSnapshot> {

        private static final String LOG_TAG = "FirebaseLiveData";

        private final Query query;
        private final MyValueEventListener listener = new MyValueEventListener();

        public FirebaseLiveData(Query query) {
            this.query = query;
        }

        public FirebaseLiveData(DatabaseReference ref) {
            this.query = ref;
        }

        @Override
        protected void onActive() {
            Log.d(LOG_TAG, "onActive");
            query.addValueEventListener(listener);
        }

        @Override
        protected void onInactive() {
            Log.d(LOG_TAG, "onInactive");
            query.removeEventListener(listener);
        }

        private class MyValueEventListener implements ValueEventListener {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setValue(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(LOG_TAG, "Can't listen to query " + query, databaseError.toException());
            }
        }
    }
}
