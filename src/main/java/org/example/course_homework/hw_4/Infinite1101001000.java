package org.example.course_homework.hw_4;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Let's consider an infinite sequence of digits constructed of ascending powers of 10 written one after another.
 * Here is the beginning of the sequence: 110100100010000...
 * You are to find out what digit is located at the definite position of the sequence.
 * <p>
 * Input Format
 * <p>
 * There is the only integer N in the first line.
 * The i-th of N left lines contains the integer Ki — the number of position in the sequence.
 */
public class Infinite1101001000 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        long[] indexes = new long[size];
        for (int j = 0; j < size; j++) {
            indexes[j] = scanner.nextLong() - 1;
        }

        Set<Long> indexesOf1 = new HashSet<>();
        int increment = 0;
        for (long i = 0; i < Integer.MAX_VALUE; i += increment) {
            indexesOf1.add(i);
            increment += 1;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (long index : indexes) {
            int append = indexesOf1.contains(index) ? 1 : 0;
            stringBuilder.append(append);
            stringBuilder.append(" ");
        }
        System.out.println(stringBuilder);
    }
}
