package main.leetcode.dpgreedy.LC416;

import java.util.Arrays;

/**
 * 416. Partition Equal Subset Sum
 *
 * Given a non-empty array containing only positive integers, find if the array can be partitioned
 * into two subsets such that the sum of elements in both subsets is equal.
 *
 * Note:
 *
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 *
 * Example 1:
 *
 * Input: [1, 5, 11, 5]
 *
 * Output: true
 *
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 * Example 2:
 *
 * Input: [1, 2, 3, 5]
 *
 * Output: false
 *
 * Explanation: The array cannot be partitioned into equal sum subsets.
 */
public class LC416PartitionEqualSum {
  public boolean canPartition(int[] nums) {
    if (nums == null || nums.length < 2) return false;
    int sum = Arrays.stream(nums).sum();
    if (sum % 2 != 0) return false;
    sum /= 2;
    boolean[] dp = new boolean[sum + 1];
    dp[0] = true;
    for (int num : nums) {
      for (int i = sum; i >= 0; i--) {
        if (i >= num) {
          dp[i] |= dp[i - num];
        }
        if (dp[sum]) return true;
      }
    }

    return dp[sum];
  }
}
