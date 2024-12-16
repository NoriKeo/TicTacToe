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

class ScoreBoardWriterTest {


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
    void shouldInitializeDatabase() {

        try {
            MatchHistoryWrite.initializeDatabase();
            assertTrue(true, "Database initialization succeeded");
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    void insertTestData2() throws SQLException {
        String insertSQL = "INSERT INTO  public.accounts (player_name, passwort, security_question) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionHandler.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, "pikachu");
            pstmt.setString(2, "pikachu123");
            pstmt.setString(3, "Ash Ketchum");
            pstmt.executeUpdate();
        }
    }

    @Test
    void shouldInsertSCoreWriter() throws SQLException {
        insertTestData2();
        Playername.playerId = 1;
        ScoreBoardWriter.computer_score = 12;
        ScoreBoardWriter.player_score = 14;
        ScoreBoardWriter.draw_score = 20;
        Match.rounds = 0;
        ScoreBoardWriter.writer();

        try (Connection connection = ConnectionHandler.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM score WHERE player_id = ?");
            pstmt.setInt(1, Playername.playerId);
            ResultSet rs = pstmt.executeQuery();

            assertTrue(rs.next());
            assertEquals(12, rs.getInt("computer_score"), "player_plays should be 12.");
            assertEquals(14, rs.getInt("player_score"), "computer_plays should be 14.");
            assertEquals(20, rs.getInt("draw_score"), "draw_plays should be 20.");
        }
    }

    @Test
    void shouldInsertScoreBoardWriter() throws SQLException {
        insertTestData2();

        Playername.playerId = 1;
        ScoreBoardWriter.computer_score = 12;
        ScoreBoardWriter.player_score = 14;
        ScoreBoardWriter.draw_score = 20;
        Match.rounds = 0;
        ScoreBoardWriter.writer();


        Playername.playerId = 1;
        ScoreBoardWriter.computer_score = 15;
        ScoreBoardWriter.player_score = 18;
        ScoreBoardWriter.draw_score = 25;
        Match.rounds = 1;

        ScoreBoardWriter.writer();
        try (Connection connection = ConnectionHandler.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM score WHERE player_id = ?");
            pstmt.setInt(1, Playername.playerId);
            ResultSet rs = pstmt.executeQuery();

            assertTrue(rs.next());

            assertEquals(15, rs.getInt("computer_score"), "player_plays should be 15.");
            assertEquals(18, rs.getInt("player_score"), "computer_plays should be 18.");
            assertEquals(25, rs.getInt("draw_score"), "draw_plays should be 25.");

        }
    }

}