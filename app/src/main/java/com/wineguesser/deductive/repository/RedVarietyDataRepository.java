package com.wineguesser.deductive.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.wineguesser.deductive.model.RedVarietyData;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RedVarietyDataRepository extends FirebaseRepository {

//    private final DatabaseReference mReference;
//
//    public RedVarietyDataRepository() {
//        mReference = mDatabase.getReference("redVarietyData");
//    }
//
//    public LiveData<List<RedVarietyData>> getRedVarietyData() {
//
//    }
//
//    class VarietyDataList extends LiveData<List<RedVarietyData>> {
//        private final Query query;
//        private final MyValueEventListener listener = new MyValueEventListener();
//
//        VarietyDataList() {
//
//        }
//
//        private class MyValueEventListener implements ValueEventListener {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                List<RedVarietyData> data = new ArrayList<>();
//
//                for (DataSnapshot entry : dataSnapshot.getChildren()) {
//                    String
//
//
//                }
//
//
//            }
//
//
//        }
//    }
}
