package sudoku.domain;

import de.sfuhrm.sudoku.Creator;
import de.sfuhrm.sudoku.GameMatrix;


/**
 * Contains the Sudoku puzzle for the game, that contains solution and masks
 * for each Sudoku cell. Will need to be updated
 * before final version.
 * 
 */
public class Puzzle {

    private static byte[][] cells;
    private static boolean[][] masks;
    private static GameMatrix game;
    

    public static GameMatrix getGame(){
        return game;
    }
    
    public static byte[][] getCells() {
        return cells;
    }

    public static boolean[][] getMasks() {
        return masks;
    }
    
    /**
     * Creates a new Sudoku Puzzle with Creator class, by setting up each 
     * Sudoku cell to a two dimensional byte array and then setting up all 
     * the masked Sudoku cells.
     */
    public static void setPuzzle() {
        game = Creator.createFull();
        cells = game.getArray();

        // Hardcoded for testing purposes!
        boolean[][] masks 
            =   {{false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {true, false, false, false, false, false, false, false, false}};
        Puzzle.masks = masks;
    }
    
    
}
