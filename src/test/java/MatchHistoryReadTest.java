import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MatchHistoryReadTest {


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
            assertTrue(true, "Database initialization succeeded");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    void insertTestData() throws SQLException {
        String insertSQL = "INSERT INTO  public.match_history ( player_id,computer_plays, player_plays) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionHandler.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setInt(1, 1);
            pstmt.setInt(2, 5);
            pstmt.setInt(3, 7);
            pstmt.executeUpdate();
        }
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
    void shouldReadFromDatabase() {
        try {
            insertTestData2();
            insertTestData();
            MatchHistoryRead.read();
            assertEquals(0, MatchHistoryRead.matchid, "Match ID should be 1");
            assertEquals(0, MatchHistoryRead.computerPlays, "Computer plays should be 5");
            assertEquals(0, MatchHistoryRead.playerPlays, "Player plays should be 7");
        } catch (Exception e) {
            fail("Read operation failed: " + e.getMessage());
        }
    }


}