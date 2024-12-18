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

class MatchTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withInitScript("init.sql")
            .withDatabaseName("testdb")
            .withUsername("postgres")
            .withPassword("testpass");

    Match match;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
        ConnectionHandler.jdbcUrl = postgres.getJdbcUrl();
        ConnectionHandler.username = postgres.getUsername();
        ConnectionHandler.password = postgres.getPassword();

    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void setUp() {
        match = new Match();
        try {
            MatchHistoryWrite.initializeDatabase();
            Print.initializeDatabase();
        } catch (SQLException e) {
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
    void runMatchTest() {
        try {

            insertTestData2();
            setSimulatedInput("1\n2\n3\n4\n5\n");
            Playername.playerId = 1;
            match.start();
            try (Connection connection = ConnectionHandler.getConnection()) {
                PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM match_history");
                ResultSet resultSet = pstmt.executeQuery();

                assertTrue(resultSet.next());
                int playerPlays = resultSet.getInt("player_plays");
                int computerPlays = resultSet.getInt("computer_plays");
                int matchId = resultSet.getInt("match_id");

                assertEquals(5, playerPlays);
                assertEquals(5, computerPlays);
                assertTrue(matchId > 0);
            }

        } catch (Exception e) {
            fail("Fehler" + e.getMessage());
        }
    }

    private void setSimulatedInput(String input) {
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));
    }


}