package com.anddew.robotworld.controller;

import com.anddew.robotworld.log.LogManager;
import com.anddew.robotworld.model.Repertoire;
import com.anddew.robotworld.model.RoboticOrchestra;
import com.anddew.robotworld.model.robot.Robot;
import com.anddew.robotworld.model.robot.RobotFactory;
import com.anddew.robotworld.model.robot.RobotType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;


@Controller
public class OrchestraController {

    @Autowired
    private RoboticOrchestra roboticOrchestra;

    @Autowired
    private Repertoire repertoire;

    @Autowired
    private RobotFactory robotFactory;

    @Autowired
    private LogManager logManager;

    @GetMapping("/orchestra")
    public String getPage() {
        return "orchestra";
    }

    @GetMapping("/orchestra/update")
    public @ResponseBody Collection<String> getUpdates() {
        return logManager.getLog();
    }

    //todo POST
    @GetMapping("/register")
    public ResponseEntity<String> registerRobot(@RequestParam String name, @RequestParam RobotType type) {
        if (name.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Input name!");
        }

        try {
            Robot robot = robotFactory.create(name, type);
            roboticOrchestra.register(robot);
            logManager.add("Successful");
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/release")
    public ResponseEntity<String> removeRobot(@RequestParam String name) {
        try {
            roboticOrchestra.remove(name);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Attempt to remove robot is failed.");
        }

    }

}