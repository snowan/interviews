package leetcode.array.LC1157;

public class LC1157MajorityElementInSubarry {
  /**
   * Boyer-Moore majority vote algorithm
   * https://www.wikiwand.com/en/Boyer%E2%80%93Moore_majority_vote_algorithm
   *
   * TC: O(n)
   * SC: O(1)
   */
  int[] input;
    public LC1157MajorityElementInSubarry(int[] arr) {
      input = arr;
    }

    public int query(int left, int right, int threshold) {
      int majority = Integer.MIN_VALUE;
      int count = 0;
      // find majority
      for (int i = left; i <= right; i++) {
        if (count == 0) {
          majority = input[i];
        }
        count = majority == input[i] ? count + 1 : count - 1;
      }

      // check threshold
      count = 0;
      for (int i = left; i <= right; i++) {
        count = majority == input[i] ? count + 1 : count;
      }
      return count >= threshold ? majority : -1;
    }
  }

/**
 * Your MajorityChecker object will be instantiated and called as such:
 * MajorityChecker obj = new MajorityChecker(arr);
 * int param_1 = obj.query(left,right,threshold);
 */
