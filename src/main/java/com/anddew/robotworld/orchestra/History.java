package com.anddew.robotworld.orchestra;

import com.google.common.collect.EvictingQueue;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Queue;

@Component
public class History {

    private Queue<String> history = EvictingQueue.create(10);

    public Collection<String> getHistory() {
        return history;
    }

    public void postMessage(String message) {
        history.add(message);
    }

}
