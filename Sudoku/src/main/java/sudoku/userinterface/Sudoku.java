package sudoku.userinterface;

import sudoku.dao.FileGameDao;
import sudoku.domain.GameLogic;
import javafx.application.Application;
import javafx.stage.Stage;
import sudoku.dao.FileSettingsDao;
import sudoku.domain.Settings;

public class Sudoku extends Application {

    private GameLogic game;
    private FileGameDao gameDao;
    private FileSettingsDao settingsDao;
    private Settings settings;
    
    @Override
    public void init() throws Exception {
        gameDao = new FileGameDao("savedGame.txt");
        settingsDao = new FileSettingsDao("configs.txt");
        settings = new Settings(settingsDao);
        game = new GameLogic(gameDao, settings);
        game.initGame();
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Sudoku");
        
        MenuScene menuScene = new MenuScene(stage, game);
        IScene settingsScene = new SettingsScene(stage, settings);
        IScene gameScene = new GameScene(stage, game);
        
        menuScene.setScenes(gameScene);
        menuScene.setScenes(settingsScene);
        settingsScene.setScenes(menuScene);
        gameScene.setScenes(menuScene);
        
        menuScene.setScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
