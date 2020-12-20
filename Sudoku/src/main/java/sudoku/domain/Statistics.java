package sudoku.domain;

/**
 * Handles and converts statistics data from SudokuService class and returns it
 * to UI in String format.
 */
public class Statistics {

    private int count;
    private int avg;
    private int min;
    private int max;

    /**
     * Sets all relevant fields of this object.
     *
     * @param count Number of games played
     * @param avg Average time for all games played
     * @param min Minimum time for all games played
     * @param max Maximum time for all games played
     */
    public void setAll(int count, int avg, int min, int max) {
        this.count = count;
        this.avg = avg;
        this.min = min;
        this.max = max;
    }

    /**
     * Returns the number of all games saved on the database.
     * 
     * @return Number of games played as a String
     */
    public String getCount() {
        return String.valueOf(count);
    }

    /**
     * Converts average game length to minutes and seconds and returns a
     * String.
     * 
     * @return Average game length converted to minutes and seconds. 
     */
    public String getAvg() {
        int minutes = avg / 60;
        int seconds = avg - (minutes * 60);
        return minutes + " min " + seconds + " sec";
    }

    /**
     * Converts maximum game length to minutes and seconds and returns a
     * String.
     * 
     * @return Maximum game length converted to minutes and seconds.
     */
    public String getMax() {
        int minutes = max / 60;
        int seconds = max - (minutes * 60);
        return minutes + " min " + seconds + " sec";
    }

    /**
     * Converts minimum game length to minutes and seconds and returns a
     * String.
     * 
     * @return Minimum game length converted to minutes and seconds.
     */
    public String getMin() {
        int minutes = min / 60;
        int seconds = min - (minutes * 60);
        return minutes + " min " + seconds + " sec";
    }
}
