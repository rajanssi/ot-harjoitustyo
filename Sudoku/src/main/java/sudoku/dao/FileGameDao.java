package sudoku.dao;

import de.sfuhrm.sudoku.GameMatrix;
import de.sfuhrm.sudoku.output.PlainTextFormatter;
import sudoku.domain.Difficulty;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;

/**
 * Handles loading and saving game files.
 */
public class FileGameDao {

    private final String sudokuFile;
    private final PlainTextFormatter textFormatter;

    /**
     * Takes in the filename of the savefile.
     * 
     * @param sudokuFile filename of the savefile
     */
    public FileGameDao(String sudokuFile) {
        this.sudokuFile = sudokuFile;
        this.textFormatter = new PlainTextFormatter();
    }
    /**
     * Saves a new .csv file to the user's drive. 
     * 
     * @param game GameMatrix object that contains all the cell values
     * @param masks masked cells of the current game
     * @param originalMasks masked cells of the original game
     * @param time current game time.
     * @param difficulty difficulty of the current game.
     * @return true on success
     */
    public boolean saveFile(GameMatrix game, byte[][] masks, byte[][] originalMasks, int time, Difficulty difficulty) {
        textFormatter.setLineSeparator(";");
        String gameString = textFormatter.format(game);
        String maskString = new String();
        String originalMaskString = new String();
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                maskString += masks[x][y];
                originalMaskString += originalMasks[x][y];
            }
        }
        try (FileWriter writer = new FileWriter(sudokuFile)) {
            writer.write(gameString + "\n");
            writer.write(maskString + "\n");
            writer.write(originalMaskString + "\n");
            writer.write(time + "\n");
            writer.write(difficulty.toString());

            writer.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    /**
     * Loads a .csv file that contains all the relevant data of a saved game.
     * 
     * @return String array that contains the game data in rows.
     */
    public String[] loadFile() {
        String[] data = new String[5];
        try (Scanner reader = new Scanner(new File(sudokuFile))) {
            int i = 0;
            while (reader.hasNextLine()) {
                data[i] = reader.nextLine();
                i++;
            }
            reader.close();
        } catch (Exception e) {
            return null;
        }
        return data;
    }
}
