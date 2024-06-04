package org.example.course_homework.hw_7;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Given an array nums with n objects colored red, white, or blue,
 * sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 * <p>
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * <p>
 * You must solve this problem without using the library's sort function.
 * <p>
 * Constraints
 * <p>
 * 1 <= nums.length <= 300
 * <p>
 * nums[i] is either 0, 1, or 2
 * <p>
 * Sample Input 0
 * 2 0 2 1 1 0
 * <p>
 * Sample Output 0
 * 0 0 1 1 2 2
 */
public class Colors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        bubbleSort(numbers);

        numbers.forEach(x -> System.out.print(x + " "));

    }

    private static void bubbleSort(List<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size(); j++) {
                if (numbers.get(j) > numbers.get(i)) {
                    swap(numbers, i, j);
                }
            }
        }
    }

    private static void swap(List<Integer> numbers, int i, int j) {
        Integer temp = numbers.get(i);
        numbers.set(i, numbers.get(j));
        numbers.set(j, temp);
    }
}
