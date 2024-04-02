package org.example.course_homework.hw_2;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Вибір курсу
 * Іван вибирає собі спеціальність в університеті на якій він буде навчатися. Останнім часом їх з'явилося стільки,
 * що важко порівняти всі між собою та зробити правильний вибір.
 * <p>
 * Для початку Іван дізнався, які предмети викладають на кожній із спеціальностей.
 * Після цього він хоче вибрати пару предметів A та B і розбити всі спеціальності
 * на чотири множини (деякі з яких можуть бути порожніми):
 * <p>
 * Спеціальності, на яких викладають як предмет A, так і предмет B.
 * Спеціальності, на яких викладають предмет A, але не викладають предмет B.
 * Спеціальності, на яких не викладають предмет A, але викладають предмет B.
 * Спеціальності, на яких не викладають ні предмет A, ні предмет B.
 * Івану хочеться, щоб розмір найбільшої множини з цих чотирьох був якнайменше. Які предмети A та B він має вибрати?
 * <p>
 * Input Format
 * <p>
 * У першому рядку дано цілі числа n та m — кількість спеціальностей та кількість предметів, що викладаються.
 * У наступних n рядках записано по m чисел 0 або 1. j-е число в i-му рядку дорівнює одиниці,
 * якщо на i-й спеціальності викладають j-й предмет, і нулю в іншому випадку.
 * <p>
 * Output Format
 * <p>
 * У першому рядку виведіть розмір максимальної множини з описаних чотирьох при оптимальному розбитті.
 * У другому рядку виведіть два різні цілі числа в межах від 1 до m — номери предметів, які повинен вибрати Іван.
 * Якщо завдання має кілька оптимальних рішень, можна вивести будь-яке з них
 * <p>
 * Sample Input 0
 * <p>
 * 6 4
 * 0 0 0 1
 * 0 0 1 0
 * 0 1 1 1
 * 1 1 1 1
 * 0 0 0 0
 * 1 0 0 1
 * Sample Output 0
 * <p>
 * 2
 * 1 3
 */
public class CoursesBestSubset {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        int length = scanner.nextInt();
        int width = scanner.nextInt();
        int[][] study = new int[length][width];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                study[i][j] = scanner.nextInt();
            }
        }

        scanner.close();

        Map<String, Integer> solved = solve(study, length, width);

        var entry = solved.entrySet().stream()
                .min(Comparator.comparingInt(Map.Entry::getValue))
                .orElseThrow();
        System.out.printf("%d\n%s", entry.getValue(), entry.getKey());
    }

    private static Map<String, Integer> solve(int[][] arr, int length, int width) {
        Map<String, Integer> res = new HashMap<>();
        for (int i = 0; i < width; i++) {
            for (int j = i; j < width; j++) {
                if (i == j) continue;
                Map<String, Integer> map = new HashMap<>();
                map.put("00", 0);
                map.put("01", 0);
                map.put("10", 0);
                map.put("11", 0);
                for (int row_index = 0; row_index < length; row_index++) {
                    add(arr[row_index][i], arr[row_index][j], map);
                }
                res.put((i + 1 + " " + (j + 1)), calculateMax(map));
            }
        }
        return res;
    }

    private static Integer calculateMax(Map<String, Integer> map) {
        return map.values().stream()
                .max(Comparator.comparingInt(x -> x))
                .orElseThrow();
    }

    private static void add(int i, int i1, Map<String, Integer> map) {
        var key = String.valueOf(i) + i1;
        map.put(key, map.get(key));
    }
}
