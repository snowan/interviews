package leetcode.string.LC1177;

import java.util.ArrayList;
import java.util.List;

public class LC1177CanMakePalindromeFromSubstring {
  public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
    int n = s.length();
    int[][] prefixSum = new int[n + 1][26];
    for (int i = 0; i < n; i++) {
      prefixSum[i + 1] = prefixSum[i].clone();
      prefixSum[i + 1][s.charAt(i) - 'a']++;
    }
    List<Boolean> res = new ArrayList<>();
    for (int[] q : queries) {
      int start = q[0];
      int end = q[1];
      int k = q[2];
      int count = 0;
      // count odd characters number
      for (int i = 0; i < 26; i++) {
        count += (prefixSum[end + 1][i] - prefixSum[start][i]) % 2;
      }
      res.add(count / 2 <= k);
    }
    return res;
  }
}
