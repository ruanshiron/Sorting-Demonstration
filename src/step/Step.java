package step;

import element.Element;
import javafx.animation.Animation;

public abstract class Step {

    private Animation animation;

    private Animation reverse;

    private Step next;

    private Step previous;

    String text;

    Element node1;

    Element node2;

    Step (Element node1, Element node2) {
        this.node1 = node1;
        this.node2 = node2;

    }

    public void initAnimationAndReverse() {
        this.animation = makeAnimation();
        this.reverse = makeReverse();
    }

    abstract void setElementState();

    abstract void reverseElementState();

    abstract Animation makeAnimation();

    abstract Animation makeReverse();

    public void playOne(Closure onFinished) {
        if (previous!=null) previous.reverseElementState();
        setElementState();

        animation.pause();

        animation.setOnFinished(event -> {
            // Do nothing here
            onFinished.process();
        });

        animation.play();
    }

    public void reverse(Closure onFinished) {
        if (next!=null) next.reverseElementState();
        setElementState();

        reverse.pause();

        reverse.setOnFinished(event -> {
            // Do nothing here
            onFinished.process();

        });

        reverse.play();

    }

    public void play(Closure onFinished) {
        reverseElementState();
        if (previous!=null) previous.reverseElementState();
        animation.pause();

        animation.setOnFinished(event -> {
            onFinished.process();
            if (next != null) next.play(onFinished);
        });

        animation.play();
        setElementState();
    }

    public void stop() {
        animation.stop();
        reverse.stop();
    }

    public void setNodes(Element node1, Element node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    public void setNext(Step next) {
        this.next = next;
    }

    public void setPrevious(Step previous) {
        this.previous = previous;
    }

    public Step getNext() {
        return next;
    }

    public Step getPrevious() {
        return previous;
    }

    @Override
    public String toString() {
        return text;
    }

    @FunctionalInterface
    interface Closure {
        void process();
    }
}
