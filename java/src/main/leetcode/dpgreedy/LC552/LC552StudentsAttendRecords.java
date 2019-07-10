package main.leetcode.dpgreedy.LC552;

/**
 * 552. Student Attendance Record II
 *
 * Given a positive integer n, return the number of all possible attendance records with length n,
 * which will be regarded as rewardable. The answer may be very large, return it after mod 109 + 7.
 *
 * A student attendance record is a string that only contains the following three characters:
 *
 * 'A' : Absent.
 * 'L' : Late.
 * 'P' : Present.
 * A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 8
 * Explanation:
 * There are 8 records with length 2 will be regarded as rewardable:
 * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * Only "AA" won't be regarded as rewardable owing to more than one absent times.
 */
public class LC552StudentsAttendRecords {
  private static int MOD = (int)1e9 + 7;

  /**
   * Solution: #1. DP, refer to Kjer discussion: https://leetcode.com/problems/student-attendance-record-ii/discuss/101643/Share-my-O(n)-C%2B%2B-DP-solution-with-thinking-process-and-explanation
   *
   */
  public int checkRecord(int n) {
    if (n == 0) return 1;
    if (n == 1) return 3;
    if (n == 2) return 8;
    long[] PL = new long[n + 1]; // number of records contains P or L in length n
    long[] P = new long[n + 1]; // number of records contains P in length n
    P[0] = PL[0] = 1;
    P[1] = 1; // P
    PL[1] = 2; // P or L
    for (int i = 2; i <= n; i++) {
      P[i] = PL[i - 1];
      PL[i] = (P[i] + P[i - 1] + P[i - 2]) % MOD; // P[i-1] can add one 'L', P[i-2] can add two 'L'.
    }
    long res = PL[n];
    // insert 'A' into n - 1 - i length string
    for (int i = 0; i < n; i++) {
      long A = (PL[i] * PL[n - 1 - i]) % MOD;
      res = (res + A) % MOD;
    }
    return (int)res;
  }
}
