import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.IOException;

import static org.mockito.Mockito.*;


public class ScoreBoardPrinterTest {

    @Mock
    private BufferedReader mockiBufferedReader;

    @Mock
    Board board;

    @InjectMocks
    private ScoreBoardPrinter scoreBoardPrinter;

    @Mock
    ScoreBoardWriter mockiScoreBoardWriter;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void winInfoPrinttestPlayer() throws IOException {
        when(mockiBufferedReader.readLine()).thenReturn("3", "1", "0");
        Match.playerWin = true;
        Match.computerWin = false;
        scoreBoardPrinter.winInfoPrint(board);
        verify(mockiBufferedReader, times(3)).readLine();

        Match.playerWin = false;
        Match.computerWin = false;
    }
}