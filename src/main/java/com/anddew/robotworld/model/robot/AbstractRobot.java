package com.anddew.robotworld.model.robot;

import java.io.Serializable;
import java.util.Objects;


/**
 * Basic implementation of robot entity.
 *
 * @author Anddew
 */
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        AbstractRobot that = (AbstractRobot) o;
        return Objects.equals(name, that.name) &&
            type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        return name + " : " + type;
    }
}