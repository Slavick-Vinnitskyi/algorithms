package org.example.course_homework.hw_3;

import java.util.Scanner;


/**
 * Проаналізуйте записи логу сервера,
 * які представляють собою послідовність нулів та одиниць - результат обробки окремого запиту.
 * Ваше завдання - ідентифікувати та повідомити довжину найдовшої послідовності безперервних одиниць,
 * що відображає неперервний ряд успішно оброблених запитів.
 * Input Format
 * <p>
 * На вхід подається довжина масиву та з наступного рядку сам масив
 */
public class ServerLogs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println(solve(arr));
    }

    private static int solve(int[] arr) {
        int maxLength = 0;
        int left = 0;
        int right = 0;
        while (right < arr.length) {
            if (arr[right] == 1) {
                right++;
                maxLength = Math.max(maxLength, right - left);
            } else {
                right++;
                left = right;
            }
        }
        return maxLength;
    }
}
