## Problem 2096. Step-By-Step Directions From a Binary Tree Node to Another

https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/

You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.

Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:

'L' means to go from a node to its left child node.
'R' means to go from a node to its right child node.
'U' means to go from a node to its parent node.
Return the step-by-step directions of the shortest path from node s to node t.

 

 Example 1:


Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
Output: "UURL"
Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.

Example 2:


Input: root = [2,1], startValue = 2, destValue = 1
Output: "L"
Explanation: The shortest path is: 2 → 1.
 

Constraints:

The number of nodes in the tree is n.
2 <= n <= 105
1 <= Node.val <= n
All the values in the tree are unique.
1 <= startValue, destValue <= n
startValue != destValue


## Solution

the idea is to find nodes (a, b) lowest common ancestors, then construct a step-by-step directions from LCA. 

steps:
1. find LCA of two given nodes 
2. from LCA node, construct a step by step directions

**Time Complexity:** O(N)
**Space Complexity:** O(N)


```python
class Solution:
    def getDirections(self, root: Optional[TreeNode], startValue: int, destValue: int) -> str:
        if not root:
            return ""
        
        lca = self.getLCA(root, startValue, destValue)

        queue = collections.deque([(lca, '')])
        path_start = path_end = None
        while queue:
            curr = queue.popleft()
            if curr.val == startValue:
                path_start = path
            if curr.val == destValue:
                path_end = path
            if curr.left:
                queue.append((curr.left, path + "L"))
            if curr.right:
                queue.append((curr.right, path + "R"))

        return "U" * len(path_start) + path_end

    def getLCA(root, start, end):
        if not root:
            return
        if root.val in (start, end):
            return root
        left = self.getLCA(root.left, start, end)
        right = self.getLCA(root.right, start, end)
        if left and right:
            return root
        return left or right



```
