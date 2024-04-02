package org.example.course_homework.hw_2;

import java.util.Scanner;

/**
 * Нехай A та B – додатні цілі числа.
 * Число A краще ніж число B, якщо сума квадратів цифр числа A більша, ніж сума квадратів цифр числа B.
 * У випадку однакової суми квадратів, кращим вважається число з меншим значенням.
 *
 * Знайдіть найкраще просте число на відрізку [L, R] включно.
 *
 * Input Format
 *
 * Два цілі числа L та R
 *
 * Constraints
 *
 * 2 ≤ L ≤ R ≤ 50_000
 *
 * Output Format
 *
 * Одне ціле число
 *
 * Sample Input 0
 *
 * 15 30
 * Sample Output 0
 *
 * 29
 */
public class BestNumber {
    public static void main(String[] args) {
        int start, end;
        Scanner scanner = new Scanner(System.in);
        start = scanner.nextInt();
        end = scanner.nextInt();
        scanner.close();
        long quadraticSum = 0;
        int number = -1;
        for (int i = end; i >= start; i--) {
            if (isPrime(i)) {
                long quadratic = calculateQuadraticSum(i);
                if (quadratic > quadraticSum) {
                    quadraticSum = quadratic;
                    number = i;
                }
            }
        }
        System.out.println(number);
    }

    private static long calculateQuadraticSum(int i) {
        int sum = 0;
        int digits = (int) Math.log10(i) + 1;
        for (int j = 0; j < digits; j++) {
            int digit = i % 10;
            sum += digit * digit;
            i /= 10;
        }
        return sum;
    }

    private static boolean isPrime(int i) {
        for (int j = 2; j * j < i; j++) {
            if (i % j == 0) {
                return false;
            }
        }
        return true;
    }
}
