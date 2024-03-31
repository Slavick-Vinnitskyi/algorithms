package org.example.course_homework.hw_0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * You have two strings of lowercase English letters. You can perform two types of operations on the first string:
 * <p>
 * Append a lowercase English letter to the end of the string.
 * Delete the last character of the string. Performing this operation on an empty string results in an empty string.
 * Given an integer k, and two strings, s and t, determine whether or not you can convert s to t
 * by performing exactly k of the above operations on s. If it's possible, print Yes. Otherwise, print No.
 * <p>
 * Example.
 * s = [a,b,c] . t = [d,e,f] . k=6
 * <p>
 * To convert s to t, we first delete all the characters in 3 moves. Next we add each of the characters of t in order.
 * On the 6 move, you will have the matching string. Return Yes.
 * <p>
 * If there were more moves available, they could have been eliminated by performing multiple deletions on an empty string.
 * If there were fewer than  moves, we would not have succeeded in creating the new string.
 * <p>
 * Function Description
 * <p>
 * Complete the appendAndDelete function in the editor below. It should return a string, either Yes or No.
 * <p>
 * appendAndDelete has the following parameter(s):
 * <p>
 * string s: the initial string
 * string t: the desired string
 * int k: the exact number of operations that must be performed
 * Returns
 * <p>
 * string: either Yes or No
 * Input Format
 * <p>
 * The first line contains a string , the initial string.
 * The second line contains a string , the desired final string.
 * The third line contains an integer , the number of operations.
 * <p>
 * Constraints
 * <p>
 * 1 <= s <= 100
 * 1 <= t <= 100
 * 1 <= k <= 100
 * s and t consist of lowercase English letters [a=-z].
 * Sample Input 0
 * <p>
 * hackerhappy
 * hackerrank
 * 9
 * Sample Output 0
 * <p>
 * Yes
 * Explanation 0
 * <p>
 * We perform 5 delete operations to reduce string  to hacker.
 * Next, we perform 4 append operations (i.e., r, a, n, and k), to get hackerrank.
 * Because we were able to convert s to t by performing exactly k = 9 operations, we return Yes.
 * <p>
 * Sample Input 1
 * <p>
 * aba
 * aba
 * 7
 * Sample Output 1
 * <p>
 * Yes
 * Explanation 1
 * <p>
 * We perform 4 delete operations to reduce string s to the empty string.
 * Recall that though the string will be empty after 3 deletions, we can still perform a delete operation on an
 * empty string to get the empty string.
 * Next, we perform 3  append operations (i.e., a, b, and a).
 * Because we were able to convert s to t by performing exactly k=7 operations, we return Yes.
 * <p>
 * Sample Input 2
 * ashley
 * ash
 * 2
 * Sample Output 2
 * No
 * Explanation 2
 * <p>
 * To convert ashley to ash a minimum of  steps are needed. Hence we print No as answer.
 */
public class AppendAndDelete {

    public static void main(String[] args) throws IOException {
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var s = bufferedReader.readLine();
        var t = bufferedReader.readLine();
        var k = Integer.parseInt(bufferedReader.readLine().trim());
        bufferedReader.close();

        System.out.println(appendAndDelete(s, t, k));
    }

    public static String appendAndDelete(String s, String t, int k) {
        int s_length = s.length();
        int t_length = t.length();
        int max_length = Math.min(s_length, t_length);
        int prefix_size = 0;

        if (s_length + t_length < k) {
            return "Yes";
        }

        if (s.equals(t)) {
            return "Yes";
        }

        for (int i = 0; i < max_length; i++) {
            char char_s = getCharAt(s, i, s_length);
            char char_t = getCharAt(t, i, t_length);
            if (char_s != char_t) {
                break;
            }
            prefix_size += 1;
        }

        int symbolsDiff = s_length + t_length - prefix_size * 2;
        if (symbolsDiff <= k && ((k - symbolsDiff) % 2 == 0)) {

            return "Yes";
        }
        return "No";
    }

    private static char getCharAt(String s, int i, int length) {
        if (i >= length) {
            return '*';
        }
        return s.charAt(i);
    }
}







