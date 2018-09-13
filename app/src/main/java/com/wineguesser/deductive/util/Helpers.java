package com.wineguesser.deductive.util;

import android.content.Context;
import android.widget.Toast;

public class Helpers {

    public static int castKey(String key) { return Integer.parseInt(key); }

    public static boolean castChecked(int checkedInt) {
        return checkedInt == 1;
    }

    public static int castChecked(boolean checkedBoolean) {
        int CHECKED = 1;
        int NOT_CHECKED = 0;
        if (checkedBoolean) {
            return CHECKED;
        } else {
            return NOT_CHECKED;
        }
    }

    public static int parseEntryValue(Object value) {
        return Integer.parseInt(value.toString());
    }

    public static boolean parseChecked(Object value) {
        return castChecked(parseEntryValue(value));
    }

    public static void makeToastShort(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }
}
