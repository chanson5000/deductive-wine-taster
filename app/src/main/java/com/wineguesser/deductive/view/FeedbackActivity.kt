package com.wineguesser.deductive.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.wineguesser.deductive.R

class FeedbackActivity : AppCompatActivity() {

    private var mFeedbackText: String? = null
    private lateinit var mEditTextFeedback: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.activity_feedback)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(insets.left, insets.top, insets.right, insets.bottom)
            windowInsets
        }

        mEditTextFeedback = findViewById(R.id.editText_feedback)

        if (savedInstanceState != null) {
            mFeedbackText = savedInstanceState.getString(FEEDBACK_TEXT)
        }
    }

    override fun onPause() {
        super.onPause()
        mFeedbackText = mEditTextFeedback.text.toString()
    }

    override fun onResume() {
        super.onResume()
        mEditTextFeedback.setText(mFeedbackText)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(FEEDBACK_TEXT, mFeedbackText)
    }

    fun onClickSubmitFeedback(view: View) {
        val email = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.feedback_email)))
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback_subject))
            putExtra(Intent.EXTRA_TEXT, mEditTextFeedback.text.toString())
            type = "message/rfc822"
        }

        startActivity(Intent.createChooser(email, getString(R.string.feedback_select_client)))
    }

    companion object {
        private const val FEEDBACK_TEXT = "FEEDBACK_TEXT"
    }
}
