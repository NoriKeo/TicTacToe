import java.util.Set;

public class KeepPlaying {


    private static final Set<String> INPUTS = Set.of("Ja", "ja", "Yes");

    public static boolean keepPlaying() {
        //rounds = rounds - rounds;

        //¤

        Match.match++;
        Score.score();
        System.out.println("(っ◔◡◔)っ ♥ Möchtest du weiter spielen ♥");
        String input = Player.scScanner.nextLine();


        if (INPUTS.contains(input)) {
            GameLoop gameLoop = new GameLoop();
            System.out.println("˜”*°• Viel Spaß •°*”˜");
            Match.rounds = 0;
            gameLoop.start();
            return true;
        }


        System.out.println("╰☆☆Vielen Dank fürs Spielen☆☆╮");
        return false;
    }

    public static void scoreboard() {
        Score.score();
    }


}
