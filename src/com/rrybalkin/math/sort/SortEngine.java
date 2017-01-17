package com.rrybalkin.math.sort;

import com.rrybalkin.math.sort.impl.*;

import java.util.*;

/**
 * Created by Roman Rybalkin
 * 09.10.15
 */
public class SortEngine {
    private static List<SortMethod> sortMethods;
    static {
        sortMethods = new ArrayList<>();
        sortMethods.add(new BubbleSort());
        sortMethods.add(new SelectionSort());
        sortMethods.add(new InsertionSort());
        sortMethods.add(new ArraysSort());
        sortMethods.add(new HeapSort());
        sortMethods.add(new MergeSort());
        sortMethods.add(new QuickSort());
        sortMethods.add(new FlashSort());
        sortMethods.add(new RadixSort());
    }

    public static void main(String... args) {
        // TODO use JMH instead of custom benchmarks
        final Benchmark benchmark = new Benchmark();
        final int[] array = generateRandomArray(1_00_000, 1_000_000);
        System.out.println("The array of " + array.length + " numbers is going to be sorted");
        final int[] etalonArray = getSortedArray(array);
        sortMethods.forEach(sortMethod -> {
            int[] arrayClone = array.clone();
            benchmark.start();
            int[] sortedArray = sortMethod.sort(arrayClone);
            long executionTime = benchmark.stop();
            boolean valid = Arrays.equals(etalonArray, sortedArray);
            System.out.println(sortMethod.getMethodInfo() + ": " + executionTime + " ms, " +
                    (valid ? "correct" : "incorrect"));
        });
    }

    private static int[] generateRandomArray(int size, int limit) {
        int[] a = new int[size];
        Random rand = new Random();
        int sign;
        for (int i = 0; i < size; i++) {
            sign = rand.nextInt(2) == 0 ? -1 : 1;
            a[i] = sign * rand.nextInt(limit);
        }

        return a;
    }

    private static int[] getSortedArray(int[] array) {
        int[] sortedArray = array.clone();
        Arrays.sort(sortedArray);
        return sortedArray;
    }


    private static class Benchmark {
        private long startTime;

        public long start() {
            startTime = new Date().getTime();
            return startTime;
        }

        public long stop() {
            return new Date().getTime() - startTime;
        }
    }
}
