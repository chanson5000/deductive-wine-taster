package com.wineguesser.deductive.model.wine.red;

import com.wineguesser.deductive.model.wine.WineVarietal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class RedWineVarietal extends WineVarietal {
    private RedWineDescriptors redWineDescriptors;
}
