package leetcode.binarysearch.LC4;

/**
 * 4. Median of Two Sorted Arrays
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 */
public class LC4MedianofTwoSortedArray {
  /**
   * Solution #1. Brute force, merge two sorted array into one sorted array, then calculate median.
   *
   * TC: O(m + n) - m is nums1 length, n is nums2 length
   * SC: O(m + n)
   */
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int[] newArr = mergeTwoSortedArray(nums1, nums2);
    int n = newArr.length;
    if (n % 2 == 0) {
      // even
      return (double) (newArr[n / 2] + newArr[n / 2 - 1]) / 2;
    } else {
      // odd
      return (double) newArr[n / 2];
    }
  }
  private int[] mergeTwoSortedArray(int[] nums1, int[] nums2) {
    int m = nums1.length;
    int n = nums2.length;
    int[] res = new int[m + n];
    int i = 0;
    int j = 0;
    int idx = 0;
    while (i < m && j < n) {
      if (nums1[i] <= nums2[j]) {
        res[idx++] = nums1[i++];
      } else {
        res[idx++] = nums2[j++];
      }
    }
    while (i < m) {
      res[idx++] = nums1[i++];
    }
    while (j < n) {
      res[idx++] = nums2[j++];
    }
    return res;
  }

  /**
   * Solution #2. Binary Search
   *
   * TC: O(log(min(m,n))
   */
  public static double findMedianSortedArraysBinarySearch(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length) {
      return findMedianSortedArraysBinarySearch(nums2, nums1);
    }
    int m = nums1.length;
    int n = nums2.length;
    int lo = 0;
    int hi = m;
    while (lo <= hi) {
      // partition A
      int i = lo + (hi - lo) / 2;
      // partition B
      int j = (m + n + 1) / 2 - i;

      int maxLeftA = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
      int minRightA = i == m ? Integer.MAX_VALUE : nums1[i];

      int maxLeftB = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
      int minRightB = j == n ? Integer.MAX_VALUE : nums2[j];

      if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
        if ((m + n) % 2 == 0) {
          return (double) (Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB)) / 2;
        } else {
          return (double) Math.max(maxLeftA, maxLeftB);
        }
      } else if (maxLeftA > minRightB) {
        hi = i - 1;
      } else {
        lo = i + 1;
      }
    }
    return 0.0;
  }

  public static void main(String[] args) {
    System.out.println(LC4MedianofTwoSortedArray.findMedianSortedArraysBinarySearch(new int[]{}, new int[]{1}));
  }
}
