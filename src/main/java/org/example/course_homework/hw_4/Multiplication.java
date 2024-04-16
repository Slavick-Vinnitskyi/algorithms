package org.example.course_homework.hw_4;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Маючи список чисел, що містить принаймні два елементи, знайдіть пару чисел із цього списку,
 * які разом дають найбільший добуток. Після виявлення цієї пари, виведіть ці числа у порядку їх зростання.
 * Список сконструйовано таким чином, що існує лише одна унікальна пара, яка задовольняє ці умови.
 * <p>
 * Рішення повинно мати складність O(n), де n – розмір списку.
 * <p>
 * Sample Input 0
 * <p>
 * 4 3 5 2 5
 * Sample Output 0
 * <p>
 * 5 5
 */
public class Multiplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        int firstMaxIndex = findMax(list, -1);
        int secondMaxIndex = findMax(list, firstMaxIndex);
        int maxNumber1 = list.get(firstMaxIndex);
        int maxNumber2 = list.get(secondMaxIndex);

        int firstMinIndex = findMin(list, -1);
        int secondMinIndex = findMin(list, firstMinIndex);
        int minNumber1 = list.get(firstMinIndex);
        int minNumber2 = list.get(secondMinIndex);

        if (minNumber1 * minNumber2 > maxNumber1 * maxNumber2) {
            writeResult(minNumber1, minNumber2);
        } else {
            writeResult(maxNumber1, maxNumber2);
        }
    }

    private static int findMax(List<Integer> array, int excludeIndex) {
        int maxValue = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < array.size(); i++) {
            if (i == excludeIndex) {
                continue;
            }
            int candidate = array.get(i);
            if (candidate > maxValue) {
                maxValue = candidate;
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private static int findMin(List<Integer> array, int excludeIndex) {
        int minOdd = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < array.size(); i++) {
            if (i == excludeIndex) {
                continue;
            }
            int candidate = array.get(i);
            if (candidate < minOdd) {
                minOdd = candidate;
                minIndex = i;
            }
        }
        return minIndex;
    }

    private static void writeResult(int number1, int number2) {
        if (number1 > number2) {
            System.out.printf("%d %d", number2, number1);
        } else {
            System.out.printf("%d %d", number1, number2);
        }
    }
}
