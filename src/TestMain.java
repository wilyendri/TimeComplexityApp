import java.util.Arrays;

public class TestMain {
    public static void main(String[] args) {
        int[] sortingAlgo = new int[]{3,8,6,7};
        SortingAlgo.quickSort(sortingAlgo, 0, sortingAlgo.length-1);
        System.out.println("After sorting: " + Arrays.toString(sortingAlgo));
    }
}
