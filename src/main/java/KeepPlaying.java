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



    public static boolean keepPlaying(Board board) {
        Match.match++;
        ScoreBoardPrinter.getInstance().getPrintetScore(board);
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
            BoardhistoryArray.playerFields = new ArrayList<>();

            BoardhistoryArray.computerFields = new ArrayList<>();

            try {
                gameLoop.start();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return true;
        }


        System.out.println("╰☆☆Vielen Dank fürs Spielen☆☆╮");
        System.exit(0);
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
            TimeSafe.writer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
