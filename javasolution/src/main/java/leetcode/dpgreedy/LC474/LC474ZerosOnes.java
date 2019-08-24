package leetcode.dpgreedy.LC474;

import java.util.Arrays;

public class LC474ZerosOnes {
  /**
   * solution #1. 2D DP
   */
  public int findMaxForm(String[] strs, int m, int n) {
    int[][] dp = new int[m + 1][n + 1];
    printArr(dp);
    for (String s : strs) {
      int[] counts = countZeroOnes(s);
      for (int i = m; i >= counts[0]; i--) {
        for (int j = n; j >= counts[1]; j--) {
          dp[i][j] = Math.max(1 + dp[i - counts[0]][j - counts[1]], dp[i][j]);
        }
      }
      System.out.println("current dp for string: " + s);
      printArr(dp);
      System.out.println("-------------------------------");
    }
    return dp[m][n];
  }

  private void printArr(int[][] dp) {
    for (int[] d : dp) {
      System.out.println(Arrays.toString(d));
    }
  }

  private int[] countZeroOnes(String s) {
    int[] res = new int[2];
    for (char ch : s.toCharArray()) {
      res[ch - '0']++;
    }
    return res;
  }

  /**
   * Solution #2. Brute force , recursive (TLE)
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

  /**
   * Solution #3. 3D DP
   */
  public int findMaxForm3(String[] strs, int m, int n) {
    int l = strs.length;
    int [][][] d = new int[l + 1][m + 1][n + 1];
    for (int i = 0; i <= l; i ++){
      int [] nums = new int[]{0,0};
      if (i > 0){
        nums = countZeroOnes(strs[i - 1]);
      }
      for (int j = 0; j <= m; j ++){
        for (int k = 0; k <= n; k ++){
          if (i == 0) {
            d[i][j][k] = 0;
          } else if (j >= nums[0] && k >= nums[1]){
            d[i][j][k] = Math.max(d[i - 1][j][k], d[i - 1][j - nums[0]][k - nums[1]] + 1);
          } else {
            d[i][j][k] = d[i - 1][j][k];
          }
        }
      }
    }
    return d[l][m][n];
  }

  /**
   * Solution #4. memory + recursive
   */
  public int findMaxForm4(String[] strs, int m, int n) {
    return helper(strs, 0, m, n, new int[strs.length][m + 1][n + 1]);
  }
  private int helper(String[] strs, int idx, int j, int k, int[][][] memo) {
    if (idx == strs.length) return 0;
    if (memo[idx][j][k] != 0) return memo[idx][j][k];
    int[] counts = countZeroOnes(strs[idx]);
    int takeCurrStr = j - counts[0] >= 0 && k - counts[1] >= 0
        ? 1 + helper(strs, idx + 1, j - counts[0], k - counts[1], memo)
        : -1;
    int notTakeCurrStr = helper(strs, idx + 1, j, k, memo);
    memo[idx][j][k] = Math.max(takeCurrStr, notTakeCurrStr);
    return memo[idx][j][k];
  }

  public static void main(String[] args) {
    LC474ZerosOnes test = new LC474ZerosOnes();
    test.findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3);
  }
}
