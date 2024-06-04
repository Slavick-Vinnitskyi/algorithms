package org.example.course_homework.hw_7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class AdvancedStack {

    private static final Stack maxStack = new Stack();

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
            case 2 -> maxStack.poll();
            case 3 -> result.append(maxStack.getMaxValue()).append("\n");
            default -> throw new IllegalArgumentException();
        }
    }

    static class Pair<T extends Integer> {
        @Override
        public String toString() {
            return "el:" + element + " prop:" + property;
        }

        T element;
        T property;

        public Pair(T left, T property) {
            this.element = left;
            this.property = property;
        }
    }

    static class Stack {
        Integer maxValue = Integer.MIN_VALUE;
        Node<Pair<Integer>> head;
        int size;

        public Stack() {
            head = null;
            size = 0;
        }

        public void push(Integer value) {
            maxValue = Math.max(maxValue, value);
            head = new Node<>(new Pair<>(value, maxValue), head);
            size++;
        }

        public Node<Pair<Integer>> poll() {
            if (size == 0) {
                return null;
            }
            var node = head;
            head = head.next;
            maxValue = getMaxValue();
            size--;
            return node;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public Integer getMaxValue() {
            return head == null ? Integer.MIN_VALUE : head.value.property;
        }
    }

    static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

}
