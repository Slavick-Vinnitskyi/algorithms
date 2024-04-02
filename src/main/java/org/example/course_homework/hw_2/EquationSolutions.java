package org.example.course_homework.hw_2;

import java.util.Scanner;

/**
 * Знайдіть всі цілі рішення рівняння (ax^3 + bx^2 + cx + d) / (x - e) = 0
 * на відрізку [0,1000] та виведіть їх кількість
 * <p>
 * На вхід подаються 5 чисел:
 * <p>
 * a b c d e
 * Constraints
 * <p>
 * -2^30 <= a, b, c, d, e <= 2^30
 * Output Format
 * <p>
 * Кількість рішень рівняння на відрізку від 0 до 1000
 */
public class EquationSolutions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();
        long c = scanner.nextLong();
        long d = scanner.nextLong();
        long e = scanner.nextLong();
        scanner.close();

        //(ax^3 + bx^2 + cx + d) / (x - e) = 0

        int count = 0;
        for (int x = 0; x <= 1000; x++) {
            if (x == e) {
                continue;
            }
            if ((a * Math.pow(x, 3) + b * Math.pow(x, 2) + c * x + d) == 0) {
                count++;
            }
        }
        System.out.println(count);
    }
}
