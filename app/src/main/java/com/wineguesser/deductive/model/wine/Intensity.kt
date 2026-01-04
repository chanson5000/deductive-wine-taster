package com.wineguesser.deductive.model.wine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Intensity {
    private int delicate;
    private int moderate;
    private int powerful;
}
