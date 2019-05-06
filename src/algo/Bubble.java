package algo;

import element.ElementArray;

public class Bubble implements Algorithm {
    private static Bubble Instance = new Bubble();

    public static Bubble getInstance() {
        return Instance;
    }


    private Bubble() {
    }

    public void sort(ElementArray array) {
        for (int i = array.length(); i >= 1; i--) {
            for (int j = 0; j < i - 1; j++)
                array.compareAndSwap(j, j + 1);
            // Sorted Point
        }
    }


    @Override
    public String toString() {
        return "Bubble";
    }
}
