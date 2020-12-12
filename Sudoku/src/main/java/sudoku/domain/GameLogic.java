package sudoku.domain;

import sudoku.dao.FileDao;
import de.sfuhrm.sudoku.QuadraticArrays;
import de.sfuhrm.sudoku.output.PlainTextFormatter;
import java.util.Arrays;

/**
 * GameLogic class handles things like Sudoku related things such as
 * initializing a new game, checking if a cell has been solved and checking if a
 * game has been completed.
 *
 * @author rajanssi
 */
public class GameLogic {

    private boolean[][] masks;
    private PlainTextFormatter textFormatter;
    private FileDao fileDao;
    private Puzzle puzzle;

    /**
     *
     * Constructor for a GameLogic class, that takes in one parameter and
     * initializes a new Sudoku game right away.
     *
     * @param fileDao Takes in a fileDao-class object, that will allow user to
     * save an unfinished game.
     */
    public GameLogic(FileDao fileDao) {
        this.fileDao = fileDao;
        this.puzzle = new Puzzle();
        textFormatter = new PlainTextFormatter();
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

    public void setMask(int x, int y) {
        masks[x][y] = false;
    }

    public boolean checkMask(int x, int y) {
        return masks[x][y];
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
            puzzle.setPuzzle();
            this.masks = puzzle.getMasks();
        }
    }

    public void newGame() {
        puzzle.setPuzzle();
        this.masks = puzzle.getMasks();
    }

    /**
     *
     * Saves the current game to a text file. Very much incomplete!
     *
     * @return true on success, false on failure
     */
    public boolean saveGame() {
        String formattedGame = textFormatter.format(puzzle.getGame());

        String masksToString = "";

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (checkMask(x, y)) {
                    masksToString += "1";
                } else {
                    masksToString += "0";
                }
            }
        }

        System.out.println(masksToString);

        try {
            fileDao.saveFile(formattedGame, masksToString);
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
    public boolean loadGame() {
        byte[][] sudokuData;
        try {
            String[] loadFile = fileDao.loadSudoku();
            System.out.println(Arrays.toString(loadFile));
            sudokuData = QuadraticArrays.parse(loadFile);
        } catch (Exception e) {
            System.out.println("GL error: " + Arrays.toString(e.getStackTrace()));
            return false;
        }

        System.out.println("loadMasks()");
        
        puzzle.setPuzzle(sudokuData);
        this.masks = puzzle.getMasks();

        byte[][] maskData;
        try {
            String maskText = fileDao.loadMasks();

            int index = 0;
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    if (maskText.charAt(index) == '0') {
                        setMask(x, y);
                    }
                    index++;
                }
            }
        } catch (Exception e) {
            System.out.println("Mask error: " + e.getMessage());
            return false;
        }


        return true;
    }
}
