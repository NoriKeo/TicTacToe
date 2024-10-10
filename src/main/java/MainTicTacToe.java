public class MainTicTacToe {

    static boolean gameLoopStarted;
    public static void main(String[] args) {

        try {
            new GameLoop().start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
