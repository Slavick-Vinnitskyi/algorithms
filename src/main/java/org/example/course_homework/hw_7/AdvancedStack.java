package org.example.course_homework.hw_7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class AdvancedStack {

    private static final MaxStack maxStack = new MaxStack();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int Q = Integer.parseInt(scanner.nextLine());
        List<String> commandLines = new ArrayList<>(Q);
        for (int i = 0; i < Q; i++) {
            commandLines.add(scanner.nextLine());
        }
        scanner.close();
        StringBuilder result = new StringBuilder();
        commandLines.forEach(line -> execute(line, result));
        System.out.println(result);
    }

    private static void execute(String line, StringBuilder result) {
        String[] s = line.split(" ");
        int operation = Integer.parseInt(s[0]);
        switch (operation) {
            case 1 -> maxStack.push(Integer.parseInt(s[1]));
            case 2 -> maxStack.pop();
            case 3 -> result.append(maxStack.getMaxValue() + "\n");
            default -> throw new IllegalArgumentException();
        }
    }

    static class MaxStack {
        private Integer maxValue = Integer.MIN_VALUE;
        private final LinkedList<Pair<Integer>> list = new LinkedList<>();

        public MaxStack() {
        }

        public void push(Integer value) {
            maxValue = Math.max(maxValue, value);
            list.add(new Pair<>(value, maxValue));
        }

        public void pop() {
            list.pollLast();
            maxValue = getMaxValue();
        }

        public Integer getMaxValue() {
            return list.peekLast() == null ? Integer.MIN_VALUE : list.peekLast().right;
        }
    }

    static class Pair<T> {
        @Override
        public String toString() {
            return "v:" + left + " max:" + right;
        }

        T left;
        T right;

        public Pair(T left, T right) {
            this.left = left;
            this.right = right;
        }

        public T getLeft() {
            return left;
        }

        public T getRight() {
            return right;
        }
    }
}
