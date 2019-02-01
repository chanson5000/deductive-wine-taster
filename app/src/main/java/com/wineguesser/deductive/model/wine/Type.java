package com.wineguesser.deductive.model.wine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type {
    private int typeOld;
    private int typeNew;

    public int getOld() {
        return typeOld;
    }

    public void setOld(int typeOld) {
        this.typeOld = typeOld;
    }

    public int getNew() {
        return typeNew;
    }

    public void setNew(int typeNew) {
        this.typeNew = typeNew;
    }
}
