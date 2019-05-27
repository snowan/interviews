package leetcode.dpgreedy.LC518;

/**
 * 518. Coin Change 2
 * <p>
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that amount.
 * You may assume that you have infinite number of each kind of coin.
 * <p>
 * Example 1:
 * <p>
 * Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * Example 2:
 * <p>
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 * Example 3:
 * <p>
 * Input: amount = 10, coins = [10]
 * Output: 1
 * <p>
 * Note:
 * <p>
 * You can assume that
 * <p>
 * 0 <= amount <= 5000
 * 1 <= coin <= 5000
 * the number of coins is less than 500
 * the answer is guaranteed to fit into signed 32-bit integer
 */
public class LC518CoinChange {
  /**
   * Solution: #1. DP
   * dp[len+1][amount+1]
   * dp[i][j] - number of combinations to make up j amount by using i types of coins
   * init:
   * dp[i][0] = 0;
   *
   * transition:
   * dp[i][j] -
   * 1. not using i type coin, then i - 1 types can make j amount
   * 2. using i type coin, check j >= coins[i - 1],
   * dp[i][j] = dp[i - 1][j] + j >= coins[i - 1] ? dp[i][j - coins[i - 1] : 0;
   *
   * TC: O(m * n) - m is the length of coins, n is the amount
   * SC: O(m*n)
   */
  public int change1(int amount, int[] coins) {
    int len = coins.length;
    int[][] dp = new int[len + 1][amount + 1];
    dp[0][0] = 1;
    for (int i = 1; i <= len; i++) {
      dp[i][0] = 1;
      for (int j = 1; j <= amount; j++) {
        dp[i][j] = dp[i - 1][j] + (j >= coins[i - 1] ? dp[i][j - coins[i - 1]] : 0);
      }
    }

    return dp[len][amount];
  }

  /**
   * Solution: #2. DP, based on above 2 dimentional dp, observation that current state depengs on precious state,
   * can optimize to 1 DP.
   *
   * TC: Om*n)
   * SC: O(n) - n is the amount
   */
  public int change(int amount, int[] coins) {
    int[] dp = new int[amount + 1];
    dp[0] = 1;
    int len = coins.length;
    for (int c : coins) {
      for (int i = c; i <= amount; i++) {
        dp[i] += dp[i - c];
      }
    }
    return dp[amount];
  }
}
