package com.rrybalkin.math.sort.impl;

import com.rrybalkin.math.sort.SortMethod;

import static com.rrybalkin.math.sort.SortMethod.swap;

/**
 * Created by Roman Rybalkin
 * 09.10.15
 */
public class QuickSort implements SortMethod {

    @Override
    public String getMethodInfo() {
        return "Quick";
    }

    @Override
    public int[] sort(int[] a) {
        divArray(a, 0, a.length - 1);
        return a;
    }

    private static void divArray(int[] a, int start, int end) {
        int i = start, j = end, medium = a[(start + end) / 2];
        do {
            while (a[i] < medium)
                ++i;
            while (a[j] > medium)
                --j;
            if (i <= j) {
                swap(a, i, j);
                i++;
                j--;
            }
        } while (i < j);

        if (start < j)
            divArray(a, start, j);
        if (i < end)
            divArray(a, i, end);
    }
}
