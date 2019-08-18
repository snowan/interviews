package leetcode.hashtable.LC1160;

/**
 * https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/
 */
public class LC1160WordsCanBeReFormed {
  public int countCharacters(String[] words, String chars) {
    int[] map = new int[26];
    for (char ch : chars.toCharArray()) {
      map[ch - 'a']++;
    }
    int res = 0;
    for (String word : words) {
      if (canFormWord(word, map.clone())) {
        res += word.length();
      }
    }
    return res;
  }

  private boolean canFormWord(String word, int[] map) {
    for (char ch : word.toCharArray()) {
      if (map[ch - 'a'] == 0) return false;
      map[ch - 'a']--;
    }
    return true;
  }
}
