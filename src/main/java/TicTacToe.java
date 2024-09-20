

/*public class TicTacToe {
    HashMap<Integer, Field> fields = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    Random compute = new Random();
    int input2;


    int scorex;
    int scorey;
    boolean playagen = false;
    int playingfieldkey;
    boolean printet = false;
    int[] computerGamePlay = new int[0];
    int rounds;
    boolean playerWinn = false;
    boolean computerWinn = false;
    //Player player = new Player(1);
    ComputerGamePlay player2 = new ComputerGamePlay();


    public  void main() {

        fields.put(1, new Field(1));
        fields.put(2, new Field(2));
        fields.put(3, new Field(3));
        fields.put(4, new Field(4));
        fields.put(5, new Field(5));
        fields.put(6, new Field(6));
        fields.put(7, new Field(7));
        fields.put(8, new Field(8));
        fields.put(9, new Field(9));



        while (!win() || playagen == true) {
            printbord();
            Player player = new Player(input2);

            player.player(input2);
            player.getInput2();
            printet = false;
            printbord();
            computerGamePlay();
            printbord();
            rounds++;
            if (win()) {
                if (playerWinn) {
                    scorex++;
                    System.out.println("Der Gewinner ist ♡ mit einem score von " + scorex);

                }
                if (computerWinn) {
                    scorey++;
                    System.out.println("Der Gewinner ist ☮ mit einem score von " + scorey);

                }
                keepPlaying();


            }


        }


    }

    public void printbord() {
        System.out.println("✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮");
        System.out.println("            " + fields.get(1).getGameCharacter() + fields.get(2).getGameCharacter() + fields.get(3).getGameCharacter());
        System.out.println("            " + fields.get(4).getGameCharacter() + fields.get(5).getGameCharacter() + fields.get(6).getGameCharacter());
        System.out.println("            " + fields.get(7).getGameCharacter() + fields.get(8).getGameCharacter() + fields.get(9).getGameCharacter());
        System.out.println("✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮✮");

    }

    public boolean win() {
        List<int[]> winnerlist = List.of(new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}, new int[]{1, 4, 7}, new int[]{2, 5, 8}, new int[]{3, 6, 9}, new int[]{1, 5, 9}, new int[]{3, 5, 7});

        for (int[] i : winnerlist) {
            if (checkline(i, '♡')) {
                playerWinn = true;
                return true;

            }
            if (checkline(i, '☮')) {
                computerWinn = true;
                return true;
            }

        }


        return false;
    }


    public boolean checkline(int[] i, char gamecharacter) {

        for (int k : i) {
            if (fields.get(k).getGameCharacter() != gamecharacter) {
                return false;
            }

        }
        return true;

    }

    public boolean thisFieldIsUse(int key) {
        return fields.get(key).isUsed();


    }

    public ArrayList<Integer> computerFreeField() {
        ArrayList<Integer> freefields = new ArrayList<>();


        fields.keySet().forEach(key -> {
            if (!fields.get(key).isUsed()) {
                freefields.add(key);
            }
        });
        return freefields;
    }

    public boolean keepPlaying() {
        if (win()) {
            rounds = rounds - rounds;
            System.out.println("(っ◔◡◔)っ ♥ Möchtest du weiter spielen ♥");
            String input = sc.nextLine();
            fields.values().forEach(Field::reset);
            playerWinn = false;
            computerWinn = false;
            playagen = true;
            if (input.equals("Ja")) {
                System.out.println("˜”*°• Viel Spaß •°*”˜");
                return true;
            }
            System.out.println("╰☆☆Vielen Dank fürs Spielen☆☆╮");


        }
        return false;
    }

    public void computerGamePlay() {
        List<int[]> winnerlist = List.of(new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}, new int[]{1, 4, 7}, new int[]{2, 5, 8}, new int[]{3, 6, 9}, new int[]{1, 5, 9}, new int[]{3, 5, 7});
        List<int[]> possibleWinners = new ArrayList<>();
        List<int[]> startpossition = new ArrayList<>();
        startpossition.add(new int[]{1});
        startpossition.add(new int[]{3});
        startpossition.add(new int[]{5});
        startpossition.add(new int[]{7});
        startpossition.add(new int[]{9});
/*      int randomIndex = compute.nextInt(computerFreeField().size());
        int computerinput = computerFreeField().get(randomIndex);≈
        int randomWinnerList;
//        int[] j = new int[0];

        // get all possible winnerArrays


        for (int[] winnerFields : winnerlist) {
            boolean usable = false;

            for (int fieldID : winnerFields) {

                if (fields.get(fieldID).isUsed()) {

                    break;
                }
                usable = true;
            }
            if (usable) {
                possibleWinners.add(winnerFields);
            }
        }
        if (possibleWinners.size() > 0) {
            if (rounds > 0) {
                randomWinnerList = compute.nextInt(possibleWinners.size());
                System.out.println("Player2 .");
                System.out.println("Player2 ..");

                if (!printet) {
                    for (int a : possibleWinners.get(randomWinnerList)) {
                        //System.out.println(" zweiter array " + a);
                        if (!thisFieldIsUse(a)) {
                            fields.get(a).setUsed(true);
                            fields.get(a).setGameCharacter('☮');
                            System.out.println("Player2 ..." + a);
                            printet = true;
                            break;
                        } else {
                            computerGamePlay = winnerlist.get(randomWinnerList);

                        }
                    }
                }

            } else {
                int positionToStart = compute.nextInt(startpossition.size());
                System.out.println("Player2 .");
                System.out.println("Player2 ..");


                for (int p : startpossition.get(positionToStart)) {
                    if (!thisFieldIsUse(p)) {
                        fields.get(p).setUsed(true);
                        fields.get(p).setGameCharacter('☮');
                        System.out.println("Player2 ..." + p);
                        printet = true;
                        break;
                    }
                }
            }

            //System.out.println("j länge " + j.length);
            //to do er muss sich merken welches ziel er sich in der letzten runde gesetzt hat
            // und sich erst ein neuse suchen wenn da alle besetzt sind


        }
    }

    /*public int player() {
        int input2;
        do {
            System.out.println("♥ Gebe eine Zahl von eins bis neun ein ♥");
            String input = sc.nextLine();
            input2 = Integer.parseInt(input);
        } while (thisFieldIsUse(input2));
        fields.get(input2).setUsed(true);
        fields.get(input2).setGameCharacter('♡');


        return input2;
    }


}*/



