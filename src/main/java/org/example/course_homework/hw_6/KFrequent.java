package org.example.course_homework.hw_6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toList;

/**
 * You are given a string of length N. The string consists of lowercase english alphabets only i.e. a to z.
 * You are also given an integer K. You need the find the Kth most frequent alphabet in the string.
 * If more than one alphabet share the Kth frequency,
 * you need to find the lexicographically smallest alphabet among them.
 * In case no Kth most popular alphabet exists, you need to write "NONE" (without the inverted commas).
 * The first line contains T, the number of test cases in the input.
 * <p>
 * This is follwed by T test cases, each test case containing 2 lines.
 * <p>
 * The first line of each test case contains the values N and K for the test case, and the second line contains the string.
 */
public class KFrequent {
    private static MinHeap<CodePointEntry> heap;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int[] codePoints = scanner.next().codePoints().toArray();
            var entries = Arrays.stream(codePoints).boxed()
                    .collect(Collectors.groupingBy(Function.identity(), collectingAndThen(counting(), Long::intValue)));
            var codePointEntries = entries.entrySet().stream()
                    .map(e -> new CodePointEntry(e.getKey(), e.getValue()))
                    .collect(toList());
            heap = new MinHeap<>(k);
            codePointEntries.forEach(cp -> addValue(cp, k));
            result.append(heap.getSize() < k ? "NONE" : heap.get(1));
            result.append("\n");
        }
        scanner.close();
        System.out.println(result);
    }

    private static void addValue(CodePointEntry codePointEntry, int k) {
        if (heap.indexOf(codePointEntry) != -1) {
            var indexOfFrequency = heap.indexOf(codePointEntry);
            if (heap.get(indexOfFrequency).comparePoint(codePointEntry) > 0) {
                heap.append(codePointEntry);
                heap.swap(indexOfFrequency, heap.getSize());
                heap.removeLast();
            }
        } else if (heap.getSize() < k) {
            insert(codePointEntry);
        } else if (heap.get(1).compareTo(codePointEntry) < 0) {
            deleteFirst();
            insert(codePointEntry);
        }
    }

    private static void insert(CodePointEntry value) {
        heap.append(value);
        heap.siftUp(heap.getSize());
    }

    private static void deleteFirst() {
        heap.swap(1, heap.getSize());
        heap.removeLast();
        heap.siftDown(1);
    }
}


class MinHeap<T extends Comparable<T>> {
    private final List<T> list;

    public MinHeap(int maxSize) {
        list = new ArrayList<>(maxSize);
        append(null);
    }

    public T get(int index) {
        if (list.size() <= index) {
            return null;
        }
        return list.get(index);
    }

    public void siftUp(int index) {
        int parentIndex = index / 2;
        while (parentIndex >= 1 && list.get(parentIndex).compareTo(list.get(index)) > 0) {
            swap(parentIndex, index);
            index = parentIndex;
            parentIndex /= 2;
        }
    }

    public void siftDown(int index) {
        int listSize = this.list.size();
        int startIndex = index;
        while (startIndex * 2 < listSize) {
            var leftIndex = index * 2;
            var rightIndex = index * 2 + 1;

            if (list.get(leftIndex).compareTo(list.get(index)) < 0) { // left < parent
                index = leftIndex;
            }
            if (rightIndex < listSize && list.get(rightIndex).compareTo(list.get(index)) < 0) { // right < (parent or left)
                index = rightIndex;
            }

            if (startIndex == index) {
                break;
            }

            swap(startIndex, index);
            startIndex = index;
        }
    }

    public void swap(int i1, int i2) {
        var first = list.get(i1);
        var second = list.get(i2);
        list.set(i1, second);
        list.set(i2, first);
    }

    public void append(T value) {
        this.list.add(value);
    }

    public void removeLast() {
        this.list.remove(getSize());
    }

    public int indexOf(T codePointEntry) {
        return list.indexOf(codePointEntry);
    }

    public int getSize() {
        return this.list.size() - 1;
    }
}


class CodePointEntry implements Comparable<CodePointEntry> {

    private final int codePoint;
    private final int frequency;

    public CodePointEntry(int codePoint, int frequency) {
        this.codePoint = codePoint;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(CodePointEntry o) {
        return Integer.compare(this.frequency, o.frequency);
    }

    public int comparePoint(CodePointEntry o) {
        return Integer.compare(this.codePoint, o.codePoint);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CodePointEntry)) {
            return false;
        }

        return frequency == ((CodePointEntry) o).frequency;
    }

    @Override
    public String toString() {
        return Character.toString(codePoint);
    }
}
