package leetcode.dpgreedy.LC1000;

import java.util.Arrays;

/**
 * 1000. Minimum Cost to Merge Stones
 *
 * There are N piles of stones arranged in a row.  The i-th pile has stones[i] stones.
 *
 * A move consists of merging exactly K consecutive piles into one pile, and the cost of this move is equal to the
 * total number of stones in these K piles.
 *
 * Find the minimum cost to merge all piles of stones into one pile.  If it is impossible, return -1.
 *
 * Example 1:
 *
 * Input: stones = [3,2,4,1], K = 2
 * Output: 20
 * Explanation:
 * We start with [3, 2, 4, 1].
 * We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
 * We merge [4, 1] for a cost of 5, and we are left with [5, 5].
 * We merge [5, 5] for a cost of 10, and we are left with [10].
 * The total cost was 20, and this is the minimum possible.
 * Example 2:
 *
 * Input: stones = [3,2,4,1], K = 3
 * Output: -1
 * Explanation: After any merge operation, there are 2 piles left, and we can't merge anymore.
 * So the task is impossible.
 *
 * Example 3:
 *
 * Input: stones = [3,5,1,2,6], K = 3
 * Output: 25
 * Explanation:
 * We start with [3, 5, 1, 2, 6].
 * We merge [5, 1, 2] for a cost of 8, and we are left with [3, 8, 6].
 * We merge [3, 8, 6] for a cost of 17, and we are left with [17].
 * The total cost was 25, and this is the minimum possible.
 *
 * Note:
 *
 * 1 <= stones.length <= 30
 * 2 <= K <= 30
 * 1 <= stones[i] <= 100
 */
public class LC1000MinCostMergeStones {
  /**
   * refer: https://leetcode.com/problems/minimum-cost-to-merge-stones/discuss/247657/JAVA-Bottom-Up-%2B-Top-Down-DP-With-Explaination
   * Solution: DP
   * dp[i][j][k] - min cost to merge subarray from [i, j] into k piles
   * init: dp[i][i][1] = 0 since no need to merge
   * transition: dp[i][j[[k] = min(dp[i][j][k], dp[i][t][k - 1] + dp[t + 1][j][1]) (i <= t < j)
   * dp[i][j][1] = dp[i][j][K] + presum[j] - presum[i - 1] (presum[i~j])
   *
   * TC: O(n^3 * K) - n is the length of stones
   * Sc: O(n^3)
   */
  private static final int MAX = Integer.MAX_VALUE;
  public int mergeStones(int[] stones, int K) {
    int len = stones.length;
    if ((len - 1) % (K - 1) != 0) {
      return -1;
    }
    int[] preSum = new int[len + 1];
    for (int i = 1; i <= len; i++) {
      preSum[i] = preSum[i - 1] + stones[i - 1];
    }
    int[][][] dp = new int[len + 1][len + 1][K + 1];
    for (int i = 1; i <= len; i++) {
      for (int j = 1; j <= len; j++) {
        Arrays.fill(dp[i][j], MAX);
      }
      dp[i][i][1] = 0;
    }

    for (int l = 2; l <= len; l++) {
      for (int i = 1; i <= len - l + 1; i++) {
        int j = i + l - 1;
        for (int k = 2; k <= K; k++) {
          for (int t = i; t < j; t++) {
            if (dp[i][t][1] == MAX || dp[t + 1][j][k - 1] == MAX) continue;
            dp[i][j][k] = Math.min(dp[i][j][k], dp[i][t][1] + dp[t + 1][j][k - 1]);
          }
        }
        if (dp[i][j][K] == MAX) continue;
        dp[i][j][1] = dp[i][j][K] + (preSum[j] - preSum[i - 1]);
      }
    }

    return dp[1][len][1];
  }
}
