package com.wineguesser.deductive.model.wine.white;

import com.wineguesser.deductive.model.wine.WineDescriptors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
class WhiteWineDescriptors extends WineDescriptors {
    private Color color;
    private Sweetness sweetness;
    private int phenolicBitter;
}
