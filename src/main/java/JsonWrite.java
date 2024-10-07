import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonWrite {
    static PrintWriter fileWriter = null;
    static File s = new File("test.json");


    public static void jsonWriter() throws IOException {
        int round = Match.match;
        JSONObject object;
        JSONArray jsonArray;
        JSONArray jsonArray2;
        if (s.exists() && s.length() > 0) {
            String content = new String(Files.readAllBytes(Paths.get("test.json"))).trim();
            if (content.startsWith("{")) {
                object = new JSONObject(content);
                if (object.has("playerFields" + round) && object.has("computerFields" + round)) {
                    jsonArray = object.getJSONArray("playerFields" + round);
                    jsonArray2 = object.getJSONArray("computerFields" + round);
                } else {
                    jsonArray = new JSONArray();
                    jsonArray2 = new JSONArray();
                }

            } else {
                object = new JSONObject();
                jsonArray = new JSONArray();
                jsonArray2 = new JSONArray();
            }

        } else {
            object = new JSONObject();
            jsonArray = new JSONArray();
            jsonArray2 = new JSONArray();
        }

        BoardhistoryArray scoreBoard = new BoardhistoryArray();

        for (int playerFields : BoardhistoryArray.playerFields) {
            jsonArray.put(playerFields);
        }

        object.put("playerFields " + round, jsonArray);
        for (int computerFields : BoardhistoryArray.computerFields) {
            jsonArray2.put(computerFields);
        }
        object.put("computerFields " + round, jsonArray2);


        //jsonObject.put("Board" + i,board.getField);


        fileWriter = new PrintWriter(new FileWriter(s, false));
        fileWriter.write(object.toString(2));


        fileWriter.close();


    }


}
