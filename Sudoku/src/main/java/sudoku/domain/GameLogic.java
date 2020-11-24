package sudoku.domain;

public class GameLogic {

    private boolean[][] masks;

    public GameLogic() {
        initGame();
    }
    
    public boolean checkCell(int x, int y, int value) {
        return Puzzle.getCells()[x][y] == value;
    }
    
    public void setMask(int x, int y) {
        masks[x][y] = false;
    }
    
    public boolean checkComplete() {
        for (int row = 0; row < 9; ++row) {
            for (int col = 0; col < 9; ++col) {
                if (masks[row][col] == true) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void initGame() {
        Puzzle.setPuzzle();
        this.masks = Puzzle.getMasks();
    }
}
