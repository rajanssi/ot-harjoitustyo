package sudoku.userinterface;

import sudoku.domain.GameLogic;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sudoku.domain.Timer;

/**
 * Creates the graphical user interface for the program.
 */
public class UserInterface {

    private GameLogic game;
    private Stage stage;
    private Scene scene;
    private Text text;

    /**
     * Constructor for the UserInterface class that takes in two parameters. As
     * of now you can just instant the class from start() method without
     * assignment.
     *
     * @param game Handles the logic of a Sudoku puzzle.
     *
     * @param stage Stage, where the GUI screen will be drawn into.
     */
    public UserInterface(GameLogic game, Stage stage) {
        this.game = game;
        this.stage = stage;
        scene = new Scene(getScene());
        stage.setScene(scene);
        stage.show();
    }

    private Parent getScene() {
        BorderPane layout = new BorderPane();
        layout.setCenter(setGrid());
        layout.setRight(gameDurationText());
        layout.setTop(newGameButton());
        return layout;
    }

    private Button newGameButton() {
        Button bt = new Button("New game");
        bt.setOnAction((event) -> {
            newScene();
        });

        return bt;
    }

    private Text gameDurationText() {
        text = new Text("00.00");
        Timer timer = new Timer(text);

        return text;
    }

    private GridPane setGrid() {
        GridPane grid = new GridPane();

        for (int x = 0; x < 9; x++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }

        for (int y = 0; y < 9; y++) {
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }

        for (int row = 0; row < 9; ++row) {
            for (int col = 0; col < 9; ++col) {
                SudokuCell c = new SudokuCell(row, col);

                if (!game.checkMask(row, col)) {
                    c.getTextField().setEditable(false);
                    c.getTextField().setText(game.getCell(row, col) + "");
                }

                addListener(row, col, c);
                grid.add(c, row, col);
            }
        }
        grid.setStyle("-fx-padding: 5;");
        grid.setMaxSize(600, 600);
        grid.setMinSize(600, 600);
        return grid;
    }

    private void newScene() {
        game.newGame();
        stage.setScene(new Scene(getScene()));
    }

    private void addListener(int x, int y, SudokuCell c) {
        TextField tf = c.getTextField();
        tf.textProperty().addListener((ObservableValue<? extends String> change, String oldVal, String newVal) -> {
            int value;
            try {
                value = Integer.parseInt(newVal);
            } catch (NumberFormatException ex) {
                value = -1;
            }

            if (game.checkCell(x, y, value)) {
                game.setMask(x, y);
                tf.setStyle("-fx-text-fill: green;");
            }

            if (game.checkComplete()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Onneksi olkoon!");
                alert.setHeaderText("Voitit pelin! Pelaatko uudestaan?");
                alert.setContentText(null);
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        newScene();
                    }
                });
            }
        });
    }
}
