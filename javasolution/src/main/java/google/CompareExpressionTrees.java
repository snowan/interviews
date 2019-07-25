package google;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    Set<String> set1 = new HashSet<>(Arrays.asList(sb1.toString().split("\\+")));
    Set<String> set2 = new HashSet<>(Arrays.asList(sb2.toString().split("\\+")));
    boolean res = set1.size() == set2.size() && set1.containsAll(set2);
    System.out.println(res);
    return res;
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
    String[] nodes1 = {"+", "a", "+", null, null, "c", "de"}; //{"+", "a", "b"}; //
    String[] nodes2 = {"+", "+", "de", "a", "c"}; //{"+", "b", "a"}; //
    TreeNode r1 = CompareExpressionTrees.buildTree(nodes1, new TreeNode(null), 0);
    TreeNode r2 = CompareExpressionTrees.buildTree(nodes2, new TreeNode(null), 0);
    CompareExpressionTrees.equalExpressionTrees(r1, r2);
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
