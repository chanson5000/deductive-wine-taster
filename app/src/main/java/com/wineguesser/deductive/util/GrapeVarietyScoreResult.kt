package com.wineguesser.deductive.util

interface GrapeVarietyScoreResult {
    fun onGrapeResult(topScoreVariety: String?)
    fun onGrapeFailure()
}
