package core;

import javafx.animation.*;
import javafx.util.Duration;



public class ElementArray {
    private Element [] elements;

    private SequentialTransition animation;

    public ElementArray(int length) {
        elements = new Element[length];
        animation = new SequentialTransition();

        for (int i = 0; i<length; i++) {
            int value = (int) ( Math.random() * Common.RANDOM ) + 1;
            elements[i] = new Element(value);
            elements[i].setIndex(i);

            elements[i].setX(Common.SCENE_WIDTH/2 - Common.DISTANCE * length/2 + i * Common.DISTANCE);
            elements[i].setY(Common.SCENE_HEIGHT*0.7 - value * Common.HEIGHT);

            System.out.print(value + " ");
        }
    }

    public Element [] getAll () {
        return elements;
    }

    public Element getElementAt(int i) {
        return elements[i];
    }

    public int length() {
        return elements.length;
    }


    public void swap(int i, int j) {
        Element tmp = elements[i];
        elements[i] = elements[j];
        elements[j] = tmp;

        elements[i].setIndex(i);
        elements[j].setIndex(j);

        TranslateTransition tt1 = new TranslateTransition();
        tt1.setDuration(Duration.seconds(Common.DURATION));
        tt1.setByX(Common.DISTANCE * (i-j));
        tt1.setNode(elements[i]);

        TranslateTransition tt2 = new TranslateTransition();
        tt2.setDuration(Duration.seconds(Common.DURATION));
        tt2.setByX(Common.DISTANCE * (j-i));
        tt2.setNode(elements[j]);

        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(tt1, tt2);

        animation.getChildren().add(pt);
    }

    public void compareAndSwap(int i, int j) {
        if (elements[i].compareTo(elements[j]) < 0) {
            swap(i, j);
        }
    }

    public SequentialTransition getAnimation() {
        return animation;
    }

    public int compare(int i, int j) {
        return elements[i].compareTo(elements[j]);
    }

    public Element getMax() {
        Element MAX = elements[0];
        for (Element e: elements) {
            if (MAX.compareTo(e) > 0) {
                MAX = e;
            }
        }
        return MAX;
    }

    public void moveY(int i, int y) {
        TranslateTransition tt1 = new TranslateTransition();
        tt1.setDuration(Duration.seconds(Common.DURATION));
        tt1.setByY(- Common.SCENE_HEIGHT * 0.7 * y / getMax().getValue());
        tt1.setNode(elements[i]);

        animation.getChildren().add(tt1);
    }
}
