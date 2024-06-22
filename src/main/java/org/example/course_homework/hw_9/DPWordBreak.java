package org.example.course_homework.hw_9;

import java.util.List;

/*************************************************************************
 * 139. Word Break
 * Given a string s and a dictionary of strings wordDict,
 * return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 */

public class DPWordBreak {

    public static void main(String[] args) {
        var wordBreak = wordBreak("aaaaaaa", List.of("aaaa", "aa"));
        System.out.println(wordBreak);
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        return wordBreakInternal(s, wordDict, 0, new Boolean[s.length()]);
    }

    public static boolean wordBreakInternal(String currentWord, List<String> breakWords, int index, Boolean[] dp) {
        if (dp[index] != null) {
            return dp[index];
        }
        if (index == currentWord.length()) {
            return true;
        }

        for (int j = 0; j < breakWords.size(); j++) {
            var breakWord = breakWords.get(j);
            if (canBreak(currentWord, breakWord, index)) {
                index += breakWord.length();

                var result = wordBreakInternal(currentWord, breakWords, index, dp);
                dp[index] = result;
                if (result) {
                    return true;
                }

                index -= breakWord.length();
            }
        }
        return false;
    }

    private static boolean canBreak(String currentWord, String breakWord, int index) {
        return currentWord.startsWith(breakWord, index);
    }
}