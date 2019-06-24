package leetcode.string.LC422;

import java.util.List;

/**
 * 422. Valid Word Square
 * Given a sequence of words, check whether it forms a valid word square.
 *
 * A sequence of words forms a valid word square if the kth row and column read the exact same string,
 * where 0 ≤ k < max(numRows, numColumns).
 *
 * Note:
 *
 * The number of words given is at least 1 and does not exceed 500.
 * Word length will be at least 1 and does not exceed 500.
 * Each word contains only lowercase English alphabet a-z.
 * Example 1:
 *
 * Input:
 * [
 *   "abcd",
 *   "bnrt",
 *   "crmy",
 *   "dtye"
 * ]
 *
 * Output:
 * true
 *
 * Explanation:
 * The first row and first column both read "abcd".
 * The second row and second column both read "bnrt".
 * The third row and third column both read "crmy".
 * The fourth row and fourth column both read "dtye".
 *
 * Therefore, it is a valid word square.
 * Example 2:
 *
 * Input:
 * [
 *   "abcd",
 *   "bnrt",
 *   "crm",
 *   "dt"
 * ]
 *
 * Output:
 * true
 *
 * Explanation:
 * The first row and first column both read "abcd".
 * The second row and second column both read "bnrt".
 * The third row and third column both read "crm".
 * The fourth row and fourth column both read "dt".
 *
 * Therefore, it is a valid word square.
 * Example 3:
 *
 * Input:
 * [
 *   "ball",
 *   "area",
 *   "read",
 *   "lady"
 * ]
 *
 * Output:
 * false
 *
 * Explanation:
 * The third row reads "read" while the third column reads "lead".
 *
 * Therefore, it is NOT a valid word square.
 */
public class LC422AvalidStrSquare {
  public boolean validWordSquare(List<String> words) {
    if (words == null || words.size() == 0) return true;
    int size = words.size();
    for (int r = 0; r < size; r++) {
      for (int c = 0; c < words.get(r).length(); c++) {
        if (r == c) continue;
        if (c >= size || words.get(c).length() <= r
            || words.get(r).charAt(c) != words.get(c).charAt(r)) {
          return false;
        }
      }
    }
    return true;
  }
}
