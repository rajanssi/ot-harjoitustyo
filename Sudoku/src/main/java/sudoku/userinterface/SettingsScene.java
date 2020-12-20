package sudoku.userinterface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.controlsfx.control.ToggleSwitch;
import sudoku.domain.Difficulty;
import sudoku.domain.Settings;
import sudoku.domain.SudokuService;
import sudoku.messages.Message;
import sudoku.messages.Language;

public class SettingsScene implements IScene {

    private final SudokuService service;
    private final Settings settings;
    private final Stage stage;
    private IScene menuScene;
    private Language language;
    private Difficulty difficulty;
    private ToggleSwitch toggle;

    public SettingsScene(Stage stage, SudokuService service) {
        this.stage = stage;
        this.language = Message.getLanguage();
        this.service = service;
        this.settings = service.getSettings();
        this.difficulty = settings.getDifficulty();
    }

    @Override
    public void setScenes(IScene scene) {
        menuScene = scene;
    }

    @Override
    public void setScene() {
        stage.setScene(sceneLayout());
    }

    // Takes in menu scene from a button
    @Override
    public void changeScene(IScene scene) {
        scene.changeScene(scene);
    }

    @Override
    public void exitGame() {
        stage.close();
    }

    private ComboBox languageChoices() {
        ComboBox cBox = new ComboBox();
        cBox.getItems().addAll(Message.languageEn(), Message.languageFi());

        if (language == Language.FINNISH) {
            cBox.getSelectionModel().selectLast();
        } else {
            cBox.getSelectionModel().selectFirst();
        }

        cBox.valueProperty().addListener((ov, t, t1) -> {
            if (cBox.getValue().equals(Message.languageEn())) {
                language = Language.ENGLISH;
            } else {
                language = Language.FINNISH;
            }
        });

        return cBox;
    }

    private ComboBox difficultyChoices() {
        ComboBox cBox = new ComboBox();
        cBox.getItems().addAll(Message.difficultyEasy(), Message.difficultyHard());

        if (difficulty == Difficulty.HARD) {
            cBox.getSelectionModel().selectLast();
        } else {
            cBox.getSelectionModel().selectFirst();
        }

        cBox.valueProperty().addListener((ov, t, t1) -> {
            if (cBox.getValue().equals(Message.difficultyEasy())) {
                difficulty = Difficulty.EASY;
            } else {
                difficulty = Difficulty.HARD;
            }

        });

        return cBox;
    }

    private VBox setMenuLayout() {
        VBox layout = new VBox();

        // Menu texts
        Label lbLanguage = new Label(Message.language());
        Label lbDifficulty = new Label(Message.difficulty());
        Label lbAssists = new Label(Message.showMistakes());

        lbLanguage.setFont(new Font(18));
        lbDifficulty.setFont(new Font(18));
        lbAssists.setFont(new Font(18));

        ComboBox cbLanguage = languageChoices();
        ComboBox cbDifficulty = difficultyChoices();
        toggle = new ToggleSwitch();

        // Set initial state of toggle switch
        if (settings.isShowMistakes()) {
            toggle.setSelected(true);
        } else {
            toggle.setSelected(false);
        }

        GridPane grid = new GridPane();

        for (int x = 0; x < 2; x++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }

        for (int y = 0; y < 3; y++) {
            RowConstraints rc = new RowConstraints();
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }

        grid.add(lbLanguage, 0, 0);
        grid.add(cbLanguage, 1, 0);
        grid.add(lbDifficulty, 0, 1);
        grid.add(cbDifficulty, 1, 1);
        grid.add(lbAssists, 0, 2);
        grid.add(toggle, 1, 2);

        grid.setMaxSize(400, 200);
        grid.setMinSize(400, 200);
        layout.getChildren().add(grid);
        layout.setSpacing(30);
        layout.setPadding(new Insets(40, 40, 40, 40));
        layout.setAlignment(Pos.CENTER);
        return layout;
    }

    private AnchorPane addAnchorPane() {
        AnchorPane anchorpane = new AnchorPane();
        Button buttonSave = new Button(Message.apply());
        Button buttonCancel = new Button(Message.backToMenu());

        buttonSave.setOnAction(e -> {
            service.applySettings(language, difficulty, toggle.isSelected());
            setScene();
        });

        buttonCancel.setOnAction(e -> {
            changeScene(menuScene);
        });

        HBox hb = new HBox();
        hb.setPadding(new Insets(0, 10, 10, 10));
        hb.setSpacing(10);
        hb.getChildren().addAll(buttonSave, buttonCancel);

        anchorpane.getChildren().addAll(hb);
        AnchorPane.setBottomAnchor(hb, 8.0);
        AnchorPane.setRightAnchor(hb, 5.0);

        return anchorpane;
    }

    @Override
    public Scene sceneLayout() {
        VBox menuItems = setMenuLayout();
        BorderPane root = new BorderPane();

        Button button = new Button(Message.backToMenu());
        button.setOnAction(e -> {
            changeScene(menuScene);
        });

        root.setCenter(menuItems);
        root.setBottom(addAnchorPane());

        return new Scene(root, 1280, 720);
    }
}
