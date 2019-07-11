package leetcode.dpgreedy.LC926;

/**
 * 926. Flip String to Monotone Increasing
 * A string of '0's and '1's is monotone increasing if it consists of some number of '0's (possibly 0),
 * followed by some number of '1's (also possibly 0.)
 *
 * We are given a string S of '0's and '1's, and we may flip any '0' to a '1' or a '1' to a '0'.
 *
 * Return the minimum number of flips to make S monotone increasing.
 *
 * Example 1:
 *
 * Input: "00110"
 * Output: 1
 * Explanation: We flip the last digit to get 00111.
 * Example 2:
 *
 * Input: "010110"
 * Output: 2
 * Explanation: We flip to get 011111, or alternatively 000111.
 * Example 3:
 *
 * Input: "00011000"
 * Output: 2
 * Explanation: We flip to get 00000000.
 *
 * Note:
 *
 * 1 <= S.length <= 20000
 * S only consists of '0' and '1' characters.
 */
public class LC926FlipString2MonotoneIncrease {
  public int minFlipsMonoIncr(String S) {
    int len = S.length();
    if (len < 3) return 0;
    int[] zero = new int[len];
    int[] one = new int[len];
    zero[0] = S.charAt(0) == '0' ? 0 : 1;
    one[0] = S.charAt(0) == '1' ? 0 : 1;
    for (int i = 1; i < len; i++) {
      char curr = S.charAt(i);
      if (curr == '0') {
        zero[i] = zero[i - 1];
        one[i] = 1 + Math.min(zero[i - 1], one[i - 1]);
      } else {
        zero[i] = 1 + zero[i - 1];
        one[i] = Math.min(zero[i - 1], one[i - 1]);
      }
    }

    return Math.min(zero[len - 1], one[len - 1]);
  }

  public int minFlipMonoIncr_2(String S) {
    if (S == null || S.length() < 3) return 0;
    int countZero = 0;
    int countOne = 0;
    for (char ch : S.toCharArray()) {
      countOne = Math.min(countOne, countZero);
      if (ch == '0') {
        countOne++;
      } else {
        countZero++;
      }
    }
    return Math.min(countOne, countZero);
  }
}
