## Problem: 366 Find Leaves of Binary Tree

Description
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

Example
Example1
Input: {1,2,3,4,5}
Output: [[4, 5, 3], [2], [1]].
Explanation:

        1

       / \

      2   3

    / \     

   4   5    
Example2
Input: {1,2,3,4}
Output: [[4, 3], [2], [1]].


## Solution
DFS, bottom up (from leaves to root), mark leaves node as 0, and up level 1, until root 

1. left node layer(depth), leaves layer 0
2. right node layer(depth)
3. current node is the max (left, right) layer + 1
4. add current node to res 
5. return current node layer (depth)

**Time Complexity:** O(n)

**Space Complexity:** O(n)

```python
class Solution:
    def findLeaves(self, root):
        if not root:
            return []

        def dfs(node):
            if not node:
                return -1

            left = dfs(node.left)
            right = dfs(node.right)
            depth = max(left, right) + 1
            if depth == len(res):
                res.append([])
            res[depth].append(node.val)
            return depth
        
        res = []
        dfs(root)
        return res

```
