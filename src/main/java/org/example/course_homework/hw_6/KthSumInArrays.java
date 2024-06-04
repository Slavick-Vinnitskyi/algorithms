//package org.example.course_homework.hw_6;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
///**
// * You are given two arrays A, B and integer K.
// * Your task is to find the K-th smallest value (1-based) among all sums a + b where a ∈ A, b ∈ B
// * <p>
// * Input Format
// * <p>
// * The first line contains integer N which is the size of array A.
// * The second line contains N integers separated by single spaces A1, … ,AN — the elements of array A
// * <p>
// * The next line contains integer M which is the size of array B.
// * The following line contains M integers separated by single spaces B1, … ,BM — the elements of array B
// * <p>
// * The last line contains integer K
// * <p>
// * Constraints
// * <p>
// * 1 <= N, M <= 200000
// * −1000000000 <= Aj, Bj <= 1000000000
// * 1 <= K <= N*M
// * Output Format
// * <p>
// * The K-th smallest value.
// * <p>
// * Sample Input 0
// * <p>
// * 3
// * 2 3 4
// * 3
// * 3 2 1
// * 4
// * Sample Output 0
// * <p>
// * 5
// * Sample Input 1
// * <p>
// * 6
// * -20 -7 12 13 14 90
// * 5
// * -8 -4 -2 0 30
// * 5
// * Sample Output 1
// * <p>
// * -15
// */
//public class KthSumInArrays {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int N = scanner.nextInt();
//        List<Integer> a = new ArrayList<>(N);
//        for (int i = 0; i < N; i++) {
//            a.add(scanner.nextInt());
//        }
//
//        int M = scanner.nextInt();
//        List<Integer> b = new ArrayList<>(N);
//        for (int i = 0; i < M; i++) {
//            b.add(scanner.nextInt());
//        }
//
//        int K = scanner.nextInt();
//
//        int sum = findKSmallestSum(a, b, K);
//        System.out.println(sum);
//    }
//
//    private static int findKSmallestSum(List<Integer> a, List<Integer> b, int k) {
//        a.sort(Integer::compareTo);
//        b.sort(Integer::compareTo);
//
//        int good = a.getLast() + b.getLast() + 1;
//        int bad = a.getFirst() + b.getFirst() - 1;
//
//        while (bad != good) {
//            int medium = (bad + good) / 2;
//            if (isGood(a, b, medium, k)) {
//                bad = medium;
//            } else {
//                good = medium;
//            }
//        }
//
//        return good;
//    }
//
//
//    /**
//     * Should answer to question : Can value be on k-th position of a + b sums
//     */
//    private static boolean isGood(List<Integer> a, List<Integer> b, int value, int k) {
//        var minValuePosition = 0;
//        if (a.getFirst() + b.getLast() < value) {
//            minValuePosition = k;
//        }
//
//
//        return minValuePosition >= k;
//    }
//}
