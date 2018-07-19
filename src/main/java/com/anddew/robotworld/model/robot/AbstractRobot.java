package com.anddew.robotworld.model.robot;

import java.io.Serializable;


public abstract class AbstractRobot implements Robot, Serializable {

    private static final long serialVersionUID = -167408714671107801L;

    private String name;
    private RobotType type;

    public AbstractRobot(String name, RobotType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public RobotType getType() {
        return type;
    }

}