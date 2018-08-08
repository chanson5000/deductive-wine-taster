package com.nverno.deductivewinetaster.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nverno.deductivewinetaster.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonRedWine(View view) {
        Intent intent = new Intent(this, RedDeductionFormActivity.class);
        startActivity(intent);
    }

    public void buttonWhiteWine(View view) {
        Intent intent = new Intent(this, RedDeductionFormActivity.class);
        // TODO: Launch white wine deduction activity.
    }
}
