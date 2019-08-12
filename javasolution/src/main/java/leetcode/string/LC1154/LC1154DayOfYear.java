package leetcode.string.LC1154;


import java.time.LocalDate;

/**
 * 1154. Day of the Year
 * Given a string date representing a Gregorian calendar date formatted as YYYY-MM-DD, return the day number of the year.
 *
 * Example 1:
 *
 * Input: date = "2019-01-09"
 * Output: 9
 * Explanation: Given date is the 9th day of the year in 2019.
 * Example 2:
 *
 * Input: date = "2019-02-10"
 * Output: 41
 * Example 3:
 *
 * Input: date = "2003-03-01"
 * Output: 60
 * Example 4:
 *
 * Input: date = "2004-03-01"
 * Output: 61
 *
 * Constraints:
 *
 * date.length == 10
 * date[4] == date[7] == '-', and all other date[i]'s are digits
 * date represents a calendar date between Jan 1st, 1900 and Dec 31, 2019.
 */
public class LC1154DayOfYear {
  public static int dayOfYear(String date) {
    return LocalDate.parse(date).getDayOfYear();
  }

  public static void main(String[] args) {
    System.out.println(LC1154DayOfYear.dayOfYear("2019-01-09"));
    System.out.println(LC1154DayOfYear.dayOfYear("2019-02-10"));
    System.out.println(LC1154DayOfYear.dayOfYear("2003-03-01"));
    System.out.println(LC1154DayOfYear.dayOfYear("2004-03-01"));
  }
}
