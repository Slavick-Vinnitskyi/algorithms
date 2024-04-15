package org.example.course_homework.hw_3;

import java.util.Scanner;

/**
 * Ви працюєте над проектом в сфері комп'ютерного зору, де потрібно розробити алгоритм для визначення
 * оптимальної пари об'єктів з двох різних наборів на основі мінімальної різниці в їхніх характеристиках.
 * <p>
 * Конкретно, задача полягає у виборі пари з N об'єктів першого типу та M об'єктів другого типу так,
 * щоб різниця між їх характеристиками (наприклад, кольором) була найменшою.
 * <p>
 * Input Format
 * <p>
 * У першому рядку дано ціле число N, що відповідає кількості об'єктів.
 * В другому рядку надається N цілих чисел, які представляють кольори цих об'єктів,
 * зазначених в зростаючому порядку. Гарантується, що кольори є унікальними.
 * <p>
 * В аналогічному форматі вводяться дані про об'єкти другого типу - вказується кількість об'єктів M,
 * а потім у наступному рядку - M цілих чисел, що представляють кольори цих об'єктів у зростаючому порядку.
 */
public class Vision {

    private static final StructuredResult RESULT = new StructuredResult();

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

        writeResult(Math.abs(arr1[0] - arr2[0]), arr1[0], arr2[0]);

        solve(arr1, arr2);

        System.out.printf("%d %d", RESULT.elementTop, RESULT.elementBottom);

    }

    private static void solve(int[] arrayTop, int[] arrayBottom) {
        int bottomIndex = 0;
        for (int topIndex = 0; topIndex < arrayTop.length; topIndex++) {
            bottomIndex = solveInternal(arrayTop[topIndex], arrayBottom, Math.max(bottomIndex - 1, 0));
            if (RESULT.diff == 0) {
                return;
            }
        }
    }

    private static int solveInternal(int arrayTopElem, int[] arrayBottom, int bottomIndex) {
        var localMinDiff = 9_999_999;

        while (bottomIndex < arrayBottom.length) {
            int localCurrentDif = Math.abs(arrayTopElem - arrayBottom[bottomIndex]);

            if (localCurrentDif < localMinDiff) {
                localMinDiff = localCurrentDif;
                writeResult(localCurrentDif, arrayTopElem, arrayBottom[bottomIndex]);
            } else {
                return bottomIndex;
            }

            bottomIndex++;
        }
        return bottomIndex;
    }

    private static void writeResult(int diff, int elementTop, int elementBottom) {
        if (diff < RESULT.diff) {
            RESULT.diff = diff;
            RESULT.elementTop = elementTop;
            RESULT.elementBottom = elementBottom;
        }
    }

}

class StructuredResult {
    int diff = 9_999_999;
    int elementTop;
    int elementBottom;
}