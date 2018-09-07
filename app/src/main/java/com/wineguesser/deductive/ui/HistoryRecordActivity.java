package com.wineguesser.deductive.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wineguesser.deductive.R;
import com.wineguesser.deductive.databinding.ActivityHistoryRecordBinding;
import com.wineguesser.deductive.model.ConclusionRecord;
import com.wineguesser.deductive.viewmodel.HistoryRecordViewModel;


public class HistoryRecordActivity extends AppCompatActivity {

    private HistoryRecordViewModel historyRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHistoryRecordBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_history_record);

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
}
