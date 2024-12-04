import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class KeepPlaying {

    private static final Set<String> INPUTS = Set.of("Ja", "ja", "Yes", "j", "jaa", " ");
    static long time;
    static long seconds;
    static DateTimeFormatter df;


    public static boolean keepPlaying(Board board) {

        try {
            ScoreBoardPrinter.getInstance().getPrintetScore(board);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        timeStemp();
        try {
            JsonWrite.initializeDatabase();
            JsonWrite.writer();
            //JsonWrite.jsonWriter();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        seconds = TimeUnit.MILLISECONDS.toMinutes(time);
        Match.match++;
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
            if (KeepPlaying.keepPlaying(Match.board)) {
                JsonWrite.object.remove("PID");
            }
            return true;
        }

        System.out.println("╰☆☆Vielen Dank fürs Spielen☆☆╮");
        try {
            JsonWrite.initializeDatabase();
            JsonWrite.writer();
            //JsonWrite.jsonWriter();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
        //return false;
        return false;
    }

    public static void timeStemp() {
        time = Match.t2 - Match.t1;
        timeStemp();
        LocalDateTime now = LocalDateTime.now();
        df = DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm");
        int seconds7 = Math.toIntExact((time / 1000) % 60);
        System.out.println(now.format(df));
        System.out.println("Dieses Match wurde in einer zeit von " + seconds7 + " sekunden bestritten └│∵│┐┌│∵│┘");
       /* try {
            TimeSafe.writer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/


    }

    public static void main(String[] args) {
        timeStemp();
    }


}
