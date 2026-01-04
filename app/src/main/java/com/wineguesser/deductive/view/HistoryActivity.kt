package com.wineguesser.deductive.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.wineguesser.deductive.R
import com.wineguesser.deductive.databinding.ActivityHistoryBinding
import com.wineguesser.deductive.model.ConclusionRecord
import com.wineguesser.deductive.repository.ConclusionsRepository
import com.wineguesser.deductive.util.Helpers
import com.wineguesser.deductive.view.adapter.ConclusionItemAdapter
import com.wineguesser.deductive.viewmodel.HistoryActivityViewModel

class HistoryActivity : AppCompatActivity(), ConclusionItemAdapter.HistoryItemOnClickHandler {

    private val mCurrentUser = FirebaseAuth.getInstance().currentUser
    private lateinit var historyActivity: HistoryActivityViewModel
    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        // SplashScreen.installSplashScreen(this)
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        mContext = this
        val binding: ActivityHistoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_history)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(insets.left, insets.top, insets.right, insets.bottom)
            windowInsets
        }

        title = getString(R.string.history_activity_title)

        historyActivity = ViewModelProvider(this).get(HistoryActivityViewModel::class.java)
        binding.lifecycleOwner = this
        binding.historyActivity = historyActivity

        val recyclerView = findViewById<RecyclerView>(R.id.conclusion_item_recycler_view)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels / displayMetrics.density

        val columnCount: Int = when {
            width > 800 -> 3
            width > 600 -> 2
            else -> 1
        }

        if (columnCount > 1) {
            recyclerView.layoutManager = StaggeredGridLayoutManager(columnCount, StaggeredGridLayoutManager.VERTICAL)
        } else {
            recyclerView.layoutManager = LinearLayoutManager(this)
        }

        val conclusionAdapter = ConclusionItemAdapter(this, this)
        recyclerView.adapter = conclusionAdapter

        mCurrentUser?.let { user ->
            historyActivity.getUserConclusions(user.uid).observe(this) { conclusionRecords ->
                if (!conclusionRecords.isNullOrEmpty()) {
                    historyActivity.setNoData(false)
                    conclusionAdapter.setConclusionData(conclusionRecords)
                } else {
                    historyActivity.setNoData(true)
                    conclusionAdapter.setConclusionData(null)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_history_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.clear_history -> {
                mCurrentUser?.let { user ->
                    val builder = AlertDialog.Builder(mContext)
                    builder.setCancelable(true)
                    builder.setTitle(R.string.dialog_clear_history)
                    builder.setMessage(R.string.dialog_clear_history_ask)
                    builder.setPositiveButton(R.string.yes) { _, _ ->
                        val repository = ConclusionsRepository()
                        repository.clearUserConclusions(user.uid)
                        Helpers.makeToastShort(mContext, R.string.history_cleared)
                    }

                    builder.setNegativeButton(android.R.string.cancel) { _, _ ->
                        Helpers.makeToastShort(mContext, R.string.cancel_history_clear)
                    }

                    val dialog = builder.create()
                    dialog.show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onHistoryItemClick(conclusionRecord: ConclusionRecord) {
        val intent = Intent(this, HistoryRecordActivity::class.java)
        mCurrentUser?.let {
            conclusionRecord.userId = it.uid
        }
        intent.putExtra(Helpers.CONCLUSION_PARCEL, conclusionRecord)
        startActivity(intent)
    }
}
