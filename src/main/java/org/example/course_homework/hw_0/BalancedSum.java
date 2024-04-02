package org.example.course_homework.hw_0;

import java.io.IOException;
import java.util.Arrays;

/**
 * Watson gives Sherlock an array of integers.
 * His challenge is to find an element of the array such that the sum
 * of all elements to the left is equal to the sum of all elements to the right.
 * Example
 * arr = [5,6,8,11]
 * The answer is YES. 8 is between two subarrays that sum to 11.
 * <p>
 * You will be given arrays of integers and must determine whether there is an element that meets the criterion.
 * If there is, return YES. Otherwise, return NO.
 */


public class BalancedSum {
    public static void main(String[] args) throws IOException {
        System.out.println(balancedSums(new int[]{0, 0, 1, 4, 1}));
    }


    public static String balancedSums(int[] arr) {
        if (arr == null) {
            return "NO";
        }
        int totalSumLeft = 0;
        int totalSumRight = Arrays.stream(arr).reduce(0, Integer::sum);

        for (int i = 1; i < arr.length; i++) {
            var element = arr[i - 1];

            totalSumLeft = totalSumLeft + element;
            totalSumRight = totalSumRight - element;

            if (totalSumLeft == totalSumRight - arr[i]) {
                return "YES";
            }
        }
        return "NO";
    }
}

