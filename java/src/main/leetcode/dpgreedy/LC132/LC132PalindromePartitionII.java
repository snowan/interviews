package main.leetcode.dpgreedy.LC132;

import java.util.Arrays;

/**
 * 132. Palindrome Partitioning II
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return the minimum cuts needed for a palindrome partitioning of s.
 *
 * Example:
 *
 * Input: "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
public class LC132PalindromePartitionII {
  /**
   * Solution: DP,
   *
   * TC: O(n ^ 4) - n is the length of input string
   * SC: O(n^2)
   */
  public int minCut(String s) {
    if (s == null || s.length() < 2) return 0;
    int len = s.length();
    int[][] dp = new int[len][len];
    for (int i = 0; i < len; i++) {
      Arrays.fill(dp[i], len + 1);
      dp[i][i] = 0; // [i,i] is palindome, no need to cut
    }
    for (int l = 2; l <= len; l++) {
      for (int i = 0; i < len - l + 1; i++) {
        int j = l + i - 1;
        if (isPalindrome(i, j, s)) dp[i][j] = 0;
        else {
          for (int k = i; k < j; k++) {
            dp[i][j] = Math.min(dp[i][j], 1 + dp[i][k] + dp[k + 1][j]);
          }
        }
      }
    }

    return dp[0][len - 1];
  }
  private boolean isPalindrome(int i , int j, String s) {
    while (i < j) {
      if (s.charAt(i++) != s.charAt(j--)) return false;
    }
    return true;
  }

  /**
   * TC: O(n^2)
   * SC: O(n^2)
   */
  public int minCutII(String s) {
    if (s == null || s.length() < 2) return 0;
    int len = s.length();
    int[] cut = new int[len];
    boolean[][] palindrome = new boolean[len][len];
    for (int i = 0; i < len; i++) {
      int min = i;
      for (int j = 0; j <= i; j++) {
        if (s.charAt(i) == s.charAt(j) &&
            ((j + 1 > i - 1) || palindrome[j + 1][i - 1])) {
          palindrome[j][i] = true;
          min = j == 0 ? 0 : Math.min(min, cut[j - 1] + 1);
        }
      }
      cut[i] = min;
    }
    return cut[len - 1];
  }
}
