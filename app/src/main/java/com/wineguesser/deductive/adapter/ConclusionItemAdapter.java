package com.wineguesser.deductive.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

    public ConclusionItemAdapter(Context context) {
        mContext = context;
    }

    class ConclusionItemAdapterViewHolder extends RecyclerView.ViewHolder {

        final TextView mActualGrape;
        final TextView mUserGrape;
        final TextView mAppGrape;

        ConclusionItemAdapterViewHolder(View view) {
            super(view);

            mActualGrape = view.findViewById(R.id.textView_actual_grape);
            mUserGrape = view.findViewById(R.id.textView_user_conclusion_grape);
            mAppGrape = view.findViewById(R.id.textView_app_conclusion_grape);
        }
    }

    @NonNull
    public ConclusionItemAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int layoutId = R.layout.item_grape_conclusion_record;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(layoutId, viewGroup, false);

        return new ConclusionItemAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConclusionItemAdapterViewHolder conclusionItemAdapterViewHolder, int position) {

        conclusionItemAdapterViewHolder.mActualGrape.setText(mConclusionRecords.get(position).getActualVariety());
        conclusionItemAdapterViewHolder.mUserGrape.setText(mConclusionRecords.get(position).getUserConclusionGrape());
        conclusionItemAdapterViewHolder.mAppGrape.setText(mConclusionRecords.get(position).getAppConclusionGrape());
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
