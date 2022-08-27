package algorithms;

public class SearchAlgo implements SearchAlgoInterface{
    private int[] array;

    public SearchAlgo(int[] array){
        this.array = array;
    }

    @Override
    public int binarySearch(int elementToSearch){
        int start = 0;
        int end = array.length-1;

        while(start <= end){
            int mid = (start + end) / 2;
            if(elementToSearch == array[mid]){
                return mid;
            } else if (elementToSearch < array[mid]) {
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }

        return -1;
    }

    @Override
    public int linearSearch(int elementToSearch) {
        for(int i = 0; i < array.length; i++){
            if(array[i] == elementToSearch){
                return i;
            }
        }
        return -1;
    }
}
