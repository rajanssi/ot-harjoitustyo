package sudoku.userinterface;

import sudoku.messages.Message;
import sudoku.messages.Language;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sudoku.domain.Difficulty;
import sudoku.domain.Settings;

public class SettingsScene implements IScene {

    private final Stage stage;
    private final Settings settings;
    private IScene menuScene;
    private Language language;
    private Difficulty difficulty;
    private SwitchButton sbAssists;

    public SettingsScene(Stage stage, Settings settings) {
        this.stage = stage;
        this.language = Message.getLanguage();
        this.settings = settings;
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

    private void applyChanges() {
        System.out.println(sbAssists.isState());
        settings.applySettings(language, difficulty, sbAssists.isState());
    }

    private ComboBox languageChoices() {
        ComboBox cBox = new ComboBox();
        cBox.getItems().addAll(Message.LANGUAGE_EN(), Message.LANGUAGE_FI());

        if (language == Language.FINNISH) {
            cBox.getSelectionModel().selectLast();
        } else {
            cBox.getSelectionModel().selectFirst();
        }

        cBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                if (cBox.getValue().equals(Message.LANGUAGE_EN())) {
                    language = Language.ENGLISH;
                } else {
                    language = Language.FINNISH;
                }
            }
        });

        return cBox;
    }

    private ComboBox difficultyChoices() {
        ComboBox cBox = new ComboBox();
        cBox.getItems().addAll(Message.DIFFICULTY_E(), Message.DIFFICULTY_H());

        if (difficulty == Difficulty.HARD) {
            cBox.getSelectionModel().selectLast();
        } else {
            cBox.getSelectionModel().selectFirst();
        }

        cBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                if (cBox.getValue().equals(Message.DIFFICULTY_E())) {
                    difficulty = Difficulty.EASY;
                } else {
                    difficulty = Difficulty.HARD;
                }
            }
        });

        return cBox;
    }

    private VBox setMenuLayout() {
        VBox layout = new VBox();
        ArrayList<HBox> items = new ArrayList();

        // Menu texts
        Label lbLanguage = new Label(Message.LANGUAGE());
        Label lbDifficulty = new Label(Message.DIFFICULTY());
        Label lbAssists = new Label(Message.SHOWMISTAKES());

        lbLanguage.setFont(new Font(15));
        lbDifficulty.setFont(new Font(15));
        lbAssists.setFont(new Font(15));

        ComboBox cbLanguage = languageChoices();
        ComboBox cbDifficulty = difficultyChoices();
        sbAssists = new SwitchButton(settings.isShowMistakes());

        items.add(new HBox(lbLanguage, cbLanguage));
        items.add(new HBox(lbDifficulty, cbDifficulty));
        items.add(new HBox(lbAssists, sbAssists));

        items.forEach(item -> {
            item.setSpacing(100);
        });

        layout.getChildren().addAll(items);
        layout.setSpacing(30);
        layout.setPadding(new Insets(40, 40, 40, 40));
        layout.setAlignment(Pos.CENTER);
        return layout;
    }

    private AnchorPane addAnchorPane() {
        AnchorPane anchorpane = new AnchorPane();
        Button buttonSave = new Button("Apply (placeholder)");
        Button buttonCancel = new Button(Message.QUIT());

        buttonSave.setOnAction(e -> {
            applyChanges();
        });

        buttonCancel.setOnAction(e -> {
            changeScene(menuScene);
        });

        HBox hb = new HBox();
        hb.setPadding(new Insets(0, 10, 10, 10));
        hb.setSpacing(10);
        hb.getChildren().addAll(buttonSave, buttonCancel);

        anchorpane.getChildren().addAll(hb);   // Add grid from Example 1-5
        AnchorPane.setBottomAnchor(hb, 8.0);
        AnchorPane.setRightAnchor(hb, 5.0);

        return anchorpane;
    }

    private Scene sceneLayout() {
        VBox menuItems = setMenuLayout();
        BorderPane root = new BorderPane();

        //Placeholder button
        Button button = new Button(Message.BACKTOMENU());
        button.setOnAction(e -> {
            changeScene(menuScene);
        });
        //menuItems.getChildren().add(button);
        root.setCenter(menuItems);
        root.setBottom(addAnchorPane());

        return new Scene(root, 1280, 720);
    }
}
