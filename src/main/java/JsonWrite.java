import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.locks.ReentrantLock;

public class JsonWrite {
    static PrintWriter fileWriter = null;
    static File s = new File("test.json");
    private static final ReentrantLock lock = new ReentrantLock();
    static JSONObject object;

    public static void jsonWriter() throws IOException {
        lock.lock();
        try {


        int round = Match.match;

            JSONObject matchhistory;
            JSONArray jsonArraybreck;
            JSONArray jsonArraybreck2;

            if (s.exists() && s.length() > 0) {
            String content = new String(Files.readAllBytes(Paths.get("test.json"))).trim();
            if (content.startsWith("{")) {
                object = new JSONObject(content);

                if (object.has("matchhistory " + round + " PID: " + GameLoop.pid)) {
                    matchhistory = object.getJSONObject("matchhistory " + round + " PID: " + GameLoop.pid);
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
                matchhistory = new JSONObject("matchhistory " + round + " PID: " + GameLoop.pid);
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
                object.put("playerFieldsbreck" + " PID: " + GameLoop.pid, jsonArraybreck);

                for (int computerFieldsbreck : BoardhistoryArray.computerFieldsbreck) {
                    jsonArraybreck2.put(computerFieldsbreck);
                }
                object.put("computerFieldsbreck" + " PID: " + GameLoop.pid, jsonArraybreck2);

            } else {
                object.put("playerFieldsbreck", jsonArraybreck);
                object.put("computerFieldsbreck", jsonArraybreck2);
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

                object.put("matchhistory " + round + " PID: " + GameLoop.pid, matchhistory);
                object.put("PID", "" + GameLoop.pid);


            }
            fileWriter = new PrintWriter(new FileWriter(s, false));

            fileWriter.write(object.toString(3));


        fileWriter.close();
        } finally {
            lock.unlock();
        }

    }


}