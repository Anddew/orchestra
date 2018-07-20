package com.anddew.robotworld.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anddew.robotworld.history.HistoryHolder;
import com.anddew.robotworld.model.Repertoire;
import com.anddew.robotworld.model.Song;


/**
 * Song requests REST-controller.
 *
 * @author Anddew
 */
@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private HistoryHolder historyHolder;

    @Autowired
    private Repertoire repertoire;

    /**
     * Find all available song titles.
     *
     * @return collection {@link Set} of available song titles.
     */
    @GetMapping
    public Set<String> getSongs() {
        return repertoire.getSongs();
    }

    /**
     * Creates a new song.
     *
     * @param song to create
     */
    @PostMapping
    public void createSong(@RequestBody Song song) {
        repertoire.addSong(song);
        historyHolder.add("New song: " + song);
    }

}