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
    static ArrayList<Field> winsstrateg = new ArrayList<>();
    static ArrayList<Field> winStrategComputer = new ArrayList<>();
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

        for (Field field : winsstrateg) {
            if (!field.isEmpty()) {
                toRemove.add(field);
            }
        }
        if (!toRemove.isEmpty()) {
            winsstrateg.removeAll(toRemove);
        }


        if (winsstrateg.isEmpty()) {
            for (Field[] field : winsstrategs) {
                for (Field field2 : field) {
                    for (Field field3 : field) {
                        for (Field field4 : field) {
                            if (winsstrateg.size() < 3) {
                                if (field4 != field2 && field3 != field4 && field2 != field3) {
                                    if (field2.isEmpty() && field3.isEmpty() && field4.isEmpty()) {
                                        winsstrateg.add(field2);
                                        winsstrateg.add(field3);
                                        winsstrateg.add(field4);
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

    public static void winStrategsComputer(Board board) {
        ArrayList<Field> toRemove = new ArrayList<>();
        Field[][] winStrategsComputer = {{board.getRows().get(position1.getRow()).fields.get(position1.getColumn()), board.getRows().get(position2.getRow()).fields.get(position2.getColumn()), board.getRows().get(position3.getRow()).fields.get(position3.getColumn())},
                {board.getRows().get(position4.getRow()).fields.get(position4.getColumn()), board.getRows().get(position5.getRow()).fields.get(position5.getColumn()), board.getRows().get(position6.getRow()).fields.get(position6.getColumn())},
                {board.getRows().get(position7.getRow()).fields.get(position7.getColumn()), board.getRows().get(position8.getRow()).fields.get(position8.getColumn()), board.getRows().get(position9.getRow()).fields.get(position9.getColumn())},
                {board.getRows().get(position1.getRow()).fields.get(position1.getColumn()), board.getRows().get(position5.getRow()).fields.get(position5.getColumn()), board.getRows().get(position9.getRow()).fields.get(position9.getColumn())},
                {board.getRows().get(position3.getRow()).fields.get(position3.getColumn()), board.getRows().get(position5.getRow()).fields.get(position5.getColumn()), board.getRows().get(position7.getRow()).fields.get(position7.getColumn())},
                {board.getRows().get(position1.getRow()).fields.get(position1.getColumn()), board.getRows().get(position4.getRow()).fields.get(position4.getColumn()), board.getRows().get(position7.getRow()).fields.get(position7.getColumn())},
                {board.getRows().get(position2.getRow()).fields.get(position2.getColumn()), board.getRows().get(position5.getRow()).fields.get(position5.getColumn()), board.getRows().get(position8.getRow()).fields.get(position8.getColumn())},
                {board.getRows().get(position3.getRow()).fields.get(position3.getColumn()), board.getRows().get(position6.getRow()).fields.get(position6.getColumn()), board.getRows().get(position9.getRow()).fields.get(position9.getColumn())},
        };

        for (Field field : winStrategComputer) {
            if (!field.isEmpty()) {
                toRemove.add(field);
            }
        }
        if (!toRemove.isEmpty()) {
            winStrategComputer.removeAll(toRemove);
        }

        if (winStrategComputer.isEmpty()) {
            for (Field[] field : winStrategsComputer) {
                for (Field field2 : field) {
                    for (Field field3 : field) {
                        for (Field field4 : field) {
                            if (winStrategComputer.size() < 1) {
                                if (field4 != field2 && field3 != field4 && field2 != field3) {
                                    if (field2.isEmpty() && field3.isComputer() && field4.isComputer()) {
                                        winStrategComputer.add(field2);
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


    public static Position getComputerMovement(Board board) {
        Random random = new Random();
        rowStrategs(board);
        columnStrategs(board);
        diagonalStrategs(board);
        winsstrategs(board);
        winStrategsComputer(board);


        if (board.getRows().get(position5.getRow()).fields.get(position5.getColumn()).isEmpty()) {
            return position5;
        }


        if (!winStrategComputer.isEmpty()) {
            int index1 = random.nextInt(winStrategComputer.size());
            Field freeField5 = winStrategComputer.getLast();
            System.out.println("computer will gewinnen");
            return freeField5.getPosition();
        }

        if (!rowStrateg.isEmpty()) {
            int index = random.nextInt(rowStrateg.size());
            Field freeField3 = rowStrateg.getFirst();
            System.out.println("row");
            return freeField3.getPosition();

        }
        if (!diagonalStrateg.isEmpty()) {
            int index = random.nextInt(diagonalStrateg.size());
            Field freeField1 = diagonalStrateg.getFirst();
            System.out.println("diagonal");
            return freeField1.getPosition();


        }
        if (!columnStrateg.isEmpty()) {
            int index = random.nextInt(columnStrateg.size());
            Field freeField2 = columnStrateg.getFirst();
            System.out.println("column");
            return freeField2.getPosition();

        }
        if (winsstrateg.isEmpty()) {
            System.out.println("★·.·´¯`·.·★unentschieden★·.·`¯´·.·★");
            if (!KeepPlaying.keepPlaying()) {
                System.out.println("Bye Bye");
            }
        }
        int index2 = random.nextInt(winsstrateg.size());
        Field freeField = winsstrateg.getFirst();
        System.out.println("winverfolgung");
        return freeField.getPosition();

    }


}




