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
        ConnectionHandler.setJdbcUrl(postgres.getJdbcUrl());
        ConnectionHandler.setUsername(postgres.getUsername());
        ConnectionHandler.setPassword(postgres.getPassword());

        matchHistoryWrite.initializeDatabase();
    }

    @Test
    void shouldInitializeDatabase() throws SQLException {
        try (Connection connection = ConnectionHandler.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT table_name FROM information_schema.tables WHERE table_name = 'match_history'");
            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next());
        }
    }

    @Test
    void shouldInsertMatchHistory() throws SQLException {
        Playername.playerId = 1;
        BoardhistoryArray.playerplay = "12";
        BoardhistoryArray.computer_play = "14";

        MatchHistoryWrite.writer();

        try (Connection connection = ConnectionHandler.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM match_history WHERE player_id = ?");
            pstmt.setInt(1, Playername.playerId);
            ResultSet rs = pstmt.executeQuery();

            assertTrue(rs.next(), "Match history should be inserted for player_id 1.");
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

            assertTrue(rs.next(), "Match history should exist for match_id 1.");
            assertEquals(18, rs.getInt("player_plays"), "player_plays should be updated to 18.");
            assertEquals(20, rs.getInt("computer_plays"), "computer_plays should be updated to 20.");
            assertFalse(rs.getBoolean("win"), "win should remain false.");
        }
    }


}