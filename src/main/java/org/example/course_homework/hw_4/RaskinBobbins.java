package org.example.course_homework.hw_4;

import java.util.*;

/**
 * Each time Sunny and Johnny take a trip to the Raskin Bobbins, they pool together  dollars for ice cream.
 * On any given day, the Raskin Bobbins offers a line of  flavors.
 * Each flavor, i, is numbered sequentially with a unique ID number from 1 to n and has a cost, Ci, associated with it.
 * <p>
 * Given the value of  and the cost of each flavor for  trips to the Raskin Bobbins, help Sunny and Johnny choose
 * two flavors such that they spend their entire pool of money (m) during each visit.
 * For each trip to the Raskin Bobbins, print the ID numbers for the two types of ice cream that
 * Sunny and Johnny purchase as two space-separated integers on a new line.
 * You must print the smaller ID first and the larger ID second.
 * <p>
 * Note: Two ice creams having unique IDs  and  may have the same cost (i.e.,  ).
 * <p>
 * Sample Input 0
 * <p>
 * 2
 * 4
 * 5
 * 1 4 5 3 2
 * 4
 * 4
 * 2 2 4 3
 * Sample Output 0
 * <p>
 * 1 4
 * 1 2
 */
public class RaskinBobbins {
    public static void main(String[] args) {
        List<String> results = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        for (int i = 0; i < t; i++) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            int[] flavours = new int[n];
            for (int j = 0; j < n; j++) {
                flavours[j] = scanner.nextInt();
            }
            results.add(solve(m, flavours));
        }

        for (String result : results) {
            System.out.println(result);
        }
    }

    private static String solve(int moneyPool, int[] flavours) {
        StringBuilder result = new StringBuilder();
        Map<Integer, Integer> flavoursDiff = new HashMap<>(flavours.length);
        for (int f = 0; f < flavours.length; f++) {
            flavoursDiff.put(flavours[f], f);
        }

        for (int f = 0; f < flavours.length; f++) {
            var diffElement = flavoursDiff.get(moneyPool - flavours[f]);
            if (diffElement != null && !diffElement.equals(f)) {
                result.append(f + 1);
                result.append(" ");
                result.append(diffElement + 1);
                break;
            }
        }
        return result.toString();
    }
}
