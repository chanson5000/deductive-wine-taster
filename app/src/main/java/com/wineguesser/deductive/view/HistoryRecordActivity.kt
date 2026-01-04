package com.wineguesser.deductive.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.Insets

import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.wineguesser.deductive.R
import com.wineguesser.deductive.databinding.ActivityHistoryRecordBinding
import com.wineguesser.deductive.model.ConclusionRecord
import com.wineguesser.deductive.repository.ConclusionsRepository
import com.wineguesser.deductive.util.Helpers
import com.wineguesser.deductive.view.*
import com.wineguesser.deductive.viewmodel.HistoryRecordViewModel

class HistoryRecordActivity : AppCompatActivity() {

    private lateinit var historyRecord: HistoryRecordViewModel
    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        // SplashScreen.installSplashScreen(this)
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        mContext = this
        val binding: ActivityHistoryRecordBinding = DataBindingUtil.setContentView(this, R.layout.activity_history_record)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(insets.left, insets.top, insets.right, insets.bottom)
            windowInsets
        }

        title = getString(R.string.history_record_activity_title)

        historyRecord = ViewModelProvider(this).get(HistoryRecordViewModel::class.java)
        binding.lifecycleOwner = this
        binding.historyRecord = historyRecord

        val parentIntent = intent
        if (parentIntent != null && parentIntent.hasExtra(Helpers.CONCLUSION_PARCEL)) {
            val conclusionRecord = parentIntent.getParcelableExtra<ConclusionRecord>(Helpers.CONCLUSION_PARCEL)
            if (conclusionRecord != null) {
                historyRecord.setConclusionRecord(conclusionRecord)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_history_record_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_history_record -> {
                val repository = ConclusionsRepository()
                val conclusionRecord = historyRecord.conclusionRecord.value
                if (conclusionRecord != null) {
                    val builder = AlertDialog.Builder(mContext)
                    builder.setCancelable(true)
                    builder.setTitle(R.string.up_dialog_delete_conclusion_record)
                    builder.setMessage(R.string.up_dialog_confirm_delete_record)
                    builder.setPositiveButton(R.string.yes) { _, _ ->
                        conclusionRecord.userId?.let { uid ->
                            repository.removeConclusionRecord(uid, conclusionRecord)
                        }
                        Helpers.makeToastShort(mContext, R.string.record_removed)
                        onBackPressed()
                    }

                    builder.setNegativeButton(android.R.string.cancel) { _, _ ->
                        Helpers.makeToastShort(mContext, R.string.up_dialog_cancel_record_deletion)
                    }

                    val dialog = builder.create()
                    dialog.show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
