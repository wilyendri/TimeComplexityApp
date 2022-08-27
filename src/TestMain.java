import timeManager.TimeCalculator;

public class TestMain {
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,4,5,6,7,8,9};
        TimeCalculator searchTime = new TimeCalculator(array);
        System.out.println(searchTime.calculateLinearSearch(9));

    }
}
