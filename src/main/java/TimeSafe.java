import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TimeSafe {

    static PrintWriter fileWriter = null;
    static File s = new File("timesafe.json");


    public static void timeSafer() throws IOException {

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


}
