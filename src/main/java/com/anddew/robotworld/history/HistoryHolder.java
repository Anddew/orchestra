package com.anddew.robotworld.history;

import com.google.common.collect.EvictingQueue;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Queue;

/**
 * History holder.
 *
 * @author Anddew
 */
@Component
public class HistoryHolder {

    private static final int HISTORY_SIZE = 50;

    private Queue<String> history = EvictingQueue.create(HISTORY_SIZE);

    /**
     * Get history.
     *
     * @return collection of records.
     */
    public Collection<String> getHistory() {
        return history;
    }

    /**
     * Add message to history
     *
     * @param message to add
     */
    public void add(String message) {
        history.add(message);
    }

}