package core;

import javafx.animation.Animation;
import javafx.scene.paint.Color;

public class BucketStep extends Step {
    BucketStep (Animation animation, Animation reverse, String text) {
        super(animation,reverse, text);
    }

    @Override
    void setElementState() {
        node1.setFill(Color.GREEN);
        node2.setFill(Color.GREEN);
    }

    @Override
    void reverseElementState() {
        node1.setFill(Color.BLACK);
        node2.setFill(Color.BLACK);
    }
}
