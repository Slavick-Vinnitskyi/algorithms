package org.example.practice;

import java.util.Arrays;

public class MergeArrays {
    public static void main(String[] args) {
        int[] a = {100};
        int[] b = {-100};
        Arrays.sort(a);
        Arrays.sort(b);
        int[] c1 = Linear.merge(a, b);
        int[] c2 = Quadratic.merge(a, b);
        System.out.println(Arrays.toString(c1));
        System.out.println(Arrays.toString(c2));
    }

    static class Linear {
        /**
         * Time complexity is linear
         * Input arrays has to be sorted
         */
        public static int[] merge(int[] a, int[] b) {
            int len_a = a.length;
            int len_b = b.length;
            int[] c = new int[len_a + len_b];
            int i = 0;
            int j = 0;

            while (i < len_a || j < len_b) {
                if (j == len_b || (i < len_a && a[i] < b[j])) {
                    c[i + j] = a[i];
                    i++;
                } else {
                    c[i + j] = b[j];
                    j++;
                }
            }

            return c;
        }
    }

    static class Quadratic {

        /**
         * Time complexity is n^2 but input arrays
         * is not required to be sorted
         */
        public static int[] merge(int[] a, int[] b) {
            int a_length = a.length;
            int b_length = b.length;
            int[] a_b = new int[a_length + b_length];
            int i = 0;
            int j = 0;
            int k = 0;
            while (i < a_length || j < b_length) {
                int min_a = findMin(a);
                int min_b = findMin(b);
                if (min_a < min_b || j == b_length) {
                    a = remove(a, min_a);
                    i++;
                } else {
                    b = remove(b, min_b);
                    j++;
                }
                a_b[k] = Math.min(min_a, min_b);
                k++;
            }

            return a_b;
        }

        private static int[] remove(int[] arr, int value) {
            if (arr.length <= 1) {
                return new int[]{};
            }
            int[] arr_new = new int[arr.length - 1];

            for (int i = 0, k = 0; i < arr.length; i++) {
                if (arr[i] != value) {
                    arr_new[k] = arr[i];
                    k++;
                }
            }
            return arr_new;
        }

        private static int findMin(int[] a) {
            if (a.length < 1) {
                return Integer.MAX_VALUE;
            }
            int i = 0;
            int min = a[i];
            while (i < a.length) {
                if (a[i] < min) {
                    min = a[i];
                }
                i++;
            }
            return min;
        }
    }
}