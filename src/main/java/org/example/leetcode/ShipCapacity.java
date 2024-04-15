package org.example.leetcode;

/**
 * 1011. Capacity To Ship Packages Within D Days
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/description/
 * A conveyor belt has packages that must be shipped from one port to another within days days.
 * <p>
 * The ith package on the conveyor belt has a weight of weights[i].
 * Each day, we load the ship with packages on the conveyor belt (in the order given by weights).
 * We may not load more weight than the maximum weight capacity of the ship.
 * <p>
 * Return the least weight capacity of the ship that will result in all
 * the packages on the conveyor belt being shipped within days days.
 */
public class ShipCapacity {

    public static void main(String[] args) {
        int load = shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5);
        System.out.println(load);
    }

    /**
     * Picking correct ship weight using the binary search
     */
    public static int shipWithinDays(int[] weights, int days) {
        int shipCapacity = 0;
        int good = getSum(weights);
        int bad = -1;

        while (good - bad > 1) {
            shipCapacity = (good + bad) / 2;
            if (canShip(weights, days, shipCapacity)) {
                good = shipCapacity;
            } else {
                bad = shipCapacity;
            }
        }
        return good;
    }

    private static int getSum(int[] weights) {
        int sum = 0;
        for (int weight : weights) {
            sum += weight;
        }
        return sum;
    }

    /**
     * Calculating if ship can load all packages
     */
    private static boolean canShip(int[] weights, int days, int shipCapacity) {
        int index = 0;
        for (int d = 0; d < days; d++) {
            int limit = shipCapacity;
            while (index < weights.length && limit - weights[index] >= 0) {
                limit -= weights[index];
                index++;
            }
        }
        return index == weights.length;
    }
}
