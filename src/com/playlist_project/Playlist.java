package com.playlist_project;

import java.util.*;

public class Playlist {

    private String playlistName;
    private String description;
    private LinkedList<Song> songs;  //use Java implementation of doubly linked list for playlist
    private Queue<Song> shuffleQueue;

    public Playlist(String name) {
        playlistName = name;
        description = "";
        songs = new LinkedList<Song>();
        shuffleQueue = null;
    }

    public Playlist(String name, String desc) {
        playlistName = name;
        description = desc;
        songs = new LinkedList<Song>();
        shuffleQueue = null;
    }

    public String getPlaylistName() {
        return this.playlistName;
    }

    public String getDescription() {
        return this.description;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setPlaylistName(String newName) {
        this.playlistName = newName;
    }

    public void setDescription(String newDesc) {
        this.description = newDesc;
    }

    //adds song with song object to end if song is not already in playlist
    public boolean addSong(Song s) {
        if(!songs.contains(s)) {
            songs.add(s);
            return true;
        }
        return false;
    }

    //adds song with these parameters if song is not already in playlist
    public boolean addSong(String songName, String artist, String duration) {
        Song s = new Song(songName, artist, duration);
        if(!songs.contains(s)) {
            songs.add(s);
            return true;
        }
        return false;
    }

    //adds songs from given list to list that are not already in the playlist
    public void addSongs(List<Song> newSongs) {
        for(Song newSong : newSongs) {
            if(!songs.contains(newSong)) {
                songs.add(newSong);
            }
        }
    }

    //deletes song of given song object parameter
        //returns false if song does not exist in playlist, so can't delete it
    public boolean deleteSong(Song s) {
        if(songs.contains(s)) {
            songs.remove(s);
            return true;
        }
        System.out.println("Song does not exist in playlist.");
        return false;
    }

    //deletes song with given String name and artist (not just name because multiple songs could have same song by different artists)
    public boolean deleteSong(String name, String artist) {
        Song songToDel = null;
        for (Song s : songs) {
            if (s.getName().equals(name) && s.getArtist().equals(artist)) {
                songToDel = s;
                break;
            }
        }

        if(songToDel != null) {
            songs.remove(songToDel);
            return true;
        }
        System.out.println("Song does not exist in playlist.");
        return false;
    }

    //returns a double of total duration of playlist in minutes
    public double getTotalLength() {
        double length = 0;
        for(Song s : songs) {
            length += s.getLengthInMins();
        }
        return Math.round(length*100.00)/100.00;
    }

    //returns list of songs with specified name in playlist
    public List<Song> searchSongs(String name) {
        List<Song> pl= new LinkedList<Song>();

        for(Song s : songs) {
            if(s.getName().equals(name)) {
                pl.add(s);
            }
        }

        if(pl.isEmpty()) {
            return null; //if no songs with name found, returns null
        }
        return pl; //if songs with name are found, returns the list of songs with name
    }

    //returns song with specific name and artist
    public Song playSong(String name, String artist) {
        for (Song s : songs) {
            if (s.getName().equals(name) && s.getArtist().equals(artist)) {
                s.incrTimesPlayed();
                return s;
            }
        }
        System.out.println("Error- Song does not exist in playlist.");
        return null;
    }

    //creates and returns a queue of shuffled songs to play
    public Queue<Song> createShuffleQueue() {
        if(songs.isEmpty()) {
            return null;
        }

        shuffleQueue = new LinkedList<Song>(); //using linked list as a queue (FIFO)
        LinkedList<Song> plCopy = (LinkedList<Song>)(songs.clone());
        while(!plCopy.isEmpty()) {
            int removeIndex = (int)(Math.random()*plCopy.size());
            shuffleQueue.offer(plCopy.remove(removeIndex));
        }

        return shuffleQueue;
    }

    //prints out the queue created in shuffleQueue() method
    public void printQueue() {
        System.out.println("Queue Created: ");
        for(Song s : shuffleQueue) {
            System.out.println(s.toString());
        }
    }

    //returns song to "play" queue created with createShuffleQueue() method
        //will return head of queue because First In First Out
    public void playQueue() {
        if (shuffleQueue == null) { //if shuffleQueue is empty
            return;
        }
        Song songPlayed = shuffleQueue.poll();
        songPlayed.incrTimesPlayed();
        System.out.println("Playing queue..." + songPlayed.toString());
    }

    //returns most played song in playlist from comparing each song's timesPlayed field
        //if 2 songs have been played the same number of times, returns the first one
    public String mostPlayedSong() {
        Song mostPlayed = null;
        if(!songs.isEmpty()) {
            mostPlayed = songs.peek();
            for(Song s : songs) {
                if(s.getTimesPlayed() > mostPlayed.getTimesPlayed()) {
                    mostPlayed = s;
                }
            }
        }
        return mostPlayed.getName() + ", played: " + mostPlayed.getTimesPlayed() + " time(s)";  //returns null if playlist is empty
    }

    public String toString() {
        String cheese = "Playlist: " + playlistName + "\nDescription: " + description + "\n";
        for(Song song : songs) {
            cheese += song.toString() + "\n";
        }
        return cheese;
    }
}