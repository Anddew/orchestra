package com.anddew.robotworld.model.robot;

import com.anddew.robotworld.model.Song;


/**
 * Interface of robot entity.
 *
 * @author Anddew
 */
public interface Robot {

    /**
     * Get name of appropriate robot.
     *
     * @return robot name
     */
    String getName();

    /**
     * Get type of appropriate robot.
     *
     * @return robot type
     * @see RobotType
     */
    RobotType getType();

    /**
     * Plays given song.
     *
     * @param song to play
     * @return text representation of musical part
     */
    String play(Song song);

}