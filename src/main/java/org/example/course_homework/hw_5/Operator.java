package org.example.course_homework.hw_5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Мобільний оператор Lvivstar забезпечує мобільний зв'язок лише вздовж траси Київ-Львів.
 * Для цього він встановив вздовж траси N базових станцій, пронумерованих числами від 1 до N.
 * Кожна базова станція має певну зону покриття. Абонент може одночасно знаходитись в зоні покриття декількох станцій.
 * <p>
 * Абоненти Lvivstar — зайняті люди, що поспішають у своїх справах.
 * Тому час від часу вони потрапляють або виходять з зони покриття певних станцій оператора.
 * В ці моменти базова станція реєструє подію типу ENTER або LEAVE,
 * що означає збільнення або зменшення кількості клієнтів, що обслуговуються станцією, на 1.
 * <p>
 * "Залізо" від компанії Wuahei, яке використовується компанією, розраховане на певне максимальне навантаження.
 * Тому конче важливо відслідковувати поточне навантаження на різних суцільних ділянках ланцюжка станцій.
 * Розробку системи, яка б дозволила швидко знаходити цю інформацію, покладено на вас.
 * <p>
 * Реалізувати кореневу ідею.
 * Sample Input 0
 * <p>
 * 5
 * 2 0 2 3 1
 * 9
 * COUNT 2 4
 * ENTER 2
 * LEAVE 1
 * COUNT 2 4
 * LEAVE 5
 * COUNT 4 5
 * COUNT 1 2
 * ENTER 2
 * COUNT 1 2
 * Sample Output 0
 * <p>
 * 5
 * 6
 * 3
 * 2
 * 3
 */
public class Operator {

    private static int SEGMENT_SIZE = 1;
    private static int SEGMENT_COUNT = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int stationsCount = Integer.parseInt(scanner.nextLine());
        int[] stationsLoad = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int operationsCount = Integer.parseInt(scanner.nextLine());
        String[] operations = new String[operationsCount];
        for (int i = 0; i < operationsCount; i++) {
            operations[i] = scanner.nextLine();
        }
        scanner.close();

        Segment[] segments = divideBySegments(stationsLoad, stationsCount);
        StringBuilder sb = new StringBuilder();
        for (var operation : operations) {
            execute(segments, operation, sb);
        }
        System.out.println(sb);
    }

    private static Segment[] divideBySegments(int[] stations, int stationsCount) {
        for (int i = 1; i * i < stationsCount; i++) {
            SEGMENT_COUNT++;
        }
        SEGMENT_SIZE = stationsCount / SEGMENT_COUNT + 1;

        Segment[] segments = new Segment[SEGMENT_COUNT];
        int k = 0;
        for (int i = 0; i < stationsCount; i += SEGMENT_SIZE) {
            segments[k] = new Segment(SEGMENT_SIZE, stations, i, i + SEGMENT_SIZE);
            k++;
        }
        return segments;
    }

    private static void execute(Segment[] segments, String operation, StringBuilder result) {
        String[] args = operation.split(" ");
        String operationTypes = args[0];
        switch (operationTypes) {
            case "ENTER" -> enter(segments, Integer.parseInt(args[1]) - 1);
            case "LEAVE" -> leave(segments, Integer.parseInt(args[1]) - 1);
            case "COUNT" -> count(segments, Integer.parseInt(args[1]) - 1, Integer.parseInt(args[2]) - 1, result);
            default -> throw new UnsupportedOperationException();
        }
    }

    private static void count(Segment[] segments, int startIndex, int endIndex, StringBuilder result) {
        int startGroup = resolveGroupNumber(startIndex);
        int indexInFirstGroup = resolveIndex(startIndex, startGroup);
        int endGroup = resolveGroupNumber(endIndex);
        int indexInLastGroup = resolveIndex(endIndex, endGroup);
        int count = calculateCount(segments, startGroup, indexInFirstGroup, endGroup, indexInLastGroup);
        result.append(count);
        result.append("\n");
    }

    private static int calculateCount(Segment[] segments, int startGroup, int indexInFirstGroup, int endGroup, int indexInLastGroup) {
        int countInFirstSegment = 0;
        int countBetween = 0;
        int countInLastSegment = 0;

        if (startGroup == endGroup) {
            if (indexInLastGroup - indexInFirstGroup + 1 == segments[startGroup].stations.length) {
                return segments[startGroup].userCount;
            }
            for (int i = indexInFirstGroup; i <= indexInLastGroup; i++) {
                countInFirstSegment += segments[startGroup].stations[i];
            }
            return countInFirstSegment;
        }

        for (int i = indexInFirstGroup; i < SEGMENT_SIZE; i++) {
            countInFirstSegment += segments[startGroup].stations[i];
        }

        for (int i = 0; i <= indexInLastGroup; i++) {
            countInLastSegment += segments[endGroup].stations[i];
        }

        for (int i = startGroup + 1; i < endGroup; i++) {
            countBetween += segments[i].userCount;
        }

        return countInFirstSegment + countBetween + countInLastSegment;
    }

    private static void leave(Segment[] segments, int stationId) {
        int groupNumber = resolveGroupNumber(stationId);
        int index = resolveIndex(stationId, groupNumber);
        segments[groupNumber].leaveUser(index);
    }

    private static void enter(Segment[] segments, Integer stationId) {
        int groupNumber = resolveGroupNumber(stationId);
        int index = resolveIndex(stationId, groupNumber);
        segments[groupNumber].enterUser(index);
    }

    private static int resolveGroupNumber(int inputIndex) {
        if (inputIndex <= SEGMENT_SIZE) {
            return 0;
        }
        return inputIndex / SEGMENT_SIZE;
    }


    private static int resolveIndex(int inputIndex, int groupNumber) {
        return inputIndex - groupNumber * SEGMENT_SIZE;
    }

    static class Segment {
        int userCount;
        int[] stations;

        public Segment(int groupSize, int[] stations, int start, int end) {
            this.stations = new int[groupSize];
            int j = 0;
            for (int i = start; i < Math.min(stations.length, end); i++) {
                this.stations[j] = stations[i];
                this.userCount += stations[i];
                j++;
            }
        }

        public void setValue(int i, int value) {
            this.userCount = this.userCount - this.stations[i] + value;
            this.stations[i] = value;
        }

        public void enterUser(int station) {
            setValue(station, this.stations[station] + 1);
        }

        public void leaveUser(int station) {
            setValue(station, this.stations[station] - 1);
        }

        @Override
        public String toString() {
            return "Segment{userCount=%d, stations=%s}".formatted(userCount, Arrays.toString(stations));
        }
    }
}
