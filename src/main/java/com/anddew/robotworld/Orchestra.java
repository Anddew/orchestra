package com.anddew.robotworld;

import com.anddew.robotworld.model.robot.Robot;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Representation of orchestra.
 *
 * @author Anddew
 */
@Component
public class Orchestra {

    private final static Logger LOGGER = Logger.getLogger(Orchestra.class);

    private Map<String, Robot> freeRobots = new ConcurrentHashMap<>();
    private Map<String, Robot> busyRobots = new ConcurrentHashMap<>();


    /**
     * Find all free robots.
     *
     * @return collection of free robots
     */
    public Collection<Robot> getAll() {
        return freeRobots.values();
    }

    /**
     * Register new robot in orchestra.
     *
     * @param robot to be registered
     * @throws RuntimeException if robot with such name is already exist
     */
    public synchronized void register(Robot robot) {
        if (freeRobots.containsKey(robot.getName()) || busyRobots.containsKey(robot.getName())) {
            throw new RuntimeException("Robot with name '" + robot.getName() + "' is already exist.");
        }
        freeRobots.put(robot.getName(), robot);
        LOGGER.info("Successful registration. [" + robot + "]");
    }

    /**
     * Retrieve free robot by name.
     *
     * @param name of requested robot
     * @return robot with specified name
     * @throws RuntimeException if no free robot with specified name was found
     */
    public synchronized Robot retrieve(String name) {
        Robot robot = freeRobots.get(name);
        if (robot == null) {
            throw new RuntimeException("Robot with name '" + name + "' is busy or not exist.");
        }
        busyRobots.put(robot.getName(), robot);
        return robot;
    }

    /**
     * Retrieve all free robots.
     *
     * @return collection {@link List} of free robots or empty one if no robots available
     */
    public synchronized List<Robot> retrieveAll() {
        List<Robot> result = new ArrayList<>(freeRobots.values());
        busyRobots.putAll(freeRobots);
        freeRobots.clear();
        return result;
    }

    /**
     * Release busy robot and make it available.
     *
     * @param robot to release
     */
    public synchronized void release(Robot robot) {
        Robot releasedRobot = busyRobots.remove(robot.getName());
        if (releasedRobot == null) {
            throw new RuntimeException("Attempt to release robot failed. No such busy robot - '" + robot + "'.");
        }
        freeRobots.put(robot.getName(), robot);
    }

    /**
     * Release collection of busy robots and make it available.
     *
     * @param robots to release
     */
    public synchronized void releaseAll(Collection<Robot> robots) {
        for (Robot robot : robots) {
            Robot removedRobot = busyRobots.remove(robot.getName());
            if (removedRobot != null) {
                freeRobots.put(removedRobot.getName(), removedRobot);
            }
        }
    }

    /**
     * Removing robot with specified name from orchestra.
     *
     * @param name of robot to be removed
     * @throws RuntimeException if robot with specified name was found
     */
    public synchronized void remove(String name) {
        if (freeRobots.containsKey(name)) {
            freeRobots.remove(name);
            LOGGER.info("Successful removing. Name = '" + name + "'.");
            return;
        }

        if (busyRobots.containsKey(name)) {
            busyRobots.remove(name);
            LOGGER.info("Successful removing. Name = '" + name + "'.");
            return;
        }

        LOGGER.info("Removing failed. Robot not found. Name = '" + name + "'.");
        throw new RuntimeException("Robot with name '" + name + "' is not exist.");
    }

}