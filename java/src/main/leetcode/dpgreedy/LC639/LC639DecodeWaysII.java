package main.leetcode.dpgreedy.LC639;

/**
 * 639. Decode Ways II
 * A message containing letters from A-Z is being encoded to numbers using the following mapping way:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Beyond that, now the encoded string can also contain the character '*',
 * which can be treated as one of the numbers from 1 to 9.
 *
 * Given the encoded message containing digits and the character '*', return the total number of ways to decode it.
 *
 * Also, since the answer may be very large, you should return the output mod 109 + 7.
 *
 * Example 1:
 *
 * Input: "*"
 * Output: 9
 * Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
 * Example 2:
 *
 * Input: "1*"
 * Output: 9 + 9 = 18
 * Note:
 *
 * The length of the input string will fit in range [1, 105].
 * The input string will only contain the character '*' and digits '0' - '9'.
 */
public class LC639DecodeWaysII {
  /**
   * Solution: recursive + memorization
   *
   * TC: O(N) - N is the length of input String
   * SC: O(N)
   */
  private static final int MOD = 1000000007;
  public int numDecodings(String s) {
    if (s == null || s.length() == 0) return 0;
    return (int)helper(s, 0, new int[s.length()]);
  }
  private long helper(String s, int idx, int[] map) {
    if (idx >= s.length()) return 1;
    if (map[idx] != 0) return map[idx];
    char curr = s.charAt(idx);
    int c1 = 0;
    if (curr == '0') c1 = 0;
    else c1 = curr == '*' ? 9 : 1;
    long res = 0;
    if (idx == s.length() - 1) res = c1;
    else {
      res = (c1 * helper(s, idx + 1, map)) % MOD;
      char next = s.charAt(idx + 1);
      res = (res + count(curr, next) * helper(s, idx + 2, map)) % MOD;
    }
    map[idx] = (int)res;
    return res;
  }

  private int count(char curr, char next) {
    if (curr == '0') return 0;
    if (curr == '*' && next == '*') return 15;
    if (curr == '*') {
      if (next <= '6') return 2;
      return 1;
    }
    if (next == '*') {
      if (curr == '1') return 9;
      if (curr == '2') return 6;
      return 0;
    }
    if (curr == '1' || (curr == '2' && next <= '6')) return 1;
    return 0;
  }

  /**
   * Solution: #2. dp[len + 1] - len is the input string length;
   * dp[i] number of decodes ways from index [0, i]
   * init: dp[0] = 1
   * dp[1] = s.charAt(0) == '*' ? 9 : 1;
   * transition:
   * check prev char and curr char.
   * only consider 1 character: get count c1
   * dp[i] = c1 * dp[i - 1];
   * consider 2 prev characters, get count c2
   * dp[i] = c2 * dp[i - 2]
   *
   * return dp[len].
   *
   * TC: O(n)
   * SC: O(n)
   * */
  public int numDecodes(String s) {
    if (s == null || s.length() == 0 || s.charAt(0) == '0') return 0;
    int len = s.length();
    long[] dp = new long[len + 1];
    dp[0] = 1;
    dp[1] = s.charAt(0) == '*' ? 9 : 1;
    for (int i = 2; i <= len; i++) {
      char curr = s.charAt(i - 1);
      char prev = s.charAt(i - 2);

      if (curr == '*') dp[i] += 9 * dp[i - 1];
      else if (curr != '0') dp[i] += dp[i - 1];

      dp[i] += count(prev, curr) * dp[i - 2];
      dp[i] %= MOD;
    }

    return (int)dp[len];
  }
}
