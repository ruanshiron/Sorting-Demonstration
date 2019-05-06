package core;

import javafx.animation.Animation;
import javafx.scene.paint.Color;

public class SwapStep extends Step {
    SwapStep (Animation animation, Animation reverse, String text) {
        super(animation,reverse, text);
    }

    @Override
    void setElementState() {
        node1.setFill(Color.BLUE);
        node2.setFill(Color.BLUE);
    }

    @Override
    void reverseElementState() {
        node1.setFill(Color.BLACK);
        node2.setFill(Color.BLACK);
    }
}
