package org.example.course_homework.hw_5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * This question is designed to help you get a better understanding of basic heap operations.
 * <p>
 * There are  types of query:
 * <p>
 * "1 e" - Add an element e to the heap.
 * "2 e" - Delete the element e from the heap.
 * "3" - Print the minimum of all the elements in the heap.
 * NOTE: It is guaranteed that the element to be deleted will be there in the heap.
 * Also, at any instant, only distinct elements will be in the heap.
 * Input Format
 * <p>
 * The first line contains the number of queries, Q.
 * Each of the next Q lines contains one of the 3 types of query.
 * <p>
 * Constraints
 * 1 <= Q <= 10^5
 * -10^9 <= e <= 10^9
 * <p>
 * Output Format
 * <p>
 * For each query of type 3, print the minimum value on a single line.
 * <p>
 * Sample Input
 * <p>
 * STDIN       Function
 * -----       --------
 * 5           Q = 5
 * 1 4         insert 4
 * 1 9         insert 9
 * 3           print minimum
 * 2 4         delete 4
 * 3           print minimum
 * Sample Output
 * <p>
 * 4
 * 9
 */
public class QHeap1 {

    private static MyHeap heap;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int Q = Integer.parseInt(scanner.nextLine());
        List<String> commandLines = new ArrayList<>(Q);
        for (int i = 0; i < Q; i++) {
            commandLines.add(scanner.nextLine());
        }
        scanner.close();

        heap = new MyHeap(Q);
        StringBuilder result = new StringBuilder();
        commandLines.forEach(line -> execute(line, result));
        System.out.println(result);
    }

    private static void execute(String line, StringBuilder result) {
        String[] s = line.split(" ");
        int operation = Integer.parseInt(s[0]);
        switch (operation) {
            case 1 -> insert(Integer.parseInt(s[1]));
            case 2 -> delete(Integer.parseInt(s[1]));
            case 3 -> printMin(result);
            default -> throw new IllegalArgumentException();
        }
    }

    private static void insert(Integer value) {
        heap.listAppend(value);
        int index = heap.siftUp(heap.getListSize() - 1);
        heap.indexes.put(value, index);
    }

    private static void delete(Integer value) {
        int index = heap.indexes.get(value);
        heap.swap(index, heap.getListSize() - 1);
        heap.listReduce();
        heap.indexes.remove(value);

        heap.siftDown(index);
    }

    private static void printMin(StringBuilder result) {
        Integer first = heap.findFirst();
        if (first == null) {
            return;
        }
        result.append(first);
        result.append("\n");
    }

}

class MyHeap {
    private final List<Integer> list;
    public final Map<Integer, Integer> indexes;

    public MyHeap(int maxSize) {
        list = new ArrayList<>(maxSize);
        listAppend(null);
        indexes = new HashMap<>(maxSize);
    }

    public Integer findFirst() {
        if (list.size() <= 1) {
            return null;
        }
        return list.get(1);
    }

    public int siftUp(int index) {
        int parentIndex = index / 2;
        while (parentIndex >= 1 && list.get(parentIndex) > list.get(index)) {
            swap(parentIndex, index);
            index = parentIndex;
            parentIndex /= 2;
        }
        return index;
    }

    public void siftDown(int index) {
        int listSize = getListSize();
        int startIndex = index;
        while (startIndex * 2 < listSize) {
            var leftIndex = index * 2;
            var rightIndex = index * 2 + 1;

            if (list.get(leftIndex) < list.get(index)) { // left < parent
                index = leftIndex;
            }
            if (rightIndex < listSize && list.get(rightIndex) < list.get(index)) { // right < (parent or left)
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
        Integer first = list.get(i1);
        Integer second = list.get(i2);
        list.set(i1, second);
        list.set(i2, first);
        indexes.put(first, i2);
        indexes.put(second, i1);
    }

    public void listAppend(Integer value) {
        this.list.add(value);
    }

    public int getListSize() {
        return this.list.size();
    }

    public void listReduce() {
        this.list.remove(list.size() - 1);
    }
}