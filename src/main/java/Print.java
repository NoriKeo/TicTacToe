import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Print {
    static int nummber;
    private static Print instance;
    public static Board boardbreck;
    static ArrayList<Integer> numbersplayer = new ArrayList();
    static int[] playerNumbers = new int[30];
    static int[] computerNumbers = new int[30];
    public Print() {

    }

    public static Print getInstancePrint() {
        if (instance == null) {
            instance = new Print();
        }
        return instance;
    }

    public void matchHistory() {

        nummber++;

        Board board = new Board();
        int i = 0;
        for (Integer playerfield : JsonFileRead.getInstance().playerArray) {
                board.getField(new Position(playerfield)).setGameCharacter('♡');
            playerNumbers[i] = playerfield;
            i++;

            }
        int c = 0;
        for (Integer computerfield : JsonFileRead.getInstance().computerArray) {
                board.getField(new Position(computerfield)).setGameCharacter('¤');
            //computerNumbers[computerfield]++;
            computerNumbers[c] = computerfield;
            c++;


            }

            System.out.println("Board " + nummber);
            board.print();
            numberUsed();


        JsonFileRead.getInstance().list3.remove("matchhistory " + JsonFileRead.getInstance().i);
        JsonFileRead.getInstance().i++;
        try {
            JsonFileRead.getInstance().jsonRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
            }




    }

    public static Board breckBoard() {
        try {
            JsonFileRead.getInstance().breck();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (JsonFileRead.getInstance().getComputerbreck() != null && JsonFileRead.getInstance().getPlayerbreck() != null) {
            int[] playerbreck = new int[JsonFileRead.getInstance().playerbreck.size()];
            int[] computerbreck = new int[JsonFileRead.getInstance().computerbreck.size()];

            for (int x = 0; x < JsonFileRead.getInstance().playerbreck.size(); x++) {
                playerbreck[x] = JsonFileRead.getInstance().playerbreck.getInt(x);
            }
            for (int x = 0; x < JsonFileRead.getInstance().computerbreck.size(); x++) {
                computerbreck[x] = JsonFileRead.getInstance().computerbreck.getInt(x);
            }

            boardbreck = new Board();
            for (Integer playerfield : playerbreck) {
                boardbreck.getField(new Position(playerfield)).setGameCharacter('♡');
            }
            for (Integer computerfield : computerbreck) {
                boardbreck.getField(new Position(computerfield)).setGameCharacter('¤');
            }

                boardbreck.print();
            return boardbreck;

        }
        return null;
    }

    public static void numberUsed() {
        int number = Integer.parseInt(ScoreBoardPrinter.getInstance().playerScore);
        int number1 = Integer.parseInt(ScoreBoardPrinter.getInstance().computerScore);
        if (number > number1) {
            for (int i = 1; i <= 9; i++) {
                int traget = i;
                long conut = Arrays.stream(playerNumbers).filter(num -> num == traget).count();
                if (conut > Match.match) {
                    System.out.println("der Spieler hat am meisten gewonnen un da bei nutzete er " + traget + " genau " + conut + " mal");

                }
            }
        }
        if (number < number1) {
            for (int i = 1; i <= 9; i++) {
                int traget = i;
                long conut = Arrays.stream(computerNumbers).filter(num -> num == traget).count();
                if (conut > Match.match && traget != 5) {
                    System.out.println("der Computer hat am meisten gewonnen un da bei nutzete er " + traget + " genau " + conut + " mal");
                }
            }
        }
    }










    public static void main(String[] args) {
        getInstancePrint().matchHistory();
        numberUsed();

    }
}
