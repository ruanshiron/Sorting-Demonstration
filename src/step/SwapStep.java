package step;

import element.Common;
import element.Element;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class SwapStep extends Step {

    SwapStep (Element node1, Element node2) {
        super(node1, node2);
        this.text = "SWAP    [" + node1.getIndex() + "] & ["+ node2.getIndex() + "]";
        this.initAnimationAndReverse();
    }

    @Override
    void setElementState() {
        node1.setFill(Color.BLUE);
        node2.setFill(Color.BLUE);
    }

    @Override
    Animation makeAnimation() {
        // Swap Animation
        TranslateTransition tt1 = new TranslateTransition();
        tt1.setDuration(Duration.seconds(Common.DURATION));
        tt1.setByX(Common.DISTANCE * (node1.getIndex() - node2.getIndex()));
        tt1.setNode(node1);

        TranslateTransition tt2 = new TranslateTransition();
        tt2.setDuration(Duration.seconds(Common.DURATION));
        tt2.setByX(Common.DISTANCE * (- node1.getIndex() + node2.getIndex()));
        tt2.setNode(node2);

        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(tt1, tt2);

        return pt;
    }

    @Override
    Animation makeReverse() {
        // Swap Reverse
        TranslateTransition tt3 = new TranslateTransition();
        tt3.setDuration(Duration.seconds(Common.DURATION));
        tt3.setByX(- Common.DISTANCE * (node1.getIndex() - node2.getIndex()));
        tt3.setNode(node1);

        TranslateTransition tt4 = new TranslateTransition();
        tt4.setDuration(Duration.seconds(Common.DURATION));
        tt4.setByX(- Common.DISTANCE * (- node1.getIndex() + node2.getIndex()));
        tt4.setNode(node2);

        ParallelTransition pt2 = new ParallelTransition();
        pt2.getChildren().addAll(tt3, tt4);

        return pt2;
    }

}
