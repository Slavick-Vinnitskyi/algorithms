package org.example.course_homework.hw_8;

import java.util.ArrayList;
import java.util.List;

/*************************************************************************
 * 78. Subsets
 * <a href="https://leetcode.com/problems/subsets/description/"> Link </a>
 * Given an integer array nums of unique elements, return all possible
 * subsets (the power set).
 * <p>
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 */

public class Subsets {
    public static void main(String[] args) {
        var subsets = subsets(new int[]{1, 2, 3});
        System.out.println(subsets);
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        var used = new boolean[nums.length];
        subsets(nums, new ArrayList<>(), result, used, 0);
        return result;
    }

    private static void subsets(int[] set,
                                List<Integer> currentSet,
                                List<List<Integer>> result,
                                boolean[] used,
                                int from) {

        result.add(new ArrayList<>(currentSet));

        for (int i = from; i < set.length; i++) {
            if (!used[i]) {
                used[i] = true;
                currentSet.add(set[i]);
                subsets(set, currentSet, result, used, i);
                used[i] = false;
                currentSet.removeLast();
            }

        }
    }
}
