package leetcode.array.LC1109;

/**
 * 1109. Corporate Flight Bookings
 * There are n flights, and they are labeled from 1 to n.
 *
 * We have a list of flight bookings.  The i-th booking bookings[i] = [i, j, k] means that we booked k seats
 * from flights labeled i to j inclusive.
 *
 * Return an array answer of length n, representing the number of seats booked on each flight in order of their label.
 *
 * Example 1:
 *
 * Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * Output: [10,55,45,25,25]
 *
 * Constraints:
 *
 * 1 <= bookings.length <= 20000
 * 1 <= bookings[i][0] <= bookings[i][1] <= n <= 20000
 * 1 <= bookings[i][2] <= 10000
 */
public class LC1109CorporateFlightBookings {
  public int[] corpFlightBookings(int[][] bookings, int n) {
    int[] res = new int[n];
    for (int[] book : bookings) {
      int val = book[2];
      int l = book[0];
      int r = book[1];
      for (int i = l - 1; i < r; i++) {
        res[i] += val;
      }
    }
    return res;
  }

  public int[] corpFlightBookings2(int[][] bookings, int n) {
    int[] res = new int[n];
    for (int[] book : bookings) {
      res[book[0] - 1] += book[2];
      if (book[1] < n) res[book[1]] -= book[2];
    }
    for (int i = 1; i < n; i++) {
      res[i] += res[i - 1];
    }
    return res;
  }
}
