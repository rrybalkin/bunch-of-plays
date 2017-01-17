package com.rrybalkin.math.sort.impl;

import com.rrybalkin.math.sort.SortMethod;

import static com.rrybalkin.math.sort.SortMethod.swap;

/**
 * Created by Roman Rybalkin
 * 09.10.15
 */
public class SelectionSort implements SortMethod {

    @Override
    public String getMethodInfo() {
        return "Selection";
    }

    @Override
    public int[] sort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[i]) {
                    swap(a, i, j);
                }
            }
        }

        return a;
    }
}
