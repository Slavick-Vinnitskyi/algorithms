package org.example.leetcode;

public class NumberKthFactor {
    public static void main(String[] args) {
        System.out.println(kthFactor(900, 4));
    }

    private static int kthFactor(int n, int k) {
        int iteration = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                iteration++;
            }
            if (iteration == k) {
                return i;
            }
        }
        return k > iteration ? -1 : k;
    }
}
