import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.IOException;
import java.util.ArrayList;


public class Print {
    static int nummber;
    private static Print instance;
    public static Board boardbreck;

    public Print() {

    }

    public static Print getInstancePrint() {
        if (instance == null) {
            instance = new Print();
        }
        return instance;
    }

    public void matchHistory() {

        try {
            JsonFileRead.getInstance().jsonRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        JsonArray playerArray = JsonFileRead.getInstance().getPlayerArray();
        JsonArray computerArray = JsonFileRead.getInstance().getComputerArray();
        JsonObject matchhistory = JsonFileRead.getInstance().getMatchhistory();
        ArrayList<String> list = JsonFileRead.getInstance().getList();
        ArrayList<String> list2 = JsonFileRead.getInstance().getList2();


        while (true) {
            nummber++;
            if (playerArray.isEmpty()) {
                for (String s : list) {
                    playerArray = matchhistory.getJsonArray(s);

                }
            }
            if (computerArray.isEmpty()) {
                for (String s : list2) {
                    computerArray = matchhistory.getJsonArray(s);
                }
            }

            int[] playerFields = new int[playerArray.size()];
            int[] computerFields = new int[computerArray.size()];
            for (int x = 0; x < computerArray.size(); x++) {
                computerFields[x] = computerArray.getInt(x);
            }
            for (int x = 0; x < playerArray.size(); x++) {
                playerFields[x] = playerArray.getInt(x);
            }

            Board board = new Board();
            for (Integer playerfield : playerFields) {
                board.getField(new Position(playerfield)).setGameCharacter('♡');
            }
            for (Integer computerfield : computerFields) {
                board.getField(new Position(computerfield)).setGameCharacter('¤');
            }

            System.out.println("Board " + nummber);
            board.print();

            if (list.isEmpty() && list2.isEmpty()) {
                break;
            }
            list.removeLast();
            list2.removeLast();

            playerArray = Json.createArrayBuilder().build();
            computerArray = Json.createArrayBuilder().build();


        }


    }

    public static void breckBoard() {
        if (JsonFileRead.getInstance().getComputerbreck() != null && JsonFileRead.getInstance().getPlayerbreck() != null) {
            int[] playerbreck = new int[JsonFileRead.getInstance().playerbreck.size()];
            int[] computerbreck = new int[JsonFileRead.getInstance().computerbreck.size()];

            for (int x = 0; x < JsonFileRead.getInstance().playerbreck.size(); x++) {
                playerbreck[x] = JsonFileRead.getInstance().playerbreck.getInt(x);
            }
            for (int x = 0; x < JsonFileRead.getInstance().computerbreck.size(); x++) {
                computerbreck[x] = JsonFileRead.getInstance().computerbreck.getInt(x);
            }

            boardbreck = new Board();
            for (Integer playerfield : playerbreck) {
                boardbreck.getField(new Position(playerfield)).setGameCharacter('♡');
            }
            for (Integer computerfield : computerbreck) {
                boardbreck.getField(new Position(computerfield)).setGameCharacter('¤');
            }
            boardbreck.print();

        }

    }










    public static void main(String[] args) {
        getInstancePrint().matchHistory();

    }
}
