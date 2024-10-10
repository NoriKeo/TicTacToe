import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {


    private Player player;

    @Mock
    private Board mockiBoard;

    @Mock
    private RowFromBoard mockiRowFromBoard;

    @Mock
    private Field mockiField;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        player = new Player();
    }

    @Test
    public void testAskInput() {
        String input = "1";

        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));

        int resulti = player.askInput(mockiBoard);
        assertEquals(1, resulti);
    }

    @Test
    public void testValid() {

        assertTrue(player.isvalid(1));
        assertFalse(player.isvalid(-1));
    }


   /*@Test
    public void testFreeField() throws Exception {

       List<RowFromBoard> row = new ArrayList<>();
       row.add(mockiRowFromBoard);

       Field[] fields = {mockiField, mockiField, mockiField};

       when(mockiBoard.getRows()).thenReturn(row);

       for (Field field : fields) {
       when(mockiRowFromBoard.getFields()).thenReturn(field);
       }

       when(mockiField.isEmpty()).thenReturn(false,true,false);

       when(mockiField.getPosition()).thenReturn(new Position(2));

       assertFalse(player.freefield(mockiBoard,1));

       assertTrue(player.freefield(mockiBoard,2));
   }*/
}