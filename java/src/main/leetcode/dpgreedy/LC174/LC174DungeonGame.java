package main.leetcode.dpgreedy.LC174;

import java.util.Arrays;

/**
 * 174. Dungeon Game
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
 * The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in
 * the top-left room and must fight his way through the dungeon to rescue the princess.
 *
 * The knight has an initial health point represented by a positive integer. If at any point his health point drops
 * to 0 or below, he dies immediately.
 *
 * Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms;
 * other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
 *
 * In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 *
 * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 *
 * For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the
 * optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
 *
 * -2 (K)	-3	3
 * -5	-10	1
 * 10	30	-5 (P)
 *
 * Note:
 *
 * The knight's health has no upper bound.
 * Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room
 * where the princess is imprisoned.
 */
public class LC174DungeonGame {
  /**
   * Solution: DP
   * Dp[i][j] - min health required for knight to reach (i,j)
   * init: dp[n+1][m+1]
   * dp[0][m] = MAX,
   * dp[n][0] = MAX.
   * dp[0][m-1] = dp[n-1][0] = 0;
   * transition:
   * dp[i][j] = max(0, min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j])
   * if health if 0 or below, knight will die immediately, so we must keep 0 or above health.
   *
   * TC: O(n*m)
   * SC: O(n*m)
   */
  public int calculateMinimumHP(int[][] dungeon) {
    if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;
    int row = dungeon.length;
    int col = dungeon[0].length;
    int[][] dp = new int[row + 1][col + 1];
    for (int r = 0; r <= row; r++) {
      dp[r][col] = Integer.MAX_VALUE;
    }
    Arrays.fill(dp[row], Integer.MAX_VALUE);
    dp[row][col - 1] = 0;
    dp[row - 1][col] = 0;
    for (int r = row - 1; r >= 0; r--)
      for (int c = col - 1; c >= 0; c--) {
        dp[r][c] = Math.max(0, Math.min(dp[r][c + 1], dp[r + 1][c]) - dungeon[r][c]);
      }
    return dp[0][0] + 1;
  }
}
