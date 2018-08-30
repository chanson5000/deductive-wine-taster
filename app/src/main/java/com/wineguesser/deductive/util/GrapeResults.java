package com.wineguesser.deductive.util;

public interface GrapeResults {
    void onGrapeResult(String topScoreVariety);
    void onGrapeFailure();
    void isScoring(Boolean loading);
}
