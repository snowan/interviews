package leetcode.unionfind;

import java.util.Arrays;

public class QuickFind {
  int[] id;

  public QuickFind(int N) {
    id = new int[N];
    for (int i = 0; i < N; i++) {
      id[i] = i;
    }
  }

  public boolean find(int p, int q) {
    return id[p] == id[q];
  }

  public void union(int p, int q) {
    int pid = id[p];
    int qid = id[q];
    for (int i = 0; i < id.length; i++) {
      if (id[i] == qid) {
        id[i] = pid;
      }
    }
  }

  public static void main(String[] args) {
    QuickFind test = new QuickFind(10);
    System.out.println(Arrays.toString(test.id));
    test.union(2,9);
    test.union(3,2);
    test.union(3,4);
    test.union(5,6);
    System.out.println(Arrays.toString(test.id));
    System.out.println(test.find(2,9));
  }
}
