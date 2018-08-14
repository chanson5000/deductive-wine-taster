package com.nverno.deductivewinetaster.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.widget.RadioGroup;

public class WineRadioGroup extends RadioGroup {
    public WineRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        SharedPreferences sharedPreferences = context.getSharedPreferences("red_wine", Context.MODE_PRIVATE);


    }


}
