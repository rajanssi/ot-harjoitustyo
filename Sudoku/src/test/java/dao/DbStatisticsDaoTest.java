package dao;

import java.io.IOException;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import sudoku.dao.DbStatisticsDao;
import sudoku.dao.FileGameDao;
import sudoku.dao.FileSettingsDao;
import sudoku.domain.SudokuService;

public class DbStatisticsDaoTest {

    SudokuService service;
    DbStatisticsDao statisticsDao;

    @Before
    public void setUp() throws IOException {

        String dbUrl = "jdbc:sqlite:statistics_test.db";
        FileGameDao gameDao = new FileGameDao("saveGame_test.csv");
        FileSettingsDao settingsDao = new FileSettingsDao("settings_test.csv");
        statisticsDao = new DbStatisticsDao(dbUrl);

        service = new SudokuService(gameDao, settingsDao, statisticsDao);
    }
    
    @Test
    public void daoWillReturnNullIfItIsEmpty(){
        statisticsDao.emptyTables();
        Assert.assertArrayEquals(null, statisticsDao.getAll());
    }
    
    @Test
    public void newGameCanBeInserted() {
        service.insertGame(20);
        
        assertEquals("1", service.getStatistics().getCount());
    }
    
    @Test
    public void daoWillReturnCorrectValues(){
        service.insertGame(20);
        service.insertGame(40);
        service.loadStatistics();
        int[] expected = {2,30,20,40};
        Assert.assertArrayEquals(expected, statisticsDao.getAll());
    }

    @After
    public void tearDown() throws SQLException {
        statisticsDao.emptyTables();
    }
}
