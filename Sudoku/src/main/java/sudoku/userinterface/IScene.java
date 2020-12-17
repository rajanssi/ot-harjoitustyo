package sudoku.userinterface;


public interface IScene {
    
    void setScenes(IScene scene);

    void setScene();
    
    void changeScene(IScene scene);
    
    void exitGame();
}
