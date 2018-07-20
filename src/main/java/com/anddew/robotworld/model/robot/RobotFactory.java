package com.anddew.robotworld.model.robot;

import com.anddew.robotworld.model.robot.type.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


/**
 * Robot entity factory.
 *
 * @author Anddew
 */
@Component
public class RobotFactory {

    private final static Logger LOGGER = Logger.getLogger(RobotFactory.class);

    /**
     * Create an instance of {@link Robot}.
     *
     * @param name robot name
     * @param type robot type {@link RobotType}
     * @return new instance of robot
     */
    public Robot create(String name, RobotType type) {
        Robot robot = resolveRobotType(name, type);
        LOGGER.info("New robot '" + robot + "' was created.");
        return robot;
    }

    private Robot resolveRobotType(String name, RobotType type) {
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