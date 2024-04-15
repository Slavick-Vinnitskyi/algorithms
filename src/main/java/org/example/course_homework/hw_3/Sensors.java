package org.example.course_homework.hw_3;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Сенсор має визначену відстань дії, поза якою він не може збирати інформацію.
 * Задача полягає у визначенні кількості можливих унікальних пар сенсорів,
 * які можуть функціонувати одночасно без перетину своїх зон впливу.
 * <p>
 * Input Format
 * <p>
 * У першому рядку вхідних даних зазначено два цілих числа n і r, де n - кількість сенсорів,
 * а r - максимальна відстань, на якій сенсори не повинні перекривати один одного.
 * <p>
 * У другому рядку подано n невід’ємних чисел d1, … , dn, кожне з яких представляє відстань
 * від i-го сенсора до початку лінії передачі.
 * Усі сенсори розміщені на різних відстанях від початку лінії та вказані в порядку зростання відстані.
 */
public class Sensors {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt(); // 3 * 10^5
        int radius = scanner.nextInt(); // 10^9

        int[] sensors = new int[size];

        for (int i = 0; i < size; i++) {
            int value = scanner.nextInt();
            sensors[i] = value;
        }

        long combinations = fast(sensors, radius);
        System.out.println(combinations);
    }


    public static long fast(int[] sensors, int radius) {
        int left = 0;
        int length = sensors.length;
        long result = 0;
        for (int right = 0; right < length; right++) {
            if (sensors[right] - sensors[left] > radius) {
                result += length - right;
                left++;
                right = left;
            }
        }
        return result;
    }

    public static int slow(int[] sensors, Map<Integer, Boolean> map, int radius) {
        int counter = 0;
        for (int i = 0; i < sensors.length; i++) {
            for (int j = i + 1; j < sensors.length; j++) {
                int sensor_left = sensors[i];
                int sensor_right = sensors[j];
                if (map.containsKey(sensor_left) && map.containsKey(sensor_right) && canWork(sensor_left, sensor_right, radius)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static boolean canWork(int i, int j, int radius) {
        return j - i > radius;
    }
}

class SensorsBenchmark {
    public static void main(String[] args) {
        int count = 0;
        while (true) {
            int[] arr = generateArray();
            int radius = 150;
            benchmark(arr.length, radius, arr);
            System.out.println(count++);
        }
    }

    private static int[] generateArray() {
        Random random = new Random();
        int size = random.nextInt(30_000 - 1) + 1;
        int[] ints = new int[size];
        ints[0] = 1;
        for (int i = 1; i < size; i++) {
            ints[i] = ints[i - 1] + random.nextInt(1000);
        }
        return ints;
    }

    public static void benchmark(int size, int radius, int[] arr) {
        int[] sensors = new int[size];
        Map<Integer, Boolean> sensors_map = new HashMap<>(size);
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            sensors[i] = value;
            sensors_map.put(value, true);
        }

        long start = System.nanoTime();
        long combinations1 = Sensors.fast(sensors, radius);
        long end1 = System.nanoTime();
        long combinations2 = Sensors.slow(sensors, sensors_map, radius);
        long end2 = System.nanoTime();

        if (combinations1 != combinations2) {
            System.out.println("fast :" + combinations1 + " time: " + (end1 - start));
            System.out.println("slow :" + combinations2 + " time: " + (end2 - end1));
        }
    }
}
