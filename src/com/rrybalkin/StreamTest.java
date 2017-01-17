package com.rrybalkin;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Roman Rybalkin
 * 13.10.16
 */
public class StreamTest {

    public static void main(String[] args) {
        System.out.println(ForkJoinPool.getCommonPoolParallelism());
        Stream.of(1, 2, 3);
        int sum = Stream.of("1", "2", "3", "4").unordered().mapToInt(Integer::parseInt).reduce((i1, i2) -> i1 * i2).orElse(0);
        int sum1 = Stream.of("1", "2", "3", "4").peek(System.out::println)
                .flatMapToInt(s -> Stream.<Integer>of(Integer.parseInt(s), 1).mapToInt(i -> i)).sequential().min().getAsInt();

        System.out.println(sum);
        System.out.println(sum1);

        String s1 = "abcdEFGH";
        String s2 = "efghABCD";

        String newS1 = Stream.of(s1).
                flatMap(s -> Stream.of(s.split(""))).
                map(String::toLowerCase).
                sorted((ss1, ss2) -> ss1.compareTo(ss2)).
                collect(Collectors.joining());
        String newS2 = Stream.of(s2).
                flatMap(s -> Stream.of(s.split(""))).
                map(String::toLowerCase).
                sorted().
                collect(Collectors.joining());
        System.out.println(newS1 + " " + newS2);
        System.out.println(newS1.equals(newS2));

        List<String> collection = Arrays.asList("a1", "a4", "a3", "a2", "a1", "a4");
        System.out.println(collection.stream().max(String::compareTo).get());
        System.out.println(collection.stream().sorted((o1, o2) -> o2.compareTo(o1)).distinct().collect(Collectors.toList()));
    }
}
