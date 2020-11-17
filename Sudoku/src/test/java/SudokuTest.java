import org.junit.Test;
import static org.junit.Assert.*;
import sudoku.Puzzle;

public class SudokuTest {
    
    int puzzle[][] = Puzzle.getCells();
    
    @Test
    public void checkPuzzleSize(){
        assertEquals(puzzle.length, 9);
    }
    
}
