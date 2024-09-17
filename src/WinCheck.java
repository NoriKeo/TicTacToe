import java.util.ArrayList;
import java.util.List;


public class WinCheck {


    public static boolean isWin(Board board, Move move) {


        return rowwin(board, move) || columnwin(board, move) || diagonalWin(board, move);
    }

    public static boolean rowwin(Board board, Move move) {
        Position position = move.getPosition();

        ArrayList<Position> row = new ArrayList<>();
        for (Field field : board.getRows().get(position.getRow()).getFields()) {
            row.add(field.getPosition());
        }

        return gamecharactercheck(row, move.getGamecharacter(), board);
    }

    public static boolean columnwin(Board board, Move move) {
        Position position = move.getPosition();
        ArrayList<Position> column = new ArrayList<>();

        for (Row row : board.getRows()) {
            column.add(row.getFields().get(position.getColumn()).getPosition());
        }

        return gamecharactercheck(column, move.getGamecharacter(), board);

    }

    public static boolean diagonalWin(Board board, Move move) {
        Position position = move.getPosition();

        List<Position> diagonatopleft = List.of(new Position(1), new Position(5), new Position(9));
        List<Position> diagonatopright = List.of(new Position(3), new Position(5), new Position(7));


        if (gamecharactercheck(diagonatopleft, move.gamecharacter, board)) {
            return true;
        }
        if (gamecharactercheck(diagonatopright, move.gamecharacter, board)) {
            return true;
        }


        return false;


    }

    public static boolean gamecharactercheck(List<Position> diagonatopleft, char gamecharacter, Board board) {
        for (Position fieldposition : diagonatopleft) {
            if (board.getRows().get(fieldposition.getRow()).getFields().get(fieldposition.getColumn()).getGameCharacter() != gamecharacter) {
                return false;
            }
        }
        return true;
    }


}
