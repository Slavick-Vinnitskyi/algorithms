package org.example.course_homework.hw_4;

import java.util.Scanner;

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
        double secondPointMax = a * n;
        double medium;
        double calculatedMinB = 0;
        double res = 0;
        while (secondPointMax - secondPointMin > 0.00000001) {
            medium = (secondPointMin + secondPointMax) / 2;
            calculatedMinB = calculatedMinB(n, a, medium);
            if (calculatedMinB > 0) {
                res = calculatedMinB;
                secondPointMax = medium;
            } else {
                secondPointMin = medium;
            }
        }

        return res;
    }

    private static double calculatedMinB(int n, double a, double secondPoint) {
        double firstPoint = a;
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
