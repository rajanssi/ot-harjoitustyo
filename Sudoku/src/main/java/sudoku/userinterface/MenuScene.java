package sudoku.userinterface;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sudoku.domain.SudokuService;
import sudoku.messages.Message;

public class MenuScene implements IScene {

    private final Stage stage;
    private final ArrayList<IScene> scenes;
    private final SudokuService service;

    public MenuScene(Stage stage, SudokuService gameService) {
        this.stage = stage;
        this.service = gameService;
        scenes = new ArrayList();
    }

    @Override
    public void setScene() {
        stage.setScene(sceneLayout());
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
        Button btnContinue = new Button(Message.continueGame());
        Button btnNewGame = new Button(Message.newGame());
        Button btnSettings = new Button(Message.settings());
        Button btnStatistics = new Button(Message.statistics());
        Button btnQuit = new Button(Message.quit());

        // Set actions for menu buttons
        btnContinue.setOnAction(e -> {
            changeScene(scenes.get(0)); // Get gameScene from list
        });

        btnNewGame.setOnAction(e -> {
            service.newGame();
            changeScene(scenes.get(0)); // Get gameScene from list
        });

        btnSettings.setOnAction(e -> {
            changeScene(scenes.get(1)); // Get settingsScene from list
        });

        btnStatistics.setOnAction(e -> {
            changeScene(scenes.get(2)); // Get statisticsScene from list
        });

        btnQuit.setOnAction(e -> {
            exitGame();
        });

        if (service.getGame().getGameTime() == 0) {
            btnContinue.setDisable(true);
        }

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

    @Override
    public Scene sceneLayout() {
        VBox buttonRow = menuButtons();

        BorderPane root = new BorderPane();
        root.setCenter(buttonRow);

        return new Scene(root, 1280, 720);
    }

}
