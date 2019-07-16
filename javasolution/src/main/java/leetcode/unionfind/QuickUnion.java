package leetcode.unionfind;

import java.util.Arrays;

public class QuickUnion {
  int[] parent;
  public QuickUnion(int N) {
    parent = new int[N];
    for (int i = 0; i < N; i++) {
      parent[i] = i;
    }
  }

  public int root(int p) {
    while (p != parent[p]) p = parent[p];
    return p;
  }

  public boolean find(int p, int q) {
    return root(p) == root(q);
  }

  public void union(int p, int q) {
    int rp = root(p);
    int rq = root(q);
    parent[rp] = rq;
  }

  public static void main(String[] args) {
    QuickFind test = new QuickFind(10);
    System.out.println(Arrays.toString(test.id));
    test.union(2, 9);
    test.union(3, 2);
    test.union(3, 4);
    test.union(5, 6);
    System.out.println(Arrays.toString(test.id));
    System.out.println(test.find(2, 9));
  }
}
