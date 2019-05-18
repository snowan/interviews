package leetcode.dpgreedy.LC689;

/**
 * 689. Maximum Sum of 3 Non-Overlapping Subarrays
 *
 * In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.
 * Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.
 *
 * Return the result as a list of indices representing the starting position of each interval (0-indexed).
 * If there are multiple answers, return the lexicographically smallest one.
 *
 * Example:
 *
 * Input: [1,2,1,2,6,7,5,1], 2
 * Output: [0, 3, 5]
 * Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
 * We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 *
 * Note:
 *
 * nums.length will be between 1 and 20000.
 * nums[i] will be between 1 and 65535.
 * k will be between 1 and floor(nums.length / 3).
 */
public class LC689MaxSumOf3Subarr {
  /**
   * Solution: DP
   * calculate presum[i] - sum from index 0 to i [0, i]
   * calculate maxsum[i, i + k] from left to right, and record left most index into leftPos[i] (> sum)
   * calculate maxsum[i + k, len] from right to left, record left most index into rightPos[i + k] (>= sum)
   * mid [k ~ n - 2 * k]; left [0, mid - 1], right [mid + k, len]
   * calculate maxsum[left + mid + right], and record the pos.
   *
   * TC: O(n) - n is the length of input array
   * SC: O(n)
   */
  public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
    int[] res = new int[3];
    if (nums == null || nums.length < 3) return res;
    int len = nums.length;
    int[] preSum = new int[len + 1];
    int[] leftPos = new int[len];
    int[] rightPos = new int[len];
    for (int i = 1; i <= len; i++) {
      preSum[i] = preSum[i - 1] + nums[i - 1];
    }
    int sum = preSum[k] - preSum[0];
    for (int l = k; l < len; l++) {
      int currSum = preSum[l + 1] - preSum[l - k + 1];
      if (currSum > sum) { // left pos no need to update when equal move to right
        leftPos[l] = l - k + 1;
        sum = currSum;
      } else {
        leftPos[l] = leftPos[l - 1];
      }
    }
    sum = preSum[len] - preSum[len - k];
    rightPos[len - k] = len - k;
    for (int r = len - k - 1; r >= 0; r--) {
      int currSum = preSum[r + k] - preSum[r];
      if (currSum >= sum) { // right pos need to update when equal move to left (lexicographically smaller pos)
        rightPos[r] = r;
        sum = currSum;
      } else {
        rightPos[r] = rightPos[r + 1];
      }
    }

    int maxSum = 0;
    for (int mid = k; mid <= len - 2 * k; mid++) {
      int left = leftPos[mid - 1];
      int right = rightPos[mid + k];
      int currSum = (preSum[left + k] - preSum[left]) // left sum
          + (preSum[mid + k] - preSum[mid]) // mid sum
          + (preSum[right + k] - preSum[right]); // right sum
      if (currSum > maxSum) {
        res[0] = left;
        res[1] = mid;
        res[2] = right;
        maxSum = currSum;
      }
    }
    return res;
  }
}
