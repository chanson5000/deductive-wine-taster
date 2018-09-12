package com.wineguesser.deductive.util;

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
}
