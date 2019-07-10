package main.leetcode.dpgreedy.LC920;

/**
 * 920. Number of Music Playlists
 * Your music player contains N different songs and she wants to listen to L (not necessarily different)
 * songs during your trip.  You create a playlist so that:
 * <p>
 * Every song is played at least once
 * A song can only be played again only if K other songs have been played
 * Return the number of possible playlists.  As the answer can be very large, return it modulo 10^9 + 7.
 * <p>
 * Example 1:
 * <p>
 * Input: N = 3, L = 3, K = 1
 * Output: 6
 * Explanation: There are 6 possible playlists. [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1].
 * Example 2:
 * <p>
 * Input: N = 2, L = 3, K = 0
 * Output: 6
 * Explanation: There are 6 possible playlists. [1, 1, 2], [1, 2, 1], [2, 1, 1], [2, 2, 1], [2, 1, 2], [1, 2, 2]
 * Example 3:
 * <p>
 * Input: N = 2, L = 3, K = 1
 * Output: 2
 * Explanation: There are 2 possible playlists. [1, 2, 1], [2, 1, 2]
 * <p>
 * Note:
 * <p>
 * 0 <= K < N <= L <= 100
 */
public class LC920NumMucisPlaylist {
  /**
   * Solution: DP
   * dp[L+1][N+1]
   * dp[i][j] - the number of possible playlists of length i have j unique songs.
   * <p>
   * init:
   * dp[0][0] = 1
   * transition:
   * 1. the last added song is new song, then have (N-j+1) ways to chose the new song
   * dp[i][j] += dp[i - 1][j - 1] * (N - j + 1)
   * 2. the last added song is not new song. we need to keep K other songs play, then we have (j-K) ways to chose old song
   * dp[i][j] += dp[i - 1][j] * (j-K) (j > K)
   * <p>
   * result:
   * dp[L]N]
   * <p>
   * TC: O(L * N)
   * SC: O(L * N)
   */
  private static final long MOD = (long) 1e9 + 7;

  public int numMusicPlaylists(int N, int L, int K) {
    long[][] dp = new long[L + 1][N + 1];
    dp[0][0] = 1;
    for (int i = 1; i <= L; i++) {
      for (int j = 1; j <= N; j++) {
        dp[i][j] = (dp[i - 1][j - 1] * (N - j + 1)
            + (j > K ? (j - K) : 0) * dp[i - 1][j]) % MOD;
      }
    }
    return (int) dp[L][N];
  }
}
