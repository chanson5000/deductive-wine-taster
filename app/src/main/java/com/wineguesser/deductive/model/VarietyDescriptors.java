package com.wineguesser.deductive.model;

import com.google.firebase.database.Exclude;
import com.wineguesser.deductive.repository.DatabaseContract;

abstract class VarietyDescriptors implements DatabaseContract {

    @Exclude
    private String varietyId;

    private int clarityClear;
    private int clarityHazy;
    private int clarityTurbid;
    private int concentrationPale;
    private int concentrationMedium;
    private int concentrationDeep;
    // Colors are delegated separately
    private int rimVariationYes;
    private int rimVariationNo;
    private int stainingNone;
    private int stainingLight;
    private int stainingMedium;
    private int stainingHeavy;
    private int tearingLight;
    private int tearingMedium;
    private int tearingHeavy;
    private int gasEvidenceYes;
    private int gasEvidenceNo;
    private int intensityDelicate;
    private int intensityModerate;
    private int intensityPowerful;
    private int ageAssessmentYouthful;
    private int ageAssessmentDeveloping;
    private int ageAssessmentVinous;
    private int oakOld;
    private int oakNew;
    private int oakLarge;
    private int oakSmall;
    private int oakFrench;
    private int oakAmerican;
    private int sweetnessBoneDry;
    private int sweetnessDry;
    private int sweetnessMediumSweet;
    private int sweetnessSweet;
    private int sweetnessLusciouslySweet;
    // Phenolic bitter and tannins are delgated.
    private int acidityLow;
    private int acidityMediumMinus;
    private int acidityMedium;
    private int acidityMediumPlus;
    private int acidityHigh;
    private int alcoholLow;
    private int alcoholMediumMinus;
    private int alcoholMedium;
    private int alcoholMediumPlus;
    private int alcoholHigh;
    private int bodyLight;
    private int bodyMedium;
    private int bodyFull;
    private int textureCreamy;
    private int textureRound;
    private int textureLean;
    private int balancedYes;
    private int balancedNo;
    private int finishShort;
    private int finishMediumMinus;
    private int finishMedium;
    private int finishMediumPlus;
    private int finishLong;
    private int complexityLow;
    private int complexityMediumMinus;
    private int complexityMedium;
    private int complexityMediumPlus;
    private int complexityHigh;

    private int faultTCA;
    private int faultHydorgenSulfide;
    private int faultVolatileAcidity;
    private int faultEthylAcetate;
    private int faultBrett;
    private int faultOxidization;
    private int faultOther;
    // Fruit types are delegated.
    private int characterRipe;
    private int characterFresh;
    private int characterTart;
    private int characterBaked;
    private int characterStewed;
    private int characterDried;
    private int characterDesiccated;
    private int characterBruised;
    private int characterJammy;
    private int nonFruitFloral;
    private int nonFruitVegetal;
    private int nonFruitHerbal;
    private int nonFruitSpice;
    private int nonFruitAnimal;
    private int nonFruitBarn;
    private int nonFruitPetrol;
    private int nonFruitFermentation;
    private int earthForestFloor;
    private int earthCompost;
    private int earthMushrooms;
    private int earthPottingSoil;
    private int mineralMineral;
    private int mineralWetStone;
    private int mineralLimestone;
    private int mineralChalk;
    private int mineralSlate;
    private int mineralFlint;

    VarietyDescriptors() {

    }

    public String getVarietyId() {
        return varietyId;
    }

    public void setVarietyId(String varietyId) {
        this.varietyId = varietyId;
    }

    public int getClarityClear() {
        return clarityClear;
    }

    public void setClarityClear(int clarityClear) {
        this.clarityClear = clarityClear;
    }

    public int getClarityHazy() {
        return clarityHazy;
    }

    public void setClarityHazy(int clarityHazy) {
        this.clarityHazy = clarityHazy;
    }

    public int getClarityTurbid() {
        return clarityTurbid;
    }

    public void setClarityTurbid(int clarityTurbid) {
        this.clarityTurbid = clarityTurbid;
    }

    public int getConcentrationPale() {
        return concentrationPale;
    }

    public void setConcentrationPale(int concentrationPale) {
        this.concentrationPale = concentrationPale;
    }

    public int getConcentrationMedium() {
        return concentrationMedium;
    }

    public void setConcentrationMedium(int concentrationMedium) {
        this.concentrationMedium = concentrationMedium;
    }

    public int getConcentrationDeep() {
        return concentrationDeep;
    }

    public void setConcentrationDeep(int concentrationDeep) {
        this.concentrationDeep = concentrationDeep;
    }

    public int getRimVariationYes() {
        return rimVariationYes;
    }

    public void setRimVariationYes(int rimVariationYes) {
        this.rimVariationYes = rimVariationYes;
    }

    public int getRimVariationNo() {
        return rimVariationNo;
    }

    public void setRimVariationNo(int rimVariationNo) {
        this.rimVariationNo = rimVariationNo;
    }

    public int getStainingNone() {
        return stainingNone;
    }

    public void setStainingNone(int stainingNone) {
        this.stainingNone = stainingNone;
    }

    public int getStainingLight() {
        return stainingLight;
    }

    public void setStainingLight(int stainingLight) {
        this.stainingLight = stainingLight;
    }

    public int getStainingMedium() {
        return stainingMedium;
    }

    public void setStainingMedium(int stainingMedium) {
        this.stainingMedium = stainingMedium;
    }

    public int getStainingHeavy() {
        return stainingHeavy;
    }

    public void setStainingHeavy(int stainingHeavy) {
        this.stainingHeavy = stainingHeavy;
    }

    public int getTearingLight() {
        return tearingLight;
    }

    public void setTearingLight(int tearingLight) {
        this.tearingLight = tearingLight;
    }

    public int getTearingMedium() {
        return tearingMedium;
    }

    public void setTearingMedium(int tearingMedium) {
        this.tearingMedium = tearingMedium;
    }

    public int getTearingHeavy() {
        return tearingHeavy;
    }

    public void setTearingHeavy(int tearingHeavy) {
        this.tearingHeavy = tearingHeavy;
    }

    public int getGasEvidenceYes() {
        return gasEvidenceYes;
    }

    public void setGasEvidenceYes(int gasEvidenceYes) {
        this.gasEvidenceYes = gasEvidenceYes;
    }

    public int getGasEvidenceNo() {
        return gasEvidenceNo;
    }

    public void setGasEvidenceNo(int gasEvidenceNo) {
        this.gasEvidenceNo = gasEvidenceNo;
    }

    public int getIntensityDelicate() {
        return intensityDelicate;
    }

    public void setIntensityDelicate(int intensityDelicate) {
        this.intensityDelicate = intensityDelicate;
    }

    public int getIntensityModerate() {
        return intensityModerate;
    }

    public void setIntensityModerate(int intensityModerate) {
        this.intensityModerate = intensityModerate;
    }

    public int getIntensityPowerful() {
        return intensityPowerful;
    }

    public void setIntensityPowerful(int intensityPowerful) {
        this.intensityPowerful = intensityPowerful;
    }

    public int getAgeAssessmentYouthful() {
        return ageAssessmentYouthful;
    }

    public void setAgeAssessmentYouthful(int ageAssessmentYouthful) {
        this.ageAssessmentYouthful = ageAssessmentYouthful;
    }

    public int getAgeAssessmentDeveloping() {
        return ageAssessmentDeveloping;
    }

    public void setAgeAssessmentDeveloping(int ageAssessmentDeveloping) {
        this.ageAssessmentDeveloping = ageAssessmentDeveloping;
    }

    public int getAgeAssessmentVinous() {
        return ageAssessmentVinous;
    }

    public void setAgeAssessmentVinous(int ageAssessmentVinous) {
        this.ageAssessmentVinous = ageAssessmentVinous;
    }

    public int getOakOld() {
        return oakOld;
    }

    public void setOakOld(int oakOld) {
        this.oakOld = oakOld;
    }

    public int getOakNew() {
        return oakNew;
    }

    public void setOakNew(int oakNew) {
        this.oakNew = oakNew;
    }

    public int getOakLarge() {
        return oakLarge;
    }

    public void setOakLarge(int oakLarge) {
        this.oakLarge = oakLarge;
    }

    public int getOakSmall() {
        return oakSmall;
    }

    public void setOakSmall(int oakSmall) {
        this.oakSmall = oakSmall;
    }

    public int getOakFrench() {
        return oakFrench;
    }

    public void setOakFrench(int oakFrench) {
        this.oakFrench = oakFrench;
    }

    public int getOakAmerican() {
        return oakAmerican;
    }

    public void setOakAmerican(int oakAmerican) {
        this.oakAmerican = oakAmerican;
    }

    public int getSweetnessBoneDry() {
        return sweetnessBoneDry;
    }

    public void setSweetnessBoneDry(int sweetnessBoneDry) {
        this.sweetnessBoneDry = sweetnessBoneDry;
    }

    public int getSweetnessDry() {
        return sweetnessDry;
    }

    public void setSweetnessDry(int sweetnessDry) {
        this.sweetnessDry = sweetnessDry;
    }

    public int getSweetnessMediumSweet() {
        return sweetnessMediumSweet;
    }

    public void setSweetnessMediumSweet(int sweetnessMediumSweet) {
        this.sweetnessMediumSweet = sweetnessMediumSweet;
    }

    public int getSweetnessSweet() {
        return sweetnessSweet;
    }

    public void setSweetnessSweet(int sweetnessSweet) {
        this.sweetnessSweet = sweetnessSweet;
    }

    public int getSweetnessLusciouslySweet() {
        return sweetnessLusciouslySweet;
    }

    public void setSweetnessLusciouslySweet(int sweetnessLusciouslySweet) {
        this.sweetnessLusciouslySweet = sweetnessLusciouslySweet;
    }

    public int getAcidityLow() {
        return acidityLow;
    }

    public void setAcidityLow(int acidityLow) {
        this.acidityLow = acidityLow;
    }

    public int getAcidityMediumMinus() {
        return acidityMediumMinus;
    }

    public void setAcidityMediumMinus(int acidityMediumMinus) {
        this.acidityMediumMinus = acidityMediumMinus;
    }

    public int getAcidityMedium() {
        return acidityMedium;
    }

    public void setAcidityMedium(int acidityMedium) {
        this.acidityMedium = acidityMedium;
    }

    public int getAcidityMediumPlus() {
        return acidityMediumPlus;
    }

    public void setAcidityMediumPlus(int acidityMediumPlus) {
        this.acidityMediumPlus = acidityMediumPlus;
    }

    public int getAcidityHigh() {
        return acidityHigh;
    }

    public void setAcidityHigh(int acidityHigh) {
        this.acidityHigh = acidityHigh;
    }

    public int getAlcoholLow() {
        return alcoholLow;
    }

    public void setAlcoholLow(int alcoholLow) {
        this.alcoholLow = alcoholLow;
    }

    public int getAlcoholMediumMinus() {
        return alcoholMediumMinus;
    }

    public void setAlcoholMediumMinus(int alcoholMediumMinus) {
        this.alcoholMediumMinus = alcoholMediumMinus;
    }

    public int getAlcoholMedium() {
        return alcoholMedium;
    }

    public void setAlcoholMedium(int alcoholMedium) {
        this.alcoholMedium = alcoholMedium;
    }

    public int getAlcoholMediumPlus() {
        return alcoholMediumPlus;
    }

    public void setAlcoholMediumPlus(int alcoholMediumPlus) {
        this.alcoholMediumPlus = alcoholMediumPlus;
    }

    public int getAlcoholHigh() {
        return alcoholHigh;
    }

    public void setAlcoholHigh(int alcoholHigh) {
        this.alcoholHigh = alcoholHigh;
    }

    public int getBodyLight() {
        return bodyLight;
    }

    public void setBodyLight(int bodyLight) {
        this.bodyLight = bodyLight;
    }

    public int getBodyMedium() {
        return bodyMedium;
    }

    public void setBodyMedium(int bodyMedium) {
        this.bodyMedium = bodyMedium;
    }

    public int getBodyFull() {
        return bodyFull;
    }

    public void setBodyFull(int bodyFull) {
        this.bodyFull = bodyFull;
    }

    public int getTextureCreamy() {
        return textureCreamy;
    }

    public void setTextureCreamy(int textureCreamy) {
        this.textureCreamy = textureCreamy;
    }

    public int getTextureRound() {
        return textureRound;
    }

    public void setTextureRound(int textureRound) {
        this.textureRound = textureRound;
    }

    public int getTextureLean() {
        return textureLean;
    }

    public void setTextureLean(int textureLean) {
        this.textureLean = textureLean;
    }

    public int getBalancedYes() {
        return balancedYes;
    }

    public void setBalancedYes(int balancedYes) {
        this.balancedYes = balancedYes;
    }

    public int getBalancedNo() {
        return balancedNo;
    }

    public void setBalancedNo(int balancedNo) {
        this.balancedNo = balancedNo;
    }

    public int getFinishShort() {
        return finishShort;
    }

    public void setFinishShort(int finishShort) {
        this.finishShort = finishShort;
    }

    public int getFinishMediumMinus() {
        return finishMediumMinus;
    }

    public void setFinishMediumMinus(int finishMediumMinus) {
        this.finishMediumMinus = finishMediumMinus;
    }

    public int getFinishMedium() {
        return finishMedium;
    }

    public void setFinishMedium(int finishMedium) {
        this.finishMedium = finishMedium;
    }

    public int getFinishMediumPlus() {
        return finishMediumPlus;
    }

    public void setFinishMediumPlus(int finishMediumPlus) {
        this.finishMediumPlus = finishMediumPlus;
    }

    public int getFinishLong() {
        return finishLong;
    }

    public void setFinishLong(int finishLong) {
        this.finishLong = finishLong;
    }

    public int getComplexityLow() {
        return complexityLow;
    }

    public void setComplexityLow(int complexityLow) {
        this.complexityLow = complexityLow;
    }

    public int getComplexityMediumMinus() {
        return complexityMediumMinus;
    }

    public void setComplexityMediumMinus(int complexityMediumMinus) {
        this.complexityMediumMinus = complexityMediumMinus;
    }

    public int getComplexityMedium() {
        return complexityMedium;
    }

    public void setComplexityMedium(int complexityMedium) {
        this.complexityMedium = complexityMedium;
    }

    public int getComplexityMediumPlus() {
        return complexityMediumPlus;
    }

    public void setComplexityMediumPlus(int complexityMediumPlus) {
        this.complexityMediumPlus = complexityMediumPlus;
    }

    public int getComplexityHigh() {
        return complexityHigh;
    }

    public void setComplexityHigh(int complexityHigh) {
        this.complexityHigh = complexityHigh;
    }

    public int getFaultTCA() {
        return faultTCA;
    }

    public void setFaultTCA(int faultTCA) {
        this.faultTCA = faultTCA;
    }

    public int getFaultHydorgenSulfide() {
        return faultHydorgenSulfide;
    }

    public void setFaultHydorgenSulfide(int faultHydorgenSulfide) {
        this.faultHydorgenSulfide = faultHydorgenSulfide;
    }

    public int getFaultVolatileAcidity() {
        return faultVolatileAcidity;
    }

    public void setFaultVolatileAcidity(int faultVolatileAcidity) {
        this.faultVolatileAcidity = faultVolatileAcidity;
    }

    public int getFaultEthylAcetate() {
        return faultEthylAcetate;
    }

    public void setFaultEthylAcetate(int faultEthylAcetate) {
        this.faultEthylAcetate = faultEthylAcetate;
    }

    public int getFaultBrett() {
        return faultBrett;
    }

    public void setFaultBrett(int faultBrett) {
        this.faultBrett = faultBrett;
    }

    public int getFaultOxidization() {
        return faultOxidization;
    }

    public void setFaultOxidization(int faultOxidization) {
        this.faultOxidization = faultOxidization;
    }

    public int getFaultOther() {
        return faultOther;
    }

    public void setFaultOther(int faultOther) {
        this.faultOther = faultOther;
    }

    public int getCharacterRipe() {
        return characterRipe;
    }

    public void setCharacterRipe(int characterRipe) {
        this.characterRipe = characterRipe;
    }

    public int getCharacterFresh() {
        return characterFresh;
    }

    public void setCharacterFresh(int characterFresh) {
        this.characterFresh = characterFresh;
    }

    public int getCharacterTart() {
        return characterTart;
    }

    public void setCharacterTart(int characterTart) {
        this.characterTart = characterTart;
    }

    public int getCharacterBaked() {
        return characterBaked;
    }

    public void setCharacterBaked(int characterBaked) {
        this.characterBaked = characterBaked;
    }

    public int getCharacterStewed() {
        return characterStewed;
    }

    public void setCharacterStewed(int characterStewed) {
        this.characterStewed = characterStewed;
    }

    public int getCharacterDried() {
        return characterDried;
    }

    public void setCharacterDried(int characterDried) {
        this.characterDried = characterDried;
    }

    public int getCharacterDesiccated() {
        return characterDesiccated;
    }

    public void setCharacterDesiccated(int characterDesiccated) {
        this.characterDesiccated = characterDesiccated;
    }

    public int getCharacterBruised() {
        return characterBruised;
    }

    public void setCharacterBruised(int characterBruised) {
        this.characterBruised = characterBruised;
    }

    public int getCharacterJammy() {
        return characterJammy;
    }

    public void setCharacterJammy(int characterJammy) {
        this.characterJammy = characterJammy;
    }

    public int getNonFruitFloral() {
        return nonFruitFloral;
    }

    public void setNonFruitFloral(int nonFruitFloral) {
        this.nonFruitFloral = nonFruitFloral;
    }

    public int getNonFruitVegetal() {
        return nonFruitVegetal;
    }

    public void setNonFruitVegetal(int nonFruitVegetal) {
        this.nonFruitVegetal = nonFruitVegetal;
    }

    public int getNonFruitHerbal() {
        return nonFruitHerbal;
    }

    public void setNonFruitHerbal(int nonFruitHerbal) {
        this.nonFruitHerbal = nonFruitHerbal;
    }

    public int getNonFruitSpice() {
        return nonFruitSpice;
    }

    public void setNonFruitSpice(int nonFruitSpice) {
        this.nonFruitSpice = nonFruitSpice;
    }

    public int getNonFruitAnimal() {
        return nonFruitAnimal;
    }

    public void setNonFruitAnimal(int nonFruitAnimal) {
        this.nonFruitAnimal = nonFruitAnimal;
    }

    public int getNonFruitBarn() {
        return nonFruitBarn;
    }

    public void setNonFruitBarn(int nonFruitBarn) {
        this.nonFruitBarn = nonFruitBarn;
    }

    public int getNonFruitPetrol() {
        return nonFruitPetrol;
    }

    public void setNonFruitPetrol(int nonFruitPetrol) {
        this.nonFruitPetrol = nonFruitPetrol;
    }

    public int getNonFruitFermentation() {
        return nonFruitFermentation;
    }

    public void setNonFruitFermentation(int nonFruitFermentation) {
        this.nonFruitFermentation = nonFruitFermentation;
    }

    public int getEarthForestFloor() {
        return earthForestFloor;
    }

    public void setEarthForestFloor(int earthForestFloor) {
        this.earthForestFloor = earthForestFloor;
    }

    public int getEarthCompost() {
        return earthCompost;
    }

    public void setEarthCompost(int earthCompost) {
        this.earthCompost = earthCompost;
    }

    public int getEarthMushrooms() {
        return earthMushrooms;
    }

    public void setEarthMushrooms(int earthMushrooms) {
        this.earthMushrooms = earthMushrooms;
    }

    public int getEarthPottingSoil() {
        return earthPottingSoil;
    }

    public void setEarthPottingSoil(int earthPottingSoil) {
        this.earthPottingSoil = earthPottingSoil;
    }

    public int getMineralMineral() {
        return mineralMineral;
    }

    public void setMineralMineral(int mineralMineral) {
        this.mineralMineral = mineralMineral;
    }

    public int getMineralWetStone() {
        return mineralWetStone;
    }

    public void setMineralWetStone(int mineralWetStone) {
        this.mineralWetStone = mineralWetStone;
    }

    public int getMineralLimestone() {
        return mineralLimestone;
    }

    public void setMineralLimestone(int mineralLimestone) {
        this.mineralLimestone = mineralLimestone;
    }

    public int getMineralChalk() {
        return mineralChalk;
    }

    public void setMineralChalk(int mineralChalk) {
        this.mineralChalk = mineralChalk;
    }

    public int getMineralSlate() {
        return mineralSlate;
    }

    public void setMineralSlate(int mineralSlate) {
        this.mineralSlate = mineralSlate;
    }

    public int getMineralFlint() {
        return mineralFlint;
    }

    public void setMineralFlint(int mineralFlint) {
        this.mineralFlint = mineralFlint;
    }
}
