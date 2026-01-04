package com.wineguesser.deductive.model.wine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class WineDescriptors {

    private Clarity clarity;
    private Concentration concentration;
    private int rimVariation;
    private Staining staining;
    private int gasEvidence;
    private Intensity intensity;
    private Age age;
    private Oak oak;
    private Acidity acidity;
    private Alcohol alcohol;
    private Body body;
    private Texture texture;
    private int balanced;
    private Finish finish;
    private Complexity complexity;
    private Fault fault;
    private FruitCharacter fruitCharacter;
    private NonFruit nonFruit;
    private Earth earth;
    private Minerality minerality;
}
