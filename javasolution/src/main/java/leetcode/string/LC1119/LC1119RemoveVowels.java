package leetcode.string.LC1119;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 1119. Remove Vowels from a String
 * Given a string S, remove the vowels 'a', 'e', 'i', 'o', and 'u' from it, and return the new string.
 *
 * Example 1:
 *
 * Input: "leetcodeisacommunityforcoders"
 * Output: "ltcdscmmntyfrcdrs"
 * Example 2:
 *
 * Input: "aeiou"
 * Output: ""
 *
 * Note:
 *
 * S consists of lowercase English letters only.
 * 1 <= S.length <= 1000
 */
public class LC1119RemoveVowels {
  // Solution #1. regex, replace all vowels to ""
  public String removeVowels(String S) {
    return S.replaceAll("[aAiIuUeEoO]", "");
  }

  // Solution #2, add all vowels into set, loop through String S, build new string which are not vowels
  public String removeVowels2(String S) {
    Set<Character> vowel = new HashSet<>(Arrays.asList('a', 'i', 'u', 'e', 'o'));
    StringBuilder sb = new StringBuilder();
    for (char ch : S.toCharArray()) {
      if (vowel.contains(ch)) continue;
      sb.append(ch);
    }
    return sb.toString();
  }
}
