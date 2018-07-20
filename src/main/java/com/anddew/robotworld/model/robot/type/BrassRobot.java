package com.anddew.robotworld.model.robot.type;

import com.anddew.robotworld.model.Song;
import com.anddew.robotworld.model.robot.AbstractRobot;
import com.anddew.robotworld.model.robot.RobotType;


/**
 * Brass type of robot. Interpret song as a brass player.
 *
 * @author Anddew
 */
public class BrassRobot extends AbstractRobot {

    private static final long serialVersionUID = -2138032495003575693L;

    private static final int MILLIS_IN_SECOND = 1000;

    /**
     * Constructor. Creates an instance of {@link RobotType#BRASS} robot.
     *
     * @param name specified robot name
     */
    public BrassRobot(String name) {
        super(name, RobotType.BRASS);
    }


    @Override
    public synchronized String play(Song song) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < song.getText().length(); i++) {
            sb.append("tu");
        }

        try {
            Thread.sleep(song.getDuration() * MILLIS_IN_SECOND);
        } catch (InterruptedException e) {
            throw new RuntimeException("Cannot complete blowing pipe, something going wrong...", e);
        }

        return sb.toString();
    }

}