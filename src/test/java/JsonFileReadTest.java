import org.json.JSONArray;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import javax.json.JsonObject;

import static org.mockito.Mockito.mock;


class JsonFileReadTest {
    Board board;
    JsonObject object;
    JSONArray jsonArray;


    @Before
    public void setUp() {
        object = (JsonObject) mock(JsonWrite.class);
    }


    @Test
    void jsonRead() {

    }
}