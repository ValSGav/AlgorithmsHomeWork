package homeWork1;

import homeWork1.HeapSort;

public class Main {
    public static void main(String[] args) {
        int[] arr = {4, 6, 1, 9, 2, 3, 22, 5};
        HeapSort sorter = new HeapSort();
        sorter.printArray(arr);
        sorter.sort(arr);
        sorter.printArray(arr);
    }
}
