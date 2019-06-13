package leetcode.twopointers.LC828;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LC828UniqueLetter {
  private static final int MOD = (int)Math.pow(10, 9) + 7;
  public static int uniqueLetterString(String S) {
    if (S == null || S.length() == 0) return 0;
    int n = S.length();
    if (n == 1) return n;
    long res = n;
    for (int len = 2; len <= n; len++) {
      Map<Character, Integer> counts = new HashMap<>();
      for (int i = 0; i < len; i++) {
        counts.put(S.charAt(i), counts.getOrDefault(S.charAt(i), 0) + 1);
      }
      res = (res + getCount(counts)) % MOD;

      for (int l = 1; l <= n - len; l++) {
        int r = len + l - 1;
        counts.put(S.charAt(l - 1), counts.get(S.charAt(l - 1)) - 1);
        if (counts.get(S.charAt(l - 1)) == 0) counts.remove(S.charAt(l - 1));
        counts.put(S.charAt(r), counts.getOrDefault(S.charAt(r), 0) + 1);
        res = (res + getCount(counts)) % MOD;
      }
    }
    System.out.println("count " + res);
    return (int)res;
  }

  private static int getCount(Map<Character, Integer> map) {
    return map.values().stream().filter(v -> v == 1).mapToInt(Integer::intValue).sum();
  }

  public static int uniqueLetterString2(String S) {
    if (S == null || S.length() == 0) return 0;
    Map<Character, List<Integer>> indexMap = new HashMap<>();
    int len = S.length();
    for (int i = 0; i < len; i++) {
      indexMap.computeIfAbsent(S.charAt(i), c -> new ArrayList<>()).add(i);
    }
    long res = 0;
    for (List<Integer> indices : indexMap.values()) {
      int size = indices.size();
      for (int i = 0; i < size; i++) {
        int currIdx = indices.get(i);
        int preIdx = i > 0 ? indices.get(i - 1) : -1;
        int nextIdx = i < size - 1 ? indices.get(i + 1) : len;
        res += (currIdx - preIdx) * (nextIdx - currIdx);
      }
      res %= MOD;
    }
    return (int)res;
  }



  public static void main(String[] args) {
    LC828UniqueLetter.uniqueLetterString("ABC");
    LC828UniqueLetter.uniqueLetterString("ABA");
    System.out.println("====================================");
    LC828UniqueLetter.uniqueLetterString2("ABC");
    LC828UniqueLetter.uniqueLetterString2("ABA");
  }
}
