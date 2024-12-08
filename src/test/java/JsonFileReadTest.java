import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


public class JsonFileReadTest {


    @InjectMocks
    private Match_History_Read jsonFileRead;

    @Mock
    InputStream inputStreamMocki;

    @Mock
    JsonObject jsonObjectMocki;

    @Mock
    JsonReader jsonReaderMocki;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testReadJsonFileExist() throws IOException {

        when(jsonReaderMocki.readObject()).thenReturn(jsonObjectMocki);
        when(jsonObjectMocki.get("playerFields 1")).thenReturn(jsonObjectMocki);
        when(jsonObjectMocki.get("computerFields 1")).thenReturn(jsonObjectMocki);


        //jsonFileRead.jsonRead();

        assertTrue(jsonFileRead.getPlayerArray().isEmpty());

        assertTrue(jsonFileRead.getComputerArray().isEmpty());
    }

    /*@Test
    public void testReadJsonFileNotExist() throws IOException {

        doThrow(new NoSuchFieldException("test.json")).when(jsonReaderMocki).readObject();



        JsonFileRead.getInstance().jsonRead();

        assertTrue(jsonFileRead.getPlayerArray().isEmpty());
        assertTrue(jsonFileRead.getComputerArray().isEmpty());
    }*/

    @Test
    public void testInitializeDatabase() throws SQLException {
        try (Connection connection = ConnectionHandler.getConnection()) {
            Statement stmt = connection.createStatement();
            String dropTableSQL = "DROP TABLE IF EXISTS match_history";
            stmt.execute(dropTableSQL);
        }

        Match_History_Read.initializeDatabase();


        try (Connection connection = ConnectionHandler.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "match_history", null);
            assertTrue(tables.next(), "table existiert");
        }
    }

    @Test
    public void testRead() throws SQLException {
        try (Connection connection = ConnectionHandler.getConnection()) {
            String insertSQL = "INSERT INTO match_history (player_id, computer_plays, player_plays) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(insertSQL);
            pstmt.setInt(1, 1);
            pstmt.setInt(2, 5);
            pstmt.setInt(3, 10);
            pstmt.executeUpdate();
        }

        Playername.playerId = 1;
        Match_History_Read.read();

        assertEquals(String.valueOf(5), Match_History_Read.computerPlays);
        assertEquals(String.valueOf(10), Match_History_Read.playerPlays);
        assertTrue(Match_History_Read.matchid > 0);
    }


}