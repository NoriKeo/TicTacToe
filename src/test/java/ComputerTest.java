import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class ComputerTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        Match.rounds = 0;
        Match.match = 0;
        Computer.numbers.clear();
        Computer.draw = false;
    }

    @Test
    void computerTestGameStart() {
        Position computerMovement = Computer.getComputerMovement(board);
        assertEquals(5, computerMovement.getIndex());

    }

    @Test
    void computerTestwinMove() {
        Board board = new Board();
        Match.match = 6;
        board.getField(new Position(5)).setGameCharacter('¤');
        board.getField(new Position(4)).setGameCharacter('¤');
        Position computerMovement = Computer.getComputerMovement(board);

        assertEquals(6, computerMovement.getIndex());

    }

    @Test
    void randomPositionTest() {
        for (int i = 1; i <= 9; i++) {
            if (i != 8 && i != 9) {
                board.getField(new Position(i)).setGameCharacter('♡');
            }
        }
        Position computerMovement = Computer.getComputerMovement(board);
        assertTrue(computerMovement.getIndex() == 9 || computerMovement.getIndex() == 8);
    }

    /*@Test
    void computerDrawTest() {
        Match.match = 6;
        for (int i = 1; i <= 9; i++) {
            if (i % 2 == 0) {
                board.getField(new Position(i)).setGameCharacter('♡');
            } else {
                board.getField(new Position(i)).setGameCharacter('¤');
            }
        }
        Position computerMovement = Computer.getComputerMovement(board);
        Assertions.assertNull(computerMovement);
        assertEquals(false, !Computer.draw);
    }*/

    @Test
    void computerMoveTestDiagonal() {
        board.getField(new Position(1)).setGameCharacter('¤');
        board.getField(new Position(9)).setGameCharacter('¤');
        Position computerMovement = Computer.getComputerMovement(board);
        assertEquals(5, computerMovement.getIndex());
    }
    


}