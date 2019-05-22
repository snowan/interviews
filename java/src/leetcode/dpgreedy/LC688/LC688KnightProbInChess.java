package leetcode.dpgreedy.LC688;

import java.util.Arrays;

/**
 * 688. Knight Probability in Chessboard
 *
 * On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves.
 * The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).
 *
 * A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal
 * direction, then one square in an orthogonal direction.
 *
 * Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece
 * would go off the chessboard) and moves there.
 *
 * The knight continues moving until it has made exactly K moves or has moved off the chessboard.
 * Return the probability that the knight remains on the board after it has stopped moving.
 *
 * Example:
 *
 * Input: 3, 2, 0, 0
 * Output: 0.0625
 * Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
 * From each of those positions, there are also two moves that will keep the knight on the board.
 * The total probability the knight stays on the board is 0.0625.
 *
 * Note:
 *
 * N will be between 1 and 25.
 * K will be between 0 and 100.
 * The knight always initially starts on the board.
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
