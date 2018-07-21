package com.anddew.robotworld.history;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class HistoryHolderTest {

    private static final int TEST_MESSAGES_COUNT = 3;
    private static final String TEST_MESSAGE_ONE = "test message one";
    private static final String TEST_MESSAGE_TWO = "test message two";
    private static final String TEST_MESSAGE_THREE = "test message three";

    private HistoryHolder historyHolder;

    @Before
    public void setUp() {
        historyHolder = new HistoryHolder();
        historyHolder.add(TEST_MESSAGE_ONE);
        historyHolder.add(TEST_MESSAGE_TWO);
        historyHolder.add(TEST_MESSAGE_THREE);
    }

    @Test
    public void testGetHistoryMethod() {
        Collection<String> history = historyHolder.getHistory();
        Assert.assertTrue(history.size() == TEST_MESSAGES_COUNT);
        Assert.assertTrue(history.contains(TEST_MESSAGE_ONE));
        Assert.assertTrue(history.contains(TEST_MESSAGE_TWO));
        Assert.assertTrue(history.contains(TEST_MESSAGE_THREE));
    }

    @Test
    public void testAddMessageMethod() {
        int sizeBefore = historyHolder.getHistory().size();
        String newRecord = "new record";
        historyHolder.add(newRecord);
        Assert.assertTrue(sizeBefore == (historyHolder.getHistory().size() - 1));
        Assert.assertTrue(historyHolder.getHistory().contains(newRecord));
    }

}