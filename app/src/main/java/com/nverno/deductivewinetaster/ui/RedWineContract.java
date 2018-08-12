package com.nverno.deductivewinetaster.ui;

import com.nverno.deductivewinetaster.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface RedWineContract {

    String CURRENT_PAGE = "CURRENT_PAGE";
    int RED_SIGHT_PAGE = 0;
    int RED_NOSE_PAGE = 1;
    int RED_PALATE_PAGE = 2;

    String RED_SIGHT_PAGE_TITLE = "Red Wine - Sight";
    String RED_NOSE_PAGE_TITLE = "Red Wine - Nose";
    String RED_PALATE_PAGE_TITLE = "Red Wine - Palate";

    String RED_WINE_FORM_PREFERENCES = "RED_WINE_FORM_PREFERENCES";

    int NOT_CHECKED = 0;
    int NONE_SELECTED = -1;

    int CLARITY = R.id.radio_group_clarity;
    int CONCENTRATION = R.id.radio_group_concentration;
    int COLOR = R.id.radio_group_color_redwine;
    int SECONDARY_COLOR = R.id.radio_group_colorsecondary_redwine;
    int RIM_VARIATION = R.id.radio_group_rimvariation;
    int EXTRACT_STAINING = R.id.radio_group_extractstain_redwine;
    int TEARING = R.id.radio_group_tearing;
    int GAS_EVIDENCE = R.id.radio_group_gasevidence;
    int FAULTY_TCA = R.id.check_faulty_tca;
    int FAULTY_HYDROGEN_SULFIDE = R.id.check_faulty_hydrogen_sulfide;
    int FAULTY_VOLATILE_ACIDITY = R.id.check_faulty_volatile_acidity;
    int FAULTY_ETHYL_ACETATE = R.id.check_faulty_ethyl_acetate;
    int FAULTY_BRETT = R.id.check_faulty_brett;
    int FAULTY_OXIDIZATION = R.id.check_faulty_oxidization;
    int FAULTY_OTHER = R.id.check_faulty_other;
    int NOSE_INTENSITY = R.id.radio_group_nose_intensity;
    int NOSE_AGE_ASSESSMENT = R.id.radio_group_nose_age_assessment;

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
    // TODO: Add the rest of the views from above.

    List<Integer> AllRadioGroups = new ArrayList<>(Arrays.asList(CLARITY, CONCENTRATION, COLOR,
            SECONDARY_COLOR, RIM_VARIATION, EXTRACT_STAINING, TEARING, GAS_EVIDENCE, NOSE_INTENSITY,
            NOSE_AGE_ASSESSMENT, NOSE_WOOD_OLD_VS_NEW, NOSE_WOOD_LARGE_VS_SMALL,
            NOSE_WOOD_FRENCH_VS_AMERICAN, PALATE_SWEETNESS, PALATE_WOOD_OLD_VS_NEW,
            PALATE_WOOD_LARGE_VS_SMALL, PALATE_WOOD_FRENCH_VS_AMERICAN));

    List<Integer> AllSwitches = new ArrayList<>(Arrays.asList(NOSE_WOOD, PALATE_WOOD));

    List<Integer> AllCheckBoxes = new ArrayList<>(Arrays.asList(FAULTY_TCA, FAULTY_HYDROGEN_SULFIDE,
            FAULTY_VOLATILE_ACIDITY, FAULTY_ETHYL_ACETATE, FAULTY_BRETT, FAULTY_OXIDIZATION,
            FAULTY_OTHER, NOSE_FRUIT_RED, NOSE_FRUIT_BLACK, NOSE_FRUIT_BLUE,
            NOSE_FRUIT_CHARACTER_RIPE, NOSE_FRUIT_CHARACTER_FRESH, NOSE_FRUIT_CHARACTER_TART,
            NOSE_FRUIT_CHARACTER_BAKED, NOSE_FRUIT_CHARACTER_STEWED, NOSE_FRUIT_CHARACTER_DRIED,
            NOSE_FRUIT_CHARACTER_DESICATTED, NOSE_FRUIT_CHARACTER_BRUISED,
            NOSE_FRUIT_CHARACTER_JAMMY, NOSE_NON_FRUIT_FLORAL, NOSE_NON_FRUIT_VEGETAL,
            NOSE_NON_FRUIT_HERBAL, NOSE_NON_FRUIT_SPICE, NOSE_NON_FRUIT_ANIMAL, NOSE_NON_FRUIT_BARN,
            NOSE_NON_FRUIT_PETROL, NOSE_NON_FRUIT_FERMENTATION, NOSE_EARTH_FOREST_FLOOR,
            NOSE_EARTH_COMPOST,NOSE_EARTH_MUSHROOMS, NOSE_EARTH_POTTING_SOIL, NOSE_MINERAL_MINERAL,
            NOSE_MINERAL_WET_STONE, NOSE_MINERAL_LIMESTONE, NOSE_MINERAL_CHALK, NOSE_MINERAL_SLATE,
            NOSE_MINERAL_FLINT, PALATE_FRUIT_RED, PALATE_FRUIT_BLACK, PALATE_FRUIT_BLUE,
            PALATE_FRUIT_CHARACTER_RIPE, PALATE_FRUIT_CHARACTER_FRESH, PALATE_FRUIT_CHARACTER_TART,
            PALATE_FRUIT_CHARACTER_BAKED, PALATE_FRUIT_CHARACTER_STEWED,
            PALATE_FRUIT_CHARACTER_DRIED, PALATE_FRUIT_CHARACTER_DESICATTED,
            PALATE_FRUIT_CHARACTER_BRUISED, PALATE_FRUIT_CHARACTER_JAMMY, PALATE_NON_FRUIT_FLORAL,
            PALATE_NON_FRUIT_VEGETAL, PALATE_NON_FRUIT_HERBAL, PALATE_NON_FRUIT_SPICE,
            PALATE_NON_FRUIT_ANIMAL, PALATE_NON_FRUIT_BARN, PALATE_NON_FRUIT_PETROL,
            PALATE_NON_FRUIT_FERMENTATION, PALATE_EARTH_FOREST_FLOOR, PALATE_EARTH_COMPOST,
            PALATE_EARTH_MUSHROOMS, PALATE_EARTH_POTTING_SOIL, PALATE_MINERAL_MINERAL,
            PALATE_MINERAL_WET_STONE, PALATE_MINERAL_LIMESTONE, PALATE_MINERAL_CHALK,
            PALATE_MINERAL_SLATE, PALATE_MINERAL_FLINT));

    List<Integer> redSightViews = new ArrayList<>(Arrays.asList(CLARITY, CONCENTRATION, COLOR,
            SECONDARY_COLOR, RIM_VARIATION, EXTRACT_STAINING, TEARING, GAS_EVIDENCE));

    List<Integer> redNoseViews = new ArrayList<>(Arrays.asList(FAULTY_TCA, FAULTY_HYDROGEN_SULFIDE,
            FAULTY_HYDROGEN_SULFIDE, FAULTY_VOLATILE_ACIDITY, FAULTY_ETHYL_ACETATE, FAULTY_BRETT,
            FAULTY_OXIDIZATION, FAULTY_OTHER, NOSE_INTENSITY, NOSE_AGE_ASSESSMENT, NOSE_FRUIT_RED,
            NOSE_FRUIT_BLACK, NOSE_FRUIT_BLUE, NOSE_FRUIT_CHARACTER_RIPE, NOSE_FRUIT_CHARACTER_FRESH,
            NOSE_FRUIT_CHARACTER_TART, NOSE_FRUIT_CHARACTER_BAKED, NOSE_FRUIT_CHARACTER_STEWED,
            NOSE_FRUIT_CHARACTER_DRIED, NOSE_FRUIT_CHARACTER_DESICATTED,
            NOSE_FRUIT_CHARACTER_BRUISED, NOSE_FRUIT_CHARACTER_JAMMY, NOSE_NON_FRUIT_FLORAL,
            NOSE_NON_FRUIT_VEGETAL, NOSE_NON_FRUIT_HERBAL, NOSE_NON_FRUIT_SPICE,
            NOSE_NON_FRUIT_ANIMAL, NOSE_NON_FRUIT_BARN, NOSE_NON_FRUIT_PETROL,
            NOSE_NON_FRUIT_FERMENTATION, NOSE_EARTH_FOREST_FLOOR, NOSE_EARTH_COMPOST,
            NOSE_EARTH_MUSHROOMS, NOSE_EARTH_POTTING_SOIL, NOSE_MINERAL_MINERAL,
            NOSE_MINERAL_WET_STONE, NOSE_MINERAL_LIMESTONE, NOSE_MINERAL_CHALK, NOSE_MINERAL_SLATE,
            NOSE_MINERAL_FLINT, NOSE_WOOD, NOSE_WOOD_OLD_VS_NEW, NOSE_WOOD_LARGE_VS_SMALL,
            NOSE_WOOD_FRENCH_VS_AMERICAN));

    List<Integer> redPalateViews = new ArrayList<>(Arrays.asList(PALATE_SWEETNESS, PALATE_FRUIT_RED,
            PALATE_FRUIT_BLACK, PALATE_FRUIT_BLUE, PALATE_FRUIT_CHARACTER_RIPE,
            PALATE_FRUIT_CHARACTER_FRESH, PALATE_FRUIT_CHARACTER_TART, PALATE_FRUIT_CHARACTER_BAKED,
            PALATE_FRUIT_CHARACTER_STEWED, PALATE_FRUIT_CHARACTER_DRIED,
            PALATE_FRUIT_CHARACTER_DESICATTED, PALATE_FRUIT_CHARACTER_BRUISED,
            PALATE_FRUIT_CHARACTER_JAMMY, PALATE_NON_FRUIT_FLORAL, PALATE_NON_FRUIT_VEGETAL,
            PALATE_NON_FRUIT_HERBAL, PALATE_NON_FRUIT_SPICE, PALATE_NON_FRUIT_ANIMAL,
            PALATE_NON_FRUIT_BARN, PALATE_NON_FRUIT_PETROL, PALATE_NON_FRUIT_FERMENTATION,
            PALATE_EARTH_FOREST_FLOOR, PALATE_EARTH_COMPOST, PALATE_EARTH_MUSHROOMS,
            PALATE_EARTH_POTTING_SOIL, PALATE_MINERAL_MINERAL, PALATE_MINERAL_WET_STONE,
            PALATE_MINERAL_LIMESTONE, PALATE_MINERAL_CHALK, PALATE_MINERAL_SLATE,
            PALATE_MINERAL_FLINT, PALATE_WOOD, PALATE_WOOD_OLD_VS_NEW, PALATE_WOOD_LARGE_VS_SMALL,
            PALATE_WOOD_FRENCH_VS_AMERICAN));

    // May or may not want these for later.
//    int CLARITY_CLEAR = R.id.radio_clarity_clear;
//    int CLARITY_HAZY = R.id.radio_clarity_hazy;
//    int CLARITY_TURBID = R.id.radio_clarity_turbid;
//    int CONCENTRATION_PALE = R.id.radio_concentration_pale;
//    int CONCENTRATION_MEDIUM = R.id.radio_concentration_medium;
//    int CONCENTRATION_DEEP = R.id.radio_concentration_deep;
//    int COLOR_PURPLE = R.id.radio_color_purple;
//    int COLOR_RUBY = R.id.radio_color_ruby;
//    int COLOR_RED = R.id.radio_color_red;
//    int COLOR_GARNET = R.id.radio_color_garnet;
//    int COLOR_WATER_WHITE = R.id.radio_color_white;
//    int COLOR_STRAW = R.id.radio_color_straw;
//    int COLOR_YELLOW = R.id.radio_color_yellow;
//    int COLOR_GOLD = R.id.radio_color_gold;
//    int SECONDARY_COLOR_ORANGE = R.id.radio_secondary_color_orange;
//    int SECONDARY_COLOR_BLUE = R.id.radio_secondary_color_blue;
//    int SECONDARY_COLOR_RUBY = R.id.radio_secondary_color_ruby;
//    int SECONDARY_COLOR_GARNET = R.id.radio_secondary_color_garnet;
//    int SECONDARY_COLOR_BROWN = R.id.radio_secondary_color_brown;
//    int SECONDARY_COLOR_SILVER = R.id.radio_secondary_color_silver;
//    int SECONDARY_COLOR_GREEN = R.id.radio_secondary_color_green;
//    int SECONDARY_COLOR_COPPER = R.id.radio_secondary_color_copper;
}
