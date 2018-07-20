package com.anddew.robotworld.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
     * @throws RuntimeException if any field is empty
     */
    @PostMapping
    public ResponseEntity<String> createSong(@RequestBody Song song) {
        if (song.getArtist().isEmpty() || song.getTitle().isEmpty() || song.getDuration() == 0 || song.getText().isEmpty()) {
            return ResponseEntity.status(400).body("All fields in object Song are required.");
        }

        repertoire.addSong(song);
        historyHolder.add("New song: " + song);
        return ResponseEntity.status(200).build();
    }

}