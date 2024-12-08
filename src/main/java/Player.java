import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Player {
    static Scanner scScanner = new Scanner(System.in);

    private static final Set<String> INPUTS = Set.of("i", "I", "info", "INFO", "Game", "game", "g", "G", "Score", "score", "s", "S");//'♡'
    private static Player instance;

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }


    public int askInput(Board board) {
        int input2 = 0;
        String input = "";
        System.out.println("♥ Gebe eine Zahl ein oder i für Info ♥");
        System.out.println("♥ Gebe eine Zahl von eins bis neun ein ♥");


        //scScanner.next();

        input = scScanner.nextLine();
        //input = scScanner.next();


        if (INPUTS.contains(input)) {
            Infofield.getInstance().info();
        }
        if (!INPUTS.contains(input)) {
            do {
                input2 = Integer.parseInt(input);
            } while (!isvalid(input2));
        }
        if (!freefield(board, input2) && !INPUTS.contains(input)) {
            System.out.println("(¯`·.¸¸.-> °ºDas Feld ist leider schon vergebenº° >-.¸¸.·`¯(");
            //String input = sc.nextLine();
            //input2 = Integer.parseInt(input);
            input2 = askInput(board);
        }
        if (INPUTS.contains(input)) {
            input2 = askInput(board);
        }

        return input2;
    }

    /*public int test(Board board) {
        Random rand = new Random();
        ArrayList<Integer> test = new ArrayList();
        for (int i = 1; i <= 9; i++) {
            test.add(i);
        }
        System.out.println(test);
        while (true) {
            int input = rand.nextInt(test.size() + 1);
            System.out.println("ini " + input);
            if ((board.getField(new Position(input)).isEmpty()) || input != 0) {
                return input;
            }
        }
    }
*/
    public boolean isvalid(int input2) {
        return true;
    }

    public boolean freefield(Board board, int input2) {
        List<Position> freeFields = new ArrayList<>();
        for (RowFromBoard row : board.getRows()) {
            for (Field field : row.getFields()) {
                if (field.isEmpty()) {
                    freeFields.add(field.getPosition());
                }
            }
        }
        for (Position field : freeFields) {
            if (field.getIndex() == input2) {
                return true;
            }
        }
        return false;
    }
}







