package com.anddew.robotworld.model.robot;

import com.anddew.robotworld.model.Song;


public interface Robot {

    String getName();

    RobotType getType();

    String play(Song song);

}