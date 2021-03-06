package com.wineguesser.deductive.repository;

// These are the keys that our database must match.
public interface DatabaseContract {

    public String DB_EMAIL_VERIFICATION = "verificationEmailSent";

    String DB_REFERENCE_RED_VARIETY_DESCRIPTORS = "/redVarietyDescriptors";
    String DB_REFERENCE_WHITE_VARIETY_DESCRIPTORS = "/whiteVarietyDescriptors";
    public String DB_REFERENCE_RED_VARIETAL_DATA = "/redVarietyData";
    public String DB_REFERENCE_WHITE_VARIETAL_DATA = "/whiteVarietyData";

    String CLARITY_PREPEND = "clarity";
    String CONCENTRATION_PREPEND = "concentration";
    String COLOR_PREPEND = "color";
    String SECONDARY_COLOR_PREPEND = "secondaryColor";
    String RIM_VARIATION_PREPEND = "rimVariation";
    String STAINING_PREPEND = "staining";
    String TEARING_PREPEND = "tearing";
    String GAS_EVIDENCE_PREPEND = "gasEvidence";
    String INTENSITY_PREPEND = "intensity";
    String AGE_ASSESSMENT_PREPEND = "ageAssessment";
    String WOOD_PREPEND = "oak";
    String SWEETNESS_PREPEND = "sweetness";
    String PHENOLIC_BITTER_PREPEND = "phenolicBitter";
    String TANNIN_PREPEND = "tannin";
    String ACID_PREPEND = "acidity";
    String ALCOHOL_PREPEND = "alcohol";
    String BODY_PREPEND = "body";
    String TEXTURE_PREPEND = "texture";
    String BALANCE_PREPEND = "balanced";
    String FINISH_PREPEND = "finish";
    String COMPLEXITY_PREPEND = "complexity";

    String FAULT_PREPEND = "fault";
    String FRUIT_PREPEND = "fruit";
    String CHARACTER_PREPEND = "character";
    String NON_FRUIT_PREPEND = "nonFruit";
    String EARTH_PREPEND = "earth";
    String MINERAL_PREPEND = "mineral";

    String CLEAR = "Clear";
    String HAZY = "Hazy";
    String TURBID = "Turbid";
    String PALE = "Pale";
    String MEDIUM = "Medium";
    String DEEP = "Deep";
    String PURPLE = "Purple";
    String RUBY = "Ruby";
    String GARNET = "Garnet";
    String STRAW = "Straw";
    String YELLOW = "Yellow";
    String GOLD = "Gold";
    String ORANGE = "Orange";
    String BLUE = "Blue";
    String BROWN = "Brown";
    String SILVER = "Silver";
    String GREEN = "Green";
    String COPPER = "Copper";
    String YES = "Yes";
    String NO = "No";
    String NONE = "None";
    String LIGHT = "Light";
    String HEAVY = "Heavy";
    String DELICATE = "Delicate";
    String MODERATE = "Moderate";
    String POWERFUL = "Powerful";
    String YOUTHFUL = "Youthful";
    String DEVELOPING = "Developing";
    String VINOUS = "Vinous";
    String OLD = "Old";
    String NEW = "New";
    String LARGE = "Large";
    String SMALL = "Small";
    String FRENCH = "French";
    String AMERICAN = "American";
    String BONE_DRY = "BoneDry";
    String DRY = "Dry";
    String OFF_DRY = "OffDry";
    String MEDIUM_SWEET = "MediumSweet";
    String SWEET = "Sweet";
    String LUSCIOUSLY_SWEET = "LusciouslySweet";
    String LOW = "Low";
    String MEDIUM_MINUS = "MediumMinus";
    String MEDIUM_PLUS = "MediumPlus";
    String HIGH = "High";
    String FULL = "Full";
    String CREAMY = "Creamy";
    String ROUND = "Round";
    String LEAN = "Lean";
    String SHORT = "Short";
    String LONG = "Long";
    String TCA = "TCA";
    String HYDROGEN_SULFIDE = "HydrogenSulfide";
    String VOLATILE_ACIDITY = "VolatileAcidity";
    String ETHYL_ACETATE = "EthylAcetate";
    String BRETT = "Brett";
    String OXIDIZATION = "Oxidization";
    String OTHER = "Other";
    String RED = "Red";
    String BLACK = "Black";
    String CITRUS = "Citrus";
    String APPLE_PEAR = "ApplePear";
    String STONE_PIT = "StonePit";
    String TROPICAL = "Tropical";
    String MELON = "Melon";
    String RIPE = "Ripe";
    String FRESH = "Fresh";
    String TART = "Tart";
    String BAKED = "Baked";
    String STEWED = "Stewed";
    String DRIED = "Dried";
    String DESICCATED = "Desiccated";
    String BRUISED = "Bruised";
    String JAMMY = "Jammy";
    String FLORAL = "Floral";
    String VEGETAL = "Vegetal";
    String HERBAL = "Herbal";
    String SPICE = "Spice";
    String ANIMAL = "Animal";
    String BARN = "Barn";
    String PETROL = "Petrol";
    String FERMENTATION = "Fermentation";
    String FOREST_FLOOR = "ForestFloor";
    String COMPOST = "Compost";
    String MUSHROOMS = "Mushrooms";
    String POTTING_SOIL = "PottingSoil";
    String MINERAL = "Mineral";
    String WET_STONE = "WetStone";
    String LIMESTONE = "Limestone";
    String CHALK = "Chalk";
    String SLATE = "Slate";
    String FLINT = "Flint";


    String KEY_CLARITY_CLEAR = CLARITY_PREPEND + CLEAR;
    String KEY_CLARITY_HAZY = CLARITY_PREPEND + HAZY;
    String KEY_CLARITY_TURBID = CLARITY_PREPEND + TURBID;
    String KEY_CONCENTRATION_PALE = CONCENTRATION_PREPEND + PALE;
    String KEY_CONCENTRATION_MEDIUM = CONCENTRATION_PREPEND + MEDIUM;
    String KEY_CONCENTRATION_DEEP = CONCENTRATION_PREPEND + DEEP;
    String KEY_COLOR_PURPLE = COLOR_PREPEND + PURPLE;
    String KEY_COLOR_RUBY = COLOR_PREPEND + RUBY;
    String KEY_COLOR_GARNET = COLOR_PREPEND + GARNET;
    String KEY_COLOR_STRAW = COLOR_PREPEND + STRAW;
    String KEY_COLOR_YELLOW = COLOR_PREPEND + YELLOW;
    String KEY_COLOR_GOLD = COLOR_PREPEND + GOLD;
    String KEY_SECONDARY_COLOR_ORANGE = SECONDARY_COLOR_PREPEND + ORANGE;
    String KEY_SECONDARY_COLOR_BLUE = SECONDARY_COLOR_PREPEND + BLUE;
    String KEY_SECONDARY_COLOR_RUBY = SECONDARY_COLOR_PREPEND + RUBY;
    String KEY_SECONDARY_COLOR_GARNET = SECONDARY_COLOR_PREPEND + GARNET;
    String KEY_SECONDARY_COLOR_BROWN = SECONDARY_COLOR_PREPEND + BROWN;
    String KEY_SECONDARY_COLOR_SILVER = SECONDARY_COLOR_PREPEND + SILVER;
    String KEY_SECONDARY_COLOR_GREEN = SECONDARY_COLOR_PREPEND + GREEN;
    String KEY_SECONDARY_COLOR_COPPER = SECONDARY_COLOR_PREPEND + COPPER;
    String KEY_RIM_VARIATION_YES = RIM_VARIATION_PREPEND + YES;
    String KEY_RIM_VARIATION_NO = RIM_VARIATION_PREPEND + NO;
    String KEY_STAINING_NONE = STAINING_PREPEND + NONE;
    String KEY_STAINING_LIGHT = STAINING_PREPEND + LIGHT;
    String KEY_STAINING_MEDIUM = STAINING_PREPEND + MEDIUM;
    String KEY_STAINING_HEAVY = STAINING_PREPEND + HEAVY;
    String KEY_TEARING_LIGHT = TEARING_PREPEND + LIGHT;
    String KEY_TEARING_MEDIUM = TEARING_PREPEND + MEDIUM;
    String KEY_TEARING_HEAVY = TEARING_PREPEND + HEAVY;
    String KEY_GAS_EVIDENCE_YES = GAS_EVIDENCE_PREPEND + YES;
    String KEY_GAS_EVIDENCE_NO = GAS_EVIDENCE_PREPEND + NO;
    String KEY_INTENSITY_DELICATE = INTENSITY_PREPEND + DELICATE;
    String KEY_INTENSITY_MODERATE = INTENSITY_PREPEND + MODERATE;
    String KEY_INTENSITY_POWERFUL = INTENSITY_PREPEND + POWERFUL;
    String KEY_AGE_ASSESSMENT_YOUTHFUL = AGE_ASSESSMENT_PREPEND + YOUTHFUL;
    String KEY_AGE_ASSESSMENT_DEVELOPING = AGE_ASSESSMENT_PREPEND + DEVELOPING;
    String KEY_AGE_ASSESSMENT_VINOUS = AGE_ASSESSMENT_PREPEND + VINOUS;
    String KEY_WOOD_OLD = WOOD_PREPEND + OLD;
    String KEY_WOOD_NEW = WOOD_PREPEND + NEW;
    String KEY_WOOD_LARGE = WOOD_PREPEND + LARGE;
    String KEY_WOOD_SMALL = WOOD_PREPEND + SMALL;
    String KEY_WOOD_FRENCH = WOOD_PREPEND + FRENCH;
    String KEY_WOOD_AMERICAN = WOOD_PREPEND + AMERICAN;
    String KEY_SWEETNESS_BONE_DRY = SWEETNESS_PREPEND + BONE_DRY;
    String KEY_SWEETNESS_OFF_DRY = SWEETNESS_PREPEND + OFF_DRY;
    String KEY_SWEETNESS_DRY = SWEETNESS_PREPEND + DRY;
    String KEY_SWEETNESS_MEDIUM_SWEET = SWEETNESS_PREPEND + MEDIUM_SWEET;
    String KEY_SWEETNESS_SWEET = SWEETNESS_PREPEND + SWEET;
    String KEY_SWEETNESS_LUSCIOUSLY_SWEET = SWEETNESS_PREPEND + LUSCIOUSLY_SWEET;
    String KEY_PHENOLIC_BITTER_YES = PHENOLIC_BITTER_PREPEND + YES;
    String KEY_PHENOLIC_BITTER_NO = PHENOLIC_BITTER_PREPEND + NO;
    String KEY_TANNIN_LOW = TANNIN_PREPEND + LOW;
    String KEY_TANNIN_MEDIUM_MINUS = TANNIN_PREPEND + MEDIUM_MINUS;
    String KEY_TANNIN_MEDIUM = TANNIN_PREPEND + MEDIUM;
    String KEY_TANNIN_MEDIUM_PLUS = TANNIN_PREPEND + MEDIUM_PLUS;
    String KEY_TANNIN_HIGH = TANNIN_PREPEND + HIGH;
    String KEY_ACID_LOW = ACID_PREPEND + LOW;
    String KEY_ACID_MEDIUM_MINUS = ACID_PREPEND + MEDIUM_MINUS;
    String KEY_ACID_MEDIUM = ACID_PREPEND + MEDIUM;
    String KEY_ACID_MEDIUM_PLUS = ACID_PREPEND + MEDIUM_PLUS;
    String KEY_ACID_HIGH = ACID_PREPEND + HIGH;
    String KEY_ALCOHOL_LOW = ALCOHOL_PREPEND + LOW;
    String KEY_ALCOHOL_MEDIUM_MINUS = ALCOHOL_PREPEND + MEDIUM_MINUS;
    String KEY_ALCOHOL_MEDIUM = ALCOHOL_PREPEND + MEDIUM;
    String KEY_ALCOHOL_MEDIUM_PLUS = ALCOHOL_PREPEND + MEDIUM_PLUS;
    String KEY_ALCOHOL_HIGH = ALCOHOL_PREPEND + HIGH;
    String KEY_BODY_LIGHT = BODY_PREPEND + LIGHT;
    String KEY_BODY_MEDIUM = BODY_PREPEND + MEDIUM;
    String KEY_BODY_FULL = BODY_PREPEND + FULL;
    String KEY_TEXTURE_CREAMY = TEXTURE_PREPEND + CREAMY;
    String KEY_TEXTURE_ROUND = TEXTURE_PREPEND + ROUND;
    String KEY_TEXTURE_LEAN = TEXTURE_PREPEND + LEAN;
    String KEY_BALANCED_YES = BALANCE_PREPEND + YES;
    String KEY_BALANCED_NO = BALANCE_PREPEND + NO;
    String KEY_FINISH_SHORT = FINISH_PREPEND + SHORT;
    String KEY_FINISH_MEDIUM_MINUS = FINISH_PREPEND + MEDIUM_MINUS;
    String KEY_FINISH_MEDIUM = FINISH_PREPEND + MEDIUM;
    String KEY_FINISH_MEDIUM_PLUS = FINISH_PREPEND + MEDIUM_PLUS;
    String KEY_FINISH_LONG = FINISH_PREPEND + LONG;
    String KEY_COMPLEXITY_LOW = COMPLEXITY_PREPEND + LOW;
    String KEY_COMPLEXITY_MEDIUM_MINUS = COMPLEXITY_PREPEND + MEDIUM_MINUS;
    String KEY_COMPLEXITY_MEDIUM = COMPLEXITY_PREPEND + MEDIUM;
    String KEY_COMPLEXITY_MEDIUM_PLUS = COMPLEXITY_PREPEND + MEDIUM_PLUS;
    String KEY_COMPLEXITY_HIGH = COMPLEXITY_PREPEND + HIGH;


    String KEY_FAULT_TCA = FAULT_PREPEND + TCA;
    String KEY_FAULT_HYDROGEN_SULFIDE = FAULT_PREPEND + HYDROGEN_SULFIDE;
    String KEY_FAULT_VOLATILE_ACIDITY = FAULT_PREPEND + VOLATILE_ACIDITY;
    String KEY_FAULT_ETHYL_ACETATE = FAULT_PREPEND + ETHYL_ACETATE;
    String KEY_FAULT_BRETT = FAULT_PREPEND + BRETT;
    String KEY_FAULT_OXIDIZATION = FAULT_PREPEND + OXIDIZATION;
    String KEY_FAULT_OTHER = FAULT_PREPEND + OTHER;
    String KEY_FRUIT_RED = FRUIT_PREPEND + RED;
    String KEY_FRUIT_BLACK = FRUIT_PREPEND + BLACK;
    String KEY_FRUIT_BLUE = FRUIT_PREPEND + BLUE;
    String KEY_FRUIT_CITRUS = FRUIT_PREPEND + CITRUS;
    String KEY_FRUIT_APPLE_PEAR = FRUIT_PREPEND + APPLE_PEAR;
    String KEY_FRUIT_STONE_PIT = FRUIT_PREPEND + STONE_PIT;
    String KEY_FRUIT_TROPICAL = FRUIT_PREPEND + TROPICAL;
    String KEY_FRUIT_MELON = FRUIT_PREPEND + MELON;
    String KEY_FRUIT_CHARACTER_RIPE = CHARACTER_PREPEND + RIPE;
    String KEY_FRUIT_CHARACTER_FRESH = CHARACTER_PREPEND + FRESH;
    String KEY_FRUIT_CHARACTER_TART = CHARACTER_PREPEND + TART;
    String KEY_FRUIT_CHARACTER_BAKED = CHARACTER_PREPEND + BAKED;
    String KEY_FRUIT_CHARACTER_STEWED = CHARACTER_PREPEND + STEWED;
    String KEY_FRUIT_CHARACTER_DRIED = CHARACTER_PREPEND + DRIED;
    String KEY_FRUIT_CHARACTER_DESICCATED = CHARACTER_PREPEND + DESICCATED;
    String KEY_FRUIT_CHARACTER_BRUISED = CHARACTER_PREPEND + BRUISED;
    String KEY_FRUIT_CHARACTER_JAMMY = CHARACTER_PREPEND + JAMMY;
    String KEY_NON_FRUIT_FLORAL = NON_FRUIT_PREPEND + FLORAL;
    String KEY_NON_FRUIT_VEGETAL = NON_FRUIT_PREPEND + VEGETAL;
    String KEY_NON_FRUIT_HERBAL = NON_FRUIT_PREPEND + HERBAL;
    String KEY_NON_FRUIT_SPICE = NON_FRUIT_PREPEND + SPICE;
    String KEY_NON_FRUIT_ANIMAL = NON_FRUIT_PREPEND + ANIMAL;
    String KEY_NON_FRUIT_BARN = NON_FRUIT_PREPEND + BARN;
    String KEY_NON_FRUIT_PETROL = NON_FRUIT_PREPEND + PETROL;
    String KEY_NON_FRUIT_FERMENTATION = NON_FRUIT_PREPEND + FERMENTATION;
    String KEY_EARTH_FOREST_FLOOR = EARTH_PREPEND + FOREST_FLOOR;
    String KEY_EARTH_COMPOST = EARTH_PREPEND + COMPOST;
    String KEY_EARTH_MUSHROOMS = EARTH_PREPEND + MUSHROOMS;
    String KEY_EARTH_POTTING_SOIL = EARTH_PREPEND + POTTING_SOIL;
    String KEY_MINERAL_MINERAL = MINERAL_PREPEND + MINERAL;
    String KEY_MINERAL_WET_STONE = MINERAL_PREPEND + WET_STONE;
    String KEY_MINERAL_LIMESTONE = MINERAL_PREPEND + LIMESTONE;
    String KEY_MINERAL_CHALK = MINERAL_PREPEND + CHALK;
    String KEY_MINERAL_SLATE = MINERAL_PREPEND + SLATE;
    String KEY_MINERAL_FLINT = MINERAL_PREPEND + FLINT;
}
