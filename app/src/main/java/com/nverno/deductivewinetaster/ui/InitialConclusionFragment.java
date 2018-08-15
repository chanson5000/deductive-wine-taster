package com.nverno.deductivewinetaster.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
    private DatabaseReference mDataReference;

    private List<String> countries = new ArrayList<>();

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
        } else {
            mSharedPreferences = mFragmentActivity
                    .getSharedPreferences(WHITE_WINE_FORM_PREFERENCES, Context.MODE_PRIVATE);
        }
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();


        mDataReference = mDatabase.child("deductive-wine-taster").child("lists").child("countries");

        mDataReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot country : dataSnapshot.getChildren()) {
                    countries.add(country.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;
        MultiAutoCompleteTextView countryText;
        String[] testCountries = new String[]{
                "Belgium", "France", "Italy", "Germany"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(mFragmentActivity,
                android.R.layout.simple_dropdown_item_1line, countries);


        rootView = inflater.inflate(R.layout.fragment_initial_conclusion,
                container, false);

        countryText = rootView.findViewById(R.id.multiText_initial_countries);
        countryText.setAdapter(adapter);
        countryText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        setUiState();
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
