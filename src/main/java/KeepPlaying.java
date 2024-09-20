import java.util.Set;

public class KeepPlaying {


    private static final Set<String> INPUTS = Set.of("Ja", "ja", "Yes");

    public static boolean keepPlaying() {
        //rounds = rounds - rounds;

        //¤

        if (Match.playerWin) {
            Match.scorex++;
            System.out.println("Der Gewinner ist ♡ mit einem score von " + Match.scorex);
            Match.playerWin = false;

        }
        if (Match.computerWin) {
            Match.scorey++;
            System.out.println("Der Gewinner ist ¤ mit einem score von " + Match.scorey);
            Match.computerWin = false;
        }


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


}
