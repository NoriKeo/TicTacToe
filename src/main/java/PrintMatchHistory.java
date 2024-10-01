import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.IOException;
import java.util.ArrayList;

public class PrintMatchHistory {


    public static void matchHistory() {

        try {
            JsonFileRead.jsonRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        JsonArray jsonArray = JsonFileRead.jsonArray;
        JsonArray jsonArray1 = JsonFileRead.jsonArray1;
        JsonObject object = JsonFileRead.object;
        ArrayList<String> list = JsonFileRead.list;
        ArrayList<String> list2 = JsonFileRead.list2;


        while (true) {
            if (JsonFileRead.jsonArray.isEmpty()) {
                for (String s : JsonFileRead.list) {
                    jsonArray = object.getJsonArray(s);

                }
            }
            if (jsonArray1.isEmpty()) {
                for (String s : list2) {
                    jsonArray1 = object.getJsonArray(s);
                }
            }

            int[] playerFields = new int[jsonArray.size()];
            int[] computerFields = new int[jsonArray1.size()];
            for (int x = 0; x < jsonArray1.size(); x++) {
                computerFields[x] = jsonArray1.getInt(x);
            }
            for (int x = 0; x < jsonArray.size(); x++) {
                playerFields[x] = jsonArray.getInt(x);
            }

            Board board = new Board();
            for (Integer playerfield : playerFields) {
                board.getField(new Position(playerfield)).setGameCharacter('♡');
            }
            for (Integer computerfield : computerFields) {
                board.getField(new Position(computerfield)).setGameCharacter('¤');
            }


            System.out.println("boardi");
            board.print();

            if (list.isEmpty() && list2.isEmpty()) {
                break;
            }
            list.remove(list.size() - 1);
            list2.remove(list2.size() - 1);

            jsonArray = Json.createArrayBuilder().build();
            jsonArray1 = Json.createArrayBuilder().build();

        }


    }

    public static void main(String[] args) throws IOException {
        matchHistory();

    }
}
