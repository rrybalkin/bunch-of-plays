package com.rrybalkin.math.sort.impl;

import com.rrybalkin.math.sort.SortMethod;

/**
 * Created by Roman Rybalkin
 * 09.10.15
 */
public class HeapSort implements SortMethod {

    @Override
    public String getMethodInfo() {
        return "Heap";
    }

    @Override
    public int[] sort(int[] a) {
        int n = a.length;
        heapMake(a);
        while (n > 0) {
            SortMethod.swap(a, 0, n - 1);
            n--;
            heapify(a, n, 0);
        }
        return a;
    }

    private void heapify(int[] a, int size, int pos) {
        while (2 * pos + 1 < size) {
            int t = 2 * pos + 1;
            if (2 * pos + 2 < size && a[2 * pos + 1] < a[2 * pos + 2]) {
                t = 2 * pos + 2;
            }
            if (a[pos] < a[t]) {
                SortMethod.swap(a, pos, t);
                pos = t;
            } else {
                break;
            }
        }
    }

    private void heapMake(int[] a) {
        int n = a.length;
        for (int i = n - 1; i >= 0; i--) {
            heapify(a, n, i);
        }
    }
}
