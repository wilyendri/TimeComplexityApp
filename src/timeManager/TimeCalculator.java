package timeManager;

import algorithms.SearchAlgo;
import algorithms.SortingAlgo;

import java.util.Arrays;
import java.util.concurrent.TimeoutException;

public class TimeCalculator extends SearchAlgo {
    private final int[] array;

    public TimeCalculator(int[] array){
        super(array);
        this.array = Arrays.copyOf(array, array.length);
    }

    public long calculateMergeTime(){
        long initTime = System.currentTimeMillis();
        SortingAlgo.mergeSort(array);
        long endTime = System.currentTimeMillis();
        return endTime - initTime;
    }

    public long calculateBubbleTime() throws TimeoutException {
        long initTime = System.currentTimeMillis();
        SortingAlgo.bubbleSort(array);
        long endTime = System.currentTimeMillis();
        return endTime - initTime;
    }

    public long calculateInsertionTime() throws TimeoutException {
        long initTime = System.currentTimeMillis();
        SortingAlgo.insertionSort(array);
        long endTime = System.currentTimeMillis();
        return endTime - initTime;
    }

    public long calculateQuickTime() throws StackOverflowError, java.lang.OutOfMemoryError{
        long initTime = System.currentTimeMillis();
        SortingAlgo.quickSort(array, 0, array.length-1);
        long endTime = System.currentTimeMillis();
        return endTime - initTime;
    }

    public long calculateBinarySearch(int elementToSearch){
        long initTime = System.currentTimeMillis();
        super.binarySearch(elementToSearch);
        long endTime = System.currentTimeMillis();
        return endTime - initTime;
    }

    public long calculateLinearSearch(int elementToSearch){
        long initTime = System.currentTimeMillis();
        super.linearSearch(elementToSearch);
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

    private void calculateTime(){

    }
}
