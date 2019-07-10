package main.leetcode.string.LC1062;

import java.util.HashSet;
import java.util.Set;

/**
 * 1062. Longest Repeating Substring
 * Given a string S, find out the length of the longest repeating substring(s). Return 0 if no repeating substring exists.
 *
 * Example 1:
 *
 * Input: "abcd"
 * Output: 0
 * Explanation: There is no repeating substring.
 * Example 2:
 *
 * Input: "abbaba"
 * Output: 2
 * Explanation: The longest repeating substrings are "ab" and "ba", each of which occurs twice.
 * Example 3:
 *
 * Input: "aabcaabdaab"
 * Output: 3
 * Explanation: The longest repeating substring is "aab", which occurs 3 times.
 * Example 4:
 *
 * Input: "aaaaa"
 * Output: 4
 * Explanation: The longest repeating substring is "aaaa", which occurs twice.
 *
 * Note:
 *
 * The string S consists of only lowercase English letters from 'a' - 'z'.
 * 1 <= S.length <= 1500
 */
public class LC1062LongestRepeatSubstr {
  /**
   * Solution: Generate all possible substrings, find repeat longest substring
   * Start from longest substring length, generate all possible the same length substring,
   * if found repeat, found longest, return result.
   * If didn't find repeat substring when substring len = 1, then return 0.
   *
   * TC: O(n^3) - n is the length of input string
   * SC: O(n) - when len = 1, total have n different substring
   */
  public int longestRepeatingSubstring(String S) {
    if (S == null || S.length() < 2) return 0;
    Set<String> set = new HashSet<>();
    int n = S.length();
    for (int len = n; len >= 1; len--) {
      for (int l = 0; l <= n - len; l++) {
        int r = l + len - 1;
        String temp = S.substring(l, r + 1);
        if (!set.add(temp)) {
          return temp.length();
        }
      }
      set.clear();
    }
    return 0;
  }
}
