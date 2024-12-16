import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MatchHistoryWriteTest {


    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withInitScript("init.sql")
            .withDatabaseName("testdb")
            .withUsername("postgres")
            .withPassword("testpass");

    MatchHistoryWrite matchHistoryWrite;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void setUp() throws SQLException {
        ConnectionHandler.jdbcUrl = postgres.getJdbcUrl();
        ConnectionHandler.username = postgres.getUsername();
        ConnectionHandler.password = postgres.getPassword();
        matchHistoryWrite = MatchHistoryWrite.getInstance();
        try {
            MatchHistoryWrite.initializeDatabase();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void shouldInitializeDatabase() throws SQLException {

        try {
            MatchHistoryWrite.initializeDatabase();
            assertTrue(true, "Database initialization succeeded");
        } catch (Exception e) {
            fail(e.getMessage());
        }
        /*try (Connection connection = ConnectionHandler.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT table_name FROM information_schema.tables WHERE table_name = 'match_history'");
            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next());
        }*/
    }

    void insertTestData2() throws SQLException {
        String insertSQL = "INSERT INTO  public.accounts (player_name, passwort, security_question) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionHandler.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, "name");
            pstmt.setString(2, "passwort");
            pstmt.setString(3, "Questinstuff");
            pstmt.executeUpdate();
        }
    }


    @Test
    void shouldInsertMatchHistory() throws SQLException {
        insertTestData2();
        Playername.playerId = 1;
        BoardhistoryArray.playerplay = "12";
        BoardhistoryArray.computer_play = "14";

        MatchHistoryWrite.writer();

        try (Connection connection = ConnectionHandler.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM match_history WHERE player_id = ?");
            pstmt.setInt(1, Playername.playerId);
            ResultSet rs = pstmt.executeQuery();

            assertTrue(rs.next());
            assertEquals(12, rs.getInt("player_plays"), "player_plays should be 12.");
            assertEquals(14, rs.getInt("computer_plays"), "computer_plays should be 14.");
            assertFalse(rs.getBoolean("win"), "win should be false by default.");
        }
    }

    @Test
    void shouldUpdateMatchHistory() throws SQLException {
        Playername.playerId = 1;
        BoardhistoryArray.playerplay = "12";
        BoardhistoryArray.computer_play = "14";

        MatchHistoryWrite.writer();

        BoardhistoryArray.playerplay = "18";
        BoardhistoryArray.computer_play = "20";
        MatchHistoryRead.matchid = 1;

        MatchHistoryWrite.updater();

        try (Connection connection = ConnectionHandler.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM match_history WHERE match_id = ?");
            pstmt.setInt(1, MatchHistoryRead.matchid);
            ResultSet rs = pstmt.executeQuery();

            assertTrue(rs.next());
            assertEquals(18, rs.getInt("player_plays"), "sollte 18 sein");
            assertEquals(20, rs.getInt("computer_plays"), "sollte 20 sein ");
            assertFalse(rs.getBoolean("win"));
        }
    }


}