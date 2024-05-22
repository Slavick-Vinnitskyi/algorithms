package org.example.course_homework.hw_6;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * There are N classes in a school, each with M students.
 * There is going to be a race of 100m dash, and a representative from each class will be chosen to participate in this race.
 * You were assigned a task to choose these representatives.
 * Since you did not want the race to be one sided, you wanted to choose the representatives such that the
 * difference between the ability of the best representative and the worst representative is minimal.
 * For example,
 * <p>
 * if N = 3 and M = 4, and each class has students with following abilities:
 * <p>
 * Class 1: {12, 16, 67, 43}
 * <p>
 * Class 2: {7, 17, 68, 48}
 * <p>
 * Class 3: {14, 15, 77, 54}
 * it is best to choose the student with ability 16 from Class 1, 17 from Class 2, and 15 from Class 3.
 * Thus, the difference in this case would be 17 - 15 = 2.
 * <p>
 * Your task is to calculate the minimal possible difference you can achieve by choosing a representative from each class.
 * <p>
 * Input Format
 * <p>
 * The first line of the input consists of two integers, N and M
 * <p>
 * The next N lines will have M integers. The jth element of ith line is the ability of the jth student in ith class.
 * <p>
 * Constraints
 * <p>
 * 1 <= N <= 1000
 * <p>
 * 1 <= M <= 1000
 * <p>
 * The numbers are between 0 and 10^9, inclusive.
 * <p>
 * Output Format
 * <p>
 * Output the minimal difference one can achieve by choosing the representative from each class.
 * <p>
 * Sample Input 0
 * <p>
 * 3 4
 * 12 16 67 43
 * 7 17 68 48
 * 14 15 77 54
 * Sample Output 0
 * <p>
 * 2
 */

public class MultipleMin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        List<List<Integer>> students = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            var row = new ArrayList<Integer>();
            for (int j = 0; j < M; j++) {
                row.add(scanner.nextInt());
            }
            row.add(Integer.MAX_VALUE);
            row.sort(Integer::compareTo);
            students.add(row);
        }

        var iterators = students.stream().map(List::listIterator).collect(Collectors.toList());
        var elements = iterators.stream().map(ListIterator::next).collect(Collectors.toList());

        int minDiff = Integer.MAX_VALUE;

        while (iterators.stream().allMatch(ListIterator::hasNext)) {
            var maxAbilityIndex = findAbilityIndex(elements, (max, integer) -> max < integer);
            var minAbilityIndex = findAbilityIndex(elements, (min, integer) -> min > integer);
            var maxAbility = elements.get(maxAbilityIndex);
            var minAbility = elements.get(minAbilityIndex);

            minDiff = Math.min(minDiff, maxAbility - minAbility);
            elements.set(minAbilityIndex, iterators.get(minAbilityIndex).next());
        }

        System.out.println(minDiff);
    }

    private static Integer findAbilityIndex(List<Integer> elements, BiFunction<Integer, Integer, Boolean> isBetter) {
        int good = elements.get(0);
        int index = 0;
        for (int i = 0; i < elements.size(); i++) {
            if (isBetter.apply(good, elements.get(i))) {
                good = elements.get(i);
                index = i;
            }
        }
        return index;
    }
}
