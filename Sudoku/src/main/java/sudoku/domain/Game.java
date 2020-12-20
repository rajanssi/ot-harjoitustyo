package sudoku.domain;

/**
 * GameLogic class handles game related logic such as checking if a cell has
 * been solved and checking if a game has been completed.
 *
 */
public class Game {

    private final Puzzle puzzle;
    private boolean[][] masks;
    private Difficulty difficulty;
    private int gameTime;

    /**
     * Constructor for a class that takes in a difficulty and initializes game
     * time to 0 by default.
     *
     * @param difficulty The difficulty of a given Sudoku puzzle, that will
     * determine how many cells are shown at the start of a game.
     */
    public Game(Difficulty difficulty) {
        this.puzzle = new Puzzle();
        this.gameTime = 0;
    }

    /**
     * Return the Difficulty value of this game.
     *
     * @return Difficulty value
     */
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    /**
     * Sets Difficulty value of this game.
     *
     * @param difficulty Difficulty value
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Return the value of how long it has taken to solve this game.
     *
     * @return integer value of current game time seconds.
     */
    public int getGameTime() {
        return gameTime;
    }

    /**
     * Sets the value of how long it has taken to solve this game.
     *
     * @param gameTime integer value of current game time in seconds
     */
    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    /**
     * Returns a Puzzle object that contains all the cells of a puzzle and
     * methods for creating new puzzles.
     *
     * @see Puzzle  
     *
     * @return Puzzle object
     */
    public Puzzle getPuzzle() {
        return this.puzzle;
    }

    /**
     * Returns whether a specified cell of a current game is masked.
     *
     * @param x X-coordinate of the specified cell
     * @param y Y-coordinate of the specified cell
     * @return true or false, depending whether the specified cell is masked or
     * not
     */
    public boolean getMask(int x, int y) {
        return masks[x][y];
    }

    /**
     * Sets the mask of a specified cell to false.
     *
     * @param x X-coordinate of the specified cell
     * @param y Y-coordinate of the specified cell
     */
    private void setMaskFalse(int x, int y) {
        masks[x][y] = false;
    }

    /**
     * Sets the mask of a specified cell to true.
     *
     * @param x X-coordinate of the specified cell
     * @param y Y-coordinate of the specified cell
     */
    private void setMaskTrue(int x, int y) {
        masks[x][y] = true;
    }

    /**
     * Returns the values for the original masks of the game for when it was
     * initially created.
     *
     * @param x X-coordinate of the specified cell
     * @param y Y-coordinate of the specified cell
     * @return true or false, depending if the cell in question was originally
     * masked or not
     */
    public boolean getOriginalMask(int x, int y) {
        return puzzle.getMasks()[x][y];
    }

    /**
     * Takes in a full boolean array and sets up all the masked cells of this
     * game.
     *
     * @param masks 81-cell 2x2 boolean array
     */
    public void setMasks(boolean[][] masks) {
        this.masks = masks;
    }

    /**
     * Initializes masks for the current game when starting a new game.
     */
    public void initMasks() {
        boolean[][] masks = new boolean[9][9];
        for (int x = 0; x < 9; x++) {
            System.arraycopy(puzzle.getMasks()[x], 0, masks[x], 0, 9);
        }
        this.masks = masks;
    }

    /**
     * Returns the value of a specified cell in the current game.
     *
     * @param x X-coordinate of the specified cell
     * @param y Y-coordinate of the specified cell
     * @return integer value of the specified cell
     */
    public int getCell(int x, int y) {
        return puzzle.getCell(x, y);
    }

    /**
     * Check if the user has given a correct answer to a Sudoku Cell. If true,
     * then set mask on that cell to false.
     *
     * @param x X coordinate of the Sudoku cell that will be checked
     * @param y Y coordinate of the Sudoku cell that will be checked
     * @param value Value that the user has inputted
     * @return true or false, depending if the user has guessed correctly
     */
    public boolean checkCell(int x, int y, int value) {
        if (puzzle.getCell(x, y) == value) {
            setMaskFalse(x, y);
            return true;
        }
        setMaskTrue(x, y);
        return false;
    }

    /**
     * Check if a game has been completed, i.e. all Sudoku cells have been
     * solved.
     *
     * @return true or false, depending if all the Sudoku cells have been
     * solved.
     */
    public boolean checkComplete() {
        for (int row = 0; row < 9; ++row) {
            for (int col = 0; col < 9; ++col) {
                if (masks[row][col] == true) {
                    return false;
                }
            }
        }
        return true;
    }
}
