package com.wineguesser.deductive;

import com.wineguesser.deductive.model.RedWine;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class WineClassUnitTests {

    @Test
    public void redWine_clarityShouldMatch() {
        RedWine redWine = new RedWine();

        int expected = 1;
        redWine.clarity().setClear(expected);

        int result = redWine.clarity().getClear();

        assertEquals(expected, result);
    }

    @Test
    public void redWine_setColorWorks() {
        RedWine redWine = new RedWine();

        int expected = 1;
        redWine.color().setRuby(expected);

        int result = redWine.color().getRuby();

        assertEquals(expected, result);
    }

    @Test
    public void redWine_setNameWorks() {
        RedWine redWine = new RedWine();

        String expected = "Cabernet Sauvignon";
        redWine.setName(expected);

        String result = redWine.getName();

        assertEquals(expected, result);
    }

    @Test
    public void redWine_nonSetNameReturnsNull() {
        RedWine redWine = new RedWine();

        String result = redWine.getName();

        assertEquals(null, result);
    }
}