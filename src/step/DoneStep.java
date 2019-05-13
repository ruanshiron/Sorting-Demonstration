package step;

import element.Common;
import element.Element;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class DoneStep extends Step {

    DoneStep(Element node) {
        super(node, node);
        this.text = "DONE    [" + node1.getIndex() + "]";
        initAnimationAndReverse();
    }

    @Override
    void setElementState() {
        node1.setStateColor(Element.State.DONE);
        node2.setStateColor(Element.State.DONE);
    }

    @Override
    void reverseElementState() {
        node1.setStateColor(Element.State.DONE);
        node2.setStateColor(Element.State.DONE);
    }

    @Override
    Animation makeAnimation() {
        TranslateTransition tt1 = new TranslateTransition();
        tt1.setDuration(Duration.seconds(Common.DURATION/2));
        tt1.setCycleCount(2);
        tt1.setAutoReverse(true);
        tt1.setByY(-Common.HEIGHT);
        tt1.setNode(node1.getShape());

        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(tt1);

        return pt;
    }

    @Override
    Animation makeReverse() {
        return makeAnimation();
    }
}
