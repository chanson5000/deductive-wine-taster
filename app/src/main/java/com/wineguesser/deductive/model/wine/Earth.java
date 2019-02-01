package com.wineguesser.deductive.model.wine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Earth {
    private int forestFloor;
    private int compost;
    private int mushrooms;
    private int pottingSoil;
}
