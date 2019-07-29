package google;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a coke machine with a series of buttons. If you press a button it will get you a certain range of coke.
 * Find out if it's possible to get the target range of coke. You can press buttons any number of times.
 * <p>
 * Example 1:
 * <p>
 * Input: buttons = [[100, 120], [200, 240], [400, 410]], target = [100, 110]
 * Output: false
 * Explanation: if we press first button it might give us 120 volume of coke, not in the target range.
 * Example 2:
 * <p>
 * Input: buttons = [[100, 120], [200, 240], [400, 410]], target = [90, 120]
 * Output: true
 * Explanation: press first button and you will always get amount of coke in the target range.
 * Example 3:
 * <p>
 * Input: buttons = [[100, 120], [200, 240], [400, 410]], target = [300, 360]
 * Output: true
 * Explanation: press first and second button and you will always get amount of coke in the target range.
 * Example 4:
 * <p>
 * Input: buttons = [[100, 120], [200, 240], [400, 410]], target = [310, 360]
 * Output: false
 * Explanation: we can press 1st button 3 times or 1st and 2nd button but it's possible to get only 300, not in the target range.
 * Example 5:
 * <p>
 * Input: buttons = [[100, 120], [200, 240], [400, 410]], target = [1, 9999999999]
 * Output: true
 * Explanation: you can press any button.
 */
public class CokeMachine {
  public static boolean coke(int[][] buttons, int[] target) {
    int m = target[0];
    int n = target[1];
    boolean[][] dp = new boolean[m + 1][n + 1];
    // initial, all button falls between target, then dp[i][j]=true
    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        for (int[] button : buttons) {
          if (i <= button[0] && j >= button[1]) {
            dp[i][j] = true;
            break;
          }
        }
      }
    }

    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        for (int[] button : buttons) {
          int preL = i - button[0];
          int preR = j - button[1];
          if (preL >= 0 && preR >= 0 && dp[preL][preR]) {
            dp[i][j] = true;
            break;
          }
        }
      }
    }
    return dp[m][n];
  }

  public static boolean cokeDFSMemo(int[][] buttons, int[] target) {
    return helper(buttons, target, 0, 0, new HashSet<>());
  }
  private static boolean helper(int[][] buttons, int[] target, int low, int high, Set<String> visited) {
    if (low >= target[0] && high <= target[1]) return true;
    if (high > target[1]) return false;
    String key = low + "_" + high;
    if (visited.contains(key)) return false;
    for (int[] btn : buttons) {
      low += btn[0];
      high += btn[1];
      if (helper(buttons, target, low, high, visited)) return true;
      low -= btn[0];
      high -= btn[1];
    }
    visited.add(key);
    return false;
  }

  public static void main(String[] args) {
    int[][] buttons1 = {{100, 120}, {200, 240}, {400, 410}};
    int[][] buttons2 = {{100, 120}, {200, 240}, {400, 410}};
    int[][] buttons3 = {{100, 120}, {200, 240}, {400, 410}};
    int[][] buttons4 = {{100, 120}, {200, 240}, {400, 410}};
    int[][] buttons5 = {{100, 120}, {200, 240}, {400, 410}};
    System.out.println(CokeMachine.coke(buttons1, new int[]{100, 110}));
    System.out.println(CokeMachine.coke(buttons2, new int[]{90, 120}));
    System.out.println(CokeMachine.coke(buttons3, new int[]{300, 360}));
    System.out.println(CokeMachine.coke(buttons4, new int[]{310, 360}));
    System.out.println(CokeMachine.coke(buttons5, new int[]{1, 999999999}));
    System.out.println("====================================================");
    System.out.println(CokeMachine.cokeDFSMemo(buttons1, new int[]{100, 110}));
    System.out.println(CokeMachine.cokeDFSMemo(buttons2, new int[]{90, 120}));
    System.out.println(CokeMachine.cokeDFSMemo(buttons3, new int[]{300, 360}));
    System.out.println(CokeMachine.cokeDFSMemo(buttons4, new int[]{310, 360}));
    System.out.println(CokeMachine.cokeDFSMemo(buttons5, new int[]{1, 999999999}));
  }
}
