package org.example.course_homework.hw_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;

/**
 * Given a string s, consists of English letters, digits, symbols and spaces,
 * find the length of the longest substring without repeating characters.
 * Output Format
 * <p>
 * The length of the longest substring without repeating characters.
 * <p>
 * Sample Input 0
 * <p>
 * abcabcbb
 * Sample Output 0
 * <p>
 * 3
 */
public class Unique {

    private static final Map<Character, Integer> OCCURRENCES = new HashMap<>(26);

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        String line = scanner.nextLine();

        char[] codePoints = new char[line.length()];
        for (int i = 0; i < line.length(); i++) {
            codePoints[i] = line.charAt(i);
        }


        int maxLen = -1;
        int left = 0;

        for (int right = 0; right < codePoints.length; right++) {
            add(codePoints[right]);
            while (left <= right && !allUnique()) {
                remove(codePoints[left]);
                left++;
            }

            int currentLen = right - left + 1;
            if (maxLen < currentLen && allUnique()) {
                maxLen = currentLen;
            }
        }
        System.out.println(maxLen);
    }

    private static void add(Character codePoint) {
        OCCURRENCES.putIfAbsent(codePoint, 0);
        OCCURRENCES.compute(codePoint, increment());
    }

    private static void remove(Character codePoint) {
        OCCURRENCES.compute(codePoint, decrement());
    }

    private static boolean allUnique() {
        return OCCURRENCES.values().stream().allMatch(v -> v <= 1);
    }

    private static BiFunction<Character, Integer, Integer> increment() {
        return (number, count) -> count + 1;
    }

    private static BiFunction<Character, Integer, Integer> decrement() {
        return (number, count) -> count - 1;
    }
}
