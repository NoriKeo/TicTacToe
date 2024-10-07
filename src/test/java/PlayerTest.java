import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class PlayerTest {


    private static Board getBoard(List<Integer> blcokposition) {
        Board board = new Board();
        for (Integer i : blcokposition) {
            Position position = new Position(i);
            board.getRows().get(position.getRow()).fields.get(position.getColumn()).setGameCharacter('♡');
        }
        return board;
    }

    private static List<Field> returnWinStrategList() {
        Board board = new Board();
        List<Field> winStrategs = new ArrayList<>();
        winStrategs.add(board.getField(new Position(1)));
        winStrategs.add(board.getField(new Position(2)));
        winStrategs.add(board.getField(new Position(3)));

        return winStrategs;
    }

    private static Stream<Arguments> returnPositionsCheckstream() {
        return Stream.of(
                Arguments.of(new Board(), new Position(5)),
                Arguments.of(getBoard(List.of(1)), new Position(5)),
                Arguments.of(getBoard(List.of(5)), new Position(5))

        );
    }


    @ParameterizedTest
    @MethodSource("returnWinStrategList")
    void winStrategComputerEmptyCheck() {
        Board board = new Board();

        assertTrue(Player.askInput());

    }

    @Test
    void askInput() {


    }
}