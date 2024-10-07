import org.json.JSONObject;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TimeSafe {

    static PrintWriter fileWriter = null;
    static File s = new File("timesafe.json");
    static JsonReader jsonReader;
    static JsonObject jsonObject;
    static JsonObject timeread;

    public static void writer() throws IOException {

        JSONObject object;

        if (s.exists() && s.length() > 0) {
            String content = new String(Files.readAllBytes(Paths.get("timesafe.json"))).trim();
            if (content.startsWith("{")) {
                object = new JSONObject(content);
            } else {
                object = new JSONObject();
            }
        } else {
            object = new JSONObject();
        }

        object.put("time " + Match.match, KeepPlaying.seconds);

        fileWriter = new PrintWriter(new FileWriter(s, false));
        fileWriter.write(object.toString(2));


        fileWriter.close();
    }

    public static void read() throws IOException {
        if (s.exists() && s.length() > 0) {
            String content = new String(Files.readAllBytes(Paths.get("timesafe.json"))).trim();
            InputStream is = new ByteArrayInputStream(content.getBytes());
            jsonReader = Json.createReader(is);
            jsonObject = jsonReader.readObject();
            is.close();
            jsonReader.close();
            if (content.startsWith("{")) {
                timeread = jsonObject.getJsonObject("time " + Match.match);
            } else {
                timeread = Json.createObjectBuilder().build();
            }
        } else {
            timeread = Json.createObjectBuilder().build();
        }

        System.out.println(jsonObject);
    }

    public static void main(String[] args) throws IOException {

        read();
    }


}
