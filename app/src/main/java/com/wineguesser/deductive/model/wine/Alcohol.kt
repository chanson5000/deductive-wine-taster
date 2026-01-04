package com.wineguesser.deductive.model.wine

data class Alcohol(
    var low: Int = 0,
    var mediumMinus: Int = 0,
    var medium: Int = 0,
    var mediumPlus: Int = 0,
    var high: Int = 0
)
