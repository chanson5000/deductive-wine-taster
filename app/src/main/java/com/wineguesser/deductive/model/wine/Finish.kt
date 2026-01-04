package com.wineguesser.deductive.model.wine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Finish {

    private int finishShort;
    private int mediumMinus;
    private int medium;
    private int mediumPlus;
    private int finishLong;

    public int getShort() {
        return finishShort;
    }

    public void setShort(int finishShort) {
        this.finishShort = finishShort;
    }

    public int getLong() {
        return finishLong;
    }

    public void setLong(int finishLong) {
        this.finishLong = finishLong;
    }
}
