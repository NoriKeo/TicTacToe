import java.io.IOException;
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
    public Match() {
        board = new Board();
    }

    public void start() {
        //GameLoop.lock();
        try {
            JsonFileRead.getInstance().jsonRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (JsonFileRead.getInstance().getComputerbreck() == null && JsonFileRead.getInstance().getPlayerbreck() == null) {
            board.print();

        } else {
            Print.breckBoard();
            board = Print.boardbreck;
        }
        if (rounds == 0) {
            BoardhistoryArray.playerFieldsbreck = new ArrayList<>();
            BoardhistoryArray.computerFieldsbreck = new ArrayList<>();
        }
        do {
            t1 = System.currentTimeMillis();
            input = Player.getInstance().askInput(board);
            position = new Position(input);
            board.getRows().get(position.getRow()).getFields().get(position.getColumn()).setGameCharacter('♡');
            board.print();
            GamePlayMove move = new GamePlayMove(position, '♡');
            if (!WinCheck.isWin(board, move)) {
                scoreBoard.safeGamePlayPlayer();

            }
            if (WinCheck.isWin(board, move)) {
                scoreBoard.safeGamePlayPlayer();
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
                scoreBoard.safeGamePlayComputer();


            }
            if (WinCheck.isWin(board, computermove)) {
                computerWin = true;
                scoreBoard.safeGamePlayComputer();
                t2 = System.currentTimeMillis();
                if (!KeepPlaying.keepPlaying(board)) {
                    break;
                }
            }


            if (board.isFull() && !KeepPlaying.keepPlaying(board)) {
                    System.out.println("Game Over");
                    break;
                }
            System.out.println(computerWin + " computer winni");
            BoardhistoryArray.fieldbrecks();
            if (WinCheck.isWin(board, computermove)) {
                computerWin = false;
                playerWin = false;
            }

            System.out.println("hallllp" + BoardhistoryArray.playerFieldsbreck);
            System.out.println("hallllp" + BoardhistoryArray.computerFieldsbreck);


            //GameLoop.lock();
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
