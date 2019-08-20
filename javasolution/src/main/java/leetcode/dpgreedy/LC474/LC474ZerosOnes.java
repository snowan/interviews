package leetcode.dpgreedy.LC474;

public class LC474ZerosOnes {
  /**
   * DP
   */
  public int findMaxForm(String[] strs, int m, int n) {
    int[][] dp = new int[m + 1][n + 1];
    for (String s : strs) {
      int[] counts = countZeroOnes(s);
      for (int i = m; i >= counts[0]; i--) {
        for (int j = n; j >= counts[1]; j--) {
          dp[i][j] = Math.max(1 + dp[i - counts[0]][j - counts[1]], dp[i][j]);
        }
      }
    }
    return dp[m][n];
  }

  private int[] countZeroOnes(String s) {
    int[] res = new int[2];
    for (char ch : s.toCharArray()) {
      res[ch - '0']++;
    }
    return res;
  }

  /**
   * recursive (TLE)
   */
  public int findMaxForm2(String[] strs, int m, int n) {
    return helper(strs, 0, m, n);
  }
  private int helper(String[] strs, int idx, int zeros, int ones) {
    if (idx == strs.length) return 0;
    int[] counts = countZeroOnes(strs[idx]);
    int take = zeros - counts[0] >= 0 && ones - counts[1] >= 0
        ? 1 + helper(strs, idx + 1, zeros - counts[0], ones - counts[1])
        : -1;
    return Math.max(take, helper(strs, idx + 1, zeros, ones));
  }
}
