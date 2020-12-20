package sudoku.userinterface;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class SudokuCell extends StackPane {

    private final TextField tField;
    private final int x;
    private final int y;
    
    public SudokuCell(int x, int y) {
        this.x = x;
        this.y = y;
        
        this.setStyle(setCellStyle());
        tField = new TextField();
        setTextStyle();
        this.getChildren().add(tField);
        
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
        tField.setFont(new Font(23));
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
