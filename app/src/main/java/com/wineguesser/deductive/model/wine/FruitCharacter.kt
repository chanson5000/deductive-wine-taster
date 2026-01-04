package com.wineguesser.deductive.model.wine

data class FruitCharacter(
    var ripe: Int = 0,
    var fresh: Int = 0,
    var tart: Int = 0,
    var baked: Int = 0,
    var stewed: Int = 0,
    var dried: Int = 0,
    var desiccated: Int = 0,
    var bruised: Int = 0,
    var jammy: Int = 0
)
