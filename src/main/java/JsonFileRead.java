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
import java.sql.*;
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
    static int computerPlays;
    static int playerPlays;
    static int matchid;
    static int i = 0;
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


                if (content.contains("matchhistory " + i + " Name " + Playername.name)) {

                    matchhistory = objectreader.getJsonObject("matchhistory " + i + " Name " + Playername.name);
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

        } else {

            objectreader = Json.createObjectBuilder().build();
            System.out.println("File does not exist");
        }


    }

    public void breck() throws IOException {
        if (s.exists() && s.length() > 0) {
            String content = new String(Files.readAllBytes(Paths.get("test.json"))).trim();
            InputStream is = new FileInputStream("test.json");
            jsonReader = Json.createReader(is);
            objectreader = jsonReader.readObject();
            jsonReader.close();
            is.close();
            if (content.contains("playerFieldsbreck" + " Name " + Playername.name) && content.contains("computerFieldsbreck" + " Name " + Playername.name)) {
                playerbreck = objectreader.getJsonArray("playerFieldsbreck" + " Name " + Playername.name);
                computerbreck = objectreader.getJsonArray("computerFieldsbreck" + " Name " + Playername.name);
                if (playerbreck == null && computerbreck == null) {

                }
            }

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

    /*public static void main(String[] args) {
        try {
            JsonFileRead.getInstance().jsonRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    public static void initializeDatabase() throws SQLException {
        try (Connection connection = ConnectionHandler.getConnection()) {
            Statement stmt = connection.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS match_history (" +
                    "match_id SERIAL PRIMARY KEY not null, " +
                    "player_id int NOT NULL  FOREIGN key (player_id) REFERENCES accounts(player_id), " +
                    "computer_plays int, " +
                    "player_plays int )";

            stmt.execute(createTableSQL);

        }
    }

    public static void read() throws SQLException {
        String querySQL = "SELECT computer_plays, player_plays, match_id FROM match_history WHERE player_id = ?";

        try (Connection connection = ConnectionHandler.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(querySQL);
            pstmt.setInt(1, Playername.playerId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    matchid = rs.getInt("match_id");
                    computerPlays = rs.getInt("computer_plays");
                    playerPlays = rs.getInt("player_plays");
                }
            }
        }
    }

}






