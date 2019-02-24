package com.wineguesser.deductive.viewmodel;

import com.wineguesser.deductive.repository.ConclusionsRepository;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doReturn;

public class HistoryActivityViewModelTests {
    private HistoryActivityViewModel historyActivityViewModel;


    @Mock
    private ConclusionsRepository conclusionsRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        historyActivityViewModel = new HistoryActivityViewModel();

    }

    // FIXME: Fix tests.
    @Test
    @Ignore("Does not work.")
    public void getUserConclusionsTest() {

        doReturn(anyObject()).when(conclusionsRepository).getConclusionsForUser("test");

        System.out.println(historyActivityViewModel.getUserConclusions("test"));
    }
}
