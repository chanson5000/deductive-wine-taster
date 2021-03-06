package com.wineguesser.deductive.view.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wineguesser.deductive.R;
import com.wineguesser.deductive.model.ConclusionRecord;


import java.util.List;

public class ConclusionItemAdapter extends RecyclerView.Adapter<ConclusionItemAdapter.ConclusionItemAdapterViewHolder> {

    private final Context mContext;
    private List<ConclusionRecord> mConclusionRecords;
    private final HistoryItemOnClickHandler mClickHandler;

    public interface HistoryItemOnClickHandler {
        void onHistoryItemClick(ConclusionRecord conclusionRecord);
    }

    public ConclusionItemAdapter(Context context, HistoryItemOnClickHandler clickHandler) {
        mContext = context;
        mClickHandler = clickHandler;
    }

    class ConclusionItemAdapterViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener {

        final TextView mActualLabel;
        final TextView mActualGrape;
        final TextView mUserGrape;
        final TextView mAppGrape;

        ConclusionItemAdapterViewHolder(View view) {
            super(view);

            mActualLabel = view.findViewById(R.id.textView_actual_label);
            mActualGrape = view.findViewById(R.id.textView_actual_variety);
            mUserGrape = view.findViewById(R.id.textView_user_conclusion_grape);
            mAppGrape = view.findViewById(R.id.textView_app_conclusion_grape);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            ConclusionRecord conclusionRecord = mConclusionRecords.get(adapterPosition);
            mClickHandler.onHistoryItemClick(conclusionRecord);
        }
    }

    @NonNull
    public ConclusionItemAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int layoutId = R.layout.item_conclusion_record;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(layoutId, viewGroup, false);
        view.setFocusable(true);

        return new ConclusionItemAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConclusionItemAdapterViewHolder conclusionItemAdapterViewHolder, int position) {

        conclusionItemAdapterViewHolder.mActualLabel.setText(mConclusionRecords.get(position).getActualLabel());

        conclusionItemAdapterViewHolder.mActualGrape.setText(mConclusionRecords.get(position).getActualVariety());
        conclusionItemAdapterViewHolder.mUserGrape.setText(mConclusionRecords.get(position).getUserConclusionVariety());
        String appConclusionVariety = mConclusionRecords.get(position).getAppConclusionVariety();
        if (appConclusionVariety == null || appConclusionVariety.isEmpty()) {
            conclusionItemAdapterViewHolder.mAppGrape.setText(R.string.no_data);
        } else {
            conclusionItemAdapterViewHolder.mAppGrape.setText(appConclusionVariety);
        }
    }

    @Override
    public int getItemCount() {
        if (mConclusionRecords == null) return 0;
        return mConclusionRecords.size();
    }

    public void setConclusionData(List<ConclusionRecord> conclusionData) {
        mConclusionRecords = conclusionData;
        notifyDataSetChanged();
    }
}
