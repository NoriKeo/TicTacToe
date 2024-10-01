import java.util.Scanner;
import java.util.Set;

public class Infofield {
    private static Infofield INSTANCE;

    private static final Set<String> INPUTS = Set.of("i", "I", "info", "INFO");
    private static final Set<String> INPUTS2 = Set.of("Game", "game", "g", "G");
    private static final Set<String> INPUTS3 = Set.of("Score", "score", "s", "S");
    static boolean scoreprint = false;
    Scanner scScanner = new Scanner(System.in);

    private Infofield() {

    }

    public void info() {
        Board board = new Board();


        System.out.println("Wilkommen im infoteil");
        System.out.println("Wenn vergangene spiele sehen möchtes gebe bitte Game ein");
        System.out.println("Möchtest du das Scoreboard sehen geben bitte score ein");
        System.out.println("Möchtest du zum Spiel gebe eine Zahl ein");
        String input = input();
        if (INPUTS2.contains(input)) {
            System.out.println("game");

            PrintMatchHistory.matchHistory();

        }
        if (INPUTS3.contains(input)) {
            scoreprint = true;
            Score.score(board);
        }
        if (INPUTS.contains(input)) {
            System.out.println("Wilkommen im infoteil");
            System.out.println("Wenn vergangene spiele sehen möchtes gebe bitte Game ein");
            System.out.println("Möchtest du das Scoreboard sehen geben bitte score ein");
            System.out.println("Möchtest du zum Spiel gebe eine Zahl ein");
        }
        if (!INPUTS.contains(input) || !INPUTS2.contains(input) || !INPUTS3.contains(input)) {
            Player.askInput(board);
        }


    }

    String input() {
        return scScanner.nextLine();
    }

    public static Infofield getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Infofield();
        }

        return INSTANCE;
    }


}
