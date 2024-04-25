package org.example.course_homework.hw_4;

import java.util.Scanner;

/**
 * Мета задачі — створити мінне поле для гри у сапера на основі вхідних координат мін.
 * Гравець грає на двовимірному полі, де деякі клітини містять міни.
 * Ваше завдання — згенерувати представлення цього поля, показуючи кількість мін у сусідніх клітинах для кожної клітини без міни.
 * Перший рядок вводу містить 3 цілих числа n, m — розміри поля (висота та ширина відповідно) та k - кількість мін.
 * Наступні рядки містять пари цілих чисел, які вказують координати мін на полі.
 * Координати вказані як (r, c), де r — номер рядка (починаючи з 1), а c — номер стовпця (починаючи з 1).
 * Constraints
 * <p>
 * 1 <= n, m, k <= 100
 * <p>
 * Координати мін завжди будуть унікальними і в межах вказаних розмірів поля.
 * <p>
 * Output Format
 * <p>
 * Вивести n рядків, що містять по m символів. Кожен символ має бути або цифрою,
 * яка вказує кількість мін у сусідніх клітинах (від 0 до 8), або символом "*", якщо у клітині міна.
 * <p>
 * Sample Input 0
 * <p>
 * 4 4 4
 * 1 3
 * 2 1
 * 4 2
 * 4 4
 * Sample Output 0
 * <p>
 * 1 2 * 1
 * * 2 1 1
 * 2 2 2 1
 * 1 * 2 *
 */
public class Minesweeper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();

        int[][] field = createEmptyField(n, m);

        for (int i = 0; i < k; i++) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            field[row - 1][col - 1] = -1;
        }

        calculateAroundMines(field, n, m);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (field[i][j] == -1) {
                    System.out.print("* ");
                } else {
                    System.out.printf("%d ", field[i][j]);
                }
            }
            System.out.println();
        }
    }

    private static int[][] createEmptyField(int n, int m) {
        int[][] field = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                field[i][j] = 0;
            }
        }
        return field;
    }

    private static void calculateAroundMines(int[][] field, int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (field[i][j] == -1) {
                    incrementAround(field, i, j);
                }
            }
        }
    }

    private static void incrementAround(int[][] field, int i, int j) {
        int[] dx = new int[]{-1, -1, -1,  0, 0,  1, 1, 1};
        int[] dy = new int[]{-1,  0,  1, -1, 1, -1, 0, 1};

        for (int step = 0; step < dx.length; step++) {
            int coordinate_i = i + dx[step];
            int coordinate_j = j + dy[step];
            if (inRange(coordinate_i, field.length) && inRange(coordinate_j, field[0].length) && field[coordinate_i][coordinate_j] != -1) {
                field[coordinate_i][coordinate_j] += 1;
            }
        }
    }

    private static boolean inRange(int coordinate, int limit) {
        return coordinate >= 0 && coordinate < limit;
    }
}
