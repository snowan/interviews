package main.leetcode.recursion_dfs_bfs.LC1091;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1091. Shortest Path in Binary Matrix
 * In an N by N square grid, each cell is either empty (0) or blocked (1).
 * <p>
 * A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:
 * <p>
 * Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
 * C_1 is at location (0, 0) (ie. has value grid[0][0])
 * C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
 * If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
 * Return the length of the shortest such clear path from top-left to bottom-right.
 * If such a path does not exist, return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: [[0,1],[1,0]]
 * Output: 2
 * Example 2:
 * <p>
 * Input: [[0,0,0],[1,1,0],[1,1,0]]
 * Output: 4
 * <p>
 * Note:
 * <p>
 * 1 <= grid.length == grid[0].length <= 100
 * grid[r][c] is 0 or 1
 */
public class LC1091ShortestPathInBM {
  private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

  /**
   * Sollution: BFS, shortest path question, BFS
   */
  public int shortestPathBinaryMatrix(int[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) return -1;
    int n = grid.length;
    if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) return -1;
    Queue<Point> queue = new LinkedList<>();
    queue.offer(new Point(0, 0));
    int steps = 1;
    boolean[][] visited = new boolean[n][n];
    visited[0][0] = true;
    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size-- > 0) {
        Point curr = queue.poll();
        if (curr.x == n - 1 && curr.y == n - 1) {
          return steps;
        }
        for (int[] d : dirs) {
          int nx = curr.x + d[0];
          int ny = curr.y + d[1];
          if (!isValid(nx, ny, n, grid) || visited[nx][ny]) continue;
          queue.offer(new Point(nx, ny));
          visited[nx][ny] = true;
        }
      }
      steps++;
    }
    return -1;
  }

  class Point {
    int x;
    int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  private boolean isValid(int x, int y, int n, int[][] grid) {
    return x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0;
  }
}
