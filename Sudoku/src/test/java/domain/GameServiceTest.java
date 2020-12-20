package domain;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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
import sudoku.domain.Difficulty;
import sudoku.domain.Settings;
import sudoku.domain.Statistics;
import sudoku.domain.SudokuService;
import sudoku.messages.Language;
import sudoku.messages.Message;

public class GameServiceTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    SudokuService service;
    File saveFile;
    File settingsFile;
    Settings settings;
    Statistics statistics;
    DbStatisticsDao statisticsDao;

    @Before
    public void setUp() throws IOException {
        String dbUrl = "jdbc:sqlite:statistics_test.db";
        saveFile = testFolder.newFile("saveGame_test.csv");
        settingsFile = testFolder.newFile("settings_test.csv");

        FileGameDao gameDao = new FileGameDao(saveFile.getPath());
        FileSettingsDao settingsDao = new FileSettingsDao(settingsFile.getPath());
        statisticsDao = new DbStatisticsDao(dbUrl);

        service = new SudokuService(gameDao, settingsDao, statisticsDao);
        settings = service.getSettings();
        statistics = service.getStatistics();
    }

    @Test
    public void ifGameHasntBeenLoadedANewGameWillBegin() {
        service.loadGame();
        int time = service.getGame().getGameTime();
        assertEquals(0, time);
    }

    @Test
    public void willSetDefaultSettingsIfSettingsHaventBeenSaved() {
        service.loadSettings();
        boolean check = settings.getDifficulty() == Difficulty.EASY;
        check = settings.isShowMistakes() == true;
        check = Message.getLanguage() == Language.FINNISH;
        assertTrue(check);
    }

    @Test
    public void willSetDifaultStatisticsIfStatisticsHaventBeenLoaded() {
        String statsText = "0 min 0 sec";
        service.loadStatistics();
        boolean check = statistics.getAvg().equals(statsText);
        check = statistics.getCount().equals("0");
        check = statistics.getMin().equals(statsText);
        check = statistics.getMax().equals(statsText);

        assertTrue(check);
    }

    @Test
    public void newGameCanBeInserted() {
        service.insertGame(20);

        assertEquals("1", service.getStatistics().getCount());
    }

    @Test
    public void saveFileCanBeSet() {
        assertEquals(true, service.saveGame(20));
    }

    @Test
    public void settingsWillReturnCorrectValues() {
        service.insertGame(20);
        service.insertGame(40);
        service.loadStatistics();
        int[] expected = {2, 30, 20, 40};
        Assert.assertArrayEquals(expected, statisticsDao.getAll());
    }

    @Test
    public void settingsWillBeSavedAfterApplyingThem() {
        service.applySettings(Language.ENGLISH, Difficulty.HARD, false);
        assertTrue(service.loadSettings());
    }

    @After
    public void tearDown() throws SQLException {
        statisticsDao.emptyTables();
        saveFile.delete();
    }
}
