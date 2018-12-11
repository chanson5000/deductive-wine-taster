package com.wineguesser.deductive.model.wine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class FruitCharacter {
    private int ripe;
    private int fresh;
    private int tart;
    private int baked;
    private int stewed;
    private int dried;
    private int desiccated;
    private int bruised;
    private int jammy;
}
