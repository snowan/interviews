package leetcode.string.LC1065;

import java.util.*;

/**
 * 1065. Index Pairs of a String
 * Given a text string and words (a list of strings), return all index pairs [i, j]
 * so that the substring text[i]...text[j] is in the list of words.
 *
 * Example 1:
 *
 * Input: text = "thestoryofleetcodeandme", words = ["story","fleet","leetcode"]
 * Output: [[3,7],[9,13],[10,17]]
 * Example 2:
 *
 * Input: text = "ababa", words = ["aba","ab"]
 * Output: [[0,1],[0,2],[2,3],[2,4]]
 * Explanation:
 * Notice that matches can overlap, see "aba" is found in [0,2] and [2,4].
 *
 * Note:
 *
 * All strings contains only lowercase English letters.
 * It's guaranteed that all strings in words are different.
 * 1 <= text.length <= 100
 * 1 <= words.length <= 20
 * 1 <= words[i].length <= 50
 * Return the pairs [i,j] in sorted order (i.e. sort them by their first coordinate in case of ties sort
 * them by their second coordinate).
 */
public class LC1065IndexPairStr {
  public int[][] indexPairs(String text, String[] words) {
    List<int[]> res = new ArrayList<>();
    Map<Character, List<String>> map = new HashMap<>();
    for (String w : words) {
      map.computeIfAbsent(w.charAt(0), l -> new ArrayList<>()).add(w);
    }
    int len = text.length();
    for (int i = 0; i < len; i++) {
      char curr = text.charAt(i);
      if (!map.containsKey(curr)) continue;
      List<String> wds = map.get(curr);
      for (String w : wds) {
        int wlen = w.length();
        if (wlen > len - i) continue;
        if (w.equalsIgnoreCase(text.substring(i, i + wlen))) {
          res.add(new int[]{i, i + wlen - 1});
        }
      }
    }
    int[][] ans = new int[res.size()][2];
    Collections.sort(res, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
    for (int i = 0; i < res.size(); i++) {
      ans[i] = res.get(i);
    }
    return ans;
  }
}
