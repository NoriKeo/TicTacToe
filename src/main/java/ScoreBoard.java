import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {
    Position position;
    Board board;


    static List<Integer> playerFields = new ArrayList<>();
    static List<Integer> computerFields = new ArrayList<>();

    public void safeGamePlayPlayer() {
        playerFields.add(Match.input);

    }

    public void safeGamePlayComputer() {
        int i = Integer.valueOf(Match.computerPosition.getIndex());
        computerFields.add(i);

    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

}