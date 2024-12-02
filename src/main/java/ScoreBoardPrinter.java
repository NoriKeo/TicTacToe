import java.io.BufferedReader;
import java.io.File;
import java.sql.*;


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

    public static void initializeDatabase() throws SQLException {
        try (Connection connection = ConnectionHandler.getConnection()) {
            Statement stmt = connection.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS score (" +
                    "score_id SERIAL PRIMARY KEY not null, " +
                    "player_id int NOT NULL foreign key (player_id) REFERENCES accounts(player_id), " +
                    "computer_score int, " +
                    "player_score int," +
                    " draw_score int) )";
            stmt.execute(createTableSQL);
        }
    }

    public void read(int playerId) throws SQLException {
        String querySQL = "SELECT computer_score, player_score , draw_score FROM score WHERE player_id = ?";

        try (Connection connection = ConnectionHandler.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(querySQL);
            pstmt.setInt(1, playerId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    computerScore = String.valueOf(rs.getInt("computer_plays"));
                    playerScore = String.valueOf(rs.getInt("player_plays"));
                    drawScore = String.valueOf(rs.getInt("draw_plays"));
                }
            }
        }
    }


    public void winInfoPrint(Board board) {
       /* FileInputStream fis;

        {
            try {
                fis = new FileInputStream(s);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }


        InputStreamReader isr = new InputStreamReader(fis);
        br = new BufferedReader(isr);
*/

       /* try {
            playerScore = br.readLine();
            computerScore = br.readLine();
            drawScore = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        if (Match.playerWin && !Computer.draw) {
            System.out.println("Der Gewinner ist ♡ mit einem score von " + playerScore + " ( •̀ᄇ• ́)ﻭ✧ ");
            System.out.println("Der score von ¤ ist " + computerScore);
        }
        if (Match.computerWin && !Computer.draw) {
            System.out.println("Der Gewinner ist ¤ mit einem score von " + computerScore + "╭( ･ㅂ･)و ̑̑ ＂");
            System.out.println("Der scorer von ♡ ist " + playerScore);
        }
        if (Computer.winsStrategy(board).isEmpty() && !Match.playerWin && !Match.computerWin || Computer.draw) {
            System.out.println("★·.·´¯`·.·★unentschieden★·.·`¯´·.·★");
            System.out.println("es steht zum " + drawScore + " mal unentscheiden (❁ᴗ͈ ˬ ᴗ͈)ᶻᶻᶻ✧");
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
       /* Match.computerWin = false;
        Match.playerWin = false;*/

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
