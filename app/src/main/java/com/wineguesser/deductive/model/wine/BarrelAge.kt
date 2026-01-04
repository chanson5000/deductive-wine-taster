package com.wineguesser.deductive.model.wine

data class BarrelAge(
    var ageOld: Int = 0,
    var ageNew: Int = 0
) {
    var old: Int
        get() = ageOld
        set(value) { ageOld = value }
    var new: Int
        get() = ageNew
        set(value) { ageNew = value }
}
