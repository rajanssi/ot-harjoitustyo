package sudoku.userinterface;

import sudoku.domain.GameLogic;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sudoku.messages.Message;

/**
 * Creates the graphical user interface for the program.
 */
public class GameScene implements IScene {

    private final GameLogic game;
    private final Stage stage;
    private final Text text;
    private final Timer timer;
    private IScene menuScene;

    /**
     * Constructor for the UserInterface class that takes in two parameters. As
     * of now you can just instant the class from start() method without
     * assignment.
     *
     * @param game Handles the logic of a Sudoku puzzle.
     *
     * @param stage Stage, where the GUI screen will be drawn into.
     */
    public GameScene(Stage stage, GameLogic game) {
        this.game = game;
        this.stage = stage;

        text = new Text("00:00");
        timer = new Timer(text, game.getGameTime());
    }

    @Override
    public void setScenes(IScene menuScene) {
        this.menuScene = menuScene;
    }

    @Override
    public void setScene() {
        this.stage.setOnCloseRequest(e -> exitGame());
        this.stage.setScene(gameSceneLayout());
        System.out.println(game.getGameTime());
        timer.continueTime(game.getGameTime());
    }

    @Override
    public void changeScene(IScene scene) {
        timer.pauseTime();
        scene.setScene();
    }

    @Override
    public void exitGame() {
        game.saveGame(timer.getTime());
        stage.close();
    }

    private void startNewGame() {
        game.newGame();
        text.setText("00:00");
        timer.resetTime(text);
        stage.setScene(gameSceneLayout());
    }

    private Scene gameSceneLayout() {
        FlowPane pane = new FlowPane();
        GridPane grid = sudokuGrid();
        GridPane numberPane = new GridPane();
        VBox sudokuBox = new VBox();
        VBox numberBox = new VBox();

        sudokuBox.setPadding(new Insets(10, 10, 10, 10));
        numberBox.setPadding(new Insets(10, 10, 10, 10));

        Button btnBackToMenu = new Button(Message.BACKTOMENU());
        btnBackToMenu.setPrefSize(100, 20);
        btnBackToMenu.setOnAction(e -> {
            game.saveGame(timer.getTime());
            game.setGameTime(timer.getTime());
            changeScene(menuScene);
        });

        sudokuBox.getChildren().add(grid);
        numberBox.getChildren().addAll(newGameButton(), numberPane);
        pane.getChildren().addAll(sudokuBox, numberBox);

        BorderPane root = new BorderPane();
        root.setTop(addHBox(btnBackToMenu));
        root.setCenter(pane);

        root.setStyle("cell-color: white ;"
                + "cell-border-color: black ;");
        return new Scene(root, 1280, 720);
    }

    private HBox addHBox(Button b1) {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #ffffff;");

        hbox.getChildren().addAll(b1, text);

        return hbox;
    }

    private Button newGameButton() {
        Button bt = new Button(Message.NEWGAME());
        bt.setOnAction((event) -> {
            startNewGame();
        });

        return bt;
    }

    private GridPane sudokuGrid() {
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

                if (!game.getOriginalMask(row, col)) {
                    c.getTextField().setEditable(false);
                    c.getTextField().setText(game.getCell(row, col) + "");
                } else if (!game.checkMask(row, col)) {
                    c.getTextField().setText(game.getCell(row, col) + "");
                    c.getTextField().setStyle("-fx-text-fill: green;");
                }

                sudokuCellListener(row, col, c);
                grid.add(c, row, col);
            }
        }
        grid.setStyle("-fx-padding: 5;");
        grid.setMaxSize(600, 600);
        grid.setMinSize(600, 600);
        return grid;
    }

    private void gameComplete() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(Message.CONGRATS());
        alert.setHeaderText(Message.WINMESSAGE());
        alert.setContentText(null);

        ButtonType confirm = new ButtonType("OK");
        ButtonType deny = new ButtonType(Message.BACKTOMENU());
        ButtonType exit = new ButtonType(Message.QUIT());

        alert.getButtonTypes().setAll(confirm, deny, exit);

        alert.showAndWait().ifPresent(rs -> {
            if (rs == confirm) {
                startNewGame();
            } else if (rs == deny) {
                game.newGame();
                changeScene(menuScene);
            } else if (rs == exit) {
                game.newGame();
                exitGame();
            }
        });
    }

    private void sudokuCellListener(int x, int y, SudokuCell c) {
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
            } else if (value == -1) {
                tf.setStyle("-fx-text-fill: black;");
            } else {
                tf.setStyle("-fx-text-fill: red;");
            }

            if (game.checkComplete()) {
                gameComplete();
            }
        });
    }
}
