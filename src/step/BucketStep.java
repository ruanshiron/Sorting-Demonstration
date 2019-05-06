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
        node1.setFill(Color.GREEN);
        node2.setFill(Color.GREEN);
    }

    @Override
    Animation makeAnimation() {
        TranslateTransition tt1 = new TranslateTransition();
        tt1.setDuration(Duration.seconds(Common.DURATION));
        tt1.setByY(- Common.SCENE_HEIGHT * 0.7 * index / Common.RANDOM);
        tt1.setNode(node1);

        ParallelTransition pt1 = new ParallelTransition();
        pt1.getChildren().add(tt1);

        return pt1;
    }

    @Override
    Animation makeReverse() {
        TranslateTransition tt2 = new TranslateTransition();
        tt2.setDuration(Duration.seconds(Common.DURATION));
        tt2.setByY(Common.SCENE_HEIGHT * 0.7 * index / Common.RANDOM);
        tt2.setNode(node2);

        ParallelTransition pt2 = new ParallelTransition();
        pt2.getChildren().add(tt2);

        return pt2;
    }

}
