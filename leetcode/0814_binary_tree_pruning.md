## Problem: 814. Binary Tree Pruning

https://leetcode.com/problems/binary-tree-pruning/

Given the root of a binary tree, return the same tree where every subtree (of the given tree) not containing a 1 has been removed.

A subtree of a node node is node plus every node that is a descendant of node.


Example 1:
Input: root = [1,null,0,0,1]
Output: [1,null,0,null,1]
Explanation: 
Only the red nodes satisfy the property "every subtree not containing a 1".
The diagram on the right represents the answer.

Exampe 2:
Input: root = [1,0,1,0,0,0,1]
Output: [1,null,1,null,1]

Example 3:
Input: root = [1,1,0,1,1,0,1,0]
Output: [1,1,0,1,1,null,1]
 

 Constraints:

 The number of nodes in the tree is in the range [1, 200].
 Node.val is either 0 or 1.


## Solutions

recursive to prune subtree that not has 1 

```python

class Solution:
    def pruneTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        if not root:
            return None

        return root if self.dfs(root) else None

    def dfs(self, node):
        if not node:
            return False

        left_has_1 = self.dfs(node.left)
        right_has_1 = self.dfs(node.right)

        # if left subtree has no 1, prune left subtree
        f not left_has_1:
            node.left = None

        # if right subtree has no 1, prune right subtree
        if not right_has_1:
            node.right = None

        # if node.val = 1 or left subtree has 1 or right subtree has 1
        return node.val or left_has_1 or right_has_1


```
