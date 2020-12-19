package sudoku.userinterface;

import javafx.scene.Scene;


public interface IScene {
    
    void setScenes(IScene scene);

    void setScene();
    
    void changeScene(IScene scene);
    
    void exitGame();
    
    Scene sceneLayout();
}
