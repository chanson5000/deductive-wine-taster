package com.wineguesser.deductive.util.Varietal;

public interface VarietalScoreResult {
    void onGrapeResult(String topScoreVariety);
    void onGrapeFailure();
}
