package org.example.course_homework.hw_4;

import java.util.Arrays;
import java.util.Scanner;

public class Search {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        int key = scanner.nextInt();

        int lastIndex = bin_search_last(array, key);
        int firstIndex = bin_search_first(Arrays.copyOfRange(array, 0, lastIndex), key);
        if (lastIndex == size - 1 && firstIndex == size - 1) {
            System.out.println("-1 -1 0");
        } else {
            System.out.printf("%d %d %d", firstIndex, lastIndex, lastIndex - firstIndex + 1);
        }
    }

    private static int bin_search_last(int[] array, int key) {
        int good = -1;
        int bad = array.length;

        while (bad - good > 1) {
            int medium = (good + bad) / 2;
            if (array[medium] > key) {
                bad = medium;
            } else {
                good = medium;
            }
        }
        return good;
    }

    private static int bin_search_first(int[] array, int key) {
        int good = array.length;
        int bad = -1;

        while (good - bad > 1) {
            int medium = (good + bad) / 2;
            if (array[medium] == key) {
                good = medium;
            } else {
                bad = medium;
            }
        }
        return good;
    }
}
