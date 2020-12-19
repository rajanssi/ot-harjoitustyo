package dao;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sudoku.dao.DbStatisticsDao;
import sudoku.dao.FileGameDao;
import sudoku.dao.FileSettingsDao;
import sudoku.domain.Difficulty;
import sudoku.domain.SudokuService;
import sudoku.messages.Language;

public class FileSettingsDaoTest {

    SudokuService service;
    File settingsFile;
    FileSettingsDao settingsDao;

    @Before
    public void setUp() {
        String dbUrl = "jdbc:sqlite:statistics_test.db";
        settingsFile = new File("settings_test.csv");

        FileGameDao gameDao = new FileGameDao("saveGame_test.csv");
        settingsDao = new FileSettingsDao(settingsFile.getPath());
        DbStatisticsDao statisticsDao = new DbStatisticsDao(dbUrl);

        service = new SudokuService(gameDao, settingsDao, statisticsDao);
    }
    
    @Test
    public void settingsWillBeSavedAfterApplyingThem(){
        service.applySettings(Language.ENGLISH, Difficulty.HARD, false);
        assertTrue(service.loadSettings());
    }

    
    @Test
    public void settingsFileFormatIsCorrect(){
        service.saveSettings();
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
