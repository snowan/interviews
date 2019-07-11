package leetcode.dpgreedy.LC576;

import java.util.Arrays;

/**
 * 576. Out of Boundary Paths
 * There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move
 * the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right).
 * However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary.
 * The answer may be very large, return it after mod 109 + 7.
 *
 * Example 1:
 *
 * Input: m = 2, n = 2, N = 2, i = 0, j = 0
 * Output: 6
 * Explanation:
 *
 * Example 2:
 *
 * Input: m = 1, n = 3, N = 3, i = 0, j = 1
 * Output: 12
 * Explanation:
 *
 * Note:
 *
 * Once you move the ball out of boundary, you cannot move it back.
 * The length and height of the grid is in range [1,50].
 * N is in range [0,50].
 */
public class LC576OutofBoundPaths {
  private static final int MOD = 1000000007;
  private static final int[][] dirs = {{0,1},{-1,0},{1,0},{0,-1}};

  /**
   * Solution: DP.
   * dp[i][j][s] - the number of ways to walk to cell (i,j) in step s.
   *
   * transition:
   * 1. when next step out of boundary,
   * dp[i][j][s] += 1
   * 2. next step not out of boundary, current paths depend on previous four ways sum
   * dp[i][j][s] = dp[i-1][j][s-1] + dp[i+1][j][s-1] + dp[i][j-1][s-1] + dp[i][j+1][s-1]
   *
   * result: dp[i][j][N] - the ways on cell (i,j) in step N.
   *
   * TC: O(m*n*N) - m is row size, n is column size, N is the steps size
   * SC: O(m*n*N)
   */
  public int findPaths(int m, int n, int N, int i, int j) {
    if (N <= 0) return 0;
    int[][][] dp = new int[m][n][N+1];
    for (int step = 1; step <= N; step++) {
      for (int r = 0; r < m; r++) {
        for (int c = 0; c < n; c++) {
          for (int[] d : dirs) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
              dp[r][c][step] = (1 + dp[r][c][step]) % MOD;
            } else {
              dp[r][c][step] = (dp[r][c][step] + dp[nr][nc][step - 1]) % MOD;
            }
          }
        }
      }
    }
    return dp[i][j][N];
  }

  /**
   * Solution #2. Recursive + memorization
   *
   */
  public int findPathsRec(int m, int n, int N, int i, int j) {
    if (N <= 0) return 0;
    int[][][] dp = new int[m][n][N+1];
    for (int r = 0; r < m; r++) {
      for (int c = 0; c < n; c++) {
        Arrays.fill(dp[r][c], -1);
      }
    }
    return helper(m, n, N, i, j, dp);
  }
  private int helper(int m, int n, int N, int i, int j, int[][][] dp) {
    // out of boundary
    if (i < 0 || i >= m || j < 0 || j >= n) return 1;
    if (N == 0) return 0;
    if (dp[i][j][N] != -1) return dp[i][j][N];
    dp[i][j][N] = 0;
    for (int[] d : dirs) {
      int r = i + d[0];
      int c = j + d[1];
      dp[i][j][N] = (dp[i][j][N] + helper(m, n, N - 1, r, c, dp)) % MOD;
    }
    return dp[i][j][N];
  }
}
