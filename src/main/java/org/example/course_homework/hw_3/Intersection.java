package org.example.course_homework.hw_3;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Дано два масиви цілих чисел A та B, перетніть їх.
 * Кожен елемент у результаті повинен з'являтися стільки разів, скільки він зустрічається в обох масивах.
 * <p>
 * Input Format
 * <p>
 * Перший рядок містить одне ціле число n, що означає кількість елементів у масиві A.
 * Другий рядок містить n цілих чисел, розділених пробілом - елементи масиву A.
 * <p>
 * Третій рядок містить одне ціле число m, що означає кількість елементів у масиві B.
 * Четвертий рядок містить m цілих чисел, розділених пробілом - елементи масиву B.
 */
public class Intersection {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sizeTop = scanner.nextInt();
        int[] arr1 = new int[sizeTop];
        for (int i = 0; i < sizeTop; i++) {
            arr1[i] = scanner.nextInt();
        }

        int sizeBottom = scanner.nextInt();
        int[] arr2 = new int[sizeBottom];

        for (int j = 0; j < sizeBottom; j++) {
            arr2[j] = scanner.nextInt();
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        var result = intersection(arr1, arr2);
        if (result.isEmpty()) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    private static StringBuilder intersection(int[] arrTop, int[] arrBottom) {
        StringBuilder result = new StringBuilder();
        int topIndex = 0;
        int topCount = 1;
        int bottomIndex = 0;
        int bottomCount = 0;

        while (topIndex < arrTop.length) {
            int lookingNumber = arrTop[topIndex];

            if (topIndex < arrTop.length - 1 && lookingNumber == arrTop[topIndex + 1]) {
                topCount++;
                topIndex++;
                continue;
            }

            while (bottomIndex < arrBottom.length && lookingNumber >= arrBottom[bottomIndex]) {
                if (lookingNumber == arrBottom[bottomIndex]) {
                    bottomCount++;
                }
                bottomIndex++;
            }

            writeResult(result, lookingNumber, Math.min(topCount, bottomCount));

            topCount = 1;
            bottomCount = 0;
            topIndex++;
        }
        return result;
    }

    private static void writeResult(StringBuilder result, int number, int count) {
        for (int i = 0; i < count; i++) {
            result.append(number);
            result.append(" ");
        }
    }
}
