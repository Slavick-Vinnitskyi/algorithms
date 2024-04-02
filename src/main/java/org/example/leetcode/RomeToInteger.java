package org.example.leetcode;

import java.util.Map;

public class RomeToInteger {
    public static void main(String[] args) {
        System.out.println(romanToInt("VIII"));
    }

    private static final Map<String, Integer> rome = Map.of(
            "I", 1,
            "V", 5,
            "X", 10,
            "L", 50,
            "C", 100,
            "D", 500,
            "M", 1000);

    public static int romanToInt(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int currentInt1 = 0;
            if (i + 1 < s.length() && getCurrent(s, i) < getCurrent(s, i + 1)) {
                currentInt1 -= getCurrent(s, i);
            } else currentInt1 += getCurrent(s, i);

            sum += currentInt1;
        }
        return sum;
    }

    private static int getCurrent(String s, int i) {
        return rome.get(String.valueOf(s.charAt(i)));
    }

}
