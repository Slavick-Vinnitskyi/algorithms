package org.example.course_homework.hw_3;

import java.util.Scanner;

/**
 * Дано послідовність цілих чисел довжиною N і M запитів.
 * Кожен запит містить два числа L і R. Ваше завдання - визначити кількість нулів на напівінтервалі [L, R).
 * <p>
 * Input Format
 * <p>
 * Перший рядок містить одне ціле число N - довжину послідовності.
 * <p>
 * Другий рядок містить N цілих чисел, розділених пробілами - саму послідовність.
 * <p>
 * Третій рядок містить одне ціле число M - кількість запитів.
 * <p>
 * Наступні M рядків містять по два цілі числа L і R кожен, розділених пробілом.
 * Sample Input 0
 * <p>
 * 8
 * 0 1 2 0 3 0 4 0
 * 3
 * 0 8
 * 2 6
 * 4 8
 * Sample Output 0
 * <p>
 * 4
 * 2
 * 2
 */
public class Zeros {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        int[] zerosCount = preprocess(arr);

        StringBuilder result = new StringBuilder();

        int intervalCounts = scanner.nextInt();
        for (int j = 0; j < intervalCounts; j++) {
            int startInterval = scanner.nextInt();
            int endInterval = scanner.nextInt();
            result.append((zerosCount[endInterval] - zerosCount[startInterval]));
            result.append("\n");
        }

        System.out.println(result);
    }

    private static int[] preprocess(int[] arr) {
        int[] ints = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            ints[i + 1] = ints[i] + (arr[i] == 0 ? 1 : 0);
        }
        return ints;
    }
}
