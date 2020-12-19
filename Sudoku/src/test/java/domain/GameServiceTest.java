package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
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

    SudokuService service;
    Settings settings;
    Statistics statistisc;

    @Before
    public void setUp() {
        String dbUrl = "jdbc:sqlite:statistics_test.db";

        FileGameDao gameDao = new FileGameDao("saveFile_test.csv");
        FileSettingsDao settingsDao = new FileSettingsDao("settings_test.csv");
        DbStatisticsDao statisticsDao = new DbStatisticsDao(dbUrl);

        service = new SudokuService(gameDao, settingsDao, statisticsDao);
        settings = service.getSettings();
        statistisc = service.getStatistics();
    }

    @Test
    public void ifGameHasntBeenLoadedANewGameWillBegin(){
        service.loadGame();
        int time = service.getGame().getGameTime();
        assertEquals(0, time);
    }
    
    @Test
    public void willSetDefaultSettingsIfSettingsHaventBeenSaved(){
        service.loadSettings();
        boolean check = settings.getDifficulty() == Difficulty.EASY;
        check = settings.isShowMistakes() == true;
        check = Message.getLanguage() == Language.FINNISH;
        assertTrue(check);
    }
    
    @Test
    public void willSetDifaultStatisticsIfStatisticsHaventBeenLoaded(){
        String statsText = "0 min 0 sec";
        service.loadStatistics();
        boolean check = statistisc.getAvg().equals(statsText);
        check = statistisc.getCount().equals("0");
        check = statistisc.getMin().equals(statsText);
        check = statistisc.getMax().equals(statsText);
        
        assertTrue(check);
    }

}
