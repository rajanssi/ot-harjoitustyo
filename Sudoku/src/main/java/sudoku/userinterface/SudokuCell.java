package sudoku.userinterface;

import sudoku.domain.Puzzle;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;

public class SudokuCell extends TextField {

    public SudokuCell(int x, int y) {
        this.setFont(new Font(20));
        this.setAlignment(Pos.CENTER);
        this.setPrefHeight(64);
        this.setPrefWidth(64);
        this.setBackground(Background.EMPTY);

        if (!Puzzle.getMasks()[x][y]) {
            this.setEditable(false);
            this.setText(Puzzle.getCells()[x][y] + "");
        }

        // Set input validation
        this.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent keyEvent) -> {
            if (!"123456789".contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }
        });
    }
}
