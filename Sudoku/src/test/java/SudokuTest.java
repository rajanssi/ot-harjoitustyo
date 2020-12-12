import sudoku.dao.FileDao;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import sudoku.domain.GameLogic;

public class SudokuTest {

    GameLogic gl;
    byte[][] cells;

    @Before
    public void setUp() {
        gl = new GameLogic(new FileDao(""));
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
        boolean check = Puzzle.getMasks()[8][0];
        assertEquals(check, true);
    }
    
    @Test
    public void checkCellValueTrue(){
        int value = cells[1][1];
        boolean check = gl.checkCell(1, 1, value);
        assertEquals(check, true);
    }
    
    @Test
    public void checkCellValueFalse(){
        int value = -1;
        boolean check = gl.checkCell(1, 1, value);
        assertEquals(check, false);
        
    }
    
    @Test
    public void checkCompleteFalse(){
        boolean check = gl.checkComplete();
        assertEquals(check, false);
    }
    
    @Test
    public void checkCompleteTrue(){
        gl.setMask(8, 0);
        boolean check = gl.checkComplete();
        assertEquals(check, true);
    }
    
    @Test 
    public void checkAfterInit(){
        gl.initGame();
        boolean check = gl.checkComplete();
        assertEquals(check, false);
    }
    
    @Test
    public void checkSudokuXSize(){
        int puzzleSize = Puzzle.getCells().length;
        assertEquals(puzzleSize, 9);
    }
    
    @Test
    public void checkSudokuYSize(){
        int puzzleSize = Puzzle.getCells()[0].length;
        assertEquals(puzzleSize, 9);
    }
    
}
