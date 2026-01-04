package com.wineguesser.deductive.model.wine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class BarrelAge {
    private int ageOld;
    private int ageNew;

    public int getOld() {
        return ageOld;
    }

    public void setOld(int ageOld) {
        this.ageOld = ageOld;
    }

    public int getNew() {
        return ageNew;
    }

    public void setNew(int ageNew) {
        this.ageNew = ageNew;
    }
}
