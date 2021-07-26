package sortvisualiser.algorithm;

import sortvisualiser.SortArray;

class SortAlgorithm {

    void BubbleSort(SortArray array, int delay) {
        array.setDelay(delay);
        int length = array.getArrayLength();
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (array.getValue(j) > array.getValue(j + 1)) {
                    array.swap(j, j + 1, delay, true);
                }
            }
        }
    }

    void InsertionSort(SortArray array, int delay) {
        array.setDelay(delay);
        for (int i = 0; i < array.getArrayLength() - 1; i++) {
            int key = array.getValue(i);
            int j = i - 1;
            while (j >= 0 && array.getValue(j) > key) {
                array.updateSingle(j + 1, array.getValue(j), delay, true);
                j--;
            }
            array.updateSingle(j + 1, key, delay, true);
        }
    }

    void PancakeSort(SortArray array, int delay) {
        array.setDelay(delay);
        for (int i = array.getArrayLength(); i > 1; i--) {
            int maxValueIndex = findMaxValueIndex(array, i);
            if (maxValueIndex != i - 1) {
                flip(array, maxValueIndex, delay);
                flip(array, i - 1, delay);
            }
        }
    }

    void MergeSort(SortArray array, int left, int right, int delay) {
        array.setDelay(delay);

        if (left < right) {
            int middleIndex = (left + right) / 2;

            MergeSort(array, left, middleIndex, delay);
            MergeSort(array, middleIndex + 1, right, delay);
            merge(array, left, middleIndex, right, delay);
        }

    }

    void CountingSort(SortArray array, int delay) {
        array.setDelay(delay);
        int[] result = new int[array.getArrayLength()];
        int[] count = new int[array.getMaxValue() + 1];
        for (int i = 0; i < result.length; ++i) {
            array.updateSingle(i, result[i] = array.getValue(i), delay, false);
            ++count[array.getValue(i)];
        }
        for (int i = 1; i < count.length; ++i)
            count[i] += count[i - 1];
        for (int i = result.length - 1; i > -1; --i)
            array.updateSingle(--count[result[i]], result[i], delay, true);
    }

    void QuickSort(SortArray array, int lowIndex, int highIndex, int delay) {
        array.setDelay(20);
        if (lowIndex < highIndex) {
            int pivotPoint = findPivotPoint(array, lowIndex, highIndex, delay);
            QuickSort(array, lowIndex, pivotPoint - 1, delay);
            QuickSort(array, pivotPoint + 1, highIndex, delay);
        }
    }

    private void flip(SortArray array, int i, int delay) {
        for (int j = 0; j < i; j++, i--) {
            array.swap(i, j, delay, true);
        }
    }

    private int[] getSubArray(SortArray array, int begin, int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = array.getValue(begin + i);
        }
        return arr;
    }

    private void merge(SortArray array, int left, int middle, int right, int delay) {
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        int[] leftArray = getSubArray(array, left, leftSize);
        int[] rightArray = getSubArray(array, middle + 1, rightSize);

        int i = 0, j = 0, k = left;
        while (i < leftSize && j < rightSize) {
            if (leftArray[i] <= rightArray[j]) {
                array.updateSingle(k, leftArray[i], delay, true);
                i++;
            } else {
                array.updateSingle(k, rightArray[j], delay, true);
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            array.updateSingle(k, leftArray[i], delay, true);
            i++;
            k++;
        }

        while (j < rightSize) {
            array.updateSingle(k, rightArray[j], delay, true);
            j++;
            k++;
        }
    }

    private int findPivotPoint(SortArray array, int lowIndex, int highIndex, int delay) {
        int pivotValue = array.getValue(highIndex);
        int i = lowIndex - 1;
        for (int j = lowIndex; j <= highIndex - 1; j++) {
            if (array.getValue(j) <= pivotValue) {
                i++;
                array.swap(i, j, delay, true);
            }
        }
        array.swap(i + 1, highIndex, delay, true);
        return i + 1;
    }

    private int findMaxValueIndex(SortArray array, int upToIndex) {
        int maxIndex = 0;
        for (int i = 0; i < upToIndex; i++) {
            if (array.getValue(i) > array.getValue(maxIndex)) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }


}
