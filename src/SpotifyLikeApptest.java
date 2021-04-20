import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class SpotifyLikeApptest {

    

    public static JSONArray ReadJSONArrayFile(String fileName) {

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

        public static void main(final String[] args) {
        
  
        String pathToFile = "src/songList.json";

        JSONArray jsonData = ReadJSONArrayFile(pathToFile);


        // loop over list
        String title;
        String artist;
        String year;
        String genre;
        String filePath;

        // Map<String, String> lookUp = new HashMap<String, String>();
        JSONObject obj;
                for (Integer i =  0; i < jsonData.size() ; i++) {

            // parse the object and pull out the name and birthday
            obj = (JSONObject) jsonData.get(i);
            title = (String) obj.get("title");
            artist = (String) obj.get("artist");
            year = (String) obj.get("year");
            genre = (String) obj.get("genre");
            filePath = (String) obj.get("filePath");

            System.out.println("Track Number = " + i);
            System.out.println("title = " + title);
            System.out.println("artist = " + artist);
            System.out.println("year = " + year);
            System.out.println("genre = " + genre);
            System.out.println("filePath = " + filePath);
            
        
            // lookUp.put(title, artist, year, genre, filePath);

        }
        
    }
}
        
    
               

        // System.out.println("Your input is = " + key);
        // String result = lookUp.get(key);
        // System.out.println("This person's birthday is on " + result);




        /*
        if name == i.get(name)
        Avery H.:
        return i.get(brithday)

                        System.out.println("You entered this name: " + key);
        if key == i.get(name);
            return i.get(birthday);
            System.out.println(birthday);
        else System.out.println("Name not found");


        Map<String, String> lookUp = new HashMap<String, String>();
        lookUp.put(name, birthday);

            String birthdayPoop = lookUp.get(birthday);
        System.out.println(name);    
        System.out.println(birthdayPoop);
        */

    
    

