package org.example.course_homework.hw_3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * У парку є алея, яка складається з N посаджених в ряд дерев одного з K сортів.
 * У зв'язку з тим, що місто приймає чемпіонат з програмування, було вирішено збудувати величезну арену.
 * Згідно з цим планом, вся алея підлягала вирубці. Проте міністерство дерев проти цього рішення.
 * Згідно з новим планом будівництва, всі дерева, які не будуть вирубані, повинні утворювати один неперервний відрізок.
 * Кожного з видів дерева потрібно зберегти хоча б по одному екземпляру.
 * На вас покладено завдання знайти відрізок найменшої довжини, який відповідає зазначеним обмеженням.
 * <p>
 * У першому рядку є два числа N і K. У другому рядку слідують N чисел (розділених пробілами),
 * i-е число другого рядка задає сорт i-ого зліва дерева в алеї.
 * Гарантується, що є хоча б одне дерево кожного сорту.
 */
public class Trees {
    private static final Map<Integer, Integer> NUMBER_TO_COUNT = new HashMap<>(249_999);
    private static Integer COUNT_SORTS = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        int[] trees = new int[N];
        for (int i = 0; i < N; i++) {
            trees[i] = scanner.nextInt();
        }
        solve(trees, K);
    }

    /**
     * Find shortest subarray with K variations of
     *
     * @param trees - numbers array
     * @param k     - count of number variations
     */
    private static void solve(int[] trees, int k) {
        int start_result = 0;
        int end_result = trees.length;
        int left = 0;
        for (int right = 0; right < trees.length; right++) {
            add(trees[right]);
            int storedLen = end_result - start_result;
            while (left <= right && isPresentMoreThanOnce(trees[left])) { // while left is deletable
                delete(trees[left]);
                left++;
            }
            if (isFullCombo(k) && right - left < storedLen) {
                start_result = left;
                end_result = right;
            }
        }
        System.out.println(start_result + 1 + " " + (end_result + 1));
    }

    private static boolean isFullCombo(int k) {
        return COUNT_SORTS.equals(k);
    }

    private static void add(int tree) {
        if (!NUMBER_TO_COUNT.containsKey(tree)) {
            COUNT_SORTS++;
        }
        NUMBER_TO_COUNT.putIfAbsent(tree, 0);
        NUMBER_TO_COUNT.compute(tree, (key, val) -> val + 1);
    }

    private static void delete(int tree) {
        NUMBER_TO_COUNT.computeIfPresent(tree, (key, val) -> val - 1);
        if (NUMBER_TO_COUNT.containsKey(tree) && NUMBER_TO_COUNT.get(tree) == 0) {
            NUMBER_TO_COUNT.remove(tree);
            COUNT_SORTS--;
        }
    }

    private static boolean isPresentMoreThanOnce(int tree) {
        return NUMBER_TO_COUNT.getOrDefault(tree, 0) > 1;
    }
}
