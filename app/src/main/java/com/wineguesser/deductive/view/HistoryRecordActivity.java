package com.wineguesser.deductive.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.wineguesser.deductive.R;
import com.wineguesser.deductive.databinding.ActivityHistoryRecordBinding;
import com.wineguesser.deductive.model.ConclusionRecord;
import com.wineguesser.deductive.repository.ConclusionsRepository;
import com.wineguesser.deductive.viewmodel.HistoryRecordViewModel;


public class HistoryRecordActivity extends AppCompatActivity {

    private HistoryRecordViewModel historyRecord;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        ActivityHistoryRecordBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_history_record);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        historyRecord = ViewModelProviders.of(this)
                .get(HistoryRecordViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setHistoryRecord(historyRecord);

        Intent parentIntent = getIntent();
        if (parentIntent != null && parentIntent.hasExtra("PARCELABLE_CONCLUSION")) {
            ConclusionRecord conclusionRecord =
                    parentIntent.getParcelableExtra("PARCELABLE_CONCLUSION");
            historyRecord.setConclusionRecord(conclusionRecord);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_history_record_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_history_record:
                ConclusionsRepository repository = new ConclusionsRepository();
                ConclusionRecord conclusionRecord = historyRecord.getConclusionRecord().getValue();
                if (conclusionRecord != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setCancelable(true);
                    builder.setTitle(R.string.up_dialog_delete_conclusion_record);
                    builder.setMessage(R.string.up_dialog_confirm_delete_record);
                    builder.setPositiveButton(R.string.yes, (dialog, which) -> {
                        repository.removeConclusionRecord(conclusionRecord);
                        Toast.makeText(mContext, R.string.record_removed,
                                Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    });

                    builder.setNegativeButton(android.R.string.cancel, (dialog, which) ->
                            Toast.makeText(mContext, R.string.up_dialog_cancel_record_deletion,
                            Toast.LENGTH_SHORT).show());

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
