package com.wineguesser.deductive.util;

public interface GrapeVarietyScoreResult {
    void onGrapeResult(String topScoreVariety);
    void onGrapeFailure();
}
