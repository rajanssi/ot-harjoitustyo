package sudoku;

import sudoku.dao.FileDao;
import sudoku.userinterface.UserInterface;
import sudoku.domain.GameLogic;
import javafx.application.Application;
import javafx.stage.Stage;

public class Sudoku extends Application {

    private GameLogic game;
    private FileDao fileDao;

    @Override
    public void init() throws Exception {
        fileDao = new FileDao("sudoku.txt", "masks.txt");
        game = new GameLogic(fileDao);
        game.initGame();
    }

    @Override
    public void start(Stage stage) throws Exception {
        UserInterface userInterface = new UserInterface(game, stage);
        stage.setOnCloseRequest(e -> game.saveGame());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
