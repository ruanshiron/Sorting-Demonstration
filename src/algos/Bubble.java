package algos;

import core.Algorithm;
import core.ElementArray;
import javafx.animation.SequentialTransition;
import javafx.event.Event;
import javafx.event.EventHandler;

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
            //array.setElement(i - 1, SortArray.ElementState.DONE);
        }
    }

    public void sort(ElementArray array, int n)
    {
        if (n == 1)return;
        else{
            sort(array, n - 1);
            if (array.getElementAt(n-1).compareTo(array.getElementAt(n-2)) <0)
            {
                array.swap(n-1, n-2);
		        sort(array, n - 1);
            }
        }

    }

    @Override
    public String getName() {
        return "Bubble";
    }

    @Override
    public String toString() {
        return "Bubble";
    }
}
