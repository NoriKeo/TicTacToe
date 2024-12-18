import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {


    private Player player;
    private Board board;

    @BeforeEach
    void setUp() {
        player = Player.getInstance();
        board = new Board();
        for (int i = 1; i <= 9; i++) {
            board.getField(new Position(i)).isEmpty();
        }
    }

    @Test
    public void inputValideTest() {

        String simulatedInput = "5\n";
        Scanner testScanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        int input2 = player.askInput(board, testScanner);
        assertEquals(input2, 5);
    }

    @Test
    public void inputFieldTakenTest() {
        board.getField(new Position(5)).setGameCharacter('¤');
        String simulatedInput = "5\n3\n";
        Scanner testScanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        int input2 = player.askInput(board, testScanner);
        assertEquals(input2, 3);
    }

    @Test
    public void inputInvalideTest() {
        String simulatedInput = "x\n6\n";
        Scanner testScanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        int input2 = player.askInput(board, testScanner);
        assertEquals(input2, 6);
    }






}