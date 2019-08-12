package leetcode.dpgreedy.LC1155;

public class LC1155NumberOfDiceRollsWithTargetSum {
  private static final int MOD = 1000000007;
  public int numRollsToTarget(int d, int f, int target) {
    long[][] dp = new long[d + 1][target + 1];
    dp[0][0] = 1;
    long max = d * f;
    for (int i = 1; i <= d; i++) {
      for (int j = 1; j <= target; j++) {
        if (j > max) continue;
        for (int k = 1; k <= f && k <= j; k++) {
          dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % MOD;
        }
      }
    }
    return (int)dp[d][target];
  }
}
