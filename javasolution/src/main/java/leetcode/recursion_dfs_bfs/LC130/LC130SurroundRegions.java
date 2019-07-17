package leetcode.recursion_dfs_bfs.LC130;

/**
 * 130. Surrounded Regions
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Example:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * Explanation:
 *
 * Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped
 * to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
 * Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */
public class LC130SurroundRegions {
  private static final int[][] DIRS = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
  public void solve(char[][] board) {
    if (board == null || board.length == 0 || board[0].length == 0) return;
    int rows = board.length;
    int cols = board[0].length;
    boolean[][] visited = new boolean[rows][cols];
    // visit first row, last row
    for (int c = 0; c < cols; c++) {
      // first row, and mark connected 'O' visited
      if (!visited[0][c] && board[0][c] == 'O') {
        dfs(board, 0, c, visited);
      }
      // last row, mast connected 'O' visited
      if (!visited[rows - 1][c] && board[rows - 1][c] == 'O') {
        dfs(board, rows - 1, c, visited);
      }
    }
    // visit first column, last column
    for (int r = 0; r < rows; r++) {
      // first column, mark connected visited
      if (!visited[r][0] && board[r][0] == 'O') {
        dfs(board, r, 0, visited);
      }
      // last column, mark connected 'O' visited
      if (!visited[r][cols - 1] && board[r][cols - 1] == 'O') {
        dfs(board, r, cols - 1, visited);
      }
    }

    // after visit surround region, mark all non-visited 'O' to 'X'
    for (int r = 1; r < rows - 1; r++) {
      for (int c = 1; c < cols - 1; c++) {
        if (!visited[r][c] && board[r][c] == 'O') {
          board[r][c] = 'X';
        }
      }
    }
  }

  private void dfs(char[][] board, int r, int c, boolean[][] visited) {
    for (int[] d : DIRS) {
      int nr = r + d[0];
      int nc = c + d[1];
      if (isValid(nr, nc, board) && !visited[nr][nc] && board[nr][nc] == 'O') {
        visited[nr][nc] = true;
        dfs(board, nr, nc, visited);
      }
    }
  }

  private boolean isValid(int r, int c, char[][] board) {
    return r >= 0 && r < board.length && c >= 0 && c < board[0].length;
  }
}
