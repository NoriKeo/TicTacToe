import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {

    /*default*/static Scanner scScanner = new Scanner(System.in);


    //'♡'
    public static int askInput(Board board) {
        int input2;

        do {
            System.out.println("♥ Gebe eine Zahl von eins bis neun ein ♥");
            String input = scScanner.nextLine();
            input2 = Integer.parseInt(input);
        } while (!isvalid(input2));


        if (!freefield(board, input2)) {
            System.out.println("(¯`·.¸¸.-> °ºDas Feld ist leider schon vergebenº° >-.¸¸.·`¯(");
            //String input = sc.nextLine();
            //input2 = Integer.parseInt(input);
            input2 = askInput(board);

        }

        return input2;

    }

    public static boolean isvalid(int input2) {
        return true;
    }

    public static boolean freefield(Board board, int input2) {

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







