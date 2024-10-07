import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;


public class JsonFileReadTest {


    @InjectMocks
    private JsonFileRead jsonFileRead;

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


        jsonFileRead.jsonRead();

        assertFalse(jsonFileRead.getPlayerArray().isEmpty());

        assertFalse(jsonFileRead.getComputerArray().isEmpty());
    }

    /*@Test
    public void testReadJsonFileNotExist() throws IOException {

        doThrow(new NoSuchFieldException("test.json")).when(jsonReaderMocki).readObject();



        JsonFileRead.getInstance().jsonRead();

        assertTrue(jsonFileRead.getPlayerArray().isEmpty());
        assertTrue(jsonFileRead.getComputerArray().isEmpty());
    }*/

}
