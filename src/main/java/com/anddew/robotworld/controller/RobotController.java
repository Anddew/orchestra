package com.anddew.robotworld.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anddew.robotworld.log.LogManager;
import com.anddew.robotworld.model.Repertoire;
import com.anddew.robotworld.model.RoboticOrchestra;
import com.anddew.robotworld.model.Song;
import com.anddew.robotworld.model.robot.Robot;
import com.anddew.robotworld.model.robot.RobotFactory;
import com.anddew.robotworld.model.robot.RobotType;


@RestController
public class RobotController {

    private static final String ROBOT_NAME_PARAMETER = "name";
    private static final String ROBOT_TYPE_PARAMETER = "type";
    private static final String SONG_TITLE_PARAMETER = "title";

    @Autowired
    private RoboticOrchestra roboticOrchestra;

    @Autowired
    private Repertoire repertoire;

    @Autowired
    private RobotFactory robotFactory;

    @Autowired
    private LogManager logManager;


    @ResponseBody
    @PostMapping("/register")
    public ResponseEntity<String> registerRobot(@RequestBody Map<String, String> parameters) {
        String name = parameters.get(ROBOT_NAME_PARAMETER);
        String type = parameters.get(ROBOT_TYPE_PARAMETER);
        if (name == null || name.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Input name!");
        }

        try {
            Robot robot = robotFactory.create(name, RobotType.valueOf(type));
            roboticOrchestra.register(robot);
            logManager.add("New robot '" + robot + "' was created.");
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/release")
    public ResponseEntity<String> removeRobot(@RequestBody Map<String, String> parameters) {
        try {
            String name = parameters.get(ROBOT_NAME_PARAMETER);
            roboticOrchestra.remove(name);
            logManager.add("Robot '" + name + "' was removed.");
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PostMapping("/play")
    public ResponseEntity<String> play(@RequestBody Map<String, String> parameters) {
        try {
            String name = parameters.get(ROBOT_NAME_PARAMETER);
            Robot robot = roboticOrchestra.get(name);
            String title = parameters.get(SONG_TITLE_PARAMETER);
            Song song = repertoire.getSong(title);

            logManager.add("New task - '" + name + "' plays '" + song + "'.");
            String musicalPart = robot.play(song);
            logManager.add("Robot '" + robot + "': '" + musicalPart + "'.");
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
