package sudoku.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Handles loading and saving settings.
 */
public class FileSettingsDao {

    private final String configFile;

    /**
     * Takes in the filename of the settings file.
     * 
     * @param configFile filename of the settings file.
     */
    public FileSettingsDao(String configFile) {
        this.configFile = configFile;
    }

    /**
     * Saves settings data to a file.
     * 
     * @param language Language value for the application
     * @param difficulty Difficulty value for new games
     * @param showMistakes Whether mistakes are shown to the player
     * @return true on success
     */
    public boolean saveFile(String language, String difficulty, String showMistakes) {
        try (FileWriter writer = new FileWriter(configFile)) {
            writer.write(language + ";" + difficulty + ";" + showMistakes);
            writer.close();
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    /**
     * Loads .csv file from the drive.
     * 
     * @return String array on success, null on failure
     */
    public String[] loadFile() {
        String[] settings = new String[3];

        try (Scanner reader = new Scanner(new File(configFile))) {
            while (reader.hasNextLine()) {
                settings = reader.nextLine().split(";");
            }
            reader.close();
        } catch (Exception e) {
            return null;
        }

        return settings;
    }
    
}
