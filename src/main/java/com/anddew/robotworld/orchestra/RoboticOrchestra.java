package com.anddew.robotworld.orchestra;

import com.anddew.robotworld.robot.Robot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

@Component
public class RoboticOrchestra {

    private Map<String, Robot> robots = new HashMap<>();

    public void register(Robot robot) {
        robots.put(robot.getName(), robot);
    }

    public Robot get(String name) {
        return robots.get(name);
    }

    public void remove(String name) {
        robots.remove(name);
    }

}