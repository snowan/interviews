package leetcode.dpgreedy.LC1147;

public class LC1147LongestChunkedPalindrome {
  public int longestDecomposition1(String text) {
    if (text == null || text.length() == 0) return 0;
    int len = text.length();
    for (int i = 0; i < len / 2; i++) {
      String prefix = text.substring(0, i + 1);
      String suffix = text.substring(len - i - 1, len);
      if (prefix.equals(suffix)) {
        return 2 + longestDecomposition1(text.substring(i + 1, len - i - 1));
      }
    }
    return 1;
  }

  public int longestDecomposition2(String text) {
    if (text == null || text.length() == 0) return 0;
    int l = 0;
    int r = text.length() - 1;
    int max = 0;
    int preL = l;
    int preR = r;
    while (l < r) {
      String prefix = text.substring(preL, l + 1); // include right
      String sufix = text.substring(r, preR + 1);
      if (prefix.equals(sufix)) {
        preL = l + 1;
        preR = r - 1;
        max += 2;
      }
      l++;
      r--;
    }
    if (preL <= preR) max++;
    return max;
  }
}
