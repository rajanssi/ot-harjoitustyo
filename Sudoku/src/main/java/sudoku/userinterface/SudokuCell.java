package sudoku.userinterface;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 * Class for creating Sudoku cells, that can be interacted with. 
 * 
 */
public class SudokuCell extends StackPane {

    private TextField tField;
    private int x;
    private int y;
    
    /**
     * Constructor for a Sudoku cell, that will be assigned to a grid.
     * 
     * @param x X coordinate of this Sudoku cell in a Sudoku grid
     * @param y Y coordinate of this Sudoku cell in a Sudoku grid
     */
    public SudokuCell(int x, int y) {
        this.x = x;
        this.y = y;
        
        this.setStyle(setCellStyle());
        tField = new TextField();
        setTextStyle();
        this.getChildren().add(tField);
        
        /*
        if (!Puzzle.getMasks()[x][y]) {
            tField.setEditable(false);
            tField.setText(Puzzle.getCells()[x][y] + "");
        }
        */
        
        // Set input validation, NOT COMPLETE
        tField.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent keyEvent) -> {
            if (!"123456789".contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }    
        });
    }
    
    public TextField getTextField(){
        return this.tField;
    }

    private void setTextStyle() {
        tField.setFont(new Font(20));
        tField.setAlignment(Pos.CENTER);
        tField.setPrefHeight(64);
        tField.setPrefWidth(64);
        tField.setBackground(Background.EMPTY);
    }
    
    private String setCellStyle() {
        String style = "-fx-background-color: black, white;\n" + "-fx-background-insets: 0, 1 1 1 1 ;";
        if (x == 0) {
            switch (y) {
                case 0:
                    style = "-fx-background-color: black, white;\n" + "-fx-background-insets: 0, 3 1 1 3 ;";
                    break;
                case 2:
                    style = "-fx-background-color: black, white;\n" + "-fx-background-insets: 0, 1 1 3 3 ;";
                    break;
                case 5:
                    style = "-fx-background-color: black, white;\n" + "-fx-background-insets: 0, 1 1 3 3 ;";
                    break;
                case 8:
                    style = "-fx-background-color: black, white;\n" + "-fx-background-insets: 0, 1 1 3 3 ;";
                    break;
                default:
                    style = "-fx-background-color: black, white;\n" + "-fx-background-insets: 0, 1 1 1 3 ;";
                    break;
            }
        } else if (x == 2 || x == 5 || x == 8) {
            switch (y) {
                case 0:
                    style = "-fx-background-color: black, white;\n" + "-fx-background-insets: 0, 3 3 1 1 ;";
                    break;
                case 2:
                    style = "-fx-background-color: black, white;\n" + "-fx-background-insets: 0, 1 3 3 1 ;";
                    break;
                case 5:
                    style = "-fx-background-color: black, white;\n" + "-fx-background-insets: 0, 1 3 3 1 ;";
                    break;
                case 8:
                    style = "-fx-background-color: black, white;\n" + "-fx-background-insets: 0, 1 3 3 1 ;";
                    break;
                default:
                    style = "-fx-background-color: black, white;\n" + "-fx-background-insets: 0, 1 3 1 1 ;";
                    break;
            }
        } else {
            switch (y) {
                case 0:
                    style = "-fx-background-color: black, white;\n" + "-fx-background-insets: 0, 3 1 1 1 ;";
                    break;
                case 2:
                    style = "-fx-background-color: black, white;\n" + "-fx-background-insets: 0, 1 1 3 1 ;";
                    break;
                case 5:
                    style = "-fx-background-color: black, white;\n" + "-fx-background-insets: 0, 1 1 3 1 ;";
                    break;
                case 8:
                    style = "-fx-background-color: black, white;\n" + "-fx-background-insets: 0, 1 1 3 1 ;";
                    break;
            }
        }

        return style;
    }
}
