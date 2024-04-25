package org.example.course_homework.hw_4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;

/**
 * Given an array of n integers, your task is to count the number of subarrays where the sum of values is divisible by n.
 * <p>
 * Input Format
 * <p>
 * The first input line has an integer n - the size of the array.
 * The next line has n integers: the contents of the array
 * Print one integer: the required number of subarrays.
 * <p>
 * Sample Input 0
 * <p>
 * 5
 * 3 1 2 7 4 8 2 8
 * Sample Output 0
 * <p>
 * 1
 */
public class CountDivisibility {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] numbers = new int[size];

        for (int j = 0; j < size; j++) {
            numbers[j] = scanner.nextInt();
        }

        long combinations = solveFast(numbers);
        System.out.println(combinations);
    }

    public static int solveFast(int[] numbers) {
        int[] prefix_sums = new int[numbers.length + 1];
        prefix_sums[0] = 0;
        for (int i = 0; i < numbers.length; i++) {
            prefix_sums[i + 1] = prefix_sums[i] + numbers[i];
        }

        Map<Integer, Integer> map = new HashMap<>(numbers.length);

        for (int prefix_sum : prefix_sums) {
            int key = prefix_sum % numbers.length;

            if (key < 0) { // convert negative mod result to positive
                key += numbers.length;
            }

            add(map, key);
        }

        return map.values().stream().map(CountDivisibility::sumForCount).reduce(0, Integer::sum);
    }

    private static Integer sumForCount(Integer integer) {
        int sum = 0;
        for (int i = 0; i < integer; i++) {
            sum += i;
        }
        return sum;
    }


    private static void add(Map<Integer, Integer> map, int key) {
        map.putIfAbsent(key, 0);
        map.compute(key, increment());
    }

    private static BiFunction<Integer, Integer, Integer> increment() {
        return (number, count) -> count + 1;
    }

}

class CountDivisibilityBenchmark {
    public static void main(String[] args) {
        int count = 0;
        while (true) {
            int[] arr = generateArray();
            benchmark(arr);
            System.out.println(count++);
        }
    }

    private static int[] generateArray() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        int size = 10;
        int[] ints = new int[size];
        for (int i = 0; i < size; i++) {
            ints[i] = threadLocalRandom.nextInt(-15, 16);
        }
        return ints;
    }

    public static void benchmark(int[] arr) {
        long start = System.nanoTime();
        long combinations1 = solveSlow(arr);
        long end1 = System.nanoTime();
        long combinations2 = CountDivisibility.solveFast(arr);
        long end2 = System.nanoTime();

        double pow = 1_000_000_000;
        if (combinations1 != combinations2) {
            System.out.println(Arrays.toString(arr));
            System.out.println("fast :" + combinations1 + " time: " + (end1 - start) / pow);
            System.out.println("slow :" + combinations2 + " time: " + (end2 - end1) / pow);
        }
    }

    private static long solveSlow(int[] numbers) {
        long combination = 0;

        int length = numbers.length;

        for (int i = 0; i < length; i++) {
            long sum = 0;
            for (int j = i; j < length; j++) {
                sum += numbers[j];
                if (sum % length == 0) {
                    combination++;
                }
            }
        }
        return combination;
    }
}
