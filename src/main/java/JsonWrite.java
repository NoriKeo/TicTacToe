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


    public static void jsonWriter() throws IOException {
        lock.lock();
        try {


        int round = Match.match;
        JSONObject object;
            JSONObject matchhistory;
        JSONArray jsonArray;
        JSONArray jsonArray2;
            JSONArray jsonArraybreck;
            JSONArray jsonArraybreck2;

        if (s.exists() && s.length() > 0) {
            String content = new String(Files.readAllBytes(Paths.get("test.json"))).trim();
            if (content.startsWith("{")) {
                object = new JSONObject("matchbreck", content);
                matchhistory = new JSONObject("matchhistory", content);
                if (matchhistory.has("playerFields" + round) && matchhistory.has("computerFields" + round)) {
                    jsonArray = matchhistory.getJSONArray("playerFields" + round);
                    jsonArray2 = matchhistory.getJSONArray("computerFields" + round);
                } else {
                    jsonArray = new JSONArray();
                    jsonArray2 = new JSONArray();
                }
                jsonArraybreck = new JSONArray();
                jsonArraybreck2 = new JSONArray();

            } else {
                object = new JSONObject();
                matchhistory = new JSONObject();
                jsonArray = new JSONArray();
                jsonArray2 = new JSONArray();
                jsonArraybreck = new JSONArray();
                jsonArraybreck2 = new JSONArray();
            }

        } else {
            object = new JSONObject();
            matchhistory = new JSONObject();
            jsonArray = new JSONArray();
            jsonArray2 = new JSONArray();
            jsonArraybreck = new JSONArray();
            jsonArraybreck2 = new JSONArray();
        }

        BoardhistoryArray scoreBoard = new BoardhistoryArray();

            if (!Match.playerWin && !Match.computerWin) {
                for (int playerFieldsbreck : BoardhistoryArray.playerFieldsbreck) {
                    jsonArraybreck.put(playerFieldsbreck);
                }
                object.put("playerFieldsbreck", jsonArraybreck);

                for (int computerFieldsbreck : BoardhistoryArray.computerFieldsbreck) {
                    jsonArraybreck2.put(computerFieldsbreck);
                }
                object.put("computerFieldsbreck", jsonArraybreck2);

            } else {
                object.put("playerFieldsbreck", jsonArraybreck);
                object.put("computerFieldsbreck", jsonArraybreck2);
            }

            if (Match.computerWin || Match.playerWin || Computer.winsStrategy(Match.board).isEmpty() && !Match.playerWin && !Match.computerWin) {
                for (int playerFields : BoardhistoryArray.playerFields) {
                    jsonArray.put(playerFields);
                }

                matchhistory.put("playerFields " + round, jsonArray);
                for (int computerFields : BoardhistoryArray.computerFields) {
                    jsonArray2.put(computerFields);
                }
                matchhistory.put("computerFields " + round, jsonArray2);
            }

        //jsonObject.put("Board" + i,board.getField);


        fileWriter = new PrintWriter(new FileWriter(s, false));
            fileWriter.write(matchhistory.toString(2));
            fileWriter.write(object.toString(3));


        fileWriter.close();
        } finally {
            lock.unlock();
        }

    }


}
