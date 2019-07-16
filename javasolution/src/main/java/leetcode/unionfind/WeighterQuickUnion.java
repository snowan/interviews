package leetcode.unionfind;

public class WeighterQuickUnion {
  int[] parent;
  int[] rank;

  public WeighterQuickUnion(int N) {
    parent = new int[N];
    rank = new int[N];
    for (int i = 0; i < N; i++) {
      parent[i] = i;
      rank[i] = 1;
    }
  }

  public int root(int x) {
    return parent[x] == x ? x : root(parent[x]);
  }

  public boolean find(int p, int q) {
    return root(p) == root(q);
  }

  public void union(int p, int q) {
    int rp = root(p);
    int rq = root(q);
    if (rank[rp] >= rank[rq]) {
      parent[rq] = rp;
      rank[rp] += rank[rq];
    } else {
      parent[rp] = rq;
      rank[rq] += rank[rp];
    }
  }
}
