package leetcode.binarysearch.LC374;

/**
 * 374. Guess Number Higher or Lower
 * We are playing the Guess Game. The game is as follows:
 *
 * I pick a number from 1 to n. You have to guess which number I picked.
 *
 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 *
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 *
 * -1 : My number is lower
 *  1 : My number is higher
 *  0 : Congrats! You got it!
 * Example :
 *
 * Input: n = 10, pick = 6
 * Output: 6
 */
public class LC374GuessNum {
  private static final int pick = 5;
  public int guessNumber(int n) {
    int low = 1;
    int hi = n;
    while (low < hi) {
      int mid = low + (hi - low) / 2;
      int guessNum = guess(mid);
      if (guessNum == 0) {
        return mid;
      } else if (guessNum == 1) {
        low = mid + 1;
      } else if (guessNum == -1) {
        hi = mid;
      }
    }
    return low;
  }

  private int guess(int num) {
    return Integer.compare(num, pick);
  }
}
