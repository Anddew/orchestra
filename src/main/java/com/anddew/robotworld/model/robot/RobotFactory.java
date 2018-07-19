package com.anddew.robotworld.model.robot;

import com.anddew.robotworld.model.robot.type.*;
import org.springframework.stereotype.Component;


@Component
public class RobotFactory {

    public Robot create(String name, RobotType type) {
        switch (type) {
            case VOCAL:
                return new VocalRobot(name);
            case GUITAR:
                return new GuitarRobot(name);
            case BASS:
                return new BassRobot(name);
            case BRASS:
                return new BrassRobot(name);
            case DRUMS:
                return new DrumRobot(name);
            default:
                throw new RuntimeException("Unknown robot type - '" + type + "'.");
        }
    }

}