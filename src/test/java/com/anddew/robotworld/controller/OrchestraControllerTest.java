package com.anddew.robotworld.controller;

import com.anddew.robotworld.history.HistoryHolder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrchestraControllerTest {

    private static final String HISTORY_MESSAGE_ONE = "history message one";
    private static final String HISTORY_MESSAGE_TWO = "history message two";

    private OrchestraController orchestraController;
    private HistoryHolder historyHolder;

    public OrchestraControllerTest() {
        orchestraController = new OrchestraController();
        historyHolder = mock(HistoryHolder.class);
    }

    @Before
    public void setUp() {
        Collection<String> history = new ArrayList<>();
        history.add(HISTORY_MESSAGE_ONE);
        history.add(HISTORY_MESSAGE_TWO);
        when(historyHolder.getHistory()).thenReturn(history);
        Whitebox.setInternalState(orchestraController, "historyHolder", historyHolder);
    }

    @Test
    public void testGetUpdatesMethod() {
        Collection<String> history = orchestraController.getUpdates();
        Assert.assertEquals(history, historyHolder.getHistory());
    }

}
