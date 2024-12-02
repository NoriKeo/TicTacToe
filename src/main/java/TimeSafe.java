/*
public class TimeSafe {

    static PrintWriter fileWriter = null;
    static File s = new File("timesafe.json");
    static JsonReader jsonReader;
    static JsonObject jsonObject;
    static JsonObject timeread;

    public static void writer() throws IOException {

        JSONObject object;

        if (s.exists() && s.length() > 0) {
            String content = new String(Files.readAllBytes(Paths.get("timesafe.json"))).trim();
            if (content.startsWith("{")) {
                object = new JSONObject(content);
            } else {
                object = new JSONObject();
            }
        } else {
            object = new JSONObject();
        }

        object.put("time " + Match.match, KeepPlaying.seconds);

        fileWriter = new PrintWriter(new FileWriter(s, false));
        fileWriter.write(object.toString(2));


        fileWriter.close();
    }

    public static void read() throws IOException {
        if (s.exists() && s.length() > 0) {
            String content = new String(Files.readAllBytes(Paths.get("timesafe.json"))).trim();
            InputStream is = new ByteArrayInputStream(content.getBytes());
            jsonReader = Json.createReader(is);
            jsonObject = jsonReader.readObject();
            is.close();
            jsonReader.close();
            if (content.startsWith("{")) {
                timeread = jsonObject.getJsonObject("time " + Match.match);
            } else {
                timeread = Json.createObjectBuilder().build();
            }
        } else {
            timeread = Json.createObjectBuilder().build();
        }

        System.out.println(jsonObject);
    }

    public static void main(String[] args) throws IOException {

        read();
    }


}
*/


/*
public class TimeSafe {


    public static void initializeDatabase() throws SQLException {
        try (Connection connection = ConnectionHandler.getConnection()) {
            Statement stmt = connection.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS match_time (" +
                    "time_id SERIAL PRIMARY KEY, " +
                    "player_id INTEGER NOT NULL, " +
                    "match_data_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                    "CONSTRAINT fk_player FOREIGN KEY (player_id) REFERENCES accounts(player_id))";
            stmt.execute(createTableSQL);
        }
    }

    static DateTimeFormatter x = KeepPlaying.df;
    static int time = x;

    public static void writer() throws SQLException {
        String insertOrUpdateSQL = "INSERT INTO match_time (match_data_time) VALUES (?) " +
                "ON CONFLICT(match) DO UPDATE SET seconds = ?";

        try (Connection connection = ConnectionHandler.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(insertOrUpdateSQL);
            pstmt.setInt(1, x);
            pstmt.setInt(2, x);
            pstmt.executeUpdate();
        }
    }




    public static void read() throws SQLException {
        String querySQL = "SELECT seconds FROM match_time WHERE match_data_time = ? AND player_id = ?";

        try (Connection connection = ConnectionHandler.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(querySQL);
            pstmt.setInt(1, time);
            pstmt.setLong(2, player_id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int seconds = rs.getInt("seconds"); // Spaltenname korrigieren
                    System.out.println("Seconds: " + seconds);
                } else {
                    System.out.println("Keine Daten gefunden");
                }
            }
        }
    }


    public static void main(String[] args) {
        try {

            initializeDatabase();
            writer();

            read();

            ConnectionHandler.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

*/
