import java.io.*;


public class ScoreBoardWriter {
    File s = new File("Score.txt");
    int scorex;
    int scorey;
    PrintWriter pWriter;
    int draw;
    BufferedWriter writer;
    private static ScoreBoardWriter instance;


    public ScoreBoardWriter() {
    }

   /* {
        try {
            writer = new BufferedWriter(new FileWriter(s));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/


    public static ScoreBoardWriter getInstance() {
        if (instance == null) {
            instance = new ScoreBoardWriter();
        }
        return instance;
    }


    public void scoreCounter() {
        if (Match.playerWin) {
            scorex++;
        }
        if (Match.computerWin) {
            scorey++;
        }
        if (Computer.draw) {
            draw++;
        }
    }


    public void scoreWrite() {
        FileInputStream fis;
        try {
            fis = new FileInputStream(s);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }


        InputStreamReader isr1 = new InputStreamReader(fis);
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
        if (x == null && y == null && d == null) {
            x = "0";
            y = "0";
            d = "0";
        }
        if (Match.match == 0) {
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

    }






}



