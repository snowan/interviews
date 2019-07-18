package google;

public class ChunkedPalindrome {
  public static int maxChunkedPalindrome2(String s) {
    if (s == null || s.length() == 0) return 0;
    int l = 0;
    int r = s.length() - 1;
    int max = 0;
    int preL = l;
    int preR = r;
    while (l < r) {
      String prefix = s.substring(preL, l + 1); // include right
      String sufix = s.substring(r, preR + 1);
      if (prefix.equals(sufix)) {
        preL = l + 1;
        preR = r - 1;
        max += 2;
      }
      l++;
      r--;
    }
    if (preL <= preR) max++;
    System.out.println("max chunk palindrom: " + max);
    return max;
  }

  public static void main(String[] args) {
    ChunkedPalindrome.maxChunkedPalindrome2("volvol");
    ChunkedPalindrome.maxChunkedPalindrome2("volvolvo");
    ChunkedPalindrome.maxChunkedPalindrome2("vovo");
    ChunkedPalindrome.maxChunkedPalindrome2("aaaaaa");
    ChunkedPalindrome.maxChunkedPalindrome2("antaprezatepzapreanta");
    ChunkedPalindrome.maxChunkedPalindrome2("voabcvo");
    ChunkedPalindrome.maxChunkedPalindrome2("valve");
  }
}
