import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends JFrame {

    public Main() {


        super("canvas");


        Canvas c = new Canvas() {
            int rec = 200;

            //yoben
            int yachseoben = 50;
            //ymitte
            int yachsemitte = 300;
            //yunten
            int yachseunten = 550;
            //xachse
            int ausenlinks = 50;
            int mitte = 300;
            int ausenrechts = 550;
            boolean feldgesetzt = false;
            boolean computerblau = false;
            boolean computergruen = false;


            // paint the canvas

            @Override
            public void paint(Graphics g) {


                Scanner startscanner = new Scanner(System.in);
                Scanner scanner = new Scanner(System.in);
                Random generator = new Random();
                int[] computer = {1, 2, 3, 4, 5, 6, 7, 8, 9};


                Feld f1 = new Feld(g, rec, rec, ausenlinks, yachseoben, Color.pink, false);
                Feld f2 = new Feld(g, rec, rec, mitte, yachseoben, Color.pink, false);
                Feld f3 = new Feld(g, rec, rec, ausenrechts, yachseoben, Color.pink, false);
                Feld f4 = new Feld(g, rec, rec, ausenlinks, yachsemitte, Color.pink, false);
                Feld f5 = new Feld(g, rec, rec, mitte, yachsemitte, Color.pink, false);
                Feld f6 = new Feld(g, rec, rec, ausenrechts, yachsemitte, Color.pink, false);
                Feld f7 = new Feld(g, rec, rec, ausenlinks, yachseunten, Color.pink, false);
                Feld f8 = new Feld(g, rec, rec, mitte, yachseunten, Color.pink, false);
                Feld f9 = new Feld(g, rec, rec, ausenrechts, yachseunten, Color.pink, false);

                //String startfrage = "Blue";
                //int eingabe = 1;
                System.out.println("willst du spielen wähle eine Farbe Green/Blue");
                String startfrage = startscanner.nextLine();
                System.out.println("deine Farbe ist " + startfrage);


                if (startfrage.equals("Blue")) {
                    computergruen = true;
                    System.out.println("Die Farbe des Computers ist Green " + computergruen);


                } else if (startfrage.equals("Green")) {

                    computerblau = true;
                    System.out.println("Die Farbe des Computers ist Blau" + computerblau);

                }

                while (feldgesetzt == false) {

                    System.out.println("Aufwelches feld möchtest du setzen");
                    System.out.println("1           2         3");
                    System.out.println("4           5         6");
                    System.out.println("7           8         9");
                    int eingabe = scanner.nextInt();
                    int computerzahl = generator.nextInt(computer.length);

                    // System.out.println("Computer zahl " + computer[computerzahl]);

                    if (eingabe == 1) {

                        if (f1.belegt == false) {
                            if (startfrage.equals("Blue")) {
                                f1.setColor(Color.BLUE);
                                System.out.println("Blau gefärbt");

                            } else if (startfrage.equals("Green")) {
                                f1.setColor(Color.GREEN);
                                System.out.println("Grüne gefärbt");
                            }

                        } else {
                            System.out.println("feld belgt");
                        }
                        f1.belegt = true;
                    } else if (eingabe == 2) {
                        if (f2.belegt == false) {
                            if (startfrage.equals("Blue")) {
                                f2.setColor(Color.BLUE);
                                System.out.println("Blau gefärbt");
                            } else if (startfrage.equals("Green")) {
                                f2.setColor(Color.GREEN);
                                System.out.println("Grüne gefärbt");
                            }

                        } else {
                            System.out.println("feld belgt");
                        }
                        f2.belegt = true;

                    } else if (eingabe == 3) {
                        if (f3.belegt == false) {
                            if (startfrage.equals("Blue")) {
                                f3.setColor(Color.BLUE);
                            } else if (startfrage.equals("Green")) {
                                f3.setColor(Color.GREEN);
                            }
                        } else {
                            System.out.println("feld belgt");
                        }
                        f3.belegt = true;

                    } else if (eingabe == 4) {
                        if (f4.belegt == false) {
                            if (startfrage.equals("Blue")) {
                                f4.setColor(Color.BLUE);
                            } else if (startfrage.equals("Green")) {
                                f4.setColor(Color.GREEN);
                            }
                        } else {
                            System.out.println("feld belgt");
                        }
                        f4.belegt = true;

                    } else if (eingabe == 5) {
                        if (f5.belegt == false) {
                            if (startfrage.equals("Blue")) {
                                f5.setColor(Color.BLUE);
                            } else if (startfrage.equals("Green")) {
                                f5.setColor(Color.GREEN);
                            }
                        } else {
                            System.out.println("feld belgt");
                        }
                        f5.belegt = true;

                    } else if (eingabe == 6) {
                        if (f6.belegt == false) {
                            if (startfrage.equals("Blue")) {
                                f6.setColor(Color.BLUE);
                            } else if (startfrage.equals("Green") || computergruen == true) {
                                f6.setColor(Color.GREEN);
                            }
                        } else {
                            System.out.println("feld belgt");
                        }
                        f6.belegt = true;

                    } else if (eingabe == 7) {
                        if (f7.belegt == false) {
                            if (startfrage.equals("Blue")) {
                                f7.setColor(Color.BLUE);
                            } else if (startfrage.equals("Green")) {
                                f7.setColor(Color.GREEN);
                            }
                        } else {
                            System.out.println("feld belgt");
                        }
                        f7.belegt = true;

                    } else if (eingabe == 8) {
                        if (f8.belegt == false) {
                            if (startfrage.equals("Blue")) {
                                f8.setColor(Color.BLUE);
                            } else if (startfrage.equals("Green")) {
                                f8.setColor(Color.GREEN);
                            }
                        } else {
                            System.out.println("feld belgt");
                        }
                        f8.belegt = true;

                    } else if (eingabe == 9) {
                        if (f9.belegt == false) {
                            if (startfrage.equals("Blue")) {
                                f9.setColor(Color.BLUE);
                            } else if (startfrage.equals("Green")) {
                                f9.setColor(Color.GREEN);
                            }
                        } else {
                            System.out.println("feld belgt");
                        }
                        f9.belegt = true;

                    }

                    if (computerzahl == 1) {
                        if (f1.belegt == false) {
                            if (computergruen == false) {
                                f1.setColor(Color.BLUE);
                            } else if (computerblau == false) {
                            }
                            {
                                f1.setColor(Color.GREEN);
                            }
                        } else {
                            System.out.println("feld belgt");
                        }
                        System.out.println("computer setzt");
                        f1.belegt = true;

                    } else if (computerzahl == 2) {
                        if (f2.belegt == false) {
                            if (computergruen == false) {
                                f2.setColor(Color.BLUE);
                            } else if (computerblau == false) {
                            }
                            {
                                f2.setColor(Color.GREEN);
                            }
                        } else {
                            System.out.println("feld belgt");
                        }
                        System.out.println("computer setzt");
                        f2.belegt = true;
                    } else if (computerzahl == 3) {
                        if (f3.belegt == false) {
                            if (computergruen == false) {
                                f3.setColor(Color.BLUE);
                            } else if (computerblau == false) {
                            }
                            {
                                f3.setColor(Color.GREEN);
                            }
                        } else {
                            System.out.println("feld belgt");
                        }
                        System.out.println("computer setzt");
                        f3.belegt = true;
                    } else if (computerzahl == 4) {
                        if (f4.belegt == false) {
                            if (computergruen == false) {
                                f4.setColor(Color.BLUE);
                            } else if (computerblau == false) {
                            }
                            {
                                f4.setColor(Color.GREEN);
                            }
                        } else {
                            System.out.println("feld belgt");
                        }
                        System.out.println("computer setzt");
                        f4.belegt = true;
                    } else if (computerzahl == 5) {
                        if (f5.belegt == false) {
                            if (computergruen == false) {
                                f5.setColor(Color.BLUE);
                            } else if (computerblau == false) {
                            }
                            {
                                f5.setColor(Color.GREEN);
                            }
                        } else {
                            System.out.println("feld belgt");
                        }
                        System.out.println("computer setzt");
                        f5.belegt = true;
                    } else if (computerzahl == 6) {
                        if (f6.belegt == false) {
                            if (computergruen == false) {
                                f6.setColor(Color.BLUE);
                            } else if (computerblau == false) {
                                f6.setColor(Color.GREEN);
                            }
                        } else {
                            System.out.println("feld belgt");
                        }
                        System.out.println("computer setzt");
                        f6.belegt = true;
                    } else if (computerzahl == 7) {
                        if (f7.belegt == false) {
                            if (computergruen == false) {
                                f7.setColor(Color.BLUE);
                            } else if (computerblau == false) {
                                f7.setColor(Color.GREEN);
                            }
                        } else {
                            System.out.println("feld belgt");
                        }
                        System.out.println("computer setzt");
                        f7.belegt = true;
                    } else if (computerzahl == 8) {
                        if (f8.belegt == false) {
                            if (computergruen == false) {
                                f8.setColor(Color.BLUE);
                            } else if (computerblau == false) {
                                f8.setColor(Color.GREEN);
                            }
                        } else {
                            System.out.println("feld belgt");
                        }
                        System.out.println("computer setzt");
                        f8.belegt = true;
                    } else if (computerzahl == 9) {
                        if (f9.belegt == false) {
                            if (computergruen == false) {
                                f9.setColor(Color.BLUE);
                            } else if (computerblau == false) {
                                f9.setColor(Color.GREEN);
                            }
                        } else {
                            System.out.println("feld belgt");
                        }
                        System.out.println("computer setzt");
                        f9.belegt = true;
                    }

                    if (startfrage.equals("Blue")) {

                        if (f1.belegt = true && f2.belegt == true && f3.belegt || f4.belegt == true && f5.belegt == true && f6.belegt == true || f7.belegt == true && f8.belegt == true && f9.belegt == true) {
                            feldgesetzt = true;
                            System.out.println("Blue gewinnt");
                        }
                        if (f1.belegt == true && f5.belegt == true && f9.belegt == true || f3.belegt == true && f5.belegt == true && f7.belegt == true) {
                            feldgesetzt = true;
                            System.out.println("Blue gewinnt");
                        }
                        if (f1.belegt == true && f4.belegt == true && f7.belegt == true || f2.belegt == true && f5.belegt == true && f8.belegt == true || f3.belegt == true && f6.belegt == true && f9.belegt == true) {
                            feldgesetzt = true;
                            System.out.println("Blue gewinnt");
                        }
                        feldgesetzt = false;
                    }

                    if (startfrage.equals("Green")) {

                        if (f1.belegt = true && f2.belegt == true && f3.belegt || f4.belegt == true && f5.belegt == true && f6.belegt == true || f7.belegt == true && f8.belegt == true && f9.belegt == true) {
                            feldgesetzt = true;
                            System.out.println("Greene gewinnt");
                        }
                        if (f1.belegt == true && f5.belegt == true && f9.belegt == true || f3.belegt == true && f5.belegt == true && f7.belegt == true) {
                            feldgesetzt = true;
                            System.out.println("Greene gewinnt");
                        }
                        if (f1.belegt == true && f4.belegt == true && f7.belegt == true || f2.belegt == true && f5.belegt == true && f8.belegt == true || f3.belegt == true && f6.belegt == true && f9.belegt == true) {
                            feldgesetzt = true;
                            System.out.println("Greene gewinnt");
                        }
                        feldgesetzt = false;
                    }
                }
            }
        };

        c.setBackground(Color.black);

        add(c);
        setSize(800, 800);
        show();
    }

    public static void main(String args[]) {
        Main c = new Main();


    }
}
