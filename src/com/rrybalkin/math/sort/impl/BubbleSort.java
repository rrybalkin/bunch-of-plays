package com.rrybalkin.math.sort.impl;

import com.rrybalkin.math.sort.SortMethod;

import static com.rrybalkin.math.sort.SortMethod.swap;

/**
 * Created by Roman Rybalkin
 * 09.10.15
 */
public class BubbleSort implements SortMethod {

    @Override
    public String getMethodInfo() {
        return "Bubble";
    }

    @Override
    public int[] sort(int[] a) {
        for (int i = a.length - 1; i >= 0; i--) {
            boolean swap = false;
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    swap = true;
                }
            }
            if (!swap) {
                return a;
            }
        }

        return a;
    }
}
