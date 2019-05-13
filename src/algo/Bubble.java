package algo;

import element.ElementArray;

public class Bubble implements Algorithm {
    private static Bubble ourInstance = new Bubble();

    public static Bubble getInstance() {
        return ourInstance;
    }


    private Bubble() {
    }

    public void sort(ElementArray array) {
        for (int i = array.length(); i >= 1; i--) {
            for (int j = 0; j < i - 1; j++)
                array.compareAndSwap(j, j + 1);
            // Sorted Point
            array.steps.addDoneStep(array.getElementAt(i-1));
        }
    }


    @Override
    public String toString() {
        return "Bubble";
    }
}
