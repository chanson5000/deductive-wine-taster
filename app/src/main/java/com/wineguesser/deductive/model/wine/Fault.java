package com.wineguesser.deductive.model.wine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Fault {
    private int tca;
    private int hydrogenSulfide;
    private int volatileAcidity;
    private int ethylAcetate;
    private int brett;
    private int oxidization;
    private int other;
}
