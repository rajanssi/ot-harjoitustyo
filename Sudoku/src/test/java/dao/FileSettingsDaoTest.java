package dao;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import sudoku.dao.DbStatisticsDao;
import sudoku.dao.FileGameDao;
import sudoku.dao.FileSettingsDao;
import sudoku.domain.Difficulty;
import sudoku.domain.SudokuService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import sudoku.dao.DbStatisticsDao;
import sudoku.dao.FileGameDao;
import sudoku.dao.FileSettingsDao;
import sudoku.domain.Difficulty;
import sudoku.domain.SudokuService;
import sudoku.messages.Language;

public class FileSettingsDaoTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    SudokuService service;
    File settingsFile;
    FileSettingsDao settingsDao;
    Language language;
    Difficulty difficulty;

    @Before
    public void setUp() throws IOException {
        String dbUrl = "jdbc:sqlite:statistics_test.db";
        settingsFile = testFolder.newFile("settings_test.csv");

        FileGameDao gameDao = new FileGameDao("saveGame_test.csv");
        settingsDao = new FileSettingsDao(settingsFile.getPath());
        DbStatisticsDao statisticsDao = new DbStatisticsDao(dbUrl);

        service = new SudokuService(gameDao, settingsDao, statisticsDao);
        language = Language.FINNISH;
        difficulty = Difficulty.EASY;
    }
        
    @Test
    public void settingsFileFormatIsCorrect(){
        //service.saveSettings();
        settingsDao.saveFile(language.toString(), difficulty.toString(), "true");
        String[] data = settingsDao.loadFile();
        boolean check = data[0].equals(Language.FINNISH.toString());
        check = data[1].equals(Difficulty.EASY.toString());
        check = data[2].equals("true");
        assertTrue(check);
    }
    

    @After
    public void tearDown() {
        settingsFile.delete();
    }
}
