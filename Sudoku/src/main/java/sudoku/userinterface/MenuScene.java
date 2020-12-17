package sudoku.userinterface;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sudoku.domain.GameLogic;
import sudoku.messages.Message;

public class MenuScene implements IScene {

    private final GameLogic game;
    private final Stage stage;
    private final ArrayList<IScene> scenes;

    public MenuScene(Stage stage, GameLogic game) {
        this.game = game;
        this.stage = stage;
        scenes = new ArrayList();
    }

    @Override
    public void setScene() {
        stage.setScene(menuSceneLayout());
    }

    @Override
    public void changeScene(IScene scene) {
        scene.setScene();
    }

    @Override
    public void setScenes(IScene scene) {
        scenes.add(scene);
    }

    @Override
    public void exitGame() {
        stage.close();
    }

    private VBox menuButtons() {
        Button btnContinue = new Button(Message.CONTINUE());
        Button btnNewGame = new Button(Message.NEWGAME());
        Button btnSettings = new Button(Message.SETTINGS());
        Button btnStatistics = new Button(Message.STATISTICS());
        Button btnQuit = new Button(Message.QUIT());

        // Set actions for menu buttons
        btnContinue.setOnAction(e -> {
            changeScene(scenes.get(0)); // Get gameScene from list
        });

        btnNewGame.setOnAction(e -> {
            game.newGame();
            changeScene(scenes.get(0)); // Get gameScene from list
        });

        btnSettings.setOnAction(e -> {
            changeScene(scenes.get(1)); // Get settingsScene from list
        });

        btnQuit.setOnAction(e -> {
            exitGame();
        });

        // Setup menu buttons
        ArrayList<Button> buttons = new ArrayList();
        buttons.add(btnContinue);
        buttons.add(btnNewGame);
        buttons.add(btnSettings);
        buttons.add(btnStatistics);
        buttons.add(btnQuit);

        // Menu button styling
        buttons.stream().forEach(button -> {
            button.setPrefSize(150, 50);
        });

        // Style vertical box and add menu buttons to it
        VBox vbButtons = new VBox();
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(0, 20, 10, 20));
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.getChildren().addAll(buttons);

        return vbButtons;
    }

    private Scene menuSceneLayout() {
        VBox buttonRow = menuButtons();

        BorderPane root = new BorderPane();
        root.setCenter(buttonRow);

        return new Scene(root, 1280, 720);
    }

}
