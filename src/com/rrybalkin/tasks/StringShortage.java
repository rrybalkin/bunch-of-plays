package com.rrybalkin.tasks;

/**
 * Created by Roman Rybalkin
 * 05.10.16
 */
public class StringShortage {

    public static void main(String[] args) {
        String sample = "aabcccccaaa";
        System.out.println(sample + " -> " + shortage(sample));
    }

    public static String shortage(String source) {
        if (source == null || source.length() == 0) {
            return source;
        }
        StringBuilder shorten = new StringBuilder(source.length() / 2);
        char prevS = source.charAt(0);
        int n = 1;
        for (int i = 1; i < source.length(); i++) {
            char s = source.charAt(i);
            if (prevS == s) {
                n++;
            } else {
                shorten.append(prevS).append(n);
                prevS = s;
                n = 1;
            }
        }
        // process last symbols
        shorten.append(prevS).append(n);
        return shorten.length() > source.length() ? source : shorten.toString();
    }
}
