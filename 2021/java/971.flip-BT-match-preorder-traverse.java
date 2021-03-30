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
 // traverse start from index = 0 (preorder: root -> left -> right)
 // 1. start from root, check current value == root, if not, cannot flip, -1. 
 // 2. index++ move to next one (left)
 // 3. check left node, value != current val, and right node == current value, can flip, record current node val into res, flip to traverse right, then 
 //    continue traverse left. 
 // 4. if left node == current val, then continue to traverse left node, then traverse right value.
class Solution {
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> res = new ArrayList<>();
        
        return dfs(root, voyage, 0, res) != -1 ? res : Arrays.asList(-1);
    }
    private int dfs(TreeNode node, int[] v, int idx, List<Integer> res) {
        if (node == null) return idx;
        if (idx == -1 || node.val != v[idx]) return -1;
        idx++;
        if (node.left != null && node.left.val != v[idx] && node.right != null && node.right.val == v[idx]) {
            res.add(node.val);
            int nextIdx = dfs(node.right, v, idx, res);
            return dfs(node.left, v, nextIdx, res);
        }
        int nIdx = dfs(node.left, v, idx, res);
        return dfs(node.right, v, nIdx, res);
    }
}
