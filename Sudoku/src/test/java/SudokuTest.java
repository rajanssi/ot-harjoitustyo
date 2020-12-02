import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import sudoku.domain.GameLogic;
import sudoku.domain.Puzzle;

public class SudokuTest {

    GameLogic gl;
    byte[][] cells;

    @Before
    public void setUp() {
        gl = new GameLogic();
        cells = Puzzle.getCells();
        gl.setMask(0, 5);
    }

    @Test
    public void checkmaskFalse() {
        boolean check = Puzzle.getMasks()[5][0];
        assertEquals(check, false);
    }

    @Test
    public void checkMaskFalse2() {
        boolean check = Puzzle.getMasks()[0][5];
        assertEquals(check, false);
    }

    @Test
    public void checkMaskTrue() {
        boolean check = Puzzle.getMasks()[1][8];
        assertEquals(check, true);
    }
    
    @Test
    public void checkCompleteFalse(){
        boolean check = gl.checkComplete();
        assertEquals(check, false);
    }
    
    @Test 
    public void checkAfterInit(){
        gl.initGame();
        boolean check = gl.checkComplete();
        assertEquals(check, false);
    }
    
}
