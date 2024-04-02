package org.example.leetcode;

import java.util.Arrays;

public class ArrayContainsDuplicates {
    public static void main(String[] args) {
        System.out.println(containsDuplicate(new int[]{1, 2, 3, 4, 4}));
    }

    public static boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        int prev = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == prev) {
                return true;
            }
            prev = nums[i];
        }
        return false;
    }
}
