import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class ScoreBoardWriter {
    File s = new File("Score.txt");
    static int scorex;
    static int scorey;
    PrintWriter pWriter;
    int draw;
    BufferedWriter writer;
    private static ScoreBoardWriter instance;


    public ScoreBoardWriter() {
    }

   /* {
        try {
            writer = new BufferedWriter(new FileWriter(s));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/


    public static ScoreBoardWriter getInstance() {
        if (instance == null) {
            instance = new ScoreBoardWriter();
        }
        return instance;
    }


    public void scoreCounter() {
        if (Match.playerWin) {
            scorex++;
        }
        if (Match.computerWin) {
            scorey++;
        }
        if (Computer.draw) {
            draw++;
        }
    }


    public void scoreWrite() {
        FileInputStream fis;
        try {
            fis = new FileInputStream(s);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }


        InputStreamReader isr1 = new InputStreamReader(fis);
        BufferedReader br2 = new BufferedReader(isr1);
        String x;
        String y;
        String d;

        try {
            x = br2.readLine();//System.out.println(x);
            y = br2.readLine();//System.out.println(y);
            d = br2.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (x == null && y == null && d == null) {
            x = "0";
            y = "0";
            d = "0";
        }
        if (Match.match == 0) {
            int nummer = Integer.parseInt(x);
            scorex = scorex + nummer;
            int nummer2 = Integer.parseInt(y);

            scorey = scorey + nummer2;
            int nummer3 = Integer.parseInt(d);
            draw = draw + nummer3;
        }
        try {
            pWriter = new PrintWriter(new FileWriter(s));
            pWriter.println(scorex);
            pWriter.println(scorey);
            pWriter.println(draw);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (pWriter != null) {
                pWriter.flush();
                pWriter.close();
            }
        }

    }

    public static void initializeDatabase() throws SQLException {
        try (Connection connection = ConnectionHandler.getConnection()) {
            Statement stmt = connection.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS score (" +
                    "score_id SERIAL PRIMARY KEY, " +
                    "player_id INT NOT NULL, " +
                    "computer_score INT, " +
                    "player_score INT, " +
                    "draw_score INT," +
                    "FOREIGN KEY (player_id) REFERENCES accounts(player_id)" +
                    ");";
            stmt.execute(createTableSQL);
        }
    }


    int computer_score = scorey;
    int player_score = scorex;
    int draw_score = draw;

    public void writer(int playerId) throws SQLException {
        String insertOrUpdateSQL = "INSERT INTO scor (player_id, computer_score, player_score, draw_score) " +
                "VALUES (?, ?, ?,?) " +
                "ON CONFLICT (player_id) " +
                "DO UPDATE SET computer_score = EXCLUDED.computer_score, player_score = EXCLUDED.player_score, draw_score = EXCLUDED.draw_score;";

        try (Connection connection = ConnectionHandler.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(insertOrUpdateSQL);
            pstmt.setInt(1, playerId);
            pstmt.setInt(2, computer_score);
            pstmt.setInt(3, player_score);
            pstmt.setInt(4, draw_score);
            pstmt.executeUpdate();
        }
    }







}



