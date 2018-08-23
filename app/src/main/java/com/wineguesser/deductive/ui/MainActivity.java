package com.wineguesser.deductive.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wineguesser.deductive.R;
import com.wineguesser.deductive.repository.RepoKeyContract;

public class MainActivity extends AppCompatActivity implements DeductionFormContract,
        RepoKeyContract {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonRedWine(View view) {
        Intent intent = new Intent(this, DeductionFormActivity.class);
        intent.putExtra(IS_RED_WINE, RED_WINE);
        startActivity(intent);
    }

    public void buttonWhiteWine(View view) {
        Intent intent = new Intent(this, DeductionFormActivity.class);
        startActivity(intent);
    }
}
