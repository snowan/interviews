package main.leetcode.string.LC408;

/**
 * 408. Valid Word Abbreviation
 * Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.
 *
 * A string such as "word" contains only the following valid abbreviations:
 *
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * Notice that only the above abbreviations are valid abbreviations of the string "word".
 * Any other string is not a valid abbreviation of "word".
 *
 * Note:
 * Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.
 *
 * Example 1:
 *
 * Given s = "internationalization", abbr = "i12iz4n":
 *
 * Return true.
 * Example 2:
 *
 * Given s = "apple", abbr = "a2e":
 *
 * Return false.
 */
public class LC408ValidWordAbbr {
  public boolean validWordAbbreviation(String word, String abbr) {
    int len = word.length();
    int n = abbr.length();
    if (n > len) return false;
    int l = 0;
    int i = 0;
    while (l < len && i < n) {
      int num = 0;
      if (abbr.charAt(i) == '0') return false;
      while (i < n && Character.isDigit(abbr.charAt(i))) {
        num = num * 10 + (abbr.charAt(i) - '0');
        i++;
      }
      l = num == 0 ? l : l + num;
      if (l == len && i == n) return true;
      if (l >= len || i >= n || word.charAt(l) != abbr.charAt(i)) return false;
      l++;
      i++;
    }
    return true;
  }
}
