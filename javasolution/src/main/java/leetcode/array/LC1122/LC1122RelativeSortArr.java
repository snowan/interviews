package leetcode.array.LC1122;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 1122. Relative Sort Array
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 *
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.
 * Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
 *
 * Example 1:
 *
 * Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * Output: [2,2,2,1,4,3,3,9,6,7,19]
 *
 * Constraints:
 *
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * Each arr2[i] is distinct.
 * Each arr2[i] is in arr1.
 */
public class LC1122RelativeSortArr {
  /**
   * Solution: map arr2 value and index
   * sort arr1 based on map on index
   */
  public int[] relativeSortArray(int[] arr1, int[] arr2) {
    if (arr1 == null || arr1.length == 0) return arr1;
    if (arr2 == null || arr2.length == 0) {
      Arrays.sort(arr1);
      return arr1;
    }
    Map<Integer, Integer> indexMap = new HashMap<>();

    for (int i = 0; i < arr2.length; i++) {
      indexMap.put(arr2[i], i);
    }
    List<ElemIndex> res = new ArrayList<>();
    List<Integer> rest = new ArrayList<>();
    for (int num : arr1) {
      if (indexMap.containsKey(num)) {
        res.add(new ElemIndex(num, indexMap.get(num)));
      } else {
        rest.add(num);
      }
    }
    res.sort((a, b) -> a.index - b.index);
    rest.sort((a, b) -> a - b);
    int[] ans = new int[arr1.length];
    int idx = 0;
    for (ElemIndex ele : res) {
      ans[idx++] = ele.val;
    }
    for (int num : rest) {
      ans[idx++] = num;
    }
    return ans;
  }
  class ElemIndex {
    int val;
    int index;
    public ElemIndex(int val, int index) {
      this.val = val;
      this.index = index;
    }
  }

  public int[] relativeSortArray2(int[] arr1, int[] arr2) {
    if (arr1 == null || arr1.length == 0) return arr1;
    if (arr2 == null || arr2.length == 0) {
      Arrays.sort(arr1);
      return arr1;
    }
    Map<Integer, Integer> indexMap = new HashMap<>();
    final int len = arr2.length;
    for (int i = 0; i < len; i++) {
      indexMap.put(arr2[i], i);
    }

    List<Integer> res = Arrays.stream(arr1).mapToObj(Integer::new).collect(Collectors.toList());
    res.sort((a, b) -> !indexMap.containsKey(a) && !indexMap.containsKey(b)
        ? (a - b) : (indexMap.getOrDefault(a, len) - indexMap.getOrDefault(b, len)));
    int[] ans = new int[arr1.length];
    for (int i = 0; i < arr1.length; i++) {
      ans[i] = res.get(i);
    }
    return ans;
  }
}
