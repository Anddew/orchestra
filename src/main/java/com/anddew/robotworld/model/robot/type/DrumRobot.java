package com.anddew.robotworld.model.robot.type;

import com.anddew.robotworld.model.Song;
import com.anddew.robotworld.model.robot.AbstractRobot;
import com.anddew.robotworld.model.robot.RobotType;

public class DrumRobot extends AbstractRobot {

    private static final long serialVersionUID = -8164153677383680655L;

    private static final int MILLIS_IN_SECOND = 1000;

    public DrumRobot(String name) {
        super(name, RobotType.DRUMS);
    }


    @Override
    public synchronized String play(Song song) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < song.getDuration(); i++) {
            if ((i % 2) == 0) {
                sb.append("bum bum ");
            }
            if ((i % 3) == 0) {
                sb.append("ts ts");
            }
            if ((i % 5) == 0) {
                sb.append(" trrrrrrrr tsss");
            }
        }

        try {
            Thread.sleep(song.getDuration() * MILLIS_IN_SECOND);
        } catch (InterruptedException e) {
            throw new RuntimeException("Cannot complete drumming, something going wrong...", e);
        }

        return sb.toString();
    }

}
