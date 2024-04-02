package org.example.course_homework.hw_2;

import java.math.BigDecimal;
import java.util.Scanner;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * На початку війни в нашому морі було безліч руських кораблів.
 * Перед вами карта з їх розташуванням.
 * Допоможіть Валерію Залужному визначити ймовірність влучити в однин з них, випадковим пострілом.
 * <p>
 * Input Format
 * <p>
 * Перше число в рядку вказує на кількість рядків на карті, а друге число - на кількість стовпців.
 * Після цього слідують рядки, що представляють карту, де кожен символ відображає стан комірки.
 * Цифра 1 позначає наявність корабля у відповідній комірці карти, а цифра 0 - відсутність корабля.
 * Input
 * 2 3
 * 0 0 1
 * 1 0 1
 * Output
 * 0.500000
 */
public class BlackSea {
    public static void main(String[] args) {
        int m, n;

        Scanner scanner = new Scanner(System.in);

        m = scanner.nextInt();
        n = scanner.nextInt();
        BigDecimal sum = BigDecimal.ZERO;
        int size = m * n;
        for (int i = 0; i < size; i++) {
            sum = sum.add(scanner.nextBigDecimal());
        }
        scanner.close();

        BigDecimal result = sum.divide(BigDecimal.valueOf(size),6, ROUND_HALF_UP);
        System.out.println(result);
    }
}
