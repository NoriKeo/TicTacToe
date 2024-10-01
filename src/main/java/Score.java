import java.io.*;


public class Score {

    /*default*/static int scorex;
    /*default*/static int scorey;
    /*default*/static PrintWriter pWriter = null;
    static int draw;
    /*default*/ //static String s = "Score.txt";
    static File s = new File("Score.txt");
    BufferedWriter writer;

    {
        try {
            writer = new BufferedWriter(new FileWriter(s));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void score(Board board) {
        if (Match.playerWin) {
            scorex++;
        }
        if (Match.computerWin) {
            scorey++;
        }
        if (Computer.draw) {
            draw++;
        }


        FileInputStream fis1;


            try {
                fis1 = new FileInputStream(s);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        InputStreamReader isr1 = new InputStreamReader(fis1);
        BufferedReader br2 = new BufferedReader(isr1);
        String x;
        String y;
        String d;
        try {
            x = br2.readLine();//System.out.println(x);
            y = br2.readLine();//System.out.println(y);
            d = br2.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (Match.match == 1) {
            int nummer = Integer.parseInt(x);
            scorex = scorex + nummer;
            int nummer2 = Integer.parseInt(y);
            scorey = scorey + nummer2;
            int nummer3 = Integer.parseInt(d);
            draw = draw + nummer3;
        }

        try {
            pWriter = new PrintWriter(new FileWriter(s));
            pWriter.println(scorex);
            pWriter.println(scorey);
            pWriter.println(draw);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (pWriter != null) {
                pWriter.flush();
                pWriter.close();
            }
        }

        FileInputStream fis;

        {
            try {
                fis = new FileInputStream(s);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }


        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String line;
        String line2;
        String line3;

        try {
            line = br.readLine();
            line2 = br.readLine();
            line3 = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //process the line
        if (Match.playerWin) {

            System.out.println("Der Gewinner ist ♡ mit einem score von " + line);
            System.out.println("Der score von ¤ ist " + line2);
            Match.playerWin = false;

        }
        if (Match.computerWin) {

            System.out.println("Der Gewinner ist ¤ mit einem score von " + line2);
            System.out.println("Der scorer von ♡ ist " + line);
            Match.computerWin = false;
        }
        if (Computer.winsStrategy(board).isEmpty()) {
            System.out.println("★·.·´¯`·.·★unentschieden★·.·`¯´·.·★");
            System.out.println("es steht zum " + line3 + " unentscheiden");
            System.out.println("Der scorer von ♡ ist " + line);
            System.out.println("Der score von ¤ ist " + line2);

        }
        if (Infofield.scoreprint) {
            System.out.println("(¯´•._.•Score•._.•´¯(");
            System.out.println("Der scorer von ♡ ist " + line);
            System.out.println("Der score von ¤ ist " + line2);
            System.out.println("es gab " + line3 + " ein unentscheiden");
            System.out.println("♥ ----------------------------------- ♥");
        }

        //br.close();




        /*System.out.println("Read text file using Scanner");

        if (!s.isEmpty()){
            String line = scanner.readAllLines();
            String line2 = scanner.next();
            System.out.println(line);
            System.out.println(line2);
            }*/






        /*String line;
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //process the line
            System.out.println(line);
        }*/


    }
}



