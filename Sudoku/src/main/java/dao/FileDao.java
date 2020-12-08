package dao;

import java.io.PrintWriter;
import java.util.Scanner;
import java.nio.file.Paths;

public class FileDao {

    private String file;
    
    
    public FileDao(String file) {
        this.file = file;
    }

    public void saveFile(String gameText) {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println(gameText);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
