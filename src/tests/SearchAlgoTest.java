package tests;

import algorithms.SearchAlgo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SearchAlgoTest {
    int[] array;
    SearchAlgo searchAlgo;

    @BeforeEach
    void setUp() {
        array = new int[]{1,2,3,4,5,6};
        searchAlgo = new SearchAlgo(array);

    }

    @Test
    void binarySearch() {
        assertEquals(searchAlgo.binarySearch(6), Arrays.binarySearch(array, 6));
    }

    @Test
    void linearSearch() {
        assertEquals(searchAlgo.linearSearch(3), Arrays.binarySearch(array, 3));
    }
}