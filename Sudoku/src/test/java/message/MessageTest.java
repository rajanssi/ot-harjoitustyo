package message;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sudoku.messages.Language;
import sudoku.messages.Message;

public class MessageTest {

    @Before
    public void setUp() {
        Message.setLanguage(Language.FINNISH);
    }

    @Test
    public void languageCanBeSet() {
        Message.setLanguage(Language.ENGLISH);
        assertEquals(Language.ENGLISH, Message.getLanguage());
    }

    @Test
    public void finnishMessageWorks() {
        String message = Message.congrats();
        assertEquals("Onnea!", message);
    }

    @Test
    public void englishMessageWorks() {
        Message.setLanguage(Language.ENGLISH);
        String message = Message.quit();
        assertEquals("Quit", message);
    }

}
