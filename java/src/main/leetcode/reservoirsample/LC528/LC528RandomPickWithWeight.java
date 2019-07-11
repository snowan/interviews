package main.leetcode.reservoirsample.LC528;

import java.util.Random;
import java.util.TreeMap;

/**
 * 528. Random Pick with Weight
 * Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex
 * which randomly picks an index in proportion to its weight.
 *
 * Note:
 *
 * 1 <= w.length <= 10000
 * 1 <= w[i] <= 10^5
 * pickIndex will be called at most 10000 times.
 * Example 1:
 *
 * Input:
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * Output: [null,0]
 * Example 2:
 *
 * Input:
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * Output: [null,0,1,1,1,0]
 * Explanation of Input Syntax:
 *
 * The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument,
 * the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.
 */
public class LC528RandomPickWithWeight {
  /**
   * Solution: prefix Sum + binary search
   * for example: [1, 2, 5, 3, 4]
   * prefix Sum; [1, 3, 8, 11, 15]
   * Random pick: [1, 15]
   * index pick:
   * 0 - [1]
   * 1 - [2, 3]
   * 2 - [4, 8]
   * 3 - [9, 11]
   * 4 - [12, 15]
   *
   * now, the problem becomes to search in a range. binary search performs well.
   * for example, random get 10,
   * lo = 0, hi = 4, mid = 2, presum[2] = 8 < 10, lo = mid + 1 = 3
   * lo = 3, hi = 4, mid = 3, presum[3] = 11 > 10, hi = mid = 3
   * lo = 3, hi = 3, lo == hi, break while loop.
   * return index = 3, from index matching range table, 10 is within [9, 11] - index = 3.
   *
   */
  private int[] preSum;
  private int sum;
  private int len;
  private Random random;
  public LC528RandomPickWithWeight(int[] w) {
    preSum = w;
    len = w.length;
    for (int i = 1; i < len; i++) {
      preSum[i] += w[i - 1];
    }
    sum = preSum[len - 1];
    random = new Random();
  }

  public int pickIndex() {
    int next = random.nextInt(sum) + 1;
    int lo = 0;
    int hi = len - 1;
    while (lo < hi) {
      int mid = (hi - lo) / 2 + lo;
      if (preSum[mid] == next) return mid;
      else if (preSum[mid] < next) {
        lo = mid + 1;
      } else {
        hi = mid;
      }
    }
    return lo;
  }

  /**
   * Solution #2: use TreeMap
   */
  private TreeMap<Integer, Integer> map;
  private int count;
  private Random rnd;
  public LC528RandomPickWithWeight(int[] w, int size) {
    map = new TreeMap<>();
    for (int i = 0; i < size; i++) {
      count += w[i];
      map.put(count, i);
    }
    rnd = new Random();
  }

  public int randomePickIndex() {
    int pick = rnd.nextInt(count);
    int key = map.higherKey(pick);
    return map.get(key);
  }
}
