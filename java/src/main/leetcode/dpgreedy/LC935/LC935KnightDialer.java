package main.leetcode.dpgreedy.LC935;

import java.util.Arrays;

/**
 * 935. Knight Dialer
 * A chess knight can move as indicated in the chess diagram below:
 * <p>
 * This time, we place our chess knight on any numbered key of a phone pad (indicated above),
 * and the knight makes N-1 hops.  Each hop must be from one key to another numbered key.
 * <p>
 * Each time it lands on a key (including the initial placement of the knight), it presses the number of that key,
 * pressing N digits total.
 * <p>
 * How many distinct numbers can you dial in this manner?
 * <p>
 * Since the answer may be large, output the answer modulo 10^9 + 7.
 * <p>
 * Example 1:
 * <p>
 * Input: 1
 * Output: 10
 * Example 2:
 * <p>
 * Input: 2
 * Output: 20
 * Example 3:
 * <p>
 * Input: 3
 * Output: 46
 * <p>
 * Note:
 * <p>
 * 1 <= N <= 5000
 */
public class LC935KnightDialer {
  private static final int MOD = 1000000007;
  private static final int[][] graph = {{4, 6}, {6, 8}, {7, 9}, {4, 8}, {3, 9, 0}, {}, {1, 7, 0}, {2, 6}, {1, 3}, {2, 4}};

  /**
   * Solution: DP
   * dp[N+1][10]
   * dp[i][j] - numbers of number for number j in hop i.
   * init:
   * when i = 1 (hop), dp[1][j] = 1
   * from dialer board, L shape move, observation:
   * map:
   * 0 -> [4,6]
   * 1 -> [6,8]
   * 2 -> [7,9]
   * 3 -> [4,8]
   * 4 -> [0,3,9]
   * 5 -> []
   * 6 -> [0,1,7]
   * 7 -> [2,9]
   * 8 -> [1,3]
   * 9 -> [2,4]]
   * <p>
   * dp[i][j] = (sum of dp[i-1][map[j]]) % mod
   * <p>
   * result: sum of hops N,
   * return dp[N][0]+dp[N][1]+...+dp[N][9].
   * <p>
   * TC: O(10*N)
   * SC: O(10*N)
   */
  public int knightDialer(int N) {
    if (N == 1) return 10;
    int[][] dp = new int[N + 1][10];
    Arrays.fill(dp[1], 1);
    for (int i = 2; i <= N; i++) {
      for (int j = 0; j < 10; j++) {
        for (int h : graph[j]) {
          dp[i][j] = (dp[i][j] + dp[i - 1][h]) % MOD;
        }
      }
    }
    int res = 0;
    for (int i = 0; i < 10; i++) {
      res = (res + dp[N][i]) % MOD;
    }
    return res;
  }

  /**
   * Solution: recursive + memorization
   */
  public int knightDialerRec(int N) {
    if (N == 1) return 10;
    int[][] dp = new int[N + 1][10];
    int res = 0;
    for (int i = 0; i < 10; i++) {
      res = (res + helper(N - 1, i, dp, graph)) % MOD;
    }
    return res;
  }

  private int helper(int N, int num, int[][] dp, int[][] graph) {
    if (N == 0) return 1;
    if (dp[N][num] != 0) {
      return dp[N][num];
    }
    int count = 0;
    for (int conn : graph[num]) {
      count = (count + helper(N - 1, conn, dp, graph)) % MOD;
    }
    dp[N][num] = count;
    return count;
  }

  /**
   * Solution #3. refer to discussion: https://leetcode.com/problems/knight-dialer/discuss/189252/O(logN)
   * O(logn)
   */
}
