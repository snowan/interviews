class Solution {
    // dfs
    // 1. get longest depth
    // 2. dfs traverse tree, when current node is in longest depth, add into result
    public int deepestLeavesSum(TreeNode root) {
        int depth = getDepth(root);
        
        int[] res = new int[1];
        dfs(root, depth, 0, res);
        return res[0];
    }
    private void dfs(TreeNode node, int depth, int currDepth, int[] res) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            if (currDepth == depth - 1)
                res[0] += node.val;
            return;
        }
        dfs(node.left, depth, currDepth + 1, res);
        dfs(node.right, depth, currDepth + 1, res);
        
    }
    private int getDepth(TreeNode node) {
        if (node == null) return 0;
        int left = getDepth(node.left);
        int right = getDepth(node.right);
        return Math.max(left, right) + 1;
    }
    
    
    // BFS,level traverse, do not need to get depth.
    // level by level calculte sum
    // last level is deepest leaves sum.
    public int deepestLeavesSum(TreeNode root) {
        int res = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            res = 0;
            while (size-- > 0) {
                TreeNode curr = queue.poll();
                res += curr.val;
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }
        return res;
    }
}
