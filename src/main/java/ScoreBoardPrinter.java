import java.io.*;


public class ScoreBoardPrinter {

    String playerScore;
    String computerScore;
    String drawScore;
    BufferedReader br;
    File s = new File("Score.txt");
    private final ScoreBoardWriter boardWriter;

    private static ScoreBoardPrinter instance;

    public ScoreBoardPrinter(ScoreBoardWriter scoreBoardWriter) {
        this.boardWriter = scoreBoardWriter;
    }

    public static ScoreBoardPrinter getInstance() {
        if (instance == null) {
            instance = new ScoreBoardPrinter(ScoreBoardWriter.getInstance());
        }
        return instance;
    }


    public void winInfoPrint(Board board) {
        FileInputStream fis;

        {
            try {
                fis = new FileInputStream(s);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }


        InputStreamReader isr = new InputStreamReader(fis);
        br = new BufferedReader(isr);


        try {
            playerScore = br.readLine();
            computerScore = br.readLine();
            drawScore = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (Match.playerWin) {
            System.out.println("Der Gewinner ist ♡ mit einem score von " + playerScore);
            System.out.println("Der score von ¤ ist " + computerScore);
        }
        if (Match.computerWin) {
            System.out.println("Der Gewinner ist ¤ mit einem score von " + computerScore);
            System.out.println("Der scorer von ♡ ist " + playerScore);
        }
        if (Computer.winsStrategy(board).isEmpty() && !Match.playerWin && !Match.computerWin) {
            System.out.println("★·.·´¯`·.·★unentschieden★·.·`¯´·.·★");
            System.out.println("es steht zum " + drawScore + "mal unentscheiden");
            System.out.println("Der scorer von ♡ ist " + playerScore);
            System.out.println("Der score von ¤ ist " + computerScore);
        }
        if (Infofield.scoreprint && !Match.playerWin && !Match.computerWin && !Computer.winsStrategy(board).isEmpty()) {
            System.out.println("(¯´•._.•Score•._.•´¯(");
            System.out.println("Der scorer von ♡ ist " + playerScore);
            System.out.println("Der score von ¤ ist " + computerScore);
            System.out.println("es gab " + drawScore + " ein unentscheiden");
            System.out.println("♥ ----------------------------------- ♥");
        }
        Match.computerWin = false;
        Match.playerWin = false;

    }

    public void getPrintetScore(Board board) {
        boardWriter.scoreCounter();
        boardWriter.scoreWrite();
        winInfoPrint(board);
    }

   /* public static void main (String[] args) {
       for (int i = 0; i < 10;){
            Match.computerWin = true;
            Match.match = i;
            ScoreBoardPrinter.getInstance().getPrintetScore(new Board());
           i++;
       }

    }*/
}
