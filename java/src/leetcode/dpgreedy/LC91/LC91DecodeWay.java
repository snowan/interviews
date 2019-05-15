package leetcode.dpgreedy.LC91;


import java.util.HashMap;
import java.util.Map;

public class LC91DecodeWay {
  /**
   * Solution: DP
   * dp[i] -
   *
   * TC: O(N) - N is the length of input string
   * SC: O(N)
   */
  public int numDecodings(String s) {
    if (s == null || s.length() == 0) return 0;
    int len = s.length();
    int[] dp = new int[len];
    dp[0] = s.charAt(0) == '0' ? 0 : 1;
    for (int i = 1; i < len; i++) {
      int curr = s.charAt(i) - '0';
      int prev = s.charAt(i - 1) - '0';
      if (curr != 0) dp[i] = dp[i - 1];
      int num = prev * 10 + curr;
      if (num >= 10 && num <= 26) {
        dp[i] += i >= 2 ? dp[i - 2] : 1;
      }
    }
    return dp[len - 1];
  }

  /**
   * solution #2. recursive + memorization
   *
   * TC: O(n)
   * SC: O(n)
   */
  public int numDecodes(String s) {
    if (s == null || s.length() == 0) return 0;
    return helper(s, 0, new HashMap<Integer, Integer>());
  }
  private int helper(String s, int idx, Map<Integer, Integer> map) {
    if (idx == s.length()) return 1;
    if (map.containsKey(idx)) return map.get(idx);
    int res = 0;
    int curr = s.charAt(idx) - '0';
    if (curr != 0) res += helper(s, idx + 1, map);
    int num = (idx < s.length() - 1) ? curr * 10 + (s.charAt(idx + 1) - '0') : 0;
    if (num >= 10 && num <= 26) res += helper(s, idx + 2, map);
    map.put(idx, res);
    return res;
  }
}
