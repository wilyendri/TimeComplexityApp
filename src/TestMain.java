public class TestMain {
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,4,5,6,7,8,9};
        SearchAlgo searchAlgo = new SearchAlgo(array);
        System.out.println(searchAlgo.binarySearch(9));
    }
}
