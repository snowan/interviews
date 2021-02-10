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
    // recursive
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        helper(root, new int[1]);
        return root;
    }
    private void helper(TreeNode node, int[] sum) {
        if (node == null) return;
        helper(node.right, sum);
        node.val += sum[0];
        sum[0] = node.val;
        helper(node.left, sum);
    }
    
    // iterative
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            // put right treenode into stack
            while (node != null) {
                stack.push(node);
                node = node.right;
            }
            // calculate sum, and reset node val
            node = stack.pop();
            sum += node.val;
            node.val = sum;
            
            // after set right node, set node to left, 
            node = node.left;
        }
        return root;
    }
}
