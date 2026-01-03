package com.wineguesser.deductive.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.EditText;


import com.wineguesser.deductive.R;

public class FeedbackActivity extends AppCompatActivity {

    private static final String FEEDBACK_TEXT = "FEEDBACK_TEXT";

    private String mFeedbackText;
    private EditText mEditTextFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_feedback);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(insets.left, insets.top, insets.right, insets.bottom);
            return windowInsets;
        });

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
