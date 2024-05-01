package org.example.course_homework.hw_5;

import java.util.Scanner;

/**
 * Given a two-dimensional array where each row is sorted in ascending order,
 * your task is to sort the entire 2D array in-place in ascending order as well.
 * <p>
 * Input Format
 * <p>
 * The first line contains two integers R and C, the number of rows and columns in the matrix.
 * <p>
 * The next R lines each contain C integers, representing the elements of the matrix,
 * where each row is sorted in ascending order.
 * Constraints
 * <p>
 * 1 <= R, C <= 1000
 * <p>
 * -10^6 <= matrix[i][j] <= 10^6
 * <p>
 * Each row of the matrix is sorted in ascending order.
 * Output Format
 * <p>
 * Output R lines each containing C integers, representing the sorted matrix in row-major form,
 * such that the entire 2D array is sorted in ascending order.
 * <p>
 * Sample Input 0
 * <p>
 * 4 5
 * 5 12 17 21 23
 * 1 2 4 6 8
 * 12 14 18 19 27
 * 3 7 9 15 25
 * Sample Output 0
 * <p>
 * 1 2 3 4 5
 * 6 7 8 9 12
 * 12 14 15 17 18
 * 19 21 23 25 27
 */
public class Sort2dArray {
    private static int HEIGHT;
    private static int WIDTH;

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        HEIGHT = scanner.nextInt();
        WIDTH = scanner.nextInt();

        int[][] matrix = new int[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        scanner.close();

        sort(matrix);
        print(matrix);
    }

    private static void sort(int[][] matrix) {
        for (int i = 0; i < HEIGHT - 1; i++) {
            for (int j = 0; j < WIDTH; j++) {
                int minRow = findRowWithMin(matrix, i);
                if (swap(matrix, i, j, minRow, 0)) {
                    sortRow(matrix, minRow);
                }
            }
        }
    }

    private static boolean swap(int[][] matrix, int i0, int j0, int i1, int j1) {
        if (matrix[i0][j0] <= matrix[i1][j1]) {
            return false;
        }
        int temp = matrix[i0][j0];
        matrix[i0][j0] = matrix[i1][j1];
        matrix[i1][j1] = temp;
        return true;
    }

    private static void sortRow(int[][] matrix, int row) {
        for (int i = 0; i < WIDTH - 1; i++) {
            if (matrix[row][i] > matrix[row][i + 1]) {
                swap(matrix, row, i, row, i + 1);
            }
        }
    }

    private static int findRowWithMin(int[][] matrix, int topLimit) {
        int min = Integer.MAX_VALUE;
        int minRow = topLimit;
        for (int i = topLimit + 1; i < HEIGHT; i++) {
            if (matrix[i][0] < min) {
                min = matrix[i][0];
                minRow = i;
            }
        }
        return minRow;
    }

    private static void print(int[][] matrix) {
        var stringBuilder = new StringBuilder();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                stringBuilder.append(matrix[i][j]);
                stringBuilder.append(" ");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }
}
