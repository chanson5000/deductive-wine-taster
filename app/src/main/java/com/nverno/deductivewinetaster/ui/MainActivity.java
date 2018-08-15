package com.nverno.deductivewinetaster.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nverno.deductivewinetaster.R;

public class MainActivity extends AppCompatActivity implements DeductionFormContract {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonRedWine(View view) {
        Intent intent = new Intent(this, DeductionFormActivity.class);
        intent.putExtra(WINE_TYPE, RED_WINE);
        startActivity(intent);
    }

    public void buttonWhiteWine(View view) {
        Intent intent = new Intent(this, DeductionFormActivity.class);
        startActivity(intent);
    }
}
