/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        int[] max = new int[]{Integer.MIN_VALUE};
        dfs(root, max);
        return max[0];
    }
    private int dfs(TreeNode root, int[] max) {
        if (root == null) return 0;
        int left = Math.max(0, dfs(root.left, max));
        int right = Math.max(0, dfs(root.right, max));
        max[0] = Math.max(left + right + root.val, max[0]);
        return Math.max(left, right) + root.val;
    }
}
