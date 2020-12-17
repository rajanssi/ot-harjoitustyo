package sudoku.domain;

import sudoku.dao.FileGameDao;
import de.sfuhrm.sudoku.QuadraticArrays;

/**
 * GameLogic class handles things like Sudoku related things such as
 * initializing a new game, checking if a cell has been solved and checking if a
 * game has been completed.
 *
 */
public class GameLogic {

    private final FileGameDao fileDao;
    private final Puzzle puzzle;
    private final Settings settings;
    private boolean[][] masks;
    private Difficulty difficulty;
    private int gameTime;

    /**
     *
     * Constructor for a GameLogic class, that takes in one parameter and
     * initializes a new Sudoku game right away.
     *
     * @param fileDao Takes in a fileDao-class object, that will allow user to
     * save an unfinished game.
     */
    public GameLogic(FileGameDao fileDao, Settings settings) {
        this.settings = settings;
        this.fileDao = fileDao;
        this.puzzle = new Puzzle();
        this.gameTime = 0;
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
        return puzzle.getCell(x, y) == value;
    }

    public int getCell(int x, int y) {
        return puzzle.getCell(x, y);
    }

    private void initMasks() {
        boolean[][] masks = new boolean[9][9];
        for (int x = 0; x < 9; x++) {
            System.arraycopy(puzzle.getMasks()[x], 0, masks[x], 0, 9);
        }
        this.masks = masks;
    }

    public void setMask(int x, int y) {
        masks[x][y] = false;
    }

    public boolean checkMask(int x, int y) {
        return masks[x][y];
    }

    public boolean getOriginalMask(int x, int y) {
        return puzzle.getMasks()[x][y];
    }

    public int getGameTime() {
        return gameTime;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
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

    /**
     * Initializes a new Sudoku game
     */
    public void initGame() {
        if (!loadGame()) {
            newGame();
        }
    }

    public void newGame() {
        puzzle.setPuzzle(settings.getDifficulty());
        initMasks();
        this.gameTime = 0;
    }

    /**
     *
     * Saves the current game to a text file. Very much incomplete!
     *
     * @return true on success, false on failure
     */
    public boolean saveGame(int time) {
        byte[][] originalMasks = new byte[9][9];
        byte[][] gameMasks = new byte[9][9];

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                gameMasks[x][y] = (byte) (checkMask(x, y) ? 1 : 0);
                originalMasks[x][y] = (byte) (getOriginalMask(x, y) ? 1 : 0);
            }
        }

        try {
            fileDao.saveFile(puzzle.getGame(), gameMasks, originalMasks, time, settings.getDifficulty());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * TODO: Make this into a boolean, that checks if a loaded game exists. If
     * not, then return false, which sets up a new Puzzle from scratch.
     *
     * @return
     */
    private boolean loadGame() {
        String[] loadData;
        byte[][] sudokuMatrix;
        boolean[][] masks = new boolean[9][9], originalMasks = new boolean[9][9];

        try {
            loadData = fileDao.loadFile();
            sudokuMatrix = QuadraticArrays.parse(loadData[0].split(";"));

            int index = 0;
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    masks[x][y] = loadData[1].charAt(index) == '1';
                    originalMasks[x][y] = loadData[2].charAt(index) == '1';
                    index++;
                }
            }

            setGameTime(Integer.parseInt(loadData[3]));

            if (loadData[4].equals("EASY")) {
                this.difficulty = Difficulty.EASY;
            } else {
                this.difficulty = Difficulty.HARD;
            }
        } catch (Exception e) {
            System.out.println("GL load error: " + e.getMessage());
            return false;
        }

        puzzle.setPuzzle(sudokuMatrix, originalMasks);
        this.masks = masks;

        return true;
    }
}
