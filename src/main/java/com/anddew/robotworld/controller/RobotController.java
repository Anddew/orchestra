package com.anddew.robotworld.controller;

import com.anddew.robotworld.orchestra.RoboticOrchestra;
import com.anddew.robotworld.robot.Robot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class RobotController {

    @Autowired
    private RoboticOrchestra roboticOrchestra;

    @GetMapping("/robot")
    public @ResponseBody String getRobot(@RequestParam Map<String, String> parameters) {
        System.out.println("Controller do");
        System.out.println(parameters);
        return "Task complete.";
    }

    @GetMapping("/register")
    public void registerRobot(@RequestParam Robot robot) {
        System.out.println(robot);
        roboticOrchestra.register(robot);
    }

}