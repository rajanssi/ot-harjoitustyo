package sudoku.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileSettingsDao {

    private final String configFile;

    public FileSettingsDao(String configFile) {
        this.configFile = configFile;
    }

    public void saveFile(String language, String difficulty, String showMistakes) {
        try (FileWriter writer = new FileWriter(configFile)) {
            writer.write(language + ";" + difficulty + ";" + showMistakes);
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String[] loadFile() {
        String[] settings = new String[3];

        try (Scanner reader = new Scanner(new File(configFile))) {
            while(reader.hasNextLine()){
                settings = reader.nextLine().split(";");
            }
            reader.close();
        } catch (Exception e) {
            return null;
        }

        return settings;
    }

}
