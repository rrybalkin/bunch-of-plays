package com.rrybalkin.math.sort.impl;

import com.rrybalkin.math.sort.SortMethod;

/**
 * Created by Roman Rybalkin
 * 09.10.15
 */
public class MergeSort implements SortMethod {

    @Override
    public String getMethodInfo() {
        return "Merge";
    }

    @Override
    public int[] sort(int[] a) {
        splitAndSortArray(a, 0, a.length);
        return a;
    }

    private static void mergeAndSortArrays(int[] a, int start, int end) {
        int length = end - start;
        int middle = length / 2;
        int[] c = new int[length];
        int cntA = 0;
        int cntB = 0;
        for (int i = 0; i < length; i++) {
            if (cntA == middle) {
                c[i] = a[start + middle + cntB];
                cntB++;
                continue;
            }
            if (cntB == length - middle) {
                c[i] = a[start + cntA];
                cntA++;
                continue;
            }
            if (a[start + cntA] <= a[start + middle + cntB]) {
                c[i] = a[start + cntA];
                cntA++;
            } else {
                c[i] = a[start + middle + cntB];
                cntB++;
            }
        }
        System.arraycopy(c, 0, a, start, c.length);
    }

    private void splitAndSortArray(int[] a, int start, int end) {
        if (end - start <= 1)
            return;
        int middle = (end - start) / 2;
        splitAndSortArray(a, start, start + middle);
        splitAndSortArray(a, start + middle, end);
        mergeAndSortArrays(a, start, end);
    }
}
