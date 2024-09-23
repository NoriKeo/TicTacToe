import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Score {

    /*default*/static int scorex;
    /*default*/static int scorey;
    static PrintWriter pWriter = null;
    static String s = "Score.txt";


    static Path path = Paths.get(s);
    static Scanner scanner;

    static {
        try {
            scanner = new Scanner(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /*static FileInputStream fis = new FileInputStream(file);
    static InputStreamReader isr = new InputStreamReader(fis);
    static BufferedReader br = new BufferedReader(isr);*/

    public static void score() {
        if (Match.playerWin) {
            scorex++;
        }
        if (Match.computerWin) {
            scorey++;
        }
        FileInputStream fis1;

        {
            try {
                fis1 = new FileInputStream(s);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        InputStreamReader isr1 = new InputStreamReader(fis1);
        BufferedReader br2 = new BufferedReader(isr1);
        String x;
        String y;
        try {
            x = br2.readLine();//System.out.println(x);
            y = br2.readLine();//System.out.println(y);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("matches " + Match.match);
        if (Match.match == 1) {
            int nummer = Integer.parseInt(x);
            scorex = scorex + nummer;
            int nummer2 = Integer.parseInt(y);
            scorey = scorey + nummer2;
        }
        try {
            pWriter = new PrintWriter(new FileWriter(s));
            pWriter.println(scorex);
            pWriter.println(scorey);
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

        try {
            if (((line = br.readLine()) != null)) {
                System.out.println(line);
            }
            if (((line2 = br.readLine()) != null)) {
                System.out.println(line2);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //process the line
        if (Match.playerWin) {

            System.out.println("Der Gewinner ist ♡ mit einem score von " + line);
            Match.playerWin = false;

        }
        if (Match.computerWin) {

            System.out.println("Der Gewinner ist ¤ mit einem score von " + line2);
            Match.computerWin = false;
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



