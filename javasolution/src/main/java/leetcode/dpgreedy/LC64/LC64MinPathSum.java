package leetcode.dpgreedy.LC64;

/**
 * 64. Minimum Path Sum
 *
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes
 * the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * Example:
 *
 * Input:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
public class LC64MinPathSum {
  /**
   * Solution: DP
   * dp[i][j] - min sum from [0,0] to [i,j]
   * init:
   * dp[0][0] = grid[0][0]
   * d[i][0] - the first col, only one path,
   * dp[i][0] = dp[i - 1][0] + grid[i][0]
   * dp[0][i] - the first row, only one path,
   * dp[0][i[ = dp[0][i - 1] + grid[0][i];
   * <p>
   * transition:
   * dp[i][j] - come from two possible paths, [i-1, j] or [i,j-1] chose min sum
   * dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j].
   * <p>
   * TC: O(n*m) - n is row size, m is column size
   * SC: O(n*m)
   */
  public int minPathSum(int[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
    int row = grid.length;
    int col = grid[0].length;
    int[][] dp = new int[row][col];
    dp[0][0] = grid[0][0];
    for (int r = 1; r < row; r++) {
      dp[r][0] = grid[r][0] + dp[r - 1][0];
    }
    for (int c = 1; c < col; c++) {
      dp[0][c] = grid[0][c] + dp[0][c - 1];
    }
    for (int r = 1; r < row; r++) {
      for (int c = 1; c < col; c++) {
        dp[r][c] = Math.min(dp[r - 1][c], dp[r][c - 1]) + grid[r][c];
      }
    }
    return dp[row - 1][col - 1];
  }

  /**
   * Solution: DP, from above 2D matrix dp -> 1D dp.
   * * init:
   * dp[j] += dp[j - 1] + grid[0][j]
   * transition:
   * dp[j] = grid[i][j] + min(dp[c],dp[c - 1])
   *
   * TC: O(n*m)
   * SC: O(m)
   */
  public int minPathSum2(int[][] grid) {
    int row = grid.length;
    int col = grid[0].length;
    int[] dp = new int[col];
    dp[0] = grid[0][0];
    for (int c = 1; c < col; c++) {
      dp[c] = dp[c - 1] + grid[0][c];
    }
    for (int r = 1; r < row; r++) {
      for (int c = 0; c < col; c++) {
        dp[c] += grid[r][c];
        if (c > 0) {
          dp[c] = Math.min(dp[c], dp[c - 1] + grid[r][c]);
        }
      }
    }
    return dp[col - 1];
  }

  /**
   * Solution: #2. Recursive + memorization
   *
   * TC: O(n*m)
   * SC: O(n*m)
   */
  public int minPathSumRec(int[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
    int row = grid.length;
    int col = grid[0].length;
    int[][] dp = new int[row][col];
    return helper(grid, 0, 0, dp, row, col);
  }

  private int helper(int[][] grid, int r, int c, int[][] dp, int row, int col) {
    if (r < row && c < col) {
      if (r == row - 1 && c == col - 1) {
        return grid[r][c];
      }
      if (dp[r][c] != 0) return dp[r][c];
      int right = helper(grid, r, c + 1, dp, row, col);
      int down = helper(grid, r + 1, c, dp, row, col);
      dp[r][c] = Math.min(right, down) + grid[r][c];
      return dp[r][c];
    }
    return Integer.MAX_VALUE;
  }
}
