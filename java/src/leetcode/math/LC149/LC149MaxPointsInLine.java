package leetcode.math.LC149;

import java.util.HashMap;
import java.util.Map;

/**
 * 149. Max Points on a Line
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 *
 * Example 1:
 *
 * Input: [[1,1],[2,2],[3,3]]
 * Output: 3
 * Explanation:
 * ^
 * |
 * |        o
 * |     o
 * |  o
 * +------------->
 * 0  1  2  3  4
 * Example 2:
 *
 * Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * Output: 4
 * Explanation:
 * ^
 * |
 * |  o
 * |     o        o
 * |        o
 * |  o        o
 * +------------------->
 * 0  1  2  3  4  5  6
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition
 * to get new method signature.
 */
public class LC149MaxPointsInLine {
  public int maxPoints(int[][] points) {
    if (points == null) return 0;
    int len = points.length;
    if (len < 2) return len;
    int res = 0;
    Map<String, Integer> map = new HashMap<>();
    for (int i = 0; i < len; i++) {
      map.clear();
      int currMax = 0;
      // number duplicate points
      int dup = 1;
      int[] x = points[i];
      // number points in vertical line
      int line = 0;
      for (int j = i + 1; j < len; j++) {
        int[] y = points[j];
        if (x[0] == y[0] && x[1] == y[1]) {
          dup++;
          continue;
        }
        if (x[0] == y[0]) {
          line++;
          continue;
        }
        int diffX = x[0] - y[0];
        int diffY = x[1] - y[1];
        int gcd = getGcd(diffX, diffY);
        diffX /= gcd;
        diffY /= gcd;
        String slop =  diffY + "/" + diffX;
        map.put(slop, map.getOrDefault(slop, 0) + 1);
        currMax = Math.max(currMax, map.get(slop));
      }
      res = Math.max(res, Math.max(dup + currMax, line + dup));
    }
    return res;
  }

  private int getGcd(int x, int y) {
    if (y == 0) return x;
    return getGcd(y, x % y);
  }
}
