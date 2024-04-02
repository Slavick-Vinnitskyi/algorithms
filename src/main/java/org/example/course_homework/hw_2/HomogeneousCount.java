package org.example.course_homework.hw_2;

import java.util.Scanner;

/**
 * Однорідні числа
 * Problem
 * Submissions
 * Додатнє ціле число вважається однорідним, якщо всі його цифри рівні.
 * Наприклад, 222 є однорідним, тоді як 223 не є.
 * Задані два додатніх цілих числа A і B. Визначте кількість однорідних цілих чисел між ними включно.
 *
 * Input Format
 *
 * Два цілих числа
 *
 * Constraints
 *
 * 1 <= A <= B <= 10^12
 *
 * Output Format
 *
 * Кількість однорідних цілих чисел між A і B включно.
 *
 * Sample Input 0
 *
 * 75 300
 * Sample Output 0
 *
 * 5
 * Sample Input 1
 *
 * 1 8
 * Sample Output 1
 *
 * 8
 */
public class HomogeneousCount {
    public static void main(String[] args) {
        long start, end;
        Scanner scanner = new Scanner(System.in);
        start = scanner.nextLong();
        end = scanner.nextLong();
        scanner.close();

        long increment = 1;
        long homogeneousCount = 0;

        for (long i = start; i <= end; i += increment) {
            if (homogeneousCount != 0 || isHomogeneous(i)) {
                homogeneousCount++;

                int currentNumberLength = getLength(i);

                boolean invalidIncrement = currentNumberLength != getLength(increment);
                if (invalidIncrement) {
                    increment = calculateDivisor(i);
                } else {
                    boolean lastNumberInFactor = currentNumberLength != getLength(i + 1);
                    if (lastNumberInFactor) {
                        i++;
                    }
                }
            }
        }

        System.out.println(homogeneousCount);
    }

    private static boolean isHomogeneous(long i) {
        return i % calculateDivisor(i) == 0;
    }

    private static long calculateDivisor(long number) {
        int length = getLength(number);
        int base = 1;
        long result = 0;
        long multiplier = (long) Math.pow(10, length);
        while (multiplier != 0) {
            result += base * multiplier;
            multiplier /= 10;
        }
        return result;
    }

    private static int getLength(long firstHomogeneous) {
        return (int) Math.log10(firstHomogeneous);
    }
}

