package com.wineguesser.deductive.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;


import com.wineguesser.deductive.R;

public class FeedbackActivity extends AppCompatActivity {

    private static final String FEEDBACK_TEXT = "FEEDBACK_TEXT";

    private String mFeedbackText;
    private EditText mEditTextFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEditTextFeedback = findViewById(R.id.editText_feedback);

        if (savedInstanceState != null) {
            mFeedbackText = savedInstanceState.getString(FEEDBACK_TEXT);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mFeedbackText = mEditTextFeedback.getText().toString();
    }

    @Override
    public void onResume() {
        super.onResume();
        mEditTextFeedback.setText(mFeedbackText);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString(FEEDBACK_TEXT, mFeedbackText);
    }

    @SuppressWarnings("unused")
    public void onClickSubmitFeedback(View view) {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.feedback_email)});
        email.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback_subject));
        email.putExtra(Intent.EXTRA_TEXT, mEditTextFeedback.getText().toString());
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, getString(R.string.feedback_select_client)));
    }
}
