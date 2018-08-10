package com.nverno.deductivewinetaster.ui;

import com.nverno.deductivewinetaster.R;

public interface RedWineContract {


    String CURRENT_PAGE = "CURRENT_PAGE";
    int RED_SIGHT_PAGE = 0;
    int RED_NOSE_PAGE = 1;
    int RED_PALATE_PAGE = 2;

    String RED_SIGHT_PAGE_TITLE = "Red Wine - Sight";
    String RED_NOSE_PAGE_TITLE = "Red Wine - Nose";
    String RED_PALATE_PAGE_TITLE = "Red Wine - Palate";

    int NOT_SET = 0;
    int NOT_CHECKED = 0;
    int CHECKED = 1;
    int NONE_SELECTED = -1;

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

    String FAULTY = "FAULTY";
    String FAULTY_TCA = "FAULTY_TCA";
    int CHECK_FAULTY_TCA = R.id.check_faulty_tca;
    String FAULTY_HYDROGEN_SULFIDE = "FAULTY_HYDROGEN_SULFIDE";
    int CHECK_FAULTY_HYDROGEN_SULFIDE = R.id.check_faulty_hydrogen_sulfide;
    String FAULTY_VOLATILE_ACIDITY = "FAULTY_VOLATILE_ACIDITY";
    int CHECK_FAULTY_VOLATILE_ACIDITY = R.id.check_faulty_volatile_acidity;
    String FAULTY_ETHYL_ACETATE = "FAULTY_ETHYL_ACETATE";
    int CHECK_FAULTY_ETHYL_ACETATE = R.id.check_faulty_ethyl_acetate;
    String FAULTY_BRETT = "FAULTY_BRETT";
    int CHECK_FAULTY_BRETT = R.id.check_faulty_brett;
    String FAULTY_OXIDIZATION = "FAULTY_OXIDIZATION";
    int CHECK_FAULTY_OXIDIZATION = R.id.check_faulty_oxidization;
    String FAULTY_OTHER = "FAULTY_OTHER";
    int CHECK_FAULTY_OTHER = R.id.check_faulty_other;

    String NOSE_INTENSITY = "NOSE_INTENSITY";

    String NOSE_AGE_ASSESSMENT = "NOSE_AGE_ASSESSMENT";
    String NOSE_FRUIT = "NOSE_FRUIT";
    String NOSE_FRUIT_RED = "NOSE_FRUIT_RED";
    String NOSE_FRUIT_BLACK = "NOSE_FRUIT_BLACK";
    String NOSE_FRUIT_BLUE = "NOSE_FRUIT_BLUE";
    String NOSE_FRUIT_CHARACTER_RIPE = "NOSE_FRUIT_CHARACTER_RIPE";
    String NOSE_FRUIT_CHARACTER_FRESH = "NOSE_FRUIT_CHARACTER_FRESH";
    String NOSE_FRUIT_CHARACTER_TART = "NOSE_FRUIT_CHARACTER_TART";
    String NOSE_FRUIT_CHARACTER_BAKED = "NOSE_FRUIT_CHARACTER_BAKED";
    String NOSE_FRUIT_CHARACTER_STEWED = "NOSE_FRUIT_CHARACTER_STEWED";
    String NOSE_FRUIT_CHARACTER_DRIED = "NOSE_FRUIT_CHARACTER_DRIED";
    String NOSE_FRUIT_CHARACTER_DESICATTED = "NOSE_FRUIT_CHARACTER_DESICCATED";
    String NOSE_FRUIT_CHARACTER_BRUISED = "NOSE_FRUIT_CHARACTER_BRUISED";
    String NOSE_FRUIT_CHARACTER_JAMMY = "NOSE_FRUIT_CHARACTER_JAMMY";
    String NOSE_NON_FRUIT_FLORAL = "NOSE_NON_FRUIT_FLORAL";
    String NOSE_NON_FRUIT_VEGETAL = "NOSE_NON_FRUIT_VEGETAL";
    String NOSE_NON_FRUIT_HERBAL = "NOSE_NON_FRUIT_HERBAL";
    String NOSE_NON_FRUIT_SPICE = "NOSE_NON_FRUIT_SPICE";
    String NOSE_NON_FRUIT_ANIMAL = "NOSE_NON_FRUIT_ANIMAL";
    String NOSE_NON_FRUIT_BARN = "NOSE_NON_FRUIT_BARN";
    String NOSE_NON_FRUIT_PETROL = "NOSE_NON_FRUIT_PETROL";
    String NOSE_NON_FRUIT_FERMENTATION = "NOSE_NON_FRUIT_FERMENTATION";
    String NOSE_EARTH_FOREST_FLOOR = "NOSE_EARTH_FOREST_FLOOR";
    String NOSE_EARTH_COMPOST = "NOSE_EARTH_COMPOST";
    String NOSE_EARTH_MUSHROOMS = "NOSE_EARTH_MUSHROOMS";
    String NOSE_EARTH_POTTING_SOIL = "NOSE_EARTH_POTTING_SOIL";
    String NOSE_MINERAL_MINERAL = "NOSE_MINERAL_MINERAL";
    String NOSE_MINERAL_WET_STONE = "NOSE_MINERAL_WET_STONE";
    String NOSE_MINERAL_LIMESTONE = "NOSE_MINERAL_LIMESTONE";
    String NOSE_MINERAL_CHALK = "NOSE_MINERAL_CHALK";
    String NOSE_MINERAL_SLATE = "NOSE_MINERAL_SLATE";
    String NOSE_MINERAL_FLINT = "NOSE_MINERAL_FLINT";
    String NOSE_WOOD = "NOSE_WOOD";
    String NOSE_WOOD_OLD_VS_NEW = "NOSE_WOOD_OLD_VS_NEW";
    String NOSE_WOOD_LARGE_VS_SMALL = "NOSE_WOOD_LARGE_VS_SMALL";
    String NOSE_WOOD_FRENCH_VS_AMERICAN = "NOSE_WOOD_FRENCH_VS_AMERICAN";





}
