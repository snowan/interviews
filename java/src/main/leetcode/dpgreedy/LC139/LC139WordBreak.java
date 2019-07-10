package main.leetcode.dpgreedy.LC139;

import java.util.*;

/**
 * 139. Word Break
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input: s = "main.leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "main.leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 */
public class LC139WordBreak {
  /**
   * Solution 1: DP
   *
   * TC: O(n^2)
   * SC: O(n)
   */
  public boolean wordBreak(String s, List<String> wordDict) {
    int len = s.length();
    boolean[] dp = new boolean[len + 1];
    dp[0] = true;
    Set<String> words = new HashSet<>(wordDict);
    for (int i = 1; i <= len; i++) {
      for (int j = 0; j < i; j++) {
        if (dp[j] && words.contains(s.substring(j, i))) {
          dp[i] = true;
          break;
        }
      }
    }

    return dp[len];
  }

  /**
   * Solution 2: recursion + memorization
   */
  public static boolean wordBreak2(String s, List<String> wordDict) {
    int len = s.length();
    return helper(s, new HashSet<>(wordDict), new HashSet<>(), 0);
  }

  private static boolean helper(String s, Set<String> words, Set<Integer> visited, int idx) {
    if (idx == s.length()) return true;
    if (visited.contains(idx)) return false;
    for (int i = idx + 1; i <= s.length(); i++) {
      if (words.contains(s.substring(idx, i))) {
        if (helper(s, words, visited, i)) return true;
        else visited.add(i);
      }
    }
    visited.add(idx);
    return false;
  }

  public static void main(String[] args) {
    LC139WordBreak.wordBreak2("main/leetcode", Arrays.asList("leet", "code"));
  }
}
