package core;

import javafx.animation.Animation;
import javafx.scene.paint.Color;

public abstract class Step {

    private Animation animation;

    private Animation reverse;

    private String text;

    private Step next;

    private Step previous;

    Element node1;

    Element node2;

    Step (Animation animation, Animation reverse, String text) {
        this.animation = animation;
        this.reverse = reverse;
        this.text = text;
    }

    Step (Animation animation, Animation reverse, String text, Step previous, Step next) {
        this.animation = animation;
        this.reverse = reverse;
        this.text = text;
        this.next = next;
        this.previous = previous;
    }


    abstract void setElementState();

    abstract void reverseElementState();

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
