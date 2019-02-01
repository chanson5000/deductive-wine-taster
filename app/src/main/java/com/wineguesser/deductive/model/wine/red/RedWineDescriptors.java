package com.wineguesser.deductive.model.wine.red;

import com.wineguesser.deductive.model.wine.WineDescriptors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class RedWineDescriptors extends WineDescriptors {
    private Color color;
    private SecondaryColor secondaryColor;
    private Tannin tannin;
}
