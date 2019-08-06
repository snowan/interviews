package leetcode.array.LC215;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 215. Kth Largest Element in an Array
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 *
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 *
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class LC215KthLargestElemInArr {
  /**
   * Solution 1: Heap, keep k elements in heap
   * <p>
   * TC: O(nlogk) - k elements
   * SC: O(k)
   *
   * reference: https://en.wikipedia.org/wiki/Quickselect
   */
  public static int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int num : nums) {
      pq.offer(num);
      if (pq.size() > k) {
        pq.poll();
      }
    }

    return pq.poll();
  }

  /**
   * Solution 2: Sort, kth largest = n - k
   * <p>
   * TC: O(nlogn) - n is the length of array
   * SC: O(1)
   */
  public static int findKthlargest2(int[] nums, int k) {
    Arrays.sort(nums);
    return nums[nums.length - k];
  }

  /**
   * solution 3: Quick Select
   * get kth largest number = get n - kth smallest number
   *
   * TC:
   * avg: O(n)
   * worst case: O(n * n)
   *
   * SC: O(1)
   */
  static Random random = new Random();
  public static int findKthLargest3(int[] nums, int k) {
    int len = nums.length;
    return select(nums, 0, len - 1, len - k);
  }

  private static int select(int[] nums, int left, int right, int k) {
    if (left == right) return nums[left];
    // random select pivotIndex between left and right
    int pivotIndex = left + random.nextInt(right - left);
    // do partition, move smaller than pivot number into pivot left
    int pos = partition(nums, left, right, pivotIndex);
    if (pos == k) {
      return nums[pos];
    } else if (pos > k) {
      return select(nums, left, pos - 1, k);
    } else {
      return select(nums, pos + 1, right, k);
    }
  }

  private static int partition(int[] nums, int left, int right, int pivotIndex) {
    int pivot = nums[pivotIndex];
    // move pivot to end
    System.out.println("pivot index: " + pivotIndex);
    swap(nums, right, pivotIndex);
    int pos = left;
    // move smaller num to pivot left
    for (int i = left; i <= right; i++) {
      if (nums[i] < pivot) {
        swap(nums, pos++, i);
      }
    }
    // move pivot to original place
    swap(nums, right, pos);
    System.out.println("after partition: " + Arrays.toString(nums));
    System.out.println("pivot pos: " + pos + ", number: " + nums[pos]);
    return pos;
  }

  private static void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }

  public static void main(String[] args) {
    System.out.println(LC215KthLargestElemInArr.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    System.out.println(LC215KthLargestElemInArr.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    System.out.println("--------------------------------------------");
    System.out.println(LC215KthLargestElemInArr.findKthlargest2(new int[]{3, 2, 1, 5, 6, 4}, 2));
    System.out.println(LC215KthLargestElemInArr.findKthlargest2(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    System.out.println("--------------------------------------------");
    System.out.println(LC215KthLargestElemInArr.findKthLargest3(new int[]{3, 2, 1, 5, 6, 4}, 2));
    System.out.println(LC215KthLargestElemInArr.findKthLargest3(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
  }
}
