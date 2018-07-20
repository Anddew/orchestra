package com.anddew.robotworld.model.robot.type;

import com.anddew.robotworld.model.Song;
import com.anddew.robotworld.model.robot.AbstractRobot;
import com.anddew.robotworld.model.robot.RobotType;

/**
 * Guitar type of robot. Interpret song as a guitar player.
 *
 * @author Anddew
 */
public class GuitarRobot extends AbstractRobot {

    private static final long serialVersionUID = 3831824713765902760L;

    private static final int MILLIS_IN_SECOND = 1000;

    /**
     * Constructor. Creates an instance of {@link RobotType#GUITAR} robot.
     *
     * @param name specified robot name
     */
    public GuitarRobot(String name) {
        super(name, RobotType.GUITAR);
    }

    @Override
    public synchronized String play(Song song) {
        StringBuffer sb = new StringBuffer();
        int tactCount = song.getDuration() % 4;

        for (int i = 0; i < tactCount; i++) {
            sb.append("bring... ");
        }

        try {
            Thread.sleep(song.getDuration() * MILLIS_IN_SECOND);
        } catch (InterruptedException e) {
            throw new RuntimeException("Cannot complete blowing pipe, something going wrong...", e);
        }

        return sb.toString();
    }

}