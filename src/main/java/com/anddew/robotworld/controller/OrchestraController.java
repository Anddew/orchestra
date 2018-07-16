package com.anddew.robotworld.controller;

import com.anddew.robotworld.orchestra.History;
import com.anddew.robotworld.robot.Robot;
import com.anddew.robotworld.robot.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class OrchestraController {

    @Autowired
    private History history;

    @Qualifier("sopranoSingerRobot")
    private Robot robot;

    @GetMapping("/orchestra")
    public String getPage() {
        System.out.println("Get Home");
        return "orchestra";
    }

    @GetMapping("/orchestra/update")
    public @ResponseBody Collection<String> getUpdates() {
        robot.play(new Song("Jingle", 20));
        System.out.println("Get Updates");
        return history.getHistory();
    }

}