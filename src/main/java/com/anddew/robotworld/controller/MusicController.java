package com.anddew.robotworld.controller;

import com.anddew.robotworld.model.robot.Robot;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MusicController {

    //todo POST
    @GetMapping("/play")
    public void play(@RequestParam String name, @RequestParam String song) {

//        Robot robot = roboticOrchestra.get(name);

    }

}
