import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Match {
    /*default*/ static Board board;
    /*default*/static boolean playerWin = false;
    /*default*/static boolean computerWin = false;
    /*default*/static int rounds;
    BoardhistoryArray scoreBoard = new BoardhistoryArray();
    static int match;
    static Position position;
    static int input;
    static long t1;
    static long t2;
    static Position computerPosition;
    static boolean breckBoard = false;
    static int roundprintsafe;

    public Match() {
        board = new Board();
    }

    public void start() {
        if (rounds == 0) {
            BoardhistoryArray.playerFieldsbreck = new ArrayList<>();
            BoardhistoryArray.computerFieldsbreck = new ArrayList<>();
        }
        /*f (Print.breckBoard() == null) {
            try {
                Print.initializeDatabase();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Print.breckBoard();
            board = Print.boardbreck;
            board.print();

        } else {*/

        // }
        board = new Board();
        board.print();

        do {
            t1 = System.currentTimeMillis();
            input = Player.getInstance().askInput(board);
            //input = Player.getInstance().test(board);
            position = new Position(input);
            board.getRows().get(position.getRow()).getFields().get(position.getColumn()).setGameCharacter('♡');
            board.print();
            GamePlayMove move = new GamePlayMove(position, '♡');
            if (!WinCheck.isWin(board, move)) {
                BoardhistoryArray.safeGamePlayPlayer();


            }
            if (WinCheck.isWin(board, move)) {
                BoardhistoryArray.safeGamePlayPlayer();
                try (Connection connection = ConnectionHandler.getConnection()) {
                    PreparedStatement winUpate = connection.prepareStatement(
                            "UPDATE match_history SET win = ? WHERE match_id = ? ");
                    winUpate.setBoolean(1, true);
                    winUpate.setInt(2, match);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


                playerWin = true;
                t2 = System.currentTimeMillis();
                if (!KeepPlaying.keepPlaying(board)) {
                    break;
                }

            }
            computerPosition = Computer.getComputerMovement(board);
            board.getRows().get(computerPosition.getRow()).getFields().get(computerPosition.getColumn()).setGameCharacter('¤');
            GamePlayMove computermove = new GamePlayMove(computerPosition, '¤');
            board.print();
            if (!WinCheck.isWin(board, computermove)) {
                BoardhistoryArray.safeGamePlayComputer();


            }
            if (WinCheck.isWin(board, computermove)) {
                computerWin = true;
                BoardhistoryArray.safeGamePlayComputer();
                try (Connection connection = ConnectionHandler.getConnection()) {
                    PreparedStatement winUpate = connection.prepareStatement(
                            "UPDATE match_history SET win = ? WHERE match_id = ?");
                    winUpate.setBoolean(1, true);
                    winUpate.setInt(2, match);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                t2 = System.currentTimeMillis();
                if (!KeepPlaying.keepPlaying(board)) {
                    break;
                }
            }


            if (board.isFull() && !KeepPlaying.keepPlaying(board)) {
                System.out.println("Game Over");
                break;
            }
            //System.out.println(computerWin + " computer winni");
            //BoardhistoryArray.fieldbrecks();
            if (WinCheck.isWin(board, computermove)) {
                computerWin = false;
                playerWin = false;
            }

            //System.out.println("hallllp" + BoardhistoryArray.playerFieldsbreck);
            //System.out.println("hallllp" + BoardhistoryArray.computerFieldsbreck);


            //GameLoop.writeLock();

           /* try {
                JsonWrite.jsonWriter();
                roundprintsafe++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }*/
            rounds++;
        } while (!board.isFull());


    }

    public static Board getBoard() {
        return board;
    }

    public static void setBoard(Board board) {
        Match.board = board;
    }
}
