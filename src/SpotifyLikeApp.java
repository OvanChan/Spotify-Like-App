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
    

    public static JSONArray ReadJSONArrayFile(String fileName) {

        JSONParser jsonParser = new JSONParser();

        JSONArray songList = null;
        
        try (FileReader reader = new FileReader(fileName))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            songList = (JSONArray) obj;
            // System.out.println(songList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return songList;
        
    }       
              
    // class songList {
    //     Map<String, song> songList;
    // }
    
    // class song {
    //     String artist;
    //     String album;
    //     String year;
    //     String genre;
    //     String filePath;
    // }

    // "main" makes this class a java app that can be executed
    public static void main(final String[] args) {
        
        // Read the JSON data file
        String pathToFile = "src/songList.json";
        JSONArray jsonData = ReadJSONArrayFile(pathToFile);
        
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
            handleMenu(userInput, input, jsonData);

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
    public static void handleMenu(String userInput, Scanner input, JSONArray jsonData) {
        
        
        switch(userInput) {

            case "h":
                System.out.println("-->Home<--");
                break;

            case "s":
                System.out.println("-->Search by title<--");
                break;

            case "l":
                System.out.println("-->Library<--");
                Library(jsonData);
                break;
                
            case "p":
            
                // Scanner songPick = new Scanner(System.in);
                // System.out.println("Play which song?");
                // Integer songSelection = songPick.nextInt();
                // songPick.close();

                play(input, jsonData);
                break;

            case "q":
                System.out.println("-->Quit<--");
                break;

            default:
                break;
        }

    }


    public static void Library(JSONArray jsonData) {
                
            // loop over list
            String title;
            String artist;
            String year;
            String genre;
            String filePath;
                           
            JSONObject obj;
                for (Integer i =  0; i < jsonData.size() ; i++) {
    
                    // parse the object and pull out the name and birthday
                    obj = (JSONObject) jsonData.get(i);
                    title = (String) obj.get("title");
                    artist = (String) obj.get("artist");
                    year = (String) obj.get("year");
                    genre = (String) obj.get("genre");
                    filePath = (String) obj.get("filePath");
    
                      
                    System.out.println("title = " + title);
                    System.out.println("artist = " + artist);
                    System.out.println("year = " + year);
                    System.out.println("genre = " + genre);
                    System.out.println("filePath = " + filePath);
                    System.out.println("");
                                                          
                    
                }
            }

    /*
     * plays an audio file
     */
    public static void play(Scanner input, JSONArray jsonData) {
        
        // Scanner input;
        String songSelect;
        System.out.println("-->Play<--");
        System.out.println("Play which song?");
        songSelect = input.nextLine();
        System.out.println("You've selected:" + songSelect);
        
        String title;
        String artist;
        String year;
        String genre;
        String filePath;
                           
        JSONObject obj;
            for (Integer i =  0; i < jsonData.size() ; i++) {
                // parse the object and pull out the name and birthday
                obj = (JSONObject) jsonData.get(i);
                title = (String) obj.get("title");
                artist = (String) obj.get("artist");
                year = (String) obj.get("year");
                genre = (String) obj.get("genre");
                filePath = (String) obj.get("filePath");                    
            }
        }

        jsonData.get(filePath);
        
        // open the audio file
        final File file = new File("./src/library/blues.wav");


        try {
        
            // create clip 
            audioClip = AudioSystem.getClip();

            // get input stream
            final AudioInputStream in = getAudioInputStream(file);
            
            // 5 seconds is 5000, 
            audioClip.open(in);
            audioClip.setMicrosecondPosition(0);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch(Exception e) {
            e.printStackTrace(); 
        }

    }


}



// package src;

// import java.io.*;
// import java.util.*;
// import org.json.simple.*;
// import org.json.simple.parser.*;


// public class BirthdayExample {

//     /* 
//     Dunc: ReadJSONFile
//     Desc: Reads a json file storing an array and returns an object 
//     that can be iterated over

//     Class are groups of functions,
//     1 code contains one main function
//     */


//     public static JSONArray ReadJSONArrayFile(String fileName) {

//         JSONParser jsonParser = new JSONParser();

//         JSONArray birthdayList = null;
         
//         try (FileReader reader = new FileReader(fileName))
//         {
//             //Read JSON file
//             Object obj = jsonParser.parse(reader);
 
//             birthdayList = (JSONArray) obj;
//             System.out.println(birthdayList);
 
//         } catch (FileNotFoundException e) {
//             e.printStackTrace();
//         } catch (IOException e) {
//             e.printStackTrace();
//         } catch (ParseException e) {
//             e.printStackTrace();
//         }

//         return birthdayList;

//     }

//         //
//         // reads a json data file
//         //

//         // this is the relative path to the .json file.  If this does not work for you, use
//         // a full path to the file. Example is below.
        
//         // relative path
//         String pathToFile = "./src/songList.json";



//         JSONArray jsonData = ReadJSONArrayFile(pathToFile);


//         // loop over list
//         String birthday;
//         String name;
//         Map<String, String> lookUp = new HashMap<String, String>();
//         JSONObject obj;
//                 for (Integer i = 1; i <= jsonData.size() ; i++) {

            // // parse the object and pull out the name and birthday
            // obj = (JSONObject) jsonData.get(i);
            // title = (String) obj.get("title");
            // artist = (String) obj.get("artist");
            // year = (String) obj.get("year");
            // genre = (String) obj.get("genre");
            //     filePath = (String) obj.get("filePath");

//             // System.out.println("Track Number = " + i);
//             // System.out.println("title = " + title);
//             // System.out.println("artist = " + artist);
//             // System.out.println("year = " + year);
//             // System.out.println("genre = " + genre);
//             // System.out.println("filePath = " + filePath);
            
           
//             lookUp.put(name, birthday);

//         }
               

//         System.out.println("Your input is = " + key);
//         String result = lookUp.get(key);
//         System.out.println("This person's birthday is on " + result);


// /*
//         for (int i = 0; i < jsonData.size() ; i++) {
//             // parse the object and pull out the name and birthday
//             obj = (JSONObject) jsonData.get(i);
//             name = (String) obj.get("name");
//             if (name == key){
//             birthday = (String) obj.get("birthday");
//             System.out.println("birthday = " + birthday);
//             }
           
//             }
           
// */

//         /*
//         if name == i.get(name)
//         Avery H.:
//         return i.get(brithday)

//                         System.out.println("You entered this name: " + key);
//         if key == i.get(name);
//             return i.get(birthday);
//             System.out.println(birthday);
//         else System.out.println("Name not found");


//         Map<String, String> lookUp = new HashMap<String, String>();
//         lookUp.put(name, birthday);

//             String birthdayPoop = lookUp.get(birthday);
//         System.out.println(name);    
//         System.out.println(birthdayPoop);
//         */

//     }
    
// }
