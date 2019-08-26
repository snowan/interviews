package leetcode.unionfind.LC1168;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/optimize-water-distribution-in-a-village/
 * There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.
 *
 * For each house i, we can either build a well inside it directly with cost wells[i], or pipe in water from another well to it.
 * The costs to lay pipes between houses are given by the array pipes,
 * where each pipes[i] = [house1, house2, cost] represents the cost to connect house1 and house2 together using a pipe. Connections are bidirectional.
 *
 * Find the minimum total cost to supply water to all houses.
 *
 * Example 1:
 *
 * Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
 * Output: 3
 * Explanation:
 * The image shows the costs of connecting houses using pipes.
 * The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.
 *
 * Constraints:
 *
 * 1 <= n <= 10000
 * wells.length == n
 * 0 <= wells[i] <= 10^5
 * 1 <= pipes.length <= 10000
 * 1 <= pipes[i][0], pipes[i][1] <= n
 * 0 <= pipes[i][2] <= 10^5
 * pipes[i][0] != pipes[i][1]
 */
public class LC1168MinCostDistributionWater {
  /**
   * Graph problem, return minimum distance to connect the graph.
   *
   *  TC: O(ElogE) - E is the number of edges
   *  SC: O(E)
   */
  public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
    List<EdgeCost> costs = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      costs.add(new EdgeCost(0, i, wells[i - 1]));
    }
    for (int[] p : pipes) {
      costs.add(new EdgeCost(p[0], p[1], p[2]));
    }
    Collections.sort(costs);
    int minCosts = 0;
    UnionFind uf = new UnionFind(n);
    for (EdgeCost edge : costs) {
      int rootX = uf.find(edge.node1);
      int rootY = uf.find(edge.node2);
      if (rootX == rootY) continue;
      minCosts += edge.cost;
      uf.union(edge.node1, edge.node2);
      n--;
      if (n == 0) {
        return minCosts;
      }
    }
    return minCosts;
  }

  class EdgeCost implements Comparable<EdgeCost> {
    int node1;
    int node2;
    int cost;
    public EdgeCost(int node1, int node2, int cost) {
      this.node1 = node1;
      this.node2 = node2;
      this.cost = cost;
    }

    @Override
    public int compareTo(EdgeCost o) {
      return this.cost - o.cost;
    }
  }
  class UnionFind {
    int[] parent;
    int[] rank;
    public UnionFind(int n) {
      parent = new int[n + 1];
      for (int i = 0; i <= n; i++) {
        parent[i] = i;
      }
      rank = new int[n + 1];
    }

    public int find(int x) {
      return x == parent[x] ? x : find(parent[x]);
    }
    public void union(int x, int y) {
      int px = find(x);
      int py = find(y);
      if (px == py) return;
      if (rank[px] >= rank[py]) {
        parent[py] = px;
        rank[px] += rank[py];
      } else {
        parent[px] = py;
        rank[py] += rank[px];
      }
    }
  }

  public static void main(String[] args) {
    LC1168MinCostDistributionWater test = new LC1168MinCostDistributionWater();
    System.out.println(test.minCostToSupplyWater(5, new int[]{1,2,2,3,2}, new int[][]{{1,2,1}, {2,3,1}, {4,5,7}}));
  }
}
