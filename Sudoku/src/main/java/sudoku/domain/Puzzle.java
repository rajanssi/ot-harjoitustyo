package sudoku.domain;

import de.sfuhrm.sudoku.Creator;
import de.sfuhrm.sudoku.GameMatrix;
import de.sfuhrm.sudoku.GameMatrixFactory;
import de.sfuhrm.sudoku.Riddle;

/**
 * Contains the Sudoku puzzle for the game, that contains solution and masks for
 * each Sudoku cell. Will need to be updated before final version.
 *
 */
public class Puzzle {

    private boolean[][] masks;
    private GameMatrix fullGame;
    private Riddle riddle;
    private final GameMatrixFactory gmf;

    public Puzzle() {
        gmf = new GameMatrixFactory();
    }

    public GameMatrix getGame() {
        return fullGame;
    }

    public byte getCell(int x, int y) {
        return fullGame.get(x, y);
    }

    public boolean[][] getMasks() {
        return masks;
    }

    public void setPuzzle(byte[][] gameArray, boolean[][] masks) {
        fullGame = gmf.newGameMatrix();
        fullGame.setAll(gameArray);
        /*
        boolean[][] masks = new boolean[9][9];
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                masks[x][y] = true;
            }
        }*/
        this.masks = masks;
    }

    /**
     * Creates a new Sudoku Puzzle with Creator class, by setting up each Sudoku
     * cell to a two dimensional byte array and then setting up all the masked
     * Sudoku cells.
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

    public boolean[][] testMasks() {

        boolean[][] masks
                = {{true, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false}};

        return masks;
    }

}
