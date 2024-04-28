package org.example.course_homework.hw_5;

import java.util.ArrayList;
import java.util.List;

/**
 * Consider an array of numeric strings where each string is a positive number with anywhere from  to  digits.
 * Sort the array's elements in non-decreasing, or ascending order of their integer values and return the sorted array.
 * <p>
 * Example
 * unsorted = ['1', '200', '150', '3']
 * Return the array ['1', '3', '150', '200'].
 */
public class BigSorting {

    public static void main(String[] args) {
        List<String> list = List.of(
                "1", "2", "100", "31415926535897932384626433832795", "1", "3", "10", "3", "5",
                "12303479849857341718340192371", "3084193741082937", "3084193741082938", "111", "200"
        );
        List<String> copy = new ArrayList<>(list);
        var sorted = bigSorting(copy);
        System.out.println(sorted);
    }

    /**
     * Complete the 'bigSorting' function below.
     * <p>
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING_ARRAY unsorted as parameter.
     */
    public static List<String> bigSorting(List<String> unsorted) {
        bigMergeSorting(unsorted, 0, unsorted.size());
        return unsorted;
    }

    private static void bigMergeSorting(List<String> list, int left, int right) {
        if (right - left < 2) {
            return;
        }

        int middle = (left + right) / 2;

        bigMergeSorting(list, left, middle);
        bigMergeSorting(list, middle, right);
        merge(list, left, middle, right);
    }

    private static void merge(List<String> list, int left, int middle, int right) {
        List<String> left_list = List.copyOf(list.subList(left, middle));
        int left_size = left_list.size();
        int i = 0;
        int j = middle;
        int k = left;

        while (i < left_size || j < right) {
            if ((j == right) || (i < left_size && compareTo(left_list.get(i), list.get(j)) < 0)) {
                list.set(k, left_list.get(i));
                i++;
            } else {
                list.set(k, list.get(j));
                j++;
            }
            k++;
        }
    }

    private static int compareTo(String s1, String s2) {
        if (s1.length() == s2.length()) {
            int i = 0;
            int compare = 0;
            while (i < s1.length() && compare == 0) {
                compare = Integer.compare(s1.charAt(i), s2.charAt(i));
                i++;
            }
            return compare;
        }
        return s1.length() > s2.length() ? 1 : -1;
    }
}





