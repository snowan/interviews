package leetcode.string.LC792;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 792. Number of Matching Subsequences
 * Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.
 *
 * Example :
 * Input:
 * S = "abcde"
 * words = ["a", "bb", "acd", "ace"]
 * Output: 3
 * Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
 * Note:
 *
 * All words in words and S will only consists of lowercase letters.
 * The length of S will be in the range of [1, 50000].
 * The length of words will be in the range of [1, 5000].
 * The length of words[i] will be in the range of [1, 50].
 */
public class LC792NumOfMatchSubseq {
  public static int numMatchingSubseq(String S, String[] words) {
    if (S == null || S.length() == 0 || words == null || words.length == 0) return 0;
    int res = 0;
    for (String w : words) {
      if (isMatch(S, w)) res++;
    }
    return res;
  }
  private static boolean isMatch(String s, String t) {
    if (t.length() > s.length()) return false;
    int n = s.length();
    int m = t.length();
    int i = 0;
    int j = 0;
    int count = 0;
    while (i < n && j < m) {
      // rest cannot match, early termination
      if (n - i < m - j) return false;
      if (s.charAt(i++) == t.charAt(j++)) {
        count++;
      } else {
        i++;
      }
    }
    return count == m;
  }

  /**
   * Solution: Thanks to StefanPochmann post and explaination.
   * https://leetcode.com/problems/number-of-matching-subsequences/discuss/117634/Efficient-and-simple-go-through-words-in-parallel-with-explanation
   *
   * Iterator S once since S length will be much longer.
   */
  public static int numMatchingSubseq2(String S, String[] words) {
    if (S == null || S.length() == 0 || words == null || words.length == 0) return 0;
    int res = 0;
    Map<Character, List<String>> map = new HashMap<>();
    for (String w : words) {
      char ch = w.charAt(0);
      List<String> list = map.getOrDefault(ch, new ArrayList<>());
      list.add(w);
      map.put(ch, list);
    }
    for (char ch : S.toCharArray()) {
      if (!map.containsKey(ch)) continue;
      List<String> curr = map.get(ch);
      map.remove(ch);
      for (String s : curr) {
        String tmp = s.substring(1);
        if (tmp.length() == 0) {
          res++;
        } else {
          List<String> next = map.getOrDefault(tmp.charAt(0), new ArrayList<>());
          next.add(tmp);
          map.put(tmp.charAt(0), next);
        }
      }
    }
    return res;
  }

  public static void main(String[] args) {
    LC792NumOfMatchSubseq.numMatchingSubseq("abcde", new String[]{"a", "bb", "acd", "ace"});
    LC792NumOfMatchSubseq.numMatchingSubseq2("abcde", new String[]{"a", "bb", "acd", "ace"});
  }
}
