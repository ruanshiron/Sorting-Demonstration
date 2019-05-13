package algo;

import element.Element;
import element.ElementArray;

public class Merge implements Algorithm {
    private static Merge ourInstance = new Merge();

    public static Merge getInstance() {
        return ourInstance;
    }

    private Merge() {
    }

    public void sort(ElementArray array) {
        sort(array, 0, array.length() - 1);

        for (Element e: array.getAll()) {
            array.steps.addDoneStep(e);
        }
    }


    private void sort(ElementArray arr,int min,int max){
        if(max-min==0){//only one element.
            //no swap
        }
        else if(max-min==1){//only two elements and swaps them
            if(arr.compare(min, max) < 0)
                arr.swap(min, max);
        }
        else{
            int mid=((int) Math.floor((min+max)/2));//The midpoint

            sort(arr,min,mid);//sort the left side
            sort(arr,mid+1,max);//sort the right side

            merge(arr, min, max, mid);//combines them
        }


    }


    private void merge(ElementArray arr, int min,int max,int mid){
        int i=min;
        while(i<=mid){
            if(arr.compare(i, mid+1) < 0){
                arr.swap(i,mid+1);

                push(arr,mid+1,max);
            }
            i++;
        }

    }

    private void push(ElementArray arr,int s,int e){
        for(int i=s;i<e;i++){
            if(arr.compare(i, i+1)<0) {

                arr.swap(i,i+1);

            }

        }

    }


    @Override
    public String toString() {
        return "Merge";
    }
}
