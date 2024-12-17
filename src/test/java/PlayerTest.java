import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

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
        String input = "5\n";
        InputStream stream = new ByteArrayInputStream(input.getBytes());
        System.setIn(stream);
        int input2 = player.askInput(board);
        assertEquals(input2, 5);
    }
}