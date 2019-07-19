package google;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class MinDays2Bloom {
  public static int minDaysBloom(int[] roses, int k, int n) {
    if (roses == null || roses.length == 0 || n == 0) return 0;
    int[] maxKWindow = getMaxInKWindow(roses, k);
    int len = roses.length;
    int[][] dp = new int[n + 1][len + 1];
    for (int i = 1; i <= n; i++) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
      for (int j = k; j <= len; j++) {
        dp[i][j] = Math.min(dp[i][j - 1], Math.max(dp[i - 1][j - 1], maxKWindow[j - k]));
        System.out.print("dp[" + i + "]" + "[" + j + "] = " + dp[i][j] + ", ");
      }
      System.out.println();
    }
    System.out.println("Min days to Bloom: " + dp[n][len]);
    return dp[n][len];
  }
  private static int[] getMaxInKWindow(int[] roses, int k) {
    int len = roses.length;
    int[] res = new int[len - k + 1];
    Deque<Integer> dq = new LinkedList<>();
    int idx = 0;
    for (int i = 0; i < len; i++) {
      while (!dq.isEmpty() && dq.peek() < i - k + 1) {
        dq.poll();
      }
      while (!dq.isEmpty() && roses[dq.peekLast()] <= roses[i]) {
        dq.pollLast();
      }
      dq.offer(i);
      if (i >= k - 1) {
        res[idx++] = roses[dq.peek()];
      }
    }
    System.out.println("Max In K Window: " + Arrays.toString(res));
    return res;
  }

  public static void main(String[] args) {
    MinDays2Bloom.minDaysBloom(new int[]{1, 2, 4, 9, 3, 4, 1}, 2, 2);
  }
}
