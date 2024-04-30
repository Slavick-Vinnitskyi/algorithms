package org.example.course_homework.hw_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * Intervals
 * Given an array of intervals where intervals[i] = [start_i, end_i], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * <p>
 * Input Format
 * <p>
 * The first line of the input contains a single integer, n, representing the number of intervals.
 * <p>
 * Each of the next n lines contains two integers separated by a space. These integers represent an interval.
 * The first integer of each line, start_i, indicates the start time of the ith interval, and the second integer,
 * end_i, represents the end time of the ith interval.
 * The first line of the input contains a single integer, n, representing the number of intervals.
 * <p>
 * Each of the next n lines contains two integers separated by a space. These integers represent an interval. The first integer of each line, start_i, indicates the start time of the ith interval, and the second integer, end_i, represents the end time of the ith interval.
 * <p>
 * Constraints
 * <p>
 * 1 <= n <= 10^5
 * <p>
 * intervals[i].length == 2
 * <p>
 * 0 <= start_i <= end_i <= 10^4
 * Output Format
 * <p>
 * The output should consist of the merged non-overlapping intervals that cover all the intervals in the input.
 * Each interval should be printed on a new line. Within each line, print the start and end times of the interval,
 * separated by a space. The intervals should be output in the order in which they appear after merging.
 * <p>
 * Sample Input 0
 * <p>
 * 4
 * 1 3
 * 2 6
 * 8 10
 * 15 18
 * Sample Output 0
 * <p>
 * 1 6
 * 8 10
 * 15 18
 */
public class IntervalsWithSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[][] intervals = new int[size][2];

        for (int i = 0; i < size; i++) {
            int left = scanner.nextInt();
            int right = scanner.nextInt();
            intervals[i] = new int[]{left, right};
        }

        List<int[]> mergedIntervals = mergeIntervals(intervals);
        for (int[] mergedInterval : mergedIntervals) {
            System.out.printf("%d %d\n", mergedInterval[0], mergedInterval[1]);
        }
    }

    private static List<int[]> mergeIntervals(int[][] intervals) {
        sort(intervals);
        List<int[]> merged = new ArrayList<>(intervals.length);
        merged.add(intervals[0]);
        int intervals_p = 0;
        int merged_p = 0;
        while (intervals_p < intervals.length) {
            int[] interval_elem = intervals[intervals_p];
            int[] merged_elem = merged.get(merged_p);
            if (hasOverlap(merged_elem, interval_elem)) {
                merged.set(merged_p, mergeIntervals(merged_elem, interval_elem));
            } else {
                merged_p += 1;
                merged.add(interval_elem);

            }
            intervals_p++;
        }
        return merged;
    }

    private static boolean hasOverlap(int[] a, int[] b) {
        int a_left = a[0];
        int a_right = a[1];
        int b_left = b[0];
        int b_right = b[1];

        if (a_left == b_left && a_right <= b_right) {
            return true;
        }

        if (a_left <= b_left && a_right >= b_left) {
            return true;
        }
        return false;
    }

    private static int[] mergeIntervals(int[] a, int[] b) {
        return new int[]{Math.min(a[0], b[0]), Math.max(a[1], b[1])};
    }

    private static void sort(int[][] intervals) {
        mergeSort(intervals, 0, intervals.length);
    }

    private static void mergeSort(int[][] intervals, int left, int right) {
        if (right - left < 2) {
            return;
        }

        int middle = (left + right) / 2;
        mergeSort(intervals, left, middle);
        mergeSort(intervals, middle, right);
        merge(intervals, left, middle, right);
    }

    private static void merge(int[][] intervals, int left, int medium, int right) {
        int[][] leftIntervals = makeCopy(intervals, left, medium);
        int left_size = leftIntervals.length;
        int i = 0;
        int j = medium;
        int k = left;

        while (i < left_size || j < right) {
            if ((j == right) || (i < left_size && compareTo(leftIntervals[i][0], intervals[j][0]) < 0)) {
                intervals[k] = leftIntervals[i];
                i++;
            } else {
                intervals[k] = intervals[j];
                j++;
            }
            k++;
        }
    }

    private static int compareTo(int i, int i2) {
        if (i == i2) {
            return 0;
        }
        return i > i2 ? 1 : -1;
    }

    private static int[][] makeCopy(int[][] intervals, int left, int medium) {
        int[][] copy = new int[medium - left][2];
        int i = 0;
        while (left < medium) {
            copy[i] = intervals[left];
            i++;
            left++;
        }
        return copy;
    }
}

/**
 * Failed attempt because of consecutive 1-sized intervals were merged into one
 */
class Intervals {
    private static Integer MAX_INDEX = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[][] intervals = new int[size][2];
        for (int i = 0; i < size; i++) {
            int left = scanner.nextInt();
            int right = scanner.nextInt();
            MAX_INDEX = Math.max(MAX_INDEX, left);
            MAX_INDEX = Math.max(MAX_INDEX, right);
            intervals[i] = new int[]{left, right};
        }

        List<int[]> mergedIntervals = mergeIntervals(intervals);
        for (int[] mergedInterval : mergedIntervals) {
            System.out.printf("%d %d\n", mergedInterval[0], mergedInterval[1]);
        }
    }


    private static List<int[]> mergeIntervals(int[][] intervals) {
        int[] projection = new int[MAX_INDEX + 2];
        for (int i = 0; i < intervals.length; i++) {
            projection[intervals[i][0]] = 1;
            Arrays.fill(projection, intervals[i][0], intervals[i][1], 1);
        }

        List<int[]> mergedIntervals = new ArrayList<>(intervals.length);

        int left = 0;
        for (int right = 0; right < projection.length; right++) { // two pointers to find subarray`s of 1
            while (left <= right && projection[left] != 1) {
                left++;
            }

            if (left <= right && projection[right] == 0 && projection[left] == 1) {
                mergedIntervals.add(new int[]{left, right});
                left = right;
            }
        }
        return mergedIntervals;
    }
}
