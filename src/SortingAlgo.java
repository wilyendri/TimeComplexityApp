import java.util.concurrent.TimeoutException;

/**This class holds different sorting algorithm. It was made final to avoid extending this class.
@author Wilyendri Duran*/

public final class SortingAlgo{
    /**Private constructor so it cannot be instantiated*/
    private SortingAlgo(){}


    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void mergeSort(int[] arrayInput){
        int midSize = arrayInput.length/2;
        int[] firstHalfArray = new int[midSize];
        int[] secondHalf = new int[arrayInput.length - midSize];

        if(arrayInput.length < 2){
            return;
        }

        for(int i  = 0; i < midSize; i++){
            firstHalfArray[i] = arrayInput[i];

        }

        for(int j = midSize; j < arrayInput.length; j ++){
            secondHalf[j - midSize] = arrayInput[j];

        }

        mergeSort(firstHalfArray);
        mergeSort(secondHalf);
        merge(arrayInput, firstHalfArray, secondHalf);

    }

    private static void merge(int[] array, int[] firstHalf, int[] secondHalf){
        int i = 0, j = 0, k = 0;
        int firstLength = firstHalf.length;
        int secondLength = secondHalf.length;

        while(i < firstLength && j < secondLength){
            if(firstHalf[i] <= secondHalf[j]){
                array[k] = firstHalf[i];
                i++;
            }else{
                array[k] = secondHalf[j];
                j++;
            }
            k++;
        }

        while(i < firstLength){
            array[k] = firstHalf[i];
            i++;
            k++;
        }

        while(j < secondLength){
            array[k] = secondHalf[j];
            j++;
            k++;
        }
    }

    /** Sort array by Bubble Sort
     * @param inputArray to be sorted
     * @throws TimeoutException if the sorting takes longer than 10seg*/
    static void bubbleSort(int[] inputArray) throws TimeoutException {
        long initTime = System.currentTimeMillis();
        long endTime = initTime + 10 * 1000;
        for(int i  = 0; i < inputArray.length && System.currentTimeMillis() < endTime; i++){
            for(int j = i+1; j < inputArray.length; j++){
                if(inputArray[i] > inputArray[j]){
                    swap(inputArray, i, j);
                }
            }
        }
        if(System.currentTimeMillis() >= endTime){
            throw new TimeoutException();
        }

    }

    /** Sort array by Insertion Sort
     * @param inputArray to be sorted
     * @throws TimeoutException if the sorting takes longer than 10seg*/
    static void insertionSort(int[] inputArray) throws TimeoutException {
        long initTime = System.currentTimeMillis();
        long endTime = initTime + 10 * 1000;
        for(int i  = 1; i < inputArray.length && System.currentTimeMillis() < endTime; i++){
            int currentElement = inputArray[i];
            int k;
            for(k = i -1; k >= 0 && inputArray[k] > currentElement; k--){
                inputArray[k+1] = inputArray[k];
            }

            inputArray[k+1] = currentElement;
        }

        if(System.currentTimeMillis() >= endTime){
            throw new TimeoutException();
        }
    }

    static void quickSort(int[] inputArray, int low, int high) throws StackOverflowError{
        if(low < high){
            int pivot = partition(inputArray, low, high);
            quickSort(inputArray, low, pivot-1);
            quickSort(inputArray, pivot+1, high);
        }
    }
    //testing commit2
    private static int partition(int[] array, int low, int high){
        int pivot = array[high];
        int j = (low -1);
        for(int i = low; i <= high-1; i++){
            if(array[i] < pivot){
                j++;
                swap(array, i, j);
            }

        }

        swap(array, j +1, high);

        return j + 1;

    }
}
