package leetcode.recursion.LC5087;

import java.util.Arrays;

/**
 * 5087. Letter Tile Possibilities
 * You have a set of tiles, where each tile has one letter tiles[i] printed on it.
 * Return the number of possible non-empty sequences of letters you can make.
 *
 * Example 1:
 *
 * Input: "AAB"
 * Output: 8
 * Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
 * Example 2:
 *
 * Input: "AAABBC"
 * Output: 188
 *
 * Note:
 *
 * 1 <= tiles.length <= 7
 * tiles consists of uppercase English letters.
 */
public class LC5087LetterTilePosibility {
  int res = 0;
  public int numTilePossibilities(String tiles) {
    if (tiles == null || tiles.length() == 0) return 0;
    char[] arrs = tiles.toCharArray();
    Arrays.sort(arrs);
    helper(arrs, new boolean[arrs.length], new StringBuilder());
    return res;
  }
  private void helper(char[] arrs, boolean[] visited, StringBuilder sb) {
    if (sb.length() > 0) {
      res++;
    }
    for (int i = 0; i < arrs.length; i++) {
      if (visited[i]) continue;
      if (i > 0 && arrs[i] == arrs[i - 1] && !visited[i - 1]) continue;
      sb.append(arrs[i]);
      visited[i] = true;
      helper(arrs, visited, sb);
      visited[i] = false;
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}
