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
    public Match() {
        this.board = new Board();
    }

    public void start() {
        board.print();
        do {
            t1 = System.currentTimeMillis();
            input = Player.askInput(board);
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
