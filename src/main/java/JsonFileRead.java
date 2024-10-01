import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class JsonFileRead {


    static File s = new File("test.json");
    static JsonObject object;
    static JsonArray jsonArray = Json.createArrayBuilder().build();
    static JsonArray jsonArray1 = Json.createArrayBuilder().build();
    static JsonReader jsonReader;
    static ArrayList<String> list = new ArrayList<>();
    static ArrayList<String> list2 = new ArrayList<>();


    public static void jsonRead() throws IOException {


        if (s.exists() && s.length() > 0) {
            String content = new String(Files.readAllBytes(Paths.get("test.json"))).trim();
            InputStream is = new FileInputStream("test.json");
            jsonReader = Json.createReader(is);
            object = jsonReader.readObject();
            jsonReader.close();
            is.close();
            for (int i = 1; i < 10; i++) {
                if (content.contains("playerFields " + i)) {
                    list.add("playerFields " + i);
                }
                if (content.contains("computerFields " + i)) {
                    list2.add("computerFields " + i);
                }
            }
            if (content.startsWith("{")) {
                for (String s : list) {
                    jsonArray = object.getJsonArray(s);
                }
                for (String s : list2) {
                    jsonArray1 = object.getJsonArray(s);

                }
            } else {
                jsonArray = Json.createArrayBuilder().build();
                jsonArray1 = Json.createArrayBuilder().build();
                object = Json.createObjectBuilder().build();
            }
        } else {
            jsonArray = Json.createArrayBuilder().build();
            jsonArray1 = Json.createArrayBuilder().build();
            object = Json.createObjectBuilder().build();
            System.out.println("File does not exist");
        }


    }
}






