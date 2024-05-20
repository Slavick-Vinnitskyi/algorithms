package org.example.course_homework.hw_6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Given an integer array of length n and an integer target,
 * find 3 integers in the array such that the sum is closest to target.
 * <p>
 * Input Format
 * <p>
 * First line contains an array values, second line contains target value.
 */
public class Sum3Closest {

    private static final String SPACE = " ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int target = scanner.nextInt();
        scanner.close();
        String[] strings = line.split(SPACE);
        int length = strings.length;
        int[] numbers = new int[length];
        for (int i = 0; i < length; i++) {
            numbers[i] = Integer.parseInt(strings[i]);
        }

        Arrays.sort(numbers);
        List<DoubleSum> doubleSums = calculateDoubleSums(numbers);
        List<Integer> tripleSums = doubleSums.stream().map(ds -> binSearchClosestTripleSum(numbers, ds, target))
                .collect(Collectors.toList());
        Integer tripleSumWithMinDiff = findWithMinDiff(tripleSums, target);
        System.out.println(tripleSumWithMinDiff);
    }

    private static Integer findWithMinDiff(List<Integer> tripleSums, int target) {
        int minDiff = Integer.MAX_VALUE;
        Integer result = null;
        for (var tripleSum : tripleSums) {
            int currentDiff = Math.abs(target - tripleSum);
            if (currentDiff < minDiff) {
                result = tripleSum;
                minDiff = currentDiff;
            }
        }

        return result;
    }

    private static List<DoubleSum> calculateDoubleSums(int[] numbers) {
        List<DoubleSum> result = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                result.add(new DoubleSum(numbers[i] + numbers[j], i, j));
            }
        }
        return result;
    }

    private static int binSearchClosestTripleSum(int[] numbers, DoubleSum doubleSum, int target) {
        int[] numbersCopy = createCopy(numbers, doubleSum.i, doubleSum.j, numbers.length - 2);
        int good = -1;
        int bad = numbersCopy.length;
        int tripleSum = doubleSum.sum + numbersCopy[0];

        while (bad - good > 1) {
            int medium = (good + bad) / 2;
            tripleSum = doubleSum.sum + numbersCopy[medium];
            int diff = target - tripleSum;
            if (diff < 0) {
                bad = medium;
            } else {
                good = medium;
            }
        }
        return tripleSum;
    }

    private static int[] createCopy(int[] numbers, int excludeIndex1, int excludeIndex2, int length) {
        int[] numbersCopy = new int[length];
        int j = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (i == excludeIndex1 || i == excludeIndex2) {
                continue;
            }
            numbersCopy[j] = numbers[i];
            j++;
        }
        return numbersCopy;
    }


    private static class DoubleSum {
        int sum;
        int i;
        int j;

        public DoubleSum(int sum, int i, int j) {
            this.sum = sum;
            this.i = i;
            this.j = j;
        }
    }
}
