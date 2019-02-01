package com.wineguesser.deductive.model.wine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Oak {
    private Type type;
    private BarrelSize size;
    private BarrelAge age;

    public BarrelSize getSize() {
        return size;
    }

    public void setSize(BarrelSize size) {
        this.size = size;
    }

    public BarrelAge getAge() {
        return age;
    }

    public void setAge(BarrelAge age) {
        this.age = age;
    }
}
