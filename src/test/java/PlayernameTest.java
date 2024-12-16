import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class PlayernameTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withInitScript("init.sql")
            .withDatabaseName("testdb")
            .withUsername("postgres")
            .withPassword("testpass");

    Playername playername;

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
        playername = Playername.getInstance();
        try {
            MatchHistoryRead.initializeDatabase();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void shouldInitializeDatabase() {
        try {
            Playername.initializeDatabase();
            assertTrue(true);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }


    @Test
    void shouldNewAccount() {
        InputStream orginal = System.in;
        try {
            String playerName = "pikachu";
            String passwort = "pikachu123";
            String securityQuestion = "Ash Ketchum";

            String simulatedInput = "pikachu\npikachu123\nAsh Ketchum\n";
            Scanner testScanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

            Playername.createNewAccount(testScanner);


            String hashedPassword = Playername.hashPassword(passwort);
            String hashedSecurityQuestion = Playername.hashPassword(securityQuestion);


            try (Connection connection = ConnectionHandler.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE player_name = ?");
                preparedStatement.setString(1, playerName);
                ResultSet resultSet = preparedStatement.executeQuery();

                assertTrue(resultSet.next(), "gibt es in der Datenbank");
                assertEquals(playerName, resultSet.getString("player_name"));
                assertEquals(hashedSecurityQuestion, resultSet.getString("security_question"));
                assertEquals(hashedPassword, resultSet.getString("passwort"));

            }


        } catch (Exception e) {
            fail("Fehler" + e.getMessage());

        } finally {
            System.setIn(orginal);
        }
    }

    @Test
    void shouldLogin() {
        try {
            try (Connection connection = ConnectionHandler.getConnection()) {
                String sql = "INSERT INTO accounts (player_name, security_question, passwort) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, "pikachu");
                preparedStatement.setString(2, "pikachu123");
                preparedStatement.setString(3, "Ash Ketchum");
                preparedStatement.executeUpdate();

                Playername.name = "pikachu";
                String passwort = "pikachu123";
                String simulatedInput = "pikachu\npikachu123\n";
                Scanner testScanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

                boolean login = Playername.askPlayername(testScanner);
                assertTrue(login);
                assertEquals(1, Playername.playerId);


            }

        } catch (Exception e) {
            fail("Fehler beim Einloggen: " + e.getMessage());
        }
    }

    @Test
    void shouldUpdateAccount() {
        try {
            /*try(Connection connection = ConnectionHandler.getConnection()) {
                String sql = "INSERT INTO accounts(player_name,passwort,security_question) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, "pikachu");
                preparedStatement.setString(2, "pikachu123");
                preparedStatement.setString(3, "Ash Ketchum");
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }*/

            String playerName = "pikachu";
            String passwort = "pikachu123";
            String securityQuestion = "Ash Ketchum";

            String simulatedInput = "pikachu\npikachu123\nAsh Ketchum\n";
            Scanner testScanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

            Playername.createNewAccount(testScanner);


            Playername.name = "pikachu";
            Playername.question2 = "Ash Ketchum";
            Playername.newPasswort = "pikachu1234";
            String simulatedInput2 = "pikachu\npikachu1234\nquestion\nAsh Ketchum\npikachu1234\npikachu\npikachu1234\n";
            Playername.playerId = 1;
            Scanner testScanner2 = new Scanner(new ByteArrayInputStream(simulatedInput2.getBytes()));

            Playername.askPlayername(testScanner2);
            try (Connection connection = ConnectionHandler.getConnection()) {
                String sql = "SELECT passwort FROM accounts WHERE player_name = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, "pikachu");
                ResultSet resultSet = preparedStatement.executeQuery();

                assertTrue(resultSet.next(), "gibt es in der Datenbank");
                assertEquals(Playername.hashPassword("pikachu1234"), resultSet.getString("passwort"));
            }


        } catch (Exception e) {
            fail("Fehler" + e.getMessage());
        }
    }

    @Test
    void shouldName() {
        String simulatedInput = "pikachu@@@\n";
        Scanner testScanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        Playername.name = "pikachu@@@";
        assertThrows(SQLException.class, () -> Playername.askPlayername(testScanner));
    }
}

