package com.nverno.deductivewinetaster.util;

import com.nverno.deductivewinetaster.R;

public interface RedWineContract {


    String CURRENT_PAGE = "CURRENT_PAGE";

    int NOT_SET = 0;
    int NOT_CHECKED = -1;

    String CLARITY = "CLARITY";
    int CLARITY_CLEAR = R.id.radio_clarity_clear;
    int CLARITY_HAZY = R.id.radio_clarity_hazy;
    int CLARITY_TURBID = R.id.radio_clarity_turbid;

    String CONCENTRATION = "CONCENTRATION";
    int CONCENTRATION_PALE = R.id.radio_concentration_pale;
    int CONCENTRATION_MEDIUM = R.id.radio_concentration_medium;
    int CONCENTRATION_DEEP = R.id.radio_concentration_deep;

    String COLOR = "COLOR";
    int COLOR_PURPLE = R.id.radio_color_purple;
    int COLOR_RUBY = R.id.radio_color_ruby;
    int COLOR_RED = R.id.radio_color_red;
    int COLOR_GARNET = R.id.radio_color_garnet;
    int COLOR_WATER_WHITE = R.id.radio_color_white;
    int COLOR_STRAW = R.id.radio_color_straw;
    int COLOR_YELLOW = R.id.radio_color_yellow;
    int COLOR_GOLD = R.id.radio_color_gold;

    String SECONDARY_COLOR = "SECONDARY_COLOR";
    int SECONDARY_COLOR_ORANGE = R.id.radio_secondary_color_orange;
    int SECONDARY_COLOR_BLUE = R.id.radio_secondary_color_blue;
    int SECONDARY_COLOR_RUBY = R.id.radio_secondary_color_ruby;
    int SECONDARY_COLOR_GARNET = R.id.radio_secondary_color_garnet;
    int SECONDARY_COLOR_BROWN = R.id.radio_secondary_color_brown;
    int SECONDARY_COLOR_SILVER = R.id.radio_secondary_color_silver;
    int SECONDARY_COLOR_GREEN = R.id.radio_secondary_color_green;
    int SECONDARY_COLOR_COPPER = R.id.radio_secondary_color_copper;

    String RIM_VARIATION = "RIM_VARIATION";

    String EXTRACT_STAINING = "EXTRACT_STAINING";

    String TEARING = "TEARING";

    String GAS_EVIDENCE = "GAS_EVIDENCE";


}
