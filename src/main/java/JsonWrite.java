import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.ReentrantLock;

public class JsonWrite {
    static PrintWriter fileWriter = null;
    static File s = new File("test.json");
    private static final ReentrantLock lock = new ReentrantLock();
    static JSONObject object;
    static String name = Playername.name;
    static int playerplay = Integer.parseInt(BoardhistoryArray.playerplay);
    static int computerPlays = Integer.parseInt(BoardhistoryArray.computer_play);
    public static void jsonWriter() throws IOException {
        lock.lock();
        try {
            //System.out.println(" Name: " + name);

        int round = Match.match;

            JSONObject matchhistory;
            JSONArray jsonArraybreck;
            JSONArray jsonArraybreck2;

            if (s.exists() && s.length() > 0) {
            String content = new String(Files.readAllBytes(Paths.get("test.json"))).trim();
            if (content.startsWith("{")) {
                object = new JSONObject(content);

                if (object.has("matchhistory " + round + " Name " + name)) {
                    matchhistory = object.getJSONObject("matchhistory " + round + " Name " + name);
                    /*if (content.contains("playerFields " + round) && content.contains("computerFields " + round)) {
                    p = matchhistory.getJSONObject("playerFields " + round);
                    c = matchhistory.getJSONObject("computerFields " + round);
                    }else {
                        p = new JSONObject();
                        c = new JSONObject();
                    }*/
                } else {
                    //p = new JSONObject();
                    //c = new JSONObject();
                    matchhistory = new JSONObject();
                }
                jsonArraybreck = new JSONArray();
                jsonArraybreck2 = new JSONArray();

            } else {
                //p = new JSONObject();
                object = new JSONObject();
                matchhistory = new JSONObject();
                //c = new JSONObject();
                jsonArraybreck = new JSONArray();
                jsonArraybreck2 = new JSONArray();
            }

        } else {
                // p = new JSONObject();
            object = new JSONObject();
            matchhistory = new JSONObject();
                //c = new JSONObject();
            jsonArraybreck = new JSONArray();
            jsonArraybreck2 = new JSONArray();
        }

        BoardhistoryArray scoreBoard = new BoardhistoryArray();

            if (!Match.playerWin && !Match.computerWin) {
                for (int playerFieldsbreck : BoardhistoryArray.playerFieldsbreck) {
                    jsonArraybreck.put(playerFieldsbreck);
                }
                object.put("playerFieldsbreck" + " Name " + name, jsonArraybreck);

                for (int computerFieldsbreck : BoardhistoryArray.computerFieldsbreck) {
                    jsonArraybreck2.put(computerFieldsbreck);
                }
                object.put("computerFieldsbreck" + " Name " + name, jsonArraybreck2);

            } else {
                object.put("playerFieldsbreck" + " Name " + name, jsonArraybreck);
                object.put("computerFieldsbreck" + " Name " + name, jsonArraybreck2);
            }

            if (Match.computerWin || Match.playerWin || Computer.winsStrategy(Match.board).isEmpty() && !Match.playerWin && !Match.computerWin) {
                int i = 0;
                for (int playerFields : BoardhistoryArray.playerFields) {
                    matchhistory.put("player " + i, playerFields);
                    i++;
                }
                i = 0;
                for (int computerFields : BoardhistoryArray.computerFields) {
                    matchhistory.put("computer " + i, computerFields);
                    i++;
                }
                BoardhistoryArray.playerFieldsbreck.add(Match.input);

                object.put("matchhistory " + round + " Name " + name, matchhistory);
                object.put("PID", "" + GameLoop.pid);
                object.put(" Name ", name);


            }
            fileWriter = new PrintWriter(new FileWriter(s, false));

            fileWriter.write(object.toString(3));


        fileWriter.close();
        } finally {
            lock.unlock();
        }

    }


    public static void initializeDatabase() throws SQLException {
        try (Connection connection = ConnectionHandler.getConnection()) {
            Statement stmt = connection.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS match_history (" +
                    "match_id SERIAL PRIMARY KEY, " +
                    "player_id INT NOT NULL, " +
                    "computer_plays INT, " +
                    "player_plays INT, " +
                    "win boolean," +
                    "FOREIGN KEY (player_id) REFERENCES accounts(player_id)" +
                    ");";
            stmt.execute(createTableSQL);
        }
    }


    public static void writer(int playerId) throws SQLException {
        String insertOrUpdateSQL = "INSERT INTO match_history (player_id, computer_plays, player_plays) " +
                "VALUES (?, ?, ?) " +
                "ON CONFLICT (player_id) " +
                "DO UPDATE SET computer_plays = EXCLUDED.computer_plays, player_plays = EXCLUDED.player_plays;";

        try (Connection connection = ConnectionHandler.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(insertOrUpdateSQL);
            pstmt.setInt(1, playerId);
            pstmt.setInt(2, computerPlays);
            pstmt.setInt(3, playerplay);
            pstmt.executeUpdate();
        }
    }


}

