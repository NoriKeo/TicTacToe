import java.util.ArrayList;
import java.util.Random;


public class Computer {


    public Computer() {
    }


    static Position position1 = new Position(1);
    static Position position2 = new Position(2);
    static Position position3 = new Position(3);
    static Position position4 = new Position(4);
    static Position position5 = new Position(5);
    static Position position6 = new Position(6);
    static Position position7 = new Position(7);
    static Position position8 = new Position(8);
    static Position position9 = new Position(9);
    static ArrayList<Field> columnStrateg = new ArrayList<>();
    static ArrayList<Field> rowStrateg = new ArrayList<>();
    static ArrayList<Field> diagonalStrateg = new ArrayList<>();
    static ArrayList<Field> winBlock = new ArrayList<>();
    static ArrayList<Field> startMove = new ArrayList<>();
    ;


    public static void rowStrategs(Board board) {
        ArrayList<Field> toRemove = new ArrayList<>();
        Field[][] rowWinStrategs = {{board.getRows().get(position1.getRow()).fields.get(position1.getColumn()), board.getRows().get(position2.getRow()).fields.get(position2.getColumn()), board.getRows().get(position3.getRow()).fields.get(position3.getColumn())},
                {board.getRows().get(position4.getRow()).fields.get(position4.getColumn()), board.getRows().get(position5.getRow()).fields.get(position5.getColumn()), board.getRows().get(position6.getRow()).fields.get(position6.getColumn())},
                {board.getRows().get(position7.getRow()).fields.get(position7.getColumn()), board.getRows().get(position8.getRow()).fields.get(position8.getColumn()), board.getRows().get(position9.getRow()).fields.get(position9.getColumn())},};

        for (Field field : rowStrateg) {
            if (!field.isEmpty()) {
                toRemove.add(field);
            }
        }
        if (!toRemove.isEmpty()) {
            rowStrateg.removeAll(toRemove);
        }
        if (rowStrateg.isEmpty()) {
            for (Field[] field : rowWinStrategs) {
                for (Field field2 : field) {
                    for (Field field3 : field) {
                        for (Field field4 : field) {
                            if (field2 != field3) {
                                if (field4.isEmpty() && field2.isPlayer() && field3.isPlayer()) {
                                    if (rowStrateg.size() < 1) {
                                        rowStrateg.add(field4);
                                        break;
                                    }
                                }
                            }


                        }

                    }

                }

            }
        }

    }

    public static void columnStrategs(Board board) {
        ArrayList<Field> toRemove = new ArrayList<>();
        Field[][] columnWinStrategs = {{board.getRows().get(position1.getRow()).fields.get(position1.getColumn()), board.getRows().get(position4.getRow()).fields.get(position4.getColumn()), board.getRows().get(position7.getRow()).fields.get(position7.getColumn())},
                {board.getRows().get(position2.getRow()).fields.get(position2.getColumn()), board.getRows().get(position5.getRow()).fields.get(position5.getColumn()), board.getRows().get(position8.getRow()).fields.get(position8.getColumn())},
                {board.getRows().get(position3.getRow()).fields.get(position3.getColumn()), board.getRows().get(position6.getRow()).fields.get(position6.getColumn()), board.getRows().get(position9.getRow()).fields.get(position9.getColumn())},};

        for (Field field : columnStrateg) {
            if (!field.isEmpty()) {
                toRemove.add(field);
            }
        }
        if (!toRemove.isEmpty()) {
            columnStrateg.removeAll(toRemove);
        }
        if (columnStrateg.isEmpty()) {
            for (Field[] field : columnWinStrategs) {
                for (Field field2 : field) {
                    for (Field field3 : field) {
                        for (Field field4 : field) {
                            if (field2 != field3) {
                                if (field4.isEmpty() && field2.isPlayer() && field3.isPlayer()) {
                                    if (columnStrateg.size() < 3) {
                                        columnStrateg.add(field4);
                                        break;
                                    }
                                }
                            }


                        }

                    }

                }

            }
        }


    }

    public static void diagonalStrategs(Board board) {
        ArrayList<Field> toRemove = new ArrayList<>();
        Field[][] diagonalWinStrategs = {{board.getRows().get(position1.getRow()).fields.get(position1.getColumn()), board.getRows().get(position5.getRow()).fields.get(position5.getColumn()), board.getRows().get(position9.getRow()).fields.get(position9.getColumn())},
                {board.getRows().get(position3.getRow()).fields.get(position3.getColumn()), board.getRows().get(position5.getRow()).fields.get(position5.getColumn()), board.getRows().get(position7.getRow()).fields.get(position7.getColumn())},
        };

        for (Field field : diagonalStrateg) {
            if (!field.isEmpty()) {
                toRemove.add(field);
            }
        }
        if (!toRemove.isEmpty()) {
            diagonalStrateg.removeAll(toRemove);
        }

        if (diagonalStrateg.isEmpty()) {
            for (Field[] field : diagonalWinStrategs) {
                for (Field field2 : field) {
                    for (Field field3 : field) {
                        for (Field field4 : field) {
                            if (field3 != field2) {
                                if (field4.isEmpty() && field2.isPlayer() && field3.isPlayer()) {
                                    if (diagonalStrateg.size() < 3) {
                                        diagonalStrateg.add(field4);
                                        break;
                                    }
                                }
                            }
                        }

                    }

                }

            }
        }

    }

    public static void winsstrategs(Board board) {
        ArrayList<Field> toRemove = new ArrayList<>();
        Field[][] winsstrategs = {{board.getRows().get(position1.getRow()).fields.get(position1.getColumn()), board.getRows().get(position2.getRow()).fields.get(position2.getColumn()), board.getRows().get(position3.getRow()).fields.get(position3.getColumn())},
                {board.getRows().get(position4.getRow()).fields.get(position4.getColumn()), board.getRows().get(position5.getRow()).fields.get(position5.getColumn()), board.getRows().get(position6.getRow()).fields.get(position6.getColumn())},
                {board.getRows().get(position7.getRow()).fields.get(position7.getColumn()), board.getRows().get(position8.getRow()).fields.get(position8.getColumn()), board.getRows().get(position9.getRow()).fields.get(position9.getColumn())},
                {board.getRows().get(position1.getRow()).fields.get(position1.getColumn()), board.getRows().get(position5.getRow()).fields.get(position5.getColumn()), board.getRows().get(position9.getRow()).fields.get(position9.getColumn())},
                {board.getRows().get(position3.getRow()).fields.get(position3.getColumn()), board.getRows().get(position5.getRow()).fields.get(position5.getColumn()), board.getRows().get(position7.getRow()).fields.get(position7.getColumn())},
                {board.getRows().get(position1.getRow()).fields.get(position1.getColumn()), board.getRows().get(position4.getRow()).fields.get(position4.getColumn()), board.getRows().get(position7.getRow()).fields.get(position7.getColumn())},
                {board.getRows().get(position2.getRow()).fields.get(position2.getColumn()), board.getRows().get(position5.getRow()).fields.get(position5.getColumn()), board.getRows().get(position8.getRow()).fields.get(position8.getColumn())},
                {board.getRows().get(position3.getRow()).fields.get(position3.getColumn()), board.getRows().get(position6.getRow()).fields.get(position6.getColumn()), board.getRows().get(position9.getRow()).fields.get(position9.getColumn())},


        };

        for (Field field : winBlock) {
            if (!field.isEmpty()) {
                toRemove.add(field);
            }
        }
        if (!toRemove.isEmpty()) {
            winBlock.removeAll(toRemove);
        }


        if (winBlock.isEmpty()) {
            for (Field[] field : winsstrategs) {
                for (Field field2 : field) {

                    if (winBlock.size() < 3) {
                        if (field2.isEmpty()) {
                            winBlock.add(field2);
                            break;
                        }

                    }

                }
            }
        }


    }

    public static void startMoves(Board board) {
        Field[][] startMoves = {{board.getRows().get(position1.getRow()).fields.get(position1.getColumn())},
                {board.getRows().get(position3.getRow()).fields.get(position3.getColumn())},
                {board.getRows().get(position5.getRow()).fields.get(position5.getColumn())},
                {board.getRows().get(position7.getRow()).fields.get(position7.getColumn())},
                {board.getRows().get(position9.getRow()).fields.get(position9.getColumn())},

        };

        for (Field[] field : startMoves) {
            for (Field field2 : field) {
                if (field2.isEmpty()) {
                    startMove.add(field2);
                }
            }
        }

    }

    public static void playMoveCheck(Board board) {


    }

    public static Position getRandomMove(Board board) {
        Random random = new Random();


        /*ArrayList<Field> freeFields = new ArrayList<>();

        for (Row row : board.getRows()) {
            for (Field field : row.getFields()) {
                if (field.isEmpty()) {
                    freeFields.add(field);
                }
            }
        }*/
        if (Match.rounds == 0) {
            startMoves(board);
        }
        winsstrategs(board);
        columnStrategs(board);
        rowStrategs(board);
        diagonalStrategs(board);

        int index2 = random.nextInt(winBlock.size());
        Field freeField = winBlock.get(index2);
        //Field freeField = winBlock.getFirst();



       /* if (Match.rounds == 0) {
            if (board.getRows().get(position5.getRow()).fields.get(position5.getColumn()).isEmpty()) {
                board.getRows().get(position5.getRow()).fields.get(position5.getColumn()).setGameCharacter('¤');
            }*/
        if (Match.rounds == 0) {
            if (!startMove.isEmpty()) {
                int index1 = random.nextInt(startMove.size());
                Field freeFieldstart = startMove.getLast();
                return freeFieldstart.getPosition();
            }
        }

        if (!rowStrateg.isEmpty()) {


            //
            //
            if (board.getRows().get(position2.getRow()).fields.get(position2.getColumn()).isPlayer()
                    && board.getRows().get(position1.getRow()).fields.get(position1.getColumn()).isPlayer()
                    || board.getRows().get(position8.getRow()).fields.get(position8.getColumn()).isPlayer()
                    && board.getRows().get(position7.getRow()).fields.get(position7.getColumn()).isPlayer()
                    || board.getRows().get(position5.getRow()).fields.get(position5.getColumn()).isPlayer()
                    && board.getRows().get(position4.getRow()).fields.get(position4.getColumn()).isPlayer()
                    || board.getRows().get(position2.getRow()).fields.get(position2.getColumn()).isPlayer()
                    && board.getRows().get(position3.getRow()).fields.get(position3.getColumn()).isPlayer()
                    || board.getRows().get(position8.getRow()).fields.get(position8.getColumn()).isPlayer()
                    && board.getRows().get(position9.getRow()).fields.get(position9.getColumn()).isPlayer()
                    || board.getRows().get(position5.getRow()).fields.get(position5.getColumn()).isPlayer()
                    && board.getRows().get(position6.getRow()).fields.get(position6.getColumn()).isPlayer()) {
                int index = random.nextInt(rowStrateg.size());
                Field freeField3 = rowStrateg.get(index);
                System.out.println("row");
                return freeField3.getPosition();

            }
        }
        if (!columnStrateg.isEmpty()) {


            //columnStrateg.stream().filter(position5 == position7)
            //System.out.println("column");

            //return freeField2.getPosition();
            if (board.getRows().get(position4.getRow()).fields.get(position4.getColumn()).isPlayer()
                    && board.getRows().get(position1.getRow()).fields.get(position1.getColumn()).isPlayer()
                    || board.getRows().get(position5.getRow()).fields.get(position5.getColumn()).isPlayer()
                    && board.getRows().get(position2.getRow()).fields.get(position2.getColumn()).isPlayer()
                    || board.getRows().get(position6.getRow()).fields.get(position6.getColumn()).isPlayer()
                    && board.getRows().get(position3.getRow()).fields.get(position3.getColumn()).isPlayer()
                    || board.getRows().get(position4.getRow()).fields.get(position4.getColumn()).isPlayer()
                    && board.getRows().get(position7.getRow()).fields.get(position7.getColumn()).isPlayer()
                    || board.getRows().get(position5.getRow()).fields.get(position5.getColumn()).isPlayer()
                    && board.getRows().get(position8.getRow()).fields.get(position8.getColumn()).isPlayer()
                    || board.getRows().get(position6.getRow()).fields.get(position6.getColumn()).isPlayer()
                    && board.getRows().get(position9.getRow()).fields.get(position9.getColumn()).isPlayer()) {
                System.out.println("column");
                //int index = random.nextInt(columnStrateg.size());
                Field freeField2 = columnStrateg.getFirst();
                //columnStrateg.stream().filter(position5 == position7)
                return freeField2.getPosition();

            }
        }
        if (!diagonalStrateg.isEmpty()) {
            //System.out.println("diagonal");

            if (board.getRows().get(position5.getRow()).fields.get(position5.getColumn()).isPlayer()
                    && board.getRows().get(position1.getRow()).fields.get(position1.getColumn()).isPlayer()
                    || board.getRows().get(position5.getRow()).fields.get(position5.getColumn()).isPlayer()
                    && board.getRows().get(position3.getRow()).fields.get(position3.getColumn()).isPlayer()
                    || board.getRows().get(position5.getRow()).fields.get(position5.getColumn()).isPlayer()
                    && board.getRows().get(position7.getRow()).fields.get(position7.getColumn()).isPlayer()
                    || board.getRows().get(position5.getRow()).fields.get(position5.getColumn()).isPlayer()
                    && board.getRows().get(position9.getRow()).fields.get(position9.getColumn()).isPlayer()) {

                int index = random.nextInt(diagonalStrateg.size());
                Field freeField1 = diagonalStrateg.getFirst();
                System.out.println("diagonal");
                return freeField1.getPosition();

            }
        }
        System.out.println("winblock");
        return freeField.getPosition();


        //return freeField.getPosition();
    }


}




