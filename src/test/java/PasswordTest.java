import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PasswordTest {

    private final File testFile = new File("name.json");

    @Before
    public void setUp() throws IOException {
        if (!testFile.exists()) {
            testFile.createNewFile();
        }
    }

    @After
    public void tearDown() throws IOException {
        if (testFile.exists()) {
            Files.deleteIfExists(Paths.get("name.json"));
        }
    }

    @Test
    public void testRightPassword() throws IOException {
        userInput("richtigesPassword\n");
        playerMocki("richtigesPassword");

        //boolean result = Password.ask();

        // assertTrue("Password richig", result);
    }


    @Test
    public void testFalsePassword() throws IOException {
        userInput("wrong_password\ncorrect_answer\n");
        playerMocki("richtigesPassword");

        // boolean result = Password.ask();

        //assertFalse("Password falsch", result);
    }


    @Test
    public void testSecurityQuestion() throws IOException {
        userInput("Berlin\n");
        // Password.securityQuestion();

    }

    @Test
    public void testSecurityQuestionMismatch() throws IOException {
        userInput("wrong_answer\n");
        playerMocki("richtigesPassword");

        // Password.askSecurityQuestion();

    }

    private void userInput(String data) {
        InputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);
    }

    private void playerMocki(String password) throws IOException {
        if (!testFile.exists()) {
            testFile.createNewFile();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Playername");
        jsonObject.put("password", String.valueOf(password.hashCode()));
        jsonObject.put("sicherheitsFrage", String.valueOf("Berlin".hashCode()));

        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write(jsonObject.toString(2));
        }
    }
}