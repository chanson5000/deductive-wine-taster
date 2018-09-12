package com.wineguesser.deductive.view;

import com.wineguesser.deductive.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface DeductionFormContract {

    String APP_VARIETY_GUESS_ID = "APP_VARIETY_GUESS_ID";
    String IS_RED_WINE = "IS_RED_WINE";
    Boolean TRUE = true;
    Boolean FALSE = false;
    String USER_CONCLUSION_VARIETY = "USER_CONCLUSION_VARIETY";
    String USER_CONCLUSION_COUNTRY = "USER_CONCLUSION_COUNTRY";
    String USER_CONCLUSION_REGION = "USER_CONCLUSION_REGION";
    String USER_CONCLUSION_QUALITY = "USER_CONCLUSION_QUALITY";
    String USER_CONCLUSION_VINTAGE = "USER_CONCLUSION_VINTAGE";

    String CURRENT_PAGE_RED = "CURRENT_PAGE_RED";
    String CURRENT_PAGE_WHITE = "CURRENT_PAGE_WHITE";

    String RED_SIGHT_Y_SCROLL = "RED_SIGHT_Y_SCROLL";
    String WHITE_SIGHT_Y_SCROLL = "WHITE_SIGHT_Y_SCROLL";
    String RED_NOSE_Y_SCROLL = "RED_NOSE_Y_SCROLL";
    String WHITE_NOSE_Y_SCROLL = "WHITE_NOSE_Y_SCROLL";
    String RED_PALATE_A_Y_SCROLL = "RED_PALATE_A_Y_SCROLL";
    String WHITE_PALATE_A_Y_SCROLL = "WHITE_PALATE_A_Y_SCROLL";
    String RED_PALATE_B_Y_SCROLL = "RED_PALATE_B_Y_SCROLL";
    String WHITE_PALATE_B_Y_SCROLL = "WHITE_PALATE_B_Y_SCROLL";
    String RED_INITIAL_Y_SCROLL = "RED_INITIAL_Y_SCROLL";
    String WHITE_INITIAL_Y_SCROLL = "WHITE_INITIAL_Y_SCROLL";
    String RED_FINAL_Y_SCROLL = "RED_FINAL_Y_SCROLL";
    String WHITE_FINAL_Y_SCROLL = "WHITE_FINAL_Y_SCROLL";

    int NUM_PAGES = 6;
    int SIGHT_PAGE = 0;
    int NOSE_PAGE = 1;
    int PALATE_PAGE_A = 2;
    int PALATE_PAGE_B = 3;
    int INITIAL_CONCLUSION_PAGE = 4;
    int FINAL_CONCLUSION_PAGE = 5;

    String SIGHT_PAGE_TITLE = "Sight";
    String NOSE_PAGE_TITLE = "Nose";
    String PALATE_PAGE_TITLE = "Palate";

    String INITIAL_CONCLUSION_PAGE_TITLE = "Initial Conclusion";
    String FINAL_CONCLUSION_PAGE_TITLE = "Final Conclusion";

    String RED_WINE_FORM_PREFERENCES = "RED_WINE_FORM_PREFERENCES";
    String WHITE_WINE_FORM_PREFERENCES = "WHITE_WINE_FORM_PREFERENCES";

    int CHECKED = 1;
    int NOT_CHECKED = 0;
    int NONE_SELECTED = -1;

    // These strings I will not want translated so I am not using strings.xml.
    // If it does come to that we can use the snippet below.
    // Resources.getSystem().getString(android.R.string.string_name)

    String Albarino = "Albarino";
    String Cabernet_Franc = "Cabernet Franc";
    String Cabernet_Sauvignon = "Cabernet Sauvignon";
    String Chardonnay = "Chardonnay";
    String Chenin_Blanc = "Chenin Blanc";
    String Gamay = "Gamay";
    String Gewurztraminer = "Gewurztraminer";
    String Grenache = "Grenache";
    String Malbec = "Malbec";
    String Merlot = "Merlot";
    String Nebbiolo = "Nebbiolo";
    String Pinot_Gris = "Pinot Gris";
    String Pinot_Noir = "Pinot Noir";
    String Riesling = "Riesling";
    String Sangiovese = "Sangiovese";
    String Sauvignon_Blanc = "Sauvignon Blanc";
    String Syrah = "Syrah";
    String Tempranillo = "Tempranillo";
    String Torrontes = "Torrontes";
    String Viognier = "Viognier";
    String Zinfandel = "Zinfandel";

    String Argentina = "Argentina";
    String Australia = "Australia";
    String Chile = "Chile";
    String France = "France";
    String Germany = "Germany";
    String Italy = "Italy";
    String New_Zealand = "New Zealand";
    String Spain = "Spain";
    String United_States = "United States";

    String Alsace = "Alsace";
    String Burgundy = "Burgundy";
    String Bordeaux = "Bordeaux";
    String Loire_Valley = "Loire Valley";
    String South_Australia = "South Australia";
    String Western_Australia = "Western Australia";
    String Central_Valley = "Central Valley";
    String California = "California";
    String Rhone_Valley = "Rhone Valley";
    String Victoria = "Victoria";
    String Piedmont = "Piedmont";
    String Oregon = "Oregon";
    String South_Island = "South Island";
    String North_Island = "North Island";
    String Tuscany = "Tuscany";

    List<String> AllRegions = new ArrayList<>(Arrays.asList(Alsace, Burgundy, Bordeaux, Loire_Valley, South_Australia,
            Western_Australia, Central_Valley, California, Rhone_Valley, Victoria, Piedmont, Oregon,
            South_Island, North_Island, Tuscany));

    List<String> RegionsFrance = new ArrayList<>(Arrays.asList(Alsace, Burgundy, Bordeaux, Loire_Valley, Rhone_Valley));
    List<String> RegionsUnitedStates = new ArrayList<>(Arrays.asList(California, Oregon));
    List<String> RegionsAustralia = new ArrayList<>(Arrays.asList(South_Australia, Western_Australia, Victoria));
    List<String> RegionsNewZealand = new ArrayList<>(Arrays.asList(North_Island, South_Island));
    List<String> RegionsItaly = new ArrayList<>(Arrays.asList(Piedmont, Tuscany));
    List<String> RegionsChile = Collections.singletonList(Central_Valley);

    List<String> AllCountries = new ArrayList<>(Arrays.asList(Argentina, Australia, Chile, France, Germany,
            Italy, New_Zealand, Spain, United_States));

    List<String> NewWorldCountries = new ArrayList<>(Arrays.asList(Argentina, Australia, Chile, New_Zealand,
            United_States));

    List<String> OldWorldCountries = new ArrayList<>(Arrays.asList(France, Germany, Italy, Spain));

    List<String> AllVarieties = new ArrayList<>(Arrays.asList(Albarino, Cabernet_Franc, Cabernet_Sauvignon,
            Chardonnay, Chenin_Blanc, Gamay, Gewurztraminer, Grenache, Malbec, Merlot,
            Nebbiolo, Pinot_Gris, Pinot_Noir, Riesling, Sangiovese, Sauvignon_Blanc,
            Syrah, Tempranillo, Torrontes, Viognier, Zinfandel));

    List<String> RedVarieties = new ArrayList<>(Arrays.asList(Cabernet_Sauvignon, Gamay,
            Grenache, Malbec, Merlot, Nebbiolo, Pinot_Noir, Sangiovese, Syrah,
            Tempranillo, Zinfandel));

    List<String> WhiteVarieties = new ArrayList<>(Arrays.asList(Albarino, Chardonnay, Chenin_Blanc,
            Gewurztraminer, Pinot_Gris, Riesling, Sauvignon_Blanc, Torrontes, Viognier));

    List<String> ArgentinaVarieties = new ArrayList<>(Arrays.asList(Malbec, Torrontes));

    List<String> AustraliaVarieties = new ArrayList<>(Arrays.asList(Cabernet_Sauvignon, Grenache, Riesling,
            Syrah));

    List<String> ChileVarieties = new ArrayList<>(Arrays.asList(Cabernet_Sauvignon));

    List<String> FranceVarieties = new ArrayList<>(Arrays.asList(Cabernet_Franc, Cabernet_Sauvignon, Chardonnay,
            Chenin_Blanc, Gamay, Gewurztraminer, Grenache, Merlot, Pinot_Gris, Riesling,
            Sauvignon_Blanc, Syrah, Viognier));

    List<String> GermanyVarieties = new ArrayList<>(Arrays.asList(Riesling));

    List<String> ItalyVarieties = new ArrayList<>(Arrays.asList(Nebbiolo, Pinot_Gris, Sangiovese));

    List<String> NewZealandVarieties = new ArrayList<>(Arrays.asList(Pinot_Noir, Sauvignon_Blanc));

    List<String> SpainVarieties = new ArrayList<>(Arrays.asList(Albarino, Tempranillo));

    List<String> UnitedStatesVarieties = new ArrayList<>(Arrays.asList(Cabernet_Sauvignon, Chardonnay, Merlot,
            Pinot_Noir, Sauvignon_Blanc, Syrah, Zinfandel));


    int RADIO_GROUP_CLARITY = R.id.radio_group_clarity;
    int RADIO_GROUP_CONCENTRATION = R.id.radio_group_concentration;
    int RADIO_GROUP_COLOR_RED_WINE = R.id.radio_group_color_redwine;
    int RADIO_GROUP_COLOR_WHITE_WINE = R.id.radio_group_color_whitewine;
    int RADIO_GROUP_SECONDARY_COLOR_RED_WINE = R.id.radio_group_colorsecondary_redwine;
    int RADIO_GROUP_SECONDARY_COLOR_WHITE_WINE = R.id.radio_group_colorsecondary_whitewine;
    int RADIO_GROUP_RIM_VARIATION = R.id.radio_group_rimvariation;
    int RADIO_GROUP_EXTRACT_STAINING = R.id.radio_group_extractstain_redwine;
    int RADIO_GROUP_TEARING = R.id.radio_group_tearing;
    int RADIO_GROUP_GAS_EVIDENCE = R.id.radio_group_gasevidence;

    int CHECK_FAULT_TCA = R.id.check_faulty_tca;
    int CHECK_FAULT_HYDROGEN_SULFIDE = R.id.check_faulty_hydrogen_sulfide;
    int CHECK_FAULT_VOLATILE_ACIDITY = R.id.check_faulty_volatile_acidity;
    int CHECK_FAULT_ETHYL_ACETATE = R.id.check_faulty_ethyl_acetate;
    int CHECK_FAULT_BRETT = R.id.check_faulty_brett;
    int CHECK_FAULT_OXIDIZATION = R.id.check_faulty_oxidization;
    int CHECK_FAULT_OTHER = R.id.check_faulty_other;
    int RADIO_GROUP_NOSE_INTENSITY = R.id.radio_group_nose_intensity;
    int RADIO_GROUP_NOSE_AGE_ASSESSMENT = R.id.radio_group_nose_age_assessment;
    int CHECK_NOSE_FRUIT_RED = R.id.check_nose_fruit_red;
    int CHECK_NOSE_FRUIT_BLACK = R.id.check_nose_fruit_black;
    int CHECK_NOSE_FRUIT_BLUE = R.id.check_nose_fruit_blue;
    int CHECK_NOSE_FRUIT_CITRUS = R.id.check_nose_fruit_citrus;
    int CHECK_NOSE_FRUIT_APPLE_PEAR = R.id.check_nose_fruit_apple_pear;
    int CHECK_NOSE_FRUIT_STONE_PIT = R.id.check_nose_fruit_stone_pit;
    int CHECK_NOSE_FRUIT_TROPICAL = R.id.check_nose_fruit_tropical;
    int CHECK_NOSE_FRUIT_MELON = R.id.check_nose_fruit_melon;
    int CHECK_NOSE_FRUIT_CHARACTER_RIPE = R.id.check_nose_fruit_character_ripe;
    int CHECK_NOSE_FRUIT_CHARACTER_FRESH = R.id.check_nose_fruit_character_fresh;
    int CHECK_NOSE_FRUIT_CHARACTER_TART = R.id.check_nose_fruit_character_tart;
    int CHECK_NOSE_FRUIT_CHARACTER_BAKED = R.id.check_nose_fruit_character_baked;
    int CHECK_NOSE_FRUIT_CHARACTER_STEWED = R.id.check_nose_fruit_character_stewed;
    int CHECK_NOSE_FRUIT_CHARACTER_DRIED = R.id.check_nose_fruit_character_dried;
    int CHECK_NOSE_FRUIT_CHARACTER_DESICCATED = R.id.check_nose_fruit_character_desiccated;
    int CHECK_NOSE_FRUIT_CHARACTER_BRUISED = R.id.check_nose_fruit_character_bruised;
    int CHECK_NOSE_FRUIT_CHARACTER_JAMMY = R.id.check_nose_fruit_character_jammy;
    int CHECK_NOSE_NON_FRUIT_FLORAL = R.id.check_nose_non_fruit_floral;
    int CHECK_NOSE_NON_FRUIT_VEGETAL = R.id.check_nose_non_fruit_vegetal;
    int CHECK_NOSE_NON_FRUIT_HERBAL = R.id.check_nose_non_fruit_herbal;
    int CHECK_NOSE_NON_FRUIT_SPICE = R.id.check_nose_non_fruit_spice;
    int CHECK_NOSE_NON_FRUIT_ANIMAL = R.id.check_nose_non_fruit_animal;
    int CHECK_NOSE_NON_FRUIT_BARN = R.id.check_nose_non_fruit_barn;
    int CHECK_NOSE_NON_FRUIT_PETROL = R.id.check_nose_non_fruit_petrol;
    int CHECK_NOSE_NON_FRUIT_FERMENTATION = R.id.check_nose_non_fruit_fermentation;
    int CHECK_NOSE_EARTH_FOREST_FLOOR = R.id.check_nose_earth_forest_floor;
    int CHECK_NOSE_EARTH_COMPOST = R.id.check_nose_earth_compost;
    int CHECK_NOSE_EARTH_MUSHROOMS = R.id.check_nose_earth_mushrooms;
    int CHECK_NOSE_EARTH_POTTING_SOIL = R.id.check_nose_earth_potting_soil;
    int CHECK_NOSE_MINERAL_MINERAL = R.id.check_nose_mineral_mineral;
    int CHECK_NOSE_MINERAL_WET_STONE = R.id.check_nose_mineral_wet_stone;
    int CHECK_NOSE_MINERAL_LIMESTONE = R.id.check_nose_mineral_limestone;
    int CHECK_NOSE_MINERAL_CHALK = R.id.check_nose_mineral_chalk;
    int CHECK_NOSE_MINERAL_SLATE = R.id.check_nose_mineral_slate;
    int CHECK_NOSE_MINERAL_FLINT = R.id.check_nose_mineral_flint;
    int SWITCH_NOSE_WOOD = R.id.switch_nose_wood;
    int RADIO_GROUP_NOSE_WOOD_OLD_VS_NEW = R.id.radio_group_nose_wood_old_vs_new;
    int RADIO_GROUP_NOSE_WOOD_LARGE_VS_SMALL = R.id.radio_group_nose_wood_large_vs_small;
    int RADIO_GROUP_NOSE_WOOD_FRENCH_VS_AMERICAN = R.id.radio_group_nose_wood_french_vs_american;

    int RADIO_GROUP_PALATE_SWEETNESS = R.id.radio_group_palate_sweetness;
    int CHECK_PALATE_FRUIT_RED = R.id.check_palate_fruit_red;
    int CHECK_PALATE_FRUIT_BLACK = R.id.check_palate_fruit_black;
    int CHECK_PALATE_FRUIT_BLUE = R.id.check_palate_fruit_blue;
    int CHECK_PALATE_FRUIT_CITRUS = R.id.check_palate_fruit_citrus;
    int CHECK_PALATE_FRUIT_APPLE_PEAR = R.id.check_palate_fruit_apple_pear;
    int CHECK_PALATE_FRUIT_STONE_PIT = R.id.check_palate_fruit_stone_pit;
    int CHECK_PALATE_FRUIT_TROPICAL = R.id.check_palate_fruit_tropical;
    int CHECK_PALATE_FRUIT_MELON = R.id.check_palate_fruit_melon;
    int CHECK_PALATE_FRUIT_CHARACTER_RIPE = R.id.check_palate_fruit_character_ripe;
    int CHECK_PALATE_FRUIT_CHARACTER_FRESH = R.id.check_palate_fruit_character_fresh;
    int CHECK_PALATE_FRUIT_CHARACTER_TART = R.id.check_palate_fruit_character_tart;
    int CHECK_PALATE_FRUIT_CHARACTER_BAKED = R.id.check_palate_fruit_character_baked;
    int CHECK_PALATE_FRUIT_CHARACTER_STEWED = R.id.check_palate_fruit_character_stewed;
    int CHECK_PALATE_FRUIT_CHARACTER_DRIED = R.id.check_palate_fruit_character_dried;
    int CHECK_PALATE_FRUIT_CHARACTER_DESICCATED = R.id.check_palate_fruit_character_desiccated;
    int CHECK_PALATE_FRUIT_CHARACTER_BRUISED = R.id.check_palate_fruit_character_bruised;
    int CHECK_PALATE_FRUIT_CHARACTER_JAMMY = R.id.check_palate_fruit_character_jammy;
    int CHECK_PALATE_NON_FRUIT_FLORAL = R.id.check_palate_non_fruit_floral;
    int CHECK_PALATE_NON_FRUIT_VEGETAL = R.id.check_palate_non_fruit_vegetal;
    int CHECK_PALATE_NON_FRUIT_HERBAL = R.id.check_palate_non_fruit_herbal;
    int CHECK_PALATE_NON_FRUIT_SPICE = R.id.check_palate_non_fruit_spice;
    int CHECK_PALATE_NON_FRUIT_ANIMAL = R.id.check_palate_non_fruit_animal;
    int CHECK_PALATE_NON_FRUIT_BARN = R.id.check_palate_non_fruit_barn;
    int CHECK_PALATE_NON_FRUIT_PETROL = R.id.check_palate_non_fruit_petrol;
    int CHECK_PALATE_NON_FRUIT_FERMENTATION = R.id.check_palate_non_fruit_fermentation;
    int CHECK_PALATE_EARTH_FOREST_FLOOR = R.id.check_palate_earth_forest_floor;
    int CHECK_PALATE_EARTH_COMPOST = R.id.check_palate_earth_compost;
    int CHECK_PALATE_EARTH_MUSHROOMS = R.id.check_palate_earth_mushrooms;
    int CHECK_PALATE_EARTH_POTTING_SOIL = R.id.check_palate_earth_potting_soil;
    int CHECK_PALATE_MINERAL_MINERAL = R.id.check_palate_mineral_mineral;
    int CHECK_PALATE_MINERAL_WET_STONE = R.id.check_palate_mineral_wet_stone;
    int CHECK_PALATE_MINERAL_LIMESTONE = R.id.check_palate_mineral_limestone;
    int CHECK_PALATE_MINERAL_CHALK = R.id.check_palate_mineral_chalk;
    int CHECK_PALATE_MINERAL_SLATE = R.id.check_palate_mineral_slate;
    int CHECK_PALATE_MINERAL_FLINT = R.id.check_palate_mineral_flint;
    int SWITCH_PALATE_WOOD = R.id.switch_palate_wood;

    int RADIO_GROUP_PALATE_WOOD_OLD_VS_NEW = R.id.radio_group_palate_wood_old_vs_new;
    int RADIO_GROUP_PALATE_WOOD_LARGE_VS_SMALL = R.id.radio_group_palate_wood_large_vs_small;
    int RADIO_GROUP_PALATE_WOOD_FRENCH_VS_AMERICAN = R.id.radio_group_palate_french_vs_american;

    int RADIO_GROUP_PALATE_PHENOLIC_BITTER = R.id.radio_group_phenolic_bitter;
    int RADIO_GROUP_PALATE_TANNIN = R.id.radio_group_palate_tannin;
    int RADIO_GROUP_PALATE_ACID = R.id.radio_group_palate_acid;
    int RADIO_GROUP_PALATE_ALCOHOL = R.id.radio_group_palate_alcohol;
    int RADIO_GROUP_PALATE_BODY = R.id.radio_group_palate_body;
    int RADIO_GROUP_PALATE_TEXTURE = R.id.radio_group_palate_texture;
    int RADIO_GROUP_PALATE_BALANCE = R.id.radio_group_palate_balance;
    int RADIO_GROUP_PALATE_LENGTH_FINISH = R.id.radio_group_palate_length_finish;
    int RADIO_GROUP_PALATE_COMPLEXITY = R.id.radio_group_palate_complexity;

    int TEXT_MULTI_INITIAL_GRAPE_VARIETIES = R.id.multiText_initial_varieties;
    int RADIO_GROUP_INITIAL_WORLD = R.id.radio_group_initial_world;
    int RADIO_GROUP_INITIAL_CLIMATE = R.id.radio_group_initial_climate;
    int TEXT_MULTI_INITIAL_COUNTRIES = R.id.multiText_initial_countries;
    int RADIO_GROUP_INITIAL_AGE_RANGE = R.id.radio_group_initial_age;

    int RADIO_CLARITY_CLEAR = R.id.radio_clarity_clear;
    int RADIO_CLARITY_HAZY = R.id.radio_clarity_hazy;
    int RADIO_CLARITY_TURBID = R.id.radio_clarity_turbid;
    int RADIO_CONCENTRATION_PALE = R.id.radio_concentration_pale;
    int RADIO_CONCENTRATION_MEDIUM = R.id.radio_concentration_medium;
    int RADIO_CONCENTRATION_DEEP = R.id.radio_concentration_deep;
    int RADIO_COLOR_PURPLE = R.id.radio_color_purple;
    int RADIO_COLOR_RUBY = R.id.radio_color_ruby;
    int RADIO_COLOR_GARNET = R.id.radio_color_garnet;
    int RADIO_COLOR_STRAW = R.id.radio_color_straw;
    int RADIO_COLOR_YELLOW = R.id.radio_color_yellow;
    int RADIO_COLOR_GOLD = R.id.radio_color_gold;
    int RADIO_SECONDARY_COLOR_ORANGE = R.id.radio_secondary_color_orange;
    int RADIO_SECONDARY_COLOR_BLUE = R.id.radio_secondary_color_blue;
    int RADIO_SECONDARY_COLOR_RUBY = R.id.radio_secondary_color_ruby;
    int RADIO_SECONDARY_COLOR_GARNET = R.id.radio_secondary_color_garnet;
    int RADIO_SECONDARY_COLOR_BROWN = R.id.radio_secondary_color_brown;
    int RADIO_SECONDARY_COLOR_SILVER = R.id.radio_secondary_color_silver;
    int RADIO_SECONDARY_COLOR_GREEN = R.id.radio_secondary_color_green;
    int RADIO_SECONDARY_COLOR_COPPER = R.id.radio_secondary_color_copper;
    int RADIO_RIM_VARIATION_YES = R.id.radio_rim_variation_yes;
    int RADIO_RIM_VARIATION_NO = R.id.radio_rim_variation_no;
    int RADIO_STAIN_NONE = R.id.radio_extract_stain_none;
    int RADIO_STAIN_LIGHT = R.id.radio_extract_stain_light;
    int RADIO_STAIN_MEDIUM = R.id.radio_extract_stain_medium;
    int RADIO_STAIN_HEAVY = R.id.radio_extract_stain_heavy;
    int RADIO_TEARING_LIGHT = R.id.radio_tearing_light;
    int RADIO_TEARING_MEDIUM = R.id.radio_tearing_medium;
    int RADIO_TEARING_HEAVY = R.id.radio_tearing_heavy;
    int RADIO_GAS_EVIDENCE_YES = R.id.radio_gas_evidence_yes;
    int RADIO_GAS_EVIDENCE_NO = R.id.radio_gas_evidence_no;
    int RADIO_INTENSITY_DELICATE = R.id.radio_intensity_delicate;
    int RADIO_INTENSITY_MODERATE = R.id.radio_intensity_moderate;
    int RADIO_INTENSITY_POWERFUL = R.id.radio_intensity_powerful;
    int RADIO_AGE_ASSESSMENT_YOUTHFUL = R.id.radio_age_assessment_youthful;
    int RADIO_AGE_ASSESSMENT_DEVELOPING = R.id.radio_age_assessment_developing;
    int RADIO_AGE_ASSESSMENT_VINOUS = R.id.radio_age_assessment_vinous;
    int RADIO_NOSE_WOOD_OLD = R.id.radio_nose_wood_old;
    int RADIO_NOSE_WOOD_NEW = R.id.radio_nose_wood_new;
    int RADIO_NOSE_WOOD_LARGE = R.id.radio_nose_wood_large;
    int RADIO_NOSE_WOOD_SMALL = R.id.radio_nose_wood_small;
    int RADIO_NOSE_WOOD_FRENCH = R.id.radio_nose_wood_french;
    int RADIO_NOSE_WOOD_AMERICAN = R.id.radio_nose_wood_american;
    int RADIO_SWEETNESS_BONE_DRY = R.id.radio_palate_sweetness_bone_dry;
    int RADIO_SWEETNESS_DRY = R.id.radio_palate_sweetness_dry;
    int RADIO_SWEETNESS_OFF_DRY = R.id.radio_palate_sweetness_off_dry;
    int RADIO_SWEETNESS_MEDIUM_SWEET = R.id.radio_palate_sweetness_medium_sweet;
    int RADIO_SWEETNESS_SWEET = R.id.radio_palate_sweetness_sweet;
    int RADIO_SWEETNESS_LUSCIOUSLY_SWEET = R.id.radio_palate_sweetness_lusciously_sweet;
    int RADIO_PALATE_WOOD_OLD = R.id.radio_palate_wood_old;
    int RADIO_PALATE_WOOD_NEW = R.id.radio_palate_wood_new;
    int RADIO_PALATE_WOOD_LARGE = R.id.radio_palate_wood_large;
    int RADIO_PALATE_WOOD_SMALL = R.id.radio_palate_wood_small;
    int RADIO_PALATE_WOOD_FRENCH = R.id.radio_palate_wood_french;
    int RADIO_PALATE_WOOD_AMERICAN = R.id.radio_palate_wood_american;
    int RADIO_PHENOLIC_BITTER_YES = R.id.radio_palate_phenolic_bitter_yes;
    int RADIO_PHENOLIC_BITTER_NO = R.id.radio_palate_phenolic_bitter_no;
    int RADIO_TANNIN_LOW = R.id.radio_palate_tannin_low;
    int RADIO_TANNIN_MED_MINUS = R.id.radio_palate_tannin_med_minus;
    int RADIO_TANNIN_MED = R.id.radio_palate_tannin_med;
    int RADIO_TANNIN_MED_PLUS = R.id.radio_palate_tannin_med_plus;
    int RADIO_TANNIN_HIGH = R.id.radio_palate_tannin_high;
    int RADIO_ACID_LOW = R.id.radio_palate_acid_low;
    int RADIO_ACID_MED_MINUS = R.id.radio_palate_acid_med_minus;
    int RADIO_ACID_MED = R.id.radio_palate_acid_med;
    int RADIO_ACID_MED_PLUS = R.id.radio_palate_acid_med_plus;
    int RADIO_ACID_HIGH = R.id.radio_palate_acid_high;
    int RADIO_ALCOHOL_LOW = R.id.radio_palate_alcohol_low;
    int RADIO_ALCOHOL_MED_MINUS = R.id.radio_palate_alcohol_med_minus;
    int RADIO_ALCOHOL_MED = R.id.radio_palate_alcohol_med;
    int RADIO_ALCOHOL_MED_PLUS = R.id.radio_palate_alcohol_med_plus;
    int RADIO_ALCOHOL_HIGH = R.id.radio_palate_alcohol_high;
    int RADIO_BODY_LIGHT = R.id.radio_palate_body_light;
    int RADIO_BODY_MEDIUM = R.id.radio_palate_body_medium;
    int RADIO_BODY_FULL = R.id.radio_palate_body_full;
    int RADIO_TEXTURE_CREAMY = R.id.radio_palate_texture_creamy;
    int RADIO_TEXTURE_ROUND = R.id.radio_palate_texture_round;
    int RADIO_TEXTURE_LEAN = R.id.radio_palate_texture_lean;
    int RADIO_BALANCED_YES = R.id.radio_palate_balanced_yes;
    int RADIO_BALANCED_NO = R.id.radio_palate_balanced_no;
    int RADIO_FINISH_SHORT = R.id.radio_palate_length_finish_short;
    int RADIO_FINISH_MED_MINUS = R.id.radio_palate_length_finish_med_minus;
    int RADIO_FINISH_MED = R.id.radio_palate_length_finish_med;
    int RADIO_FINISH_MED_PLUS = R.id.radio_palate_length_finish_med_plus;
    int RADIO_FINISH_LONG = R.id.radio_palate_length_finish_long;
    int RADIO_COMPLEXITY_LOW = R.id.radio_palate_complexity_low;
    int RADIO_COMPLEXITY_MED_MINUS = R.id.radio_palate_complexity_med_minus;
    int RADIO_COMPLEXITY_MED = R.id.radio_palate_complexity_med;
    int RADIO_COMPLEXITY_MED_PLUS = R.id.radio_palate_complexity_med_plus;
    int RADIO_COMPLEXITY_HIGH = R.id.radio_palate_complexity_high;

    List<Integer> InitialConclusionMultiAutoCompleteTextViews = new ArrayList<>(Arrays.asList(
            TEXT_MULTI_INITIAL_GRAPE_VARIETIES,
            TEXT_MULTI_INITIAL_COUNTRIES
    ));

    int TEXT_SINGLE_FINAL_GRAPE_VARIETY = R.id.autoText_final_grape_variety;
    int TEXT_SINGLE_FINAL_COUNTRY_ORIGIN = R.id.autoText_final_country;
    int TEXT_SINGLE_FINAL_REGION = R.id.autoText_final_region;
    int TEXT_SINGLE_FINAL_QUALITY = R.id.autoText_final_quality;
    int TEXT_SINGLE_FINAL_VINTAGE = R.id.autoText_final_vintage;

    List<Integer> FinalConclusionAutoCompleteTextViews = new ArrayList<>(Arrays.asList(
            TEXT_SINGLE_FINAL_GRAPE_VARIETY,
            TEXT_SINGLE_FINAL_COUNTRY_ORIGIN,
            TEXT_SINGLE_FINAL_REGION,
            TEXT_SINGLE_FINAL_QUALITY,
            TEXT_SINGLE_FINAL_VINTAGE
    ));

    List<Integer> PalateSwitches = new ArrayList<>(Arrays.asList(SWITCH_NOSE_WOOD,
            SWITCH_PALATE_WOOD));

    List<Integer> AllWoodRadioGroups = new ArrayList<>(Arrays.asList(RADIO_GROUP_NOSE_WOOD_OLD_VS_NEW,
            RADIO_GROUP_NOSE_WOOD_FRENCH_VS_AMERICAN, RADIO_GROUP_NOSE_WOOD_LARGE_VS_SMALL,
            RADIO_GROUP_PALATE_WOOD_OLD_VS_NEW, RADIO_GROUP_PALATE_WOOD_FRENCH_VS_AMERICAN,
            RADIO_GROUP_PALATE_WOOD_LARGE_VS_SMALL));

    List<Integer> NoseWoodRadioGroups = new ArrayList<>(Arrays.asList(RADIO_GROUP_NOSE_WOOD_OLD_VS_NEW,
            RADIO_GROUP_NOSE_WOOD_FRENCH_VS_AMERICAN, RADIO_GROUP_NOSE_WOOD_LARGE_VS_SMALL));

    List<Integer> PalateWoodRadioGroups = new ArrayList<>(Arrays.asList(
            RADIO_GROUP_PALATE_WOOD_OLD_VS_NEW, RADIO_GROUP_PALATE_WOOD_FRENCH_VS_AMERICAN,
            RADIO_GROUP_PALATE_WOOD_LARGE_VS_SMALL));

    List<Integer> AllRadioGroups = new ArrayList<>(Arrays.asList(RADIO_GROUP_CLARITY,
            RADIO_GROUP_CONCENTRATION, RADIO_GROUP_COLOR_RED_WINE, RADIO_GROUP_COLOR_WHITE_WINE,
            RADIO_GROUP_SECONDARY_COLOR_RED_WINE, RADIO_GROUP_SECONDARY_COLOR_WHITE_WINE,
            RADIO_GROUP_RIM_VARIATION, RADIO_GROUP_EXTRACT_STAINING, RADIO_GROUP_TEARING,
            RADIO_GROUP_GAS_EVIDENCE, RADIO_GROUP_NOSE_INTENSITY, RADIO_GROUP_NOSE_AGE_ASSESSMENT,
            RADIO_GROUP_NOSE_WOOD_OLD_VS_NEW, RADIO_GROUP_NOSE_WOOD_LARGE_VS_SMALL,
            RADIO_GROUP_NOSE_WOOD_FRENCH_VS_AMERICAN, RADIO_GROUP_PALATE_SWEETNESS,
            RADIO_GROUP_PALATE_WOOD_OLD_VS_NEW, RADIO_GROUP_PALATE_WOOD_LARGE_VS_SMALL,
            RADIO_GROUP_PALATE_WOOD_FRENCH_VS_AMERICAN, RADIO_GROUP_PALATE_PHENOLIC_BITTER,
            RADIO_GROUP_PALATE_TANNIN, RADIO_GROUP_PALATE_ACID, RADIO_GROUP_PALATE_ALCOHOL,
            RADIO_GROUP_PALATE_BODY, RADIO_GROUP_PALATE_TEXTURE, RADIO_GROUP_PALATE_BALANCE,
            RADIO_GROUP_PALATE_LENGTH_FINISH, RADIO_GROUP_PALATE_COMPLEXITY,
            RADIO_GROUP_INITIAL_WORLD, RADIO_GROUP_INITIAL_CLIMATE, RADIO_GROUP_INITIAL_AGE_RANGE));

    List<Integer> AllRedRadioGroups = new ArrayList<>(Arrays.asList(RADIO_GROUP_CLARITY,
            RADIO_GROUP_CONCENTRATION, RADIO_GROUP_COLOR_RED_WINE,
            RADIO_GROUP_SECONDARY_COLOR_RED_WINE, RADIO_GROUP_RIM_VARIATION,
            RADIO_GROUP_EXTRACT_STAINING, RADIO_GROUP_TEARING, RADIO_GROUP_GAS_EVIDENCE,
            RADIO_GROUP_NOSE_INTENSITY, RADIO_GROUP_NOSE_AGE_ASSESSMENT,
            RADIO_GROUP_NOSE_WOOD_OLD_VS_NEW, RADIO_GROUP_NOSE_WOOD_LARGE_VS_SMALL,
            RADIO_GROUP_NOSE_WOOD_FRENCH_VS_AMERICAN, RADIO_GROUP_PALATE_SWEETNESS,
            RADIO_GROUP_PALATE_WOOD_OLD_VS_NEW, RADIO_GROUP_PALATE_WOOD_LARGE_VS_SMALL,
            RADIO_GROUP_PALATE_WOOD_FRENCH_VS_AMERICAN, RADIO_GROUP_PALATE_TANNIN,
            RADIO_GROUP_PALATE_ACID, RADIO_GROUP_PALATE_ALCOHOL, RADIO_GROUP_PALATE_BODY,
            RADIO_GROUP_PALATE_TEXTURE, RADIO_GROUP_PALATE_BALANCE,
            RADIO_GROUP_PALATE_LENGTH_FINISH, RADIO_GROUP_PALATE_COMPLEXITY,
            RADIO_GROUP_INITIAL_WORLD, RADIO_GROUP_INITIAL_CLIMATE, RADIO_GROUP_INITIAL_AGE_RANGE));

    List<Integer> AllWhiteRadioGroups = new ArrayList<>(Arrays.asList(RADIO_GROUP_CLARITY,
            RADIO_GROUP_CONCENTRATION, RADIO_GROUP_COLOR_WHITE_WINE,
            RADIO_GROUP_SECONDARY_COLOR_WHITE_WINE, RADIO_GROUP_RIM_VARIATION,
            RADIO_GROUP_EXTRACT_STAINING, RADIO_GROUP_TEARING, RADIO_GROUP_GAS_EVIDENCE,
            RADIO_GROUP_NOSE_INTENSITY, RADIO_GROUP_NOSE_AGE_ASSESSMENT,
            RADIO_GROUP_NOSE_WOOD_OLD_VS_NEW, RADIO_GROUP_NOSE_WOOD_LARGE_VS_SMALL,
            RADIO_GROUP_NOSE_WOOD_FRENCH_VS_AMERICAN, RADIO_GROUP_PALATE_SWEETNESS,
            RADIO_GROUP_PALATE_WOOD_OLD_VS_NEW, RADIO_GROUP_PALATE_WOOD_LARGE_VS_SMALL,
            RADIO_GROUP_PALATE_WOOD_FRENCH_VS_AMERICAN, RADIO_GROUP_PALATE_PHENOLIC_BITTER,
            RADIO_GROUP_PALATE_ACID, RADIO_GROUP_PALATE_ALCOHOL, RADIO_GROUP_PALATE_BODY,
            RADIO_GROUP_PALATE_TEXTURE, RADIO_GROUP_PALATE_BALANCE,
            RADIO_GROUP_PALATE_LENGTH_FINISH, RADIO_GROUP_PALATE_COMPLEXITY,
            RADIO_GROUP_INITIAL_WORLD, RADIO_GROUP_INITIAL_CLIMATE, RADIO_GROUP_INITIAL_AGE_RANGE));

    List<Integer> AllCheckBoxes = new ArrayList<>(Arrays.asList(CHECK_FAULT_TCA,
            CHECK_FAULT_HYDROGEN_SULFIDE, CHECK_FAULT_VOLATILE_ACIDITY, CHECK_FAULT_ETHYL_ACETATE,
            CHECK_FAULT_BRETT, CHECK_FAULT_OXIDIZATION, CHECK_FAULT_OTHER, CHECK_NOSE_FRUIT_RED,
            CHECK_NOSE_FRUIT_BLACK, CHECK_NOSE_FRUIT_BLUE, CHECK_NOSE_FRUIT_CITRUS,
            CHECK_NOSE_FRUIT_APPLE_PEAR, CHECK_NOSE_FRUIT_STONE_PIT, CHECK_NOSE_FRUIT_TROPICAL,
            CHECK_NOSE_FRUIT_MELON, CHECK_NOSE_FRUIT_CHARACTER_RIPE, CHECK_NOSE_FRUIT_CHARACTER_FRESH,
            CHECK_NOSE_FRUIT_CHARACTER_TART, CHECK_NOSE_FRUIT_CHARACTER_BAKED,
            CHECK_NOSE_FRUIT_CHARACTER_STEWED, CHECK_NOSE_FRUIT_CHARACTER_DRIED,
            CHECK_NOSE_FRUIT_CHARACTER_DESICCATED, CHECK_NOSE_FRUIT_CHARACTER_BRUISED,
            CHECK_NOSE_FRUIT_CHARACTER_JAMMY, CHECK_NOSE_NON_FRUIT_FLORAL, CHECK_NOSE_NON_FRUIT_VEGETAL,
            CHECK_NOSE_NON_FRUIT_HERBAL, CHECK_NOSE_NON_FRUIT_SPICE, CHECK_NOSE_NON_FRUIT_ANIMAL,
            CHECK_NOSE_NON_FRUIT_BARN, CHECK_NOSE_NON_FRUIT_PETROL, CHECK_NOSE_NON_FRUIT_FERMENTATION,
            CHECK_NOSE_EARTH_FOREST_FLOOR, CHECK_NOSE_EARTH_COMPOST, CHECK_NOSE_EARTH_MUSHROOMS,
            CHECK_NOSE_EARTH_POTTING_SOIL, CHECK_NOSE_MINERAL_MINERAL, CHECK_NOSE_MINERAL_WET_STONE,
            CHECK_NOSE_MINERAL_LIMESTONE, CHECK_NOSE_MINERAL_CHALK, CHECK_NOSE_MINERAL_SLATE,
            CHECK_NOSE_MINERAL_FLINT, CHECK_PALATE_FRUIT_RED, CHECK_PALATE_FRUIT_BLACK,
            CHECK_PALATE_FRUIT_BLUE, CHECK_PALATE_FRUIT_CITRUS, CHECK_PALATE_FRUIT_APPLE_PEAR,
            CHECK_PALATE_FRUIT_STONE_PIT, CHECK_PALATE_FRUIT_TROPICAL, CHECK_PALATE_FRUIT_MELON,
            CHECK_PALATE_FRUIT_CHARACTER_RIPE, CHECK_PALATE_FRUIT_CHARACTER_FRESH,
            CHECK_PALATE_FRUIT_CHARACTER_TART, CHECK_PALATE_FRUIT_CHARACTER_BAKED,
            CHECK_PALATE_FRUIT_CHARACTER_STEWED, CHECK_PALATE_FRUIT_CHARACTER_DRIED,
            CHECK_PALATE_FRUIT_CHARACTER_DESICCATED, CHECK_PALATE_FRUIT_CHARACTER_BRUISED,
            CHECK_PALATE_FRUIT_CHARACTER_JAMMY, CHECK_PALATE_NON_FRUIT_FLORAL,
            CHECK_PALATE_NON_FRUIT_VEGETAL, CHECK_PALATE_NON_FRUIT_HERBAL,
            CHECK_PALATE_NON_FRUIT_SPICE, CHECK_PALATE_NON_FRUIT_ANIMAL,
            CHECK_PALATE_NON_FRUIT_BARN, CHECK_PALATE_NON_FRUIT_PETROL,
            CHECK_PALATE_NON_FRUIT_FERMENTATION,
            CHECK_PALATE_EARTH_FOREST_FLOOR, CHECK_PALATE_EARTH_COMPOST, CHECK_PALATE_EARTH_MUSHROOMS,
            CHECK_PALATE_EARTH_POTTING_SOIL, CHECK_PALATE_MINERAL_MINERAL,
            CHECK_PALATE_MINERAL_WET_STONE,
            CHECK_PALATE_MINERAL_LIMESTONE, CHECK_PALATE_MINERAL_CHALK, CHECK_PALATE_MINERAL_SLATE,
            CHECK_PALATE_MINERAL_FLINT));

    List<Integer> AllSwitches = new ArrayList<>(Arrays.asList(SWITCH_NOSE_WOOD, SWITCH_PALATE_WOOD));

    List<Integer> AllAutoMultiText = new ArrayList<>(Arrays.asList(TEXT_MULTI_INITIAL_GRAPE_VARIETIES,
            TEXT_MULTI_INITIAL_COUNTRIES));

    List<Integer> AllAutoText = new ArrayList<>(Arrays.asList(TEXT_SINGLE_FINAL_GRAPE_VARIETY,
            TEXT_SINGLE_FINAL_COUNTRY_ORIGIN, TEXT_SINGLE_FINAL_REGION, TEXT_SINGLE_FINAL_QUALITY,
            TEXT_SINGLE_FINAL_VINTAGE));

    List<Integer> redSightViews = new ArrayList<>(Arrays.asList(RADIO_GROUP_CLARITY,
            RADIO_GROUP_CONCENTRATION, RADIO_GROUP_COLOR_RED_WINE,
            RADIO_GROUP_SECONDARY_COLOR_RED_WINE, RADIO_GROUP_RIM_VARIATION,
            RADIO_GROUP_EXTRACT_STAINING, RADIO_GROUP_TEARING, RADIO_GROUP_GAS_EVIDENCE));

    List<Integer> whiteSightViews = new ArrayList<>(Arrays.asList(RADIO_GROUP_CLARITY,
            RADIO_GROUP_CONCENTRATION, RADIO_GROUP_COLOR_WHITE_WINE,
            RADIO_GROUP_SECONDARY_COLOR_WHITE_WINE, RADIO_GROUP_RIM_VARIATION, RADIO_GROUP_TEARING,
            RADIO_GROUP_GAS_EVIDENCE));

    List<Integer> redNoseViews = new ArrayList<>(Arrays.asList(CHECK_FAULT_TCA,
            CHECK_FAULT_HYDROGEN_SULFIDE, CHECK_FAULT_VOLATILE_ACIDITY, CHECK_FAULT_ETHYL_ACETATE,
            CHECK_FAULT_BRETT, CHECK_FAULT_OXIDIZATION, CHECK_FAULT_OTHER,
            RADIO_GROUP_NOSE_INTENSITY, RADIO_GROUP_NOSE_AGE_ASSESSMENT, CHECK_NOSE_FRUIT_RED,
            CHECK_NOSE_FRUIT_BLACK, CHECK_NOSE_FRUIT_BLUE, CHECK_NOSE_FRUIT_CHARACTER_RIPE,
            CHECK_NOSE_FRUIT_CHARACTER_FRESH, CHECK_NOSE_FRUIT_CHARACTER_TART,
            CHECK_NOSE_FRUIT_CHARACTER_BAKED, CHECK_NOSE_FRUIT_CHARACTER_STEWED,
            CHECK_NOSE_FRUIT_CHARACTER_DRIED, CHECK_NOSE_FRUIT_CHARACTER_DESICCATED,
            CHECK_NOSE_FRUIT_CHARACTER_BRUISED, CHECK_NOSE_FRUIT_CHARACTER_JAMMY,
            CHECK_NOSE_NON_FRUIT_FLORAL, CHECK_NOSE_NON_FRUIT_VEGETAL, CHECK_NOSE_NON_FRUIT_HERBAL,
            CHECK_NOSE_NON_FRUIT_SPICE, CHECK_NOSE_NON_FRUIT_ANIMAL, CHECK_NOSE_NON_FRUIT_BARN,
            CHECK_NOSE_NON_FRUIT_PETROL, CHECK_NOSE_NON_FRUIT_FERMENTATION,
            CHECK_NOSE_EARTH_FOREST_FLOOR, CHECK_NOSE_EARTH_COMPOST, CHECK_NOSE_EARTH_MUSHROOMS,
            CHECK_NOSE_EARTH_POTTING_SOIL, CHECK_NOSE_MINERAL_MINERAL, CHECK_NOSE_MINERAL_WET_STONE,
            CHECK_NOSE_MINERAL_LIMESTONE, CHECK_NOSE_MINERAL_CHALK, CHECK_NOSE_MINERAL_SLATE,
            CHECK_NOSE_MINERAL_FLINT, SWITCH_NOSE_WOOD, RADIO_GROUP_NOSE_WOOD_OLD_VS_NEW,
            RADIO_GROUP_NOSE_WOOD_LARGE_VS_SMALL, RADIO_GROUP_NOSE_WOOD_FRENCH_VS_AMERICAN));

    List<Integer> whiteNoseViews = new ArrayList<>(Arrays.asList(CHECK_FAULT_TCA,
            CHECK_FAULT_HYDROGEN_SULFIDE, CHECK_FAULT_VOLATILE_ACIDITY, CHECK_FAULT_ETHYL_ACETATE,
            CHECK_FAULT_BRETT, CHECK_FAULT_OXIDIZATION, CHECK_FAULT_OTHER, RADIO_GROUP_NOSE_INTENSITY,
            RADIO_GROUP_NOSE_AGE_ASSESSMENT, CHECK_NOSE_FRUIT_CITRUS, CHECK_NOSE_FRUIT_APPLE_PEAR,
            CHECK_NOSE_FRUIT_STONE_PIT, CHECK_NOSE_FRUIT_TROPICAL, CHECK_NOSE_FRUIT_MELON,
            CHECK_NOSE_FRUIT_CHARACTER_RIPE, CHECK_NOSE_FRUIT_CHARACTER_FRESH,
            CHECK_NOSE_FRUIT_CHARACTER_TART, CHECK_NOSE_FRUIT_CHARACTER_BAKED,
            CHECK_NOSE_FRUIT_CHARACTER_STEWED, CHECK_NOSE_FRUIT_CHARACTER_DRIED,
            CHECK_NOSE_FRUIT_CHARACTER_DESICCATED, CHECK_NOSE_FRUIT_CHARACTER_BRUISED,
            CHECK_NOSE_FRUIT_CHARACTER_JAMMY, CHECK_NOSE_NON_FRUIT_FLORAL,
            CHECK_NOSE_NON_FRUIT_VEGETAL, CHECK_NOSE_NON_FRUIT_HERBAL, CHECK_NOSE_NON_FRUIT_SPICE,
            CHECK_NOSE_NON_FRUIT_ANIMAL, CHECK_NOSE_NON_FRUIT_BARN, CHECK_NOSE_NON_FRUIT_PETROL,
            CHECK_NOSE_NON_FRUIT_FERMENTATION, CHECK_NOSE_EARTH_FOREST_FLOOR,
            CHECK_NOSE_EARTH_COMPOST, CHECK_NOSE_EARTH_MUSHROOMS, CHECK_NOSE_EARTH_POTTING_SOIL,
            CHECK_NOSE_MINERAL_MINERAL, CHECK_NOSE_MINERAL_WET_STONE, CHECK_NOSE_MINERAL_LIMESTONE,
            CHECK_NOSE_MINERAL_CHALK, CHECK_NOSE_MINERAL_SLATE, CHECK_NOSE_MINERAL_FLINT,
            SWITCH_NOSE_WOOD, RADIO_GROUP_NOSE_WOOD_OLD_VS_NEW, RADIO_GROUP_NOSE_WOOD_LARGE_VS_SMALL,
            RADIO_GROUP_NOSE_WOOD_FRENCH_VS_AMERICAN));

    List<Integer> redPalateViewsA = new ArrayList<>(Arrays.asList(RADIO_GROUP_PALATE_SWEETNESS,
            CHECK_PALATE_FRUIT_RED, CHECK_PALATE_FRUIT_BLACK, CHECK_PALATE_FRUIT_BLUE,
            CHECK_PALATE_FRUIT_CHARACTER_RIPE, CHECK_PALATE_FRUIT_CHARACTER_FRESH,
            CHECK_PALATE_FRUIT_CHARACTER_TART, CHECK_PALATE_FRUIT_CHARACTER_BAKED,
            CHECK_PALATE_FRUIT_CHARACTER_STEWED, CHECK_PALATE_FRUIT_CHARACTER_DRIED,
            CHECK_PALATE_FRUIT_CHARACTER_DESICCATED, CHECK_PALATE_FRUIT_CHARACTER_BRUISED,
            CHECK_PALATE_FRUIT_CHARACTER_JAMMY, CHECK_PALATE_NON_FRUIT_FLORAL,
            CHECK_PALATE_NON_FRUIT_VEGETAL, CHECK_PALATE_NON_FRUIT_HERBAL,
            CHECK_PALATE_NON_FRUIT_SPICE, CHECK_PALATE_NON_FRUIT_ANIMAL,
            CHECK_PALATE_NON_FRUIT_BARN, CHECK_PALATE_NON_FRUIT_PETROL,
            CHECK_PALATE_NON_FRUIT_FERMENTATION, CHECK_PALATE_EARTH_FOREST_FLOOR,
            CHECK_PALATE_EARTH_COMPOST, CHECK_PALATE_EARTH_MUSHROOMS, CHECK_PALATE_EARTH_POTTING_SOIL,
            CHECK_PALATE_MINERAL_MINERAL, CHECK_PALATE_MINERAL_WET_STONE,
            CHECK_PALATE_MINERAL_LIMESTONE, CHECK_PALATE_MINERAL_CHALK, CHECK_PALATE_MINERAL_SLATE,
            CHECK_PALATE_MINERAL_FLINT, SWITCH_PALATE_WOOD, RADIO_GROUP_PALATE_WOOD_OLD_VS_NEW,
            RADIO_GROUP_PALATE_WOOD_LARGE_VS_SMALL, RADIO_GROUP_PALATE_WOOD_FRENCH_VS_AMERICAN));

    List<Integer> whitePalateViewsA = new ArrayList<>(Arrays.asList(RADIO_GROUP_PALATE_SWEETNESS,
            CHECK_PALATE_FRUIT_CITRUS, CHECK_PALATE_FRUIT_APPLE_PEAR, CHECK_PALATE_FRUIT_STONE_PIT,
            CHECK_PALATE_FRUIT_TROPICAL, CHECK_PALATE_FRUIT_MELON, CHECK_PALATE_FRUIT_CHARACTER_RIPE,
            CHECK_PALATE_FRUIT_CHARACTER_FRESH, CHECK_PALATE_FRUIT_CHARACTER_TART,
            CHECK_PALATE_FRUIT_CHARACTER_BAKED, CHECK_PALATE_FRUIT_CHARACTER_STEWED,
            CHECK_PALATE_FRUIT_CHARACTER_DRIED, CHECK_PALATE_FRUIT_CHARACTER_DESICCATED,
            CHECK_PALATE_FRUIT_CHARACTER_BRUISED, CHECK_PALATE_FRUIT_CHARACTER_JAMMY,
            CHECK_PALATE_NON_FRUIT_FLORAL, CHECK_PALATE_NON_FRUIT_VEGETAL,
            CHECK_PALATE_NON_FRUIT_HERBAL, CHECK_PALATE_NON_FRUIT_SPICE,
            CHECK_PALATE_NON_FRUIT_ANIMAL, CHECK_PALATE_NON_FRUIT_BARN, CHECK_PALATE_NON_FRUIT_PETROL,
            CHECK_PALATE_NON_FRUIT_FERMENTATION, CHECK_PALATE_EARTH_FOREST_FLOOR,
            CHECK_PALATE_EARTH_COMPOST, CHECK_PALATE_EARTH_MUSHROOMS, CHECK_PALATE_EARTH_POTTING_SOIL,
            CHECK_PALATE_MINERAL_MINERAL, CHECK_PALATE_MINERAL_WET_STONE,
            CHECK_PALATE_MINERAL_LIMESTONE, CHECK_PALATE_MINERAL_CHALK, CHECK_PALATE_MINERAL_SLATE,
            CHECK_PALATE_MINERAL_FLINT, SWITCH_PALATE_WOOD, RADIO_GROUP_PALATE_WOOD_OLD_VS_NEW,
            RADIO_GROUP_PALATE_WOOD_LARGE_VS_SMALL, RADIO_GROUP_PALATE_WOOD_FRENCH_VS_AMERICAN));

    List<Integer> redPalateViewsB = new ArrayList<>(Arrays.asList(RADIO_GROUP_PALATE_TANNIN,
            RADIO_GROUP_PALATE_ACID, RADIO_GROUP_PALATE_ALCOHOL, RADIO_GROUP_PALATE_BODY,
            RADIO_GROUP_PALATE_TEXTURE, RADIO_GROUP_PALATE_BALANCE, RADIO_GROUP_PALATE_LENGTH_FINISH,
            RADIO_GROUP_PALATE_COMPLEXITY));

    List<Integer> whitePalateViewsB = new ArrayList<>(Arrays.asList(RADIO_GROUP_PALATE_PHENOLIC_BITTER,
            RADIO_GROUP_PALATE_ACID, RADIO_GROUP_PALATE_ALCOHOL, RADIO_GROUP_PALATE_BODY,
            RADIO_GROUP_PALATE_TEXTURE, RADIO_GROUP_PALATE_BALANCE, RADIO_GROUP_PALATE_LENGTH_FINISH,
            RADIO_GROUP_PALATE_COMPLEXITY));

    List<Integer> initialConclusionViews = new ArrayList<>(Arrays.asList(TEXT_MULTI_INITIAL_GRAPE_VARIETIES,
            RADIO_GROUP_INITIAL_WORLD, RADIO_GROUP_INITIAL_CLIMATE, TEXT_MULTI_INITIAL_COUNTRIES,
            RADIO_GROUP_INITIAL_AGE_RANGE));

    List<Integer> finalConclusionViews = new ArrayList<>(Arrays.asList(TEXT_SINGLE_FINAL_GRAPE_VARIETY,
            TEXT_SINGLE_FINAL_COUNTRY_ORIGIN, TEXT_SINGLE_FINAL_REGION, TEXT_SINGLE_FINAL_QUALITY,
            TEXT_SINGLE_FINAL_VINTAGE));

}
