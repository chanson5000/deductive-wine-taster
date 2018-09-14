package com.wineguesser.deductive.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wineguesser.deductive.R;
import com.wineguesser.deductive.util.Helpers;
import com.wineguesser.deductive.view.adapter.ConclusionItemAdapter;
import com.wineguesser.deductive.databinding.ActivityHistoryBinding;
import com.wineguesser.deductive.model.ConclusionRecord;
import com.wineguesser.deductive.repository.ConclusionsRepository;
import com.wineguesser.deductive.repository.DatabaseContract;
import com.wineguesser.deductive.viewmodel.HistoryActivityViewModel;

public class HistoryActivity extends AppCompatActivity implements DatabaseContract,
        ConclusionItemAdapter.HistoryItemOnClickHandler {

    private FirebaseUser mCurrentUser;
    private HistoryActivityViewModel historyActivity;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        ActivityHistoryBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_history);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setTitle(R.string.history_activity_title);

        historyActivity = ViewModelProviders.of(this)
                .get(HistoryActivityViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setHistoryActivity(historyActivity);

        RecyclerView recyclerView = findViewById(R.id.conclusion_item_recycler_view);
        if (findViewById(R.id.is_600dp) != null) {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        ConclusionItemAdapter conclusionAdapter =
                new ConclusionItemAdapter(this, this);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setCancelable(true);
                    builder.setTitle(R.string.dialog_clear_history);
                    builder.setMessage(R.string.dialog_clear_history_ask);
                    builder.setPositiveButton(R.string.yes, (dialog, which) -> {
                        ConclusionsRepository repository = new ConclusionsRepository();
                        repository.clearUserConclusions(mCurrentUser.getUid());
                        Helpers.makeToastShort(mContext, R.string.history_cleared);
                    });

                    builder.setNegativeButton(android.R.string.cancel, (dialog, which) ->
                            Helpers.makeToastShort(mContext, R.string.cancel_history_clear));

                    AlertDialog dialog = builder.create();
                    dialog.show();
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
        intent.putExtra(Helpers.CONCLUSION_PARCEL, conclusionRecord);
        startActivity(intent);
    }
}
