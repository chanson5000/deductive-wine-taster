package com.wineguesser.deductive.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wineguesser.deductive.R;
import com.wineguesser.deductive.adapter.ConclusionItemAdapter;
import com.wineguesser.deductive.model.ConclusionRecord;
import com.wineguesser.deductive.repository.RepoKeyContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity implements RepoKeyContract {

    private ConclusionItemAdapter mConclusionAdapter;
    private static DatabaseReference mDbReferenceUserConclusions;
    private FirebaseAuth mAuth;
    private String uid;

    @BindView(R.id.conclusion_item_recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setHasFixedSize(true);
        mConclusionAdapter = new ConclusionItemAdapter(this);
        mRecyclerView.setAdapter(mConclusionAdapter);

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<ConclusionRecord> conclusionRecords = new ArrayList<>();
                for (DataSnapshot conclusionDbRecord : dataSnapshot.getChildren()) {
                    ConclusionRecord conclusionRecord = new ConclusionRecord();

                    for (DataSnapshot conclusionDbItem : conclusionDbRecord.getChildren()) {
                        String key = conclusionDbItem.getKey();
                        Object value = conclusionDbItem.getValue();
                        if (key != null && value instanceof String) {
                            switch (key) {
                                case DB_ACTUAL_GRAPE:
                                    conclusionRecord.setActualGrape((String) value);
                                    break;
                                case DB_USER_CONCLUSION_GRAPE:
                                    conclusionRecord.setUserConclusionGrape((String) value);
                                    break;
                                case DB_APP_CONCLUSION:
                                    conclusionRecord.setAppConclusionGrape((String) value);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    conclusionRecords.add(conclusionRecord);
                }
                mConclusionAdapter.setConclusionData(conclusionRecords);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            uid = user.getUid();

            mDbReferenceUserConclusions = FirebaseDatabase.getInstance().getReference()
                    .child(DB_REFERENCE_ALL_CONCLUSIONS).child(uid);

            mDbReferenceUserConclusions.addListenerForSingleValueEvent(listener);
        }
    }

}
