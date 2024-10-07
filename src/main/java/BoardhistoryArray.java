import java.util.ArrayList;
import java.util.List;

public class BoardhistoryArray {


    static List<Integer> playerFields = new ArrayList<>();
    static List<Integer> computerFields = new ArrayList<>();

    public void safeGamePlayPlayer() {
        playerFields.add(Match.input);

    }

    public void safeGamePlayComputer() {
        int i = Match.computerPosition.getIndex();
        computerFields.add(i);

    }



}