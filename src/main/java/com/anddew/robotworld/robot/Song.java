package com.anddew.robotworld.robot;

import java.io.Serializable;
import java.util.Objects;

// immutable
public class Song implements Serializable {

    private static final long serialVersionUID = -297379485024110836L;

    private String text;
    private int duration;

    public Song(String text, int duration) {
        this.text = text;
        this.duration = duration;
    }

    public String getText() {
        return text;
    }

    public int getDuration() {
        return duration;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return duration == song.duration &&
                Objects.equals(text, song.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, duration);
    }

    @Override
    public String toString() {
        return "Song{" +
                "text='" + text + '\'' +
                ", duration=" + duration +
                '}';
    }

}