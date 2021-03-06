package leetcode.string.LC459;

/**
 * 459. Repeated Substring Pattern
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies
 * of the substring together. You may assume the given string consists of lowercase English letters only and
 * its length will not exceed 10000.
 *
 * Example 1:
 *
 * Input: "abab"
 * Output: True
 * Explanation: It's the substring "ab" twice.
 * Example 2:
 *
 * Input: "aba"
 * Output: False
 * Example 3:
 *
 * Input: "abcabcabcabc"
 * Output: True
 * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 */
public class LC459RepeatSubstrPattern {
  public boolean repeatedSubstringPattern(String s) {
    if (s == null || s.length() == 0) return true;
    int len = s.length();
    for (int i = len / 2; i >= 1; i--) {
      if (len % i != 0) continue;
      String pattern = s.substring(0, i);
      if (s.replaceAll(pattern, "").length() == 0) return true;
    }
    return false;
  }

  public boolean repeatedSubstringPattern2(String s) {
    if (s == null || s.length() == 0) return true;
    int len = s.length();
    return (s + s).substring(1, 2 * len - 1).contains(s);
  }
}
