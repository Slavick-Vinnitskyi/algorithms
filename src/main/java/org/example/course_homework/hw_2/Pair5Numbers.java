package org.example.course_homework.hw_2;

import java.util.Scanner;

/**
 * Ділення
 * Напишіть програму, яка знаходить та виводить на екран усі пари 5-значних чисел,
 * які використовують цифри від 0 до 9 один раз, так що перше число, поділене на друге, дорівнює цілому числу N.
 * Тобто, rysni / puzda = N де кожна літера позначає окрему цифру.
 * Допускається, що перша цифра одного з чисел дорівнює нулю.
 *
 * Input Format
 *
 * На вхід подається ціле число N
 *
 * Constraints
 *
 * 2 ≤ N ≤ 79
 *
 * Output Format
 *
 * Виведіть на екран всі пари в форматі:
 *
 * xxxxx / xxxxx = N
 * відсортовані за зростанням чисельника і, звичайно, знаменника. Якщо відповіді не існує виведіть для n
 *
 * There are no solutions for {n}.
 * Sample Input 0
 *
 * 3
 * Sample Output 0
 *
 * 17469 / 05823 = 3
 * 17496 / 05832 = 3
 * 50382 / 16794 = 3
 * 53082 / 17694 = 3
 * 61749 / 20583 = 3
 * 69174 / 23058 = 3
 * 91746 / 30582 = 3
 * 96174 / 32058 = 3
 */
public class Pair5Numbers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int divisor = scanner.nextInt();
        scanner.close();

        long start = System.currentTimeMillis();
        var uniquePairs = generateUniqueInt(divisor);
        long end = System.currentTimeMillis();
        System.out.println("TIME : " + (end - start));

        if (!uniquePairs) {
            System.out.printf("There are no solutions for %s.", divisor);
        }
    }

    private static boolean generateUniqueInt(int divisor) {
        boolean res = false;
        for (int i = 1; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                if (anyEqual(i, j)) {
                    continue;
                }
                for (int k = 0; k <= 9; k++) {
                    if (anyEqual(k, i, j)) {
                        continue;
                    }
                    for (int l = 0; l <= 9; l++) {
                        if (anyEqual(l, k, j, i)) {
                            continue;
                        }
                        for (int m = 0; m <= 9; m++) {
                            if (anyEqual(m, l, k, j, i)) {
                                continue;
                            }
                            for (int n = 0; n < i; n++) {
                                if (anyEqual(n, m, l, k, j, i)) {
                                    continue;
                                }
                                for (int r = 0; r <= 9; r++) {
                                    if (anyEqual(r, n, m, l, k, j, i)) {
                                        continue;
                                    }
                                    for (int o = 0; o <= 9; o++) {
                                        if (anyEqual(o, r, n, m, l, k, j, i)) {
                                            continue;
                                        }
                                        for (int p = 0; p <= 9; p++) {
                                            if (anyEqual(p, o, r, n, m, l, k, j, i)) {
                                                continue;
                                            }
                                            for (int s = 0; s <= 9; s++) {
                                                if (anyEqual(s, p, o, r, n, m, l, k, j, i)) {
                                                    continue;
                                                }

                                                Integer left_int = create(i, j, k, l, m);
                                                Integer right_int = create(n, r, o, p, s);

                                                if ((left_int > right_int) && (left_int % right_int == 0) && (left_int / right_int == divisor)) {
                                                    System.out.printf("%05d / %05d = %s%n", left_int, right_int, divisor);
                                                    res = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    private static boolean anyEqual(int el, int ind) {
        return el == ind;
    }

    private static boolean anyEqual(int el, int ind, int ind1) {
        return el == ind || el == ind1;
    }

    private static boolean anyEqual(int el, int ind, int ind1, int ind2) {
        return el == ind || el == ind1 || el == ind2;
    }

    private static boolean anyEqual(int el, int ind, int ind1, int ind2, int ind3) {
        return el == ind || el == ind1 || el == ind2 || el == ind3;
    }

    private static boolean anyEqual(int el, int ind, int ind1, int ind2, int ind3, int ind4) {
        return el == ind || el == ind1 || el == ind2 || el == ind3 || el == ind4;
    }

    private static boolean anyEqual(int el, int ind, int ind1, int ind2, int ind3, int ind4, int ind5) {
        return el == ind || el == ind1 || el == ind2 || el == ind3 || el == ind4 || el == ind5;
    }

    private static boolean anyEqual(int el, int ind, int ind1, int ind2, int ind3, int ind4, int ind5, int ind6) {
        return el == ind || el == ind1 || el == ind2 || el == ind3 || el == ind4 || el == ind5 || el == ind6;
    }

    private static boolean anyEqual(int el, int ind, int ind1, int ind2, int ind3, int ind4, int ind5, int ind6, int ind7) {
        return el == ind || el == ind1 || el == ind2 || el == ind3 || el == ind4 || el == ind5 || el == ind6 || el == ind7;
    }

    private static boolean anyEqual(int el, int ind, int ind1, int ind2, int ind3, int ind4, int ind5, int ind6, int ind7, int ind8) {
        return el == ind || el == ind1 || el == ind2 || el == ind3 || el == ind4 || el == ind5 || el == ind6 || el == ind7 || el == ind8;
    }

    private static int create(int... digits) {
        return digits[0] * 10000 + digits[1] * 1000 + digits[2] * 100 + digits[3] * 10 + digits[4];
    }
}