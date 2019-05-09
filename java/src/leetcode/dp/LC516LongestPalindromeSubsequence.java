package leetcode.dp;

/**
 * 516. Longest Palindromic Subsequence
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 *
 * Given a string s, find the longest palindromic subsequence's length in s.
 * You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 * Input:
 *
 * "bbbab"
 * Output:
 * 4
 * One possible longest palindromic subsequence is "bbbb".
 * Example 2:
 * Input:
 *
 * "cbbd"
 * Output:
 * 2
 * One possible longest palindromic subsequence is "bb".
 */
public class LC516LongestPalindromeSubsequence {
  /**
   * Solution: DP + sliding window, start from len = 2, and keep (right(j) - left(i) + 1 = len), moving from left to right.
   * for s.charAt(i) == s.charAt(j), dp[i][j] = dp[i + 1][j - 1] + 2 (when len = 2, dp[i][j] = 2)
   * otherwise dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]).
   *
   * TC: O(n ^ 2) - n is the length of input string
   * SC: O(n ^ 2)
   */
  public static int longestPalindromeSubseq(String s) {
    if (s == null) return 0;
    int n = s.length();
    if (n < 2) return n;
    int[][] dp = new int[n][n];
    for (int i = 0; i < n; i++) dp[i][i] = 1;
    for (int len = 2; len <= n; len++) {
      for (int i = 0; i < n - len + 1; i++) {
        int j = i + len - 1;
        if (s.charAt(i) == s.charAt(j)) {
          if (len == 2) dp[i][j] = 2;
          else dp[i][j] = dp[i + 1][j - 1] + 2;
        } else {
          dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp[0][n - 1];
  }

  public static int longestPalindSubseq_recur(String s) {
    if (s == null) return 0;
    int n = s.length();
    if (n < 2) return n;
    return helper(s, 0, n);
  }
  private static int helper(String s, int start, int len) {
    if (len == 1) return 1;
    if (len == 0) return 0;
    if (s.charAt(start) == s.charAt(start + len - 1)) {
      return helper(s, start + 1, len - 2) + 2;
    } else {
      return Math.max(helper(s, start + 1, len - 1), helper(s, start, len - 1));
    }
  }

  public static void main(String[] args) {
    System.out.println(longestPalindromeSubseq("abbbbadd"));
    System.out.println(longestPalindromeSubseq(""));
    System.out.println(longestPalindromeSubseq("a"));
    System.out.println(longestPalindromeSubseq("aa"));
    System.out.println(longestPalindromeSubseq("aba"));
    System.out.println(longestPalindromeSubseq("ababbbbba"));
    System.out.println("--------------------------------------------");
    System.out.println(longestPalindSubseq_recur("abbbbadd"));
    System.out.println(longestPalindSubseq_recur(""));
    System.out.println(longestPalindSubseq_recur("a"));
    System.out.println(longestPalindSubseq_recur("aa"));
    System.out.println(longestPalindSubseq_recur("aba"));
    System.out.println(longestPalindSubseq_recur("ababbbbba"));
  }
}
