package org.example.leetcode;

public class MergeStrings {
    public static void main(String[] args) {
        System.out.println(mergeLinear("abcooo", "gjk"));
    }

    private static String mergeLinear(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int limit = Math.min(len1, len2);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < limit; i++) {
            builder.append(word1.charAt(i));
            builder.append(word2.charAt(i));
        }
        builder.append(word1, limit, len1);
        builder.append(word2, limit, len2);
        return builder.toString();
    }
}
