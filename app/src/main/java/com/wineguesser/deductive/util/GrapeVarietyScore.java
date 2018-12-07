package com.wineguesser.deductive.util;


import android.os.AsyncTask;
import android.util.SparseIntArray;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wineguesser.deductive.repository.DatabaseContract;
import com.wineguesser.deductive.view.DeductionFormContract;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import timber.log.Timber;

import static com.wineguesser.deductive.repository.DatabaseContract.DB_REFERENCE_RED_VARIETY_DESCRIPTORS;
import static com.wineguesser.deductive.repository.DatabaseContract.DB_REFERENCE_WHITE_VARIETY_DESCRIPTORS;

public class GrapeVarietyScore extends AsyncTask<Map<String, Integer>, Void, Void> {

    private final GrapeVarietyScoreResult mContext;
    private static DatabaseReference mDatabaseReference;

    public GrapeVarietyScore(GrapeVarietyScoreResult context, Boolean isRedWine) {
        mContext = context;
        mDatabaseReference = setDatabaseReference(isRedWine, FirebaseDatabase.getInstance());
    }

    private DatabaseReference setDatabaseReference(boolean isRedWine, FirebaseDatabase database) {
        return isRedWine
                ? database.getReference(DB_REFERENCE_RED_VARIETY_DESCRIPTORS)
                : database.getReference(DB_REFERENCE_WHITE_VARIETY_DESCRIPTORS);
    }

    @Override
    protected Void doInBackground(Map<String, Integer>[] descriptorsMap) {
        Map<String, Integer> varietyScoresMap = new HashMap<>();

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot varietyRecord : dataSnapshot.getChildren()) {
                    int currentVarietyScore = getCurrentVarietyScore(varietyRecord, descriptorsMap[0]);
                    String varietyId = varietyRecord.getKey();
                    Timber.d("Putting score: %s, %s", varietyId, currentVarietyScore);
                    varietyScoresMap.put(varietyId, currentVarietyScore);
                }

                // Find the wine variety key with the highest score.
                String highestScoreId = Collections.max(
                        varietyScoresMap.entrySet(),
                        (wineId, wineScore) -> wineId.getValue() - wineScore.getValue()).getKey();

                if (highestScoreId != null) {
                    Timber.d("Result of wine scoring: %s", highestScoreId);
                    // We now have a wine variety to call back;
                    mContext.onGrapeResult(highestScoreId);
                } else {
                    mContext.onGrapeFailure();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mContext.onGrapeFailure();

                Timber.e(databaseError.toString());
            }
        };
        // The listener that is triggering the code block above. Only needed to trigger once.
        mDatabaseReference.addListenerForSingleValueEvent(listener);

        return null;
    }

    private int getCurrentVarietyScore(DataSnapshot varietyRecord,
                                       Map<String, Integer> descriptorsMap) {
        int currentVarietyScore = 0;

        for (DataSnapshot varietyDescriptor : varietyRecord.getChildren()) {
            if (descriptorsMap.containsKey(varietyDescriptor.getKey())) {
                currentVarietyScore +=
                        calculateScoreIncrease(varietyDescriptor.getValue(Integer.class));
            }
        }
        return currentVarietyScore;
    }

    private int calculateScoreIncrease(Integer varietyDescriptorValue) {
        if (varietyDescriptorValue != null && varietyDescriptorValue > 1) {
            // Two points for key indicator of variety (value is a 2 or higher)
            return 2;
        } else {
            // One point for regular indicator
            return 1;
        }
    }
}
