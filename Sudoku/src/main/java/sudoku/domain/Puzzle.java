package sudoku.domain;

import de.sfuhrm.sudoku.Creator;
import de.sfuhrm.sudoku.GameMatrix;
import de.sfuhrm.sudoku.GameMatrixFactory;
import de.sfuhrm.sudoku.Riddle;

/**
 * Class for creating Sudoku puzzles.
 */
public class Puzzle {

    private boolean[][] masks;
    private GameMatrix fullGame;
    private Riddle riddle;
    private final GameMatrixFactory gmf;

    /**
     * Constructor, that initializes a new GameMatrixFactory object for creating
     * new puzzles.
     */
    public Puzzle() {
        gmf = new GameMatrixFactory();
    }

    /**
     * Return GameMatrix object that contains values for every cell in this puzzle.
     * 
     * @return GameMatrix object
     */
    public GameMatrix getGame() {
        return fullGame;
    }

    /**
     * Returns a value of a specified cell.
     * 
     * @param x X-coordinate of the specified cell
     * @param y Y-coordinate of the specified cell
     * @return Value of the cell specified by the parameters
     */
    public byte getCell(int x, int y) {
        return fullGame.get(x, y);
    }

    /**
     * Returns all the masked cells of this puzzle.
     * 
     * @return 81 cell 2x2 byte array
     */
    public boolean[][] getMasks() {
        return masks;
    }

    /**
     * Sets up a Sudoku puzzle based on an existing game.
     * 
     * @param gameArray 81-cell Sudoku array that contains values for all the cells
     * @param masks Contains information whether a cell is shown or not
     */
    public void setPuzzle(byte[][] gameArray, boolean[][] masks) {
        fullGame = gmf.newGameMatrix();
        fullGame.setAll(gameArray);

        this.masks = masks;
    }

    /**
     * Creates a new Sudoku puzzle, and sets up a number of masked cells based
     * on the difficulty set by the user in settings.
     * 
     * @param difficulty Takes in a difficulty that will determine how many cells
     * are shown.
     */
    public void setPuzzle(Difficulty difficulty) {
        fullGame = Creator.createFull();
        riddle = Creator.createRiddle(fullGame);

        boolean[][] masks = new boolean[9][9];
        if (difficulty.equals(Difficulty.EASY)) {
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    masks[x][y] = !riddle.getWritable(x, y);
                }
            }
        } else {
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    masks[x][y] = riddle.getWritable(x, y);
                }
            }
        }
        this.masks = masks;
    }
}
