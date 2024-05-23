package org.example.course_homework.hw_6;

import java.util.*;

import static java.util.Optional.ofNullable;

/**
 * The median of a set of integers is the midpoint value of the data set for which an equal number of integers are
 * less than and greater than the value.
 * To find the median, you must first sort your set of integers in non-decreasing order, then:
 * <p>
 * If your set contains an odd number of elements, the median is the middle element of the sorted sample.
 * In the sorted set ,  is the median.
 * If your set contains an even number of elements, the median is the average of the two middle elements of the sorted sample.
 * In the sorted set {1, 2, 3}, 2 is the median.
 * Given an input stream of n integers, perform the following task for each ith integer:
 * Add the ith integer to a running list of integers.
 * Find the median of the updated list (i.e., for the first element through the ith element).
 * Print the updated median on a new line.
 * The printed value must be a double-precision number scaled to 1 decimal place (i.e., 12.3 format).
 * <p>
 * Add the  integer to a running list of integers.
 * Find the median of the updated list (i.e., for the first element through the  element).
 * Print the updated median on a new line. The printed value must be a double-precision number scaled to  decimal place (i.e.,  format).
 */
public class RunningMedian {
    private static final PriorityQueue<Integer> leftMaxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    private static final PriorityQueue<Integer> rightMinHeap = new PriorityQueue<>(Comparator.naturalOrder());

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        var values = new ArrayList<Integer>(n);
        for (int i = 0; i < n; i++) {
            values.add(scanner.nextInt());
        }

        runningMedian(values);
        scanner.close();
    }

    private static List<Double> runningMedian(List<Integer> values) {
        List<Double> medians = new ArrayList<>();
        for (Integer value : values) {
            addValue(value);
            medians.add(getMedian());
        }
        return medians;
    }

    private static void addValue(int value) {
        if (leftMaxHeap.isEmpty() || leftMaxHeap.peek() >= value) {
            leftMaxHeap.add(value);
        } else {
            rightMinHeap.add(value);
        }

        rebalanceIfNeeded();
    }

    private static void rebalanceIfNeeded() {
        if (leftMaxHeap.size() - rightMinHeap.size() > 1) {
            rebalance(leftMaxHeap, rightMinHeap);
        } else if (rightMinHeap.size() - leftMaxHeap.size() > 1) {
            rebalance(rightMinHeap, leftMaxHeap);
        }

    }

    private static void rebalance(PriorityQueue<Integer> source, PriorityQueue<Integer> target) {
        while (source.size() - target.size() > 1) {
            target.add(source.poll());
        }
    }

    private static double getMedian() {
        if (leftMaxHeap.size() > rightMinHeap.size()) {
            return leftMaxHeap.peek();
        }

        if (rightMinHeap.size() > leftMaxHeap.size()) {
            return rightMinHeap.peek();
        }

        return (ofNullable(leftMaxHeap.peek()).orElse(0) + ofNullable(rightMinHeap.peek()).orElse(0)) / 2f;
    }
}
