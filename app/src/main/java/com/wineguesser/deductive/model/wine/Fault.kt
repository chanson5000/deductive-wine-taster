package com.wineguesser.deductive.model.wine

data class Fault(
    var tca: Int = 0,
    var hydrogenSulfide: Int = 0,
    var volatileAcidity: Int = 0,
    var ethylAcetate: Int = 0,
    var brett: Int = 0,
    var oxidization: Int = 0,
    var other: Int = 0
)
