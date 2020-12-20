package sudoku.domain;

/**
 * Handles user preferences for game difficulty and whether mistaken
 * Sudoku cells are shown.
 */
public class Settings {

    private Difficulty difficulty;
    private boolean showMistakes;
  
    /**
     * Returns current difficulty set by the player.
     * 
     * @return Difficulty value
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the difficulty for new Sudoku games.
     * 
     * @param difficulty Difficulty value
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Shows whether mistaken Sudoku cells are highlighted.
     * 
     * @return true or false
     */
    public boolean isShowMistakes() {
        return showMistakes;
    }

    /**
     * Sets whether mistaken Sudoku cells are highlighted.
     * 
     * @param showMistakes true or false
     */
    public void setShowMistakes(boolean showMistakes) {
        this.showMistakes = showMistakes;
    }
}
