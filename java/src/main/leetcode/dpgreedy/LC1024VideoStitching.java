package main.leetcode.dpgreedy;

import java.util.Arrays;

/**
 * 1024. Video Stitching
 * https://leetcode.com/problems/video-stitching/
 *
 * You are given a series of video clips from a sporting event that lasted T seconds.
 * These video clips can be overlapping with each other and have varied lengths.
 * <p>
 * Each video clip clips[i] is an interval: it starts at time clips[i][0] and ends at time clips[i][1].
 * We can cut these clips into segments freely:
 * for example, a clip [0, 7] can be cut into segments [0, 1] + [1, 3] + [3, 7].
 * <p>
 * Return the minimum number of clips needed so that we can cut the clips into segments that cover the
 * entire sporting event ([0, T]).  If the task is impossible, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
 * Output: 3
 * Explanation:
 * We take the clips [0,2], [8,10], [1,9]; a total of 3 clips.
 * Then, we can reconstruct the sporting event as follows:
 * We cut [1,9] into segments [1,2] + [2,8] + [8,9].
 * Now we have segments [0,2] + [2,8] + [8,10] which cover the sporting event [0, 10].
 * Example 2:
 * <p>
 * Input: clips = [[0,1],[1,2]], T = 5
 * Output: -1
 * Explanation:
 * We can't cover [0,5] with only [0,1] and [0,2].
 * Example 3:
 * <p>
 * Input: clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
 * Output: 3
 * Explanation:
 * We can take clips [0,4], [4,7], and [6,9].
 * Example 4:
 * <p>
 * Input: clips = [[0,4],[2,8]], T = 5
 * Output: 2
 * Explanation:
 * Notice you can have extra video after the event ends.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= clips.length <= 100
 * 0 <= clips[i][0], clips[i][1] <= 100
 * 0 <= T <= 100
 */
public class LC1024VideoStitching {
  /**
   * Solution: Greedy, sort clips by start time. idx start from 0, and start from 0, for each interval,
   * if start > end, then no interval cover end time, return -1. for example, end start from 0, first clip start from 1,
   * [1, 3], clips sorted by start time, so cannot find 0.
   * and for all same start time, we want max end(greedy), for example, [0,1],[0,2],[0,4], for end = 0, we want [0,4]
   * which will cover the most segment. then next end will start from the max.
   *
   * TC: O(NLogN) - N is the length of clips
   * SC: O(1)
   */
  public int videoStitching(int[][] clips, int T) {
    if (clips == null || clips.length == 0) return -1;
    if (T == 0) return 0;
    Arrays.sort(clips, (a, b) -> a[0] - b[0]);
    int res = 0;
    int end = 0;
    int len = clips.length;
    int idx = 0;
    while (idx < len) {
      if (clips[idx][0] > end) return -1;
      int nextEnd = end;
      while (idx < len && clips[idx][0] <= end) {
        nextEnd = Math.max(nextEnd, clips[idx++][1]);
      }
      res++;
      end = nextEnd;
      if (end >= T) return res;
    }
    return -1;
  }

  /**
   *  Solution: DP
   *  dpgreedy[T + 1], calculate min for each t ([0,T]. for each t,
   *  loop clips, for each clop, when t is within interval (t >= curr[0], t <= curr[1]). update dpgreedy[t]
   *  dpgreedy[t] = min(dpgreedy[i], dpgreedy[curr[0]] + 1)
   *  end loop, check current t, if dpgreedy[t] == T + 1, then no interval cover t. then return -1.
   *  otherwise, continue.
   *
   * TC: O(N * T) - N is clips size, T is target
   * SC: O (T)
   */
  public int videoStitchingDP(int[][] clips, int T) {
    if (clips == null || clips.length == 0) return 0;
    int[] dp = new int[T + 1];
    Arrays.fill(dp, T + 1);
    dp[0] = 0;
    for (int t = 0; t <= T; t++) {
      for (int[] c : clips) {
        if (t >= c[0] && t <= c[1]) {
          dp[t] = Math.min(dp[t], dp[c[0]] + 1);
        }
      }
      if (dp[t] == T + 1) return -1;
    }
    return dp[T];
  }
}
