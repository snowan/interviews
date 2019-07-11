package leetcode.dpgreedy.LC801;

import java.util.Arrays;

/**
 * 801. Minimum Swaps To Make Sequences Increasing
 * We have two integer sequences A and B of the same non-zero length.
 *
 * We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their
 * respective sequences.
 *
 * At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing
 * if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)
 *
 * Given A and B, return the minimum number of swaps to make both sequences strictly increasing.
 * It is guaranteed that the given input always makes it possible.
 *
 * Example:
 * Input: A = [1,3,5,4], B = [1,2,3,7]
 * Output: 1
 * Explanation:
 * Swap A[3] and B[3].  Then the sequences are:
 * A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
 * which are both strictly increasing.
 * Note:
 *
 * A, B are arrays with the same length, and that length will be in the range [1, 1000].
 * A[i], B[i] are integer values in the range [0, 2000].
 */
public class LC801MinSwapSeqIncr {
  public int minSwap(int[] A, int[] B) {
    int len = A.length;
    int[] swap = new int[len];
    int[] noswap = new int[len];
    Arrays.fill(swap, Integer.MAX_VALUE);
    Arrays.fill(noswap, Integer.MAX_VALUE);
    swap[0] = 1;
    noswap[0] = 0;
    for (int i = 1; i < len; i++) {
      if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
        swap[i] = swap[i - 1] + 1;
        noswap[i] = noswap[i - 1];
      }
      if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
        noswap[i] = Math.min(noswap[i], swap[i - 1]);
        swap[i] = Math.min(swap[i], noswap[i - 1] + 1);
      }
    }
    return Math.min(swap[len - 1], noswap[len - 1]);
  }

  public int minSwap2(int[] A, int[] B) {
    int len = A.length;
    int preswap = 1;
    int prenoswap = 0;
    for (int i = 1; i < len; i++) {
      int swap = Integer.MAX_VALUE;
      int noswap = Integer.MAX_VALUE;
      if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
        swap = preswap + 1;
        noswap = prenoswap;
      }
      if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
        swap = Math.min(swap, prenoswap + 1);
        noswap = Math.min(noswap, preswap);
      }
      preswap = swap;
      prenoswap = noswap;
    }
    return Math.min(preswap, prenoswap);
  }
}
