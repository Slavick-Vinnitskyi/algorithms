package org.example.course_homework.hw_7;

import java.util.Scanner;

/**
 * Given an encoded string, return its decoded string.
 * <p>
 * The encoding rule is: k[encoded_string],
 * where the encoded_string inside the square brackets is being repeated exactly k times.
 * Note that k is guaranteed to be a positive integer.
 * <p>
 * You may assume that the input string is always valid; there are no extra white spaces,
 * square brackets are well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits
 * and that digits are only for those repeat numbers, k.
 * For example, there will not be input like 3a or 2[4]
 * <p>
 * Input Format
 * Encoded string
 * <p>
 * Constraints
 * 1 <= s.length <= 30
 * s consists of lowercase English letters, digits, and square brackets '[]'
 * <p>
 * s is guaranteed to be a valid input
 * <p>
 * All the integers in s are in the range [1, 300]
 * <p>
 * The test cases are generated so that the length of the output will never exceed 10^5
 * <p>
 * Sample Input 0
 * <p>
 * 3[a]2[bc]
 * Sample Output 0
 * <p>
 * aaabcbc
 */
public class String_ {
    private static final Character START = '[';
    private static final Character END = ']';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();

        StringBuilder result = new StringBuilder();

        int i = 0;
        while (i < line.length()) {
            var number = new StringBuilder();
            var string = new StringBuilder();
            i = appendChar(line, i, START, number);
            i = appendChar(line, i, END, string);
            appendResult(result, number, string);
        }

        System.out.println(result);
    }

    private static int appendChar(String source, int index, Character stopChar, StringBuilder target) {
        while (source.charAt(index) != stopChar) {
            target.append(source.charAt(index));
            index++;
        }
        index++;
        return index;
    }

    private static void appendResult(StringBuilder result, StringBuilder number, StringBuilder string) {
        int count = Integer.parseInt(number.toString());
        var repeated = String.valueOf(string).repeat(count);
        result.append(repeated);
    }
}
