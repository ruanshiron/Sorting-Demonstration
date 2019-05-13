package step;

import element.Common;
import element.Element;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class BucketStep extends Step {

    private int index;

    BucketStep (Element node, int index) {
        super(node, node);
        this.index = index;
        this.initAnimationAndReverse();
        this.text = "[" + node.getIndex() + "] -> "+"BUCKET[" + node.getValue() + "][...]" ;
    }

    BucketStep (int index ,Element node) {
        super(node, node);
        this.index = index;
        this.initAnimationAndReverse();
        this.text = "BUCKET[" + node.getValue() + "][...] -> " + "[" + node.getIndex() + "]";
    }

    @Override
    void setElementState() {
        node1.setStateColor(Element.State.BUCKET);
        node2.setStateColor(Element.State.BUCKET);
    }


    @Override
    void reverseElementState() {
        node1.setStateColor(Element.State.DEFAULT);
        node2.setStateColor(Element.State.DEFAULT);
    }


    @Override
    Animation makeAnimation() {
        TranslateTransition tt1 = new TranslateTransition();
        tt1.setDuration(Duration.seconds(Common.DURATION));
        tt1.setByY(- Common.SCENE_HEIGHT * 0.65 * index / Common.RANDOM);
        tt1.setNode(node1.getShape());

        ParallelTransition pt1 = new ParallelTransition();
        pt1.getChildren().add(tt1);

        return pt1;
    }

    @Override
    Animation makeReverse() {
        TranslateTransition tt2 = new TranslateTransition();
        tt2.setDuration(Duration.seconds(Common.DURATION));
        tt2.setByY(Common.SCENE_HEIGHT * 0.65 * index / Common.RANDOM);
        tt2.setNode(node2.getShape());

        ParallelTransition pt2 = new ParallelTransition();
        pt2.getChildren().add(tt2);

        return pt2;
    }

}
