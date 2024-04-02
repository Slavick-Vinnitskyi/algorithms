package org.example.course_homework.hw_0;

import java.util.ArrayList;
import java.util.List;

/**
 * Кожен новий член послідовності Фібоначчі утворюється шляхом додавання двох попередніх.
 * Починаючи з 1 і 2, перші 10 членів мають вигляд:
 * 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
 * Розглядаючи лише ті члени послідовності Фібоначчі, значення яких не перевищують N, знайти суму парних членів.
 * <p>
 * Формат вхідних даних
 * Перший рядок містить число , що визначає кількість тестів.
 * Після цього слідує рядків, кожен з яких містить ціле число.
 * <p>
 * Формат вихідних даних
 * Вивести шукану відповідь для кожного тесту.
 * <p>
 * Обмеження
 * 1 <= T <= 10^4
 * 10 <= N <= 4 * 10^16
 * <p>
 * Приклад вхідних даних
 * <p>
 * 2
 * 10
 * 100
 * Приклад вихідних даних
 * <p>
 * 10
 * 44
 * Sample Input 0
 * <p>
 * 2
 * 10
 * 100
 * Sample Output 0
 * <p>
 * 10
 * 44
 * Explanation 0
 * <p>
 * For , we have , sum is .
 * For , we have , sum is .
 */
public class FibonacciEven {


    public static void main(String[] args) {
        long limit = 15;
        System.out.println(evenFibonacciRecursionTop(limit));
        System.out.println(evenFibonacciBruteforce(limit));
        System.out.println(evenFibonacciFormula(limit));
    }

    private static long evenFibonacciRecursionTop(long number) {
        long recursiveSum = 0L;
        long fib = 0;
        long index = 0;
        while (fib < number) {
            fib = evenFibonacciRecursion(index);
            index++;
            if (fib % 2 == 0 && fib <= number) {
                recursiveSum += fib;
            }
        }
        return recursiveSum;
    }

    private static long evenFibonacciRecursion(long number) {
        if (number < 2) {
            return number;
        }
        return evenFibonacciRecursion(number - 1) + evenFibonacciRecursion(number - 2);
    }


    private static long evenFibonacciBruteforce(long limit) {
        List<Long> numbers = new ArrayList<>();
        numbers.add(0L);
        numbers.add(1L);

        long sum = 0;
        long sum_even = 0;

        int i = 1;
        while (sum <= limit) {
            sum = numbers.get(i) + numbers.get(i - 1);
            numbers.add(sum);
            if (sum % 2 == 0 && sum <= limit) {
                sum_even += sum;
            }
            i++;
        }
        return sum_even;
    }

    private static long evenFibonacciFormula(long limit) {
        long fib1 = 0;
        long fib2 = 2;

        long sum_even = fib1 + fib2;
        long next_fib = 0;

        while (next_fib <= limit) {
            next_fib = fib1 + 4 * fib2;

            if (next_fib > limit) {
                break;
            }

            fib1 = fib2;
            fib2 = next_fib;
            sum_even += next_fib;
        }
        return sum_even;
    }

}

