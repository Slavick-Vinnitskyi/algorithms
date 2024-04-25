package org.example.course_homework.hw_4;

import java.util.Scanner;

/**
 * The New Year garland consists of N lamps attached to a common wire that hangs down on the ends to which outermost lamps
 * are affixed.
 * The wire sags under the weight of lamp in a particular way: each lamp is hanging at the height that is 1 millimeter
 * lower than the average height of the two adjacent lamps.
 * <p>
 * The leftmost lamp is hanging at the height of A millimeters above the ground. You have to determine the lowest height B
 * of the rightmost lamp so that no lamp in the garland lies on the ground though some of them may touch the ground.
 * <p>
 * You shall neglect the lamp's size in this problem. By numbering the lamps with integers from 1 to N and denoting the i-th
 * lamp height in millimeters as H-i we derive the following equations:
 * H1 = A
 * Hi = (Hi-1 + Hi+1) / 2 - 1 for 1 < i < N
 * НN = B
 * Hi >= 0 for all i
 */
public class Garland {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double A = scanner.nextDouble();
        scanner.close();

        double B = calculateMinB(n, A);
        System.out.println(B);
    }

    private static double calculateMinB(int n, double a) {
        double secondPointMin = 0;
        double secondPointMax = a;
        double medium;
        double calculatedB;
        double resultB = 0;

        while (secondPointMax - secondPointMin > 0.00000001) {
            medium = (secondPointMin + secondPointMax) / 2;
            calculatedB = calculateLastPoint(n, a, medium);
            if (calculatedB > 0) {
                secondPointMax = medium;
                resultB = calculatedB;
            } else {
                secondPointMin = medium;
            }
        }

        return resultB;
    }

    private static double calculateLastPoint(int n, double firstPoint, double secondPoint) {
        double nextPoint = 0;

        for (int i = 2; i < n; i++) {
            nextPoint = 2 * (secondPoint + 1) - firstPoint;
            if (nextPoint < 0) {
                return -1;
            }
            firstPoint = secondPoint;
            secondPoint = nextPoint;
        }
        return nextPoint;
    }

}
