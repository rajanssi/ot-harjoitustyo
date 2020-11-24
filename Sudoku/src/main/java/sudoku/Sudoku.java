package sudoku;

import sudoku.userinterface.UserInterface;
import sudoku.domain.GameLogic;
import javafx.application.Application;
import javafx.stage.Stage;

public class Sudoku extends Application {

    private GameLogic game;
    
    @Override
    public void init() throws Exception {
        game = new GameLogic();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        UserInterface ui = new UserInterface(game, stage);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
