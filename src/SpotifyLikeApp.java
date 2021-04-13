import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.File; 
import java.io.IOException; 
import java.util.Scanner; 

  
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


    public static JSONArray ReadJSONArrayFile(String fileName) {

        /* 
        read the birthday.json file and iterate over it
    
        parsing is converting
        */
    
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
    
        JSONArray songList = null;
         
        try (FileReader reader = new FileReader(fileName))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
    
            songList = (JSONArray) obj;
            System.out.println(songList);
    
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    
        return songList;
    
    }

    // global variables for the app
    String status;
    Long position;
    static Clip audioClip;

    // "main" makes this class a java app that can be executed
    public static void main(final String[] args) {

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
        final File file = new File("./src/library/blues.wav");

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




// public class BirthdayExample {

//     /* 
//     Dunc: ReadJSONFile
//     Desc: Reads a json file storing an array and returns an object 
//     that can be iterated over

//     Class are groups of functions,
//     1 code contains one main function
//     */




//     public static void main(final String[] args) {

//         //
//         // how to read user input from keyboard
//         //
//         System.out.println("Reading user input into a string");

//         // get user input
//         Scanner input = new Scanner(System.in);
//         System.out.print("Enter a name:");
//         String key = input.nextLine();
    
        
//         // print user inputp
//         System.out.println("name = " + key);
    
//         // close the scanner
//         input.close();


//         //
//         // reads a json data file
//         //

//         // this is the relative path to the .json file.  If this does not work for you, use
//         // a full path to the file. Example is below.
        
//         // relative path
//         String pathToFile = "./src/birthday.json";

//         // full path
//         // String pathToFile = "E:/Users/jerome/GitHub/evc-cit044-java-references-and-data-structures/src/birthday.json";


//         JSONArray jsonData = ReadJSONArrayFile(pathToFile);


//         // loop over list
//         String birthday;
//         String name;
//         Map<String, String> lookUp = new HashMap<String, String>();
//         JSONObject obj;
//                 for (Integer i = 0; i < jsonData.size() ; i++) {

//             // parse the object and pull out the name and birthday
//             obj = (JSONObject) jsonData.get(i);
//             birthday = (String) obj.get("birthday");
//             name = (String) obj.get("name");

//             // System.out.println("name = " + name);
//             // System.out.println("birthday = " + birthday);
            
           
//             lookUp.put(name, birthday);

//         }
               

//         System.out.println("Your input is = " + key);
//         String result = lookUp.get(key);
//         System.out.println("This person's birthday is on " + result);
