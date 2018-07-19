package com.anddew.robotworld.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anddew.robotworld.log.LogManager;


@Controller
public class OrchestraController {

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

}