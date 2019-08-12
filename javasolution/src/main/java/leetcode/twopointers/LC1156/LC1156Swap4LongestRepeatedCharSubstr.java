package leetcode.twopointers.LC1156;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1156. Swap For Longest Repeated Character Substring
 * Given a string text, we are allowed to swap two of the characters in the string.
 * Find the length of the longest substring with repeated characters.
 * <p>
 * Example 1:
 * <p>
 * Input: text = "ababa"
 * Output: 3
 * Explanation: We can swap the first 'b' with the last 'a', or the last 'b' with the first 'a'.
 * Then, the longest repeated character substring is "aaa", which its length is 3.
 * Example 2:
 * <p>
 * Input: text = "aaabaaa"
 * Output: 6
 * Explanation: Swap 'b' with the last 'a' (or the first 'a'), and we get longest repeated character substring "aaaaaa",
 * which its length is 6.
 * Example 3:
 * <p>
 * Input: text = "aaabbaaa"
 * Output: 4
 * Example 4:
 * <p>
 * Input: text = "aaaaa"
 * Output: 5
 * Explanation: No need to swap, longest repeated character substring is "aaaaa", length is 5.
 * Example 5:
 * <p>
 * Input: text = "abcdef"
 * Output: 1
 * <p>
 * Constraints:
 * <p>
 * 1 <= text.length <= 20000
 * text consist of lowercase English characters only.
 */
public class LC1156Swap4LongestRepeatedCharSubstr {
  public static int maxRepOpt1(String text) {
    if (text == null || text.length() == 0) return 0;
    int len = text.length();
    int max = 0;
    Map<Character, List<CharPos>> groupsMap = new HashMap<>();
    int l = 0;
    int r = 0;
    while (r < len) {
      char left = text.charAt(l);
      while (r < len && text.charAt(r) == left) {
        r++;
      }
      groupsMap.computeIfAbsent(left, c -> new ArrayList<>()).add(new CharPos(left, l, r - 1));
      max = Math.max(max, r - l);
      l = r;
    }
    if (max == len) return max;
    for (List<CharPos> currGroups : groupsMap.values()) {
      for (int i = 0; i < currGroups.size() - 1; i++) {
        CharPos curr = currGroups.get(i);
        CharPos next = currGroups.get(i + 1);
        int currCount = curr.end - curr.start + 1;
        int nextCount = next.end - next.start + 1;
        if (curr.end + 1 == next.start - 1) {
          max = Math.max(max, (currGroups.size() > 2 ? 1 : 0) + (currCount + nextCount));
        } else {
          max = Math.max(max, Math.max(currCount, nextCount) + 1);
        }
      }
    }
    System.out.println("Max: " + max);
    return max;
  }

  public static void main(String[] args) {
    LC1156Swap4LongestRepeatedCharSubstr.maxRepOpt1("ababa");
    LC1156Swap4LongestRepeatedCharSubstr.maxRepOpt1("aaabaaa");
    LC1156Swap4LongestRepeatedCharSubstr.maxRepOpt1("aaaabbaaa");
    LC1156Swap4LongestRepeatedCharSubstr.maxRepOpt1("aaaaa");
    LC1156Swap4LongestRepeatedCharSubstr.maxRepOpt1("abcdef");
  }

}
class CharPos {
  char ch;
  int start;
  int end;

  public CharPos(char ch, int start, int end) {
    this.ch = ch;
    this.start = start;
    this.end = end;
  }
}

