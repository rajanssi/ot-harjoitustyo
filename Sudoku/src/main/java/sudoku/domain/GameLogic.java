package sudoku.domain;

import dao.FileDao;
import de.sfuhrm.sudoku.output.PlainTextFormatter;

/**
 * GameLogic class handles things like Sudoku related things such as 
 * initializing a new game, checking if a cell has been solved and checking if
 * a game has been completed.
 * 
 * @author rajanssi
 */
public class GameLogic {

    private boolean[][] masks;
    private PlainTextFormatter textFormatter;
    private FileDao fileDao;

    /**
     * 
     * Constructor for a GameLogic class, that takes in one parameter and 
     * initializes a new Sudoku game right away.
     * 
     * @param fileDao Takes in a fileDao-class object, that will allow user
     * to save an unfinished game.
     */
    public GameLogic(FileDao fileDao) {
        initGame();
        this.fileDao = fileDao;
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
        return Puzzle.getCells()[x][y] == value;
    }

    public void setMask(int x, int y) {
        masks[x][y] = false;
    }

    /**
     * Check if a game has been completed, i.e. all Sudoku cells have been solved.
     * @return true or false, depending if all the Sudoku cells have been solved.
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
        Puzzle.setPuzzle();
        this.masks = Puzzle.getMasks();
    }

    /**
     * 
     * Saves the current game to a text file. Very much incomplete!
     * 
     * @return true on success, false on failure
     */
    public boolean saveGame() {
        String formattedGame = textFormatter.format(Puzzle.getGame());
        try {
            fileDao.saveFile(formattedGame);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
