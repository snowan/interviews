package leetcode.array.LC31;

import java.util.Arrays;

/**
 * 31. Next Permutation
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 *
 * The replacement must be in-place and use only constant extra memory.
 *
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 *
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class LC31NextPermutation {
  public void nextPermutation(int[] nums) {
    if (nums == null || nums.length == 0) return;
    int len = nums.length;
    int idx = len - 1;
    while (idx > 0) {
      if (nums[idx] > nums[idx - 1]) break;
      idx--;
    }
    int prev = idx - 1;
    Arrays.sort(nums, idx, len);
    if (prev < 0) return;
    for (int i = prev + 1; i < len; i++) {
      if (nums[prev] < nums[i]) {
        swap(nums, prev, i);
        break;
      }
    }

  }
  private void swap(int[] nums, int fromIndex, int toIndex) {
    int tmp = nums[toIndex];
    nums[toIndex] = nums[fromIndex];
    nums[fromIndex] = tmp;
  }
}
