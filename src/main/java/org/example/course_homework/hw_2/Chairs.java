package org.example.course_homework.hw_2;

import java.util.Scanner;

/**
 * Кімната
 * У багатьох книгах з математики присутня така задача. Розставити по периметру трикутної кімнати 3 стільці так, щоб у кожної стіни стояло по 2.
 * Її рішення - поставити по стільчику в кожен з кутів кімнати.
 * <p>
 * Цю задачу можна узагальнити. Нехай кімната є трикутником ABC. Данo: загальна кількість стільців n,
 * кількість стільців nAB, що має стояти біля стіни AB, кількість стільців nBC,
 * що має стояти біля стіни BC, кількість стільців nAC, що має стояти біля стіни AC.
 * <p>
 * Необхідно знайти відповідне розташування стільців або встановити, що його не існує.
 * При цьому стільці можна ставити тільки в кути кімнати та вздовж стін, в центр кімнати ставити не можна.
 * У будь-який з кутів можна поставити довільну кількість стільців.
 * <p>
 * Input Format
 * <p>
 * Вхід містить 4 цілих числа n, nAB, nBC, nAC
 * <p>
 * Constraints
 * <p>
 * 0 ≤ n, nAB, nBC, nAC ≤ 1000
 * <p>
 * Output Format
 * <p>
 * У першому рядку виведіть NO, якщо стільці вказаним способом розставити неможливо.
 * В іншому випадку виведіть YES у першому рядку,
 * а в другому виведіть 6 цілих невід'ємних чисел: kA, kAB, kB, kBC, kC, kAC - відповідно кількість стільців,
 * які необхідно поставити в кут A вздовж стіни AB, у кут B вздовж стіни BC, і в кут C вздовж стіни AC.
 * <p>
 * Sample Input 0
 * <p>
 * 3 2 2 2
 * Sample Output 0
 * <p>
 * YES
 * 1 0 1 0 1 0
 * Sample Input 1
 * <p>
 * 197 195 781 90
 * Sample Output 1
 * <p>
 * NO
 * Sample Input 2
 * <p>
 * 15 7 10 13
 * Sample Output 2
 * <p>
 * YES
 * 5 0 2 0 8 0
 * Sample Input 3
 * <p>
 * 2 2 0 1
 * Sample Output 3
 * <p>
 * YES
 * 1 1 0 0 0 0
 * Sample Input 4
 * <p>
 * 471 790 109 43
 * Sample Output 4
 * <p>
 * NO
 * Sample Input 5
 * <p>
 * 2 4 0 0
 * Sample Output 5
 * <p>
 * NO
 */
public class Chairs {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int limit = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        long start = System.currentTimeMillis();
        solveFast(limit, a, b, c);
//        solveSlow(limit, a, b, c);

        long end = System.currentTimeMillis();
        System.out.printf("\nTIME %d! \n", (end - start));
//        IntStream.range(900, 1000).forEach(i -> benchmark(i, i - i / 10, i - i / 5, i - i / 4));

    }

    private static void benchmark(int limit, int a, int b, int c) {
        long start = System.currentTimeMillis();
//
        long end = System.currentTimeMillis();
        System.out.printf("TIME %d! %d %d, %d, %d \n", (end - start), limit, a, b, c);
    }

    private static boolean solveFast(int limit, int As, int Bs, int Cs) {
        if (As > limit || Bs > limit || Cs > limit) {
            return false;
        }

        if (limit > As + Bs + Cs) {
            return false;
        }
        if (As + Bs + Cs > limit * 3) {
            return false;
        }

        for (int ac = 0; ac <= Math.min(As, Cs); ac++) {
            for (int ab = 0; ab <= Math.min(As, Bs); ab++) {
                for (int bc = 0; bc <= Math.min(Bs, Cs); bc++) {
                    if (As - ab - ac < 0) continue;
                    if (Bs - ab - bc < 0) continue;
                    if (Cs - ac - bc < 0) continue;
                    int a = As - ab - ac;
                    int b = Bs - ab - bc;
                    int c = Cs - ac - bc;
                    int aSum = a + ac + ab;
                    int bSum = b + bc + ab;
                    int cSum = c + ac + bc;
                    if (As == aSum && Bs == bSum && Cs == cSum && (a + b + c + ac + bc + ab == limit)) {
                        System.out.printf("YES\n%d %d %d %d %d %d", ac, a, ab, b, bc, c);
                        return true;

                    }
                }
            }
        }
        return false;
    }

    private static boolean solveSlow(int limit, int As, int Bs, int Cs) {
        if (As > limit || Bs > limit || Cs > limit) {
            return false;
        }

        if (limit > As + Bs + Cs) {
            return false;
        }
        if (As + Bs + Cs > limit * 3) {
            return false;
        }

        for (int a = As; a >= 0; a--) {
            for (int ab = limit - a; ab >= a; ab--) {
                for (int b = Bs - ab; b >= ab; b--) {
                    for (int bc = limit - b; bc >= b; bc--) {
                        for (int c = Cs - bc; c >= bc; c--) {
                            int bSum = b + bc + ab;
                            for (int ac = limit - c; ac >= c; ac--) {
                                int aSum = a + ac + ab;
                                int cSum = c + ac + bc;
                                if (As == aSum && Bs == bSum && Cs == cSum && (a + b + c + ac + bc + ab == limit)) {
                                    System.out.printf("YES\n%d %d %d %d %d %d", ac, a, ab, b, bc, c);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean solveSlowAndStupid(int limit, int As, int Bs, int Cs) {
        if (As > limit || Bs > limit || Cs > limit) {
            return false;
        }

        if (limit > As + Bs + Cs) {
            return false;
        }
        if (As + Bs + Cs > limit * 3) {
            return false;
        }

        for (int ac = 0; ac <= Math.min(As, Cs); ac++) {
            for (int ab = 0; ab <= Math.min(As, Bs); ab++) {
                for (int bc = 0; bc <= Math.min(Bs, Cs); bc++) {
                    if (As - ab - ac < 0) continue;
                    if (Bs - ab - bc < 0) continue;
                    if (Cs - ac - bc < 0) continue;
                    for (int a = As - ab - ac; a >= 0; a--) {
                        for (int b = Bs - ab - bc; b >= 0; b--) {
                            for (int c = Cs - ac - bc; c >= 0; c--) {
                                int aSum = a + ac + ab;
                                int bSum = b + bc + ab;
                                int cSum = c + ac + bc;
                                if (As == aSum && Bs == bSum && Cs == cSum && (a + b + c + ac + bc + ab == limit)) {
                                    System.out.printf("YES\n%d %d %d %d %d %d", ac, a, ab, b, bc, c);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}