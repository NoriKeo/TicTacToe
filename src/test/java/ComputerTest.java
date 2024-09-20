import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ComputerTest {

    private static Board getBoard(List<Integer> blcokposition) {
        Board board = new Board();
        for (Integer i : blcokposition) {
            Position position = new Position(i);
            board.getRows().get(position.getRow()).fields.get(position.getColumn()).setGameCharacter('♡');
        }
        return board;
    }


    @Test
    void diagonalStrategsEmptyCheck() {

    }

    @Test
    void columnStrategsEmptyCheck() {

    }

    @Test
    void rowStrategsEmptyCheck() {

    }

    @Test
    void winsstrategsEmptyCheck() {

    }

    private static List<Field> returnWinStrategList() {
        Board board = new Board();
        List<Field> winStrategs = new ArrayList<>();
        winStrategs.add(board.getField(new Position(1)));
        winStrategs.add(board.getField(new Position(2)));
        winStrategs.add(board.getField(new Position(3)));

        return winStrategs;
    }


    @ParameterizedTest
    @MethodSource("returnWinStrategList")
    void winStrategComputerEmptyCheck() {
        Board board = new Board();

        assertTrue(Computer.computerWin(board).equals(returnPositionsCheckstream()));


    }

    private static Stream<Arguments> returnPositionsCheckstream() {
        return Stream.of(
                Arguments.of(new Board(), new Position(5)),
                Arguments.of(getBoard(List.of(1)), new Position(5)),
                Arguments.of(getBoard(List.of(5)), new Position(5))

        );
    }


    @ParameterizedTest
    @MethodSource("returnPositionsCheckstream")
    void returnPositionCheck(Board board, Position position) {
        Position position1 = (Computer.getComputerMovement(board));
        System.out.println(position1.getIndex());
        System.out.println(position.getIndex());

        assertEquals(position1, position);


    }

    @Test
    void test() {
        Board board = new Board();
        while (true) {
            Position position = Computer.getComputerMovement(board);
            board.getField(new Position(1)).setGameCharacter('♡');
            board.getField(new Position(5)).setGameCharacter('¤');
            board.getField(new Position(2)).setGameCharacter('♡');
            board.getField(new Position(3)).setGameCharacter('¤');
            board.getField(new Position(7)).setGameCharacter('♡');


            if (position.getIndex() != 4) {
                System.out.println(position.getIndex());
            }


        }
    }

    @Test
    void test2() {
        Board board = new Board();
        while (true) {
            Position position = Computer.getComputerMovement(board);
            board.getField(new Position(5)).setGameCharacter('♡');
            board.getField(new Position(3)).setGameCharacter('¤');
            board.getField(new Position(1)).setGameCharacter('♡');


            System.out.println(position.getIndex());


        }
    }
    /*@Test
    void test3(){
       Integer[][] positions = {{1,2,3},{4,5,6},{7,8,9}};

        for (Integer[] i : positions) {
            Arrays.stream(i).forEach(j -> Arrays.stream(i).forEach(k -> {
                Arrays.stream(i).filter(l -> j != k && k != l && l != j).map(l -> "test " + j + " . " + k + " . " + l).forEach(System.out::println);
            }));
        }
    }*/


}