import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class KeepPlaying {

    static int roundprintsafe;
    private static final Set<String> INPUTS = Set.of("Ja", "ja", "Yes");
    static long time;
    static long seconds;
    static long seconds3;
    static long seconds4;


    public static boolean keepPlaying(Board board) {
        //rounds = rounds - rounds;

        //¤
        ScoreBoard scoreBoard = new ScoreBoard();
        Match.match++;
        Score.score(board);
        timeStemp();
        try {
            JsonWrite.jsonWriter();
            roundprintsafe++;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        seconds = TimeUnit.MILLISECONDS.toMinutes(time);

        System.out.println("(っ◔◡◔)っ ♥ Möchtest du weiter spielen ♥");
        String input = Player.scScanner.nextLine();

        if (INPUTS.contains(input)) {
            GameLoop gameLoop = new GameLoop();
            System.out.println("˜”*°• Viel Spaß •°*”˜");
            Match.rounds = 0;
            ScoreBoard.playerFields = new ArrayList<>();
            ;
            ScoreBoard.computerFields = new ArrayList<>();
            ;
            gameLoop.start();
            return true;
        }


        System.out.println("╰☆☆Vielen Dank fürs Spielen☆☆╮");
        return false;
    }

    public static void timeStemp() {
        time = Match.t2 - Match.t1;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm");
        int seconds7 = Math.toIntExact((time / 1000) % 60);
        System.out.println(now.format(df));
        System.out.println("Dieses Match wurde in einer zeit von " + seconds7 + " sekunden bestritten");
        try {
            TimeSafe.timeSafer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
