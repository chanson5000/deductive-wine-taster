package com.wineguesser.deductive.repository;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wineguesser.deductive.model.RedWine;

import java.util.ArrayList;
import java.util.List;

import static com.wineguesser.deductive.data.FirebaseDataContract.Acidity;

public class WineReferenceRepository {

    private final DatabaseReference RED_WINE_COMPARATORS;

    public WineReferenceRepository() {
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

        RED_WINE_COMPARATORS = mDatabase.getReference("varietal/red/new world");

    }

    public void populateRedWineComparators() {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<RedWine> redWines = new ArrayList<>();

                for (DataSnapshot varietalEntry : dataSnapshot.getChildren()) {
                    RedWine redWine = new RedWine();
                    redWine.setName(varietalEntry.getKey());
                    for (DataSnapshot propertyEntry : varietalEntry.getChildren()) {
                        String propertyName = propertyEntry.getKey();
                        if (propertyName != null) {
                            switch (propertyName) {
                                case Acidity:
                                    for (DataSnapshot descriptorEntry : propertyEntry.getChildren()) {
                                        String descriptorName = descriptorEntry.getKey();
                                        int descriptorValue;
                                        if (descriptorEntry.getValue() != null) {
                                            descriptorValue = (Integer) descriptorEntry.getValue();
                                        } else {
                                            descriptorValue = 0;
                                        }
                                        if (descriptorName != null) {
                                            switch (descriptorName) {
                                                case "low":
                                                    redWine.acidity().setLow(descriptorValue);
                                                    break;
                                                case "medium minus":
                                                    redWine.acidity().setMediumMinus(descriptorValue);
                                                    break;
                                                case "medium":
                                                    redWine.acidity().setMedium(descriptorValue);
                                                    break;
                                                case "medium plus":
                                                    redWine.acidity().setMediumPlus(descriptorValue);
                                                    break;
                                                case "high":
                                                    redWine.acidity().setHigh(descriptorValue);
                                                    break;
                                                default:
                                                    break;
                                            }
                                            redWines.add(redWine);
                                        }
                                    }
                                case "alcohol":
                                    for (DataSnapshot descriptorEntry : propertyEntry.getChildren()) {
                                        String descriptorName = descriptorEntry.getKey();
                                        int descriptorValue;
                                        if (descriptorEntry.getValue() != null) {
                                            descriptorValue = (Integer) descriptorEntry.getValue();
                                        } else {
                                            descriptorValue = 0;
                                        }
                                        if (descriptorName != null) {
                                            switch (descriptorName) {
                                                case "low":
                                                    redWine.alcohol().setLow(descriptorValue);
                                                    break;
                                                case "medium minus":
                                                    redWine.alcohol().setMediumMinus(descriptorValue);
                                                    break;
                                                case "medium":
                                                    redWine.alcohol().setMedium(descriptorValue);
                                                    break;
                                                case "medium plus":
                                                    redWine.alcohol().setGetMediumPlus(descriptorValue);
                                                    break;
                                                case "high":
                                                    redWine.alcohol().setHigh(descriptorValue);
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                        redWines.add(redWine);
                                    }

                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
    }
}
