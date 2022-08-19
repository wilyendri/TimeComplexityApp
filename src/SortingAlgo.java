//*This class holds different sorting algorithm*//
public class SortingAlgo {

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void mergeSort(int[] arrayInput){
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

    public static void bubbleSort(int[] inputArray){
        for(int i  = 0; i < inputArray.length; i++){
            for(int j = i+1; j < inputArray.length; j++){
                if(inputArray[i] > inputArray[j]){
                    swap(inputArray, i, j);
                }
            }
        }

    }

    public static void insertionSort(int[] inputArray){
        for(int i  = 1; i < inputArray.length; i++){
            int currentElement = inputArray[i];
            int k;
            for(k = i -1; k >= 0 && inputArray[k] > currentElement; k--){
                inputArray[k+1] = inputArray[k];
            }

            inputArray[k+1] = currentElement;
        }
    }

    public static void quickSort(int[] inputArray, int low, int high){
        if(low < high){
            int pivot = partition(inputArray, low, high);
            quickSort(inputArray, low, pivot-1);
            quickSort(inputArray, pivot+1, high);
        }
    }
    //testing commit
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
