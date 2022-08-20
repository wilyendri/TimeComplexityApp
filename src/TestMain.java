import java.util.Arrays;

public class TestMain {
    public static void main(String[] args) {
        int[] sortingAlgo = new int[]{3,8,6,7,4,2,4,5,6,3,2,4,1,90,12,76,11,89,50,21};
        TimeCalculator timeCalculator = new TimeCalculator(sortingAlgo);


        System.out.println("After sorting: " + timeCalculator.calculateMergeTime() + "ms");
        System.out.println(timeCalculator);
    }
}
