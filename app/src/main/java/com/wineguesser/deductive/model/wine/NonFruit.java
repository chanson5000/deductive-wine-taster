package com.wineguesser.deductive.model.wine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class NonFruit {
    private int floral;
    private int vegetal;
    private int herbal;
    private int spice;
    private int animal;
    private int barn;
    private int petrol;
    private int fermentation;
}
