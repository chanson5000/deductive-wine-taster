package com.wineguesser.deductive.util;


import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.SparseIntArray;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wineguesser.deductive.repository.DatabaseContract;
import com.wineguesser.deductive.ui.DeductionFormContract;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

public class GrapeScore extends AsyncTask<SparseIntArray, Void, Void>
        implements DeductionFormContract, DatabaseContract {

    private static final Map<Integer, String> FormToDbConversionMap = new HashMap<Integer, String>() {{

        put(CHECK_FAULT_TCA, KEY_FAULT_TCA);
        put(CHECK_FAULT_HYDROGEN_SULFIDE, KEY_FAULT_HYDROGEN_SULFIDE);
        put(CHECK_FAULT_VOLATILE_ACIDITY, KEY_FAULT_VOLATILE_ACIDITY);
        put(CHECK_FAULT_ETHYL_ACETATE, KEY_FAULT_ETHYL_ACETATE);
        put(CHECK_FAULT_BRETT, KEY_FAULT_BRETT);
        put(CHECK_FAULT_OXIDIZATION, KEY_FAULT_OXIDIZATION);
        put(CHECK_FAULT_OTHER, KEY_FAULT_OTHER);
        put(CHECK_NOSE_FRUIT_RED, KEY_FRUIT_RED);
        put(CHECK_NOSE_FRUIT_BLUE, KEY_FRUIT_BLUE);
        put(CHECK_NOSE_FRUIT_BLACK, KEY_FRUIT_BLACK);
        put(CHECK_NOSE_FRUIT_CITRUS, KEY_FRUIT_CITRUS);
        put(CHECK_NOSE_FRUIT_APPLE_PEAR, KEY_FRUIT_APPLE_PEAR);
        put(CHECK_NOSE_FRUIT_STONE_PIT, KEY_FRUIT_STONE_PIT);
        put(CHECK_NOSE_FRUIT_TROPICAL, KEY_FRUIT_TROPICAL);
        put(CHECK_NOSE_FRUIT_MELON, KEY_FRUIT_MELON);
        put(CHECK_NOSE_FRUIT_CHARACTER_RIPE, KEY_FRUIT_CHARACTER_RIPE);
        put(CHECK_NOSE_FRUIT_CHARACTER_FRESH, KEY_FRUIT_CHARACTER_FRESH);
        put(CHECK_NOSE_FRUIT_CHARACTER_TART, KEY_FRUIT_CHARACTER_TART);
        put(CHECK_NOSE_FRUIT_CHARACTER_BAKED, KEY_FRUIT_CHARACTER_BAKED);
        put(CHECK_NOSE_FRUIT_CHARACTER_STEWED, KEY_FRUIT_CHARACTER_STEWED);
        put(CHECK_NOSE_FRUIT_CHARACTER_DRIED, KEY_FRUIT_CHARACTER_DRIED);
        put(CHECK_NOSE_FRUIT_CHARACTER_DESICCATED, KEY_FRUIT_CHARACTER_DESICCATED);
        put(CHECK_NOSE_FRUIT_CHARACTER_BRUISED, KEY_FRUIT_CHARACTER_BRUISED);
        put(CHECK_NOSE_FRUIT_CHARACTER_JAMMY, KEY_FRUIT_CHARACTER_JAMMY);
        put(CHECK_NOSE_NON_FRUIT_FLORAL, KEY_NON_FRUIT_FLORAL);
        put(CHECK_NOSE_NON_FRUIT_HERBAL, KEY_NON_FRUIT_HERBAL);
        put(CHECK_NOSE_NON_FRUIT_VEGETAL, KEY_NON_FRUIT_VEGETAL);
        put(CHECK_NOSE_NON_FRUIT_SPICE, KEY_NON_FRUIT_SPICE);
        put(CHECK_NOSE_NON_FRUIT_ANIMAL, KEY_NON_FRUIT_ANIMAL);
        put(CHECK_NOSE_NON_FRUIT_BARN, KEY_NON_FRUIT_BARN);
        put(CHECK_NOSE_NON_FRUIT_PETROL, KEY_NON_FRUIT_PETROL);
        put(CHECK_NOSE_NON_FRUIT_FERMENTATION, KEY_NON_FRUIT_FERMENTATION);
        put(CHECK_NOSE_EARTH_FOREST_FLOOR, KEY_EARTH_FOREST_FLOOR);
        put(CHECK_NOSE_EARTH_COMPOST, KEY_EARTH_COMPOST);
        put(CHECK_NOSE_EARTH_MUSHROOMS, KEY_EARTH_MUSHROOMS);
        put(CHECK_NOSE_EARTH_POTTING_SOIL, KEY_EARTH_POTTING_SOIL);
        put(CHECK_NOSE_MINERAL_MINERAL, KEY_MINERAL_MINERAL);
        put(CHECK_NOSE_MINERAL_WET_STONE, KEY_MINERAL_WET_STONE);
        put(CHECK_NOSE_MINERAL_LIMESTONE, KEY_MINERAL_LIMESTONE);
        put(CHECK_NOSE_MINERAL_CHALK, KEY_MINERAL_CHALK);
        put(CHECK_NOSE_MINERAL_SLATE, KEY_MINERAL_SLATE);
        put(CHECK_NOSE_MINERAL_FLINT, KEY_MINERAL_FLINT);
        put(CHECK_PALATE_FRUIT_RED, KEY_FRUIT_RED);
        put(CHECK_PALATE_FRUIT_BLUE, KEY_FRUIT_BLUE);
        put(CHECK_PALATE_FRUIT_BLACK, KEY_FRUIT_BLACK);
        put(CHECK_PALATE_FRUIT_CITRUS, KEY_FRUIT_CITRUS);
        put(CHECK_PALATE_FRUIT_APPLE_PEAR, KEY_FRUIT_APPLE_PEAR);
        put(CHECK_PALATE_FRUIT_STONE_PIT, KEY_FRUIT_STONE_PIT);
        put(CHECK_PALATE_FRUIT_TROPICAL, KEY_FRUIT_TROPICAL);
        put(CHECK_PALATE_FRUIT_MELON, KEY_FRUIT_MELON);
        put(CHECK_PALATE_FRUIT_CHARACTER_RIPE, KEY_FRUIT_CHARACTER_RIPE);
        put(CHECK_PALATE_FRUIT_CHARACTER_FRESH, KEY_FRUIT_CHARACTER_FRESH);
        put(CHECK_PALATE_FRUIT_CHARACTER_TART, KEY_FRUIT_CHARACTER_TART);
        put(CHECK_PALATE_FRUIT_CHARACTER_BAKED, KEY_FRUIT_CHARACTER_BAKED);
        put(CHECK_PALATE_FRUIT_CHARACTER_STEWED, KEY_FRUIT_CHARACTER_STEWED);
        put(CHECK_PALATE_FRUIT_CHARACTER_DRIED, KEY_FRUIT_CHARACTER_DRIED);
        put(CHECK_PALATE_FRUIT_CHARACTER_DESICCATED, KEY_FRUIT_CHARACTER_DESICCATED);
        put(CHECK_PALATE_FRUIT_CHARACTER_BRUISED, KEY_FRUIT_CHARACTER_BRUISED);
        put(CHECK_PALATE_FRUIT_CHARACTER_JAMMY, KEY_FRUIT_CHARACTER_JAMMY);
        put(CHECK_PALATE_NON_FRUIT_FLORAL, KEY_NON_FRUIT_FLORAL);
        put(CHECK_PALATE_NON_FRUIT_HERBAL, KEY_NON_FRUIT_HERBAL);
        put(CHECK_PALATE_NON_FRUIT_VEGETAL, KEY_NON_FRUIT_VEGETAL);
        put(CHECK_PALATE_NON_FRUIT_SPICE, KEY_NON_FRUIT_SPICE);
        put(CHECK_PALATE_NON_FRUIT_ANIMAL, KEY_NON_FRUIT_ANIMAL);
        put(CHECK_PALATE_NON_FRUIT_BARN, KEY_NON_FRUIT_BARN);
        put(CHECK_PALATE_NON_FRUIT_PETROL, KEY_NON_FRUIT_PETROL);
        put(CHECK_PALATE_NON_FRUIT_FERMENTATION, KEY_NON_FRUIT_FERMENTATION);
        put(CHECK_PALATE_EARTH_FOREST_FLOOR, KEY_EARTH_FOREST_FLOOR);
        put(CHECK_PALATE_EARTH_COMPOST, KEY_EARTH_COMPOST);
        put(CHECK_PALATE_EARTH_MUSHROOMS, KEY_EARTH_MUSHROOMS);
        put(CHECK_PALATE_EARTH_POTTING_SOIL, KEY_EARTH_POTTING_SOIL);
        put(CHECK_PALATE_MINERAL_MINERAL, KEY_MINERAL_MINERAL);
        put(CHECK_PALATE_MINERAL_WET_STONE, KEY_MINERAL_WET_STONE);
        put(CHECK_PALATE_MINERAL_LIMESTONE, KEY_MINERAL_LIMESTONE);
        put(CHECK_PALATE_MINERAL_CHALK, KEY_MINERAL_CHALK);
        put(CHECK_PALATE_MINERAL_SLATE, KEY_MINERAL_SLATE);
        put(CHECK_PALATE_MINERAL_FLINT, KEY_MINERAL_FLINT);
        put(RADIO_CLARITY_CLEAR, KEY_CLARITY_CLEAR);
        put(RADIO_CLARITY_HAZY, KEY_CLARITY_HAZY);
        put(RADIO_CLARITY_TURBID, KEY_CLARITY_TURBID);
        put(RADIO_CONCENTRATION_PALE, KEY_CONCENTRATION_PALE);
        put(RADIO_CONCENTRATION_MEDIUM, KEY_CONCENTRATION_MEDIUM);
        put(RADIO_CONCENTRATION_DEEP, KEY_CONCENTRATION_DEEP);
        put(RADIO_COLOR_PURPLE, KEY_COLOR_PURPLE);
        put(RADIO_COLOR_RUBY, KEY_COLOR_RUBY);
        put(RADIO_COLOR_GARNET, KEY_COLOR_GARNET);
        put(RADIO_COLOR_STRAW, KEY_COLOR_STRAW);
        put(RADIO_COLOR_YELLOW, KEY_COLOR_YELLOW);
        put(RADIO_COLOR_GOLD, KEY_COLOR_GOLD);
        put(RADIO_SECONDARY_COLOR_ORANGE, KEY_SECONDARY_COLOR_ORANGE);
        put(RADIO_SECONDARY_COLOR_BLUE, KEY_SECONDARY_COLOR_BLUE);
        put(RADIO_SECONDARY_COLOR_RUBY, KEY_SECONDARY_COLOR_RUBY);
        put(RADIO_SECONDARY_COLOR_GARNET, KEY_SECONDARY_COLOR_GARNET);
        put(RADIO_SECONDARY_COLOR_BROWN, KEY_SECONDARY_COLOR_BROWN);
        put(RADIO_SECONDARY_COLOR_SILVER, KEY_SECONDARY_COLOR_SILVER);
        put(RADIO_SECONDARY_COLOR_GREEN, KEY_SECONDARY_COLOR_GREEN);
        put(RADIO_SECONDARY_COLOR_COPPER, KEY_SECONDARY_COLOR_COPPER);
        put(RADIO_RIM_VARIATION_YES, KEY_RIM_VARIATION_YES);
        put(RADIO_RIM_VARIATION_NO, KEY_RIM_VARIATION_NO);
        put(RADIO_STAIN_NONE, KEY_STAINING_NONE);
        put(RADIO_STAIN_LIGHT, KEY_STAINING_LIGHT);
        put(RADIO_STAIN_MEDIUM, KEY_STAINING_MEDIUM);
        put(RADIO_STAIN_HEAVY, KEY_STAINING_HEAVY);
        put(RADIO_TEARING_LIGHT, KEY_TEARING_LIGHT);
        put(RADIO_TEARING_MEDIUM, KEY_TEARING_MEDIUM);
        put(RADIO_TEARING_HEAVY, KEY_TEARING_HEAVY);
        put(RADIO_GAS_EVIDENCE_YES, KEY_GAS_EVIDENCE_YES);
        put(RADIO_GAS_EVIDENCE_NO, KEY_GAS_EVIDENCE_NO);
        put(RADIO_INTENSITY_DELICATE, KEY_INTENSITY_DELICATE);
        put(RADIO_INTENSITY_MODERATE, KEY_INTENSITY_MODERATE);
        put(RADIO_INTENSITY_POWERFUL, KEY_INTENSITY_POWERFUL);
        put(RADIO_AGE_ASSESSMENT_YOUTHFUL, KEY_AGE_ASSESSMENT_YOUTHFUL);
        put(RADIO_AGE_ASSESSMENT_DEVELOPING, KEY_AGE_ASSESSMENT_DEVELOPING);
        put(RADIO_AGE_ASSESSMENT_VINOUS, KEY_AGE_ASSESSMENT_VINOUS);
        put(RADIO_NOSE_WOOD_OLD, KEY_WOOD_OLD);
        put(RADIO_NOSE_WOOD_NEW, KEY_WOOD_NEW);
        put(RADIO_NOSE_WOOD_LARGE, KEY_WOOD_LARGE);
        put(RADIO_NOSE_WOOD_SMALL, KEY_WOOD_SMALL);
        put(RADIO_NOSE_WOOD_FRENCH, KEY_WOOD_FRENCH);
        put(RADIO_NOSE_WOOD_AMERICAN, KEY_WOOD_AMERICAN);
        put(RADIO_SWEETNESS_BONE_DRY, KEY_SWEETNESS_BONE_DRY);
        put(RADIO_SWEETNESS_DRY, KEY_SWEETNESS_DRY);
        put(RADIO_SWEETNESS_OFF_DRY, KEY_SWEETNESS_OFF_DRY);
        put(RADIO_SWEETNESS_MEDIUM_SWEET, KEY_SWEETNESS_MEDIUM_SWEET);
        put(RADIO_SWEETNESS_SWEET, KEY_SWEETNESS_SWEET);
        put(RADIO_SWEETNESS_LUSCIOUSLY_SWEET, KEY_SWEETNESS_LUSCIOUSLY_SWEET);
        put(RADIO_PALATE_WOOD_OLD, KEY_WOOD_OLD);
        put(RADIO_PALATE_WOOD_NEW, KEY_WOOD_NEW);
        put(RADIO_PALATE_WOOD_LARGE, KEY_WOOD_LARGE);
        put(RADIO_PALATE_WOOD_SMALL, KEY_WOOD_SMALL);
        put(RADIO_PALATE_WOOD_FRENCH, KEY_WOOD_FRENCH);
        put(RADIO_PALATE_WOOD_AMERICAN, KEY_WOOD_AMERICAN);
        put(RADIO_PHENOLIC_BITTER_YES, KEY_PHENOLIC_BITTER_YES);
        put(RADIO_PHENOLIC_BITTER_NO, KEY_PHENOLIC_BITTER_NO);
        put(RADIO_TANNIN_LOW, KEY_TANNIN_LOW);
        put(RADIO_TANNIN_MED_MINUS, KEY_TANNIN_MEDIUM_MINUS);
        put(RADIO_TANNIN_MED, KEY_TANNIN_MEDIUM);
        put(RADIO_TANNIN_MED_PLUS, KEY_TANNIN_MEDIUM_PLUS);
        put(RADIO_TANNIN_HIGH, KEY_TANNIN_HIGH);
        put(RADIO_ACID_LOW, KEY_ACID_LOW);
        put(RADIO_ACID_MED_MINUS, KEY_ACID_MEDIUM_MINUS);
        put(RADIO_ACID_MED, KEY_ACID_MEDIUM);
        put(RADIO_ACID_MED_PLUS, KEY_ACID_MEDIUM_PLUS);
        put(RADIO_ACID_HIGH, KEY_ACID_HIGH);
        put(RADIO_ALCOHOL_LOW, KEY_ALCOHOL_LOW);
        put(RADIO_ALCOHOL_MED_MINUS, KEY_ALCOHOL_MEDIUM_MINUS);
        put(RADIO_ALCOHOL_MED, KEY_ALCOHOL_MEDIUM);
        put(RADIO_ALCOHOL_MED_PLUS, KEY_ALCOHOL_MEDIUM_PLUS);
        put(RADIO_ALCOHOL_HIGH, KEY_ALCOHOL_HIGH);
        put(RADIO_BODY_LIGHT, KEY_BODY_LIGHT);
        put(RADIO_BODY_MEDIUM, KEY_BODY_MEDIUM);
        put(RADIO_BODY_FULL, KEY_BODY_FULL);
        put(RADIO_TEXTURE_CREAMY, KEY_TEXTURE_CREAMY);
        put(RADIO_TEXTURE_ROUND, KEY_TEXTURE_ROUND);
        put(RADIO_TEXTURE_LEAN, KEY_TEXTURE_LEAN);
        put(RADIO_BALANCED_YES, KEY_BALANCED_YES);
        put(RADIO_BALANCED_NO, KEY_BALANCED_NO);
        put(RADIO_FINISH_SHORT, KEY_FINISH_SHORT);
        put(RADIO_FINISH_MED_MINUS, KEY_FINISH_MEDIUM_MINUS);
        put(RADIO_FINISH_MED, KEY_FINISH_MEDIUM);
        put(RADIO_FINISH_MED_PLUS, KEY_FINISH_MEDIUM_PLUS);
        put(RADIO_FINISH_LONG, KEY_FINISH_LONG);
        put(RADIO_COMPLEXITY_LOW, KEY_COMPLEXITY_LOW);
        put(RADIO_COMPLEXITY_MED_MINUS, KEY_COMPLEXITY_MEDIUM_MINUS);
        put(RADIO_COMPLEXITY_MED, KEY_COMPLEXITY_MEDIUM);
        put(RADIO_COMPLEXITY_MED_PLUS, KEY_COMPLEXITY_MEDIUM_PLUS);
        put(RADIO_COMPLEXITY_HIGH, KEY_COMPLEXITY_HIGH);
    }};

    private final GrapeResult mContext;
    private static DatabaseReference mDatabaseReference;

    public GrapeScore(GrapeResult context, Boolean isRedWine) {
        mContext = context;

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        if (isRedWine) {
            mDatabaseReference = mDatabase.getReference(DB_RED_DESC_PATH);
        } else {
            mDatabaseReference = mDatabase.getReference(DB_WHITE_DESC_PATH);
        }
    }

    @Override
    protected void onPreExecute() {
        mContext.isScoring(true);
    }

    @Override
    protected Void doInBackground(SparseIntArray... formSelections) {
        // Convert our form collection keys to our database keys so that they can be compared.
        HashMap<String, Integer> descriptorsMap = formToDbFormat(formSelections[0]);
        // New empty map to hold scores.
        HashMap<String, Integer> varietyScoresMap = new HashMap<>();

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Iterate through our database of descriptors for each wine variety and
                // add point(s) for score each time there is a characteristic match.

                // Here we iterate through each wine variety in our database.
                for (DataSnapshot varietyRecord : dataSnapshot.getChildren()) {
                    String varietyId = varietyRecord.getKey();
                    int currentVarietyScore = 0;
                    // Here we iterate through each descriptor of the variety.
                    for (DataSnapshot varietyDescriptor : varietyRecord.getChildren()) {
                        if (descriptorsMap.containsKey(varietyDescriptor.getKey())) {
                            // Increment its score each time there is a match.
                            Object varietyDescriptorValue = varietyDescriptor.getValue();
                            if (varietyDescriptorValue instanceof Integer
                                    && (Integer) varietyDescriptorValue > 1) {
                                // Two points for key indicator of variety (value was a 2 or higher)
                                currentVarietyScore += 2;
                            } else {
                                // On point for regular indicator
                                currentVarietyScore++;
                            }
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

    @Override
    protected void onPostExecute(Void result) {
        mContext.isScoring(false);
    }

    private static HashMap<String, Integer> formToDbFormat(SparseIntArray wineProperties) {

        HashMap<String, Integer> convertedData = new HashMap<>();

        for (int i = 0; i < wineProperties.size(); i++) {
            int key = wineProperties.keyAt(i);
            int value = wineProperties.get(key);

            convertedData.put(FormToDbConversionMap.get(key), value);
        }

        return convertedData;
    }
}
