package domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sudoku.domain.Difficulty;
import sudoku.domain.Game;
import sudoku.domain.Settings;
import sudoku.domain.Statistics;
import sudoku.domain.SudokuService;

public class GamePuzzleTest {

    SudokuService service;
    Settings settings;
    Statistics statistisc;
    Game game;

    @Before
    public void setUp() {
        game = new Game(Difficulty.HARD);

        boolean masks[][] = {{true, false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, true}};

        game.getPuzzle().setPuzzle(Difficulty.HARD);
        game.setMasks(masks);
    }

    @Test
    public void maskWillBeSetFalseOnACorrectGuess() {
        int value = game.getCell(0, 0);
        game.checkCell(0, 0, value);
        assertFalse(game.getMask(0, 0));
    }

    @Test
    public void maskWillNotBeSetFalseOnAnIncorrectGuess() {
        int value = game.getCell(0, 0);

        if (value > 1) {
            value -= 1;
        } else {
            value += 1;
        }

        game.checkCell(0, 0, value);

        assertTrue(game.getMask(0, 0));
    }

    @Test
    public void maskWillBeSetTrueOnAnIncorrectGuess() {
        int value = game.getCell(0, 0);
        game.checkCell(0, 0, value);

        if (value > 1) {
            value -= 1;
        } else {
            value += 1;
        }

        game.checkCell(0, 0, value);

        assertTrue(game.getMask(0, 0));
    }

    @Test
    public void completingAGameWorks() {
        int value = game.getCell(0, 0);
        game.checkCell(0, 0, value);
        value = game.getCell(8, 8);
        game.checkCell(8, 8, value);
        assertTrue(game.checkComplete());
    }
    
    @Test
    public void returnsCorrectDifficultu(){
        game.setDifficulty(Difficulty.HARD);
        Difficulty d = game.getDifficulty();
        assertEquals(Difficulty.HARD, d);
    }
    

}
