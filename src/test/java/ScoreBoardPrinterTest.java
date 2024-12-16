import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


public class ScoreBoardPrinterTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withInitScript("init.sql")
            .withDatabaseName("testdb")
            .withUsername("postgres")
            .withPassword("testpass");

    MatchHistoryRead matchHistoryRead;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void setUp() {
        ConnectionHandler.jdbcUrl = postgres.getJdbcUrl();
        ConnectionHandler.username = postgres.getUsername();
        ConnectionHandler.password = postgres.getPassword();
        matchHistoryRead = MatchHistoryRead.getInstance();
        try {
            MatchHistoryRead.initializeDatabase();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void shouldInitializeDatabase() {
        try {
            MatchHistoryRead.initializeDatabase();
            assertTrue(true);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    void insertTestData() throws SQLException {
        String insertSQL = "INSERT INTO  public.score ( player_id,computer_score, player_score,draw_score) VALUES (?, ?, ?,?)";
        try (Connection connection = ConnectionHandler.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setInt(1, 1);
            pstmt.setInt(2, 5);
            pstmt.setInt(3, 7);
            pstmt.setInt(4, 3);
            pstmt.executeUpdate();
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
    void shouldPrintScore() throws SQLException {
        insertTestData2();
        insertTestData();

        Playername.playerId = 1;
        ScoreBoardPrinter.getInstance().read();
        assertEquals("5", ScoreBoardPrinter.getInstance().computerScore, "sollte 5 sein");
        assertEquals("7", ScoreBoardPrinter.getInstance().playerScore, "sollte 7 sein");
        assertEquals("3", ScoreBoardPrinter.getInstance().drawScore, "sollte 3 sein");
    }

}