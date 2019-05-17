package leetcode.dpgreedy.LC1043;

import java.util.HashMap;
import java.util.Map;

/**
 * 1043. Partition Array for Maximum Sum
 *
 * Given an integer array A, you partition the array into (contiguous) subarrays of length at most K.
 * After partitioning, each subarray has their values changed to become the maximum value of that subarray.
 *
 * Return the largest sum of the given array after partitioning.
 *
 * Example 1:
 *
 * Input: A = [1,15,7,9,2,5,10], K = 3
 * Output: 84
 * Explanation: A becomes [15,15,15,9,10,10,10]
 *
 * Note:
 *
 * 1 <= K <= A.length <= 500
 * 0 <= A[i] <= 10^6
 */
public class LC1043PartitionArrMaxSum {
  /**
   * Solution: DP
   * dp[i] - max from A[0],..., A[i-1]
   * For j = 1 .. k that keeps everything in bounds, dp[i] is the maximum of dp[i-j] + max(A[i-1], ..., A[i-j]) * j
   *
   * TC: O(N*K) - N is the length of input string, K is the subarray length
   * SC: O(N)
   */
  public static int maxSumAfterPartitioning(int[] A, int K) {
    if (A == null || A.length == 0) return 0;
    int len = A.length;
    int[] dp = new int[len];
    for (int i = 0; i < len; i++) {
      int maxVal = A[i];
      for (int j = 1; j <= K && i - j + 1 >= 0; j++) {
        maxVal = Math.max(maxVal, A[i - j + 1]);
        int maxSum = (i >= j ? dp[i - j] : 0) + maxVal * j;
        dp[i] = Math.max(maxSum, dp[i]);
      }
    }
    System.out.println("Result= " + dp[len - 1]);
    return dp[len - 1];
  }

  /**
   * Solution: resursive + memorization
   *
   * TC: O(N * K)
   * SC: O(N)
   */
  public static int maxSumPartition(int[] A, int K) {
    if (A == null || A.length == 0) return 0;
    int max = helper(A, K, 0, new HashMap<>());
    System.out.println("Recursive result= " + max);
    return max;
  }

  private static int helper(int[] A, int K, int idx, Map<Integer, Integer> map) {
    if (idx == A.length) return 0;
    if (map.containsKey(idx)) return map.get(idx);
    int maxSum = 0;
    int maxVal = A[idx];
    for (int i = idx; i <= idx + K - 1 && i < A.length; i++) {
      maxVal = Math.max(maxVal, A[i]);
      maxSum = Math.max(maxSum, helper(A, K, i + 1, map) + maxVal * (i - idx + 1));
    }
    map.put(idx, maxSum);
    return maxSum;
  }

  public static void main(String[] args) {
    int[] A = {20779,436849,274670,543359,569973,280711,252931,424084,361618,430777,136519,749292,933277,477067,502755,
        695743,413274,168693,368216,677201,198089,927218,633399,427645,317246,403380,908594,854847,157024,719715,336407,
        933488,599856,948361,765131,335089,522119,403981,866323,519161,109154,349141,764950,558613,692211};
    int K = 26;
    maxSumAfterPartitioning(A, K);
    maxSumPartition(A, K);
  }
}
