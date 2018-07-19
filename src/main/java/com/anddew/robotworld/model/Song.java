package com.anddew.robotworld.model;

import java.util.Objects;


public class Song {

    private String artist;
    private String title;
    private int duration;
    private String text;


    public Song(String artist, String title, int duration, String text) {
        this.artist = artist;
        this.title = title;
        this.duration = duration;
        this.text = text;
    }


    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return duration == song.duration &&
                Objects.equals(artist, song.artist) &&
                Objects.equals(title, song.title) &&
                Objects.equals(text, song.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artist, title, duration, text);
    }

    @Override
    public String toString() {
        return "Song{" +
                "artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", text='" + text + '\'' +
                '}';
    }

}