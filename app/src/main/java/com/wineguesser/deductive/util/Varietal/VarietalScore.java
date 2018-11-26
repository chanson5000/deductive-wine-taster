package com.wineguesser.deductive.util.Varietal;

import android.os.AsyncTask;
import android.util.SparseIntArray;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.HashMap;

import androidx.annotation.NonNull;
import timber.log.Timber;

import static com.wineguesser.deductive.repository.DatabaseContract.DB_REFERENCE_RED_VARIETY_DESCRIPTORS;
import static com.wineguesser.deductive.repository.DatabaseContract.DB_REFERENCE_WHITE_VARIETY_DESCRIPTORS;

public class VarietalScore extends AsyncTask<SparseIntArray, Void, Void> {

    private final VarietalScoreResult varietalScoreResultContext;
    private static DatabaseReference databaseReference;

    public VarietalScore(VarietalScoreResult context, Boolean isRedWine) {
        varietalScoreResultContext = context;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        if (isRedWine) {
            databaseReference = firebaseDatabase.getReference(DB_REFERENCE_RED_VARIETY_DESCRIPTORS);
        } else {
            databaseReference = firebaseDatabase.getReference(DB_REFERENCE_WHITE_VARIETY_DESCRIPTORS);
        }
    }

    @Override
    protected Void doInBackground(SparseIntArray... formSelections) {
        // Convert our form collection keys to our database keys so that they can be compared.
        VarietalFormResultFormatter formSelectionFormatter = new VarietalFormResultFormatter(formSelections[0]);

        HashMap<String, Integer> descriptorsMap = formSelectionFormatter.getDescriptorsHashMap();
        // New empty map to hold scores.
        HashMap<String, Integer> varietyScoresMap = new HashMap<>();

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Iterate through our database of descriptors for each wine variety and
                // add point(s) for score each time there is a characteristic match.

                // Here we iterate through each wine variety in our database.
                // forEach (Iterable) is API level 24+.
                for (DataSnapshot varietyRecord : dataSnapshot.getChildren()) {
                    String varietyId = varietyRecord.getKey();
                    int currentVarietyScore = 0;
                    // Here we iterate through each descriptor of the variety.
                    for (DataSnapshot varietyDescriptor : varietyRecord.getChildren()) {
                        if (descriptorsMap.containsKey(varietyDescriptor.getKey())) {
                            // Increment a score for each match.
                            // TODO: Test this cast.
                            currentVarietyScore = calculateNewVarietalScore(
                                    currentVarietyScore,
                                    (Integer) varietyDescriptor.getValue());
                        }
                    }

                    Timber.d("Putting score: %s, %s", varietyId, currentVarietyScore);
                    // Putting the final score for the record into the map.
                    varietyScoresMap.put(varietyId, currentVarietyScore);
                }

                // Find the wine variety key with the highest score.
                String highestScoreId = Collections.max(
                        varietyScoresMap.entrySet(),
                        (wineId, wineScore) -> wineId.getValue() - wineScore.getValue()).getKey();

                if (highestScoreId != null) {
                    Timber.d("Result of wine scoring: %s", highestScoreId);
                    // We now have a wine variety to call back;
                    varietalScoreResultContext.onGrapeResult(highestScoreId);
                } else {
                    varietalScoreResultContext.onGrapeFailure();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                varietalScoreResultContext.onGrapeFailure();

                Timber.e(databaseError.toString());
            }
        };
        // The listener that is triggering the code block above. Only needed to trigger once.
        databaseReference.addListenerForSingleValueEvent(listener);

        return null;
    }

    private int calculateNewVarietalScore(int previousScore, Integer toIncrementScore) {
        if (toIncrementScore != null && toIncrementScore > 1) {
            // Two points for key indicator of variety (value was a 2 or higher)
            previousScore += 2;
        } else {
            // On point for regular indicator
            previousScore++;
        }
        return previousScore;
    }
}
