package element;

import javafx.scene.layout.Pane;
import step.Steps;


public class ElementArray {
    private Element [] elements;

    public Steps steps;

    public ElementArray(int length) {
        elements = new Element[length];
        steps = new Steps();

        for (int i = 0; i<length; i++) {
            int value = (int) ( Math.random() * Common.RANDOM ) + 1;
            elements[i] = Common.ELEMENT_TYPE == Element.Type.COLUMN ? new ColumnElement(value, i) : new BoxElement(value, i);

            // Position Element
            elements[i].getShape().setLayoutX(Common.SCENE_WIDTH/2 - (Common.WIDTH + Common.DISTANCE) * length/2 + i * (Common.WIDTH + Common.DISTANCE));
            elements[i].getShape().setLayoutY(Common.SCENE_HEIGHT * 0.7 - elements[i].getShape().getPrefHeight());

            System.out.print(value + " ");
        }

        System.out.println();
    }

    public Pane [] getAllShape () {
        Pane [] shapes = new Pane[length()];
        for (int i = 0; i < elements.length; i++) {
            shapes[i] = elements[i].getShape();
        }
        return shapes;
    }

    public Element [] getAll() {
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

        //
        steps.addSwapStep(elements[i], elements[j]);
    }

    public int compare(int i, int j) {
        //
        steps.addCompareStep(elements[i], elements[j]);

        return elements[i].compareTo(elements[j]);
    }

    public void compareAndSwap(int i, int j) {
        if (compare(i, j) < 0) {
            swap(i, j);
        }
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

    // Those methods
    public void moveToBufferArray(int index, int bufferIndex) {
        steps.addBucketStep(elements[index], bufferIndex);
    }

    public void moveFromBufferArray(int index, int bufferIndex) {
        steps.addBucketStep(bufferIndex, elements[index]);
    }

    //
    public void reposition() {
        for (Element element: elements) {
            element.getShape().setTranslateX(0);
            element.getShape().setTranslateY(0);
            element.getShape().setLayoutX(element.getShape().getLayoutX());
            element.getShape().setLayoutY(element.getShape().getLayoutY());
        }
    }
}
