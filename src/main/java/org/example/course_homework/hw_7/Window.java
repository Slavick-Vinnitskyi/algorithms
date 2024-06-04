package org.example.course_homework.hw_7;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Let's consider an array of integers of length N. A window of length K moves along the array with a step size of 1.
 * Initially, the window contains the first K numbers of the array, then on the next step,
 * the window will contain K numbers starting from the second element, and so on until the end of the array.
 * For each position of the window, we need to determine the minimum value within it.
 * <p>
 * Input Format
 * The first line of the input contains two integers N and K - the length of the sequence and the size of the window,
 * respectively. The second line contains N integers - the sequence itself.
 * <p>
 * Constraints
 * 1 <= N <= 150000
 * 1 <= K <= 10000
 * K <= N
 * <p>
 * Output Format
 * The output should contain N - K + 1 lines - the minimum values for each position of the window.
 * <p>
 * Sample Input 0
 * 7 3
 * 1 3 2 4 5 3 1
 * <p>
 * Sample Output 0
 * 1
 * 2
 * 2
 * 3
 * 1
 */
public class Window {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = scanner.nextInt();
        }

        StringBuilder result = new StringBuilder();


        PriorityQueue<Integer> minHeap = initHeap(numbers, K);
        addResult(minHeap, result);

        int i = 0;
        while (i + K < N) {
            minHeap.remove(numbers[i]);
            minHeap.add(numbers[i + K]);
            i++;
            addResult(minHeap, result);
        }

        System.out.println(result);
    }

    private static PriorityQueue<Integer> initHeap(int[] numbers, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        for (int i = 0; i < k; i++) {
            minHeap.add(numbers[i]);
        }
        return minHeap;
    }

    private static void addResult(PriorityQueue<Integer> minHeap, StringBuilder result) {
        result.append(minHeap.peek());
        result.append("\n");
    }
}
