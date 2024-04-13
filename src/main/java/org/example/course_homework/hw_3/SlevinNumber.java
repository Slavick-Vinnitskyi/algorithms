package org.example.course_homework.hw_3;

import java.util.Scanner;

/**
 * Слевін любить шукати своє щасливе число K. Він щодня ходить по вулиці, вздовж якої припарковано N машин і
 * його зацікавило питання - скільки існує наборів машин,
 * що стоять підряд на місцях від L до R і так, що сума їх номерів дорівнює K?
 * <p>
 * Наприклад, якщо число N = 5, K = 17, а номери машин дорівнюють 17, 7, 10, 7, 10, то існує 4 набори машин:
 * <p>
 * 17 (L = 1, R = 1),
 * 7, 10 (L = 2, R = 3),
 * 10, 7 (L = 3, R = 4),
 * 7, 10 (L = 4, R = 5)
 * Input Format
 * <p>
 * У першому рядку задаються числа N і K. У другому рядку міститься N чисел, що задають номери машин.
 * Номери машин можуть набувати значення від 1 до 999 включно.
 * <p>
 * Constraints
 * <p>
 * 1 <= N <= 100_000
 * <p>
 * 1 <= K <= 10^9
 * <p>
 * Output Format
 * <p>
 * Необхідно вивести одне число — кількість наборів.
 * <p>
 * Sample Input 0
 * <p>
 * 5 17
 * 17 7 10 7 10
 * Sample Output 0
 * <p>
 * 4
 */
public class SlevinNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        long sum = scanner.nextLong();
        long[] arr = new long[size];
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }
        int res = solve(arr, sum);
        System.out.println(res);
    }

    /**
     * @param arr input array
     * @param sum sum to find
     * @return count of sub-arrays with search sum
     */
    private static int solve(long[] arr, long sum) {
        int count = 0;
        int subSum = 0;
        int left = 0;
        for (int right = 0; right < arr.length; right++) {
            subSum += arr[right];
            while (subSum >= sum) {
                if (subSum == sum) {
                    count++;
                }
                subSum -= arr[left];
                left++;

            }
        }
        return count;
    }
}
