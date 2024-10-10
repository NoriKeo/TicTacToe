import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class JsonFileRead {
    JsonArray computerbreck;
    JsonArray playerbreck;
    JsonArray playerArray;
    JsonArray computerArray;
    private static JsonFileRead instance;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();
    JsonObject objectreader;
    JsonObject object;
    JsonReader jsonReader;
    File s = new File("test.json");

    public JsonFileRead() {
    }

    public static JsonFileRead getInstance() {
        if (instance == null) {
            instance = new JsonFileRead();
        }
        return instance;
    }


    public void jsonRead() throws IOException {

        if (s.exists() && s.length() > 0) {
            String content = new String(Files.readAllBytes(Paths.get("test.json"))).trim();
            InputStream is = new FileInputStream("test.json");
            jsonReader = Json.createReader(is);

            objectreader = jsonReader.readObject();
            jsonReader.close();
            is.close();
            for (int i = 1; i < 10; i++) {
                if (content.contains("playerFields " + i)) {
                    list.add("playerFields " + i);
                }
                if (content.contains("computerFields " + i)) {
                    list2.add("computerFields " + i);
                }
            }
            if (content.startsWith("{")) {
                for (String s : list) {
                    playerArray = objectreader.getJsonArray(s);
                }
                for (String s : list2) {
                    computerArray = objectreader.getJsonArray(s);

                }
            } else {
                playerArray = Json.createArrayBuilder().build();
                computerArray = Json.createArrayBuilder().build();
                objectreader = Json.createObjectBuilder().build();
            }
            if (content.contains("playerFieldsbreck") && content.contains("computerFieldsbreck")) {
                playerbreck = objectreader.getJsonArray("playerFieldsbreck");
                computerbreck = objectreader.getJsonArray("computerFieldsbreck");
                if (playerbreck.isEmpty() && computerbreck.isEmpty()) {

                }
            }
        } else {
            playerArray = Json.createArrayBuilder().build();
            computerArray = Json.createArrayBuilder().build();
            objectreader = Json.createObjectBuilder().build();
            System.out.println("File does not exist");
        }


    }

    public JsonArray getComputerArray() {
        return computerArray;
    }


    public JsonArray getPlayerArray() {
        return playerArray;
    }


    public ArrayList<String> getList() {
        return list;
    }


    public ArrayList<String> getList2() {
        return list2;
    }


    public JsonArray getComputerbreck() {

        return computerbreck;
    }

    public JsonArray getPlayerbreck() {

        return playerbreck;
    }


}






