import org.json.JSONObject;

import java.io.File;
import java.io.PrintWriter;
import java.sql.*;
import java.util.concurrent.locks.ReentrantLock;

public class Match_History_Write {
    static PrintWriter fileWriter = null;
    static File s = new File("test.json");
    private static final ReentrantLock lock = new ReentrantLock();
    static JSONObject object;
    static String name = Playername.name;
    static int playerplay = Integer.parseInt(BoardhistoryArray.playerplay);
    static int computerPlays = Integer.parseInt(BoardhistoryArray.computer_play);


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


    public static void writer() throws SQLException {
        String insertOrUpdateSQL = "INSERT INTO match_history (player_id, computer_plays, player_plays, win) VALUES (?, ?, ?,?) ";
        boolean win = false;
        System.out.println(playerplay + "testiiiii 2");
        try (Connection connection = ConnectionHandler.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(insertOrUpdateSQL);
            pstmt.setInt(1, Playername.playerId);
            pstmt.setInt(2, computerPlays);
            pstmt.setInt(3, playerplay);
            pstmt.setBoolean(4, win);
            pstmt.executeUpdate();
        }
    }

    public static boolean checkUnresolvedWins() throws SQLException {
        String checkSQL = "SELECT * FROM match_history WHERE player_id = ? AND win = false;";

        try (Connection connection = ConnectionHandler.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(checkSQL);
            pstmt.setInt(1, Playername.playerId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }


}

