package leetcode.twopointers.LC30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class LC30SubstrWithConcatenationAllWords {
  public List<Integer> findSubstring(String s, String[] words) {
    List<Integer> res = new ArrayList<>();
    if (s == null || words == null || s.length() < words.length || words.length == 0) return res;
    Map<String, Integer> wordsMap = new HashMap<>();
    for (String w : words) {
      wordsMap.put(w, wordsMap.getOrDefault(w, 0) + 1);
    }
    int len = s.length();
    int wordNum = words.length;
    int wordLen = words[0].length();
    if (len < wordNum * wordLen) return res;
    for (int i = 0; i < len - wordNum * wordLen + 1; i++) {
      Map<String, Integer> subMap = new HashMap<>();
      int currCount = 0;
      while (currCount < wordNum) {
        String currWord = s.substring(currCount * wordLen + i, (currCount + 1) * wordLen + i);
        if (!wordsMap.containsKey(currWord)) break;
        subMap.put(currWord, subMap.getOrDefault(currWord, 0) + 1);
        if (subMap.get(currWord) > wordsMap.get(currWord)) break;
        currCount++;
      }
      if (currCount == wordNum) {
        res.add(i);
      }
    }
    return res;
  }

  public static List<Integer> findSubstring2(String s, String[] words) {
    List<Integer> res = new ArrayList<>();
    if (s == null || words == null || s.length() < words.length || words.length == 0) return res;
    final Map<String, Integer> wordsMap = new HashMap<>();
    for (final String word : words) {
      wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
    }
    final int len = s.length();
    final int wordNum = words.length;
    final int wordLen = words[0].length();
    for (int i = 0; i < wordLen; i++) {
      int l = i, count = 0;
      final Map<String, Integer> subMap = new HashMap<>();
      for (int r = i; r <= len - wordLen; r += wordLen) {
        final String word = s.substring(r, r + wordLen);
        if (!wordsMap.containsKey(word)) {
          subMap.clear();
          count = 0;
          l = r + wordLen;
          continue;
        }
        subMap.put(word, subMap.getOrDefault(word, 0) + 1);
        if (subMap.get(word) <= wordsMap.get(word)) {
          count++;
        } else {
          while (subMap.get(word) > wordsMap.get(word)) {
            final String first = s.substring(l, l += wordLen);
            subMap.put(first, subMap.getOrDefault(first, 0) - 1);
            if (subMap.get(first) < wordsMap.getOrDefault(first, 0)) {
              count--;
            }
          }
        }
        if (count == wordNum) {
          res.add(l);
          count--;
          final String first = s.substring(l, l += wordLen);
          subMap.put(first, subMap.get(first) - 1);
        }
      }
    }
    return res;
  }

  public static void main(String[] args) {
    String word = "wordgoodgoodgoodbestword";
    String[] words = {"word", "good", "best", "good"};
    LC30SubstrWithConcatenationAllWords.findSubstring2(word, words);
  }
}
