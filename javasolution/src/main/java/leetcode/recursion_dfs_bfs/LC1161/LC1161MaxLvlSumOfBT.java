package leetcode.recursion_dfs_bfs.LC1161;

import leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
 */
public class LC1161MaxLvlSumOfBT {
  public int maxLevelSum(TreeNode root) {
    if (root == null) return 0;
    int minLvl = 1;
    int maxSum = root.val;
    int level = 1;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      int lvlSum = 0;
      while (size-- > 0) {
        TreeNode curr = queue.poll();
        lvlSum += curr.val;
        if (curr.left != null) queue.offer(curr.left);
        if (curr.right != null) queue.offer(curr.right);
      }
      if (lvlSum > maxSum) {
        minLvl = level;
        maxSum = lvlSum;
      }
      level++;
    }
    return minLvl;
  }
}
