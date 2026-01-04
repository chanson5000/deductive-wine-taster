package com.wineguesser.deductive.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wineguesser.deductive.R
import com.wineguesser.deductive.model.ConclusionRecord

class ConclusionItemAdapter(
    private val mContext: Context,
    private val mClickHandler: HistoryItemOnClickHandler
) : RecyclerView.Adapter<ConclusionItemAdapter.ConclusionItemAdapterViewHolder>() {

    private var mConclusionRecords: List<ConclusionRecord>? = null

    interface HistoryItemOnClickHandler {
        fun onHistoryItemClick(conclusionRecord: ConclusionRecord)
    }

    inner class ConclusionItemAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val mActualLabel: TextView = view.findViewById(R.id.textView_actual_label)
        val mActualGrape: TextView = view.findViewById(R.id.textView_actual_variety)
        val mUserGrape: TextView = view.findViewById(R.id.textView_user_conclusion_grape)
        val mAppGrape: TextView = view.findViewById(R.id.textView_app_conclusion_grape)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val adapterPosition = adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                mConclusionRecords?.get(adapterPosition)?.let {
                    mClickHandler.onHistoryItemClick(it)
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ConclusionItemAdapterViewHolder {
        val layoutId = R.layout.item_conclusion_record
        val inflater = LayoutInflater.from(mContext)
        val view = inflater.inflate(layoutId, viewGroup, false)
        view.isFocusable = true
        return ConclusionItemAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConclusionItemAdapterViewHolder, position: Int) {
        val record = mConclusionRecords?.get(position) ?: return

        holder.mActualLabel.text = record.actualLabel
        holder.mActualGrape.text = record.actualVariety
        holder.mUserGrape.text = record.userConclusionVariety

        val appConclusionVariety = record.appConclusionVariety
        if (appConclusionVariety.isNullOrEmpty()) {
            holder.mAppGrape.setText(R.string.no_data)
        } else {
            holder.mAppGrape.text = appConclusionVariety
        }
    }

    override fun getItemCount(): Int {
        return mConclusionRecords?.size ?: 0
    }

    fun setConclusionData(conclusionData: List<ConclusionRecord>?) {
        mConclusionRecords = conclusionData
        notifyDataSetChanged()
    }
}
