package com.wineguesser.deductive.view

import com.wineguesser.deductive.R

// Constants and collections for Deduction Form

const val APP_VARIETY_GUESS_ID = "APP_VARIETY_GUESS_ID"
const val IS_RED_WINE = "IS_RED_WINE"
const val TRUE = true
const val FALSE = false
const val USER_CONCLUSION_VARIETY = "USER_CONCLUSION_VARIETY"
const val USER_CONCLUSION_COUNTRY = "USER_CONCLUSION_COUNTRY"
const val USER_CONCLUSION_REGION = "USER_CONCLUSION_REGION"
const val USER_CONCLUSION_QUALITY = "USER_CONCLUSION_QUALITY"
const val USER_CONCLUSION_VINTAGE = "USER_CONCLUSION_VINTAGE"

const val FORM_ACTUAL_LABEL = "FORM_ACTUAL_LABEL"
const val FORM_ACTUAL_VARIETY = "FORM_ACTUAL_VARIETY"
const val FORM_ACTUAL_COUNTRY = "FORM_ACTUAL_COUNTRY"
const val FORM_ACTUAL_REGION = "FORM_ACTUAL_REGION"
const val FORM_ACTUAL_QUALITY = "FORM_ACTUAL_QUALITY"
const val FORM_ACTUAL_VINTAGE = "FORM_ACTUAL_VINTAGE"

const val CURRENT_PAGE_RED = "CURRENT_PAGE_RED"
const val CURRENT_PAGE_WHITE = "CURRENT_PAGE_WHITE"

const val RED_SIGHT_Y_SCROLL = "RED_SIGHT_Y_SCROLL"
const val WHITE_SIGHT_Y_SCROLL = "WHITE_SIGHT_Y_SCROLL"
const val RED_NOSE_Y_SCROLL = "RED_NOSE_Y_SCROLL"
const val WHITE_NOSE_Y_SCROLL = "WHITE_NOSE_Y_SCROLL"
const val RED_PALATE_A_Y_SCROLL = "RED_PALATE_A_Y_SCROLL"
const val WHITE_PALATE_A_Y_SCROLL = "WHITE_PALATE_A_Y_SCROLL"
const val RED_PALATE_B_Y_SCROLL = "RED_PALATE_B_Y_SCROLL"
const val WHITE_PALATE_B_Y_SCROLL = "WHITE_PALATE_B_Y_SCROLL"
const val RED_INITIAL_Y_SCROLL = "RED_INITIAL_Y_SCROLL"
const val WHITE_INITIAL_Y_SCROLL = "WHITE_INITIAL_Y_SCROLL"
const val RED_FINAL_Y_SCROLL = "RED_FINAL_Y_SCROLL"
const val WHITE_FINAL_Y_SCROLL = "WHITE_FINAL_Y_SCROLL"

const val NUM_PAGES = 6
const val SIGHT_PAGE = 0
const val NOSE_PAGE = 1
const val PALATE_PAGE_A = 2
const val PALATE_PAGE_B = 3
const val INITIAL_CONCLUSION_PAGE = 4
const val FINAL_CONCLUSION_PAGE = 5

const val RED_WINE_FORM_PREFERENCES = "RED_WINE_FORM_PREFERENCES"
const val WHITE_WINE_FORM_PREFERENCES = "WHITE_WINE_FORM_PREFERENCES"

const val CHECKED = 1
const val NOT_CHECKED = 0
const val NONE_SELECTED = -1

const val Albarino = "Albarino"
const val Cabernet_Sauvignon = "Cabernet Sauvignon"
const val Chardonnay = "Chardonnay"
const val Chenin_Blanc = "Chenin Blanc"
const val Gamay = "Gamay"
const val Gewurztraminer = "Gewurztraminer"
const val Grenache = "Grenache"
const val Malbec = "Malbec"
const val Merlot = "Merlot"
const val Nebbiolo = "Nebbiolo"
const val Pinot_Gris = "Pinot Gris"
const val Pinot_Noir = "Pinot Noir"
const val Riesling = "Riesling"
const val Sangiovese = "Sangiovese"
const val Sauvignon_Blanc = "Sauvignon Blanc"
const val Syrah = "Syrah"
const val Tempranillo = "Tempranillo"
const val Torrontes = "Torrontes"
const val Viognier = "Viognier"
const val Zinfandel = "Zinfandel"

const val Argentina = "Argentina"
const val Australia = "Australia"
const val Chile = "Chile"
const val France = "France"
const val Germany = "Germany"
const val Italy = "Italy"
const val New_Zealand = "New Zealand"
const val Spain = "Spain"
const val United_States = "United States"

const val Alsace = "Alsace"
const val Burgundy = "Burgundy"
const val Bordeaux = "Bordeaux"
const val Loire_Valley = "Loire Valley"
const val South_Australia = "South Australia"
const val Western_Australia = "Western Australia"
const val Central_Valley = "Central Valley"
const val California = "California"
const val Rhone_Valley = "Rhone Valley"
const val Victoria = "Victoria"
const val Piedmont = "Piedmont"
const val Oregon = "Oregon"
const val South_Island = "South Island"
const val North_Island = "North Island"
const val Tuscany = "Tuscany"

val AllRegions = listOf(
    Alsace, Burgundy, Bordeaux, Loire_Valley, South_Australia,
    Western_Australia, Central_Valley, California, Rhone_Valley, Victoria, Piedmont, Oregon,
    South_Island, North_Island, Tuscany
)

val AllCountries = listOf(
    Argentina, Australia, Chile, France, Germany,
    Italy, New_Zealand, Spain, United_States
)

val RedVarieties = listOf(
    Cabernet_Sauvignon, Gamay,
    Grenache, Malbec, Merlot, Nebbiolo, Pinot_Noir, Sangiovese, Syrah,
    Tempranillo, Zinfandel
)

val WhiteVarieties = listOf(
    Albarino, Chardonnay, Chenin_Blanc,
    Gewurztraminer, Pinot_Gris, Riesling, Sauvignon_Blanc, Torrontes, Viognier
)

const val RADIO_GROUP_CLARITY = R.id.radio_group_clarity
const val RADIO_GROUP_CONCENTRATION = R.id.radio_group_concentration
const val RADIO_GROUP_COLOR_RED_WINE = R.id.radio_group_color_redwine
const val RADIO_GROUP_COLOR_WHITE_WINE = R.id.radio_group_color_whitewine
const val RADIO_GROUP_SECONDARY_COLOR_RED_WINE = R.id.radio_group_colorsecondary_redwine
const val RADIO_GROUP_SECONDARY_COLOR_WHITE_WINE = R.id.radio_group_colorsecondary_whitewine
const val RADIO_GROUP_RIM_VARIATION = R.id.radio_group_rimvariation
const val RADIO_GROUP_EXTRACT_STAINING = R.id.radio_group_extractstain_redwine
const val RADIO_GROUP_TEARING = R.id.radio_group_tearing
const val RADIO_GROUP_GAS_EVIDENCE = R.id.radio_group_gasevidence

const val CHECK_FAULT_TCA = R.id.check_faulty_tca
const val CHECK_FAULT_HYDROGEN_SULFIDE = R.id.check_faulty_hydrogen_sulfide
const val CHECK_FAULT_VOLATILE_ACIDITY = R.id.check_faulty_volatile_acidity
const val CHECK_FAULT_ETHYL_ACETATE = R.id.check_faulty_ethyl_acetate
const val CHECK_FAULT_BRETT = R.id.check_faulty_brett
const val CHECK_FAULT_OXIDIZATION = R.id.check_faulty_oxidization
const val CHECK_FAULT_OTHER = R.id.check_faulty_other
const val RADIO_GROUP_NOSE_INTENSITY = R.id.radio_group_nose_intensity
const val RADIO_GROUP_NOSE_AGE_ASSESSMENT = R.id.radio_group_nose_age_assessment
const val CHECK_NOSE_FRUIT_RED = R.id.check_nose_fruit_red
const val CHECK_NOSE_FRUIT_BLACK = R.id.check_nose_fruit_black
const val CHECK_NOSE_FRUIT_BLUE = R.id.check_nose_fruit_blue
const val CHECK_NOSE_FRUIT_CITRUS = R.id.check_nose_fruit_citrus
const val CHECK_NOSE_FRUIT_APPLE_PEAR = R.id.check_nose_fruit_apple_pear
const val CHECK_NOSE_FRUIT_STONE_PIT = R.id.check_nose_fruit_stone_pit
const val CHECK_NOSE_FRUIT_TROPICAL = R.id.check_nose_fruit_tropical
const val CHECK_NOSE_FRUIT_MELON = R.id.check_nose_fruit_melon
const val CHECK_NOSE_FRUIT_CHARACTER_RIPE = R.id.check_nose_fruit_character_ripe
const val CHECK_NOSE_FRUIT_CHARACTER_FRESH = R.id.check_nose_fruit_character_fresh
const val CHECK_NOSE_FRUIT_CHARACTER_TART = R.id.check_nose_fruit_character_tart
const val CHECK_NOSE_FRUIT_CHARACTER_BAKED = R.id.check_nose_fruit_character_baked
const val CHECK_NOSE_FRUIT_CHARACTER_STEWED = R.id.check_nose_fruit_character_stewed
const val CHECK_NOSE_FRUIT_CHARACTER_DRIED = R.id.check_nose_fruit_character_dried
const val CHECK_NOSE_FRUIT_CHARACTER_DESICCATED = R.id.check_nose_fruit_character_desiccated
const val CHECK_NOSE_FRUIT_CHARACTER_BRUISED = R.id.check_nose_fruit_character_bruised
const val CHECK_NOSE_FRUIT_CHARACTER_JAMMY = R.id.check_nose_fruit_character_jammy
const val CHECK_NOSE_NON_FRUIT_FLORAL = R.id.check_nose_non_fruit_floral
const val CHECK_NOSE_NON_FRUIT_VEGETAL = R.id.check_nose_non_fruit_vegetal
const val CHECK_NOSE_NON_FRUIT_HERBAL = R.id.check_nose_non_fruit_herbal
const val CHECK_NOSE_NON_FRUIT_SPICE = R.id.check_nose_non_fruit_spice
const val CHECK_NOSE_NON_FRUIT_ANIMAL = R.id.check_nose_non_fruit_animal
const val CHECK_NOSE_NON_FRUIT_BARN = R.id.check_nose_non_fruit_barn
const val CHECK_NOSE_NON_FRUIT_PETROL = R.id.check_nose_non_fruit_petrol
const val CHECK_NOSE_NON_FRUIT_FERMENTATION = R.id.check_nose_non_fruit_fermentation
const val CHECK_NOSE_EARTH_FOREST_FLOOR = R.id.check_nose_earth_forest_floor
const val CHECK_NOSE_EARTH_COMPOST = R.id.check_nose_earth_compost
const val CHECK_NOSE_EARTH_MUSHROOMS = R.id.check_nose_earth_mushrooms
const val CHECK_NOSE_EARTH_POTTING_SOIL = R.id.check_nose_earth_potting_soil
const val CHECK_NOSE_MINERAL_MINERAL = R.id.check_nose_mineral_mineral
const val CHECK_NOSE_MINERAL_WET_STONE = R.id.check_nose_mineral_wet_stone
const val CHECK_NOSE_MINERAL_LIMESTONE = R.id.check_nose_mineral_limestone
const val CHECK_NOSE_MINERAL_CHALK = R.id.check_nose_mineral_chalk
const val CHECK_NOSE_MINERAL_SLATE = R.id.check_nose_mineral_slate
const val CHECK_NOSE_MINERAL_FLINT = R.id.check_nose_mineral_flint
const val SWITCH_NOSE_WOOD = R.id.switch_nose_wood
const val RADIO_GROUP_NOSE_WOOD_OLD_VS_NEW = R.id.radio_group_nose_wood_old_vs_new
const val RADIO_GROUP_NOSE_WOOD_LARGE_VS_SMALL = R.id.radio_group_nose_wood_large_vs_small
const val RADIO_GROUP_NOSE_WOOD_FRENCH_VS_AMERICAN = R.id.radio_group_nose_wood_french_vs_american

const val RADIO_GROUP_PALATE_SWEETNESS = R.id.radio_group_palate_sweetness
const val CHECK_PALATE_FRUIT_RED = R.id.check_palate_fruit_red
const val CHECK_PALATE_FRUIT_BLACK = R.id.check_palate_fruit_black
const val CHECK_PALATE_FRUIT_BLUE = R.id.check_palate_fruit_blue
const val CHECK_PALATE_FRUIT_CITRUS = R.id.check_palate_fruit_citrus
const val CHECK_PALATE_FRUIT_APPLE_PEAR = R.id.check_palate_fruit_apple_pear
const val CHECK_PALATE_FRUIT_STONE_PIT = R.id.check_palate_fruit_stone_pit
const val CHECK_PALATE_FRUIT_TROPICAL = R.id.check_palate_fruit_tropical
const val CHECK_PALATE_FRUIT_MELON = R.id.check_palate_fruit_melon
const val CHECK_PALATE_FRUIT_CHARACTER_RIPE = R.id.check_palate_fruit_character_ripe
const val CHECK_PALATE_FRUIT_CHARACTER_FRESH = R.id.check_palate_fruit_character_fresh
const val CHECK_PALATE_FRUIT_CHARACTER_TART = R.id.check_palate_fruit_character_tart
const val CHECK_PALATE_FRUIT_CHARACTER_BAKED = R.id.check_palate_fruit_character_baked
const val CHECK_PALATE_FRUIT_CHARACTER_STEWED = R.id.check_palate_fruit_character_stewed
const val CHECK_PALATE_FRUIT_CHARACTER_DRIED = R.id.check_palate_fruit_character_dried
const val CHECK_PALATE_FRUIT_CHARACTER_DESICCATED = R.id.check_palate_fruit_character_desiccated
const val CHECK_PALATE_FRUIT_CHARACTER_BRUISED = R.id.check_palate_fruit_character_bruised
const val CHECK_PALATE_FRUIT_CHARACTER_JAMMY = R.id.check_palate_fruit_character_jammy
const val CHECK_PALATE_NON_FRUIT_FLORAL = R.id.check_palate_non_fruit_floral
const val CHECK_PALATE_NON_FRUIT_VEGETAL = R.id.check_palate_non_fruit_vegetal
const val CHECK_PALATE_NON_FRUIT_HERBAL = R.id.check_palate_non_fruit_herbal
const val CHECK_PALATE_NON_FRUIT_SPICE = R.id.check_palate_non_fruit_spice
const val CHECK_PALATE_NON_FRUIT_ANIMAL = R.id.check_palate_non_fruit_animal
const val CHECK_PALATE_NON_FRUIT_BARN = R.id.check_palate_non_fruit_barn
const val CHECK_PALATE_NON_FRUIT_PETROL = R.id.check_palate_non_fruit_petrol
const val CHECK_PALATE_NON_FRUIT_FERMENTATION = R.id.check_palate_non_fruit_fermentation
const val CHECK_PALATE_EARTH_FOREST_FLOOR = R.id.check_palate_earth_forest_floor
const val CHECK_PALATE_EARTH_COMPOST = R.id.check_palate_earth_compost
const val CHECK_PALATE_EARTH_MUSHROOMS = R.id.check_palate_earth_mushrooms
const val CHECK_PALATE_EARTH_POTTING_SOIL = R.id.check_palate_earth_potting_soil
const val CHECK_PALATE_MINERAL_MINERAL = R.id.check_palate_mineral_mineral
const val CHECK_PALATE_MINERAL_WET_STONE = R.id.check_palate_mineral_wet_stone
const val CHECK_PALATE_MINERAL_LIMESTONE = R.id.check_palate_mineral_limestone
const val CHECK_PALATE_MINERAL_CHALK = R.id.check_palate_mineral_chalk
const val CHECK_PALATE_MINERAL_SLATE = R.id.check_palate_mineral_slate
const val CHECK_PALATE_MINERAL_FLINT = R.id.check_palate_mineral_flint
const val SWITCH_PALATE_WOOD = R.id.switch_palate_wood

const val RADIO_GROUP_PALATE_WOOD_OLD_VS_NEW = R.id.radio_group_palate_wood_old_vs_new
const val RADIO_GROUP_PALATE_WOOD_LARGE_VS_SMALL = R.id.radio_group_palate_wood_large_vs_small
const val RADIO_GROUP_PALATE_WOOD_FRENCH_VS_AMERICAN = R.id.radio_group_palate_french_vs_american

const val RADIO_GROUP_PALATE_PHENOLIC_BITTER = R.id.radio_group_phenolic_bitter
const val RADIO_GROUP_PALATE_TANNIN = R.id.radio_group_palate_tannin
const val RADIO_GROUP_PALATE_ACID = R.id.radio_group_palate_acid
const val RADIO_GROUP_PALATE_ALCOHOL = R.id.radio_group_palate_alcohol
const val RADIO_GROUP_PALATE_BODY = R.id.radio_group_palate_body
const val RADIO_GROUP_PALATE_TEXTURE = R.id.radio_group_palate_texture
const val RADIO_GROUP_PALATE_BALANCE = R.id.radio_group_palate_balance
const val RADIO_GROUP_PALATE_LENGTH_FINISH = R.id.radio_group_palate_length_finish
const val RADIO_GROUP_PALATE_COMPLEXITY = R.id.radio_group_palate_complexity

const val TEXT_MULTI_INITIAL_GRAPE_VARIETIES = R.id.multiText_initial_varieties
const val RADIO_GROUP_INITIAL_WORLD = R.id.radio_group_initial_world
const val RADIO_GROUP_INITIAL_CLIMATE = R.id.radio_group_initial_climate
const val TEXT_MULTI_INITIAL_COUNTRIES = R.id.multiText_initial_countries
const val RADIO_GROUP_INITIAL_AGE_RANGE = R.id.radio_group_initial_age

const val TEXT_SINGLE_FINAL_GRAPE_VARIETY = R.id.autoText_final_grape_variety
const val TEXT_SINGLE_FINAL_COUNTRY_ORIGIN = R.id.autoText_final_country
const val TEXT_SINGLE_FINAL_REGION = R.id.autoText_final_region
const val TEXT_SINGLE_FINAL_QUALITY = R.id.autoText_final_quality
const val TEXT_SINGLE_FINAL_VINTAGE = R.id.autoText_final_vintage

const val RADIO_CLARITY_CLEAR = R.id.radio_clarity_clear
const val RADIO_CLARITY_HAZY = R.id.radio_clarity_hazy
const val RADIO_CLARITY_TURBID = R.id.radio_clarity_turbid
const val RADIO_CONCENTRATION_PALE = R.id.radio_concentration_pale
const val RADIO_CONCENTRATION_MEDIUM = R.id.radio_concentration_medium
const val RADIO_CONCENTRATION_DEEP = R.id.radio_concentration_deep
const val RADIO_COLOR_PURPLE = R.id.radio_color_purple
const val RADIO_COLOR_RUBY = R.id.radio_color_ruby
const val RADIO_COLOR_GARNET = R.id.radio_color_garnet
const val RADIO_COLOR_STRAW = R.id.radio_color_straw
const val RADIO_COLOR_YELLOW = R.id.radio_color_yellow
const val RADIO_COLOR_GOLD = R.id.radio_color_gold
const val RADIO_SECONDARY_COLOR_ORANGE = R.id.radio_secondary_color_orange
const val RADIO_SECONDARY_COLOR_BLUE = R.id.radio_secondary_color_blue
const val RADIO_SECONDARY_COLOR_RUBY = R.id.radio_secondary_color_ruby
const val RADIO_SECONDARY_COLOR_GARNET = R.id.radio_secondary_color_garnet
const val RADIO_SECONDARY_COLOR_BROWN = R.id.radio_secondary_color_brown
const val RADIO_SECONDARY_COLOR_SILVER = R.id.radio_secondary_color_silver
const val RADIO_SECONDARY_COLOR_GREEN = R.id.radio_secondary_color_green
const val RADIO_SECONDARY_COLOR_COPPER = R.id.radio_secondary_color_copper
const val RADIO_RIM_VARIATION_YES = R.id.radio_rim_variation_yes
const val RADIO_RIM_VARIATION_NO = R.id.radio_rim_variation_no
const val RADIO_STAIN_NONE = R.id.radio_extract_stain_none
const val RADIO_STAIN_LIGHT = R.id.radio_extract_stain_light
const val RADIO_STAIN_MEDIUM = R.id.radio_extract_stain_medium
const val RADIO_STAIN_HEAVY = R.id.radio_extract_stain_heavy
const val RADIO_TEARING_LIGHT = R.id.radio_tearing_light
const val RADIO_TEARING_MEDIUM = R.id.radio_tearing_medium
const val RADIO_TEARING_HEAVY = R.id.radio_tearing_heavy
const val RADIO_GAS_EVIDENCE_YES = R.id.radio_gas_evidence_yes
const val RADIO_GAS_EVIDENCE_NO = R.id.radio_gas_evidence_no
const val RADIO_INTENSITY_DELICATE = R.id.radio_intensity_delicate
const val RADIO_INTENSITY_MODERATE = R.id.radio_intensity_moderate
const val RADIO_INTENSITY_POWERFUL = R.id.radio_intensity_powerful
const val RADIO_AGE_ASSESSMENT_YOUTHFUL = R.id.radio_age_assessment_youthful
const val RADIO_AGE_ASSESSMENT_DEVELOPING = R.id.radio_age_assessment_developing
const val RADIO_AGE_ASSESSMENT_VINOUS = R.id.radio_age_assessment_vinous
const val RADIO_NOSE_WOOD_OLD = R.id.radio_nose_wood_old
const val RADIO_NOSE_WOOD_NEW = R.id.radio_nose_wood_new
const val RADIO_NOSE_WOOD_LARGE = R.id.radio_nose_wood_large
const val RADIO_NOSE_WOOD_SMALL = R.id.radio_nose_wood_small
const val RADIO_NOSE_WOOD_FRENCH = R.id.radio_nose_wood_french
const val RADIO_NOSE_WOOD_AMERICAN = R.id.radio_nose_wood_american
const val RADIO_SWEETNESS_BONE_DRY = R.id.radio_palate_sweetness_bone_dry
const val RADIO_SWEETNESS_DRY = R.id.radio_palate_sweetness_dry
const val RADIO_SWEETNESS_OFF_DRY = R.id.radio_palate_sweetness_off_dry
const val RADIO_SWEETNESS_MEDIUM_SWEET = R.id.radio_palate_sweetness_medium_sweet
const val RADIO_SWEETNESS_SWEET = R.id.radio_palate_sweetness_sweet
const val RADIO_SWEETNESS_LUSCIOUSLY_SWEET = R.id.radio_palate_sweetness_lusciously_sweet
const val RADIO_PALATE_WOOD_OLD = R.id.radio_palate_wood_old
const val RADIO_PALATE_WOOD_NEW = R.id.radio_palate_wood_new
const val RADIO_PALATE_WOOD_LARGE = R.id.radio_palate_wood_large
const val RADIO_PALATE_WOOD_SMALL = R.id.radio_palate_wood_small
const val RADIO_PALATE_WOOD_FRENCH = R.id.radio_palate_wood_french
const val RADIO_PALATE_WOOD_AMERICAN = R.id.radio_palate_wood_american
const val RADIO_PHENOLIC_BITTER_YES = R.id.radio_palate_phenolic_bitter_yes
const val RADIO_PHENOLIC_BITTER_NO = R.id.radio_palate_phenolic_bitter_no
const val RADIO_TANNIN_LOW = R.id.radio_palate_tannin_low
const val RADIO_TANNIN_MED_MINUS = R.id.radio_palate_tannin_med_minus
const val RADIO_TANNIN_MED = R.id.radio_palate_tannin_med
const val RADIO_TANNIN_MED_PLUS = R.id.radio_palate_tannin_med_plus
const val RADIO_TANNIN_HIGH = R.id.radio_palate_tannin_high
const val RADIO_ACID_LOW = R.id.radio_palate_acid_low
const val RADIO_ACID_MED_MINUS = R.id.radio_palate_acid_med_minus
const val RADIO_ACID_MED = R.id.radio_palate_acid_med
const val RADIO_ACID_MED_PLUS = R.id.radio_palate_acid_med_plus
const val RADIO_ACID_HIGH = R.id.radio_palate_acid_high

const val RADIO_ALCOHOL_LOW = R.id.radio_palate_alcohol_low
const val RADIO_ALCOHOL_MED_MINUS = R.id.radio_palate_alcohol_med_minus
const val RADIO_ALCOHOL_MED = R.id.radio_palate_alcohol_med
const val RADIO_ALCOHOL_MED_PLUS = R.id.radio_palate_alcohol_med_plus
const val RADIO_ALCOHOL_HIGH = R.id.radio_palate_alcohol_high

const val RADIO_BALANCED_YES = R.id.radio_palate_balanced_yes
const val RADIO_BALANCED_NO = R.id.radio_palate_balanced_no

const val RADIO_FINISH_SHORT = R.id.radio_palate_length_finish_short
const val RADIO_FINISH_MED_MINUS = R.id.radio_palate_length_finish_med_minus
const val RADIO_FINISH_MED = R.id.radio_palate_length_finish_med
const val RADIO_FINISH_MED_PLUS = R.id.radio_palate_length_finish_med_plus
const val RADIO_FINISH_LONG = R.id.radio_palate_length_finish_long

const val RADIO_COMPLEXITY_LOW = R.id.radio_palate_complexity_low
const val RADIO_COMPLEXITY_MED_MINUS = R.id.radio_palate_complexity_med_minus
const val RADIO_COMPLEXITY_MED = R.id.radio_palate_complexity_med
const val RADIO_COMPLEXITY_MED_PLUS = R.id.radio_palate_complexity_med_plus
const val RADIO_COMPLEXITY_HIGH = R.id.radio_palate_complexity_high

const val RADIO_TEXTURE_CREAMY = R.id.radio_palate_texture_creamy
const val RADIO_TEXTURE_ROUND = R.id.radio_palate_texture_round
const val RADIO_TEXTURE_LEAN = R.id.radio_palate_texture_lean

const val RADIO_BODY_LIGHT = R.id.radio_palate_body_light
const val RADIO_BODY_MEDIUM = R.id.radio_palate_body_medium
const val RADIO_BODY_FULL = R.id.radio_palate_body_full

val NoseWoodRadioGroups = listOf(
    RADIO_GROUP_NOSE_WOOD_OLD_VS_NEW,
    RADIO_GROUP_NOSE_WOOD_LARGE_VS_SMALL,
    RADIO_GROUP_NOSE_WOOD_FRENCH_VS_AMERICAN
)

val PalateWoodRadioGroups = listOf(
    RADIO_GROUP_PALATE_WOOD_OLD_VS_NEW,
    RADIO_GROUP_PALATE_WOOD_LARGE_VS_SMALL,
    RADIO_GROUP_PALATE_WOOD_FRENCH_VS_AMERICAN
)

val AllWoodRadioGroups = NoseWoodRadioGroups + PalateWoodRadioGroups

val AllSwitches = listOf(
    SWITCH_NOSE_WOOD,
    SWITCH_PALATE_WOOD
)

val AllAutoText = listOf(
    TEXT_SINGLE_FINAL_GRAPE_VARIETY,
    TEXT_SINGLE_FINAL_COUNTRY_ORIGIN,
    TEXT_SINGLE_FINAL_REGION,
    TEXT_SINGLE_FINAL_QUALITY,
    TEXT_SINGLE_FINAL_VINTAGE
)

val AllAutoMultiText = listOf(
    TEXT_MULTI_INITIAL_GRAPE_VARIETIES,
    TEXT_MULTI_INITIAL_COUNTRIES
)

val AllCheckBoxes = listOf(
    CHECK_FAULT_TCA, CHECK_FAULT_HYDROGEN_SULFIDE, CHECK_FAULT_VOLATILE_ACIDITY,
    CHECK_FAULT_ETHYL_ACETATE, CHECK_FAULT_BRETT, CHECK_FAULT_OXIDIZATION,
    CHECK_FAULT_OTHER,
    CHECK_NOSE_FRUIT_RED, CHECK_NOSE_FRUIT_BLACK, CHECK_NOSE_FRUIT_BLUE,
    CHECK_NOSE_FRUIT_CITRUS, CHECK_NOSE_FRUIT_APPLE_PEAR, CHECK_NOSE_FRUIT_STONE_PIT,
    CHECK_NOSE_FRUIT_TROPICAL, CHECK_NOSE_FRUIT_MELON,
    CHECK_NOSE_FRUIT_CHARACTER_RIPE, CHECK_NOSE_FRUIT_CHARACTER_FRESH,
    CHECK_NOSE_FRUIT_CHARACTER_TART, CHECK_NOSE_FRUIT_CHARACTER_BAKED,
    CHECK_NOSE_FRUIT_CHARACTER_STEWED, CHECK_NOSE_FRUIT_CHARACTER_DRIED,
    CHECK_NOSE_FRUIT_CHARACTER_DESICCATED, CHECK_NOSE_FRUIT_CHARACTER_BRUISED,
    CHECK_NOSE_FRUIT_CHARACTER_JAMMY,
    CHECK_NOSE_NON_FRUIT_FLORAL, CHECK_NOSE_NON_FRUIT_VEGETAL,
    CHECK_NOSE_NON_FRUIT_HERBAL, CHECK_NOSE_NON_FRUIT_SPICE,
    CHECK_NOSE_NON_FRUIT_ANIMAL, CHECK_NOSE_NON_FRUIT_BARN,
    CHECK_NOSE_NON_FRUIT_PETROL, CHECK_NOSE_NON_FRUIT_FERMENTATION,
    CHECK_NOSE_EARTH_FOREST_FLOOR, CHECK_NOSE_EARTH_COMPOST,
    CHECK_NOSE_EARTH_MUSHROOMS, CHECK_NOSE_EARTH_POTTING_SOIL,
    CHECK_NOSE_MINERAL_MINERAL, CHECK_NOSE_MINERAL_WET_STONE,
    CHECK_NOSE_MINERAL_LIMESTONE, CHECK_NOSE_MINERAL_CHALK,
    CHECK_NOSE_MINERAL_SLATE, CHECK_NOSE_MINERAL_FLINT,
    CHECK_PALATE_FRUIT_RED, CHECK_PALATE_FRUIT_BLACK, CHECK_PALATE_FRUIT_BLUE,
    CHECK_PALATE_FRUIT_CITRUS, CHECK_PALATE_FRUIT_APPLE_PEAR,
    CHECK_PALATE_FRUIT_STONE_PIT, CHECK_PALATE_FRUIT_TROPICAL,
    CHECK_PALATE_FRUIT_MELON,
    CHECK_PALATE_FRUIT_CHARACTER_RIPE, CHECK_PALATE_FRUIT_CHARACTER_FRESH,
    CHECK_PALATE_FRUIT_CHARACTER_TART, CHECK_PALATE_FRUIT_CHARACTER_BAKED,
    CHECK_PALATE_FRUIT_CHARACTER_STEWED, CHECK_PALATE_FRUIT_CHARACTER_DRIED,
    CHECK_PALATE_FRUIT_CHARACTER_DESICCATED, CHECK_PALATE_FRUIT_CHARACTER_BRUISED,
    CHECK_PALATE_FRUIT_CHARACTER_JAMMY,
    CHECK_PALATE_NON_FRUIT_FLORAL, CHECK_PALATE_NON_FRUIT_VEGETAL,
    CHECK_PALATE_NON_FRUIT_HERBAL, CHECK_PALATE_NON_FRUIT_SPICE,
    CHECK_PALATE_NON_FRUIT_ANIMAL, CHECK_PALATE_NON_FRUIT_BARN,
    CHECK_PALATE_NON_FRUIT_PETROL, CHECK_PALATE_NON_FRUIT_FERMENTATION,
    CHECK_PALATE_EARTH_FOREST_FLOOR, CHECK_PALATE_EARTH_COMPOST,
    CHECK_PALATE_EARTH_MUSHROOMS, CHECK_PALATE_EARTH_POTTING_SOIL,
    CHECK_PALATE_MINERAL_MINERAL, CHECK_PALATE_MINERAL_WET_STONE,
    CHECK_PALATE_MINERAL_LIMESTONE, CHECK_PALATE_MINERAL_CHALK,
    CHECK_PALATE_MINERAL_SLATE, CHECK_PALATE_MINERAL_FLINT
)

val AllRadioGroups = listOf(
    RADIO_GROUP_CLARITY, RADIO_GROUP_CONCENTRATION, RADIO_GROUP_COLOR_RED_WINE,
    RADIO_GROUP_COLOR_WHITE_WINE, RADIO_GROUP_SECONDARY_COLOR_RED_WINE,
    RADIO_GROUP_SECONDARY_COLOR_WHITE_WINE, RADIO_GROUP_RIM_VARIATION,
    RADIO_GROUP_EXTRACT_STAINING, RADIO_GROUP_TEARING, RADIO_GROUP_GAS_EVIDENCE,
    RADIO_GROUP_NOSE_INTENSITY, RADIO_GROUP_NOSE_AGE_ASSESSMENT,
    RADIO_GROUP_PALATE_SWEETNESS, RADIO_GROUP_PALATE_PHENOLIC_BITTER,
    RADIO_GROUP_PALATE_TANNIN, RADIO_GROUP_PALATE_ACID,
    RADIO_GROUP_PALATE_ALCOHOL, RADIO_GROUP_PALATE_BODY,
    RADIO_GROUP_PALATE_TEXTURE, RADIO_GROUP_PALATE_BALANCE,
    RADIO_GROUP_PALATE_LENGTH_FINISH, RADIO_GROUP_PALATE_COMPLEXITY,
    RADIO_GROUP_INITIAL_WORLD, RADIO_GROUP_INITIAL_CLIMATE,
    RADIO_GROUP_INITIAL_AGE_RANGE
) + AllWoodRadioGroups

val AllRedRadioGroups = listOf(
    RADIO_GROUP_CLARITY, RADIO_GROUP_CONCENTRATION, RADIO_GROUP_COLOR_RED_WINE,
    RADIO_GROUP_SECONDARY_COLOR_RED_WINE, RADIO_GROUP_RIM_VARIATION,
    RADIO_GROUP_EXTRACT_STAINING, RADIO_GROUP_TEARING, RADIO_GROUP_GAS_EVIDENCE,
    RADIO_GROUP_NOSE_INTENSITY, RADIO_GROUP_NOSE_AGE_ASSESSMENT,
    RADIO_GROUP_PALATE_SWEETNESS,
    RADIO_GROUP_PALATE_TANNIN, RADIO_GROUP_PALATE_ACID,
    RADIO_GROUP_PALATE_ALCOHOL, RADIO_GROUP_PALATE_BODY,
    RADIO_GROUP_PALATE_TEXTURE, RADIO_GROUP_PALATE_BALANCE,
    RADIO_GROUP_PALATE_LENGTH_FINISH, RADIO_GROUP_PALATE_COMPLEXITY,
    RADIO_GROUP_INITIAL_WORLD, RADIO_GROUP_INITIAL_CLIMATE,
    RADIO_GROUP_INITIAL_AGE_RANGE
) + AllWoodRadioGroups

val AllWhiteRadioGroups = listOf(
    RADIO_GROUP_CLARITY, RADIO_GROUP_CONCENTRATION,
    RADIO_GROUP_COLOR_WHITE_WINE,
    RADIO_GROUP_SECONDARY_COLOR_WHITE_WINE, RADIO_GROUP_RIM_VARIATION,
     RADIO_GROUP_TEARING, RADIO_GROUP_GAS_EVIDENCE,
    RADIO_GROUP_NOSE_INTENSITY, RADIO_GROUP_NOSE_AGE_ASSESSMENT,
    RADIO_GROUP_PALATE_SWEETNESS, RADIO_GROUP_PALATE_PHENOLIC_BITTER,
    RADIO_GROUP_PALATE_ACID,
    RADIO_GROUP_PALATE_ALCOHOL, RADIO_GROUP_PALATE_BODY,
    RADIO_GROUP_PALATE_TEXTURE, RADIO_GROUP_PALATE_BALANCE,
    RADIO_GROUP_PALATE_LENGTH_FINISH, RADIO_GROUP_PALATE_COMPLEXITY,
    RADIO_GROUP_INITIAL_WORLD, RADIO_GROUP_INITIAL_CLIMATE,
    RADIO_GROUP_INITIAL_AGE_RANGE
) + AllWoodRadioGroups
val finalConclusionViews = AllAutoText

val initialConclusionViews = listOf(
    TEXT_MULTI_INITIAL_GRAPE_VARIETIES,
    TEXT_MULTI_INITIAL_COUNTRIES,
    RADIO_GROUP_INITIAL_WORLD,
    RADIO_GROUP_INITIAL_CLIMATE,
    RADIO_GROUP_INITIAL_AGE_RANGE
)

val redNoseViews = AllRadioGroups + AllCheckBoxes + AllSwitches
val whiteNoseViews = AllRadioGroups + AllCheckBoxes + AllSwitches
val redPalateViews = AllRadioGroups + AllCheckBoxes + AllSwitches
val whitePalateViews = AllRadioGroups + AllCheckBoxes + AllSwitches
val redPalateViewsA = redPalateViews
val whitePalateViewsA = whitePalateViews
val redPalateViewsB = redPalateViews
val whitePalateViewsB = whitePalateViews

val redSightViews = AllRadioGroups + AllCheckBoxes + AllSwitches
val whiteSightViews = AllRadioGroups + AllCheckBoxes + AllSwitches
