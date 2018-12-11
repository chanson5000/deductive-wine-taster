package com.wineguesser.deductive.model.wine.white;

import com.wineguesser.deductive.model.wine.WineVarietal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class WhiteWineVarietal extends WineVarietal {
    private WhiteWineDescriptors whiteWineDescriptors;

    public WhiteWineVarietal(String name,
                             String description,
                             String notes,
                             String confusion,
                             WhiteWineDescriptors whiteWineDescriptors) {
        super(name, description, notes, confusion);

        this.whiteWineDescriptors = whiteWineDescriptors;
    }
}
