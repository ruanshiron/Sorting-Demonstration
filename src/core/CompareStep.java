package core;

import javafx.animation.Animation;
import javafx.scene.paint.Color;

public class CompareStep extends Step {
    CompareStep (Animation animation, Animation reverse, String text) {
        super(animation, reverse, text);
    }

    @Override
    void setElementState() {
        node1.setFill(Color.YELLOWGREEN);
        node2.setFill(Color.YELLOWGREEN);
    }

    @Override
    void reverseElementState() {
        node1.setFill(Color.BLACK);
        node2.setFill(Color.BLACK);
    }
}
