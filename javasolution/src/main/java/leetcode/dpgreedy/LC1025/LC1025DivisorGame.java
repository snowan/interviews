package leetcode.dpgreedy.LC1025;

public class LC1025DivisorGame {
  /**
   * Solution: DP
   * dp[N+1]
   * dp[i] - the result of game at given number i the player who started the game
   * init:
   * dp[0] = dp[1] = false;
   * transition:
   * the player who start the game will try all the factors until win this round.
   * dp[i] = i % x == 0 && !dp[i - x] (x in [1, i) )
   *
   * TC: O(N * N)
   * SC: O(N)
   */
  public boolean divisorGame(int N) {
    if (N <= 1) return false;
    boolean[] dp = new boolean[N + 1];
    dp[0] = false;
    dp[1] = false;
    for (int i = 2; i <= N; i++) {
      for (int x = 1; x < i; x++) {
        if (i % x == 0 && !dp[i - x]) {
          dp[i] = true;
          break;
        }
      }
    }
    return dp[N];
  }

  /**
   * solution #2: return N % 2 == 0.
   * refer lee215 solution proof: https://leetcode.com/problems/divisor-game/discuss/274606/JavaC%2B%2BPython-return-N-2-0
   */
  public boolean divisorGame2(int N) {
    return N % 2 == 0;
  }
}
