package org.example.leetcode;

public class NumberPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome(11334443311L));
        System.out.println(isPalindromeWhile(11334443311L));
    }

    public static boolean isPalindrome(long x) {
        int zerosCount = (int) Math.round(Math.floor(Math.log10(x))) + 1;
        if (x < 0) {
            return false;
        }
        if (zerosCount <= 1) {
            return true;
        }
        long divider = 10;
        long resultNumber = 0;
        for (int i = 0; i < zerosCount; i++) {
            int digit = (int) ((x % divider) / (divider / 10));
            resultNumber += digit * (long) Math.round(Math.pow(10, zerosCount - i - 1));
            divider *= 10;
        }
        return x / resultNumber == 1;
    }

    public static boolean isPalindromeWhile(long x) {
        if (x < 0) {
            return false;
        }
        long copyX = x;
        long divider = 10;
        long resultNumber = 0;
        int i = 0;
        while (copyX != 0) {
            int digit = (int) ((x % divider) / (divider / 10));
            resultNumber += digit * Math.pow(10, i);
            divider *= 10;
            i++;
            copyX /= 10;
        }

        return x / resultNumber == 1;
    }
}
