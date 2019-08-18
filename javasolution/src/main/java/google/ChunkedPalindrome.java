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

  public int longestDecomposition(String text) {
    if (text == null || text.length() == 0) return 0;
    int len = text.length();
    for (int i = 0; i < len / 2; i++) {
      String prefix = text.substring(0, i + 1);
      String suffix = text.substring(len - i - 1, len);
      if (prefix.equals(suffix)) {
        return 2 + longestDecomposition(text.substring(i + 1, len - i - 1));
      }
    }
    return 1;
  }

  public static int maxChunkedPalindrome(String s) {
    if (s == null || s.length() == 0) return 0;
    int max = helper(s, 0, 0, s);
    System.out.println("max chunk palindrom: " + max);
    return max;
  }

  private static int helper(String curr, int count, int len, String s) {
    if (curr == null || curr.isEmpty()) return count;
    if (curr.length() <= 1) {
      if (count != 0 && s.length() - len <= 1) {
        return count + 1;
      } else return 1;
    }
    int currLen = curr.length();
    // get left and right substring and compare
    for (int i = 0; i < currLen / 2; i++) {
      String left = curr.substring(0, i + 1);
      String right = curr.substring(currLen - 1 - i, currLen);
      if (left.equals(right)) {
        return helper(curr.substring(i + 1, currLen - 1 - i),
            count + 2, len + (i + 1) * 2, s);
      }
    }
    return count + 1;
  }

  public static void main(String[] args) {
    ChunkedPalindrome.maxChunkedPalindrome2("volvol");
    ChunkedPalindrome.maxChunkedPalindrome2("volvolvo");
    ChunkedPalindrome.maxChunkedPalindrome2("vovo");
    ChunkedPalindrome.maxChunkedPalindrome2("aaaaaa");
    ChunkedPalindrome.maxChunkedPalindrome2("antaprezatepzapreanta");
    ChunkedPalindrome.maxChunkedPalindrome2("voabcvo");
    ChunkedPalindrome.maxChunkedPalindrome2("valve");

    System.out.println("============================================");
    ChunkedPalindrome.maxChunkedPalindrome("volvol");
    ChunkedPalindrome.maxChunkedPalindrome("volvolvo");
    ChunkedPalindrome.maxChunkedPalindrome("vovo");
    ChunkedPalindrome.maxChunkedPalindrome("aaaaaa");
    ChunkedPalindrome.maxChunkedPalindrome("antaprezatepzapreanta");
    ChunkedPalindrome.maxChunkedPalindrome("voabcvo");
    ChunkedPalindrome.maxChunkedPalindrome("valve");
  }
}
