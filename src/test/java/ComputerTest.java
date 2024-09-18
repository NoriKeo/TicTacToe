import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ComputerTest {


    @Test
    void diagonalStrategsEmptyCheck() {

        if (Computer.diagonalStrateg.isEmpty()) {
            assertTrue(Computer.diagonalStrateg.isEmpty());

        }
    }

    @Test
    void columnStrategsEmptyCheck() {
        if (!Computer.columnStrateg.isEmpty()) {
            assertFalse(Computer.columnStrateg.isEmpty());
        }
    }

    @Test
    void rowStrategsEmptyCheck() {
        if (!Computer.rowStrateg.isEmpty()) {
            assertFalse(Computer.rowStrateg.isEmpty());
        }
    }

    @Test
    void winsstrategsEmptyCheck() {
        if (Computer.winsstrateg.isEmpty()) {
            assertTrue(Computer.winsstrateg.isEmpty());
        }
    }

    @Test
    void winStrategComputerEmptyCheck() {
        if (!Computer.winStrategComputer.isEmpty()) {
            assertFalse(Computer.winStrategComputer.isEmpty());
        }
    }

    private static Stream<Arguments> returnPositionsCheckstream() {
        return Stream.of(
                Arguments.of(new Board(), new Position(5)),
                Arguments.of(getBoard(List.of(1)), new Position(5)),
                Arguments.of(getBoard(List.of(5)), new Position(5))

        );
    }

    private static Board getBoard(List<Integer> blcokposition) {
        Board board = new Board();
        for (Integer i : blcokposition) {
            Position position = new Position(i);
            board.getRows().get(position.getRow()).fields.get(position.getColumn()).setGameCharacter('♡');
        }
        return board;
    }

    @ParameterizedTest
    @MethodSource("returnPositionsCheckstream")
    void returnPositionCheck(Board board, Position position) {
        Position position1 = (Computer.getComputerMovement(board));
        assertEquals(position1, position);


    }


}