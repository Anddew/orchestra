package com.anddew.robotworld.model.robot.type;

import com.anddew.robotworld.model.Song;
import com.anddew.robotworld.model.robot.AbstractRobot;
import com.anddew.robotworld.model.robot.RobotType;


/**
 * Singer type of robot. Interpret song as a vocalist.
 *
 * @author Anddew
 */
public class VocalRobot extends AbstractRobot {

    private static final long serialVersionUID = -1610283979567241506L;

    private static final String PUNCTUATION_SYMBOL_REGEX = "\\p{Punct}";
    private static final String EMPTY_STRING = "";
    private static final String WORD_SEPARATOR = " ";
    private static final int MILLIS_IN_SECOND = 1000;

    /**
     * Constructor. Creates an instance of {@link RobotType#VOCAL} robot.
     *
     * @param name specified robot name
     */
    public VocalRobot(String name) {
        super(name, RobotType.VOCAL);
    }


    @Override
    public synchronized String play(Song song) {
        StringBuffer sb = new StringBuffer();
        String[] words = song.getText().replaceAll(PUNCTUATION_SYMBOL_REGEX, EMPTY_STRING).split(WORD_SEPARATOR);
        for (String word : words) {
            sb.append("la-a-a l-a-a").append(" (").append(word).append(" ) ");
        }

        try {
            Thread.sleep(song.getDuration() * MILLIS_IN_SECOND);
        } catch (InterruptedException e) {
            throw new RuntimeException("Cannot complete singing, something going wrong...", e);
        }

        return sb.toString();
    }

}