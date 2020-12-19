package sudoku.domain;

public class Settings {

    private Difficulty difficulty;
    private boolean showMistakes;
  
    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isShowMistakes() {
        return showMistakes;
    }

    public void setShowMistakes(boolean showMistakes) {
        this.showMistakes = showMistakes;
    }
}
