package com.wineguesser.deductive.viewmodel;

import com.wineguesser.deductive.repository.ConclusionsRepository;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class HistoryActivityViewModelTests {
    private HistoryActivityViewModel historyActivityViewModel;


    @Mock
    private ConclusionsRepository conclusionsRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        historyActivityViewModel = new HistoryActivityViewModel();

    }

//    @Test
//    @Ignore("Does not work.")
//    public void getUserConclusionsTest() {
//
//        doReturn(anyObject()).when(conclusionsRepository).getConclusionsForUser("test");
//
//        System.out.println(historyActivityViewModel.getUserConclusions("test"));
//    }
}
