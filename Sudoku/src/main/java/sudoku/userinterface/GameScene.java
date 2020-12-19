package sudoku.userinterface;

import sudoku.domain.Game;
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
import sudoku.domain.SudokuService;
import sudoku.messages.Message;

/**
 * Creates the graphical user interface for the program.
 */
public class GameScene implements IScene {

    private final SudokuService service;
    private final Game game;
    private final Stage stage;
    private final Text text;
    private final Timer timer;
    private IScene menuScene;

    /**
     * Constructor for the UserInterface class that takes in two parameters.As
     * of now you can just instant the class from start() method without
     * assignment.
     *
     *
     * @param stage Stage, where the GUI screen will be drawn into.
     */
    public GameScene(Stage stage, SudokuService service) {
        this.stage = stage;
        this.service = service;
        this.game = service.getGame();

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
        this.stage.setScene(sceneLayout());
        timer.continueTime(game.getGameTime());
    }

    @Override
    public void changeScene(IScene scene) {
        timer.pauseTime();
        scene.setScene();
    }

    @Override
    public void exitGame() {
        service.saveGame(timer.getTime());
        stage.close();
    }

    private void startNewGame() {
        service.newGame();
        text.setText("00:00");
        timer.resetTime(text);
        stage.setScene(sceneLayout());
    }

    @Override
    public Scene sceneLayout() {
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
            service.saveGame(timer.getTime());
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

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                SudokuCell c = new SudokuCell(x, y);

                if (!game.getOriginalMask(x, y)) {
                    c.getTextField().setEditable(false);
                    c.getTextField().setText(game.getCell(x, y) + "");
                } else if (!game.getMask(x, y)) {
                    c.getTextField().setText(game.getCell(x, y) + "");
                    c.getTextField().setStyle("-fx-text-fill: green;");
                }

                sudokuCellListener(x, y, c);
                grid.add(c, x, y);
            }
        }
        grid.setStyle("-fx-padding: 5;");
        grid.setMaxSize(600, 600);
        grid.setMinSize(600, 600);
        return grid;
    }

    private void gameComplete() {
        
        service.insertGame(timer.getTime());
        
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
                service.newGame();
                changeScene(menuScene);
            } else if (rs == exit) {
                service.newGame();
                exitGame();
            }
        });
    }

    private void sudokuCellListener(int x, int y, SudokuCell c) {
        TextField tf = c.getTextField();

        tf.textProperty().addListener((change, oldVal, newVal) -> {
            int value;
            try {
                value = Integer.parseInt(newVal);
            } catch (NumberFormatException ex) {
                value = -1;
            }

            if (service.getSettings().isShowMistakes()) {
                if (game.checkCell(x, y, value)) {
                    tf.setStyle("-fx-text-fill: green;");
                } else if (value == -1) {
                    tf.setStyle("-fx-text-fill: black;");
                } else {
                    tf.setStyle("-fx-text-fill: red;");
                }
            } else {
                game.checkCell(x, y, value);
            }

            if (game.checkComplete()) {
                gameComplete();
            }
        });
    }
}
