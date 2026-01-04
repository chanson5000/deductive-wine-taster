package com.wineguesser.deductive.model.wine

data class Minerality(
    var mineral: Int = 0,
    var wetStone: Int = 0,
    var limestone: Int = 0,
    var chalk: Int = 0,
    var slate: Int = 0,
    var flint: Int = 0
)
