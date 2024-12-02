import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class Login {
    static boolean login = false;
    static Scanner scScanner = new Scanner(System.in);
    private static final Set<String> yes = Set.of("yes", "Yes", "YES", "Ja", "JA", "ja", "j", "y");
    private static final Set<String> no = Set.of("no", "nein", "ne", "n", "N", "Nein", "No");

    public static void ask() {
        System.out.println("Möchtest du dich einloggen");
        String answer = scScanner.nextLine();
        if (yes.contains(answer)) {
            Playername.ask();
            try {
                Playername.login();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            login = true;

        }
        if (no.contains(answer)) {
            System.out.println("Möchtest du dich Regestrien");
            String answer2 = scScanner.nextLine();
            if (yes.contains(answer2)) {
                Playername.ask();
                try {
                    Playername.read();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            if (no.contains(answer2)) {
                System.out.println("Bye Bye");
                System.exit(0);
            }
        }
        if (!no.contains(answer) && !yes.contains(answer)) {
            System.out.println("Bye Bye");
            System.exit(0);
        }

    }
}