# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def levelOrder(self, root: TreeNode) -> List[List[int]]:
        if not root:
            return []
        
        def dfs(node, depth, res):
            if not node:
                return
            if depth >= len(res):
                res.append([])
            res[depth].append(node.val)
            dfs(node.left, depth + 1, res)
            dfs(node.right, depth + 1, res)
        
        res = []
        dfs(root, 0, res)
        return res
