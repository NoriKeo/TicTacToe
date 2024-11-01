import org.json.JSONObject;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;

public class Playername {
    static JsonReader jsonReader;
    static JsonObject jsonObject;
    static JsonObject nameread;
    static PrintWriter fileWriter = null;
    static String input;
    static Scanner scScanner = new Scanner(System.in);
    static String name;
    static File s = new File("name.json");
    static FileWriter data;
    private static final Set<String> Playrestart = Set.of("yes", "Yes", "YES", "Ja", "JA", "ja", "j", "y");
    private static final Set<String> inputcheck = Set.of("@", "=", "*", " ", "(", ")", "/", "|", ",", "{", "}", "[", "]");


    public static void ask() {
        try {
            askPlayername();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String askPlayername() throws IOException {
        System.out.println("^~^ Bitte geben Sie einen Namen ein:");
        name = scScanner.nextLine();

        for (String intput : inputcheck) {
            if (name.contains(intput) || name.length() > 32) {
                System.out.println("Dein Name ist zulang oder enthält unerlaubte Symbole -^^,--,~");
                askPlayername();
            }

        }

        /*if (read()) {
            return name;

        }
            return null;*/
        return name;
    }

    public static void login() throws IOException {
        if (s.exists()) {
            String content = new String(Files.readAllBytes(Paths.get("name.json"))).trim();

            Password.ask();


        } else {
            data = new FileWriter("name.json");
            System.out.println("Es exesiter noch kein Arccount ");
            Password.make();

        }
    }

    public static boolean read() throws IOException {
        if (s.exists() && s.length() > 0) {
            String content = new String(Files.readAllBytes(Paths.get("name.json"))).trim();
            InputStream is = new ByteArrayInputStream(content.getBytes());
            jsonReader = Json.createReader(is);
            jsonObject = jsonReader.readObject();
            is.close();
            jsonReader.close();
            if (content.startsWith("{")) {
                if (content.contains(name)) {
                    System.out.println("Dieser Name ist schon vergebne (;-;)");
                    Login.ask();
                    return false;
                } else {
                    System.out.println("Wilkommen " + name + " viel spaß (°<°) ");
                    Password.make();
                    return true;
                }
            }

        } else {
            nameread = Json.createObjectBuilder().build();
            writer();
        }
        return true;
    }


    public static void writer() throws IOException {
        JSONObject object;

        if (s.exists() && s.length() > 0) {
            String content = new String(Files.readAllBytes(Paths.get("name.json"))).trim();
            if (content.startsWith("{")) {
                object = new JSONObject(content);
            } else {
                object = new JSONObject();
            }
        } else {
            object = new JSONObject();
        }

        object.put("Name: " + name, name);
        object.put("Name: " + name, Password.pass);
        object.put("Name: " + name, Password.pass + " " + Password.question + " " + Password.question2);


        fileWriter = new PrintWriter(new FileWriter(s, false));
        fileWriter.write(object.toString(2));


        fileWriter.close();
    }
}
