package org.example.leetcode;

/**
 * 125. Valid Palindrome
 * https://leetcode.com/problems/valid-palindrome/description/
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all
 * non-alphanumeric characters, it reads the same forward and backward.
 * Alphanumeric characters include letters and numbers.
 * <p>
 * Given a string s, return true if it is a palindrome, or false otherwise.
 */
public class ValidPalindrome {
    public static void main(String[] args) {
        boolean palindrome = isPalindromeInlineCheck("   ");
        boolean palindrome1 = isPalindromePreProcessString("   ");
        System.out.println(palindrome);
        System.out.println(palindrome1);
    }

    private static boolean isPalindromePreProcessString(String s) {
        StringBuilder newString = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean alphabetic = Character.isAlphabetic(c);
            boolean digit = Character.isDigit(c);
            if (alphabetic || digit) {
                newString.append(c);
            }
        }
        var string = newString.toString().toLowerCase();
        if (string.isEmpty()) {
            return true;
        }
        int start = 0;
        int end = string.length() - 1;
        while (start <= end) {
            if (string.charAt(start) != string.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    private static boolean isPalindromeInlineCheck(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start <= end) {
            while (!isGood(s, start)) { // skip not good chars from the start
                if (start >= end) {
                    break;
                }
                start++;
            }
            while (!isGood(s, end)) { // skip not good chars from the end
                if (start >= end) {
                    break;
                }
                end--;
            }
            if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) { // compare good chars
                return false;
            }
//            move to next iteration
            start++;
            end--;
        }
        return true;
    }

    private static boolean isGood(String s, int i) {
        char c = s.charAt(i);
        boolean alphabetic = Character.isAlphabetic(c);
        boolean digit = Character.isDigit(c);
        return alphabetic || digit;
    }
}
