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
    ArrayList<Integer> playerArray = new ArrayList<>();
    ArrayList<Integer> computerArray = new ArrayList<>();
    JsonObject matchhistory;
    private static JsonFileRead instance;
    ArrayList<String> list;
    ArrayList<String> list2;
    ArrayList<String> list3;
    JsonObject objectreader;
    JsonReader jsonReader;
    static int i = 0;
    long p;
    File s = new File("test.json");
    int readerjust = 0;

    public JsonFileRead() {

    }

    public static JsonFileRead getInstance() {
        if (instance == null) {
            instance = new JsonFileRead();
        }
        return instance;
    }

    public void matchcounter() throws IOException {


        String content = new String(Files.readAllBytes(Paths.get("test.json"))).trim();
        list = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            if (content.contains("player " + i)) {
                list.add("player " + i);
            }
            if (content.contains("computer " + i)) {
                list2.add("computer " + i);
            }
            if (content.contains("matchhistory " + i)) {
                list3.add("matchhistory " + i);
            }
        }

    }


    public void jsonRead() throws IOException {

        if (s.exists() && s.length() > 0) {
            String content = new String(Files.readAllBytes(Paths.get("test.json"))).trim();
            InputStream is = new FileInputStream("test.json");
            jsonReader = Json.createReader(is);

            objectreader = jsonReader.readObject();
            jsonReader.close();
            is.close();

            if (content.startsWith("{")) {


                p = Long.parseLong(objectreader.getString("PID"));


                if (content.contains("matchhistory " + i + " PID: " + GameLoop.pid)) {
                    matchhistory = objectreader.getJsonObject("matchhistory " + i + " PID: " + GameLoop.pid);
                    for (String s : list) {
                        if (matchhistory.containsKey(s)) {
                            int i = matchhistory.getInt(s);
                            playerArray.add(i);
                        }
                    }
                    for (String s : list2) {
                        if (matchhistory.containsKey(s)) {
                            int i = matchhistory.getInt(s);
                            computerArray.add(i);
                        }

                    }

                }
                readerjust++;


            } else {
                objectreader = Json.createObjectBuilder().build();
            }
            if (content.contains("playerFieldsbreck " + " PID: " + GameLoop.pid) && content.contains("computerFieldsbreck " + " PID: " + GameLoop.pid)) {
                playerbreck = objectreader.getJsonArray("playerFieldsbreck");
                computerbreck = objectreader.getJsonArray("computerFieldsbreck");
                if (playerbreck == null && computerbreck == null) {

                }
            }
        } else {

            objectreader = Json.createObjectBuilder().build();
            System.out.println("File does not exist");
        }


    }


    public ArrayList getPlayerArray() {
        return playerArray;
    }

    public ArrayList getComputerArray() {
        return computerArray;
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

    public JsonObject getObjectreader() {
        return objectreader;
    }

    public static void main(String[] args) {
        try {
            JsonFileRead.getInstance().jsonRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}






