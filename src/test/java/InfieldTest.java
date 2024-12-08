import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InfieldTest {
    @Mock
    UserInputOutputService userInputOutputService;

    @Mock
    Print printi;

    @Mock
    Match_History_Read jsonFileRead;

    @Mock
    ScoreBoardWriter scoreBoardWriterandPrinter;

    @InjectMocks
    Infofield infofield;

    @Test
    public void inputGameTest() {
        doReturn("info").when(userInputOutputService).getInput();
        infofield.info();
        verify(userInputOutputService, times(2)).printWelcomeMessage();
    }

    @Test
    public void infoTest() {
        doReturn("g").when(userInputOutputService).getInput();
        infofield.info();
        verify(printi).matchHistory();


    }
    
    @Test
    public void printTest() {
        doReturn("score").when(userInputOutputService).getInput();
        infofield.info();

    }
}