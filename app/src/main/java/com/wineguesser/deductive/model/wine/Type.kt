package com.wineguesser.deductive.model.wine

data class Type(
    var typeOld: Int = 0,
    var typeNew: Int = 0
) {
    var old: Int
        get() = typeOld
        set(value) { typeOld = value }
    var new: Int
        get() = typeNew
        set(value) { typeNew = value }
}
