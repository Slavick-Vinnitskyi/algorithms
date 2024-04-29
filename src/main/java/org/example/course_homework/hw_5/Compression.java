package org.example.course_homework.hw_5;

import java.util.Scanner;

/**
 * You are given a string consisting of uppercase letters A-Z. Your task is to write a function that
 * performs Run-length encoding (RLE) on the input string and returns a compressed version of the string.
 * Input Format
 * <p>
 * A single line containing the string, which may include uppercase letters (A-Z)
 * <p>
 * Constraints
 * <p>
 * The input string only contains uppercase letters (A-Z) The input string has a maximum length of 10000 characters.
 * <p>
 * Output Format
 * <p>
 * A single line containing the compressed version of the string according to the RLE algorithm.
 * <p>
 * Sample Input 0
 * <p>
 * AABBBCCCC
 * Sample Output 0
 * <p>
 * A2B3C4
 */
public class Compression {
    private static final char END_SYMBOL = 'e';
    private static StringBuilder RESULT = new StringBuilder();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine() + END_SYMBOL;
        scanner.close();
        int left = 0;
        int right = 0;
        while (right < s.length()) {
            if (s.charAt(right) != s.charAt(left) || s.charAt(right) == END_SYMBOL) {
                RESULT.append(s.charAt(left));
                RESULT.append(right - left);
                left = right;
            }
            right++;
        }

        System.out.println(RESULT);
    }
}
