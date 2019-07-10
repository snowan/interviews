package main.leetcode.recursion_dfs_bfs.LC140;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC140WordBreakII {
  public List<String> wordBreak(String s, List<String> wordDict) {
    List<String> res = new ArrayList<>();
    if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) return res;
    return helper(s, wordDict, new HashMap<String, List<String>>());
  }
  private List<String> helper(String s, List<String> words, Map<String, List<String>> map) {
    if (map.containsKey(s)) return map.get(s);
    List<String> res = new ArrayList<>();
    for (String word : words) {
      if (s.startsWith(word)) {
        if (word.length() == s.length()) {
          res.add(word);
          continue;
        }
        List<String> subs = helper(s.substring(word.length()), words, map);
        for (String ss : subs) {
          res.add(word + " " + ss);
        }
      }
    }
    map.put(s, res);
    return res;
  }
}
