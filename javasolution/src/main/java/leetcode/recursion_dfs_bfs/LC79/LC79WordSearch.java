package leetcode.recursion_dfs_bfs.LC79;

/**
 * 79. Word Search
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
 * horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 */
public class LC79WordSearch {
  /**
   * Solution: DFS
   */
  public boolean exist(char[][] board, String word) {
    if (board == null || board.length == 0 || board[0].length == 0
        || word == null || word.length() == 0) return true;
    int rows = board.length;
    int cols = board[0].length;
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        if (board[r][c] == word.charAt(0)) {
          if (helper(board, word, r, c, 0)) {
            return true;
          }
        }
      }
    }
    return false;
  }
  private boolean helper(char[][] board, String word, int r, int c, int start) {
    if (start == word.length()) return true;
    if (r < 0 || r >= board.length || c < 0 || c >= board[0].length ||
        board[r][c] != word.charAt(start)) return false;
    board[r][c] = '*';
    boolean res = helper(board, word, r - 1, c, start + 1) // up
        ||  helper(board, word, r + 1, c, start + 1)      // down
        ||  helper(board, word, r, c - 1, start + 1)     // left
        ||  helper(board, word, r, c + 1, start + 1);   // right
    board[r][c] = word.charAt(start);
    return res;
  }
}
