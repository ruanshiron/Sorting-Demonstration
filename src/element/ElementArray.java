package element;

import step.Steps;


public class ElementArray {
    private Element [] elements;

    public Steps steps;

    public ElementArray(int length) {
        elements = new Element[length];
        steps = new Steps();

        for (int i = 0; i<length; i++) {
            int value = (int) ( Math.random() * Common.RANDOM ) + 1;
            elements[i] = new Element(value);
            elements[i].setIndex(i);

            elements[i].setLayoutX(Common.SCENE_WIDTH/2 - Common.DISTANCE * length/2 + i * Common.DISTANCE);
            elements[i].setLayoutY(Common.SCENE_HEIGHT * 0.7 - value * Common.HEIGHT);

            System.out.print(value + " ");
        }

        System.out.println();
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

    // moveY() replaced
    public void moveToBufferArray(int index, int bufferIndex) {
        steps.addBucketStep(elements[index], bufferIndex);
    }

    public void moveFromBufferArray(int index, int bufferIndex) {
        steps.addBucketStep(bufferIndex, elements[index]);
    }

    public void reposition() {
        for (Element element: elements) {
            element.setTranslateX(0);
            element.setTranslateY(0);
            element.setLayoutX(element.getLayoutX());
            element.setLayoutY(element.getLayoutY());
        }
    }
}
