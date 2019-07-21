package leetcode.hashtable.LC1128;

import java.util.HashMap;
import java.util.Map;

/**
 * 1128. Number of Equivalent Domino Pairs
 * Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [c, d] if and only if either
 * (a==c and b==d), or (a==d and b==c) - that is, one domino can be rotated to be equal to another domino.
 *
 * Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is equivalent to dominoes[j].
 *
 * Example 1:
 *
 * Input: dominoes = [[1,2],[2,1],[3,4],[5,6]]
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= dominoes.length <= 40000
 * 1 <= dominoes[i][j] <= 9
 */
public class LC1128NumEquvPairs {
  public int numEquivDominoPairs(int[][] dominoes) {
    if (dominoes == null || dominoes.length == 0) return 0;
    int numPairs = 0;
    Map<Integer, Integer> count = new HashMap<>();
    for (int[] d : dominoes) {
      int key = Math.min(d[0], d[1]) * 10 + Math.max(d[0], d[1]);
      count.put(key, count.getOrDefault(key, 0) + 1);
    }
    for (int v : count.values()) {
      numPairs += v * (v - 1) / 2;
    }

    return numPairs;
  }
}
