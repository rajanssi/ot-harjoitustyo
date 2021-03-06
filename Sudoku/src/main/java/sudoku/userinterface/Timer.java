package sudoku.userinterface;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Timer {

    private Timeline timeline;
    private Text text;
    private int sec;
    private int min;

    public Timer(Text text) {
        sec = 0;
        min = 0;
        this.text = text;
        setTimer();
    }

    public Timer(Text text, int time) {
        min = time / 60;
        sec = time - (min * 60);
        this.text = text;
        setTimer();
    }

    public int getTime() {
        return min * 60 + sec;
    }

    public void pauseTime() {
        timeline.pause();
    }

    public void continueTime(int time) {
        min = time / 60;
        sec = time - (min * 60);
        timeline.play();
    }

    public void resetTime(Text text) {
        sec = 0;
        min = 0;
        this.text = text;
    }

    private void setTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
            change(text);
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);
    }

    private void change(Text text) {
        sec++;
        if (sec == 60) {
            sec = 0;
            min++;
        }
        text.setText((((min / 10) == 0) ? "0" : "") + min + ":"
                + (((sec / 10) == 0) ? "0" : "") + sec);

    }
}
