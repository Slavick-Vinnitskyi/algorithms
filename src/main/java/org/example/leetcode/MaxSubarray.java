package org.example.leetcode;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.IntStream;

/**
 * 53. Maximum Subarray
 * https://leetcode.com/problems/maximum-subarray
 * Medium
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
 * <p>
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 */
public class MaxSubarray {

    public static final String path = "C:\\Users\\andre\\IdeaProjects\\algorithms\\src\\main\\resources\\file.txt";

    public static void main(String[] args) throws IOException {
        String arrS = Files.readString(Path.of(path));
        String[] split = arrS.split(",");
        int[] ints = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            ints[i] = Integer.parseInt(split[i].trim());
        }
        long start = System.currentTimeMillis();
        int x = maxSubArrayQuadratic(ints);
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start));
        System.out.println(x);
    }


    private static int maxSubArrayQuadratic(int[] nums) {
        int maxSum = nums[0];
        int prev_sum;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            prev_sum = 0;
            for (int j = i; j < length; j++) {
                prev_sum += nums[j];
                maxSum = Math.max(prev_sum, maxSum);
            }
        }
        return maxSum;
    }

    private static int calcSum(int[] nums, int i, int j) {
        return IntStream.range(i, j).map(ind -> nums[ind]).reduce(0, Integer::sum);
    }


//    two pointers - failed
//    private static int maxSubArray(int[] nums) {
//        int max_sum = calcSum(nums, 0, nums.length);
//        int current_sum = 0;
//        int left = 0;
//
//        for (int right = 0; right < nums.length; right++) {
//            current_sum += nums[right];
//            while (current_sum < current_sum - nums[left]) {
//                current_sum -= nums[left];
//                left++;
//            }
//            if (current_sum > max_sum) {
//                max_sum = current_sum;
//            }
//
//        }
//        return max_sum;
//    }
}
