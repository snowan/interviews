package leetcode.dpgreedy.LC1167;

import java.util.PriorityQueue;

/**
 * 1167. Minimum Cost to Connect Sticks
 * https://leetcode.com/problems/minimum-cost-to-connect-sticks/
 * You have some sticks with positive integer lengths.
 *
 * You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y.
 * You perform this action until there is one stick remaining.
 *
 * Return the minimum cost of connecting all the given sticks into one stick in this way.
 *
 * Example 1:
 *
 * Input: sticks = [2,4,3]
 * Output: 14
 * Example 2:
 *
 * Input: sticks = [1,8,3,5]
 * Output: 30
 *
 * Constraints:
 *
 * 1 <= sticks.length <= 10^4
 * 1 <= sticks[i] <= 10^4
 */

public class LC1167MinCost2ConnectSticks {
  /**
   * MinHeap, PQ
   *
   * TC: O(nlogn)
   * SC: O(n)
   *
   * similar problem: https://leetcode.com/problems/minimum-cost-to-merge-stones/
   */
  public static int connectSticks(int[] sticks) {
    if (sticks == null || sticks.length == 0) return 0;
    if (sticks.length == 1) return sticks[0];
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int s : sticks) {
      pq.offer(s);
    }
    int costs = 0;
    while (pq.size() > 1) {
      int currCost = pq.poll() + pq.poll();
      pq.offer(currCost);
      costs += currCost;
    }
    System.out.println("Costs: " + costs);
    return costs;
  }

  public static void main(String[] args) {
    LC1167MinCost2ConnectSticks.connectSticks(new int[]{1, 8, 3, 5});
    LC1167MinCost2ConnectSticks.connectSticks(new int[]{2, 4, 3});
    LC1167MinCost2ConnectSticks.connectSticks(new int[]{3354,4316,3259,4904,4598,474,3166,6322,8080,9009});
  }
}
