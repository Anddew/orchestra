package com.anddew.robotworld.robot.singer;

import com.anddew.robotworld.robot.Song;
import org.springframework.stereotype.Component;

@Component
public class SopranoSingerRobot extends SingerRobot {

    private static final long serialVersionUID = -1610283979567241506L;

    public SopranoSingerRobot() {
        this("default");
    }

    public SopranoSingerRobot(String name) {
        super(name);
    }

    @Override
    public synchronized void play(Song song) {
        try {
            Thread.sleep(song.getDuration() * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread sleep exception.");
        }
    }
}