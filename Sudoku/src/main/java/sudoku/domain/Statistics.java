package sudoku.domain;

public class Statistics {

    private int count;
    private int avg;
    private int min;
    private int max;

    public void setAll(int count, int avg, int min, int max) {
        this.count = count;
        this.avg = avg;
        this.min = min;
        this.max = max;
    }

    public String getCount() {
        return String.valueOf(count);
    }

    public String getAvg() {
        int minutes = avg / 60;
        int seconds = avg - (minutes * 60); 
        return minutes + " min " + seconds + " sec";
    }

    public String getMax() {
        int minutes = max / 60;
        int seconds = max - (minutes * 60); 
        return minutes + " min " + seconds + " sec";
    }

    public String getMin() {
        int minutes = min / 60;
        int seconds = min - (minutes * 60); 
        return minutes + " min " + seconds + " sec";
    }
}
