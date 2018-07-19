package com.anddew.robotworld.log;

import com.google.common.collect.EvictingQueue;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Queue;

@Component
public class LogManager {

    private static final int LOG_SIZE = 50;

    private Queue<String> log = EvictingQueue.create(LOG_SIZE);

    public Collection<String> getLog() {
        return log;
    }

    public void add(String message) {
        log.add(message);
    }

}