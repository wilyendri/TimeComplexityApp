import java.util.ArrayList;
import java.util.Arrays;

public class TimeCalculator{
    private int[] array;

    public TimeCalculator(int[] array){
        this.array = Arrays.copyOf(array, array.length);
    }

    public long calculateMergeTime(){
        long initTime = System.currentTimeMillis();
        SortingAlgo.mergeSort(array);
        long endTime = System.currentTimeMillis();
        return endTime - initTime;
    }

    public long calculateBubbleTime(){
        long initTime = System.currentTimeMillis();
        SortingAlgo.bubbleSort(array);
        long endTime = System.currentTimeMillis();
        return endTime - initTime;
    }

    public long calculateInsertionTime(){
        long initTime = System.currentTimeMillis();
        SortingAlgo.insertionSort(array);
        long endTime = System.currentTimeMillis();
        return endTime - initTime;
    }

    public long calculateQuickTime(){
        long initTime = System.currentTimeMillis();
        SortingAlgo.quickSort(array, 0, array.length-1);
        long endTime = System.currentTimeMillis();
        return endTime - initTime;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public int[] getArray() {
        return array;
    }
}
