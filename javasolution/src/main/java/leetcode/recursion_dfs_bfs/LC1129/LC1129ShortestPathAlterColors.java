package leetcode.recursion_dfs_bfs.LC1129;

import java.util.*;

/**
 * 1129. Shortest Path with Alternating Colors
 * Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  In this graph, each edge is either red or blue,
 * and there could be self-edges or parallel edges.
 *
 * Each [i, j] in red_edges denotes a red directed edge from node i to node j.
 * Similarly, each [i, j] in blue_edges denotes a blue directed edge from node i to node j.
 *
 * Return an array answer of length n, where each answer[X] is the length of the shortest path from node 0 to
 * node X such that the edge colors alternate along the path (or -1 if such a path doesn't exist).
 *
 * Example 1:
 *
 * Input: n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
 * Output: [0,1,-1]
 * Example 2:
 *
 * Input: n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
 * Output: [0,1,-1]
 * Example 3:
 *
 * Input: n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
 * Output: [0,-1,-1]
 * Example 4:
 *
 * Input: n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
 * Output: [0,1,2]
 * Example 5:
 *
 * Input: n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
 * Output: [0,1,1]
 *
 * Constraints:
 *
 * 1 <= n <= 100
 * red_edges.length <= 400
 * blue_edges.length <= 400
 * red_edges[i].length == blue_edges[i].length == 2
 * 0 <= red_edges[i][j], blue_edges[i][j] < n
 */
public class LC1129ShortestPathAlterColors {
  // Solution: shortest path in a graph, usually use BFS level by level traversal to find a path.
  // if find, then it is shortest path.
  // 1. build a graph for red edges and blue edges. here using one map to build both red and blue edges.
  // use positive number to represent red, negative number to represent blue
  // 2. level by level scan connected nodes, and record steps.
  public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
    int[] res = new int[n];
    Arrays.fill(res, -1);
    Map<Integer, Set<Integer>> map = new HashMap<>();
    // red edges using positive number as key and value, 1 as red color
    buildGraph(map, red_edges, 1);
    // blue edges use negative number as key, and value to distinguish with red edges, -1 as blue color
    buildGraph(map, blue_edges, -1);
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();
    // start from index 0
    queue.add(0);
    int steps = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size-- > 0) {
        int curr = queue.poll();
        if (visited.contains(curr)) continue;
        visited.add(curr);
        int key = Math.abs(curr);
        if (res[key] == -1) res[key] = steps;
        if (!map.containsKey(key)) continue;
        for (int conn : map.get(key)) {
          if (curr * conn > 0) continue;
          queue.add(conn);
        }
      }
      steps++;
    }
    return res;
  }
  private void buildGraph(Map<Integer, Set<Integer>> map, int[][] edges, int color) {
    for (int[] edg : edges) {
      map.computeIfAbsent(edg[0], c -> new HashSet<>()).add(edg[1] * color);
    }
  }
}
