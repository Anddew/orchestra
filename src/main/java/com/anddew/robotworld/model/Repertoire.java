package com.anddew.robotworld.model;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Container of songs, which orchestra can play.
 *
 * @author Anddew
 */
@Component
public class Repertoire {

    private final static Logger LOGGER = Logger.getLogger(Repertoire.class);

    private static final String SONGS_DIRECTORY_PATTERN = "/songs/*.properties";
    private static final String SONGS_DIRECTORY_PREFIX = "/songs/";
    private static final String PROPERTIES_FILE_TYPE = ".properties";
    private static final String EMPTY_STRING = "";
    private static final String ARTIST_KEY_NAME = "artist";
    private static final String TITLE_KEY_NAME = "title";
    private static final String DURATION_KEY_NAME = "duration";
    private static final String TEXT_KEY_NAME = "text";

    private Map<String, Song> repertoire = new ConcurrentHashMap<>();

    /**
     * Constructor.
     * Reads directory '/resources/song' to find '.properties' files with songs data.
     * @throws RuntimeException if attempt to read file was failed or required property not found
     */
    public Repertoire() {
        PathMatchingResourcePatternResolver searcher = new PathMatchingResourcePatternResolver();
        Resource[] resources;
        try {
            resources = searcher.getResources(SONGS_DIRECTORY_PATTERN);
        } catch (IOException e) {
            throw new RuntimeException("Error during reading resources.", e);
        }
        for (Resource resource : resources) {
            String fileName = resource.getFilename().replace(PROPERTIES_FILE_TYPE, EMPTY_STRING);
            ResourceBundle source = ResourceBundle.getBundle(SONGS_DIRECTORY_PREFIX + fileName);

            String artist = source.getString(ARTIST_KEY_NAME);
            String title = source.getString(TITLE_KEY_NAME);
            int duration = Integer.parseInt(source.getString(DURATION_KEY_NAME));
            String text = source.getString(TEXT_KEY_NAME);
            Song song = new Song(artist, title, duration, text);

            repertoire.put(title, song);
            LOGGER.info("Song '" + song.getArtist() + " : " + song.getTitle() + "' was added to repertoire.");
        }
    }

    /**
     * Find song by specified title.
     *
     * @param title of song
     * @return appropriate song
     * @throws RuntimeException if song is not exist
     */
    public Song getSong(String title) {
        Song song = repertoire.get(title);
        if (song == null) {
            throw new RuntimeException("Song with title '" + title + "' was not found.");
        }
        return song;
    }

    /**
     * Insert a new song into container.
     *
     * @param song to be inserted
     */
    public void addSong(Song song) {
        repertoire.put(song.getTitle(), song);
        LOGGER.info("Song '" + song.getArtist() + " : " + song.getTitle() + "' was added to repertoire.");
    }

    /**
     * Find all available songs.
     *
     * @return collection {@link Set} of available songs
     */
    public Set<String> getSongs() {
        return repertoire.keySet();
    }

    public void addSong(Song song) {
        repertoire.put(song.getTitle(), song);
    }

    public Set<String> getTitles() {
        return repertoire.keySet();
    }

}