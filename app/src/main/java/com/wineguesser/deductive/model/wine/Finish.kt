package com.wineguesser.deductive.model.wine

data class Finish(
    var finishShort: Int = 0,
    var mediumMinus: Int = 0,
    var medium: Int = 0,
    var mediumPlus: Int = 0,
    var finishLong: Int = 0
) {
    var short: Int
        get() = finishShort
        set(value) { finishShort = value }
    var long: Int
        get() = finishLong
        set(value) { finishLong = value }
}
