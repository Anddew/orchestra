package com.anddew.robotworld.controller;

import com.anddew.robotworld.Repertoire;
import com.anddew.robotworld.history.HistoryHolder;
import com.anddew.robotworld.model.Song;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class SongControllerTest {

    private SongController songController;
    private Repertoire repertoire;
    private HistoryHolder historyHolder;

    public SongControllerTest() {
        songController = new SongController();
    }

    @Before
    public void setUp() {
        repertoire = mock(Repertoire.class);
        Whitebox.setInternalState(songController, "repertoire", repertoire);

        historyHolder = mock(HistoryHolder.class);
        Whitebox.setInternalState(songController, "historyHolder", historyHolder);
    }

    @Test
    public void testCreateSongMethodInvalidParameters() {
        Song song = new Song(null, null, 0, null);
        ResponseEntity responseEntity = songController.createSong(song);
        Assert.assertTrue(responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testCreateSongMethod() {
        Song song = new Song("artist", "title", 42, "text");
        ResponseEntity responseEntity = songController.createSong(song);
        Assert.assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
        verify(repertoire).addSong(song);
        verifyNoMoreInteractions(repertoire);
    }

}
