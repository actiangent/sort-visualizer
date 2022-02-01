package sortvisualiser.algorithm;

import sortvisualiser.SortArray;

public class SortAlgorithmSelector {

    SortAlgorithm algorithms = new SortAlgorithm();

    public void selectAlgorithm(String algorithm, SortArray array) {
        switch (algorithm) {
            case "Bubble Sort" -> algorithms.BubbleSort(array, 10);
            case "Insertion Sort" -> algorithms.InsertionSort(array, 10);
            case "Pancake Sort" -> algorithms.PancakeSort(array, 10);
            case "Counting Sort" -> algorithms.CountingSort(array, 25);
            case "Merge Sort" -> algorithms.MergeSort(array, 0, array.getArrayLength() - 1, 20);
            case "Quick Sort" -> algorithms.QuickSort(array, 0, array.getArrayLength() - 1, 20);
            case "Radix Sort" -> algorithms.RadixSort(array, 20);
            case "Selection Sort" -> algorithms.SelectionSort(array, 10);
        }
    }
}
