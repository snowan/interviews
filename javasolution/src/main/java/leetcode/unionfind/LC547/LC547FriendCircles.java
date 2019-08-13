package leetcode.unionfind.LC547;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 547. Friend Circles
 * <p>
 * There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature.
 * For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C.
 * And we defined a friend circle is a group of students who are direct or indirect friends.
 * <p>
 * Given a N*N matrix M representing the friend relationship between students in the class.
 * If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not.
 * And you have to output the total number of friend circles among all the students.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [[1,1,0],
 * [1,1,0],
 * [0,0,1]]
 * Output: 2
 * Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
 * The 2nd student himself is in a friend circle. So return 2.
 * Example 2:
 * <p>
 * Input:
 * [[1,1,0],
 * [1,1,1],
 * [0,1,1]]
 * Output: 1
 * Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
 * so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
 * Note:
 * <p>
 * N is in range [1,200].
 * M[i][i] = 1 for all students.
 * If M[i][j] = 1, then M[j][i] = 1.
 */
public class LC547FriendCircles {
  public int findCircleNumBFS(int[][] M) {
    if (M == null || M.length == 0) return 0;
    int numCircle = 0;
    int n = M.length;
    boolean[] visited = new boolean[n];
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      // already visited, skip
      if (visited[i]) continue;
      queue.add(i);
      while (!queue.isEmpty()) {
        int curr = queue.poll();
        visited[curr] = true;
        for (int j = 0; j < n; j++) {
          if (M[curr][j] == 1 && !visited[j]) {
            queue.add(j);
          }
        }
      }
      numCircle++;
    }
    return numCircle;
  }

  public int findCircleNumDFS(int[][] M) {
    if (M == null || M.length == 0 || M[0].length == 0) return 0;
    int n = M.length;
    int countCircle = 0;
    boolean[] visited = new boolean[n];
    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        dfs(M, i, visited, n);
        countCircle++;
      }
    }
    return countCircle;
  }

  private void dfs(int[][] M, int i, boolean[] visited, int n) {
    for (int j = 0; j < n; j++) {
      if (M[i][j] == 1 && !visited[j]) {
        visited[j] = true;
        dfs(M, j, visited, n);
      }
    }
  }

  /**
   * Solution #3. UnionFind
   *
   * TC: O(n*n*log(n)) - weighted union find, union and find take O(logn)
   * SC: O(n) - parent and rank array of size n.
   */
  public int findCircleNumUF(int[][] M) {
    if (M == null || M.length == 0 || M[0].length == 0) return 0;
    int n = M.length;
    UnionFind uf = new UnionFind(n);
    for (int i = 0; i < n - 1; i++) {
      for (int j = i + 1; j < n; j++) {
        if (M[i][j] == 1) {
          uf.union(i, j);
        }
      }
    }
    return uf.count;
  }

  public static void main(String[] args) {
    int[][] M = {{1,1,1,0,0},{1,1,0,1,0},{1,0,1,0,0},{0,1,0,1,0},{0,0,0,0,1}};
    new LC547FriendCircles().findCircleNumUF(M);
  }
}

class UnionFind {
  int count;
  int[] parent;
  int[] rank;

  public UnionFind(int n) {
    count = n;
    parent = new int[n];
    rank = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = i;
    }
  }

  public int find(int a) {
    return parent[a] == a ? a : find(parent[a]);
  }

  public void union(int a, int b) {
    int rootA = find(a);
    int rootB = find(b);
    if (rootA == rootB) return;
    if (rank[rootA] < rank[rootB]) {
      parent[rootA] = rootB;
      rank[rootA] += rank[rootB];
    } else {
      parent[rootB] = rootA;
      rank[rootB] += rank[rootA];
    }
    count--;
    System.out.println("current array, union (" + a + ", " + b + "), "  + Arrays.toString(parent));
  }

  public int count() {
    return count;
  }
}
