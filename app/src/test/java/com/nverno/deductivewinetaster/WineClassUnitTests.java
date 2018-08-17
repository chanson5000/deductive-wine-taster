package com.nverno.deductivewinetaster;

import com.nverno.deductivewinetaster.model.RedWine;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class WineClassUnitTests {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void redWine_clarityShouldMatch() {
        RedWine redWine = new RedWine();

        int expected = 1;
        redWine.clarity().setClear(expected);

        int result = redWine.clarity().getClear();

        assertEquals(expected, result);
    }
}