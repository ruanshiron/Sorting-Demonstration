package algo;

import element.ElementArray;

public class Selection implements Algorithm {
    private static Selection ourInstance = new Selection();

    public static Selection getInstance() {
        return ourInstance;
    }

    private Selection() {
    }

    public void sort(ElementArray array) {
        int length = array.length();
        for (int i = 0; i < length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (array.compare(j, minIndex) > 0)
                    minIndex = j;
            }
            array.swap(i, minIndex);
            // Sorted Point
            array.steps.addDoneStep(array.getElementAt(i));
        }
    }

    @Override
    public String toString() {
        return "Selection";
    }
}
