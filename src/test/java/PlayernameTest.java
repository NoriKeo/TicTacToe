import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PlayernameTest {

    private Playername playername;

    @TempDir
    Path tempDir;

    private File testFile;

    @BeforeEach
    public void setUp() {
        testFile = new File(tempDir.toFile(), "name.json");
        playername = new Playername();
        Playername.s = testFile;
    }

    @Test
    public void testAskPlayername_ValidName() throws IOException {
        playername.scScanner = new Scanner("Namie");
        String name = playername.askPlayername();
        assertEquals("Namie", name, " guter name");
    }

    @Test
    public void testAskPlayername_InvalidName() throws IOException {
        playername.scScanner = new Scanner("12223(@zzv");
        String name = playername.askPlayername();
        assertNull(name, "nicht oki");
    }

    @Test
    public void testAskPlayername_NameTooLong() throws IOException {
        playername.scScanner = new Scanner("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        String name = playername.askPlayername();
        assertNull(name, "zu lang");
    }


    @AfterEach
    public void tearDown() {
        playername.scScanner = new Scanner(System.in);
    }
}