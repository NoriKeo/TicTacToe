
/*
import java.sql.*;
import java.util.Scanner;


public class PostgreSQLExample {



        public static void main(String[] args) throws ClassNotFoundException, SQLException {
            String jdbcUrl = "jdbc:postgresql://localhost:5432/tiktaktoe";
            String username = "postgres";
            String password = "mysecretpassword";
            try {
            Class.forName("org.postgresql.Driver");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Gebe einen  Namen ein: ");
            String input = scanner.nextLine();


                try (Connection c = DriverManager.getConnection(jdbcUrl, username, password)) {
                    c.setAutoCommit(false);
                    System.out.println("Opened database successfully");

                    String query = "SELECT * FROM employees WHERE name = ? ";
                    try (PreparedStatement pstmt = c.prepareStatement(query)) {
                        pstmt.setString(1, input); // set input parameter 1
                        if (pstmt.executeQuery().next()) {
                            try (ResultSet result = pstmt.executeQuery()) {
                                while(result.next())
                                {
                                    System.out.println(result.getString("name"));
                                }
                            }
                        }else {
                            System.out.println("Nothing found");
                        }

                    }

                } catch (SQLException exp) {
                    exp.printStackTrace();
                }
            } catch (ClassNotFoundException exp) {
                exp.printStackTrace();
                System.out.println("Failed to load driver");
            }

        }
    }






*/
