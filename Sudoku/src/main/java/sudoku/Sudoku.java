package sudoku;

import dao.FileDao;
import sudoku.userinterface.UserInterface;
import sudoku.domain.GameLogic;
import javafx.application.Application;
import javafx.stage.Stage;

public class Sudoku extends Application {

    private GameLogic game;

    @Override
    public void init() throws Exception {
        FileDao fileDao = new FileDao("file.txt");
        game = new GameLogic(fileDao);
    }

    @Override
    public void start(Stage stage) throws Exception {
        new UserInterface(game, stage);
        stage.setOnCloseRequest(e -> game.saveGame());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
