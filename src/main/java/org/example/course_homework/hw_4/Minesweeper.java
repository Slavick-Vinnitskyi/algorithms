package org.example.course_homework.hw_4;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Minesweeper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();

        Set<String> mines = new HashSet<>(k);

        for (int i = 0; i < k; i++) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            mines.add((row - 1) + "" + (col - 1));
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(getMineValue(i, j, mines));
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private static String getMineValue(int i, int j, Set<String> mines) {
        if (mines.contains(i + "" + j)) {
            return "*";
        }
        int counter = 0;

        if (mines.contains((i - 1) + "" + (j - 1))) {
            counter++;
        }

        if (mines.contains((i - 1) + "" + (j))) {
            counter++;
        }

        if (mines.contains((i - 1) + "" + (j + 1))) {
            counter++;
        }

        if (mines.contains((i) + "" + (j - 1))) {
            counter++;
        }

        if (mines.contains((i) + "" + (j + 1))) {
            counter++;
        }

        if (mines.contains((i + 1) + "" + (j - 1))) {
            counter++;
        }

        if (mines.contains((i + 1) + "" + (j))) {
            counter++;
        }

        if (mines.contains((i + 1) + "" + (j + 1))) {
            counter++;
        }

        return String.valueOf(counter);
    }
}
