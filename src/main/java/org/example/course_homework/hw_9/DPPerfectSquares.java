package org.example.course_homework.hw_9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*************************************************************************
 * 279. Perfect Squares
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 * <p>
 * A perfect square is an integer that is the square of an integer;
 * in other words, it is the product of some integer with itself.
 * For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 * Example 1:
 * <p>
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 * <p>
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 * <p>
 * Constraints:
 * 1 <= n <= 104
 */

public class DPPerfectSquares {


    public static void main(String[] args) {
        List<Integer> squares = new ArrayList<>();
        var n = 12;
        for (int i = 1; i * i <= n; i++) {
            squares.add(i * i);
        }
        Set<Integer> squaresSet = new HashSet<>(squares);

        var numSquares = numSquares(n, squares, squaresSet, new HashMap<>());
        System.out.println(numSquares);
    }

    public static int numSquares(int n, List<Integer> squares, Set<Integer> squaresSet, HashMap<Integer, Integer> memo) {
        if (memo.get(n) != null) {
            return memo.get(n);
        }
        if (squaresSet.contains(n)) {
            return 1;
        }

        int min = n;
        for (int i = 0; i < squares.size(); i++) {
            Integer square = squares.get(i);
            var rest = n - square;
            if (rest > 0) {
                var numSquares = numSquares(rest, squares, squaresSet, memo);
                min = Math.min(min, numSquares + 1);
            }
        }
        memo.put(n, min);
        return min;
    }
}
