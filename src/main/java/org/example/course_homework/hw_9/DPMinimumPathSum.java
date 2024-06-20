package org.example.course_homework.hw_9;

import java.util.Arrays;

/*************************************************************************
 *  64. Minimum Path Sum
 *  Given a m x n grid filled with non-negative numbers,
 *  find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
 *  Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 *  Output: 7
 *  Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 *  Example 2:
 *  <p>
 *  Input: grid = [[1,2,3],[4,5,6]]
 *  Output: 12
 *  <p>
 *  Constraints:
 *  m == grid.length
 *  n == grid[i].length
 *  1 <= m, n <= 200
 *  0 <= grid[i][j] <= 200
 *  Note: You can only move either down or right at any point in time.
 */

public class DPMinimumPathSum {
    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int[][] grid2 = new int[][]{{1, 2, 3}, {4, 5, 6}};
        int result = minPathSum(grid);
        int result2 = minPathSum(grid2);
        System.out.println(result);
        System.out.println(result2);
    }

    public static int minPathSum(int[][] grid) {
        int[][] dp = initDp(grid);
        return minPathInternal(grid, 0, 0, dp);
    }

    private static int minPathInternal(int[][] grid, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return grid[i][j];
        }

        var minPath = Integer.MAX_VALUE;

        for (var direction : Direction.values()) {

            var here = grid[i][j];

            if (direction.isAllowed(grid, i, j)) {
                var pathFromHere = minPathInternal(grid, i + direction.getI(), j + direction.getJ(), dp);
                minPath = Math.min(minPath, here + pathFromHere);
            }
        }
        dp[i][j] = minPath;
        return minPath;
    }

    private static int[][] initDp(int[][] grid) {
        int[][] memo = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        return memo;
    }
}

enum Direction {
    RIGHT(0, 1),
    DOWN(1, 0);

    private final int i;
    private final int j;

    Direction(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public boolean isAllowed(int[][] grid, int i, int j) {
        return i + this.i < grid.length && j + this.j < grid[0].length;
    }

    public int getJ() {
        return j;
    }

    public int getI() {
        return i;
    }

}
