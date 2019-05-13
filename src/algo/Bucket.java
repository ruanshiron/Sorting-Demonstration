package algo;

import element.Element;
import element.ElementArray;

public class Bucket implements Algorithm {
    private static Bucket ourInstance = new Bucket();

    public static Bucket getInstance() {
        return ourInstance;
    }

    private Bucket() {
    }

    private void sort(ElementArray arr, Element maxVal) {
        int [] bucket = new int[maxVal.getValue()+1];
        Element [][] bucketElement = new Element[maxVal.getValue()+1][maxVal.getValue()+1];

        for (int i=0; i<bucket.length; i++) {
            bucket[i] = 0;
        }

        for (int i=0; i<arr.length(); i++) {
            bucketElement[arr.getElementAt(i).getValue()][bucket[arr.getElementAt(i).getValue()]] = arr.getElementAt(i);
            bucket[arr.getElementAt(i).getValue()] = bucket[arr.getElementAt(i).getValue()] + 1;

            arr.moveToBufferArray(i, maxVal.getValue() - arr.getElementAt(i).getValue());
        }

        int outPos=0;

        for (int i=0 ; i<bucket.length; i++) {

            if (bucket[i] != 0) {
                for (int j=0; j<bucket[i]; j++) {
                    arr.swap((bucketElement[i][j]).getIndex(), outPos);

                    arr.moveFromBufferArray((bucketElement[i][j]).getIndex(), -maxVal.getValue() + arr.getElementAt((bucketElement[i][j]).getIndex()).getValue());

                    // Sorted Point
                    arr.steps.addDoneStep(arr.getElementAt(outPos));

                    outPos++;
                }

            }

            //System.out.print(outPos + " ");
        }

    }

    public void sort(ElementArray array) {
        sort(array, array.getMax());
    }


    @Override
    public String toString() {
        return "Bucket";
    }
}
