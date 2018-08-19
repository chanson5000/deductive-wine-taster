package com.wineguesser.deductive.model;

public class WhiteWine extends Wine {

    private Color color = new Color();
    private SecondaryColor secondaryColor = new SecondaryColor();
    private Fruit fruit = new Fruit();

    private int phenolicBitter;

    public int getPhenolicBitter() {
        return phenolicBitter;
    }

    public void setPhenolicBitter(int phenolicBitter) {
        this.phenolicBitter = phenolicBitter;
    }

    public Color color() {
        return color;
    }

    public SecondaryColor secondaryColor() {
        return secondaryColor;
    }

    public Fruit fruit() {
        return fruit;
    }

    public class Color {
        private int straw;
        private int yellow;
        private int gold;

        public int getStraw() {
            return straw;
        }

        public void setStraw(int straw) {
            this.straw = straw;
        }

        public int getYellow() {
            return yellow;
        }

        public void setYellow(int yellow) {
            this.yellow = yellow;
        }

        public int getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }
    }

    public class SecondaryColor {
        private int silver;
        private int green;
        private int copper;

        public int getSilver() {
            return silver;
        }

        public void setSilver(int silver) {
            this.silver = silver;
        }

        public int getGreen() {
            return green;
        }

        public void setGreen(int green) {
            this.green = green;
        }

        public int getCopper() {
            return copper;
        }

        public void setCopper(int copper) {
            this.copper = copper;
        }
    }

    public class Fruit {
        private int citrus;
        private int applePear;
        private int stonePit;
        private int tropical;
        private int melon;

        public int getCitrus() {
            return citrus;
        }

        public void setCitrus(int citrus) {
            this.citrus = citrus;
        }

        public int getApplePear() {
            return applePear;
        }

        public void setApplePear(int applePear) {
            this.applePear = applePear;
        }

        public int getStonePit() {
            return stonePit;
        }

        public void setStonePit(int stonePit) {
            this.stonePit = stonePit;
        }

        public int getTropical() {
            return tropical;
        }

        public void setTropical(int tropical) {
            this.tropical = tropical;
        }

        public int getMelon() {
            return melon;
        }

        public void setMelon(int melon) {
            this.melon = melon;
        }
    }

}
