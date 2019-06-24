package leetcode.recursion_dfs_bfs.LC425;

import java.util.*;

/**
 * 425. Word Squares
 * Given a set of words (without duplicates), find all word squares you can build from them.
 * <p>
 * A sequence of words forms a valid word square if the kth row and column read the exact same string,
 * where 0 ≤ k < max(numRows, numColumns).
 * <p>
 * For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads
 * the same both horizontally and vertically.
 * <p>
 * b a l l
 * a r e a
 * l e a d
 * l a d y
 * Note:
 * <p>
 * There are at least 1 and at most 1000 words.
 * All words will have the exact same length.
 * Word length is at least 1 and at most 5.
 * Each word contains only lowercase English alphabet a-z.
 * Example 1:
 * <p>
 * Input:
 * ["area","lead","wall","lady","ball"]
 * <p>
 * Output:
 * [
 * [ "wall",
 * "area",
 * "lead",
 * "lady"
 * ],
 * [ "ball",
 * "area",
 * "lead",
 * "lady"
 * ]
 * ]
 * <p>
 * Explanation:
 * The output consists of two word squares. The order of output does not matter
 * (just the order of words in each word square matters).
 * Example 2:
 * <p>
 * Input:
 * ["abat","baba","atan","atal"]
 * <p>
 * Output:
 * [
 * [ "baba",
 * "abat",
 * "baba",
 * "atan"
 * ],
 * [ "baba",
 * "abat",
 * "baba",
 * "atal"
 * ]
 * ]
 * <p>
 * Explanation:
 * The output consists of two word squares. The order of output does not matter
 * (just the order of words in each word square matters).
 */
public class LC425WordSquares {
  public List<List<String>> wordSquares(String[] words) {
    List<List<String>> res = new ArrayList<>();
    if (words == null || words.length == 0) return res;
    Map<Character, List<String>> map = new HashMap<>();
    for (String w : words) {
      char first = w.charAt(0);
      map.computeIfAbsent(first, l -> new ArrayList<>()).add(w);
    }
    for (String word : words) {
      List<String> curr = new ArrayList<>();
      curr.add(word);
      helper(curr, 1, word, res, map);
    }
    return res;
  }

  private void helper(List<String> curr, int idx, String word,
      List<List<String>> res, Map<Character, List<String>> map) {
    if (idx == word.length()) {
      res.add(new ArrayList<>(curr));
      return;
    }
    List<String> words = map.get(word.charAt(idx));
    if (words == null) return;
    for (String w : words) {
      if (isMatching(w, curr, idx)) {
        curr.add(w);
        helper(curr, idx + 1, word, res, map;
        curr.remove(curr.size() - 1);
      }
    }
  }

  private boolean isMatching(String w, List<String> words, int idx) {
    for (int i = 1; i < idx; i++) {
      if (w.charAt(i) != words.get(i).charAt(idx)) {
        return false;
      }
    }
    return true;
  }
}
