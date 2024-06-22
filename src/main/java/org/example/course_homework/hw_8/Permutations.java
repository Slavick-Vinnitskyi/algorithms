package org.example.course_homework.hw_8;

import java.util.ArrayList;
import java.util.List;

/*************************************************************************
 * 46. Permutations
 * <a href="https://leetcode.com/problems/permutations/"> Link </a>
 * Given an array nums of distinct integers, return all the possible permutations.
 * You can return the answer in any order.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * Example 2:
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * <p>
 * Example 3:
 * Input: nums = [1]
 * Output: [[1]]
 */

public class Permutations {
    public static void main(String[] args) {
        List<List<Integer>> res = new ArrayList<>();
        var nums = new int[]{1, 2, 3};
        permute(res, new ArrayList<>(), nums, new boolean[nums.length]);
        System.out.println(res);
    }

    private static void permute(List<List<Integer>> result, List<Integer> permutation, int[] nums, boolean[] used) {
        if (permutation.size() == nums.length) {
            result.add(new ArrayList<>(permutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                permutation.add(nums[i]);
                used[i] = true;
                permute(result, permutation, nums, used);
                permutation.removeLast();
                used[i] = false;
            }
        }
    }
}
