package com.wineguesser.deductive.repository;

// These are the keys that our database must match.
public interface RepoKeyContract {

    String CLARITY_PREPEND = "clarity_";
    String CONCENTRATION_PREPEND = "concentration_";
    String COLOR_PREPEND = "color_";
    String SECONDARY_COLOR_PREPEND = "secondary_color_";
    String RIM_VARIATION_PREPEND = "rim_variation_";
    String STAINING_PREPEND = "staining_";
    String TEARING_PREPEND = "tearing_";
    String GAS_EVIDENCE_PREPEND = "gas_evidence_";
    String INTENSITY_PREPEND = "intensity_";
    String AGE_ASSESSMENT_PREPEND = "age_assessment_";
    String WOOD_PREPEND = "oak_";
    String SWEETNESS_PREPEND = "sweetness_";
    String PHENOLIC_BITTER_PREPEND = "phenolic_bitter_";
    String TANNIN_PREPEND = "tannin_";
    String ACID_PREPEND = "acidity_";
    String ALCOHOL_PREPEND = "alcohol_";
    String BODY_PREPEND = "body_";
    String TEXTURE_PREPEND = "texture_";
    String BALANCE_PREPEND = "balanced_";
    String FINISH_PREPEND = "finish_";
    String COMPLEXITY_PREPEND = "complexity";

    String FAULT_PREPEND = "fault_";
    String FRUIT_PREPEND = "fruit_";
    String FRUIT_CHARACTER_PREPEND = "fruit_character_";
    String NON_FRUIT_PREPEND = "non-fruit_";
    String EARTH_PREPEND = "earth_";
    String MINERAL_PREPEND = "mineral_";

    String CLEAR = "clear";
    String HAZY = "hazy";
    String TURBID = "turbid";
    String PALE = "pale";
    String MEDIUM = "medium";
    String DEEP = "deep";
    String PURPLE = "purple";
    String RUBY = "ruby";
    String GARNET = "garnet";
    String STRAW = "straw";
    String YELLOW = "yellow";
    String GOLD = "gold";
    String ORANGE = "orange";
    String BLUE = "blue";
    String BROWN = "brown";
    String SILVER = "silver";
    String GREEN = "green";
    String COPPER = "copper";
    String YES = "yes";
    String NO = "no";
    String NONE = "none";
    String LIGHT = "light";
    String HEAVY = "heavy";
    String DELICATE = "delicate";
    String MODERATE = "moderate";
    String POWERFUL = "powerful";
    String YOUTHFUL = "youthful";
    String DEVELOPING = "developing";
    String VINOUS = "vinous";
    String OLD = "old";
    String NEW = "new";
    String LARGE = "large";
    String SMALL = "small";
    String FRENCH = "french";
    String AMERICAN = "american";
    String BONE_DRY = "bone_dry";
    String DRY = "dry";
    String OFF_DRY = "off_dry";
    String MEDIUM_SWEET = "medium_sweet";
    String SWEET = "sweet";
    String LUSCIOUSLY_SWEET = "lusciously_sweet";
    String LOW = "low";
    String MEDIUM_MINUS = "medium_minus";
    String MEDIUM_PLUS = "medium_plus";
    String HIGH = "high";
    String FULL = "full";
    String CREAMY = "creamy";
    String ROUND = "round";
    String LEAN = "lean";
    String SHORT = "short";
    String LONG = "long";
    String TCA = "TCA";
    String HYDROGEN_SULFIDE = "hydrogen_sulfide";
    String VOLATILE_ACIDITY = "volatile_acidity";
    String ETHYL_ACETATE = "ethyl_acetate";
    String BRETT = "brett";
    String OXIDIZATION = "oxidization";
    String OTHER = "other";
    String RED = "red";
    String BLACK = "black";
    String CITRUS = "citrus";
    String APPLE_PEAR = "apple_pear";
    String STONE_PIT = "stone_pit";
    String TROPICAL = "tropical";
    String MELON = "melon";
    String RIPE = "ripe";
    String FRESH = "fresh";
    String TART = "tart";
    String BAKED = "baked";
    String STEWED = "stewed";
    String DRIED = "dried";
    String DESICCATED = "desiccated";
    String BRUISED = "bruised";
    String JAMMY = "jammy";
    String FLORAL = "floral";
    String VEGETAL = "vegetal";
    String HERBAL = "herbal";
    String SPICE = "spice";
    String ANIMAL = "animal";
    String BARN = "barn";
    String PETROL = "petrol";
    String FERMENTATION = "fermentation";
    String FOREST_FLOOR = "forest_floor";
    String COMPOST = "compost";
    String MUSHROOMS = "mushrooms";
    String POTTING_SOIL = "potting_soil";
    String MINERAL = "mineral";
    String WET_STONE = "wet_stone";
    String LIMESTONE = "limestone";
    String CHALK = "chalk";
    String SLATE = "slate";
    String FLINT = "flint";


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
    String KEY_FRUIT_CHARACTER_RIPE = FRUIT_CHARACTER_PREPEND + RIPE;
    String KEY_FRUIT_CHARACTER_FRESH = FRUIT_CHARACTER_PREPEND + FRESH;
    String KEY_FRUIT_CHARACTER_TART = FRUIT_CHARACTER_PREPEND + TART;
    String KEY_FRUIT_CHARACTER_BAKED = FRUIT_CHARACTER_PREPEND + BAKED;
    String KEY_FRUIT_CHARACTER_STEWED = FRUIT_CHARACTER_PREPEND + STEWED;
    String KEY_FRUIT_CHARACTER_DRIED = FRUIT_CHARACTER_PREPEND + DRIED;
    String KEY_FRUIT_CHARACTER_DESICCATED = FRUIT_CHARACTER_PREPEND + DESICCATED;
    String KEY_FRUIT_CHARACTER_BRUISED = FRUIT_CHARACTER_PREPEND + BRUISED;
    String KEY_FRUIT_CHARACTER_JAMMY = FRUIT_CHARACTER_PREPEND + JAMMY;
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
