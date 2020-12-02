package sudoku.userinterface;

import sudoku.domain.GameLogic;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UserInterface {

    private GameLogic game;
    private final Stage stage;
    private Scene scene;

    public UserInterface(GameLogic game, Stage stage) {
        this.game = game;
        this.stage = stage;

        scene = new Scene(getScene());
        stage.setScene(scene);
        stage.show();
    }

    public Parent getScene() {
        BorderPane layout = new BorderPane();
        layout.setCenter(setGrid());
        layout.setRight(newGameButton());
        return layout;
    }

    private Button newGameButton() {
        Button bt = new Button("New game");
        bt.setOnAction((event) -> {
            game.initGame();
            stage.setScene(new Scene(getScene()));
        });

        return bt;
    }

    private GridPane setGrid() {
        GridPane grid = new GridPane();
        for (int row = 0; row < 9; ++row) {
            for (int col = 0; col < 9; ++col) {
                SudokuCell c = new SudokuCell(row, col);
                addListener(row, col, c);
                c.setStyle("-fx-background-color: black, white ;"
                        + "-fx-background-insets: 0, 0 1 1 0 ;"
                        + "-fx-border-width: 0 5 5 0;");
                grid.add(c, row, col);
            }
        }
        grid.setStyle("-fx-background-color: white ;"
                + "-fx-padding: 20;");
        grid.setGridLinesVisible(true);
        return grid;
    }

    private void addListener(int x, int y, SudokuCell c) {
        c.textProperty().addListener((ObservableValue<? extends String> change, String oldVal, String newVal) -> {
            if (game.checkCell(x, y, Integer.parseInt(newVal))) {
                // Väliaikainen ratkaisu testauksen vuoksi!
                System.out.println("oikein!");
                game.setMask(x, y);
                c.setStyle("-fx-control-inner-background: yellow;");
            }

            if (game.checkComplete()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Onneksi olkoon!");
                alert.setHeaderText("Voitit pelin! Pelaatko uudestaan?");
                alert.setContentText(null);
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        game.initGame();
                        stage.setScene(new Scene(getScene()));
                    }
                });
            }
        });
    }
}
