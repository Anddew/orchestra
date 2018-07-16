package com.anddew.robotworld.robot.singer;

import com.anddew.robotworld.robot.Robot;
import com.anddew.robotworld.robot.RobotType;
import com.anddew.robotworld.robot.Song;

public abstract class SingerRobot implements Robot {

    private static final long serialVersionUID = -167408714671107801L;

    private String name;

    public SingerRobot(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public RobotType getType() {
        return RobotType.SINGER;
    }

}