import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;

public class Password {

    public static String input;
    static Scanner scScanner = new Scanner(System.in);
    static File s = new File("name.json");
    static String pass;
    static String question;
    private static final Set<String> inputcheck = Set.of("@", "=", "*", " ", "(", ")", "/", "|", ",", "{", "}", "[", "]");
    static boolean askSecurity = true;

    public static boolean ask() throws IOException {
        System.out.print("Gebe ein Passwort ein: ");
        String password = scScanner.nextLine();
        password = String.valueOf(password.hashCode());
        if (s.exists()) {
            String content = new String(Files.readAllBytes(Paths.get("name.json"))).trim();

            if (content.contains(password) && content.contains(Playername.name)) {
                System.out.println("Dein Passwort ist Richtig ^**^");
                return true;
            } else {
                System.out.println("Das Passwort ist leider falsch :)");
                System.out.println("Probiere es noch mal ^∞^");
                String answer = scScanner.nextLine();
                if (answer.equals(password)) {
                    System.out.println("Dein Passwort ist Richtig ^><^");
                }
                if (!answer.equals(password)) {
                    askSecurityQuestion();
                }

                Login.ask();
            }
        }
        return false;
    }

    public static void make() throws IOException {
        System.out.println("Erstelle bitte ein Passwort *^_^* ");
        pass = scScanner.nextLine();
        for (String input : inputcheck) {
            if (pass.contains(input) || pass.length() > 32) {
                System.out.println("Dein Passwort enthält nicht zu gelassene Sybole oder ist zu lang");
                make();

            }
        }
        pass = String.valueOf(pass.hashCode());
        String content = new String(Files.readAllBytes(Paths.get("name.json"))).trim();
        if (s.exists()) {
            if (content.contains(pass) && content.contains(Playername.name)) {
                System.out.println("Wähle ein anders Password");
            } else {
                if (askSecurity) {
                    securityQuestion();
                }
                Playername.writer();
            }

        }
    }

    public static void securityQuestion() throws IOException {
        System.out.println(" :D Bantworte bitte die Sicherheitsfrage: ");
        System.out.println("In welcher Stadt wurdes du geboren? ");
        question = scScanner.nextLine();
        for (String input : inputcheck) {
            if (question.contains(input)) {
                System.out.println("Bitte schreibe ein richtige Stadt /(°o°)/");
            }
        }
        question = String.valueOf(question.hashCode());
        Playername.writer();

    }

    public static void askSecurityQuestion() throws IOException {
        System.out.println("Bantworte die Sicherheitsfrage: ");
        System.out.println("^_^ In welcher Stadt wurdes du geboren?");
        question = scScanner.nextLine();
        question = String.valueOf(question.hashCode());
        if (s.exists()) {
            String content = new String(Files.readAllBytes(Paths.get("name.json"))).trim();
            JSONObject jsonObject = new JSONObject(content);
            if (content.contains(question) && content.contains(Playername.name)) {
                jsonObject.remove(Playername.name);
                try (FileWriter fileWriter = new FileWriter("name.json")) {
                    fileWriter.write(jsonObject.toString(2));
                    System.out.println("Das alte Passwort wurde gelöscht");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Bitte gebe ein neues Password ein");
                askSecurity = false;
                make();

            }
        }


    }


}
