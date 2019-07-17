package leetcode;

/**
 * 1118. Number of Days in a Month
 * Given a year Y and a month M, return how many days there are in that month.
 *
 * Example 1:
 *
 * Input: Y = 1992, M = 7
 * Output: 31
 * Example 2:
 *
 * Input: Y = 2000, M = 2
 * Output: 29
 * Example 3:
 *
 * Input: Y = 1900, M = 2
 * Output: 28
 *
 * Note:
 *
 * 1583 <= Y <= 2100
 * 1 <= M <= 12
 */
public class LC1118NumberDaysInMon {
  public static int numberOfDays(int Y, int M) {
    return (java.time.YearMonth.of(Y, M).lengthOfMonth());
  }

  public static void main(String[] args) {
    System.out.println(LC1118NumberDaysInMon.numberOfDays(1992, 7));
    System.out.println(LC1118NumberDaysInMon.numberOfDays(2000, 2));
    System.out.println(LC1118NumberDaysInMon.numberOfDays(1900, 2));
  }
}
