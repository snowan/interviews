package main.leetcode.unionfind.LC1061;

/**
 * 1061. Lexicographically Smallest Equivalent String
 * Given strings A and B of the same length, we say A[i] and B[i] are equivalent characters.
 * For example, if A = "abc" and B = "cde", then we have 'a' == 'c', 'b' == 'd', 'c' == 'e'.
 * <p>
 * Equivalent characters follow the usual rules of any equivalence relation:
 * <p>
 * Reflexivity: 'a' == 'a'
 * Symmetry: 'a' == 'b' implies 'b' == 'a'
 * Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'
 * For example, given the equivalency information from A and B above, S = "eed", "acd", and "aab" are equivalent strings,
 * and "aab" is the lexicographically smallest equivalent string of S.
 * <p>
 * Return the lexicographically smallest equivalent string of S by using the equivalency information from A and B.
 * <p>
 * Example 1:
 * <p>
 * Input: A = "parker", B = "morris", S = "parser"
 * Output: "makkek"
 * Explanation: Based on the equivalency information in A and B, we can group their characters as
 * [m,p], [a,o], [k,r,s], [e,i]. The characters in each group are equivalent and sorted in lexicographical order.
 * So the answer is "makkek".
 * Example 2:
 * <p>
 * Input: A = "hello", B = "world", S = "hold"
 * Output: "hdld"
 * Explanation:  Based on the equivalency information in A and B, we can group their characters as
 * [h,w], [d,e,o], [l,r]. So only the second letter 'o' in S is changed to 'd', the answer is "hdld".
 * Example 3:
 * <p>
 * Input: A = "main.leetcode", B = "programs", S = "sourcecode"
 * Output: "aauaaaaada"
 * Explanation:  We group the equivalent characters in A and B as [a,o,e,r,s,c], [l,p], [g,t] and [d,m],
 * thus all letters in S except 'u' and 'd' are transformed to 'a', the answer is "aauaaaaada".
 * <p>
 * Note:
 * <p>
 * String A, B and S consist of only lowercase English letters from 'a' - 'z'.
 * The lengths of string A, B and S are between 1 and 1000.
 * String A and B are of the same length.
 */
public class LC1061LexicographicallySmallestEquivStr {
  public String smallestEquivalentString(String A, String B, String S) {
    UnionFind uf = new UnionFind();
    int len = A.length();
    for (int i = 0; i < len; i++) {
      int a = A.charAt(i) - 'a';
      int b = B.charAt(i) - 'a';
      int pa = uf.find(a);
      int pb = uf.find(b);
      if (pa == pb) continue;
      uf.union(a, b);
    }
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < S.length(); i++) {
      int curr = S.charAt(i) - 'a';
      res.append((char) (uf.find(curr) + 'a'));
    }
    return res.toString();
  }

  class UnionFind {
    int[] parent;

    public UnionFind() {
      parent = new int[26];
      for (int i = 0; i < 26; i++) {
        parent[i] = i;
      }
    }

    private int find(int idx) {
      return parent[idx] == idx ? idx : find(parent[idx]);
    }

    private void union(int a, int b) {
      int pa = find(a);
      int pb = find(b);
      if (pa == pb) return;
      parent[pa] = pa < pb ? pa : pb;
      parent[pb] = pa < pb ? pa : pb;
    }
  }

  public static void main(String[] args) {
    LC1061LexicographicallySmallestEquivStr test = new LC1061LexicographicallySmallestEquivStr();
    test.smallestEquivalentString("main/leetcode", "programs", "sourcecode");
  }
}
