package com.rrybalkin.math.sort.impl;

import com.rrybalkin.math.sort.SortMethod;

/**
 * Created by Roman Rybalkin
 * 09.10.15
 */
public class InsertionSort implements SortMethod {

    @Override
    public String getMethodInfo() {
        return "Insertion";
    }

    @Override
    public int[] sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int j = i;
            int tmp = a[i];
            while (j > 0 && a[j - 1] > tmp) {
                a[j] = a[j - 1];
                j = j - 1;
            }
            a[j] = tmp;
        }

        return a;
    }
}
