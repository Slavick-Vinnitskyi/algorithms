package org.example.course_homework.hw_3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;

/**
 * У вас є рядок.
 * Виберіть найдовший можливий фрагмент цього рядка, такий,
 * що жоден символ у ньому не зустрічається частіше, ніж K разів.
 * <p>
 * У першому рядку дані два цілих числа n і k, де n – кількість символів у рядку.
 * У другому рядку n символів – рядок, що складається лише з малих латинських літер.
 */
public class LongestSubstring {

    private static final Map<Integer, Integer> CODEPOINTS_COUNT = new HashMap<>(26);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] codePoints = new int[n];
        String nextLine = scanner.next();
        codePoints = nextLine.codePoints().toArray();
        fillMap();

        solve(codePoints, k);
    }


    private static void solve(int[] codePoints, int k) {
        int left_pointer = 0;
        int resultLen = -1;
        int resultLeft = 0;
        for (int right_pointer = 0; right_pointer < codePoints.length; right_pointer++) {
            add(codePoints[right_pointer]);
            while (left_pointer <= right_pointer && anyHasMoreCount(k)) {
                remove(codePoints[left_pointer]);
                left_pointer++;
            }
            int currentLen = right_pointer - left_pointer + 1;
            if (!anyHasMoreCount(k) && currentLen > resultLen) {
                resultLen = currentLen;
                resultLeft = left_pointer;
            }
        }
        System.out.println(resultLen + " " + (resultLeft + 1));
    }


    private static void remove(int codePoint) {
        CODEPOINTS_COUNT.compute(codePoint, decrement());
    }

    private static boolean anyHasMoreCount(int k) {
        return CODEPOINTS_COUNT.values().stream().anyMatch(v -> v > k);
    }

    private static void add(int codePoint) {
        CODEPOINTS_COUNT.compute(codePoint, increment());
    }

    private static BiFunction<Integer, Integer, Integer> increment() {
        return (number, count) -> count + 1;
    }

    private static BiFunction<Integer, Integer, Integer> decrement() {
        return (number, count) -> count - 1;
    }

    private static void fillMap() {
        int[] staticCodePoints = "abcdefghijklmnopqrstuvwxyz".codePoints().toArray();
        for (var codePoint : staticCodePoints) {
            CODEPOINTS_COUNT.put(codePoint, 0);
        }
    }
}
