package sudoku.domain;

/**
 * GameLogic class handles things like Sudoku related things such as
 * initializing a new game, checking if a cell has been solved and checking if a
 * game has been completed.
 *
 */
public class Game {

    private final Puzzle puzzle;
    private boolean[][] masks;
    private Difficulty difficulty;
    private int gameTime;

    public Game(Difficulty difficulty) {
        this.puzzle = new Puzzle();
        this.gameTime = 0;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getGameTime() {
        return gameTime;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public Puzzle getPuzzle() {
        return this.puzzle;
    }

    public boolean getMask(int x, int y) {
        return masks[x][y];
    }

    public void setMask(int x, int y) {
        masks[x][y] = false;
    }

    public boolean getOriginalMask(int x, int y) {
        return puzzle.getMasks()[x][y];
    }

    public void setMasks(boolean[][] masks) {
        this.masks = masks;
    }

    public void initMasks() {
        boolean[][] masks = new boolean[9][9];
        for (int x = 0; x < 9; x++) {
            System.arraycopy(puzzle.getMasks()[x], 0, masks[x], 0, 9);
        }
        this.masks = masks;
    }

    public int getCell(int x, int y) {
        return puzzle.getCell(x, y);
    }

    /**
     * Check if the user has given a correct answer to a Sudoku Cell
     *
     * @param x X coordinate of the Sudoku cell that will be checked
     * @param y Y coordinate of the Sudoku cell that will be checked
     * @param value Value that the user has inputted
     * @return true or false, depending if the user has guessed correctly
     */
    public boolean checkCell(int x, int y, int value) {
        if (puzzle.getCell(x, y) == value) {
            setMask(x, y);
            return true;
        }
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
