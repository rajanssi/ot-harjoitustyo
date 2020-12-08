package sudoku.domain;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Creates a timer that tracks how much time the user has spent trying to 
 * solve a Sudoku puzzle.
 */
public class Timer {

    Timeline timeline;
    int sec;
    int min;

    /**
     * Constructor for the Timer class, that takes in one parameter and starts
     * tracking time immediately after initialization.
     * 
     * @param text Text showing elapsed time that will be shown in the GUI 
     * and will be be incremented every second.
     */
    public Timer(Text text) {
        sec = 0;
        min = 0;

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
            change(text);
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);
        timeline.play();
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
