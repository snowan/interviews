package leetcode.tree.LC1120;

import leetcode.tree.TreeNode;

public class LC1120MaxAvgSubtree {
  class NodeAttr {
    int sum;
    int num;
    public NodeAttr(int sum, int num) {
      this.sum = sum;
      this.num = num;
    }
  }
  private double res = Double.MIN_VALUE;
  public double maximumAverageSubtree(TreeNode root) {
    if (root == null) return 0;
    helper(root);
    return res;
  }
  private NodeAttr helper(TreeNode node) {
    if (node == null) return new NodeAttr(0, 0);
    int num = 1;
    int sum = node.val;
    NodeAttr left = helper(node.left);
    NodeAttr right = helper(node.right);
    sum += left.sum + right.sum;
    num += left.num + right.num;
    res = Math.max(res, (double)sum / (double)num);
    return new NodeAttr(sum, num);
  }
}
