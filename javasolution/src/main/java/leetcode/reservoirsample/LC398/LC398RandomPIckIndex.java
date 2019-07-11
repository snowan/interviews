package leetcode.reservoirsample.LC398;

import java.util.*;

/**
 * 398. Random Pick Index
 * Given an array of integers with possible duplicates, randomly output the index of a given target number.
 * You can assume that the given target number must exist in the array.
 * <p>
 * Note:
 * The array size can be very large. Solution that uses too much extra space will not pass the judge.
 * <p>
 * Example:
 * <p>
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 * <p>
 * // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 * solution.pick(3);
 * <p>
 * // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 * solution.pick(1);
 */
public class LC398RandomPIckIndex {
  Map<Integer, List<Integer>> map;
  Random random;

  public LC398RandomPIckIndex(int[] nums) {
    map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      map.computeIfAbsent(nums[i], c -> new ArrayList<>()).add(i);
    }
    random = new Random();
  }

  public int pick(int target) {
    if (!map.containsKey(target)) return -1;
    int size = map.get(target).size();
    return map.get(target).get(random.nextInt(size));
  }
}
