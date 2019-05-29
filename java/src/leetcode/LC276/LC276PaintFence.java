package leetcode.LC276;

/**
 * 276. Paint Fence
 * There is a fence with n posts, each post can be painted with one of the k colors.
 *
 * You have to paint all the posts such that no more than two adjacent fence posts have the same color.
 *
 * Return the total number of ways you can paint the fence.
 *
 * Note:
 * n and k are non-negative integers.
 *
 * Example:
 *
 * Input: n = 3, k = 2
 * Output: 6
 * Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:
 *
 *             post1  post2  post3
 *  -----      -----  -----  -----
 *    1         c1     c1     c2
 *    2         c1     c2     c1
 *    3         c1     c2     c2
 *    4         c2     c1     c1
 *    5         c2     c1     c2
 *    6         c2     c2     c1
 */
public class LC276PaintFence {
  /**
   * Solution: DP
   * dp[n + 1], dp[i] - the number of possible ways with i posts and k colors
   * init:
   * dp[1] = k
   * dp[2] = k * k
   *
   * transition:
   * dp[i] = (dp[i - 1] + dp[i - 2]) * (k - 1)
   *  - dp[i - 1] , i post and i-1 post have the same color,
   *  - dp[i - 2], i post and i-2 post have the same color (i and i-1 posts have different color)
   *
   * Result: dp[n]
   *
   * TC: O(n)
   * SC: O(n)
   */
  public int numWays(int n, int k) {
    if (n == 0 || k == 0) return 0;
    if (n == 1) return k;
    if (n == 2) return k * k;
    int[] dp = new int[n + 1];
    dp[1] = k;
    dp[2] = k * k;
    for (int i = 3; i <= n; i++) {
      dp[i] = (dp[i - 1] + dp[i - 2]) * (k - 1);
    }
    return dp[n];
  }
}
