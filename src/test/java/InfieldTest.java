import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class InfieldTest {

    @Mock
    Scanner scanner = new Scanner(System.in);


    @Mock
    Board board;


    ;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void inputGameTest() throws IOException {
        Infofield infofield = Infofield.getInstance();
        PrintMatchHistory printMatchHistory = mock(PrintMatchHistory.class);

        when(infofield.input()).thenReturn("game");
        infofield.info();
        //verify(ArgumentMatchers(PrintMatchHistory.matchHistory()));
        //verify(ArgumentMatchers.any().);
        //Mockito.verify(infofield, Mockito.atLeastOnce()).input();
        verify(printMatchHistory);
        //verify(infofield.input(), Mockito.times(1) );




        /*verify(PrintMatchHistory,atLeastOnce()).matchHistory();
        JsonFileRead mockJsonFileRead = mock(JsonFileRead.class);


        verify(mockJsonFileRead, times(1));
        JsonFileRead.jsonRead();
        System.out.println("fertig mit game test");*/

    }

    @Test
    public void scoreTest() {

        when(scanner.nextLine()).thenReturn("Score");

        /*Infofield.getInstance().info();
        assertTrue(infofield.scoreprint);
        System.out.println("fertig mit scoretest");*/

    }

    /*@Test
    public void infoTest() throws IOException {

        when(scanner.nextLine()).thenReturn("info");

         infofield.info();
    }*/

    @Test
    public void test() {
        assertEquals(1, 1);
    }


}