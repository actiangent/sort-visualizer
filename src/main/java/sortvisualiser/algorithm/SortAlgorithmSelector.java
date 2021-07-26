package sortvisualiser.algorithm;

import sortvisualiser.SortArray;

public class SortAlgorithmSelector {

    SortAlgorithm algorithms = new SortAlgorithm();

    public void selectAlgorithm(String algorithm, SortArray array) {
        switch (algorithm) {
            case "Bubble Sort":
                algorithms.BubbleSort(array, 10);
                break;

            case "Insertion Sort":
                algorithms.InsertionSort(array, 10);
                break;

            case "Pancake Sort":
                algorithms.PancakeSort(array, 10);
                break;

            case "Counting Sort":
                algorithms.CountingSort(array, 25);
                break;

            case "Merge Sort":
                algorithms.MergeSort(array, 0, array.getArrayLength() - 1, 20);
                break;

            case "Quick Sort":
                algorithms.QuickSort(array, 0, array.getArrayLength() - 1, 20);
                break;

        }
    }
}
