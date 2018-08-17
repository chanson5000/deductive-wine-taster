package com.nverno.deductivewinetaster.model;

public class RedWine extends Wine {

    private Color color = new Color();
    private SecondaryColor secondaryColor = new SecondaryColor();
    private Staining staining = new Staining();
    private Fruit fruit = new Fruit();
    private Tannin tannin = new Tannin();

    public Color color() {
        return color;
    }

    public SecondaryColor secondaryColor() {
        return secondaryColor;
    }

    public Staining staining() {
        return staining;
    }

    public Fruit fruit() {
        return fruit;
    }

    public Tannin tannin() {
        return tannin;
    }

    public class Color {
        private int purple;
        private int ruby;
        private int garnet;

        public int getPurple() {
            return purple;
        }

        public void setPurple(int purple) {
            this.purple = purple;
        }

        public int getRuby() {
            return ruby;
        }

        public void setRuby(int ruby) {
            this.ruby = ruby;
        }

        public int getGarnet() {
            return garnet;
        }

        public void setGarnet(int garnet) {
            this.garnet = garnet;
        }
    }

    public class SecondaryColor {
        private int orange;
        private int blue;
        private int ruby;
        private int garnet;
        private int brown;

        public int getOrange() {
            return orange;
        }

        public void setOrange(int orange) {
            this.orange = orange;
        }

        public int getBlue() {
            return blue;
        }

        public void setBlue(int blue) {
            this.blue = blue;
        }

        public int getRuby() {
            return ruby;
        }

        public void setRuby(int ruby) {
            this.ruby = ruby;
        }

        public int getGarnet() {
            return garnet;
        }

        public void setGarnet(int garnet) {
            this.garnet = garnet;
        }

        public int getBrown() {
            return brown;
        }

        public void setBrown(int brown) {
            this.brown = brown;
        }
    }

    public class Staining {
        private int none;
        private int light;
        private int medium;
        private int heavy;

        public int getNone() {
            return none;
        }

        public void setNone(int none) {
            this.none = none;
        }

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

    public class Fruit {
        private int red;
        private int black;
        private int blue;

        public int getRed() {
            return red;
        }

        public void setRed(int red) {
            this.red = red;
        }

        public int getBlack() {
            return black;
        }

        public void setBlack(int black) {
            this.black = black;
        }

        public int getBlue() {
            return blue;
        }

        public void setBlue(int blue) {
            this.blue = blue;
        }
    }

    public class Tannin {
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
}
