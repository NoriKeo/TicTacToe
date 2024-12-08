import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Scanner;
import java.util.Set;

public class Playername {
    static JsonReader jsonReader;
    static JsonObject jsonObject;
    static JsonObject nameread;
    static PrintWriter fileWriter = null;
    static String input;
    static Scanner scScanner = new Scanner(System.in);
    static String name;
    static File s = new File("name.json");
    static FileWriter data;
    static String question;
    static String question2;
    static String newPasswort;
    static int playerId;
    private static final Set<String> Playrestart = Set.of("yes", "Yes", "YES", "Ja", "JA", "ja", "j", "y");
    private static final Set<String> inputcheck = Set.of("@", "=", "*", " ", "(", ")", "/", "|", ",", "{", "}", "[", "]");


    public static void ask() {
        try {
            initializeDatabase();
            askPlayername();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /* public static boolean askPlayername() throws IOException, SQLException {
         System.out.println("^~^ Bitte geben Sie einen Namen ein:");
         name = scScanner.nextLine();

         for (String intput : inputcheck) {
             if (name.contains(intput) || name.length() > 32) {
                 System.out.println("Dein Name ist zulang oder enthält unerlaubte Symbole -^^,--,~");
                 askPlayername();
             }

         }
         try (Connection connection = ConnectionHandler.getConnection();
              Scanner scanner = new Scanner(System.in)) {

             System.out.print("Gebe ein Passwort ein: ");
             String password = scScanner.nextLine();
             password = hashPassword(password);

             PreparedStatement checkCredentialsStmt = connection.prepareStatement(
                     "SELECT player_id FROM accounts WHERE player_name = ? AND passwort = ?");
             checkCredentialsStmt.setString(1, name);
             checkCredentialsStmt.setString(2, password);

             ResultSet resultSet = checkCredentialsStmt.executeQuery();
             if (resultSet.next()) {
                 playerId = resultSet.getInt("player_id");
                 System.out.println("einloggen war erfolgreich");
                 return true;
             } else {
                 System.out.println("irgend was ist falsch gelaufen");
                 System.out.println("wenn du einen neuen Account erstellen möchtest geben bitte create ein ");
                 System.out.println("wenn du die sicherheitsfrage nutzen möchtest um einen neues Passwort zu erstellen das gebe bitte question ein ");
                 String response = scanner.nextLine();

                 if (response.equalsIgnoreCase("question")) {
                     System.out.println("Bantworte die Sicherheitsfrage: ");
                     System.out.println("Wie hieß dein erstes Plüschtier? ");
                     question2 = scScanner.nextLine();
                     question2 = hashPassword(question2);
                     PreparedStatement securityQuestionStmt = connection.prepareStatement(
                             "SELECT player_id FROM accounts WHERE player_name = ? AND security_question = ?");
                     securityQuestionStmt.setString(1, name);
                     securityQuestionStmt.setString(2, question2);
                     ResultSet result = securityQuestionStmt.executeQuery();

                     if (result.next()) {
                         System.out.println("Bitte geben ein neus Passwort ein:");
                         newPasswort = scScanner.nextLine();
                         PreparedStatement newPasswordStmt = connection.prepareStatement(
                                 "UPDATE accounts SET passwort = ? WHERE player_id = ?");
                         newPasswordStmt.setString(1, newPasswort);
                         newPasswordStmt.setInt(2, playerId);
                         System.out.println("Passwort gespeichert");
                         askPlayername();
                     }
                 }
                 scScanner.close();

                 if (response.equalsIgnoreCase("create")) {
                     createNewAccount();
                     return true;
                 } else {
                     System.out.println("Goodbye!");
                     return false;
                 }
             }

         } catch (NoSuchAlgorithmException e) {
             throw new RuntimeException(e);
         }

     }*/
    public static boolean askPlayername() throws IOException, SQLException {
        System.out.println("^~^ Bitte geben Sie einen Namen ein:");
        name = scScanner.nextLine();

        for (String input : inputcheck) {
            if (name.contains(input) || name.length() > 32) {
                System.out.println("Dein Name ist zu lang oder enthält unerlaubte Symbole -^^,--,~");
                return askPlayername();
            }
        }

        try (Connection connection = ConnectionHandler.getConnection()) {

            System.out.print("Gebe ein Passwort ein: ");
            String password = scScanner.nextLine();
            password = hashPassword(password);

            PreparedStatement checkCredentialsStmt = connection.prepareStatement(
                    "SELECT player_id FROM accounts WHERE player_name = ? AND passwort = ?");
            checkCredentialsStmt.setString(1, name);
            checkCredentialsStmt.setString(2, password);

            ResultSet resultSet = checkCredentialsStmt.executeQuery();
            if (resultSet.next()) {
                playerId = resultSet.getInt("player_id");
                System.out.println("Einloggen war erfolgreich");
                return true;
            } else {
                System.out.println("Irgendwas ist falsch gelaufen");
                System.out.println("Wenn du einen neuen Account erstellen möchtest, gib bitte 'create' ein.");
                System.out.println("Wenn du die Sicherheitsfrage nutzen möchtest, um ein neues Passwort zu erstellen, gib bitte 'question' ein.");
                String response = scScanner.nextLine();

                if (response.equalsIgnoreCase("question")) {
                    System.out.println("Beantworte die Sicherheitsfrage:");
                    System.out.println("Wie hieß dein erstes Plüschtier?");
                    question2 = scScanner.nextLine();
                    question2 = hashPassword(question2);

                    PreparedStatement securityQuestionStmt = connection.prepareStatement(
                            "SELECT player_id FROM accounts WHERE player_name = ? AND security_question = ?");
                    securityQuestionStmt.setString(1, name);
                    securityQuestionStmt.setString(2, question2);
                    ResultSet result = securityQuestionStmt.executeQuery();

                    if (result.next()) {
                        playerId = result.getInt("player_id");
                        System.out.println("Bitte gib ein neues Passwort ein:");
                        newPasswort = scScanner.nextLine();
                        PreparedStatement newPasswordStmt = connection.prepareStatement(
                                "UPDATE accounts SET passwort = ? WHERE player_id = ?");
                        newPasswordStmt.setString(1, hashPassword(newPasswort));
                        newPasswordStmt.setInt(2, playerId);
                        newPasswordStmt.executeUpdate();
                        System.out.println("Passwort gespeichert");
                        return askPlayername();
                    }
                }

                if (response.equalsIgnoreCase("create")) {
                    createNewAccount();
                    return true;
                } else {
                    System.out.println("Goodbye!");
                    return false;
                }
            }

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }



       /* public static void login() throws IOException {
        if (s.exists()) {
            String content = new String(Files.readAllBytes(Paths.get("name.json"))).trim();

            Password.ask();


        } else {
            data = new FileWriter("name.json");
            System.out.println("Es exesiter noch kein Arccount ");
            Password.make();

        }
    }*/

    /*public static boolean read() throws IOException {
        if (s.exists() && s.length() > 0) {
            String content = new String(Files.readAllBytes(Paths.get("name.json"))).trim();
            InputStream is = new ByteArrayInputStream(content.getBytes());
            jsonReader = Json.createReader(is);
            jsonObject = jsonReader.readObject();
            is.close();
            jsonReader.close();
            if (content.startsWith("{")) {
                if (content.contains(name)) {
                    System.out.println("Dieser Name ist schon vergebne (;-;)");
                    Login.ask();
                    return false;
                } else {
                    System.out.println("Wilkommen " + name + " viel spaß (°<°) ");
                    Password.make();
                    return true;
                }
            }

        } else {
            nameread = Json.createObjectBuilder().build();
            writer();
        }
        return true;
    }*/


    /* public static void writer() throws IOException {
         JSONObject object;

         if (s.exists() && s.length() > 0) {
             String content = new String(Files.readAllBytes(Paths.get("name.json"))).trim();
             if (content.startsWith("{")) {
                 object = new JSONObject(content);
             } else {
                 object = new JSONObject();
             }
         } else {
             object = new JSONObject();
         }

         object.put("Name: " + name, name);
         object.put("Name: " + name, Password.pass);
         object.put("Name: " + name, Password.pass + " " + Password.question + " " + Password.question2);


         fileWriter = new PrintWriter(new FileWriter(s, false));
         fileWriter.write(object.toString(2));


         fileWriter.close();
     }*/
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }


    public static void initializeDatabase() throws SQLException {
        try (Connection connection = ConnectionHandler.getConnection()) {
            Statement stmt = connection.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS accounts (" +
                    "player_id SERIAL PRIMARY KEY, " +
                    "player_name varchar(255), " +
                    "passwort varchar(255), " +
                    "security_question varchar(255)" +
                    ");";
            stmt.execute(createTableSQL);

            String alterTableSQL = "ALTER TABLE accounts ADD COLUMN IF NOT EXISTS security_question varchar(255);";
            stmt.execute(alterTableSQL);
        }
    }

    /*public static void checkCredentials() throws SQLException {
        try (Connection connection = ConnectionHandler.getConnection();
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Enter player name:");
            String playerName = scanner.nextLine();

            System.out.println("Enter password:");
            String password = scanner.nextLine();

            // Check if player_name and passwort match under the same player_id
            PreparedStatement checkCredentialsStmt = connection.prepareStatement(
                    "SELECT player_id FROM accounts WHERE player_name = ? AND passwort = ?");
            checkCredentialsStmt.setString(1, playerName);
            checkCredentialsStmt.setString(2, password);

            ResultSet resultSet = checkCredentialsStmt.executeQuery();

            if (resultSet.next()) {
                int playerId = resultSet.getInt("player_id");
                System.out.println("Login successful! Player ID: " + playerId);
            } else {
                System.out.println("Invalid username or password. Would you like to create a new account? (yes/no)");
                String response = scanner.nextLine();

                if (response.equalsIgnoreCase("yes")) {
                    createNewAccount(connection, playerName, password);
                } else {
                    System.out.println("Goodbye!");
                }
            }
        }
    }*/

    public static void createNewAccount() throws SQLException {
        try (Connection connection = ConnectionHandler.getConnection()) {
            System.out.println("^~^ Bitte geben Sie einen Namen ein:");
            String playerName = scScanner.nextLine();
            System.out.print("Geben Sie ein Passwort ein: ");
            String password = scScanner.nextLine();
            System.out.print("Beantworten Sie die Sicherheitsfrage: ");
            System.out.println("Wie hieß dein erstes Plüschtier?");
            String securityAnswer = scScanner.nextLine();

            password = hashPassword(password);
            securityAnswer = hashPassword(securityAnswer);

            PreparedStatement insertUserStmt = connection.prepareStatement(
                    "INSERT INTO accounts (player_name, passwort, security_question) VALUES (?, ?, ?)");
            insertUserStmt.setString(1, playerName);
            insertUserStmt.setString(2, password);
            insertUserStmt.setString(3, securityAnswer);

            int rowsAffected = insertUserStmt.executeUpdate();
            if (rowsAffected > 0) {
                PreparedStatement selectUserStmt = connection.prepareStatement(
                        "SELECT player_id FROM accounts WHERE player_name = ?");
                selectUserStmt.setString(1, playerName);
                ResultSet resultSet = selectUserStmt.executeQuery();
                if (resultSet.next()) {
                    int playerId = resultSet.getInt("player_id");
                    System.out.println("Der Account wurde erfolgreich erstellt! Player ID: " + playerId);
                }
            } else {
                System.out.println("Account konnte nicht erstellt werden.");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


}


