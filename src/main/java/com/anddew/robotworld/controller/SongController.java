package com.anddew.robotworld.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anddew.robotworld.log.LogManager;
import com.anddew.robotworld.model.Repertoire;
import com.anddew.robotworld.model.Song;


@RestController("/song")
public class SongController {

    @Autowired
    private LogManager logManager;

    @Autowired
    private Repertoire repertoire;

    @GetMapping
    public Set<String> getSongTitles() {
        return repertoire.getTitles();
    }

    @PostMapping
    public void createSong(@RequestBody Song song) {
        System.out.println(song);
        repertoire.addSong(song);
        logManager.add("New song: " + song);
    }

}