import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.IOException; 
import com.google.gson.Gson;

import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.DataLine.Info;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;

/*
    To compile: javac SpotifyLikeApp.java
    To run: java SpotifyLikeApp
 */

// declares a class for the app

public class SpotifyLikeApp {
 

    // global variables for the app
    String status;
    Long position;
    static Clip audioClip;
    Integer songSelection;
    
    class songList {
        Map<String, song> songList;
    }
    
    class song {
        String artist;
        String album;
        String year;
        String genre;
        String filePath;
    }

    // "main" makes this class a java app that can be executed
    public static void main(final String[] args) {
        
        try {
            // create Gson instance
            FileReader songListFile=new FileReader("songList.json");   
            Gson gson = new Gson();
            songList songList = gson.fromJson(songListFile, songList.class);
                  
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // create a scanner for user input
        Scanner input = new Scanner(System.in);

        String userInput = "";
        while (!userInput.equals("q")) {

            menu();

            // get input
            userInput = input.nextLine();

            // accept upper or lower case commands
            userInput.toLowerCase();

            // do something
            handleMenu(userInput);

        }

        // close the scanner
        input.close();

    }


    /*
     * displays the menu for the app
     * How play or rewind or fastforward (look this up)
     */
    public static void menu() {

        System.out.println("---- Yo Olde Public Domain Music Player ----");
        System.out.println("[H]ome");
        System.out.println("[S]earch by title");
        System.out.println("[L]ibrary");
        System.out.println("[P]lay");
        System.out.println("[Q]uit");

        System.out.println("");
        System.out.print("Enter q to Quit:");

    }

    /*
     * handles the user input for the app
     */
    public static void handleMenu(String userInput) {

        switch(userInput) {

            case "h":
                System.out.println("-->Home<--");
                break;

            case "s":
                System.out.println("-->Search by title<--");
                break;

            case "l":
                System.out.println("-->Library<--");
                // loop through the titles (a la birthday look up)
                break;
                
            case "p":
                System.out.println("-->Play<--");
                
                Scanner songPick = new Scanner(System.in);
                System.out.println("Play which song (enter 1, 2, or 3)?");
                Integer songSelection = songPick.nextInt();
        
                songPick.close();

                play();
                break;

            case "q":
                System.out.println("-->Quit<--");
                break;

            default:
                break;
        }

    }

    /*
     * plays an audio file
     */
    public static void play() {

        // open the audio file

        switch(songSelection) {
        case 1:
            File file = new File("./src/library/blues.wav");
        case 2:
            File file = new File("./src/library/overall.wav");
        case 3:
            File file = new File("./src/library/waltz.wav");
        default:
            System.out.println("Invalid Entry, playing Blues my Naughty Sweetie Gives to Me");
            File file = new File("./src/library/blues.wav");
        }

        // final File file = new File("./src/library/blues.wav");


        try {
        
            // create clip 
            audioClip = AudioSystem.getClip();

            // get input stream
            final AudioInputStream in = getAudioInputStream(file);

            audioClip.open(in);
            audioClip.setMicrosecondPosition(0);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch(Exception e) {
            e.printStackTrace(); 
        }

    }


}



