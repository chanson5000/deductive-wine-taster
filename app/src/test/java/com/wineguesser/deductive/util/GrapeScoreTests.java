package com.wineguesser.deductive.util;

import com.google.firebase.database.DataSnapshot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;

@RunWith(JUnit4.class)
public class GrapeScoreTests {

    @Mock
    private DataSnapshot dataSnapshot;

    @Before
    public void SetUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenIntegerCastNonInteger() {
        doReturn("test").when(dataSnapshot).getValue();

        Integer testInteger = dataSnapshot.getValue(Integer.class);

        assertNull(testInteger);
    }
}