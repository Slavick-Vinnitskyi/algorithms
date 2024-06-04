package org.example.course_homework.hw_7;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Design an algorithm that collects daily price quotes for some stock and
 * returns the span of that stock's price for the current day.
 * <p>
 * The span of the stock's price in one day is the maximum number of consecutive days
 * (starting from that day and going backward) for which the stock price was less than or equal to the price of that day.
 * <p>
 * For example, if the prices of the stock in the last four days is [7, 2, 1, 2] and the price of the stock today is 2,
 * then the span of today is 4 because starting from today,
 * the price of the stock was less than or equal 2 for 4 consecutive days.
 * <p>
 * Also, if the prices of the stock in the last four days is [7, 34, 1, 2] and the price of the stock today is 8,
 * then the span of today is 3 because starting from today,
 * the price of the stock was less than or equal 8 for 3 consecutive days.
 * Input Format
 * <p>
 * Integer array of size of n.
 * <p>
 * Constraints
 * <p>
 * 1 <= price <= 10^5
 * Output Format
 * <p>
 * Integer array of size n.
 * <p>
 * Sample Input 0
 * <p>
 * 100 80 60 70 60 75 85
 * Sample Output 0
 * <p>
 * 1 1 1 2 1 4 6
 */
public class StockSpan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();
        String[] strings = line.split(" ");
        int length = strings.length;
        int[] numbers = new int[length];
        for (int i = 0; i < length; i++) {
            numbers[i] = Integer.parseInt(strings[i]);
        }

        System.out.println(solve(numbers));
    }

    private static String solve(int[] numbers) {
        var monotonicStack = new LinkedList<Integer>();
        var result = new StringBuilder();

        for (int i = 0; i < numbers.length; i++) {
            int countSmaller = 0;
            while (!monotonicStack.isEmpty() && numbers[monotonicStack.peekLast()] <= numbers[i]) {
                monotonicStack.pollLast();
            }

            if (monotonicStack.isEmpty()) {
                for (int j = 0; j <= i; j++) {
                    if (numbers[j] <= numbers[i]) {
                        countSmaller++;
                    }
                }
                result.append(countSmaller);
            } else {
                result.append(i - monotonicStack.peekLast());
            }
            result.append(" ");

            monotonicStack.add(i);
        }

        return result.toString();
    }
}