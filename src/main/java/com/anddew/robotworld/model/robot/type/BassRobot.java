package com.anddew.robotworld.model.robot.type;

import com.anddew.robotworld.model.Song;
import com.anddew.robotworld.model.robot.AbstractRobot;
import com.anddew.robotworld.model.robot.RobotType;

/**
 * Bass type of robot. Interpret song as a bass player.
 *
 * @author Anddew
 */
public class BassRobot extends AbstractRobot {

    private static final long serialVersionUID = 6492987543565442606L;

    private static final String WORD_SEPARATOR = " ";
    private static final int MILLIS_IN_SECOND = 1000;

    /**
     * Constructor. Creates an instance of {@link RobotType#BASS} robot.
     *
     * @param name specified robot name
     */
    public BassRobot(String name) {
        super(name, RobotType.BASS);
    }


    @Override
    public synchronized String play(Song song) {
        StringBuffer sb = new StringBuffer();
        String[] words = song.getText().split(WORD_SEPARATOR);
        for (String word : words) {
            if (word.length() > 3) {
                sb.append("poooom-poom ");
            } else {
                sb.append("pim-pim-pim");
            }
        }

        try {
            Thread.sleep(song.getDuration() * MILLIS_IN_SECOND);
        } catch (InterruptedException e) {
            throw new RuntimeException("Cannot complete playing bass, something going wrong...", e);
        }

        return sb.toString();
    }

}