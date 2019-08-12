package leetcode.string.LC1153;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC1153StringTransform {
  /**
   * Solution: refer @lee215 https://leetcode.com/problems/string-transforms-into-another-string/discuss/355382/JavaC%2B%2BPython-Need-One-Unused-Character
   *
   */
  public static boolean canConvert(String str1, String str2) {
    if (str1.equals(str2)) return true;
    int len = str1.length();
    Set<Character> set = new HashSet<>();
    Map<Character, Character> matchMap = new HashMap<>();
    for (int i = 0; i < len; i++) {
      char ch1 = str1.charAt(i);
      char ch2 = str2.charAt(i);
      if (matchMap.getOrDefault(ch1, ch2) != ch2) return false;
      matchMap.put(ch1, ch2);
      set.add(ch2);
    }
    return set.size() < 26;
  }

  public static void main(String[] args) {
    System.out.println(LC1153StringTransform.canConvert("abcdefghijklmnopqrstuvwxyz", "bcdefghijklmnopqrstuvwxyza"));
  }
}
