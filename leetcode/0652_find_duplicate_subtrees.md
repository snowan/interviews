## Problem: 652 Find duplicate subtrees 

https://leetcode.com/problems/find-duplicate-subtrees/

Given the root of a binary tree, return all duplicate subtrees.

For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with the same node values.

 

 Example 1:

 Input: root = [1,2,3,4,null,2,4,null,null,4]
 Output: [[2,4],[4]]
 

 Example 2:

Input: root = [2,1,1]
Output: [[1]]

Example 3:

Input: root = [2,2,2,3,null,3,null]
Output: [[2,3],[3]]
 

 Constraints:

 The number of the nodes in the tree will be in the range [1, 10^4]
 -200 <= Node.val <= 200


## Solution

preorder traverse tree, and use map to record each path, for example, 1#2#3#null 

for each path, check whether it is in map, already present, found duplicate node, append to the result

note, we only need to record node when the value for path is 1, skip all >1 paths in map 


```python
class Solution:
    def findDuplicateSubtrees(self, root: Optional[TreeNode]) -> List[Optional[TreeNode]]:
        if not root:
            return []

        def dfs(node, path):
            if not node:
                return '#'

            nodeVal = node.val 
            left = dfs(node.left, path)
            right = dfs(node.right, path)

            path = '$'.join(str(nodeVal), left, right)

            if path in path_map:
                path_map[path] += 1
                
                if path_map[path] == 2:
                    res.append(node)
            else:
                path_map[path] = 1

            return path

        res, path_map = [], {}

        dfs(root, '')

        return res

```
