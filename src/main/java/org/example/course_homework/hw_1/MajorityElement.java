package org.example.course_homework.hw_1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Given an array nums of size n, return the majority element.
 * <p>
 * The majority element is the element that appears more than n / 2 times.
 * You may assume that the majority element always exists in the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 * <p>
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -10^9 <= nums[i] <= 10^9
 * <p>
 * <p>
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 */
public class MajorityElement {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElementCounter(arr));
        System.out.println(majorityElementMap(arr));
        System.out.println(majorityElementMapCounter(arr));
    }

    public static int majorityElementCounter(int[] nums) {
        int majorityElement = 0;
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                majorityElement = num;
            }

            if (majorityElement == num) {
                count += 1;
            } else {
                count -= 1;
            }

        }
        return majorityElement;
    }

    public static int majorityElementMap(int[] nums) {
        var elementToCount = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return elementToCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow();
    }

    public static int majorityElementMapCounter(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            int count = map.getOrDefault(num, 0) + 1;
            if (count >= nums.length / 2 + 1) {
                return num;
            }
            map.put(num, count);
        }
        return 0;
    }
}
