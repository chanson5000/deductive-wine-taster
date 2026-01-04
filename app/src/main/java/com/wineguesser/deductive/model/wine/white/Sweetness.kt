package com.wineguesser.deductive.model.wine.white;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Sweetness {
    private int boneDry;
    private int offDry;
    private int dry;
    private int mediumSweet;
    private int sweet;
}
