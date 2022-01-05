package com.playlist_project;

public class Song {

    private String name;
    private String artist;
    private String length;  //should this be length or double?? --> string should be entered as "min:sec"
    private int timesPlayed;

    public Song(String theName, String theArtist, String theLength) {
        name = theName;
        artist = theArtist;
        length = theLength;
        timesPlayed = 0;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getLength() {
        return length;
    }

    public void incrTimesPlayed() {
        timesPlayed++;
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }

    //returns a double of the length of the song in minutes
    public double getLengthInMins() {
        String[] splitLength = length.split(":");
        return Double.parseDouble(splitLength[0]) + Double.parseDouble(splitLength[1])/60;
    }

    public String toString() {
        return "\n\tSong Name: " + this.name +
                "\n\tArtist: " + this.artist +
                "\n\tLength: " + this.length;
    }
}