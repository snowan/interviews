package leetcode.string.LC1163;

public class LC1163LastSubstringInLexicographicalOrder {
  public String lastSubstring(String s) {
    char maxChar = s.charAt(0);
    int maxCharIdx = 0;
    int len = s.length();
    for (int i = 1; i < len; i++) {
      char curr = s.charAt(i);
      if (curr > maxChar
          || (curr == maxChar
          && s.substring(maxCharIdx).compareTo(s.substring(i)) < 0)) {
        maxChar = curr;
        maxCharIdx = i;
      }
    }
    return s.substring(maxCharIdx);
  }
}
