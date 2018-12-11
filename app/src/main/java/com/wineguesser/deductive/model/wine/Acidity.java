package com.wineguesser.deductive.model.wine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Acidity {
    private int low;
    private int mediumMinus;
    private int medium;
    private int mediumPlus;
    private int high;
}
