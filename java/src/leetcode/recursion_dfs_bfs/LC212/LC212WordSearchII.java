package leetcode.recursion_dfs_bfs.LC212;

import java.util.ArrayList;
import java.util.List;

public class LC212WordSearchII {
  private static final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
  public List<String> findWords(char[][] board, String[] words) {
    if (board == null || board.length == 0 || board[0].length == 0 ||
        words == null || words.length == 0) {
      return new ArrayList<>();
    }
    List<String> res = new ArrayList<>();
    TrieNode root = buildTrie(words);
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        dfs(board, i, j, root, res);
      }
    }
    return res;
  }

  private void dfs(char[][] board, int i, int j, TrieNode root, List<String> res) {
    char ch = board[i][j];
    if (ch == '#' || root.next[ch - 'a'] == null) {
      return;
    }
    root = root.next[ch - 'a'];
    if (root.word != "") {
      res.add(root.word);
      root.word = "";
    }
    board[i][j] = '#';
    for (int[] d : dirs) {
      int r = i + d[0];
      int c = j + d[1];
      if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) continue;
      dfs(board, r, c, root, res);
    }
    board[i][j] = ch;
  }

  private TrieNode buildTrie(String[] words) {
    TrieNode root = new TrieNode();
    for (String word : words) {
      TrieNode node = root;
      for (char ch : word.toCharArray()) {
        int idx = ch - 'a';
        if (node.next[idx] == null) {
          node.next[idx] = new TrieNode();
        }
        node = node.next[idx];
      }
      node.word = word;
    }
    return root;
  }
  class TrieNode {
    TrieNode[] next;
    String word;
    public TrieNode() {
      next = new TrieNode[26];
      word = "";
    }
  }
}
