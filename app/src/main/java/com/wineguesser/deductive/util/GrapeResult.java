package com.wineguesser.deductive.util;

public interface GrapeResult {
    void onGrapeResult(String topScoreVariety);
    void onGrapeFailure();
    void isScoring(Boolean loading);
}
