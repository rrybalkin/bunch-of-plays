package com.rrybalkin.math.sort;

public interface SortMethod {

    String getMethodInfo();

    int[] sort(int[] array);

    static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
