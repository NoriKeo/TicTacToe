import java.util.Set;

public class Infofield {
    private static final Set<String> INFO = Set.of("i", "I", "info", "INFO");
    private static final Set<String> GAME = Set.of("Game", "game", "g", "G");
    private static final Set<String> SCORE = Set.of("Score", "score", "s", "S");
    private final UserInputOutputService userIOService;
    private final Print printi;
    static boolean scoreprint = false;
    private static Infofield INSTANCE;

    private Infofield(UserInputOutputService userInputOutputService, Print print) {
        this.userIOService = userInputOutputService;
        this.printi = print;
    }

    public static Infofield getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Infofield(UserInputOutputService.getInstance(), Print.getInstancePrint());
        }
        return INSTANCE;
    }

    public void info() {
        Board board = new Board();

        userIOService.printWelcomeMessage();
        String input = userIOService.getInput();

        if (GAME.contains(input)) {
            printi.matchHistory();
            return;
        }

        if (SCORE.contains(input)) {
            scoreprint = true;
            ScoreBoardPrinter.getInstance().getPrintetScore(board);
            return;
        }

        if (INFO.contains(input)) {
            userIOService.printWelcomeMessage();
            return;
        }

        Player.askInput(board);
    }
}
