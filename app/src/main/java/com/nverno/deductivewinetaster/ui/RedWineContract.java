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

    String RED_WINE_FORM_PREFERENCES = "RED_WINE_FORM_PREFERENCES";

    String SCROLL_X_POS = "SCROLL_X_POS";
    String SCROLL_Y_POS = "SCROLL_Y_POS";

    int NOT_SET = 0;
    int NOT_CHECKED = 0;
    int CHECKED = 1;
    int NONE_SELECTED = -1;

//    String CLARITY = "CLARITY";
    int CLARITY = R.id.radio_group_clarity;
    int CLARITY_CLEAR = R.id.radio_clarity_clear;
    int CLARITY_HAZY = R.id.radio_clarity_hazy;
    int CLARITY_TURBID = R.id.radio_clarity_turbid;

    int CONCENTRATION = R.id.radio_group_concentration;
    int CONCENTRATION_PALE = R.id.radio_concentration_pale;
    int CONCENTRATION_MEDIUM = R.id.radio_concentration_medium;
    int CONCENTRATION_DEEP = R.id.radio_concentration_deep;

    int COLOR = R.id.radio_group_color_redwine;
    int COLOR_PURPLE = R.id.radio_color_purple;
    int COLOR_RUBY = R.id.radio_color_ruby;
    int COLOR_RED = R.id.radio_color_red;
    int COLOR_GARNET = R.id.radio_color_garnet;
    int COLOR_WATER_WHITE = R.id.radio_color_white;
    int COLOR_STRAW = R.id.radio_color_straw;
    int COLOR_YELLOW = R.id.radio_color_yellow;
    int COLOR_GOLD = R.id.radio_color_gold;

    int SECONDARY_COLOR = R.id.radio_group_colorsecondary_redwine;
    int SECONDARY_COLOR_ORANGE = R.id.radio_secondary_color_orange;
    int SECONDARY_COLOR_BLUE = R.id.radio_secondary_color_blue;
    int SECONDARY_COLOR_RUBY = R.id.radio_secondary_color_ruby;
    int SECONDARY_COLOR_GARNET = R.id.radio_secondary_color_garnet;
    int SECONDARY_COLOR_BROWN = R.id.radio_secondary_color_brown;
    int SECONDARY_COLOR_SILVER = R.id.radio_secondary_color_silver;
    int SECONDARY_COLOR_GREEN = R.id.radio_secondary_color_green;
    int SECONDARY_COLOR_COPPER = R.id.radio_secondary_color_copper;

    int RIM_VARIATION = R.id.radio_group_rimvariation;

    int EXTRACT_STAINING = R.id.radio_group_extractstain_redwine;

    int TEARING = R.id.radio_group_tearing;

    int GAS_EVIDENCE = R.id.radio_group_tearing;

    String FAULTY = "FAULTY";
//    String FAULTY_TCA = "FAULTY_TCA";
    int FAULTY_TCA = R.id.check_faulty_tca;
//    String FAULTY_HYDROGEN_SULFIDE = "FAULTY_HYDROGEN_SULFIDE";
    int FAULTY_HYDROGEN_SULFIDE = R.id.check_faulty_hydrogen_sulfide;
//    String FAULTY_VOLATILE_ACIDITY = "FAULTY_VOLATILE_ACIDITY";
    int FAULTY_VOLATILE_ACIDITY = R.id.check_faulty_volatile_acidity;
//    String FAULTY_ETHYL_ACETATE = "FAULTY_ETHYL_ACETATE";
    int FAULTY_ETHYL_ACETATE = R.id.check_faulty_ethyl_acetate;
//    String FAULTY_BRETT = "FAULTY_BRETT";
    int FAULTY_BRETT = R.id.check_faulty_brett;
//    String FAULTY_OXIDIZATION = "FAULTY_OXIDIZATION";
    int FAULTY_OXIDIZATION = R.id.check_faulty_oxidization;
//    String FAULTY_OTHER = "FAULTY_OTHER";
    int FAULTY_OTHER = R.id.check_faulty_other;

    int NOSE_INTENSITY = R.id.radio_group_nose_intensity;

    int NOSE_AGE_ASSESSMENT = R.id.radio_group_nose_age_assessment;
    String NOSE_FRUIT = "NOSE_FRUIT";
    int NOSE_FRUIT_RED = R.id.check_nose_fruit_red;
    int NOSE_FRUIT_BLACK = R.id.check_nose_fruit_black;
    int NOSE_FRUIT_BLUE = R.id.check_nose_fruit_blue;
    int NOSE_FRUIT_CHARACTER_RIPE = R.id.check_nose_fruit_character_ripe;
    int NOSE_FRUIT_CHARACTER_FRESH = R.id.check_nose_fruit_character_fresh;
    int NOSE_FRUIT_CHARACTER_TART = R.id.check_nose_fruit_character_tart;
    int NOSE_FRUIT_CHARACTER_BAKED = R.id.check_nose_fruit_character_baked;
    int NOSE_FRUIT_CHARACTER_STEWED = R.id.check_nose_fruit_character_stewed;
    int NOSE_FRUIT_CHARACTER_DRIED = R.id.check_nose_fruit_character_dried;
    int NOSE_FRUIT_CHARACTER_DESICATTED = R.id.check_nose_fruit_character_desiccated;
    int NOSE_FRUIT_CHARACTER_BRUISED = R.id.check_nose_fruit_character_bruised;
    int NOSE_FRUIT_CHARACTER_JAMMY = R.id.check_nose_fruit_character_jammy;
    int NOSE_NON_FRUIT_FLORAL = R.id.check_nose_non_fruit_floral;
    int NOSE_NON_FRUIT_VEGETAL = R.id.check_nose_non_fruit_vegetal;
    int NOSE_NON_FRUIT_HERBAL = R.id.check_nose_non_fruit_herbal;
    int NOSE_NON_FRUIT_SPICE = R.id.check_nose_non_fruit_spice;
    int NOSE_NON_FRUIT_ANIMAL = R.id.check_nose_non_fruit_animal;
    int NOSE_NON_FRUIT_BARN = R.id.check_nose_non_fruit_barn;
    int NOSE_NON_FRUIT_PETROL = R.id.check_nose_non_fruit_petrol;
    int NOSE_NON_FRUIT_FERMENTATION = R.id.check_nose_non_fruit_fermentation;
    int NOSE_EARTH_FOREST_FLOOR = R.id.check_nose_earth_forest_floor;
    int NOSE_EARTH_COMPOST = R.id.check_nose_earth_compost;
    int NOSE_EARTH_MUSHROOMS = R.id.check_nose_earth_mushrooms;
    int NOSE_EARTH_POTTING_SOIL = R.id.check_nose_earth_potting_soil;
    int NOSE_MINERAL_MINERAL = R.id.check_nose_mineral_mineral;
    int NOSE_MINERAL_WET_STONE = R.id.check_nose_mineral_wet_stone;
    int NOSE_MINERAL_LIMESTONE = R.id.check_nose_mineral_limestone;
    int NOSE_MINERAL_CHALK = R.id.check_nose_mineral_chalk;
    int NOSE_MINERAL_SLATE = R.id.check_nose_mineral_slate;
    int NOSE_MINERAL_FLINT = R.id.check_nose_mineral_flint;
    int NOSE_WOOD = R.id.switch_nose_wood;
    int NOSE_WOOD_OLD_VS_NEW = R.id.radio_group_wood_old_vs_new;
    int NOSE_WOOD_LARGE_VS_SMALL = R.id.radio_group_wood_large_vs_small;
    int NOSE_WOOD_FRENCH_VS_AMERICAN = R.id.radio_group_wood_french_vs_american;

    int PALATE_SWEETNESS = R.id.radio_group_palate_sweetness;
    int PALATE_FRUIT_RED = R.id.check_palate_fruit_red;
    int PALATE_FRUIT_BLACK = R.id.check_palate_fruit_black;
    int PALATE_FRUIT_BLUE = R.id.check_palate_fruit_blue;
    int PALATE_FRUIT_CHARACTER_RIPE = R.id.check_palate_fruit_character_ripe;
    int PALATE_FRUIT_CHARACTER_FRESH = R.id.check_palate_fruit_character_fresh;
    int PALATE_FRUIT_CHARACTER_TART = R.id.check_palate_fruit_character_tart;
    int PALATE_FRUIT_CHARACTER_BAKED = R.id.check_palate_fruit_character_baked;
    int PALATE_FRUIT_CHARACTER_STEWED = R.id.check_palate_fruit_character_stewed;
    int PALATE_FRUIT_CHARACTER_DRIED = R.id.check_palate_fruit_character_dried;
    int PALATE_FRUIT_CHARACTER_DESICATTED = R.id.check_palate_fruit_character_desiccated;
    int PALATE_FRUIT_CHARACTER_BRUISED = R.id.check_palate_fruit_character_bruised;
    int PALATE_FRUIT_CHARACTER_JAMMY = R.id.check_palate_fruit_character_jammy;
    int PALATE_NON_FRUIT_FLORAL = R.id.check_palate_non_fruit_floral;
    int PALATE_NON_FRUIT_VEGETAL = R.id.check_palate_non_fruit_vegetal;
    int PALATE_NON_FRUIT_HERBAL = R.id.check_palate_non_fruit_herbal;
    int PALATE_NON_FRUIT_SPICE = R.id.check_palate_non_fruit_spice;
    int PALATE_NON_FRUIT_ANIMAL = R.id.check_palate_non_fruit_animal;
    int PALATE_NON_FRUIT_BARN = R.id.check_palate_non_fruit_barn;
    int PALATE_NON_FRUIT_PETROL = R.id.check_palate_non_fruit_petrol;
    int PALATE_NON_FRUIT_FERMENTATION = R.id.check_palate_non_fruit_fermentation;
    int PALATE_EARTH_FOREST_FLOOR = R.id.check_palate_earth_forest_floor;
    int PALATE_EARTH_COMPOST = R.id.check_palate_earth_compost;
    int PALATE_EARTH_MUSHROOMS = R.id.check_palate_earth_mushrooms;
    int PALATE_EARTH_POTTING_SOIL = R.id.check_palate_earth_potting_soil;
    int PALATE_MINERAL_MINERAL = R.id.check_palate_mineral_mineral;
    int PALATE_MINERAL_WET_STONE = R.id.check_palate_mineral_wet_stone;
    int PALATE_MINERAL_LIMESTONE = R.id.check_palate_mineral_limestone;
    int PALATE_MINERAL_CHALK = R.id.check_palate_mineral_chalk;
    int PALATE_MINERAL_SLATE = R.id.check_palate_mineral_slate;
    int PALATE_MINERAL_FLINT = R.id.check_palate_mineral_flint;
    int PALATE_WOOD = R.id.switch_palate_wood;
    int PALATE_WOOD_OLD_VS_NEW = R.id.radio_group_wood_old_vs_new;
    int PALATE_WOOD_LARGE_VS_SMALL = R.id.radio_group_wood_large_vs_small;
    int PALATE_WOOD_FRENCH_VS_AMERICAN = R.id.radio_group_wood_french_vs_american;

}
