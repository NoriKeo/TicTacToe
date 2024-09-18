public class Match {
    Board board;
    static int scorex;
    static int scorey;
    static boolean playerWin = false;
    static boolean computerWin = false;
    static int rounds;

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
            Move move = new Move(position, '♡');
            if (WinCheck.isWin(board, move)) {
                playerWin = true;
                if (!KeepPlaying.keepPlaying()) {
                    break;
                }
            }
            Position computerPosition = Computer.getComputerMovement(board);
            board.getRows().get(computerPosition.getRow()).getFields().get(computerPosition.getColumn()).setGameCharacter('¤');
            Move computermove = new Move(computerPosition, '¤');
            board.print();
            if (WinCheck.isWin(board, computermove)) {
                computerWin = true;
                if (!KeepPlaying.keepPlaying()) {
                    break;
                }
            }
            if (board.isFull()) {
                if (!KeepPlaying.keepPlaying()) {
                    System.out.println("Game Over");
                    break;
                }


            }

            rounds++;
        } while (!board.isFull());


    }


}
