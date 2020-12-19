package sudoku.userinterface;

import javafx.application.Application;
import javafx.stage.Stage;
import sudoku.dao.FileGameDao;
import sudoku.dao.FileSettingsDao;
import sudoku.dao.DbStatisticsDao;
import sudoku.domain.SudokuService;

public class Sudoku extends Application {

    private SudokuService service;

    @Override
    public void init() throws Exception {
        FileGameDao gameDao = new FileGameDao("savedGame.csv");
        FileSettingsDao settingsDao = new FileSettingsDao("configs.csv");
        DbStatisticsDao statisticsDao = new DbStatisticsDao("jdbc:sqlite:statistics.db");

        service = new SudokuService(gameDao, settingsDao, statisticsDao);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Sudoku");

        IScene menuScene = new MenuScene(stage, service);
        IScene settingsScene = new SettingsScene(stage, service);
        IScene gameScene = new GameScene(stage, service);
        IScene statisticsScene = new StatisticsScene(stage, service);

        menuScene.setScenes(gameScene);
        menuScene.setScenes(settingsScene);
        menuScene.setScenes(statisticsScene);

        settingsScene.setScenes(menuScene);
        gameScene.setScenes(menuScene);
        statisticsScene.setScenes(menuScene);

        menuScene.setScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
