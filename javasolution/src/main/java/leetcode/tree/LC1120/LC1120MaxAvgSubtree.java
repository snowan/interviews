package leetcode.tree.LC1120;

import leetcode.tree.TreeNode;

/**
 * 1120. Maximum Average Subtree
 * Given the root of a binary tree, find the maximum average value of any subtree of that tree.
 *
 * (A subtree of a tree is any node of that tree plus all its descendants. The average value of a tree is the sum of its values, divided by the number of nodes.)
 *
 * Example 1:
 *
 * Input: [5,6,1]
 * Output: 6.00000
 * Explanation:
 * For the node with value = 5 we have and average of (5 + 6 + 1) / 3 = 4.
 * For the node with value = 6 we have and average of 6 / 1 = 6.
 * For the node with value = 1 we have and average of 1 / 1 = 1.
 * So the answer is 6 which is the maximum.
 *
 * Note:
 *
 * The number of nodes in the tree is between 1 and 5000.
 * Each node will have a value between 0 and 100000.
 * Answers will be accepted as correct if they are within 10^-5 of the correct answer.
 */
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
