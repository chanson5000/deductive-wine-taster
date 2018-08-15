package com.nverno.deductivewinetaster.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nverno.deductivewinetaster.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InitialConclusionFragment extends Fragment implements DeductionFormContract {

    private FragmentActivity mFragmentActivity;
    private SharedPreferences mSharedPreferences;
    private boolean mIsRedWine;
    private DatabaseReference mDatabase;

    private MultiAutoCompleteTextView mMultiAutoTextVarieties;
    private MultiAutoCompleteTextView mMultiAutoTextCountries;

    public InitialConclusionFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentActivity = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences activityPreferences =
                mFragmentActivity.getPreferences(Context.MODE_PRIVATE);

        if (activityPreferences.getString(WINE_TYPE, WHITE_WINE).equals(RED_WINE)) {
            mSharedPreferences = mFragmentActivity
                    .getSharedPreferences(RED_WINE_FORM_PREFERENCES, Context.MODE_PRIVATE);
            mIsRedWine = true;
        } else {
            mSharedPreferences = mFragmentActivity
                    .getSharedPreferences(WHITE_WINE_FORM_PREFERENCES, Context.MODE_PRIVATE);
            mIsRedWine = false;
        }

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;

        rootView = inflater.inflate(R.layout.fragment_initial_conclusion,
                container, false);

        mMultiAutoTextVarieties = rootView.findViewById(R.id.multiText_initial_varieties);
        mMultiAutoTextCountries = rootView.findViewById(R.id.multiText_initial_countries);

        if (mIsRedWine) {
            mDatabase.child("lists").child("varieties").child("red").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    List<String> varieties = new ArrayList<>();
                    for (DataSnapshot entry : dataSnapshot.getChildren()) {
                        varieties.add(entry.getKey());
                    }
                    setMultiAutoTextVarieties(varieties);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            mDatabase.child("lists").child("varieties").child("white").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    List<String> varieties = new ArrayList<>();
                    for (DataSnapshot entry : dataSnapshot.getChildren()) {
                        varieties.add(entry.getKey());
                    }
                    setMultiAutoTextVarieties(varieties);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        mDatabase.child("lists").child("countries").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> countries = new ArrayList<>();
                for (DataSnapshot entry : dataSnapshot.getChildren()) {
                    countries.add(entry.getKey());
                }
                setMultiAutoTextCountries(countries);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(databaseError.getDetails(), mFragmentActivity.getClass().getName());
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        setUiState();
    }

    private void setMultiAutoTextCountries(List<String> countries) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line, countries);
        mMultiAutoTextCountries.setAdapter(adapter);
        mMultiAutoTextCountries.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    private void setMultiAutoTextVarieties(List<String> varieties) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line, varieties);
        mMultiAutoTextVarieties.setAdapter(adapter);
        mMultiAutoTextVarieties.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    private int castKey(String key) {
        return Integer.parseInt(key);
    }

    private int parseEntryValue(Object value) {
        return Integer.parseInt(value.toString());
    }

    private void setUiState() {
        Map<String, ?> allEntries = mSharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            int view = castKey(entry.getKey());

            if (initialConclusionViews.contains(view)) {
                if (AllRadioGroups.contains(view)) {
                    ((RadioGroup) mFragmentActivity.findViewById(view))
                            .check(parseEntryValue(entry.getValue()));
                } else if (AllAutoMultiText.contains(view)) {
                    ((MultiAutoCompleteTextView) mFragmentActivity.findViewById(view))
                            .setText(entry.getValue().toString());
                }
            }
        }
    }
}
