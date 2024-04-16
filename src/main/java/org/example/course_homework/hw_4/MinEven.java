package org.example.course_homework.hw_4;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Напишіть функцію, яка приймає масив цілих чисел і повертає мінімальне парне число в цьому масиві.
 * Якщо масив не містить жодного парного числа, функція повинна повернути -1.
 * <p>
 * Input Format
 * <p>
 * Один рядок, який містить цілі числа, розділені пробілом
 * <p>
 * Constraints
 * <p>
 * Розмір масиву може бути від 1 до 1000. Кожне число в масиві може бути від -1000 до 1000.
 * <p>
 * Output Format
 * <p>
 * Мінімальне парне числе у масиві або -1, якщо парних чисел у масиві немає.
 */
public class MinEven {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        int minOdd = findMinOdd(list);
        System.out.println(minOdd == Integer.MAX_VALUE ? -1 : minOdd);
    }

    private static int findMinOdd(List<Integer> array) {
        int minOdd = Integer.MAX_VALUE;
        for (int candidate : array) {
            if (candidate % 2 == 0 && candidate < minOdd) {
                minOdd = candidate;
            }
        }
        return minOdd;
    }
}
