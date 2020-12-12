package sudoku.dao;

import java.io.PrintWriter;
import java.util.Scanner;
import java.nio.file.Paths;

public class FileDao {

    private String puzzleFile;
    private String maskFile;

    public FileDao(String puzzleFile, String maskFile) {
        this.puzzleFile = puzzleFile;
        this.maskFile = maskFile;
    }

    public void saveFile(String gameText, String maskText) {
        try (PrintWriter writer = new PrintWriter(puzzleFile)) {
            writer.println(gameText);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try (PrintWriter writer = new PrintWriter(maskFile)) {
            writer.println(maskText);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String[] loadSudoku() {
        String[] sudokuFile = new String[9];
        try (Scanner fileReader = new Scanner(Paths.get(puzzleFile))) {
            int i = 0;
            while (i < 9) {
                String row = fileReader.nextLine();
                sudokuFile[i] = row;
                i++;
            }
        } catch (Exception e) {
            System.out.println("Dao error: " + e.getMessage());
        }
        return sudokuFile;
    }

    public String loadMasks() {
        String masks = "";
        try (Scanner fileReader = new Scanner(Paths.get(maskFile))) {
            int i = 0;
            while (fileReader.hasNext()) {
                masks += fileReader.next();
            }
        } catch (Exception e) {
            System.out.println("Dao error: " + e.getMessage());
            return null;
        }

        return masks;
    }

}
