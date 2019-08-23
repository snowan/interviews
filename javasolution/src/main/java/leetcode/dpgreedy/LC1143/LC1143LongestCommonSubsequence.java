package leetcode.dpgreedy.LC1143;

public class LC1143LongestCommonSubsequence {
  /**
   * Solution: DP.  when you see a problem with returning the longest/most value, but not require returning value details.
   * DP should come across your mind, and try think of the dp formula, how to get next state from previous state.
   *
   * initial state: dp[i][j] = 0;
   * dp formula:
   * if text1[i] == text2[j],
   *    dp[i][j] = 1 + dp[i - 1][j - 1]
   * else
   *    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1])
   *
   * TC: O(m * n) - m is text1 length, n is text2 length
   * SC: O(m * n)
   */
  public int longestCommonSubsequence(String text1, String text2) {
    int m = text1.length();
    int n = text2.length();
    int[][] dp = new int[m + 1][n + 1];
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp[m][n];
  }
}
