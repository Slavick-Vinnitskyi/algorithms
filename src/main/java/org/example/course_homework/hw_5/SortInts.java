package org.example.course_homework.hw_5;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Given an array of integers, sort the array in ascending order and return it.
 * <p>
 * You must solve the problem without using any built-in functions.
 * Implement merge sort algorithm in O(nlog(n)) time complexity and O(n) space complexity.
 * Input Format
 * <p>
 * List of ints
 * <p>
 * Constraints
 * <p>
 * 1 <= nums.length <= 5 * 10^4
 * <p>
 * -5 * 10^6 <= nums[i] <= 5 * 10^6
 * <p>
 * Output Format
 * <p>
 * sorted list of ints
 * <p>
 * Sample Input 0
 * <p>
 * 5 2 3 1
 * Sample Output 0
 * <p>
 * 1 2 3 5
 */
public class SortInts {

    private static final String SPACE = " ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();
        String[] strings = line.split(SPACE);
        int length = strings.length;
        int[] numbers = new int[length];
        for (int i = 0; i < length; i++) {
            numbers[i] = Integer.parseInt(strings[i]);
        }

        merge_sort(numbers, 0, numbers.length);

        StringBuilder stringBuilder = new StringBuilder();

        for (int number : numbers) {
            stringBuilder.append(number);
            stringBuilder.append(SPACE);
        }

        System.out.println(stringBuilder);
    }


    public static void merge_sort(int[] numbers, int left, int right) {
        if (right - left < 2) {
            return;
        }

        int medium = (left + right) / 2;

        merge_sort(numbers, left, medium);
        merge_sort(numbers, medium, right);
        merge(numbers, left, medium, right);
    }

    public static void merge(int[] numbers, int l, int m, int r) {
        int[] left_numbers = Arrays.copyOfRange(numbers, 0, numbers.length);
        int i = l;
        int j = m;
        int k = l;

        while (i < m || j < r) {
            if (j == r || (i < m && left_numbers[i] < numbers[j])) {
                numbers[k] = left_numbers[i];
                i++;
            } else {
                numbers[k] = numbers[j];
                j++;
            }
            k++;
        }
    }
}

class SortIntsBenchmark {
    public static void main(String[] args) {
        int count = 0;
        while (count < 6) {
            int baseSize = 10_000;
            bench(generateArray(count * baseSize));
            count++;
        }


        DoubleSummaryStatistics doubleSummaryStatistics = execs.stream().mapToDouble(Double::doubleValue).summaryStatistics();
        double min = doubleSummaryStatistics.getMin();
        double max = doubleSummaryStatistics.getMax();
        double average = doubleSummaryStatistics.getAverage();
        long countExceeding = execs.stream().filter(d -> d > 1.0).count();
        System.out.printf("min %f  max %f average %f countExceeding  %d", min, max, average, countExceeding);
    }

    private static final List<Double> execs = new ArrayList<>();

    private static void bench(int[] inputArray) {
        int[] array1 = Arrays.copyOf(inputArray, inputArray.length);
        int[] array2 = Arrays.copyOf(inputArray, inputArray.length);
        long p1 = System.nanoTime();
        SortInts.merge_sort(array1, 0, inputArray.length);
        long p2 = System.nanoTime();
        Arrays.sort(array2);
        long p3 = System.nanoTime();
        boolean equals = Arrays.equals(array1, array2);
        double my = (p2 - p1) / 1_000_000_000.0;
        double lib = (p3 - p2) / 1_000_000_000.0;
        System.out.printf("size : %d timeMySort: %.5f timeQuickSort: %.5f\n", inputArray.length, my, lib);
        execs.add(my);
        if (!equals) {
            System.out.println(array1.length);
            System.out.println(Arrays.toString(inputArray));
        }
    }

    private static int[] generateArray(int size) {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        int[] ints = new int[size];
        for (int i = 0; i < size; i++) {
            ints[i] = threadLocalRandom.nextInt(-1500, 1600);
        }
        return ints;
    }
}
