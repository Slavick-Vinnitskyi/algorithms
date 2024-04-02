package org.example.course_homework.hw_2;

import java.util.Scanner;

/**
 * Подарунки
 * Джaвелін важить X кг, Патріот – Y кг, F16 – Z кг.
 * <p>
 * Потрібно написати програму, яка визначить, скільки різних варіантів подарунків вагою рівно W кг може зробити NATO.
 * <p>
 * Input Format
 * <p>
 * Вхідний рядок містить 4 цілих числа X, Y, Z, W
 * <p>
 * Constraints
 * <p>
 * 1 ≤ X, Y, Z ≤ 100, 1 ≤ W ≤ 1000
 * <p>
 * Output Format
 * <p>
 * Oдне ціле число – кількість варіантів подарунків
 */
public class Gifts {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int z = scanner.nextInt();
        int w = 1000;
        System.out.println(solve(w, x, y, z));
    }

    private static int solve(int w, int x, int y, int z) {
        int max_count_x = w / x;
        int max_count_y = w / y;
        int max_count_z = w / z;

        int count = 0;
        for (int i = 0; i <= max_count_x; i++) {
            int xSum = i * x;
            if (xSum > w) break;
            for (int j = 0; j <= max_count_y; j++) {
                int ySum = j * y;
                if (ySum + xSum > w) break;
                for (int k = 0; k <= max_count_z; k++) {
                    if (z * k + ySum + xSum > w) break;
                    int zSum = k * z;
                    if (xSum + ySum + zSum == w) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
