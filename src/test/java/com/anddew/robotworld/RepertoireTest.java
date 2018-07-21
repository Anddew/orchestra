package com.anddew.robotworld;

import com.anddew.robotworld.model.Song;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RepertoireTest {

    private Repertoire repertoire;

    @Before
    public void setUp() {
        repertoire = new Repertoire();
    }

    @Test
    public void testAddSongMethod() {
        int sizeBefore = repertoire.getSongs().size();
        String newSongArtist = "NewArtist";
        String newSongTitle = "NewTitle";
        int newSongDuration = 10;
        String newSongText = "this is new song text";
        Song newSong = new Song(newSongArtist, newSongTitle, newSongDuration, newSongText);
        repertoire.addSong(newSong);
        Assert.assertTrue(repertoire.getSong(newSongTitle).equals(newSong));
        Assert.assertTrue(repertoire.getSongs().size() == (sizeBefore + 1));
    }
}