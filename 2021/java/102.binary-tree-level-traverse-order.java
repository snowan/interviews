class Solution {
    // BFS
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            while (size-- > 0) {
                TreeNode curr = queue.poll();
                temp.add(curr.val);
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
            res.add(temp);
        }
        return res;
    }
    
    // DFS
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }
    private void dfs(TreeNode node, int depth, List<List<Integer>> res) {
        if (node == null) return;
        if (depth >= res.size()) {
            res.add(new ArrayList<>());
        }
        
        res.get(depth).add(node.val);
        
        dfs(node.left, depth + 1, res);
        dfs(node.right, depth + 1, res);
    }
    
}
