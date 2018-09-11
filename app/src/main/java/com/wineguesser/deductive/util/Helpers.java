package com.wineguesser.deductive.util;

public class Helpers {

    private static int CHECKED = 1;
    private static int NOT_CHECKED = 0;

    public static int castKey(String key) { return Integer.parseInt(key); }

    public static boolean castChecked(int checkedInt) {
        return checkedInt == 1;
    }

    public static int castChecked(boolean checkedBoolean) {
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
