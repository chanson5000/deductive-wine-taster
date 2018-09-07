package com.wineguesser.deductive.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wineguesser.deductive.R;
import com.wineguesser.deductive.adapter.ConclusionItemAdapter;
import com.wineguesser.deductive.databinding.ActivityHistoryBinding;
import com.wineguesser.deductive.model.ConclusionRecord;
import com.wineguesser.deductive.repository.ConclusionsRepository;
import com.wineguesser.deductive.repository.DatabaseContract;
import com.wineguesser.deductive.viewmodel.HistoryActivityViewModel;

public class HistoryActivity extends AppCompatActivity implements DatabaseContract,
ConclusionItemAdapter.HistoryItemOnClickHandler {

    private FirebaseUser mCurrentUser;
    private HistoryActivityViewModel historyActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHistoryBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_history);

        historyActivity = ViewModelProviders.of(this)
                .get(HistoryActivityViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setHistoryActivity(historyActivity);

        RecyclerView recyclerView = findViewById(R.id.conclusion_item_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        ConclusionItemAdapter conclusionAdapter = new ConclusionItemAdapter(this, this);
        recyclerView.setAdapter(conclusionAdapter);

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (mCurrentUser != null) {
            String uid = mCurrentUser.getUid();

            historyActivity.getUserConclusions(uid).observe(this, conclusionRecords -> {
                if (conclusionRecords != null && !conclusionRecords.isEmpty()) {
                    historyActivity.setNoData(false);
                    conclusionAdapter.setConclusionData(conclusionRecords);
                } else {
                    historyActivity.setNoData(true);
                    conclusionAdapter.setConclusionData(null);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_history_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear_history:
                if (mCurrentUser != null) {
                    ConclusionsRepository repository = new ConclusionsRepository();
                    repository.clearUserConclusions(mCurrentUser.getUid());
                    Toast.makeText(this, getString(R.string.history_cleared),
                            Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onHistoryItemClick(ConclusionRecord conclusionRecord) {
        Intent intent = new Intent(this, HistoryRecordActivity.class);
        conclusionRecord.setUserId(mCurrentUser.getUid());
        intent.putExtra("PARCELABLE_CONCLUSION", conclusionRecord);
        startActivity(intent);
    }
}
