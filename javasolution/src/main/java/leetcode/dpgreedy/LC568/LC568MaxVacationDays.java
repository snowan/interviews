package leetcode.dpgreedy.LC568;

import java.util.Arrays;

/**
 * 568. Maximum Vacation Days
 * <p>
 * LeetCode wants to give one of its best employees the option to travel among N cities to collect algorithm problems.
 * But all work and no play makes Jack a dull boy, you could take vacations in some particular cities and weeks.
 * Your job is to schedule the traveling to maximize the number of vacation days you could take,
 * but there are certain rules and restrictions you need to follow.
 * <p>
 * Rules and restrictions:
 * <p>
 * You can only travel among N cities, represented by indexes from 0 to N-1. Initially, you are in the city indexed 0 on Monday.
 * The cities are connected by flights. The flights are represented as a N*N matrix (not necessary symmetrical),
 * called flights representing the airline status from the city i to the city j.
 * If there is no flight from the city i to the city j, flights[i][j] = 0; Otherwise, flights[i][j] = 1.
 * Also, flights[i][i] = 0 for all i.
 * You totally have K weeks (each week has 7 days) to travel. You can only take flights at most once per day and
 * can only take flights on each week's Monday morning. Since flight time is so short,
 * we don't consider the impact of flight time.
 * For each city, you can only have restricted vacation days in different weeks,
 * given an N*K matrix called days representing this relationship. For the value of days[i][j],
 * it represents the maximum days you could take vacation in the city i in the week j.
 * You're given the flights matrix and days matrix, and you need to output the maximum vacation days you could take during K weeks.
 * <p>
 * Example 1:
 * <p>
 * Input:flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[1,3,1],[6,0,3],[3,3,3]]
 * Output: 12
 * Explanation:
 * Ans = 6 + 3 + 3 = 12.
 * <p>
 * One of the best strategies is:
 * 1st week : fly from city 0 to city 1 on Monday, and play 6 days and work 1 day.
 * (Although you start at city 0, we could also fly to and start at other cities since it is Monday.)
 * 2nd week : fly from city 1 to city 2 on Monday, and play 3 days and work 4 days.
 * 3rd week : stay at city 2, and play 3 days and work 4 days.
 * Example 2:
 * <p>
 * Input:flights = [[0,0,0],[0,0,0],[0,0,0]], days = [[1,1,1],[7,7,7],[7,7,7]]
 * Output: 3
 * Explanation:
 * Ans = 1 + 1 + 1 = 3.
 * <p>
 * Since there is no flights enable you to move to another city, you have to stay at city 0 for the whole 3 weeks.
 * For each week, you only have one day to play and six days to work.
 * So the maximum number of vacation days is 3.
 * Example 3:
 * <p>
 * Input:flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[7,0,0],[0,7,0],[0,0,7]]
 * Output: 21
 * Explanation:
 * Ans = 7 + 7 + 7 = 21
 * <p>
 * One of the best strategies is:
 * 1st week : stay at city 0, and play 7 days.
 * 2nd week : fly from city 0 to city 1 on Monday, and play 7 days.
 * 3rd week : fly from city 1 to city 2 on Monday, and play 7 days.
 * Note:
 * <p>
 * N and K are positive integers, which are in the range of [1, 100].
 * In the matrix flights, all the values are integers in the range of [0, 1].
 * In the matrix days, all the values are integers in the range [0, 7].
 * You could stay at a city beyond the number of vacation days, but you should work on the extra days,
 * which won't be counted as vacation days.
 * If you fly from the city A to the city B and take the vacation on that day, the deduction towards
 * vacation days will count towards the vacation days of city B in that week.
 * We don't consider the impact of flight hours towards the calculation of vacation days.
 */
public class LC568MaxVacationDays {
  /**
   * Solution: #1. DP
   * m - the number of cities
   * n - the number of weeks
   * dp[m][n]
   * dp[i][j] - total vacation days can take in week j, at city i
   * <p>
   * init:
   * dp[i][j] = -1
   * dp[0][0] = days[0][0]
   * at city 0, when flights[0][i] == 1 - meaning city 0 can reach city i,
   * dp[i][0] = days[i][0] week 0, total vacation days at city i.
   * <p>
   * Transition:
   * dp[i][j] = max(dp[i][j], dp[oricity][j - 1] + days[i][j]) flights[oricity][i] == 1 || oricity == i
   * <p>
   * result:
   * from 0 to i city at week n - 1, find max
   * return max(dp[0][n-1], dp[1][n-1], ..., dp[m-1][n-1])
   * <p>
   * TC: (m*m*n)
   * SC: O(m*n)
   */
  public int maxVacationDays(int[][] flights, int[][] days) {
    int m = days.length;
    int n = days[0].length;
    int[][] dp = new int[m][n];
    for (int i = 0; i < m; i++) {
      Arrays.fill(dp[i], -1);
    }
    dp[0][0] = days[0][0];
    for (int i = 0; i < m; i++) {
      if (flights[0][i] == 1) dp[i][0] = days[i][0];
    }
    for (int w = 1; w < n; w++) {
      for (int c = 0; c < m; c++) {
        if (dp[c][w - 1] == -1) continue;
        for (int k = 0; k < n; k++) {
          if (k == c || flights[c][k] == 1) {
            dp[k][w] = Math.max(dp[k][w], dp[c][w - 1] + days[k][w]);
          }
        }
      }
    }
    int res = 0;
    for (int i = 0; i < m; i++) {
      res = Math.max(res, dp[i][n - 1]);
    }
    return res;
  }

  /**
   * Solution: #2. Recursive + memorization
   */
  public int maxVacationDaysRec(int[][] flights, int[][] days) {
    int m = days.length;
    int n = days[0].length;
    int[][] dp = new int[m][n];
    return helper(flights, days, dp, 0, 0, m, n);
  }

  private int helper(int[][] flights, int[][] days, int[][] dp, int city, int week, int m, int n) {
    if (week == n) return 0;
    if (dp[city][week] != 0) return dp[city][week];
    int res = 0;
    for (int c = 0; c < m; c++) {
      if (c == city || flights[city][c] == 1) {
        res = Math.max(res, days[c][week] + helper(flights, days, dp, c, week + 1, m, n));
      }
    }
    dp[city][week] = res;
    return res;
  }
}
