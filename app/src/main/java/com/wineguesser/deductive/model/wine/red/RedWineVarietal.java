package com.wineguesser.deductive.model.wine.red;

import com.wineguesser.deductive.model.wine.WineVarietal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RedWineVarietal extends WineVarietal {
    private RedWineDescriptors redWineDescriptors;

    public RedWineVarietal(String name,
                           String description,
                           String notes,
                           String confusion,
                           RedWineDescriptors redWineDescriptors) {
        super(name, description, notes, confusion);

        this.redWineDescriptors = redWineDescriptors;
    }
}
