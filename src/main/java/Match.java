public class Match {
    /*default*/ Board board;
    /*default*/static int scorex;
    /*default*/static int scorey;
    /*default*/static boolean playerWin = false;
    /*default*/static boolean computerWin = false;
    /*default*/static int rounds;

    public Match() {
        this.board = new Board();
    }

    public void start() {
        board.print();
        do {
            int input = Player.askInput(board);
            Position position = new Position(input);
            board.getRows().get(position.getRow()).getFields().get(position.getColumn()).setGameCharacter('♡');
            board.print();
            GamePlayMove move = new GamePlayMove(position, '♡');
            if (WinCheck.isWin(board, move)) {
                playerWin = true;
                if (!KeepPlaying.keepPlaying()) {
                    break;
                }
            }
            Position computerPosition = Computer.getComputerMovement(board);
            board.getRows().get(computerPosition.getRow()).getFields().get(computerPosition.getColumn()).setGameCharacter('¤');
            GamePlayMove computermove = new GamePlayMove(computerPosition, '¤');
            board.print();
            if (WinCheck.isWin(board, computermove)) {
                computerWin = true;
                if (!KeepPlaying.keepPlaying()) {
                    break;
                }
            }


            if (board.isFull() && !KeepPlaying.keepPlaying()) {
                    System.out.println("Game Over");
                    break;
                }


            rounds++;
        } while (!board.isFull());


    }


}
