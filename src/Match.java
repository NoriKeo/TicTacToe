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
                if (!KeepPlaying.keepPlaying(move.gamecharacter)) {
                    break;
                }
            }
            Position computerPosition = Computer.getRandomMove(board);
            board.getRows().get(computerPosition.getRow()).getFields().get(computerPosition.getColumn()).setGameCharacter('¤');
            Move computermove = new Move(computerPosition, '¤');
            board.print();
            if (WinCheck.isWin(board, computermove)) {
                computerWin = true;
                if (!KeepPlaying.keepPlaying(move.gamecharacter)) {
                    break;
                }
            }
            if (board.isFull()) {
                System.out.println("Game Over");
            }

            rounds++;
        } while (!board.isFull());


    }


}
