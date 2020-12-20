/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import sudoku.dao.DbStatisticsDao;
import sudoku.dao.FileGameDao;
import sudoku.dao.FileSettingsDao;
import sudoku.domain.SudokuService;

/**
 *
 * @author rajanssi
 */
public class FileGameDaoTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    SudokuService service;
    File saveFile;
    FileGameDao gameDao;

    @Before
    public void setUp() throws IOException {
        String dbUrl = "jdbc:sqlite:statistics_test.db";
        saveFile = testFolder.newFile("saveGame_test.csv");

        gameDao = new FileGameDao(saveFile.getPath());
        FileSettingsDao settingsDao = new FileSettingsDao("settings_test.csv");
        DbStatisticsDao statisticsDao = new DbStatisticsDao(dbUrl);

        service = new SudokuService(gameDao, settingsDao, statisticsDao);
    }

    @Test
    public void saveFileCantBeLoadedIfAGameHasntBeenSaved() {
        String[] emptyArr = new String[5];
        Assert.assertArrayEquals(emptyArr, gameDao.loadFile());
    }

    @Test
    public void saveFileCanBeSet() {
        assertEquals(true, service.saveGame(20));
    }

    @Test
    public void saveFileCanBeLoadedIfItExists() {
        service.saveGame(20);
        
        assertEquals(true, service.loadGame());
    }

    @Test
    public void saveFileDataIsCorrect() {
        service.saveGame(20);
        String[] data = gameDao.loadFile();
        boolean checks;
        checks = data[0].length() == 90;
        checks = data[1].length() == 81;
        checks = data[2].length() == 81;
        Assert.assertTrue(checks);
    }

    @After
    public void tearDown() {
        saveFile.delete();
    }
}
