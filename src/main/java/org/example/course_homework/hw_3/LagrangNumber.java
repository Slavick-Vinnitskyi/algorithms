package org.example.course_homework.hw_3;

import java.util.Scanner;

/**
 * Теорема Лагранжа стверджує, що будь-яке натуральне число можна представити у вигляді суми чотирьох точних квадратів.
 * За заданим числом n знайдіть таке представлення: надрукуйте від 1 до 4 натуральних чисел,
 * квадрати яких дають у сумі це число.
 * <p>
 * Input Format
 * <p>
 * Програма на вхід отримує одне натуральне число n.
 * <p>
 * Constraints
 * <p>
 * 1 <= n < 10000
 * <p>
 * Output Format
 * <p>
 * Вивести від 1 до 4 натуральних чисел, у відсортованому порядку, квадрати яких дають у сумі n.
 * Серед можливих варіантів обрати той, в якому кількість доданків найменша.
 * Якщо таких декілька, то вивести найменший в лексикографічному порядку.
 */
public class LagrangNumber {

    /**
     * n = a^2 + b^2 + c^2 + d^2
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        StringBuilder stringBuilder = new StringBuilder();
        extracted(number, stringBuilder);

        System.out.println(stringBuilder);
    }

    private static StringBuilder extracted(int number, StringBuilder stringBuilder) {
        for (int a = 0; a * a <= number; a++) {
            for (int b = 0; b * b < number - a * a; b++) {
                for (int c = 0; c * c < number - a * a - b * b; c++) {
                    var first_three = a * a + b * b + c * c;
                    double possibleD = Math.sqrt(number - first_three);
                    if (possibleD % 1 != 0) {
                        continue;
                    }
                    int intD = (int) possibleD;
                    writeResult(stringBuilder, a, b, c, intD);
                    return stringBuilder;
                }
            }
        }
        return stringBuilder;
    }

    private static void writeResult(StringBuilder stringBuilder, int a, int b, int c, int intD) {
        append(stringBuilder, a, " ");
        append(stringBuilder, b, " ");
        append(stringBuilder, c, " ");
        append(stringBuilder, intD, "\n");
    }

    private static void append(StringBuilder stringBuilder, int number, String suffix) {
        if (number != 0) {
            stringBuilder.append(number);
            stringBuilder.append(suffix);
        }
    }
}
