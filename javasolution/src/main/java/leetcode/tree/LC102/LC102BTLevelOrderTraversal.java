package leetcode.tree.LC102;

import leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. Binary Tree Level Order Traversal
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class LC102BTLevelOrderTraversal {
  // DFS (recursive)
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) return res;
    helper(root, res, 0);
    return res;
  }
  private void helper(TreeNode root, List<List<Integer>> res, int level) {
    if (root == null) return;
    if (res.size() <= level) {
      res.add(new ArrayList<>());
    }
    res.get(level).add(root.val);

    helper(root.left, res, level + 1);
    helper(root.right, res, level + 1);
  }

  // BFS, using queue
  public List<List<Integer>> levelOrderBFS(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) return res;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      List<Integer> level = new ArrayList<>();
      while (size-- > 0) {
        TreeNode curr = queue.poll();
        level.add(curr.val);
        if (curr.left != null) queue.offer(curr.left);
        if (curr.right != null) queue.offer(curr.right);
      }
      res.add(level);
    }
    return res;
  }
}
