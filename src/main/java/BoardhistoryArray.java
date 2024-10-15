import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoardhistoryArray {


    static List<Integer> playerFields = new ArrayList<>();
    static List<Integer> computerFields = new ArrayList<>();
    static List<Integer> playerFieldsbreck = new ArrayList<>();
    static List<Integer> computerFieldsbreck = new ArrayList<>();

    public static void safeGamePlayPlayer() {

        playerFields.add(Match.input);

    }

    public static void safeGamePlayComputer() {
        int i = Match.computerPosition.getIndex();
        computerFields.add(i);

    }

    public static void safer() {
        try {
            JsonWrite.jsonWriter();
            Match.roundprintsafe++;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void fieldbrecks() {
        playerFieldsbreck.add(Match.input);
        int i = Match.computerPosition.getIndex();
        computerFieldsbreck.add(i);
        try {
            JsonWrite.jsonWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}