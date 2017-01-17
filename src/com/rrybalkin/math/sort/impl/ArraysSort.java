package com.rrybalkin.math.sort.impl;

import com.rrybalkin.math.sort.SortMethod;

import java.util.Arrays;

/**
 * Created by Roman Rybalkin
 * 09.10.15
 */
public class ArraysSort implements SortMethod {

    @Override
    public String getMethodInfo() {
        return "Arrays.sort";
    }

    @Override
    public int[] sort(int[] array) {
        Arrays.sort(array);
        return array;
    }
}
