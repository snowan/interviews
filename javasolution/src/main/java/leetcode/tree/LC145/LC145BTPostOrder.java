package leetcode.tree.LC145;

import leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 145. Binary Tree Postorder Traversal
 * Given a binary tree, return the postorder traversal of its nodes' values.
 *
 * Example:
 *
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [3,2,1]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class LC145BTPostOrder {
  // Solution 1: recursive DFS
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) return res;
    postorder(root, res);
    return res;
  }
  private void postorder(TreeNode node, List<Integer> res) {
    if (node == null) return;
    postorder(node.left, res);
    postorder(node.right, res);
    res.add(node.val);
  }

  // Solution #2. Iterative Stack
  public List<Integer> postorderTraversal2(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) return res;
    Stack<TreeNode> stack = new Stack<>();
    TreeNode curr = root;
    while (curr != null || !stack.isEmpty()) {
      if (curr != null) {
        stack.push(curr);
        // root node
        res.add(curr.val);
        // right
        curr = curr.right;
      } else {
        // left
        curr = stack.pop().left;
      }
    }
    // in result order is (root, right, left), reverse ressult to pose order(left, right, root)
    Collections.reverse(res);
    return res;
  }
}
