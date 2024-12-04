import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Print {
    static int nummber;
    private static Print instance;
    public static Board boardbreck;
    static ArrayList<Integer> numbersplayer = new ArrayList();
    static int[] playerNumbers = new int[30];
    static int[] computerNumbers = new int[30];
    static int computerPlays;
    static int playerPlays;
    public Print() {

    }

    public static Print getInstancePrint() {
        if (instance == null) {
            instance = new Print();
        }
        return instance;
    }
    /*String testi2 = String.valueOf(testi);
        System.out.println(testi2.length() + " länge");


        for (char c : testi2.toCharArray()) {
        System.out.println(c + ".......");
    }*/

    public void matchHistory() {

        nummber++;

        Board board = new Board();
        try {
            JsonFileRead.initializeDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            JsonFileRead.getInstance().read();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*int i = 0;
        for (Integer playerfield : JsonFileRead.getInstance().playerArray) {
                board.getField(new Position(playerfield)).setGameCharacter('♡');
            playerNumbers[i] = playerfield;
            i++;

            }*/
        String playerPlays = String.valueOf(JsonFileRead.getInstance().playerPlays);
        for (char c : playerPlays.toCharArray()) {
            int inputPlayer = c;
            board.getField(new Position(inputPlayer)).setGameCharacter('♡');
        }
        //int c = 0;
      /*  for (Integer computerfield : JsonFileRead.getInstance().computerArray) {
                //board.getField(new Position(computerfield)).setGameCharacter('¤');
            //computerNumbers[computerfield]++;
            computerNumbers[c] = computerfield;
            c++;*/
        String computerPlays = String.valueOf(JsonFileRead.getInstance().computerPlays);
        for (char y : computerPlays.toCharArray()) {
            int inputComputer = y;
            board.getField(new Position(inputComputer)).setGameCharacter('¤');
        }


        System.out.println("Board " + JsonFileRead.getInstance().matchid);
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

    public static void initializeDatabase() throws SQLException {
        try (Connection connection = ConnectionHandler.getConnection()) {
            Statement stmt = connection.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS match_history (" +
                    "match_id SERIAL PRIMARY KEY, " +
                    "player_id INT NOT NULL, " +
                    "computer_plays INT, " +
                    "player_plays INT, " +
                    "win boolean," +
                    "FOREIGN KEY (player_id) REFERENCES accounts(player_id)" +
                    ");";
            stmt.execute(createTableSQL);
        }
    }

    public static Board breckBoard() {

        String querySQL = "SELECT computer_plays, player_plays FROM match_history WHERE player_id = ? AND win = false";
        int computerPlays = -1;
        int playerPlays = -1;

        try (Connection connection = ConnectionHandler.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(querySQL);
            pstmt.setInt(1, Playername.playerId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    computerPlays = rs.getInt("computer_plays");
                    playerPlays = rs.getInt("player_plays");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (computerPlays == -1 || playerPlays == -1) {
            return null;
        }

        String playerBreck = String.valueOf(playerPlays);
        String computerBreck = String.valueOf(computerPlays);

        Board boardBreck = new Board();
        for (char playerField : playerBreck.toCharArray()) {
            int inputPlayer = Character.getNumericValue(playerField); // Convert char to int
            boardBreck.getField(new Position(inputPlayer)).setGameCharacter('♡');
        }
        for (char computerField : computerBreck.toCharArray()) {
            int inputComputer = Character.getNumericValue(computerField); // Convert char to int
            boardBreck.getField(new Position(inputComputer)).setGameCharacter('¤');
        }

        boardBreck.print();
        return boardBreck;
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
