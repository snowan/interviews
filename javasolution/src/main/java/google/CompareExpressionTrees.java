package google;

import java.util.*;

public class CompareExpressionTrees {

  public static boolean equalExpressionTrees(TreeNode tree1, TreeNode tree2) {
    if (tree1 == null && tree2 == null) return true;
    if (tree1 == null || tree2 == null) return false;
    StringBuilder sb1 = new StringBuilder();
    getTreeExpression(tree1, sb1);
    System.out.println("tree1 " + sb1.toString());
    StringBuilder sb2 = new StringBuilder();
    getTreeExpression(tree2, sb2);
    System.out.println("tree2 " + sb2.toString());
    Map<String, Integer> plus = new HashMap<>();
    Map<String, Integer> minus = new HashMap<>();
    // e.g. a + de + a + c - d
    // after split: [a, de, a, c-d]
    // after extract string into plus and minus map
    // plus: [{a: 2}, {de, 1}, {c: 1}]
    // minus: [{d: 1}]
    String[] s = sb1.toString().split("\\+");
    for (String str : s) {
      int idx = str.indexOf("-");
      String plusStr = str;
      if (idx > 0) {
        minus.put(str.substring(idx + 1), minus.getOrDefault(str.substring(idx + 1), 0) + 1);
        plusStr = str.substring(0, idx);
      }
      plus.put(plusStr, plus.getOrDefault(plusStr, 0) + 1);
    }

    // compare
    s = sb2.toString().split("\\+");
    for (String str : s) {
      int idx = str.indexOf("-");
      String plusS = str;
      if (idx > 0) {
        if (!minus.containsKey(str.substring(idx + 1))) return false;
        String tmp = str.substring(idx + 1);
        minus.put(tmp, minus.get(tmp) - 1);
        if (minus.get(tmp) == 0) minus.remove(tmp);
        plusS = str.substring(0, idx);
      }
      if (!plus.containsKey(plusS)) return false;
      plus.put(plusS, plus.get(plusS) - 1);
      if (plus.get(plusS) == 0) plus.remove(plusS);
    }

    return minus.size() == 0 && plus.size() == 0;
  }

  private static void getTreeExpression(TreeNode node, StringBuilder sb) {
    if (node == null) return;
    getTreeExpression(node.left, sb);
    if (node.val != null) sb.append(node.val);
    getTreeExpression(node.right, sb);
  }

  private static TreeNode buildTree(String[] nodes, TreeNode root, int idx) {
    if (idx < nodes.length) {
      TreeNode node = new TreeNode(nodes[idx]);
      root = node;
      root.left = buildTree(nodes, root.left, 2 * idx + 1);
      root.right = buildTree(nodes, root.right, 2 * idx + 2);
    }
    return root;
  }

  public static void main(String[] args) {
    String[] nodes1 = {"+", "+", "+", "a", "de", "a", "c"}; //{"+", "a", "b"}; //
    String[] nodes2 = {"+", "+", "de", "a", "c"}; //{"+", "b", "a"}; //
    TreeNode r1 = CompareExpressionTrees.buildTree(nodes1, new TreeNode(null), 0);
    TreeNode r2 = CompareExpressionTrees.buildTree(nodes2, new TreeNode(null), 0);
    System.out.println(CompareExpressionTrees.equalExpressionTrees(r1, r2));
  }
}

class TreeNode {
  String val;
  TreeNode left;
  TreeNode right;

  public TreeNode(String val) {
    this.val = val;
  }
}
