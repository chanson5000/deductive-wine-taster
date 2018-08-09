package com.nverno.deductivewinetaster.util;

public interface RedWineContract {
    String CURRENT_PAGE = "CURRENT_PAGE";



    String SECONDARY_COLOR = "SECONDARY_COLOR";
    String RIM_VARIATION = "RIM_VARIATION";
    String EXTRACT_STAINING = "EXTRACT_STAINING";
    String TEARING = "TEARING";
    String GAS_EVIDENCE = "GAS_EVIDENCE";

    int NOT_SET = 0;

    String CLARITY = "CLARITY";
    int CLEAR = 1;
    int HAZY = 2;
    int TURBID = 3;

    String CONCENTRATION = "CONCENTRATION";
    int PALE = 1;
    int MEDIUM = 2;
    int DEEP = 3;

    String COLOR = "COLOR";
    int WATER_WHITE = 1;
    int STRAW = 2;
    int YELLOW = 3;
    int GOLD = 4;


}
