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
        System.out.println();
        for (int i=0 ; i<bucket.length; i++) {

            if (bucket[i] != 0) {
                for (int j=0; j<bucket[i]; j++) {
                    arr.swap((bucketElement[i][j]).getIndex(), outPos);
                    System.out.println(bucketElement[i][j].getIndex() + " "+ outPos);
                    outPos++;
                }

            }

            //System.out.print(outPos + " ");
        }

        for (int i=0; i< arr.length(); i++) {
            arr.moveFromBufferArray(i, -maxVal.getValue() + arr.getElementAt(i).getValue());
        }

        System.out.println();
        for (int e: bucket) {
            System.out.print(e+" ");
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
