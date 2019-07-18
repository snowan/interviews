package google;

public class ChunkedPalindrome {  
  public int maxChunkedPalindrome2(String s) {
    if (s == null || s.length() == 0) return 0;
    int l = 0;
    int r = s.length() - 1;
    int max = 0;
    int preL = l;
    int preR = r + 1;
    while (l < r) {
      String prefix = s.substring(preL, l + 1); // include right
      String sufix = s.substring(r, preR);
      if (prefix == sufix) {
        preL = l + 1;
        preR = r - 1;
        max += 2;
      }
      l++;
      r--;
    }
    if (preL < preR) max++;
    return max;
  }
}
