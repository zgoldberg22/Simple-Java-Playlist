package com.playlist_project;

import java.util.*;

public class main {
    public static void main(String[] args) {
        //testing methods:
        Song s1 = new Song("Don't Stop Believin'", "Journey", "4:07");
        Song s2 = new Song("Piano Man", "Billy Joel", "5:38");
        Song s3 = new Song("Eye of the Tiger", "Survivor", "4:02");
        Song s4 = new Song("Jessie's Girl", "Rick Springfield", "3:14");

        Playlist p1 = new Playlist("Classics", "Classic rock songs");
        p1.addSong(s1);
        p1.addSong(s2);
        p1.addSong(s3);
        p1.addSong(s4);
        System.out.println(p1.toString());
        System.out.println("\nPlaylist's total length: " + p1.getTotalLength() + " minutes");

        Song[] songsToAdd = new Song[]{
                new Song("Bohemian Rhapsody", "Queen", "5:54"),
                new Song("Highway to Hell", "AC/DC", "3:28"),
                new Song("Rocket Man", "Elton John", "4:40"),
                new Song("Dream On", "Aerosmith", "4:26")
        };

        p1.addSongs(Arrays.asList(songsToAdd));
        System.out.println(p1.toString());
        System.out.println("\nPlaylist's total length: " + p1.getTotalLength() + " minutes");

        p1.deleteSong("Rocket Man", "Elton John");
        System.out.println(p1.toString());

        p1.addSong("Dream On", "Glee Cast", "4:26");
        List<Song> dreamOn = p1.searchSongs("Dream On");
        System.out.println("\nSearching for song named 'Dream On': " + dreamOn.toString());

        System.out.println();
        p1.createShuffleQueue();
        p1.printQueue();

        p1.playQueue();
        System.out.println("Most Played Song is: " + p1.mostPlayedSong());
    }
}