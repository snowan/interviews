package leetcode.dpgreedy.LC688;

import java.util.Arrays;

/**
 *
 */
public class LC688KnightProbInChess {
  private static final int[][] dirs = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};

  /**
   * Solution: #1. DP
   * dp[s][i][j] - probability of in cell (i, j) in step s. (remains on the board)
   * init: dp[0][r][c] = 1, start from cell (r, c), 0 step, no move, 1
   * transition:
   * when new cell (nr, nc) is out of board, continue
   * dp[s][i][j] += dp[s-1][nr][nc] / 8; (each cell has 8 directions to move, current probability from previous cell (nr, nc) / 8)
   *
   * TC: O(N*N*K)
   * SC: (N*N*K)
   */
  public double knightProbability(int N, int K, int r, int c) {
    if (K == 0) return 1;
    double[][][] dp = new double[K + 1][N][N];
    dp[0][r][c] = 1;
    for (int step = 1; step <= K; step++) {
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          for (int[] d : dirs) {
            int nr = i + d[0];
            int nc = j + d[1];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
              dp[step][i][j] += dp[step - 1][nr][nc] / 8;
            }
          }
        }
      }
    }
    return Arrays.stream(dp[K]).flatMapToDouble(Arrays::stream).sum();
  }

  /**
   * Solution: #2. recursive + memorization
   *
   */
  public double knightProbabilityRec(int N, int K, int r, int c) {
    if (K == 0) return 1;
    double[][][] dp = new double[K + 1][N][N];
    return helper(N, K, r, c, dp) / Math.pow(8.0, K);
  }

  private double helper(int N, int K, int r, int c, double[][][] dp) {
    if (r < 0 || r >= N || c < 0 || c >= N) return 0;
    if (K == 0) return 1.0;
    if (dp[K][r][c] != 0) return dp[K][r][c];
    for (int[] d : dirs) {
      int nr = r + d[0];
      int nc = c + d[1];
      dp[K][r][c] += helper(N, K - 1, nr, nc, dp);
    }
    return dp[K][r][c];
  }
}
