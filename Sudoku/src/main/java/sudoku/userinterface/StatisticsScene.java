package sudoku.userinterface;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sudoku.domain.Statistics;
import sudoku.domain.SudokuService;
import sudoku.messages.Message;

public class StatisticsScene implements IScene {

    private final Stage stage;
    private final Statistics statistics;
    private IScene menuScene;

    public StatisticsScene(Stage stage, SudokuService service) {
        this.stage = stage;
        this.statistics = service.getStatistics();
    }

    @Override
    public void setScenes(IScene menuScene) {
        this.menuScene = menuScene;
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
    public void exitGame() {
        stage.close();
    }
    
    @Override
    public Scene sceneLayout() {
        BorderPane root = new BorderPane();
        root.setCenter(setLayout());
        root.setBottom(addAnchorPane());
        
        return new Scene(root, 1280, 720);
    }
    
    private VBox setLayout(){
        VBox layout = new VBox(); 
        
        ArrayList<Label> items = new ArrayList();

        items.add(new Label(Message.GAMESPLAYED()));
        items.add(new Label(Message.AVGLENGTH()));
        items.add(new Label(Message.MAXLENGTH()));
        items.add(new Label(Message.MINLENGTH()));
        
        items.add(new Label(statistics.getCount()));
        items.add(new Label(statistics.getAvg()));
        items.add(new Label(statistics.getMax()));
        items.add(new Label(statistics.getMin()));
        
        items.forEach(item -> {
            item.setFont(new Font(18));
        });
        
        GridPane grid = new GridPane();

        for (int x = 0; x < 2; x++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }

        for (int y = 0; y < 4; y++) {
            RowConstraints rc = new RowConstraints();
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }
        
        for (int i = 0; i < 4; i++) {
            grid.add(items.get(i), 0, i);
        }
        
        for (int i = 4; i < 8; i++){
            grid.add(items.get(i), 1, i-4);
        }
        
        grid.setMaxSize(400, 200);
        grid.setMinSize(400, 200);
        
        layout.getChildren().add(grid);
        layout.setSpacing(30);
        layout.setPadding(new Insets(40, 40, 40, 40));
        layout.setAlignment(Pos.CENTER);
        return layout;
    }

    private HBox addAnchorPane() {
        AnchorPane anchorpane = new AnchorPane();
        Button btnMenu = new Button(Message.BACKTOMENU());

        btnMenu.setOnAction(e -> {
            changeScene(menuScene);
        });

        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER_RIGHT);
        hb.setPadding(new Insets(0, 10, 10, 10));
        hb.setSpacing(10);
        hb.getChildren().add(btnMenu);

        anchorpane.getChildren().add(hb);
        AnchorPane.setBottomAnchor(hb, 8.0);
        AnchorPane.setRightAnchor(hb, 8.0);

        return hb;
    }
}
