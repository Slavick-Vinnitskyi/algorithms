package org.example.course_homework.hw_9;

import java.util.HashMap;

/*************************************************************************
 * * 198. House Robber
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 */

public class DPHouseRobber {
    public static void main(String[] args) {
        var rob = rob(new int[]{2, 7, 9, 3, 1});
        System.out.println(rob);
    }

    private static final HashMap<Integer, Integer> memo = new HashMap<>();

    public static int rob(int[] nums) {
        var rob = rob(nums, 0);
        memo.clear();
        return rob;
    }

    private static int rob(int[] nums, int from) {
        var stored = memo.get(from);
        if (stored != null) {
            return stored;
        }
        if (nums.length == from) {
            throw new IllegalArgumentException("nums is empty");
        }
        if (nums.length == from + 1) {
            return nums[from];
        }
        if (nums.length == from + 2) {
            return Math.max(nums[from], nums[from + 1]);
        }

        var max = Math.max(rob(nums, from + 1), nums[from] + rob(nums, from + 2));
        memo.put(from, max);
        return max;
    }
}
