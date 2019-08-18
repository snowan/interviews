package leetcode.recursion_dfs_bfs.LC1162;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/as-far-from-land-as-possible/
 */
public class LC1162FarFromLand {
  public int maxDistance(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    boolean[][] visited = new boolean[m][n];
    Queue<int[]> q = new LinkedList<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
          visited[i][j] = true;
          q.offer(new int[]{i, j});
        }
      }
    }
    int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    int result = -1;
    while (!q.isEmpty()) {
      int size = q.size();
      while (size-- > 0) {
        int[] cur = q.poll();
        result = Math.max(result, grid[cur[0]][cur[1]] - 1);
        for (int[] dir : dirs) {
          int x = cur[0] + dir[0];
          int y = cur[1] + dir[1];
          if (isValid(m, n, x, y) && !visited[x][y]) {
            visited[x][y] = true;
            grid[x][y] = grid[cur[0]][cur[1]] + 1;
            q.offer(new int[]{x, y});
          }
        }
      }
    }
    return result == 0 ? -1 : result;
  }

  private boolean isValid(int rows, int cols, int r, int c) {
    return r >= 0 && r < rows && c >= 0 && c < cols;
  }
}
