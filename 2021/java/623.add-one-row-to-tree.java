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
    // BFS
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            if (depth != d - 1) {
                int size = queue.size();
                while (size-- > 0) {
                    TreeNode curr = queue.poll();
                    if (curr.left != null) queue.offer(curr.left);
                    if (curr.right != null) queue.offer(curr.right);
                }
                depth++;
            } else {
                int size = queue.size();
                while (size-- > 0) {
                    TreeNode curr = queue.poll();
                    TreeNode newLeft = new TreeNode(v);
                    newLeft.left = curr.left;
                    curr.left = newLeft;
                    
                    TreeNode newRight = new TreeNode(v);
                    newRight.right = curr.right;
                    curr.right = newRight;
                }
            }
        }
        return root;
    }
    
    // DFS
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        }
        dfs(root, v, d, 1);
        return root;
    }
    private void dfs(TreeNode node, int v, int d, int depth) {
        if (node == null) return;
        if (depth < d - 1) {
            dfs(node.left, v, d, depth + 1);
            dfs(node.right, v, d, depth + 1);
        } else {
            TreeNode newLeft = new TreeNode(v);
            newLeft.left = node.left;
            node.left = newLeft;
            
            TreeNode newRight = new TreeNode(v);
            newRight.right = node.right;
            node.right = newRight;
        }
    }
}
