package com.rrybalkin.tasks;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Roman Rybalkin
 * 05.10.16
 */
public class CyclicShift {

    public static void main(String[] args) {
        System.out.println("abcd >> " + shiftTo("abcd", 2));
    }

    public static String shiftTo(String s, int shift) {
        Deque<Character> characterQueue = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            characterQueue.addLast(c);
        }
        int iteration = 0;
        while (iteration++ < shift) {
            characterQueue.offer(characterQueue.pop());
        }
        StringBuilder sb = new StringBuilder(s.length());
        for (Character c : characterQueue) {
            sb.append(c);
        }
        return sb.toString();
    }
}
