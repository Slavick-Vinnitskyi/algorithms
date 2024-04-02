package org.example.course_homework.hw_2;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Викреслення
 * Задано натуральне число N. Знайдіть кількість унікальних трьохзначних чисел,
 * що можна утворити шляхом видалення цифр з десяткового запису числа N.
 * Видалення цифр може відбуватися з будь-якого місця в числі.
 * <p>
 * Sample Input 1
 * 111111111110011111111
 * <p>
 * Sample Output 1
 * 4
 */
public class UniqueNumbersFromNumber {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String num = scanner.nextLine();
        scanner.close();

        if (num.length() < 3) {
            System.out.println(0);
            return;
        }

        Set<Integer> numbers = new HashSet<>();
        for (int i = 0; i < num.length(); i++) {
            for (int j = i + 1; j < num.length(); j++) {
                for (int k = j + 1; k < num.length(); k++) {
                    var number = Integer.parseInt(getValue(num, i) + getValue(num, j) + getValue(num, k));
                    if (Math.log10(number) >= 2) {
                        numbers.add(number);
                    }
                }
            }
        }

        System.out.println(numbers.size());
        numbers.forEach(System.out::println);
    }

    private static String getValue(String num, int i) {
        return String.valueOf(num.charAt(i));
    }
}
