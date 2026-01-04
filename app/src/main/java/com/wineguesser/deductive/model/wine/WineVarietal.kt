package com.wineguesser.deductive.model.wine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class WineVarietal {
    private String name;
    private String description;
    private String notes;
    private String confusion;
}
