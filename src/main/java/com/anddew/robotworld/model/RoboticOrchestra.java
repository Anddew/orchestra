package com.anddew.robotworld.model;

import com.anddew.robotworld.model.robot.Robot;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RoboticOrchestra {

    private final static Logger LOGGER = Logger.getLogger(RoboticOrchestra.class);

    private Map<String, Robot> freeRobots = new ConcurrentHashMap<>();
    private Map<String, Robot> busyRobots = new ConcurrentHashMap<>();


    public synchronized void register(Robot robot) {
        if (freeRobots.containsKey(robot.getName()) || busyRobots.containsKey(robot.getName())) {
            throw new RuntimeException("Robot with name '" + robot.getName() + "' is already exist.");
        }
        freeRobots.put(robot.getName(), robot);
        LOGGER.info("Successful registration. [" + robot + "]");
    }

    public synchronized Robot get(String name) {
        Robot robot = freeRobots.get(name);
        if (robot == null) {
            throw new RuntimeException("Robot with name '" + name + "' is busy or not exist.");
        }
        busyRobots.put(robot.getName(), robot);
        return robot;
    }

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