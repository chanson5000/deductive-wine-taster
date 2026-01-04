package com.wineguesser.deductive.model.wine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Minerality {
    private int mineral;
    private int wetStone;
    private int limestone;
    private int chalk;
    private int slate;
    private int flint;
}
