package org.example.course_homework.hw_9;

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

public class DPHouseRobberBottomUp {
    public static void main(String[] args) {
        var houses = new int[]{2, 7, 9, 3, 1};
        var rob = rob(houses);
        System.out.println(rob);
    }

    private static int rob(int[] houses) {
        if (houses.length == 1) {
            return houses[0];
        }
        var results = new int[houses.length];
        results[0] = houses[0];
        results[1] = Math.max(houses[0], houses[1]);

        for (int i = 2; i < houses.length; i++) {
            var max = Math.max(houses[i] + results[i - 2], results[i - 1]);
            results[i] = max;
        }
        return results[results.length - 1];
    }
}
