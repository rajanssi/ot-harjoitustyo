package sudoku.domain;

import de.sfuhrm.sudoku.Creator;
import de.sfuhrm.sudoku.GameMatrix;

public class Puzzle {

    private static byte[][] cells;
    private static boolean[][] masks;

    public static byte[][] getCells() {
        return cells;
    }

    public static boolean[][] getMasks() {
        return masks;
    }
    
    public static void setPuzzle() {
        GameMatrix game = Creator.createFull();
        cells = game.getArray();
        
        boolean[][] masks 
            =   {{false, false, false, false, false, true, false, false, false},
                {false, false, false, false, false, false, false, false, true},
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, true, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {false, false, true, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {false, true, false, false, false, false, true, false, false},
                {false, false, false, false, false, false, false, false, false}};
        Puzzle.masks = masks;
    }
    
    
}
