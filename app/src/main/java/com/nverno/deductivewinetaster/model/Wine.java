package com.nverno.deductivewinetaster.model;

public abstract class Wine {

    private Clarity clarity = new Clarity();
    private Concentration concentration = new Concentration();
    private Tearing tearing = new Tearing();
    private Fault fault = new Fault();
    private Intensity intensity = new Intensity();
    private AgeAssessment ageAssessment = new AgeAssessment();
    private FruitCharacter fruitCharacter = new FruitCharacter();
    private Earth earth = new Earth();
    private Mineral mineral = new Mineral();
    private Oak oak = new Oak();
    private Sweetness sweetness = new Sweetness();
    private Acidity acidity = new Acidity();
    private Alcohol alcohol = new Alcohol();
    private Body body = new Body();
    private Texture texture = new Texture();
    private Finish finish = new Finish();
    private Complexity complexity = new Complexity();

    private boolean hasRimVariation;
    private boolean hasGasEvidence;
    private boolean isBalanced;

    public Clarity clarity() {
        return clarity;
    }

    public Concentration concentration() {
        return concentration;
    }

    public Tearing tearing() {
        return tearing;
    }

    public Fault fault() {
        return fault;
    }

    public Intensity intensity() {
        return intensity;
    }

    public AgeAssessment ageAssessment() {
        return ageAssessment;
    }

    public FruitCharacter fruitCharacter() {
        return fruitCharacter;
    }

    public Earth earth() {
        return earth;
    }

    public Mineral mineral() {
        return mineral;
    }

    public Oak oak() {
        return oak;
    }

    public Sweetness sweetness() {
        return sweetness;
    }

    public Acidity acidity() {
        return acidity;
    }

    public Alcohol alcohol() {
        return alcohol;
    }

    public Body body() {
        return body;
    }

    public Texture texture() {
        return texture;
    }

    public Finish finish() {
        return finish;
    }

    public Complexity complexity() {
        return complexity;
    }

    public void setIsBalanced(boolean isBalanced) {
        this.isBalanced = isBalanced;
    }

    public boolean getIsBalanced() {
        return isBalanced;
    }

    // Indicators of age.
    public void setHasRimVariation(boolean hasRimVariation) {
        this.hasRimVariation = hasRimVariation;
    }

    public boolean getHasRimVariation() {
        return hasRimVariation;
    }

    public void setHasGasEvidence(boolean hasGasEvidence) {
        this.hasGasEvidence = hasGasEvidence;
    }

    public boolean getHasGasEvidence() {
        return hasGasEvidence;
    }

    public class Clarity {
        private int clear;
        private int hazy;
        private int turbid;

        public int getClear() {
            return clear;
        }

        public void setClear(Integer clear) {
            this.clear = clear;
        }

        public int getHazy() {
            return hazy;
        }

        public void setHazy(Integer hazy) {
            this.hazy = hazy;
        }

        public int getTurbid() {
            return turbid;
        }

        public void setTurbid(Integer turbid) {
            this.turbid = turbid;
        }
    }

    public class Concentration {
        private int pale;
        private int medium;

        public int getPale() {
            return pale;
        }

        public void setPale(Integer pale) {
            this.pale = pale;
        }

        public int getMedium() {
            return medium;
        }

        public void setMedium(Integer medium) {
            this.medium = medium;
        }

        public int getDeep() {
            return deep;
        }

        public void setDeep(Integer deep) {
            this.deep = deep;
        }

        private int deep;
    }

    public class Tearing {
        private int light;
        private int medium;
        private int heavy;

        public int getLight() {
            return light;
        }

        public void setLight(int light) {
            this.light = light;
        }

        public int getMedium() {
            return medium;
        }

        public void setMedium(int medium) {
            this.medium = medium;
        }

        public int getHeavy() {
            return heavy;
        }

        public void setHeavy(int heavy) {
            this.heavy = heavy;
        }
    }

    public class Fault {
        private int tca;
        private int hydrogenSulfide;
        private int volatileAcidity;
        private int ethylAcetate;
        private int brett;
        private int oxidization;
        private int other;

        public int getTca() {
            return tca;
        }

        public void setTca(int tca) {
            this.tca = tca;
        }

        public int getHydrogenSulfide() {
            return hydrogenSulfide;
        }

        public void setHydrogenSulfide(int hydrogenSulfide) {
            this.hydrogenSulfide = hydrogenSulfide;
        }

        public int getVolatileAcidity() {
            return volatileAcidity;
        }

        public void setVolatileAcidity(int volatileAcidity) {
            this.volatileAcidity = volatileAcidity;
        }

        public int getEthylAcetate() {
            return ethylAcetate;
        }

        public void setEthylAcetate(int ethylAcetate) {
            this.ethylAcetate = ethylAcetate;
        }

        public int getBrett() {
            return brett;
        }

        public void setBrett(int brett) {
            this.brett = brett;
        }

        public int getOxidization() {
            return oxidization;
        }

        public void setOxidization(int oxidization) {
            this.oxidization = oxidization;
        }

        public int getOther() {
            return other;
        }

        public void setOther(int other) {
            this.other = other;
        }
    }

    public class Intensity {
        private int delicate;
        private int moderate;
        private int powerful;

        public int getDelicate() {
            return delicate;
        }

        public void setDelicate(int delicate) {
            this.delicate = delicate;
        }

        public int getModerate() {
            return moderate;
        }

        public void setModerate(int moderate) {
            this.moderate = moderate;
        }

        public int getPowerful() {
            return powerful;
        }

        public void setPowerful(int powerful) {
            this.powerful = powerful;
        }
    }

    public class AgeAssessment {
        private int youthful;
        private int developing;
        private int vinous;

        public int getYouthful() {
            return youthful;
        }

        public void setYouthful(int youthful) {
            this.youthful = youthful;
        }

        public int getDeveloping() {
            return developing;
        }

        public void setDeveloping(int developing) {
            this.developing = developing;
        }

        public int getVinous() {
            return vinous;
        }

        public void setVinous(int vinous) {
            this.vinous = vinous;
        }
    }

    public class FruitCharacter {
        private int ripe;
        private int fresh;
        private int tart;
        private int baked;
        private int stewed;
        private int dried;
        private int desiccated;
        private int bruised;
        private int jammy;

        public int getRipe() {
            return ripe;
        }

        public void setRipe(int ripe) {
            this.ripe = ripe;
        }

        public int getFresh() {
            return fresh;
        }

        public void setFresh(int fresh) {
            this.fresh = fresh;
        }

        public int getTart() {
            return tart;
        }

        public void setTart(int tart) {
            this.tart = tart;
        }

        public int getBaked() {
            return baked;
        }

        public void setBaked(int baked) {
            this.baked = baked;
        }

        public int getStewed() {
            return stewed;
        }

        public void setStewed(int stewed) {
            this.stewed = stewed;
        }

        public int getDried() {
            return dried;
        }

        public void setDried(int dried) {
            this.dried = dried;
        }

        public int getDesiccated() {
            return desiccated;
        }

        public void setDesiccated(int desiccated) {
            this.desiccated = desiccated;
        }

        public int getBruised() {
            return bruised;
        }

        public void setBruised(int bruised) {
            this.bruised = bruised;
        }

        public int getJammy() {
            return jammy;
        }

        public void setJammy(int jammy) {
            this.jammy = jammy;
        }
    }

    public class Earth {
        private int forestFloor;
        private int compost;
        private int mushrooms;
        private int pottingSoil;

        public int getForestFloor() {
            return forestFloor;
        }

        public void setForestFloor(int forestFloor) {
            this.forestFloor = forestFloor;
        }

        public int getCompost() {
            return compost;
        }

        public void setCompost(int compost) {
            this.compost = compost;
        }

        public int getMushrooms() {
            return mushrooms;
        }

        public void setMushrooms(int mushrooms) {
            this.mushrooms = mushrooms;
        }

        public int getPottingSoil() {
            return pottingSoil;
        }

        public void setPottingSoil(int pottingSoil) {
            this.pottingSoil = pottingSoil;
        }
    }

    public class Mineral {
        private int mineral;
        private int wetStone;
        private int limestone;
        private int chalk;
        private int slate;
        private int flint;

        public int getMineral() {
            return mineral;
        }

        public void setMineral(int mineral) {
            this.mineral = mineral;
        }

        public int getWetStone() {
            return wetStone;
        }

        public void setWetStone(int wetStone) {
            this.wetStone = wetStone;
        }

        public int getLimestone() {
            return limestone;
        }

        public void setLimestone(int limestone) {
            this.limestone = limestone;
        }

        public int getChalk() {
            return chalk;
        }

        public void setChalk(int chalk) {
            this.chalk = chalk;
        }

        public int getSlate() {
            return slate;
        }

        public void setSlate(int slate) {
            this.slate = slate;
        }

        public int getFlint() {
            return flint;
        }

        public void setFlint(int flint) {
            this.flint = flint;
        }
    }

    public class Oak {
        private int oak;
        private boolean newOak;
        private boolean largeBarrel;
        private boolean frenchOak;

        public int getOak() {
            return oak;
        }

        public void setOak(int oak) {
            this.oak = oak;
        }

        public boolean isNewOak() {
            return newOak;
        }

        public void setNewOak(boolean newOak) {
            this.newOak = newOak;
        }

        public boolean isLargeBarrel() {
            return largeBarrel;
        }

        public void setLargeBarrel(boolean largeBarrel) {
            this.largeBarrel = largeBarrel;
        }

        public boolean isFrenchOak() {
            return frenchOak;
        }

        public void setFrenchOak(boolean frenchOak) {
            this.frenchOak = frenchOak;
        }
    }

    public class Sweetness {
        private int boneDry;
        private int dry;
        private int offDry;
        private int mediumSweet;
        private int sweet;
        private int lusciouslySweet;

        public int getBoneDry() {
            return boneDry;
        }

        public void setBoneDry(int boneDry) {
            this.boneDry = boneDry;
        }

        public int getDry() {
            return dry;
        }

        public void setDry(int dry) {
            this.dry = dry;
        }

        public int getOffDry() {
            return offDry;
        }

        public void setOffDry(int offDry) {
            this.offDry = offDry;
        }

        public int getMediumSweet() {
            return mediumSweet;
        }

        public void setMediumSweet(int mediumSweet) {
            this.mediumSweet = mediumSweet;
        }

        public int getSweet() {
            return sweet;
        }

        public void setSweet(int sweet) {
            this.sweet = sweet;
        }

        public int getLusciouslySweet() {
            return lusciouslySweet;
        }

        public void setLusciouslySweet(int lusciouslySweet) {
            this.lusciouslySweet = lusciouslySweet;
        }
    }

    public class Acidity {
        private int low;
        private int mediumMinus;
        private int medium;
        private int getMediumPlus;
        private int high;

        public int getLow() {
            return low;
        }

        public void setLow(int low) {
            this.low = low;
        }

        public int getMediumMinus() {
            return mediumMinus;
        }

        public void setMediumMinus(int mediumMinus) {
            this.mediumMinus = mediumMinus;
        }

        public int getMedium() {
            return medium;
        }

        public void setMedium(int medium) {
            this.medium = medium;
        }

        public int getGetMediumPlus() {
            return getMediumPlus;
        }

        public void setGetMediumPlus(int getMediumPlus) {
            this.getMediumPlus = getMediumPlus;
        }

        public int getHigh() {
            return high;
        }

        public void setHigh(int high) {
            this.high = high;
        }
    }

    public class Alcohol {
        private int low;
        private int mediumMinus;
        private int medium;
        private int getMediumPlus;
        private int high;

        public int getLow() {
            return low;
        }

        public void setLow(int low) {
            this.low = low;
        }

        public int getMediumMinus() {
            return mediumMinus;
        }

        public void setMediumMinus(int mediumMinus) {
            this.mediumMinus = mediumMinus;
        }

        public int getMedium() {
            return medium;
        }

        public void setMedium(int medium) {
            this.medium = medium;
        }

        public int getGetMediumPlus() {
            return getMediumPlus;
        }

        public void setGetMediumPlus(int getMediumPlus) {
            this.getMediumPlus = getMediumPlus;
        }

        public int getHigh() {
            return high;
        }

        public void setHigh(int high) {
            this.high = high;
        }
    }

    public class Body {
        private int light;
        private int medium;
        private int full;

        public int getLight() {
            return light;
        }

        public void setLight(int light) {
            this.light = light;
        }

        public int getMedium() {
            return medium;
        }

        public void setMedium(int medium) {
            this.medium = medium;
        }

        public int getFull() {
            return full;
        }

        public void setFull(int full) {
            this.full = full;
        }
    }

    public class Texture {
        private int creamy;
        private int round;
        private int lean;

        public int getCreamy() {
            return creamy;
        }

        public void setCreamy(int creamy) {
            this.creamy = creamy;
        }

        public int getRound() {
            return round;
        }

        public void setRound(int round) {
            this.round = round;
        }

        public int getLean() {
            return lean;
        }

        public void setLean(int lean) {
            this.lean = lean;
        }
    }

    public class Finish {
        private int isShort;
        private int mediumMinus;
        private int medium;
        private int mediumPlus;
        private int isLong;

        public int getIsShort() {
            return isShort;
        }

        public void setIsShort(int isShort) {
            this.isShort = isShort;
        }

        public int getMediumMinus() {
            return mediumMinus;
        }

        public void setMediumMinus(int mediumMinus) {
            this.mediumMinus = mediumMinus;
        }

        public int getMedium() {
            return medium;
        }

        public void setMedium(int medium) {
            this.medium = medium;
        }

        public int getMediumPlus() {
            return mediumPlus;
        }

        public void setMediumPlus(int mediumPlus) {
            this.mediumPlus = mediumPlus;
        }

        public int getIsLong() {
            return isLong;
        }

        public void setIsLong(int isLong) {
            this.isLong = isLong;
        }
    }

    public class Complexity {
        private int low;
        private int mediumMinus;
        private int medium;
        private int mediumPlus;
        private int high;

        public int getLow() {
            return low;
        }

        public void setLow(int low) {
            this.low = low;
        }

        public int getMediumMinus() {
            return mediumMinus;
        }

        public void setMediumMinus(int mediumMinus) {
            this.mediumMinus = mediumMinus;
        }

        public int getMedium() {
            return medium;
        }

        public void setMedium(int medium) {
            this.medium = medium;
        }

        public int getMediumPlus() {
            return mediumPlus;
        }

        public void setMediumPlus(int mediumPlus) {
            this.mediumPlus = mediumPlus;
        }

        public int getHigh() {
            return high;
        }

        public void setHigh(int high) {
            this.high = high;
        }
    }
}
