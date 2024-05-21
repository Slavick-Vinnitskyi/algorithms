package org.example.course_homework.hw_6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Дано n відрізків та m точок на числовій прямій. Для кожної з точок визначте, скільком відрізкам вона належить.
 * Точка x належить відрізку з кінцями в a і b, якщо виконується нерівність min(a, b) <= x <= max(a, b).
 * <p>
 * Рішення має працювати швидше за О(n*m).
 * <p>
 * Input Format
 * <p>
 * Перший рядок містить два цілих числа n – число відрізків та m – число точок.
 * У наступних n рядках по два цілі числа ai і bi - координати кінців відповідного відрізка.
 * В останньому рядку m цілих чисел - координати точок.
 * Constraints
 * 1 <= n <= 10^5
 * 1 <= m <= 10^5
 * <p>
 * Output Format
 * Виведіть m чисел – для кожної точки кількість відрізків яким вона належить.
 */

public class PointsAndSegments {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<int[]> segments = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            segments.add(new int[]{scanner.nextInt(), scanner.nextInt()});
        }
        List<Integer> points = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            points.add(scanner.nextInt());
        }
        scanner.close();

        List<SegmentEdge> edges = transformSegment(segments);
        edges.sort(Comparator.comparing(SegmentEdge::getCoordinate).thenComparing(SegmentEdge::getType));

        var startCoordinate = edges.get(0).getCoordinate();
        var finishCoordinate = edges.get(edges.size() - 1).getCoordinate();
        int segmentPointer = 0;
        int segmentCount = 0;
        Map<Integer, Integer> pointToSegmentCount = new HashMap<>();
        for (int currentCoordinate = startCoordinate; currentCoordinate <= finishCoordinate; currentCoordinate++) {

            while (coordinateHasEdge(segmentPointer, edges, currentCoordinate) && edges.get(segmentPointer).isStart()) {
                segmentCount++;
                segmentPointer++;
            }

            pointToSegmentCount.put(currentCoordinate, segmentCount);

            while (coordinateHasEdge(segmentPointer, edges, currentCoordinate) && edges.get(segmentPointer).isEnd()) {
                segmentCount--;
                segmentPointer++;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int point : points) {
            result.append(pointToSegmentCount.getOrDefault(point, 0)).append(" ");
        }
        System.out.println(result);
    }

    private static boolean coordinateHasEdge(int segmentPointer, List<SegmentEdge> edges, int currentCoordinate) {
        return segmentPointer < edges.size() && edges.get(segmentPointer).getCoordinate() == currentCoordinate;
    }

    private static List<SegmentEdge> transformSegment(List<int[]> segments) {
        List<SegmentEdge> result = new ArrayList<>();
        for (int[] segment : segments) {
            SegmentEdge start = new SegmentEdge(segment[0], -1);
            SegmentEdge end = new SegmentEdge(segment[1], 1);
            result.add(start);
            result.add(end);
        }
        return result;
    }

    private static class SegmentEdge {
        public int getCoordinate() {
            return coordinate;
        }

        public int getType() {
            return type;
        }

        public boolean isStart() {
            return type == -1;
        }

        public boolean isEnd() {
            return type == 1;
        }

        int coordinate;
        int type;

        public SegmentEdge(int coordinate, int type) {
            this.coordinate = coordinate;
            this.type = type;
        }
    }
}
